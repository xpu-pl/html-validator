package pl.xpu.html.validator

import spock.lang.Specification
import spock.lang.Unroll

class HtmlContentValidatorTest extends Specification {

	void "validateHtmlContent: returns string of possible errors"() {
		expect:
			new HtmlContentValidator().validateHtmlContent("lol") == [messages: []]
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
