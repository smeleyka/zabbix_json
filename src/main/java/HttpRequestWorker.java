import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.HostnameVerifier;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by smeleyka on 24.10.17.
 */
public class HttpRequestWorker {
    static String URL = "https://isp.vbg.ru/zabbix/api_jsonrpc.php";
    static SSLContextBuilder builder;
    static SslHostVerifier allowAllHosts;

    public static String getJson(Gson reurest) throws Exception {
        builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        allowAllHosts =new SslHostVerifier();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), allowAllHosts);
        //CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

        HttpPost httpPost = new HttpPost(URL);
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
        StringBuilder rawResult = new StringBuilder();
        String temp = "test";
        while ((temp = breader.readLine()) != null) {
            rawResult.append(temp);
        }
    }


}
