package functionalities;

import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;

public class Facebook {

	private String facebookAccessToken;
	private boolean facebookConnect = false;

	public Facebook(String facebookAccessToken) {
		this.facebookAccessToken = facebookAccessToken;
	}

	/**
	 * 
	 * Method to search post´s in facebook account with iscte information
	 * 
	 * @param values array to insert post´s of facebook
	 */
	public void viewPosts(List<String[]> values) {

		try {

			String accessToken = facebookAccessToken;
			FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		
			Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);
			
			for (List<Post> page : result) {
				for (Post aPost : page) {
					if (aPost.getMessage() != null && aPost.getMessage().toLowerCase().contains("iscte")) {
						values.add(new String[] { aPost.getCreatedTime().toString(), "Facebook", aPost.getAdminCreator().getName(), 
								"------------", aPost.getMessage().toString(), "View"});
					}
				}
			}
			facebookConnect = true;
		} catch (Exception e) {
			facebookConnect = false;
		}
	}

	public void post(String content) {
		
		String accessToken = facebookAccessToken;
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		
		fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", content));
	}

	public boolean getFacebookConnect() {
		return facebookConnect;
	}
}
