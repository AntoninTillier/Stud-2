import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Internet {

    public boolean netIsAvailable() {
	boolean connexion = false;
	try {
	    final URL url = new URL("https://www.apple.com");
	    final URLConnection conn = url.openConnection();
	    conn.connect();
	    conn.getInputStream().close();
	    connexion = true;
	} catch (MalformedURLException e) {
	    throw new RuntimeException(e);
	} catch (IOException e) {
	    connexion =  false;
	}
	return connexion;
    }
}