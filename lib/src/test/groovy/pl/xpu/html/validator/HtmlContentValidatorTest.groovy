package pl.xpu.html.validator

import spock.lang.Specification
import spock.lang.Unroll

class HtmlContentValidatorTest extends Specification {

	void "validateHtmlContent: returns JSON withhout any errors"() {
		expect:
			new HtmlContentValidator().validateHtmlContent("lol") == [messages: []]
	}

	void "validateHtmlContent: returns JSON with an errors"() {
		when: "we send not closed span element and not opened dic element"
			def result = new HtmlContentValidator().validateHtmlContent("<span>lol</div>")
		then:
			result.messages.size() == 3
			result.messages.type == ['error', 'error', 'error']
			result.messages[0] == [
					type       : 'error', lastLine: 1, lastColumn: 81, firstColumn: 76,
					message    : 'Stray end tag “div”.', extract: '><span>lol</div></body',
					hiliteStart: 10, hiliteLength: 6]
			result.messages[1] == [
					type       : 'error', lastLine: 1, lastColumn: 88, firstColumn: 82,
					message    : 'End tag for  “body” seen, but there were unclosed elements.', extract: '>lol</div></body></html',
					hiliteStart: 10, hiliteLength: 7]
			result.messages[2] == [
					type       : 'error', lastLine: 1, lastColumn: 72, firstColumn: 67,
					message    : 'Unclosed element “span”.', extract: 'ead><body><span>lol</d',
					hiliteStart: 10, hiliteLength: 6]
	}

	@Unroll
	void "hasUnclosedTags: checks if in HTML content are any unclosed tags"() {
		expect:
			new HtmlContentValidator().hasUnclosedTags(htmlContent) == expectedResult

		where:
			htmlContent                                  || expectedResult
			""                                           || false
			"lol"                                        || false
			"lol<div>"                                   || true
			"lol</div>"                                  || true
			"<div>lol</div>"                             || false
			"<div class='not-relevant-at-all'>lol</div>" || false
	}

	@Unroll
	void "isStartingWithDoctype: check if argument starts with proper DOCTYPE tag"() {
		expect:
			new HtmlContentValidator().isStartingWithDoctype(htmlContent) == expectedResult

		where:
			htmlContent         || expectedResult
			""                  || false
			null                || false
			"anything"          || false
			"<!DOCTYPE> proper" || true
	}

	void "addFakeButValidHtmlStructureForRandomHtmlContent: adds required HTML tags around specific html content"() {
		expect:
			new HtmlContentValidator().addFakeButValidHtmlStructureForRandomHtmlContent("<div>asdf</div>") == "<!DOCTYPE html><html lang='en'><head><title>t</title></head><body><div>asdf</div></body></html>"
	}
}
