import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LoginTests extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        logger.info("Start checking authorisation");
        if(app.getHelperUser().isLogged()){
            //if(app.getHelperUser().isElementPresent(By.xpath("//a"))){
            app.getHelperUser().pause(1000);
            app.getHelperUser().logout();
            logger.info("Test has needed logout");
        }else{
            logger.info("Test has not needed logout");

        }
    }
    @Test(dataProvider = "dataLogin", dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email, String password){
        logger.info("Login success started with data: "+email + "password"+password);

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(email, password);
        app.getHelperUser().submit();
        app.getHelperUser().pause(3000);
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("Assert passed");
    }


    @Test(groups = {"smoke"})
    public void loginSuccessModels(){

        logger.info("test started------------Login success Model");
        User user=new User().withEmail("423090@gmail.com").withPassword("Yy12345$");

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm("423090@gmail.com","Yy12345$");
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("Assert (Models) message 'Logged in success' present");
    }

    @Test(dataProvider = "dataModelUser", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelsDP(User user){

        logger.info("Login scenario success was used data"+user.toString());

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("Assert (Models) message 'Logged in success' present");
    }

    @Test
    public void negativeWrongEmail(){
        logger.info("Negative test(wrong email) started-----");
        User user=new User().withEmail("423090@gmail").withPassword("Yy12345$");

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        logger.info("User login with wrong email:   423090@gmail");
        app.getHelperUser().submitWithoutWait();
        // Assert errorMessagge
        //Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Authorization error");
        // Assert buttonYalla not active
        //Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Assert passed - message 'Authorization error' present");

    }
    @Test
    public void negativeWrongPassword(){
        logger.info("Negative test(wrong password) started-----");
        User user=new User().withEmail("423090@gmail.com").withPassword("Wwow12345$");

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        logger.info("User login with wrong password:   Wwow12345$");
        app.getHelperUser().submitWithoutWait();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Authorization error");
        // Assert text message "Authorization error"
        logger.info("Assert passed - message 'Authorization error' present");

    }

    @AfterMethod(alwaysRun = true)
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
