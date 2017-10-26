import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.ArrayList;

/**
 * Created by smeleyka on 24.10.17.
 */
@Contract(threading = ThreadingBehavior.IMMUTABLE)
public class SslHostVerifier implements HostnameVerifier {
    private static ArrayList<String> whiteDomains;
    public static final SslHostVerifier INSTANCE = new SslHostVerifier();

    private SslHostVerifier() {
        whiteDomains = new ArrayList<String>();
        whiteDomains.add("vyborg.net");
        whiteDomains.add("vbg.ru");
        whiteDomains.add("isp.vbg.ru");
    }

    @Override
    public boolean verify(String s, SSLSession sslSession) {
        System.out.println(s);
        if (whiteDomains.contains(s)){
            return true;
        }
        return false;
    }
}
