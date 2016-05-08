package com.tr.pages;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.internal.Extension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Throwables;
import com.sun.jna.platform.win32.OaIdl.PARAMDESC;
import com.sun.jna.platform.win32.Secur32.EXTENDED_NAME_FORMAT;
import com.sun.jna.platform.win32.WinUser.MOUSEINPUT;
import com.tr.common.MonsterUtil;
import com.tr.util.BasePageObject;

public class HomePage extends BasePageObject

{
	WebDriver driver;
	//WebElement element=null;
	
	//Constructor
		public HomePage(WebDriver driver) 
		{
		super(driver);
		}
		
	/*Variables*/
		
	boolean flag = false;
	List<WebElement> list=null;
	WebElement actualList,homePageTab=null;
	int count = 0;
	int expectedCount=4;
	String ankerLinkText=null;
	Actions act;
	String outPutValue=null;
	String expectedTopJobstext="Top Jobs";
	String txt = null;
	
	/*Web elements*/
	
	By imgSiteLogo = By.xpath("//div[@id='header']//a[contains(@class,'logo')]");
	By lnkHeaderLinks=By.xpath("//div[contains(@class,'mn-mainnav')]//a");
	By lnkMyMonster = By.id("mn-lnk-2");
	By txtJobTitle = By.id("fts_id");
	By scltExperience=By.id("selExp");
	By txtLocation=By.id("lmy");
	By btnFindBetter = By.xpath("//input[@value='Find Better']");
	By lblTopJobs = By.xpath("//span[@class='txt lft']");
			//By.linkText("Top Jobs");
			//By.xpath("//section[@id='bodysection']//div[contains(@class,'tp_jobhead')]//span[contains(@class,'txt')]");
	
	By lnkSignIn = By.xpath("//div[contains(text(),'Sign in')]");
	/**
	 * This method helps us to find the site Logo
	 * @return boolean
	 * @param No param
	 * @throws Exception
	 */
	public boolean isSiteLogoDisplayed() throws Exception
	{
		try 
		{
			flag = isElementPresent(imgSiteLogo);
			if (flag) 
			{
			System.out.println("Site logo is displayed in Home Page");	
			}			
			else
			{
				System.out.println("Site logo is not displayed in Home Page");
			}
		} 
		catch (Exception e) 
		{
			throw new Exception("Site logo is not present in Home page::"+isSiteLogoDisplayed()+e.getLocalizedMessage());
		}
		return flag;
	}
	/** To verify header links in the home page
	 * @return int
	 * @param No param
	 * @throws Exception 
	 */
	public int verifyHeaderCount() throws Exception
	{
		try 
		{
			System.out.println("Verifying header links");
			list=driver.findElements(lnkHeaderLinks);
			count = list.size();
			System.out.println("Header links count::"+count);
			if (count==expectedCount)
			{
			System.out.println("Expected link::"+expectedCount+"::Actual link"+count+" are matching");	
			}
			else
			{
				System.out.println("Expected link::"+expectedCount+"::Actual link"+count+" are not matching");
			}
							
		} 
		catch (Exception e) 
		{
		throw new Exception("Expected and Actual Link counts are not same::"+verifyHeaderLinks());	
		}
		
		return count;
	}
	/**
	 * Display the Page header Anker Link Text details
	 * @return String
	 * @param No Parm
	 * @throws Exception
	 */
	public String verifyHeaderLink() throws Exception
	{
		try 
		{
			list = driver.findElements(lnkHeaderLinks);
			count = list.size();
			for (int i = 0; i <= count; i++)
			{
				actualList=list.get(i);
				outPutValue = actualList.getText();
				System.out.println("Anker Link details are:"+outPutValue);
			}
			
		} 
		catch (Exception e) 
		{
			//Getting Exception here, Need to Check with Manju
			throw new Exception("Not showing the Anker Header Text:"+ankerLinkText);
		}
		return ankerLinkText;
	}
	/**Count the total links in 'My Manster' page
	 * @param No Param
	 * @return List<String>
	 * @throws Showing Error, need to check
	 */
	
	public List<String> verifyHeaderLinks(){
		List<String>lnk = new ArrayList<String>();
		list = driver.findElements(lnkHeaderLinks);
		for (int i = 0;i<list.size();i++) {
			  String link = list.get(i).getText();
			  lnk.add(link);
			  System.out.println("Added header name as:"+link);
		}
		System.out.println("Added all the links");
		return lnk;
	}
	
	public int myMonsterLinks()
	{
		try 
		{
			actualList=null;
			count=0;
			actualList = driver.findElement(lnkMyMonster);
			act = new Actions(driver);
			//act.moveToElement(actualList);
			//Thread.sleep(3000);
			act.click().build().perform();
			list=null;
			list = driver.findElements(By.xpath("//div[@id='mn-navdd-2']/div[contains(@class,'ddnavinnr ')]"));
			count = list.size();
			System.out.println(count);
			
		} 
		catch (Exception e) 
		{
			
		}
		return myMonsterLinks();
	}
	/**To check Job Title Text box is displayed and Enabled
	 * @return boolean;
	 * @param No Param
	 * @throws Exception
	 */
	public boolean isJobTitleTextBoxDisplayed() throws Exception
	{
		try
		{
		//flag = driver.findElement(txtJobTitle).isDisplayed() && driver.findElement(txtJobTitle).isEnabled();
		  flag = setElement(txtJobTitle).isDisplayed()&&setElement(txtJobTitle).isEnabled();
		if (flag)
		{
		System.out.println("Job Title TextBox is Displayed and it is Enabled");	
		}
		else
		{
			System.out.println("Job Title Text Box is either Not displayed OR Not Enabled");
		}
		}
		catch(Exception e)
		{
			throw new Exception("Job title text box is not displayed/enabled:"+isJobTitleTextBoxDisplayed()+e.getLocalizedMessage());
		}
		return flag;
	}
	/**
	 * To Verify Location text box status
	 * @return boolean
	 * @param No param
	 * @throws Exception
	 */
	public boolean isLocationTextBoxDisplayed() throws Exception
	{
		try 
		{
		flag=driver.findElement(txtLocation).isDisplayed() && driver.findElement(txtLocation).isEnabled();
		if (flag)
		{
		System.out.println("Location Drop Down text box is Displayed and Enabled");	
		}
		else
		{
			System.out.println("Location Drop Down text box is Not Displayed OR Enabled");
		}
		}
		catch (Exception e) 
		{
			throw new Exception("Location text box is Not Enabled/Displayed:"+isLocationTextBoxDisplayed()+e.getLocalizedMessage());
		}
		return flag;
	}
	
	/**
	 * To input jobtitle,skills.
	 * @return homePage
	 * @param String jobTitle
	 * @throws Exception
	 */
	public LoginPage enterJObTitle(String jobTitle) throws Exception{
		try {
			element = setElement(txtJobTitle);
			flag = element.isDisplayed();
			Assert.assertTrue(flag, "jobTitle is not displayed");
			clearAndEnterValueInTextBox(txtJobTitle, jobTitle);
			} 
		catch (Exception e) 
		{
			throw new Exception("Failed while entering jobTitle"+e.getLocalizedMessage());
		}
		
		return new LoginPage(driver);
	}
	/**
	 * To check Find Better check Box is displayed.
	 * @return boolean
	 * @param No param
	 * @throws Exception
	 */
	public SearchResultPage clickOnFindBetter(){
		try {
			element = setElement(btnFindBetter);
			flag = element.isDisplayed();
			Assert.assertTrue(flag, "Find button is not displayed");
			element.click();
			waitImplicit(3000);
			} 
		catch (Exception e) 
		{
			
		}
		
		return new SearchResultPage(driver);
	}
	/**
	 * to validate the Top Job Section in Monster Home page
	 * @return String
	 * @throws Exception
	 * @param No param
	 */
	public boolean verifyTopJobs()throws Exception
	 
	{
		try 
		{
			
		//txt = driver.findElement(lblTopJobs).getText();
			txt = setElement(lblTopJobs).getText();
		//Assert.assertEquals(expectedTopJobstext, homePageTab,"Tops Jobs Section not present");
		if (txt.equals(expectedTopJobstext)) 
		{
		System.out.println("Top Jobs section is Present");	
		}
		else
		{
		System.out.println("Top Jobs section is not present");
		}
		}
		catch (Exception e)
		{
			throw new Exception("TopJob Section Not Present:"+verifyTopJobs()+expectedTopJobstext+e.getLocalizedMessage());
		}
		return flag;
		
	}
	
	/**
	 * to Verify SignIn button present in the Home Page
	 * @return boolean
	 * @throws Exception
	 * @param No Param
	 */
	public boolean isSingInButtonPresent() throws Exception
	{
		try 
		{
			element = driver.findElement(lnkSignIn);
			flag = (element.isDisplayed() && element.isEnabled());
			Assert.assertTrue(flag, "Sign button is not present");
			
		} catch (Exception e)
		{
			throw new Exception("Sign-In button Not Present/Enabled:"+isSingInButtonPresent()+e.getLocalizedMessage());
		}
		return flag;
	}
	
	public LoginPage clickOnSignIn() throws Exception{
		
		try {
			MonsterUtil.explicitWait(3000);
			//element = driver.findElement(lnkSignIn);
			element = setElement(lnkSignIn);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			flag = element.isDisplayed();
			if (flag) {
				element.click();
				//MonsterUtil.implicitWait(30);
				waitImplicit(3000);
			}
		} catch (Exception e) {
			throw new Exception("Failed while clicking on Sign in link"+e.getLocalizedMessage());
		}
		return new LoginPage(driver);
	}
}
