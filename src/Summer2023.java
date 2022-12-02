import org.json.*;
import org.json.simple.JSONObject;
import org.json.JSONArray;
import java.util.Scanner;

public class Summer2023 {

    public static boolean isValid(String stale, String latest, JSONArray JSONdata) throws JSONException {
        if(stale.equals(latest)){
            return true;
        }

        // we build our string and we check if sb.toString() is equal to latest. -> if it does, then we simply return true, or else we return false;

        // Repl.it uses operational transformations to keep everyone in a multiplayer repl in sync.
        // Repl.it uses operational transformations.

        // let's first do 'skip'

        //System.out.println(JSONdata.getJSONObject(0));   // Answer: {"op":"skip","count":40}
        //System.out.println(JSONdata.getJSONObject(0).get("count"));  // Answer: 40


        int count = 0;
        int delete = 0;

        for (int i = 0; i < JSONdata.length(); i++) {
            if(JSONdata.getJSONObject(i).get("op") == "skip"){

                int value_count = (int) JSONdata.getJSONObject(i).get("count");
                count += value_count;

                if(count > stale.length()){
                    //System.out.println(count + " count is greater than stale length");
                    return false;
                }

                System.out.println(count);
            }
            else if(JSONdata.getJSONObject(i).get("op") == "delete"){
                if(count > stale.length()){
                    //System.out.println(count + "count is greater than stale length in else if");
                    return false;
                }

                int value_delete = (int) JSONdata.getJSONObject(i).get("count");
                delete += value_delete + count; // 47

                if(delete > stale.length()){
                    //System.out.println(delete + "delete is greater than stale length in else if");
                    return false;
                }

                String stale_first = stale.substring(0, count);
                String stale_second = stale.substring(count+value_delete);
                stale = stale_first + stale_second;

            }
            else if(JSONdata.getJSONObject(i).get("op") == "insert"){
                String toAdd = (String) JSONdata.getJSONObject(i).get("chars");
                count += toAdd.length();
                stale = toAdd + stale;
            }
//            System.out.println("count " + count);
//            System.out.println("delete " + delete);
//            System.out.println("Stale string:" + stale);
        }

        if(stale.equals(latest)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws JSONException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the stale string");
        String stale = scanner.nextLine();
        System.out.println("Enter the latest string");
        String latest = scanner.nextLine();
        System.out.println(latest);

        JSONArray JSONdata = new JSONArray();
        JSONObject obj = new JSONObject();

        // Case 1:
//        obj.put("op", "skip");
//        obj.put("count", 40);
//        JSONdata.put(obj);
//        obj = new JSONObject();
//        obj.put("op", "delete");
//        obj.put("count", 47);
//        JSONdata.put(obj);

//        // Case 2:
//        obj.put("op", "skip");
//        obj.put("count", 45);
//        JSONdata.put(obj);
//        obj = new JSONObject();
//        obj.put("op", "delete");
//        obj.put("count", 47);
//        JSONdata.put(obj);

//        // Case 3:
//        obj.put("op", "skip");
//        obj.put("count", 40);
//        JSONdata.put(obj);
//        obj = new JSONObject();
//        obj.put("op", "delete");
//        obj.put("count", 47);
//        JSONdata.put(obj);
//        obj = new JSONObject();
//        obj.put("op", "skip");
//        obj.put("count", 2);
//        JSONdata.put(obj);

//        // Case 4
//        obj.put("op", "delete");
//        obj.put("count", 7);
//        JSONdata.put(obj);
//        obj = new JSONObject();
//        obj.put("op", "insert");
//        obj.put("chars", "We");
//        JSONdata.put(obj);
//        obj = new JSONObject();
//        obj.put("op", "skip");
//        obj.put("count", 4);
//        JSONdata.put(obj);
//        obj = new JSONObject();
//        obj.put("op", "delete");
//        obj.put("count", 1);
//        JSONdata.put(obj);

        boolean result = isValid(stale, latest, JSONdata);
        System.out.println(result);

    }
}
