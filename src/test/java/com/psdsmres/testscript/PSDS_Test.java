package com.psdsmres.testscript;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.psdsmres.dataproviders.DataProviders;
import com.psdsmres.selenium.framework.BaseTest;

public class PSDS_Test  extends BaseTest {
	
	@Factory(dataProvider = "Config", dataProviderClass = DataProviders.class)
	public PSDS_Test(String browser) {
		super(browser);

	}
	
	@Test
	 public void init() throws Exception
		{
			//PSDSpage psdspage;
			
			//Call Search Pilot method
		psds_Page.SearchPilotField();
		//psds_Page.SearchPilot();
		psds_Page.clickconplannedjob();
		}

}
