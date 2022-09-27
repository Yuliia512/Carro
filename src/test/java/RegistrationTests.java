import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{


    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test

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



}
