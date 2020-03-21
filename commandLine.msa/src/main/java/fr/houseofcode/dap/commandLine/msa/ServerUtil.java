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

	/**
	 * String user_agent.
	 */
	private final static String USER_AGENT = "Mozilla/5.0";

	/**
	 * Add user account.
	 * 
	 * @param userKey
	 * @return
	 * @throws IOException
	 */
	public String addAccount(final String userKey) throws IOException {
		String addAccount = callserver("/user/add", userKey);
		return addAccount;
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
	 * @retunr nextEvent
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

		// HTTP GET request
		URL obj = new URL("http://localhost:8081" + url + "?userKey=" + userKey);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + "http://localhost:8081/" + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

		return response.toString();
	}
}
