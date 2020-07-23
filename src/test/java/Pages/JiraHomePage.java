package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JiraHomePage {
public JiraHomePage(WebDriver driver){
    PageFactory.initElements(driver,this);
}
@FindBy(xpath = "//a[.='Boards']")
    public WebElement boardButton;
@FindBy(xpath = "//a[.='TEC board']")
    public WebElement tecBoard;
@FindBy(xpath = "//div[@class='ghx-key']")
    public List<WebElement> keys;
@FindBy(xpath = "//span[@class='ghx-inner']")
    public List<WebElement> summaries;
@FindBy(id = "description-val")
    public WebElement descrip;
}
