package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {


    @DataProvider
    public Iterator<Object[]> dataLogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"423090@gmail.com","Yy12345$"});
        list.add(new Object[]{"423090@gmail.com","Yy12345$"});
        list.add(new Object[]{"423090@gmail.com","Yy12345$"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> regDataValid() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/registrationSuccess.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");  //  ["Noa"] ["Snow"] ["kinoa@mail.com"] ["Nn12345$"]
            list.add(new Object[]{new User().withName(split[0]).withLastname(split[1]).withEmail(split[2]).withPassword(split[3])});
            line = reader.readLine();  // null
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataModelUser(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("423090@gmail.com").withPassword("Yy12345$")});
        list.add(new Object[]{new User().withEmail("423090@gmail.com").withPassword("Yy12345$")});
        list.add(new Object[]{new User().withEmail("423090@gmail.com").withPassword("Yy12345$")});
        return list.iterator();
    }
}
