//package com.tr.util;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.TimeZone;
//
//import org.apache.log4j.Logger;
//import org.testng.ITestContext;
//
//import junit.framework.TestListener;
//
//
//
///**
// * This Class is used to generate test execution report using TestNG results.
// * 
// * @author mjr
// */
//public class ReportGenerator {
//
//	private final int m_failedTestCount;
//	private final int m_skippedTestCount;
//	private final int m_passedTestCount;
//	private final ITestContext m_testContext;
//	private final StringBuffer m_report = new StringBuffer();
//	private static Logger Log = Logger.getLogger(Logger.class.getName());
//
//	private final String ReportPath = System.getProperty("user.dir") + "\\Reports\\emailablereport.html";
//
//	public ReportGenerator(ITestContext context) {
//		m_testContext = context;
//		m_failedTestCount = context.getFailedTests().getAllResults().size();
//		m_passedTestCount = context.getPassedTests().getAllResults().size();
//		m_skippedTestCount = context.getSkippedTests().getAllResults().size();
//	}
//
//	/**
//	 * This method is sued to generate custom report and send email to the
//	 * mentioned stake holders.
//	 * 
//	 * @param browserValue
//	 * @param emailUsers
//	 * @param basePath
//	 * @throws Exception
//	 */
//	public void generateReport() throws Exception {
//
//		//Logger.info("Generate Report");
//		
//		try {
//
//			createHeader();
//			createBody();
//			createTestExecutionSummary();
//			createTestDetails();
//
//			// sendEmail(m_report.toString(), m_testContext.getSuite().getName()
//			// + " - " + generateDateFormat(), AppCenterConstants.MAILING_LIST);
//
//			sendMailViaExchnageService(AppCenterConstants.MAILING_USERNAME, AppCenterConstants.MAILING_PASSWORD, m_testContext.getSuite().getName() + " - " + generateDateFormat(), m_report.toString(),
//					Arrays.asList(AppCenterConstants.MAILING_LIST.split("\\s*,\\s*")));
//
//			// writeFile(m_report.toString());
//			// sendmailFromVBS(m_report.toString(),
//			// m_testContext.getSuite().getName() + " - " +
//			// generateDateFormat(), AppCenterConstants.MAILING_LIST);
//
//		} catch (Exception e) {
//
//			//Logger.error("Error occured during generation of custom report!! " + e.getMessage());
//			e.printStackTrace();
//
//		}
//
//	}
//
//	public void writeFile(String inputLine) throws IOException {
//		BufferedWriter writer = new BufferedWriter(new FileWriter(ReportPath));
//		try {
//			writer.write(inputLine);
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//			writer.close();
//			return;
//		}
//
//	}
//
//	private void createHeader() {
//
//		m_report.append(
//				"<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><title>TestNG:  Unit Test</title><style type=\"text/css\">table caption,table.info_table,table.param,table.passed,table.failed {margin-bottom:10px;border:1px solid #000099;border-collapse:collapse;empty-cells:show;}table.info_table td,table.info_table th,table.param td,table.param th,table.passed td,table.passed th,table.failed td,table.failed th {border:1px solid #000099;padding:.25em .5em .25em .5em}table.param th {vertical-align:bottom}td.numi,th.numi,td.numi_attn {text-align:center}tr.total td {font-weight:bold}table caption {text-align:center;font-weight:bold;}table.passed tr.stripe td,table tr.passedodd td {background-color: #00AA00;}table.passed td,table tr.passedeven td {background-color: #33FF33;}table.passed tr.stripe td,table tr.skippedodd td {background-color: #cccccc;}table.passed td,table tr.skippedodd td {background-color: #dddddd;}table.failed tr.stripe td,table tr.failedodd td,table.param td.numi_attn {background-color: #FF3333;}table.failed td,table tr.failedeven td,table.param tr.stripe td.numi_attn {background-color: #DD0000;}tr.stripe td,tr.stripe th {background-color: #E6EBF9;}p.totop {font-size:85%;text-align:center;border-bottom:2px black solid}div.shootout {padding:2em;border:3px #4854A8 solid}</style></head><body>");
//		m_report.append("Hi All,");
//		m_report.append("<p>Please find the " + m_testContext.getSuite().getName() + " </p>");
//
//	}
//
//	private void createBody() throws Exception {
//
//		m_report.append(drawPieChart(m_passedTestCount, m_skippedTestCount, m_failedTestCount));
//		m_report.append(getEnvironmentDetails(AppCenterCommonTest.m_url));
//		m_report.append("</body></html>");
//	}
//
//	private void createTestExecutionSummary() {
//
//		int sum = m_passedTestCount + m_skippedTestCount + m_failedTestCount;
//
//		m_report.append("<h4>Test Execution Summary</h4>");
//		m_report.append("<table cellspacing=\"0\" cellpadding=\"0\" width=30% border=3 class=\"param\"> ");
//		String testPackage = m_testContext.getCurrentXmlTest().getClasses().get(0).getName();
//		String[] classArray = testPackage.split("\\.");
//		String testClass = classArray[classArray.length - 1];
//		// String totalTime =
//		// AppCenterUtil.millisecondsToHMS(m_testContext.getEndDate().getTime()
//		// - m_testContext.getStartDate().getTime());
//		String totalTime = AppCenterUtil.millisecondsToHMS(TestListener.m_totalDuration);
//
//		m_report.append("<tr><td align='center'><font  color=blue >Test Class</td>");
//		m_report.append("<td align='center'><font  color=green >Pass</td>");
//		m_report.append("<td align='center'><font  color=red >Fail</td>");
//		m_report.append("<td align='center'><font  color=grey >Skip</td>");
//		m_report.append("<td align='center'><font  color=blue >Total </td>");
//		m_report.append("<td align='center'><font  color=blue >Time (hh:mm:ss)</td></tr>");
//		m_report.append("<tr><td align='center'><font  color=blue >" + testClass + "</td>");
//		m_report.append("<td align='center'><font  color=green >" + m_passedTestCount + "</td>");
//		m_report.append("<td align='center'><font  color=red >" + m_failedTestCount + "</td>");
//		m_report.append("<td align='center'><font  color=grey >" + m_skippedTestCount + "</td>");
//		m_report.append("<td align='center'><font  color=blue >" + sum + "</td>");
//		m_report.append("<td align='center'><font  color=blue >" + totalTime + "</td></tr>");
//
//		// tableData.append("<tr><td colspan=5 align='center'><font
//		// color=blue> Total </td><td align='center'><font color=blue >" +
//		// sum + "</td></tr>");
//		m_report.append("</table>");
//
//	}
//
//	private void createTestDetails() {
//
//		String body = "";
//		Map<String, List<String>> resultMap = TestListener.m_testResultMap;
//
//		int i = 1;
//		for (Map.Entry<String, List<String>> map : resultMap.entrySet()) {
//
//			String font = "";
//			String testMethodName = map.getKey();
//			String status = map.getValue().get(0);
//			String duration = map.getValue().get(1);
//
//			if (status.equalsIgnoreCase("1"))
//				font = "<font color='green'>Pass</font>";
//			else if (status.equalsIgnoreCase("2"))
//				font = "<font color='red'>Fail</font>";
//			else if (status.equalsIgnoreCase("3"))
//				font = "<font color='grey'>Skip</font>";
//
//			body = body + "<tr><td>" + i + "</td><td>" + testMethodName + "</td><td>" + font + "</td><td>" + duration + "</td>" + "</tr>";
//			i++;
//		}
//
//		m_report.append("<h4>Test Details</h4><table width='60%' border='1' cellpadding='0' cellspacing='0' >"
//				+ "<tr bgcolor='99CCFF'><td>S.No</td><td>TestCase</td><td>Result</td><td>Time (mm:ss)</td></tr>" + body + "</table>");
//		m_report.append("</body></html>");
//
//	}
//
//	public void sendEmail(String source, String subject, String mailIds) throws EmailException, MessagingException {
//		if (!"".equals(source)) {
//
//			HtmlEmail email = new HtmlEmail();
//
//			email.setHostName("mailhost.apollogrp.edu");
//			// email.setSmtpPort(587);
//			String to = "Manjunath.Reddy@apollo.edu";
//			email.addTo(to);
//
//			if (mailIds != null && mailIds.contains(",")) {
//				String[] ccArr = mailIds.split(",");
//				for (String t : ccArr) {
//					email.addCc(t);
//				}
//			}
//
//			email.setFrom("Manjunath.Reddy@apollo.edu");
//			email.setSubject(subject);
//			email.setHtmlMsg(source);
//			email.send();
//		}
//	}
//
//	public void sendmailFromVBS(String source, String subject, String mailIDs) throws IOException, InterruptedException {
//
//		String[] command = { "cmd.exe", "/C", "Start", "/wait", System.getProperty("user.dir") + "\\TestMail.vbs", mailIDs, subject, ReportPath };
//		Runtime.getRuntime().exec(command);
//		System.out.println("REPORT GENERATED");
//
//	}
//
//	private String generateDateFormat() {
//		Date date = new Date(System.currentTimeMillis());
//		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy h:mm a");
//		formatter.setTimeZone(TimeZone.getTimeZone("US/Arizona"));
//		return formatter.format(date).toString() + " (MST)";
//	}
//
//	public String parseHtmlSource(String reportURL) throws IOException, URISyntaxException {
//		WebDriver driver = new FirefoxDriver();
//		driver.get(reportURL);
//		String str = driver.getPageSource();
//		driver.close();
//		return str.toString();
//	}
//
//	public String getEnvironmentDetails(String appUrl) throws IOException, URISyntaxException {
//
//		StringBuffer sb = new StringBuffer();
//		sb.append("<table width='40%' border=1 >");
//		sb.append("<th bgcolor='#5D7B9D'  colspan=2><col width=\"40%\">  <col width=\"60\"><font color='#fff' size=3> Environment Details </font></th>");
//		sb.append("<tr>");
//		sb.append("<td ><font  size=2>APPICATION URL </font>	</td><td align='center' size=2> <a href=" + appUrl + "> " + appUrl + "</a></td></tr>");
//		sb.append("<td><font  size=2>BUILD NUMBER </td><td width=20 align='center' size=2>  " + JenkinsConnector.getBuildNo() + " </td> </font></tr>");
//		sb.append("<td><font  size=2>BROWSER TESTED </td><td width=20 align='center' size=2>  " + SeleniumConnector.m_browser + " - " + SeleniumConnector.m_browserVersion + "</td> </font></tr>");
//		sb.append("<td><font  size=2>PLATFORM </td><td width=20 align='center' size=2>  " + SeleniumConnector.m_platform + "</td> </font></tr>");
//		sb.append("<td><font  size=2>DATE OF EXECUTION  </td><td width=20 align='center' size=2>  " + new Date() + " </td> </font></tr>");
//		sb.append("<td><font  size=2>SIMP VERSION  </td><td width=20 align='center' size=2>  " + getSimpVersion() + " </td> </font></tr>");
//		sb.append("<td><font  size=2>APP VERSION  </td><td width=20 align='center' size=2>  " + getAppVersion() + " </td> </font></tr>");
//
//		String reportLink = JenkinsConnector.getBuildUrl() + "/testReport/";
//		sb.append("<td><font  size=2>REPORT LINK </td><td width=20 align='center' size=2>  " + reportLink + " </td> </font></tr>");
//
//		if (SeleniumConnector.m_executionMode.equalsIgnoreCase(AppCenterConstants.MOBILE) || SeleniumConnector.m_executionMode.equalsIgnoreCase(AppCenterConstants.SAUCE_LABS)) {
//			String sauceLabVideoLink = SauceLabConnector.getPublicJobLink();
//			sb.append("<td><font  size=2>SAUCELAB JOB LINK</td><td width=20 align='center' size=2>  " + sauceLabVideoLink + " </td> </font></tr>");
//		}
//
//		sb.append("</table>");
//
//		return sb.toString();
//	}
//
//	public String getTableDetails(String pagesource) throws IOException, URISyntaxException {
//		StringBuffer strBuf = new StringBuffer();
//
//		String strTemp = pagesource.split("</table>")[1];
//
//		int index = strTemp.indexOf("<table cellspacing=\"0\" cellpadding=\"0\" class=\"param\">");
//		if (index == -1) {
//			strTemp = pagesource.split("</table>")[0];
//			index = strTemp.indexOf("<table cellspacing=\"0\" cellpadding=\"0\" class=\"param\">");
//		}
//		strTemp = strTemp.substring(index);
//
//		strBuf.append(strTemp);
//		strBuf.append("</table>");
//
//		return strBuf.toString();
//
//	}
//
//	public String drawPieChart(int passi, int skipi, int faili) {
//		System.out.println("passi " + passi + "  faili " + faili);
//		int total = passi + skipi + faili;
//		String pass = Integer.toString(passi * 100 / total);
//		String skip = Integer.toString(skipi * 100 / total);
//		String fail = Integer.toString(faili * 100 / total);
//
//		String alt = "\" alt=";
//		String googlepiechart = "\"Google Chart";
//		String icon = "\"/>";
//		String urlimg = "\"http://chart.apis.google.com/chart?";
//		String img = "<img src=" + urlimg
//				+ "chtt=Pie+Chart&amp;chts=000000,12&amp;chs=300x150&amp;chf=bg,s,ffffff&amp;cht=p3&amp;chd=t:passtest,failtest,skiptest&amp;chl=Passed|Failed|Skipped&amp;chco=006600,ff0000,ffff00"
//				+ alt + googlepiechart + icon;
//		img = img.replaceAll("passtest", pass);
//		img = img.replaceAll("failtest", fail);
//		img = img.replaceAll("skiptest", skip);
//		return img;
//	}
//
//	private String getVersionUsingJsoup(String url) {
//
//		Document xmlDoc;
//		String version = "";
//		try {
//			AppCenterUtil.doTrustToCertificates();
//			xmlDoc = Jsoup.connect(url).get();
//			Elements links = xmlDoc.select("application");
//			version = links.attr("version");
//		} catch (Exception e) {
//			Logger.info(e.getMessage());
//		}
//
//		return version;
//
//	}
//
//	private String getAppVersion() {
//
//		String url = "";
//		String env = m_testContext.getCurrentXmlTest().getParameter(FrameworkCommonConstants.KEY_TEST_ENVIRONMENT);
//
//		if (env.equalsIgnoreCase(FrameworkCommonConstants.TEST_ENVIRONMENT_PHX_QA1))
//			url = "https://my.qaols.phoenix.edu/apply/health.xml";
//		else if (env.equalsIgnoreCase(FrameworkCommonConstants.TEST_ENVIRONMENT_PROD))
//			url = "https://my.phoenix.edu/apply/health.xml";
//		else if (env.equalsIgnoreCase(AppCenterConstants.TEST_ENVIRONMENT_PHX_MY2QA))
//			url = "https://my2.qaols.phoenix.edu/apply/health.xml";
//
//		String buildVersion = getVersionUsingJsoup(url);
//		Logger.info("App Version:: " + buildVersion);
//
//		return buildVersion;
//	}
//
//	private String getSimpVersion() {
//
//		String url = "";
//		String env = m_testContext.getCurrentXmlTest().getParameter(FrameworkCommonConstants.KEY_TEST_ENVIRONMENT);
//
//		if (env.equalsIgnoreCase(FrameworkCommonConstants.TEST_ENVIRONMENT_PHX_QA1) || env.equalsIgnoreCase(AppCenterConstants.TEST_ENVIRONMENT_PHX_MY2QA))
//			url = "http://adrs-st.qaapollogrp.edu/ad-rs/rest/health.xml";
//		else if (env.equalsIgnoreCase(FrameworkCommonConstants.TEST_ENVIRONMENT_PROD))
//			url = "http://adrs.apollogrp.edu/ad-rs/rest/health.xml";
//
//		String simpVersion = getVersionUsingJsoup(url);
//		Logger.info("SIMP Version:: " + simpVersion);
//
//		return simpVersion;
//	}
//
////	/**
////	 * This method is used to send the mail programmatically using Microsoft
////	 * Exchange Java service
////	 * 
////	 * @param username
////	 * @param password
////	 * @param toAddressList
////	 * @throws Exception
////	 */
////	public void sendMailViaExchnageService(String username, String password, String subject, String body, List<String> toAddressList) throws Exception {
////
////		Logger.info("Enter sendMailViaExchnageService");
////
////		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
////
////		ExchangeCredentials credentials = new WebCredentials(username, password);
////		service.setCredentials(credentials);
////		service.setUrl(new URI("https://outlook.office365.com/EWS/Exchange.asmx"));
////
////		EmailMessage msg = new EmailMessage(service);
////		msg.setSubject(subject);
////		msg.setBody(MessageBody.getMessageBodyFromText(body));
////
////		Iterator<String> mailList = toAddressList.iterator();
////
////		msg.getToRecipients().addSmtpAddressRange(mailList);
////		msg.send();
////
////		Logger.info("Exit sendMailViaExchnageService");
////	}
//
//}
