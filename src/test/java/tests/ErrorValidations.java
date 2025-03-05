package tests;

import TestComponents.BaseTest;
import TestComponents.Retry;
import Pages.CartPage;
import Pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;


public class ErrorValidations extends BaseTest
{
    @Test (groups = {"ErrorHandling"},retryAnalyzer = Retry.class,dataProvider = "getLoginData")
    public void loginValidation(HashMap<String, String> input) throws IOException, InterruptedException
    {
        landingPage.loginToApp(input.get("email"), input.get("password"));
        String expectedMessage = input.get("expectedMessage");
        String actualMessage;
        if (input.get("email").isEmpty() && input.get("password").isEmpty())
        {
            actualMessage = landingPage.getFrontendValidationMessage("email") + " " + landingPage.getFrontendValidationMessage("password");
            System.out.println(actualMessage);
        }
        else if (input.get("email").isEmpty() || !input.get("email").contains("@"))
        {
            actualMessage = landingPage.getFrontendValidationMessage("email");
            System.out.println(actualMessage);

        } else if (input.get("password").isEmpty() || input.get("password").length() < 6)
        {
            actualMessage = landingPage.getFrontendValidationMessage("password");
            System.out.println(actualMessage);
        } else
        {
            actualMessage = landingPage.getErrorMessage();
            System.out.println(actualMessage);
        }

        Assert.assertEquals(actualMessage.trim(), expectedMessage.trim());

    }


    @DataProvider
    public Object[][] getLoginData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder.json");
        return new Object[][]{{data.get(3)}, {data.get(4)}, {data.get(5)}, {data.get(6)}, {data.get(7)}};


    }

//    @DataProvider
//    public Object[][] getProductData() throws IOException {
//
//        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder.json");
//        return new Object[][]{{data.get(0)}, {data.get(1)}, {data.get(2)}, {data.get(3)}, {data.get(4)}};
//
//
//    }


}
