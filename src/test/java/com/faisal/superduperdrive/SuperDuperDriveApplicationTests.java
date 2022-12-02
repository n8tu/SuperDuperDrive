package com.faisal.superduperdrive;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SuperDuperDriveApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		
		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	private void doLogout()
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/logout");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
	}



	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling redirecting users 
	 * back to the login page after a succesful sign up.
	 * Read more about the requirement in the rubric: 
	 * https://review.udacity.com/#!/rubrics/2724/view 
	 */
	@Test
	public void testRedirection() {
		// Create a test account
		doMockSignUp("Redirection","Test","RT","123");
		
		// Check if we have been redirected to the log in page.
		Assertions.assertEquals("http://localhost:" + this.port + "/login", driver.getCurrentUrl());
	}

	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling bad URLs 
	 * gracefully, for example with a custom error page.
	 * 
	 * Read more about custom error pages at: 
	 * https://attacomsian.com/blog/spring-boot-custom-error-page#displaying-custom-error-page
	 */
	@Test
	public void testBadUrl() {
		// Create a test account
		doMockSignUp("URL","Test","UT","123");
		doLogIn("UT", "123");
		
		// Try to access a random made-up URL.
		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertFalse(driver.getPageSource().contains("Whitelabel Error Page"));
	}


	/**
	 * PLEASE DO NOT DELETE THIS TEST. You may modify this test to work with the 
	 * rest of your code. 
	 * This test is provided by Udacity to perform some basic sanity testing of 
	 * your code to ensure that it meets certain rubric criteria. 
	 * 
	 * If this test is failing, please ensure that you are handling uploading large files (>1MB),
	 * gracefully in your code. 
	 * 
	 * Read more about file size limits here: 
	 * https://spring.io/guides/gs/uploading-files/ under the "Tuning File Upload Limits" section.
	 */
	@Test
	public void testLargeUpload() {
		// Create a test account
		doMockSignUp("Large File","Test","LFT","123");
		doLogIn("LFT", "123");

		// Try to upload an arbitrary large file
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		String fileName = "upload5m.zip";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
		WebElement fileSelectButton = driver.findElement(By.id("fileUpload"));
		fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

		WebElement uploadButton = driver.findElement(By.id("uploadButton"));
		uploadButton.click();
		try {
			webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Large File upload failed");
		}
		Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

	}

	@Test
	public void testUnauthorizedUsersAccess(){

		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up",driver.getTitle());

		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login",driver.getTitle());

		driver.get("http://localhost:" + this.port + "/");
		Assertions.assertEquals("Login",driver.getTitle());

		driver.get("http://localhost:" + this.port + "/some-random-page");
		Assertions.assertEquals("Login",driver.getTitle());
	}

	public void openNotesPage(){

		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));

		WebElement navNotes = driver.findElement(By.id("nav-notes-tab"));
		navNotes.click();

	}
	public void createNote(String title, String description){

		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-note-form")));

		WebElement openNoteModal = driver.findElement(By.id("create-note-form"));
		openNoteModal.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		WebElement noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.sendKeys(title);

		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.click();
		noteDescription.sendKeys(description);

		WebElement noteSubmit = driver.findElement(By.id("note-form-submit"));
		noteSubmit.click();

	}

	@Test
	public void testNoteCreation(){

		doMockSignUp("Note Create","Test","LFTt","123");
		doLogIn("LFT", "123");

		String title = "Test Title" , description = "test description";
		openNotesPage();
		createNote(title,description);
		openNotesPage();

		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-note-form")));

		Assertions.assertTrue(driver.getPageSource().contains(title));
		Assertions.assertTrue(driver.getPageSource().contains(description));
	}

	@Test
	public void testNoteEdit(){

		doMockSignUp("Note Edit","Test","LFTtt","123");
		doLogIn("LFT", "123");

		String title = "Test Title" , description = "test description";
		openNotesPage();
		createNote(title,description);
		openNotesPage();

		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-note-form")));


		WebElement edit = driver.findElement(By.id("note-edit-1"));
		edit.click();
		title = "Test Title edited";
		description = "test description edited";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("note-title")));
		WebElement noteTitle = driver.findElement(By.id("note-title"));
		noteTitle.clear();
		noteTitle.sendKeys(title);

		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.clear();
		noteDescription.click();
		noteDescription.sendKeys(description);

		WebElement noteSubmit = driver.findElement(By.id("note-form-submit"));
		noteSubmit.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		openNotesPage();

		Assertions.assertTrue(driver.getPageSource().contains(title));
		Assertions.assertTrue(driver.getPageSource().contains(description));
	}

	@Test
	public void testNoteDelete(){

		doMockSignUp("Note Delete","Test","LFTs","123");
		doLogIn("LFT", "123");

		String title = "Test Title" , description = "test description";
		openNotesPage();
		createNote(title,description);
		openNotesPage();

		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-note-form")));


		WebElement delete = driver.findElement(By.id("note-delete-1"));
		delete.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-notes-tab")));
		openNotesPage();

		Assertions.assertFalse(driver.getPageSource().contains(title));
		Assertions.assertFalse(driver.getPageSource().contains(description));
	}


	// ================== //

	public void openCredentialPage(){

		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));

		WebElement navCred = driver.findElement(By.id("nav-credentials-tab"));
		navCred.click();

	}
	public void createCredential(String url, String username, String password){

		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-credential-form")));

		WebElement openCredModal = driver.findElement(By.id("create-credential-form"));
		openCredModal.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		WebElement credentialUrl = driver.findElement(By.id("credential-url"));
		credentialUrl.sendKeys(url);

		WebElement credentialUsername = driver.findElement(By.id("credential-username"));
		credentialUsername.sendKeys(username);

		WebElement credentialPassword = driver.findElement(By.id("credential-password"));
		credentialPassword.sendKeys(password);

		WebElement credSubmit = driver.findElement(By.id("credential-form-submit"));
		credSubmit.click();

	}

	@Test
	public void testCredentialCreation(){

		doMockSignUp("Credential Create","Test","LFTss","123");
		doLogIn("LFT", "123");

		String url = "www.google.com" , username = "faisal" , password = "123456";
		openCredentialPage();
		createCredential(url,username,password);
		openCredentialPage();

		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-credential-form")));

		Assertions.assertEquals(url,driver.findElement(By.id("credential-url-1")).getText());
		Assertions.assertEquals(username,driver.findElement(By.id("credential-username-1")).getText());
		Assertions.assertNotEquals(password,driver.findElement(By.id("credential-password-1")).getText());
	}

	@Test
	public void testCredentialEdit(){

		doMockSignUp("Credential Edit","Test","LFTsss","123");
		doLogIn("LFT", "123");

		String url = "www.google.com" , username = "faisal" , password = "123456";
		openCredentialPage();
		createCredential(url,username,password);
		openCredentialPage();

		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-credential-form")));


		WebElement edit = driver.findElement(By.id("credential-edit-1"));
		edit.click();
		url = "Test url edited";
		username = "test username edited";
		String newPassword = "1111111";

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url")));
		WebElement credentialUrl = driver.findElement(By.id("credential-url"));
		credentialUrl.clear();
		credentialUrl.sendKeys(url);

		WebElement credentialUsername = driver.findElement(By.id("credential-username"));
		credentialUsername.clear();
		credentialUsername.sendKeys(username);

		WebElement credentialPassword = driver.findElement(By.id("credential-password"));
		credentialPassword.clear();
		credentialPassword.sendKeys(newPassword);

		WebElement credentialSubmit = driver.findElement(By.id("credential-form-submit"));
		credentialSubmit.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
		openCredentialPage();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credential-url-1")));

		Assertions.assertEquals(url,driver.findElement(By.id("credential-url-1")).getText());
		Assertions.assertEquals(username,driver.findElement(By.id("credential-username-1")).getText());
		Assertions.assertNotEquals(newPassword,driver.findElement(By.id("credential-password-1")).getText());
	}

	@Test
	public void testCredentialDelete(){

		doMockSignUp("Credential Delete","Test","LFTz","123");
		doLogIn("LFT", "123");

		String url = "www.google.com" , username = "faisal" , password = "123456";
		openCredentialPage();
		createCredential(url,username,password);
		openCredentialPage();

		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-credential-form")));


		WebElement delete = driver.findElement(By.id("credential-delete-1"));
		delete.click();

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-credentials-tab")));
		openCredentialPage();

		Assertions.assertFalse(driver.getPageSource().contains(url));
		Assertions.assertFalse(driver.getPageSource().contains(username));
	}



}
