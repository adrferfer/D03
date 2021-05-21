package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;


public class ManagerWorkplanCreateTest extends AcmePlannerTest {

	/* showPositive
	 *   Caso positivo de mostrar un plan de trabajo como gerente autentificado y navegar a sus tasks.
	 *   No se infringe ninguna restricción.
	 *   Se espera que el plan de trabajo se muestre correctamente y se comprueben los atributos.
	 *   Se comprueba la navegabilidad a las tareas asociada al plan de trabajo
	 * */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void showPositive(final int recordIndex, final String title, final String executionPeriodStart, final String executionPeriodEnd, final String isPublic) {
		super.signIn("manager01", "manager01");
		
		super.clickOnMenu("Manager", "Create workplan");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("executionPeriodStart", executionPeriodStart);
		super.fillInputBoxIn("executionPeriodEnd", executionPeriodEnd);
		
		super.clickOnSubmitButton("Create");
		
		super.clickOnMenu("Manager", "Workplans list");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, executionPeriodStart);
		super.checkColumnHasValue(recordIndex, 2, executionPeriodEnd);

		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("executionPeriodStart", executionPeriodStart);
		super.checkInputBoxHasValue("executionPeriodEnd", executionPeriodEnd);
		super.checkInputBoxHasValue("isPublic", isPublic);
		
		super.signOut();
	}
	
	//TODO: NEGATIVE SHOWs 
	
	
}