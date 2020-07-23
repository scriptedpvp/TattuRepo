package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JiraLoginPage {
    public JiraLoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "login-form-username")
    public WebElement username;
    @FindBy(id = "login-form-password")
    public WebElement password;
    @FindBy(xpath = "//input[@name='login']")
    public WebElement loginButton;

    public void logIn(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.loginButton.click();
    }
}
