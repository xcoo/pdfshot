FROM clojure:openjdk-11-lein-buster AS builder
RUN curl -sL https://deb.nodesource.com/setup_12.x | bash - && \
    apt-get install -y nodejs
WORKDIR /build/
COPY project.clj /build/
COPY src /build/src/
RUN lein cljsbuild once

FROM node:12.18.0-alpine

RUN apk update && apk upgrade && \
    echo @edge http://nl.alpinelinux.org/alpine/edge/community >> /etc/apk/repositories && \
    echo @edge http://nl.alpinelinux.org/alpine/edge/main >> /etc/apk/repositories && \
    apk add \
      tini \
      chromium@edge \
      libstdc++@edge \
      nss@edge \
      freetype@edge \
      freetype-dev@edge \
      harfbuzz@edge \
      ttf-freefont@edge \
      curl \
      p7zip && \
    curl -O https://jaist.dl.osdn.jp/users/8/8634/genshingothic-20150607.7z && \
    7z x -ogenshingothic genshingothic-20150607.7z && \
    mv genshingothic/*.ttf /usr/share/fonts/TTF/ && \
    rm -rf genshingothic genshingothic-20150607.7z && \
    fc-cache -fv && \
    apk del --purge curl p7zip && \
    rm -rf /var/cache/apk/*

ENV PUPPETEER_SKIP_CHROMIUM_DOWNLOAD true

WORKDIR /app
RUN npm install puppeteer@3.3.0 express@4.17.1 && \
    mkdir /app/print

RUN addgroup -S pptruser && \
    adduser -S -g pptruser pptruser && \
    mkdir -p /home/pptruser/Downloads /app && \
    chown -R pptruser:pptruser /home/pptruser && \
    chown -R pptruser:pptruser /app

USER pptruser

COPY --from=builder /build/target/pdfshot.js .

ENTRYPOINT ["/sbin/tini", "--"]
CMD ["node", "/app/pdfshot.js"]
