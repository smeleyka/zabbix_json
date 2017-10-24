import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.util.List;

/**
 * Created by smeleyka on 24.10.17.
 */
@Contract(threading = ThreadingBehavior.IMMUTABLE)
public class SslHostVerifier implements HostnameVerifier {
    private static List<String> whiteDomains;
    public static final SslHostVerifier INSTANCE = new SslHostVerifier();

    public SslHostVerifier() {
        whiteDomains.add("vyborg.net");
        whiteDomains.add("vbg.ru");
    }

    @Override
    public boolean verify(String s, SSLSession sslSession) {
        System.out.println(s);
        if (whiteDomains.contains("vbg.ru")){
            return true;
        }
        return false;
    }
}
