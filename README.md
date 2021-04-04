![XPU](https://avatars.githubusercontent.com/u/81394192?s=60&v=4)


[![Build Status](https://travis-ci.org/xpu-pl/html-validator.svg?branch=master)](https://travis-ci.org/github/xpu-pl/html-validator)

# JAVA/Groovy HTML validator
> based on nu.validator:validator

It validates HTML document or just a HTML content (not a fully specified HTML DOC) and returns potential warnings/errors as an easily handled JSONObject.

## Installing / Getting started

Right now we haven't add it yet to JCentral, so feel free, to copy-paste `pl.xpu.html.validator.HtmlContentValidator` and `pl.xpu.html.validator.HtmlDocumentValidator` to your project.


### Initial Configuration

Remember to add required dependency to your build config:

	compile('nu.validator:validator:20.7.2')


## Usage

Basic use case checks if specific HTML content is valid (eg. if all elements are properly closed, empty links, spellings etc.)

```JAVA
new HtmlContentValidator().validateHtmlContent("<span>lol</div>") //for any HTML content
```

```JAVA
new HtmlDocumentValidator().validateHtmlDocument("<!DOCTYPE html><html lang='en'><head><title>t</title></head><body>content</body></html>") //for fully specified HTML DOC
```

## Contributing

If you'd like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.

## Tech stack

 - Gradle 6.8.3 
 	`sdk install gradle 6.8.3; sdk use gradle 6.8.3;`
 - JAVA 13
 	`sdk install java 13.0.1-open; sdk use java 13.0.1-open;`
 	it should be configured also in Project Structure in Intellij



## Licensing

The code in this project is licensed under MIT license.

**Free Software, Hell Yeah!**
