package fr.houseofcode.dap.commandLine.msa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author msa
 */
public class ServerUtil {
    private final static String USER_AGENT = "Mozilla/5.0";

    public String getLabels(String userKey) throws IOException {
        //TODO MSA by Djer |IDE| Supprime les "to-do automatique" une fois que tu les as traités
        // TODO Auto-generated method stub
        String getLabels = callserver("/Email/Label", userKey);
        return getLabels;
    }

    public String nextEvent(String userKey) throws IOException {
        //TODO MSA by Djer |IDE| Supprime les "to-do automatique" une fois que tu les as traités
        // TODO Auto-generated method stub
        String nextEvent = callserver("/CalendarService/Event", userKey);
        return nextEvent;
    }

    public String getNbUnreadEmail(String userKey) throws IOException {
        //TODO MSA by Djer |IDE| Supprime les "to-do automatique" une fois que tu les as traités
        // TODO Auto-generated method stub
        String NbUnreadEmail = callserver("/Email/Unread", userKey);
        return NbUnreadEmail;

    }

    public String callserver(String url, String userKey) throws IOException {

        // HTTP GET request
        URL obj = new URL("http://localhost:8080" + url + "?userKey=" + userKey);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + "http://localhost:8080/" + url);
        System.out.println("Response Code : " + responseCode);

        //TODO MSA by Djer |Gestion Exception| (optionnel) tu peux attraper la IOException levé par con.getInputStream() pour afficher un message d'erreur plus claire à l'utilisateur. Execute ton client sans démarer ton serveur pour produire une "erreur 500" facilement
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        return response.toString();
    }
}
