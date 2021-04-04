package pl.xpu.html.validator

import groovy.json.JsonSlurper

class HtmlContentValidator {
	JsonSlurper jsonSlurper = new JsonSlurper()

	boolean hasUnclosedTags(String content) {
		def errors = validateHtmlContent(content)
		if (errors?.messages) {
			return errors.messages.find {
				if (it.type == 'error') {
					it.message.contains("Unclosed element") || it.message.contains("Stray end tag")
				}
			}
		}
		return false
	}

	def validateHtmlContent(String content) {
		String htmlContent = isStartingWithDoctype(content) ? content : addFakeButValidHtmlStructureForRandomHtmlContent(content)
		String errorsAsString = new HtmlDocumentValidator().validateHtmlDocumentAsString(htmlContent)
		return errorsAsString ? jsonSlurper.parseText(errorsAsString) : null
	}

	boolean isStartingWithDoctype(String content) {
		return content?.startsWith("<!DOCTYPE")
	}

	String addFakeButValidHtmlStructureForRandomHtmlContent(String htmlContent) {
		return "<!DOCTYPE html><html lang='en'><head><title>t</title></head><body>" + htmlContent + "</body></html>"
	}

}
