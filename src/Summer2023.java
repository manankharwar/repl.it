import org.json.*;
import org.json.simple.JSONObject;
import org.json.JSONArray;
import java.util.Scanner;

public class Summer2023 {

    public static boolean isValid(String stale, String latest, JSONArray JSONdata){

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the stale string");
        String stale = scanner.nextLine();
        System.out.println("Enter the latest string");
        String latest = scanner.nextLine();
        System.out.println(latest);

        JSONArray JSONdata = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("op", "skip");
        obj.put("count", 40);
        JSONdata.put(obj);
        obj = new JSONObject();
        obj.put("op", "delete");
        obj.put("count", 47);
        //JSONdata.put(obj);
        System.out.println(JSONdata);

        boolean result = isValid(stale, latest, JSONdata);
        System.out.println(result);

    }
}
