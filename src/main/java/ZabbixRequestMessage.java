import com.google.gson.annotations.Expose;

/**
 * Created by smeleyka on 24.10.17.
 */
public class ZabbixRequestMessage {
    final private static String HOSTGET = "host.get";
    final private static String ITEMGET = "item.get";
    final private static String JSONRPC = "2.0";
    final private static String AUTHKEY = "914891f5250aaead5141749959e505f1";

    @Expose
    private String jsonrpc = JSONRPC;
    @Expose
    private String method;
    @Expose
    private String auth = AUTHKEY;
    @Expose
    private int id = 1;
    @Expose
    private ZabbixJsonParams params;

    private static ZabbixRequestMessage INSTANCE;

    private ZabbixRequestMessage(String method,int... hostids) {
        params = new ZabbixJsonParams(hostids);
        this.method = method;

    }

//    {"jsonrpc":"2.0","method":"host.get","auth":"914891f5250aaead5141749959e505f1","id":2,"params":
//        {"hostids":"12065
//            ","selectInventory":"extend"}}

    public static ZabbixRequestMessage hostGet(int... hostids) {
        INSTANCE = new ZabbixRequestMessage(HOSTGET,hostids);
        return INSTANCE;
    }

    public static ZabbixRequestMessage itemGet(int... hostids) {
        INSTANCE = new ZabbixRequestMessage(ITEMGET,hostids);
        return INSTANCE;
    }


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
        @Expose
        private String[] output;
        @Expose
        private int[] hostids;

        public ZabbixJsonParams(int... hostids) {
            this.hostids = hostids;
        }

    }
}
