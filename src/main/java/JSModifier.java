
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class JSModifier {
    static ArrayList<String>  months= new ArrayList<String>(Arrays.asList("january", "february", "march","april","may","june","july","august","september","october","november","december"));
    static ArrayList<String> monthNumbers = new ArrayList<String>(Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12"));
    static ArrayList<JSONObject> jsonObjects=new ArrayList<JSONObject>();

    public static void main(String[] args) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        JSONArray as = (JSONArray) parser.parse(new FileReader("/home/gathika/IdeaProjects/jsonModifier/src/main/resources/cricket_data.json"));

        int count=0;

        for (Object o : as)
        {
            count++;
            JSONObject news = (JSONObject) o;

            String date = (String) news.get("date");
            if(date.length()>0) {
                date = date.substring(0, date.length() - 1);
                if (date.length() < 2) {
                    date = "0" + date;
                }
            }
            System.out.println(date);
            System.out.println(date.length());

            String month1 = (String) news.get("month");
            String month=month1.toLowerCase();
            int monthIndex = months.indexOf(month);
            String monthNumber="04";
            if(monthIndex!=-1){
                monthNumber = monthNumbers.get(monthIndex);
                System.out.println(month);
                System.out.println(monthNumber);
            }


            String year = (String) news.get("year");
            if(year==null||year==""){
                year="2013";
            }
            if(date==null||date==""){
                date="02";
            }
            String finalDate = year+"-"+monthNumber+"-"+date;
            System.out.println(year);
            System.out.println(finalDate);
            System.out.println(count);

            news.put("finalDate",finalDate);
            news.put("date",date);
            jsonObjects.add(news);



        }
        System.out.println("array size: "+jsonObjects.size());
        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("/home/gathika/IdeaProjects/jsonModifier/src/main/resources/cricket_data_1.json")) {
            for (JSONObject newJO:jsonObjects
                 ) {
                file.write(newJO.toJSONString());
                file.write(",");
                file.write("\n");
            }

        }
    }



}
