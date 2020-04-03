package fr.houseofcode.dap.commandLine.msa;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author msa
 */
public class ServerUtil {

    /**
     * Access to constant LOG.
     */
    private static final Logger LOG = LogManager.getLogger();

    //TODO MSA by Djer |JavaDoc| "User_agent used by internal browser to query Server API" serait plus juste.
    /**
     * String user_agent.
     */
    private final static String USER_AGENT = "Mozilla/5.0";

    //TODO MSA by Djer |POO| Cette pas méthode n'est pas/plus utilisée.
    /**
     * Loading a web page to add a new user.
     * 
     * @param userKey
     * @throws IOException
     * @throws URISyntaxException
     */
    public void addUser(final String userKey) throws IOException, URISyntaxException {
        String addUser = "http://localhost:8081/user/add?name=" + userKey;
        loadingNavigatorPage(addUser);
    }

    /**
     * Add user account.
     * 
     * @param userKey
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public String addAccount(final String userKey) throws IOException, URISyntaxException {
        String addAccount = "http://localhost:8081/account/add/" + userKey;
        loadingNavigatorPage(addAccount);
        return addAccount;
    }

    //TODO MSA by Djer |JavaDoc| JavaDoc manquante.
    //TODO MSA by Djer |POO| Le paramètre pourrait s'appeler "url" ou "urlToOpen" au lieu de "account".
    private void loadingNavigatorPage(final String account) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI(account));
    }

    /**
     * Get labels function.
     * 
     * @param userKey
     * @return Labels
     */
    public String getLabels(final String userKey) throws IOException {
        String getLabels = callserver("/Email/Label", userKey);
        return getLabels;
    }

    /**
     * Get the next event.
     * 
     * @param userKey
     * @return nextEvent
     */
    public String nextEvent(final String userKey) throws IOException {
        String nextEvent = callserver("/event/next", userKey);
        return nextEvent;
    }

    /**
     * Get the number of unread emails.
     * 
     * @param userKey
     * @return NbUnreadEmail
     */
    public String getNbUnreadEmail(final String userKey) throws IOException {
        String NbUnreadEmail = callserver("/Email/Unread", userKey);
        return NbUnreadEmail;

    }

    /**
     * Call the server.
     * 
     * @param url, userKey
     * @return Response
     */
    public String callserver(final String url, final String userKey) throws IOException {

        //TODO MSA by Djer |POO| "http://localhost:8081" devrait être une variable local (pour éviter de repeter cette chaine ligne 119). Voir un élément configurable. 
        // HTTP GET request
        URL obj = new URL("http://localhost:8081" + url + "?userKey=" + userKey);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        // add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        //TODO MSA by Djer |Log4J| Devrait être une LOG. En Debug ? 
        System.out.println("\nSending 'GET' request to URL : " + "http://localhost:8081/" + url);
        //TODO MSA by Djer |Log4J| Devrait être une LOG. En Debug ? 
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //TODO MSA by Djer |Log4J| Devrait être une LOG. En Debug ? 
        // print result
        System.out.println(response.toString());

        return response.toString();
    }
}
