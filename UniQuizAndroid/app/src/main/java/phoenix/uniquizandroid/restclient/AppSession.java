package phoenix.uniquizandroid.restclient;

import android.util.Base64;

import phoenix.uniquizandroid.dto.UserDTO;


public class AppSession {
    public static UserDTO loggedUser;

    public static String getEncondedUserCredentials(){
        String plainCreds = AppSession.loggedUser.credentials();
        byte[] plainCredsBytes = plainCreds.getBytes();
        String base64Creds = Base64.encodeToString(plainCredsBytes, Base64.DEFAULT);

        return base64Creds;
    }
}
