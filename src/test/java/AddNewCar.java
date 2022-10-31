import manager.DataProviderCar;
import models.Car;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewCar extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        // if (!isLogged)---> login
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("noa@gmail.com").withPassword("Nnoa12345$"));
        }
    }

@Test(dataProvider = "carValidData", dataProviderClass = DataProviderCar.class)
    public  void successDP(Car car){
    Random random = new Random();
    int i =  random.nextInt(1000)+1000;
    car.setCarRegistrationNumber("111-899-" +i);
    logger.info("The test used car model : " +car.toString());

//    Car car = Car.builder()
//            .location("Haifa, Israel")
//            .make("BMW")
//            .model("M5")
//            .year("2020")
//            .engine("2.5")
//            .fuel("Petrol")
//            .gear("AT")
//            .wD("AWD")
//            .doors("5")
//            .seats("4")
//            .clasS("C")
//            .fuelConsumption("6.5")
//            //.carRegistrationNumber("11-000-999")
//            .carRegistrationNumber("11-000-"+i)
//            .price("65")
//            .distanceIncluded("800")
//            .features("Type of features")
//            .about("very nice car")
//            .build();

        app.helperCar().openCarForm();
        app.helperCar().fillCarForm(car);
        app.helperCar().attachPhoto("C:/Users/User/Downloads/Automation/car1.jpeg");
        app.helperCar().submit();

    Assert.assertEquals(app.getHelperUser().getTitleMessage(),"Car added");
    logger.info("In assert checked message 'Car added' in dialog  ");
}

    @Test(groups = {"smoke","sanity"})
    public  void success() {

        Random random = new Random();
        int i = random.nextInt(1000) + 100;

        Car car = Car.builder()
                .location("Haifa, Israel")
                .make("BMW")
                .model("M5")
                .year("2020")
                .engine("2.5")
                .fuel("Petrol")
                .gear("AT")
                .wD("AWD")
                .doors("5")
                .seats("4")
                .clasS("C")
                .fuelConsumption("6.5")
                //.carRegistrationNumber("11-000-999")
                .carRegistrationNumber("11-000-" + i)
                .price("65")
                .distanceIncluded("800")
                .features("Type of features")
                .about("very nice car")
                .build();

        app.helperCar().openCarForm();
        app.helperCar().fillCarForm(car);
        app.helperCar().attachPhoto("C:/Users/User/Downloads/Automation/car1.jpeg");
        app.helperCar().submit();

        Assert.assertEquals(app.getHelperUser().getTitleMessage(), "Car added");
    }


@AfterMethod(alwaysRun = true)
    public void postCondition(){
app.helperCar().returnHomepage();
}
}
