import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase{

    @Test(groups = {"smoke"})
    public void searchCurrentMonthSuccess(){

app.getSearch().searchCurrentMonth("Jerusalem, Israel","10/28/2022","10/30/2022");
app.getSearch().submit();
        Assert.assertTrue(app.getSearch().isListOfCarsAppeared());
    }

   @Test
    public void searchNextMonthSuccess(){

        app.getSearch().searchNextMonth("Tel Aviv, Israel","11/25/2022","11/30/2022");
        app.getSearch().submit();
       Assert.assertTrue(app.getSearch().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriod(){

        app.getSearch().selectAnyPeriod("Haifa, Israel","12/20/2022","1/10/2023");
        app.getSearch().submit();
        Assert.assertTrue(app.getSearch().isListOfCarsAppeared());
        //Assert.assertTrue(app.getSearch().isDataCorrect("12/20/2022","1/10/2023"));
    }
    @Test
    public void searchInPast(){

        app.getSearch().typePeriodInPast("Bat Yam, Israel","10/5/2022","10/10/2022");
        app.getSearch().submitWithoutWait();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        //Assert.assertTrue(app.getSearch().IsErrorMessageDisplayed());
    }
@BeforeMethod(alwaysRun = true)
    public void returnHome(){
        app.getSearch().openSearchMenu();
}
}
