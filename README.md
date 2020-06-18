# pdfshot

PDF screenshot generator for web pages

## Requirements

- Leiningen
- Docker

## Usage

```
$ curl -fsLJO 'localhost:8000/print.pdf?target=https%3A%2F%2Fwww.google.com&wait_for=%23body'
```

- `target` (Required)
  - The URL for which you want to get the screenshot.
  - `https://www.google.com`
- `wait_for` (Optional)
  - Wait for the screenshot until the selector you specified appears on the page.
  - `#body`

## Build Docker image

```
$ lein cljsbuild once
$ docker build -t xcoo/pdfshot:latest .
$ docker-compose up -d
```

## Local execution

```
$ lein cljsbuild once
$ node target/pdfshot.js
```

## License

Copyright [Xcoo, Inc.][xcoo]

Licensed under the [Apache License, Version 2.0][apache-license-2.0].

[xcoo]: https://xcoo.jp
[apache-license-2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
