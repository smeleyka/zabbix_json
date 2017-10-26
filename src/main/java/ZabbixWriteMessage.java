import com.google.gson.annotations.Expose;

/**
 * Created by smeleyka on 26.10.17.
 */
public class ZabbixWriteMessage {
    @Expose
    private String jsonrpc = "2.0";
    @Expose
    private String method = "host.get";
    @Expose
    private String auth = "914891f5250aaead5141749959e505f1";
    @Expose
    private int id = 2;
//    @Expose
//    private ZabbixWriteParams params;
//
//    public ZabbixWriteMessage(int ...hostids){
//        params = new ZabbixWriteParams(hostids);
//    }

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

//    public ZabbixWriteParams getParams() {
//        return params;
//    }

//    private class ZabbixWriteParams {
//        private String output[] = {"hostid", "host"};
//        @Expose
//        private int hostids[];
//
//        ZabbixWriteParams(int ...hostids){
//        this.hostids = hostids;
//        }
//    }

}

