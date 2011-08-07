
package tasks;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GoogleApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class TaskifyServlet extends HttpServlet {

	private OAuthService		service;
	private Token				accessToken;
	private static final String	API_KEY					= "753789996177.apps.googleusercontent.com";
	private static final String	API_SECRET				= "kjEURAYT5Cyace0-cRLlTOi6";
	private static final String	PROTECTED_RESOURCE_URL	= "https://www.googleapis.com/tasks/v1/users/@me/lists";
	private static final String	AUTHORIZE_URL			= "https://www.google.com/accounts/OAuthAuthorizeToken?oauth_token=";
	private static final String	SCOPE					= "https://www.googleapis.com/auth/tasks";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Scanner in = new Scanner(System.in);

		// Obtain the Request Token
		Token requestToken = service.getRequestToken();

		System.out.println("Now go and authorize Scribe here:");
		System.out.println(AUTHORIZE_URL + requestToken.getToken());
		System.out.println("And paste the verifier here");
		System.out.print(">>");
		Verifier verifier = new Verifier(in.nextLine());
		System.out.println();

		// Trade the Request Token and Verfier for the Access Token
		Token accessToken = service.getAccessToken(requestToken, verifier);

		// Get the protected resource
		OAuthRequest request = new OAuthRequest(Verb.GET,
				PROTECTED_RESOURCE_URL);

		// Sign the request with your access token
		service.signRequest(accessToken, request);
		request.addHeader("realm", "googleapis.com");

		// Gather the actual request
		Response response = request.send();

		TaskLists tasklist = new Gson()
				.fromJson(response.getBody(), TaskLists.class);

		System.out.println(tasklist.toString());
		// Set the response type and response length. This is necessary for it
		// to work.
	}

	/**
	 * Initializes the servlet with a Google Service.
	 */
	@Override
	public void init() {

		// Start a new service using the build in Google service
		service = new ServiceBuilder()
				.provider(GoogleApi.class)
				.apiKey(API_KEY)
				.apiSecret(API_SECRET)
				.scope(SCOPE)
				.build();
	}
}