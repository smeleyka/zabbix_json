import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Created by smeleyka on 23.10.17.
 */
public class Main {
    static Gson gson;
    static String jsonRequest = "";
    static String jsonAnswer = "";

    public static void main(String[] args) throws Exception {

        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        jsonRequest = gson.toJson(ZabbixRequestMessage.itemGet(11965));
        System.out.println(jsonRequest);
        jsonAnswer = HttpRequestWorker.getJson(jsonRequest);
        JsonMessage jsonMessage = gson.fromJson(jsonAnswer,JsonMessage.class);
        System.out.println(jsonAnswer);



    }
   class JsonMessage {
       String jsonrpc;
       ZabbixItem result[];

       public String getJsonrpc() {
           return jsonrpc;
       }

       public void setJsonrpc(String jsonrpc) {
           this.jsonrpc = jsonrpc;
       }

       public ZabbixItem[] getResult() {
           return result;
       }

       public void setResult(ZabbixItem[] result) {
           this.result = result;
       }

       @Override
       public String toString() {
           return jsonrpc+" "+result.length;
       }



   }

   class ZabbixItem{
        int hostid;
        String host;
        String name;

       public int getHostid() {
           return hostid;
       }

       public void setHostid(int hostid) {
           this.hostid = hostid;
       }

       public String getHost() {
           return host;
       }

       public void setHost(String host) {
           this.host = host;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }

}

