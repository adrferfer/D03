package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;


public class AnonymousShoutListTest extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String author, final String text, final String info, final String moment) {
		super.clickOnMenu("Anonymous", "Shouts list");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("moment", moment);
		super.checkInputBoxHasValue("author", author);
		super.checkInputBoxHasValue("text", text);
		super.checkInputBoxHasValue("info", info);
	}
	
//	@Test
//	@Order(20)
//	public void listNegative() {
//		super.signIn("administrator", "administrator");
//		
//		super.
//	}

}
