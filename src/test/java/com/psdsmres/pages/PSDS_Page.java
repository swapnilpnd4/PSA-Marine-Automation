package com.psdsmres.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.psdsmres.selenium.framework.BasePage;


public class PSDS_Page extends BasePage  {

		// TODO Auto-generated constructor stub
	

		public PSDS_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

		//Locators for Dashboard
		 @FindBy(xpath = "/html/body/app-root/app-psds/div[1]/div[1]/div[1]/div[2]/a")  
		  WebElement Home;
		 
		 @FindBy(xpath = "//*[@id='search']/input")
		  WebElement searchvessel;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[2]/a/i")
		  WebElement backarrow;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[3]/a/i")
		  WebElement farwardarrow;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[7]/select")
		  WebElement Hoursdisabledropdown;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[7]/select/option[2]")
		  WebElement pasthoursenable;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[6]/select")
		  WebElement minsdropdown;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[6]/select/option[1]")
		  WebElement fivemins;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[6]/select/option[2]")
		  WebElement fifteenmins;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[6]/select/option[3]")
		  WebElement Thirtymins;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[6]/select/option[4]")
		  WebElement onehour;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[5]/ng-multiselect-dropdown/div/div[1]/span/span[2]/span")
		  WebElement sectordropdown;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[5]/ng-multiselect-dropdown/div/div[2]/ul[1]/li/div")
		  WebElement selectall;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[5]/ng-multiselect-dropdown/div/div[2]/ul[2]/li[1]/div")
		  WebElement central;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[5]/ng-multiselect-dropdown/div/div[2]/ul[2]/li[2]/div")
		  WebElement northern;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[5]/ng-multiselect-dropdown/div/div[2]/ul[2]/li[3]/div")
		  WebElement eastern;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[5]/ng-multiselect-dropdown/div/div[2]/ul[2]/li[4]/div")
		  WebElement western;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[4]/div/select")
		  WebElement hoursdropdown;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[4]/div/select/option[2]")
		  WebElement twelevehours;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[4]/div/select/option[3]")
		  WebElement eighteenhours;
		 
		 @FindBy(xpath="/html/body/app-root/app-psds/div[1]/div[1]/div[2]/div[4]/div/select/option[2]")
		  WebElement twentyfourhours;
		 
		 
		 
	
	 
	 
    //Locator for Search Pilot Field
    @FindBy(xpath = "//*[@class='das_search']/input")  
    WebElement searchPilotField;
    
    @FindBy(xpath="//*[@data-sortable-index='12']//div[@class='pilot_name']")
    WebElement pilotCode;

    public void SearchPilotField()
    {
   	 if(searchPilotField.isDisplayed())
   	 {
   		 System.out.println("Search Pilot field FOUND");
   	 }
   	 else 
   	 { 
   		 System.out.println("Search Pilot field NOT FOUND!!!");
   	 }
    }
    
    String pilot_code;
    //Search any existing pilot using SEARCH PILOT field
    public void SearchPilot() throws InterruptedException
    {   Thread.sleep(500);
    try
    {
        if(pilotCode.isDisplayed())
   	 {    
   	   pilot_code= pilotCode.getText();
   	   System.out.println("Pilot_Code name is:"+ pilot_code );
   	 }
   	 else
   	 {
   		 System.out.println("No Pilot found");
   	 }
   	 
    }
     catch(NullPointerException e){
   	  System.out.println("No Pilot found1");
     }
   	 Thread.sleep(500);
   	 searchPilotField.click();
   	 
   	 Thread.sleep(500);
   	 searchPilotField.sendKeys(pilot_code);

   	 Thread.sleep(500);
   	 System.out.println("Pilot Code entered");
    }
    
    public void clickhourdropdown()
    {
    	WebElement element=onehour();
    	
    	
    }
private WebElement onehour() {

		return null;
	}

public void clickconplannedjob()
{
	 WebElement element=verifyPlannedJobDetails( );
	 scrollDown();
	doubleclickOn(element);
}


//Verify planned job detail

public WebElement verifyPlannedJobDetails( )
{
	 WebElement element = driver.findElement(By.xpath("//div[contains(text(),'PLANNED')]"));
	                                                                     
	 return element;

}


}
