package phoenix.uniquizandroid.restclient;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class RestProperties {
    private static final String PROPERTIES_FILE = "restserver.properties";
    private InputStream inputStream;
    private Properties properties;

    public RestProperties(Context context){
        properties = new Properties();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(PROPERTIES_FILE);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHost(){
        return properties.getProperty("webservice.host");
    }

    public String getPort(){
        return properties.getProperty("webservice.port");
    }

    public String getAppBaseUri(){
        return properties.getProperty("webservice.app.baseuri");
    }

    public String getQuizUri(){
        return properties.getProperty("webservice.quiz.baseuri");
    }

    public String getSearchUri(){
        return properties.getProperty("webservice.search.baseuri");
    }

    public String getLoginUri(){
        return properties.getProperty("webservice.login.baseuri");
    }


    public String getUsersUri(){
        return properties.getProperty("webservice.users.baseuri");
    }


    public String getScheme(){
        return properties.getProperty("webservice.scheme");
    }

    public String getConnectionUri(){
        return properties.getProperty("webservice.connection.baseuri");
    }
}
