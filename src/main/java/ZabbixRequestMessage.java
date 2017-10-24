import com.google.gson.annotations.Expose;

/**
 * Created by smeleyka on 24.10.17.
 */
public class ZabbixRequestMessage {
    @Expose
    private String jsonrpc = "2.0";
    @Expose
    private String method = "item.get";
    @Expose
    private String auth = "914891f5250aaead5141749959e505f1";
    @Expose
    private int id = 2;
    @Expose
    private ZabbixJsonParams params = new ZabbixJsonParams();


    public String getJsonrpc() {
        return jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public String getAuth() {
        return auth;
    }

    public int getId() {
        return id;
    }

    public ZabbixJsonParams getParams() {
        return params;
    }

    private class ZabbixJsonParams {
        private String output[] = {"hostid", "host"};
        @Expose
        private String hostids = "11965";
    }
}
