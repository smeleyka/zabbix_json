import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import javax.net.ssl.HostnameVerifier;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by smeleyka on 23.10.17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null,new TrustSelfSignedStrategy());
        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(),allowAllHosts);

        //CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

        HttpPost httpPost = new HttpPost("https://isp.vbg.ru/zabbix/api_jsonrpc.php");
//        StringEntity params = new StringEntity("{\n" +
//                "    \"jsonrpc\": \"2.0\",\n" +
//                "    \"method\": \"host.get\",\n" +
//                "    \"params\": {\n" +
//                "        \"output\": [\n" +
//                "            \"hostid\",\n" +
//                "            \"host\",\n" +
//                "\"name\"\n" +
//                "        ]\n" +
//                "    },\n" +
//                "    \"id\": 2,\n" +
//                "    \"auth\": \"914891f5250aaead5141749959e505f1\"\n" +
//                "}");
        StringEntity params = new StringEntity(gson.toJson(new ZabbixRequestMessage()));
        params.setContentType(ContentType.APPLICATION_JSON.getMimeType());
//        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
//        nvps.add(new BasicNameValuePair("username", "vip"));
//        nvps.add(new BasicNameValuePair("password", "secret"));
//        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
//        CloseableHttpResponse response2 = httpclient.execute(httpPost);
        httpPost.setEntity(params);
        HttpResponse response = httpclient.execute(httpPost);
        BufferedReader breader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder rawResult=new StringBuilder();
        String temp="test";
        while ((temp = breader.readLine()) != null) {
            rawResult.append(temp);
        }

        System.out.println(rawResult);
        httpclient.close();

        JsonMessage jsonAnswer = gson.fromJson(rawResult.toString(),JsonMessage.class);

        //System.out.println(jsonAnswer);

        System.out.println(gson.toJson(new ZabbixRequestMessage()));



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

