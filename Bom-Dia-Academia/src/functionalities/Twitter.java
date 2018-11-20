package functionalities;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.api.TimelinesResources;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter {
	
	private String consumerKey;
	private String consumerSecret;
	private String acessToken;
	private String acessTokenSecret;
	private boolean twitterConnect = false;
	
	public Twitter(String consumerKey, String consumerSecret, String acessToken, String acessTokenSecret) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.acessToken = acessToken;
		this.acessTokenSecret = acessTokenSecret;
	}

	/**
	 * 
	 * Method to search tweets on twitter account with iscte information
	 * 	
	 * @param values array to insert tweets
	 */
	public void viewTweets(List<String[]> values) {

		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey)
					.setOAuthConsumerSecret(consumerSecret)
					.setOAuthAccessToken(acessToken)
					.setOAuthAccessTokenSecret(acessTokenSecret);
			TwitterFactory tf = new TwitterFactory(cb.build());
			twitter4j.Twitter twitter = (twitter4j.Twitter) tf.getInstance();
			List<Status> statuses = ((TimelinesResources) twitter).getHomeTimeline();

			for (Status status : statuses) {

				if (status.getUser().getName() != null && (status.getUser().getName().toLowerCase().contains("iscte") 
						|| status.getText().toLowerCase().contains("iscte"))) 
				values.add(new String[] {status.getCreatedAt().toString(), "Twitter", status.getUser().getName(), "---------",
						status.getText(), "View"});
			}

			twitterConnect = true;
		} catch (Exception e) {
			twitterConnect = false;
		}
	}
	
	public void retweet(String content) {
		
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey)
					.setOAuthConsumerSecret(consumerSecret)
					.setOAuthAccessToken(acessToken)
					.setOAuthAccessTokenSecret(acessTokenSecret);
			TwitterFactory tf = new TwitterFactory(cb.build());
			twitter4j.Twitter twitter = (twitter4j.Twitter) tf.getInstance();
						
			twitter.updateStatus(content);
			
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	public boolean getTwitterConnect() {
		return twitterConnect;
	}
}
