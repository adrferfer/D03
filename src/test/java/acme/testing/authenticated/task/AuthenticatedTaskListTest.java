package acme.testing.authenticated.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;


public class AuthenticatedTaskListTest extends AcmePlannerTest {

	/* createPositive
	 *   Caso positivo de crear un grito.
	 *   No se infringe ninguna restricción.
	 *   Se espera que se realice el shout correctamente.
	 * */
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/task/list-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPositive(final int recordIndex, final String title, final String startMoment, final String endMoment, final String workloadHours) {
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Authenticated", "Finished tasks list");
			
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startMoment);
		super.checkColumnHasValue(recordIndex, 2, endMoment);
		super.checkColumnHasValue(recordIndex, 3, workloadHours);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startMoment", startMoment);
		super.checkInputBoxHasValue("endMoment", endMoment);
		super.checkInputBoxHasValue("workloadHours", workloadHours);
		
		super.signOut();
	}
	
	/* listNegative
	 *   Caso negativo de acceso a la lista de tareas sin autentificarse.
	 *   La restricción que se infringe es la de acceso no autorizado al ser un usuario anónimo el que intenta acceder.
	 *   Se espera que salte un panic de acceso no autorizado y que sea capturado.
	 * */
	@Test
	@Order(20)
	public void listNegative() {
		super.navigate("/authenticated/task/list", null);

		super.checkPanicExists();			
	}
	
	
}