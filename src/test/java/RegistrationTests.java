import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{


    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test(dataProvider = "regDataValid", dataProviderClass = DataProviderUser.class,enabled = false)

    public void registrationSuccessDP(User user) {

        logger.info("Login scenario success was used data" + user.toString());

        app.getHelperUser().openRegistrationFormUser();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Registered");
        logger.info("In assert checked message 'Registered' in dialog  ");
    }

    @Test(description = "Bug Jira 00012")

    public void registrationSuccess(){
        System.out.println(System.currentTimeMillis());
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User().withName("John").withLastname("Snow").withEmail("john"+i+"@gmail.com").withPassword("Yy423090$");
    app.getHelperUser().openRegistrationFormUser();
    app.getHelperUser().fillRegistrationForm(user);
    app.getHelperUser().checkPolicyXY();
    app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Registered");
    }

    @Test(groups = {"smoke"})
    public void registrationWrongPassword(){
        User user = new User().withName("Lila").withLastname("Snow").withEmail("lila@mail.com").withPassword("Lila");
        logger.info("Registration  negative scenario with wrong password was used data"+user.toString());
        app.getHelperUser().openRegistrationFormUser();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submitWithoutWait();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("The Y'alla button was not active");
    }

}
