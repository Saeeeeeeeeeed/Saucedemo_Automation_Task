package utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonDataReader {

    public static String firstname;
    public static String lastname;
    public static String zipcode;

    public Object[][] jsonReader() throws IOException, ParseException {

        String filePath = System.getProperty("user.dir")+"/src/test/resources/testdata/checkout.json";
        File srcFile = new File(filePath);

        JSONParser parser = new JSONParser();
        JSONArray jarray = (JSONArray)parser.parse(new FileReader(srcFile));

        for (Object jsonObj : jarray) {
            JSONObject person = (JSONObject) jsonObj ;
            firstname = (String) person.get("firstname");
            System.out.println(firstname);

            lastname = (String) person.get("lastname");
            System.out.println(lastname);

            zipcode = (String) person.get("zipcode");
            System.out.println(zipcode);

        }
        return new String[][]{new String[]{firstname, lastname, zipcode}};
    }
}

