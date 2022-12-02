import org.json.*;
import org.json.simple.JSONObject;
import org.json.JSONArray;
import java.util.Scanner;

public class Summer2023 {

    public static boolean isValid(String stale, String latest, JSONArray JSONdata) throws JSONException {
        if (stale.equals(latest)) {
            return true;
        }

        int count = 0;
        int delete = 0;

        for (int i = 0; i < JSONdata.length(); i++) {
            if (JSONdata.getJSONObject(i).get("op") == "skip") {

                int value_count = (int) JSONdata.getJSONObject(i).get("count");
                count += value_count;

                if (count > stale.length()) {
                    return false;
                }

            } else if (JSONdata.getJSONObject(i).get("op") == "delete") {
                if (count > stale.length()) {
                    return false;
                }

                int value_delete = (int) JSONdata.getJSONObject(i).get("count");
                delete += value_delete + count; // 47

                if (delete > stale.length()) {
                    return false;
                }

                String stale_first = stale.substring(0, count);
                String stale_second = stale.substring(count + value_delete);
                stale = stale_first + stale_second;

            } else if (JSONdata.getJSONObject(i).get("op") == "insert") {
                String toAdd = (String) JSONdata.getJSONObject(i).get("chars");
                count += toAdd.length();
                stale = toAdd + stale;
            }
        }

        if (stale.equals(latest)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws JSONException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            System.out.println("Enter the stale string");
            String stale = scanner.nextLine();
            System.out.println("Enter the latest string");
            String latest = scanner.nextLine();

            JSONArray JSONdata = new JSONArray();
            JSONObject obj = new JSONObject();

            // Case 1:
            // obj.put("op", "skip");
            // obj.put("count", 40);
            // JSONdata.put(obj);
            // obj = new JSONObject();
            // obj.put("op", "delete");
            // obj.put("count", 47);
            // JSONdata.put(obj);

            // // Case 2:
            // obj.put("op", "skip");
            // obj.put("count", 45);
            // JSONdata.put(obj);
            // obj = new JSONObject();
            // obj.put("op", "delete");
            // obj.put("count", 47);
            // JSONdata.put(obj);

            // // Case 3:
            // obj.put("op", "skip");
            // obj.put("count", 40);
            // JSONdata.put(obj);
            // obj = new JSONObject();
            // obj.put("op", "delete");
            // obj.put("count", 47);
            // JSONdata.put(obj);
            // obj = new JSONObject();
            // obj.put("op", "skip");
            // obj.put("count", 2);
            // JSONdata.put(obj);

            // // Case 4
            obj.put("op", "delete");
            obj.put("count", 7);
            JSONdata.put(obj);
            obj = new JSONObject();
            obj.put("op", "insert");
            obj.put("chars", "We");
            JSONdata.put(obj);
            obj = new JSONObject();
            obj.put("op", "skip");
            obj.put("count", 4);
            JSONdata.put(obj);
            obj = new JSONObject();
            obj.put("op", "delete");
            obj.put("count", 1);
            JSONdata.put(obj);

            boolean result = isValid(stale, latest, JSONdata);
            System.out.println(result);
        } finally {
            if (scanner != null)
                scanner.close();
        }

    }
}
