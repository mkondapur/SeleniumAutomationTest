package com.tr.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.tr.common.MonsterUtil;

public class DashboardPage {
	
//************** Declaration Area begins**************************
	WebDriver driver;
	WebElement element = null;
	boolean flag = false;
	String Str_pagetitle = null;
	By img_profilepic = By.xpath("//img[@class='round_ie']");
	String Str_username = null;
	By username = By.xpath("//a[@class ='mn-uname']");
	By completelink = By.xpath("//a[@class ='lft mn-cmplnk']");
	String Str_phoneno = null;
	By phone =By.xpath("//div[@class ='left ar-align pf_metext']");
	String Str_phoneverified = null;
	By phoneverified = By.xpath("//div[@class ='left ar-align pf_metext']/span");
	String Str_email = null;
	By email = By.xpath("//div[@class= 'left pf_metext']");
	String Str_emailverified = null;
	By emailverified = By.xpath("//div[@class= 'left pf_metext']/span");
	String Str_lastupdated = null;
	By lastupdated = By.xpath("//div[@class= 'mn-update']");
	By edit = By.xpath("// a[@class ='mn-uppf_btn mn-eico']");
	By tabs = By.xpath("//div[contains(@class,'tabitem')]");
	By popProfileCompletness = By.linkText("Do not Display Again");
	By imgLogout = By.xpath("//div[@class='uimg']/img");
	By lnkLogOut = By.linkText("Logout");
	List<WebElement>tabslist = new ArrayList<WebElement>();
	String tabclass = null;
	String tabtitle = null;
	
	
//************ Declaration area ends ***********************************
	/**
	 * This is a constructor used to pass the driver
	 * @param driver
	 */
	
	public DashboardPage (WebDriver driver){
		this.driver = driver;
	}
/**
* This method returns the page title.
* @return String page title
 * @throws Exception 
*/
	public String pagetitle() throws Exception{
		try {
			Str_pagetitle = driver.getTitle();
		} catch (Exception e) {
			throw new Exception("FAILED TO GET THE PAGE TITLE:: "+e.getLocalizedMessage());
		}
		
		return Str_pagetitle;
	}
/**
 * This method returns the user name displayed
 * @return String Username
 * @throws Exception
 */
	
	public String getUserName() throws Exception{
		try {
			element = driver.findElement(username);
			flag = element.isDisplayed();
			Assert.assertTrue(flag, "User Name is not displayed");
			Str_username = element.getText();
		} catch (Exception e) {
			throw new Exception("USER NAME NOT FOUND:: "+e.getLocalizedMessage());
		}
		
		return Str_username;
	}
/**
 * This method checks if the complete link is displayed and enabled
 * @return boolean
 * @throws Exception
 */

	public boolean  completelink() throws Exception {
		try {
			element = driver.findElement(completelink);
			flag = element.isDisplayed() && element.isEnabled();
			Assert.assertTrue(flag, "Complete link is not dispalyed and enabled");
		} catch (Exception e) {
			throw new Exception("USER NAME NOT FOUND:: "+e.getLocalizedMessage());
		}
		
		return flag;
	}
	
	public boolean phonedisplayed() throws Exception{
		try {
			element = driver.findElement(phone);
			flag = element.isDisplayed();
			Assert.assertTrue(flag, "Phone Number is not dispalyed and enabled");
		} catch (Exception e) {
			throw new Exception("Phone Number NOT FOUND:: "+e.getLocalizedMessage());
		}
		
		return flag;
	}
	
/**
 * This Method gets the Phone no.
 * @return String Phone No.
 * @throws Exception
 */
	public String  Phone() throws Exception {
		try {
			element = driver.findElement(phone);
			Str_phoneno = element.getText();
		} catch (Exception e) {
			throw new Exception("Phone Number NOT FOUND:: "+e.getLocalizedMessage());
		}
		
		return Str_phoneno;
		
	}
/**
 * This Method retrieves the verification status of the Phone Number
 * @return String phone verified
 * @throws Exception
 */
	
	public String PhoneVerify() throws Exception {
		try {
			element = driver.findElement(phoneverified);
			Str_phoneverified = element.getText();
		} catch (Exception e) {
			throw new Exception("Phone Number NOT FOUND:: "+e.getLocalizedMessage());
		}
		
		return Str_phoneverified;
		
	}
/**
 * This method checks if the email is displayed or not
 * @return boolean flag
 * @throws Exception
 */
	public boolean emailisdisplayed() throws Exception {
		try {
			element = driver.findElement(email);
			flag = element.isDisplayed();
			Assert.assertTrue(flag, "Email is is not dispalyed and enabled");
		} catch (Exception e) {
			throw new Exception("Email Id NOT FOUND:: "+e.getLocalizedMessage());
		}
		return flag;
		
	}
/**
 * This method returns the email displayed
 * @return String email id
 * @throws Exception
 */
	public String email() throws Exception {
		try {
			element = driver.findElement(email);
			Str_email = element.getText();
		} catch (Exception e) {
			throw new Exception("Email Id NOT FOUND:: "+e.getLocalizedMessage());
		}
		return Str_email;
	}
/**
 * This Method retrieves the last updated value
 * @return String Last Updated
 * @throws Exception
 */
	public String lastupdated() throws Exception{
		try {
			element = driver.findElement(lastupdated);
			Str_lastupdated = element.getText();
		} catch (Exception e) {
			throw new Exception("LAST UPDATED NOT FOUND:: "+e.getLocalizedMessage());
		}
		return Str_lastupdated;
	}
/** 
 * This click on edit link 
 * @return driver to view resume page
 * @throws Exception
 */
	/*public view_resumepage clickonedit() throws Exception{
		try {
			element = driver.findElement(edit);
			flag = element.isDisplayed() && element.isEnabled();
			Assert.assertTrue(flag, "Edit button is not displayed");
			element.click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		} catch (Exception e) {
			throw new Exception("Edit Button NOT FOUND:: "+e.getLocalizedMessage());
		}
		return new view_resumepage(driver);
	}*/
/**
 * This Method gets the all the tabs present in the jobs header
 * @return List of tabs
 * @throws Exception
 */
	public  List<WebElement> jobstab() throws Exception{
		try {
			tabslist = driver.findElements(tabs);
		} catch (Exception e) {
			throw new Exception("Jobs Tabs NOT FOUND:: "+e.getLocalizedMessage());
		}
		return tabslist;
	}
	
	
/**
 * This Method is used to click on tab 
 * @param String tabname
 * @return boolean 
 * @throws Exception
 */
	public boolean clicktab(String tabname) throws Exception {
		try {
			By tab = By.name(tabname);
			element = driver.findElement(tab);
			flag = element.isDisplayed() && element.isEnabled();
			Assert.assertTrue(flag, "Tab Not present");
			element.click();
			tabclass = element.getAttribute("Classname");
			
		} catch (Exception e) {
			throw new Exception("Tab "+tabname +" NOT FOUND:: "+e.getLocalizedMessage());
		}
		return flag;
		
	}
	
	public DashboardPage clickOnProfilePopUp(){
		
		try {
			element = driver.findElement(popProfileCompletness);
			if (element.isDisplayed()) {
				element.click();
				MonsterUtil.explicitWait(1000);
			}else {
				System.out.println("Already Pop is closed");
			}
			
		} catch (Exception e) {
			System.out.println("profile completeness pop is not appeared");
	
		}
		
		return new DashboardPage(driver);
	}
	
	public LogOutPage clickOnLogOut() throws Exception{
		element = null;
		try {
			element = driver.findElement(imgLogout);
			flag = element.isDisplayed();
			Assert.assertTrue(flag, "Log out profile is not displayed");
			element.click();
			MonsterUtil.explicitWait(100);
			element = driver.findElement(lnkLogOut);
			element.click();
			MonsterUtil.explicitWait(2000);
			
		} catch (Exception e) {
			
			throw new Exception("Failed while clicking on logout link::"+e.getLocalizedMessage());
		}
		
		return new LogOutPage(driver);
	}
}

