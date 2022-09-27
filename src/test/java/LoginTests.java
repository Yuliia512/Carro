import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            //if(app.getHelperUser().isElementPresent(By.xpath("//a"))){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm("423090@gmail.com","Yy12345$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
    }
    @Test
    public void loginSuccessModels(){


        User user=new User().withEmail("423090@gmail.com").withPassword("Yy12345$");

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm("Noa@gmail.com","Yy12345$");
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();

        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
    }

    @Test
    public void negativeWrongEmail(){

        User user=new User().withEmail("423090gmail.com").withPassword("Yy12345$");

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        // Assert errorMessagge
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        // Assert buttonYalla not active
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }
    @Test
    public void negativeWrongPassword(){
        User user=new User().withEmail("423090@gmail.com").withPassword("Wwow12345$");

        app.getHelperUser().openLoginFormHeader();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Authorization error");
        // Assert text message "Authorization error"

    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
