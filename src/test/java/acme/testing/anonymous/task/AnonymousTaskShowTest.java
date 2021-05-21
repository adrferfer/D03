package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;


public class AnonymousTaskShowTest extends AcmePlannerTest {
	
	/* showPositive
	 *   Caso positivo de mostrar una tarea.
	 *   No se infringe ninguna restricción.
	 *   Se espera que se muestre la tarea, comprobando los atributos correctamente.
	 * */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/show-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String taskId, final String title, final String startMoment, final String endMoment, 
		final String workloadHours, final String workloadFraction, final String description, final String link, final String ownerName1, final String ownerName2) {
		
		super.clickOnMenu("Anonymous", "Tasks list");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startMoment);
		super.checkColumnHasValue(recordIndex, 2, endMoment);
		super.checkColumnHasValue(recordIndex, 3, workloadHours);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("taskId", taskId);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startMoment", startMoment);
		super.checkInputBoxHasValue("endMoment", endMoment);
		super.checkInputBoxHasValue("workloadHours", workloadHours);
		super.checkInputBoxHasValue("workloadFraction", workloadFraction);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		
        final String ownerName = ownerName1+", "+ownerName2;

        super.checkInputBoxHasValue("ownerName", ownerName);   
	}
	
	/* listNegative
	 *   Caso negativo de mostrar una tarea.
	 *   Se infringe restricción de acceso no autorizado.
	 *   Se espera que se recoja el panic de acceso no autorizado tras loguearnos como authenticated e intentar hacer show.
	 * */
    @Test
    @Order(20)
    public void showNegative() {
        super.clickOnMenu("Anonymous", "Tasks list");
        
        super.clickOnListingRecord(0);
        
        final String currentUrl = super.getCurrentUrl();
        
        super.signIn("administrator","administrator"); //Logueamos como authenticated para hacer saltar el panic de acceso no autorizado.
        
        super.navigate(currentUrl,null);

        super.checkPanicExists();
        
        super.signOut();
    }

}