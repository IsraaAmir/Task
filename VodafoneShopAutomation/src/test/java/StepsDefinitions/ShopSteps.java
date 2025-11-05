package StepsDefinitions;

import Helpers.DriverActions;
import Helpers.DriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ShopSteps {

    private String mobilePhone;
    private String passwordUser;
    private String searchItem;
    private WebDriver driver;

    // Homepage and login selectors
    private By loginIcon = By.xpath("//p[contains(text(),'Login')]");
    private By mobileInput = By.xpath("//*[@tabindex='1']");
    private By passwordInput = By.xpath("//*[@tabindex='2']");
    private By loginButton = By.xpath("//input[@value='Go to my account']");
    private By cookieReject = By.id("onetrust-reject-all-handler");
    private By cancelModal = By.cssSelector("button.close-modal-desktop");

    // Products selectors
    private By firstProduct = By.xpath("(//img)[1]");
    private By secondProduct = By.xpath("(//img)[2]");
    private By addToCartButton = By.cssSelector(".add-to-cart");
    private By homeLogo = By.xpath("//img[@alt=\"vodafone's logo\"]");

    // Search & cart selectors
    private By searchBox = By.cssSelector("input[title='Search for:']");
    private By cartIcon = By.cssSelector("img[alt='shopping trolly']");

    public ShopSteps() throws Exception {
       //Read from json files
        String loginJson = new String(Files.readAllBytes(Paths.get("src/main/resources/DataFile.json")));
        JSONObject loginObj = new JSONArray(loginJson).getJSONObject(0);
        mobilePhone = loginObj.getString("MobilePhone");
        passwordUser = loginObj.getString("Password");

        String searchJson = new String(Files.readAllBytes(Paths.get("src/main/resources/SearchData.json")));
        JSONObject searchObj = new JSONArray(searchJson).getJSONObject(0);
        searchItem = searchObj.getString("search");
    }
//Test1
    @Given("User logged to the website")
    public void user_logged_in() {
        WebDriverManager.chromedriver().setup();
        driver = DriverHelper.getInstance();
        driver.get("https://eshop.vodafone.com.eg/en/");
        driver.manage().window().maximize();

        DriverActions.getInstance().click(cookieReject);
        DriverActions.getInstance().click(cancelModal);

        DriverActions.getInstance().click(loginIcon);
        DriverActions.getInstance().sendKeys(mobileInput, mobilePhone);
        DriverActions.getInstance().sendKeys(passwordInput, passwordUser);
        DriverActions.getInstance().click(loginButton);
    }
//Test2
    @When("user selects 2 products from home page")
    public void select_homepage_products() {
        DriverActions.getInstance().click(firstProduct);
        DriverActions.getInstance().click(addToCartButton);
        DriverActions.getInstance().click(homeLogo);

        DriverActions.getInstance().click(secondProduct);
        DriverActions.getInstance().click(addToCartButton);
        DriverActions.getInstance().click(homeLogo);
    }
//Test3
    @And("user adds 1 product from search")
    public void add_product_from_search() {
        DriverActions.getInstance().sendKeys(searchBox, searchItem);
        DriverActions.getInstance().click(addToCartButton);
        DriverActions.getInstance().click(homeLogo);
    }
//Test4
    @Then("user opens the cart to check order")
    public void open_cart() {
        DriverActions.getInstance().click(cartIcon);
        driver.quit();
    }
}
