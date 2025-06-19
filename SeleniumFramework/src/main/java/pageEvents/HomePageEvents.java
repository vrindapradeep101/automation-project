package pageEvents;

import pageObjects.HomePageElements;
import utils.ElementFetch;

public class HomePageEvents {
	ElementFetch ele=new ElementFetch();
	public void SignInNutton() {
		ele.getWebElement("XPATH", HomePageElements.SigInButtonTexts).click();

	}

}
