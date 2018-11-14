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
		// http://twitter4j.org
		// http://twitter4j.org/en/code-examples.html
		// https://www.youtube.com/watch?v=uYPmkzMpnxw
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey)
					.setOAuthConsumerSecret(consumerSecret)
					.setOAuthAccessToken(acessToken)
					.setOAuthAccessTokenSecret(acessTokenSecret);
			TwitterFactory tf = new TwitterFactory(cb.build());
			twitter4j.Twitter twitter = (twitter4j.Twitter) tf.getInstance();
			List<Status> statuses = ((TimelinesResources) twitter).getHomeTimeline();
			//System.out.println("------------------------\n Showing home timeline \n------------------------");
			int counter = 0;
			int counterTotal = 0;
			for (Status status : statuses) {
				// Filters only tweets from user "catarina"
				//if (status.getUser().getName() != null && status.getUser().getName().contains("catarina")) {
					//System.out.println(status.getUser().getName() + ":" + status.getText());
				values.add(new String[] {status.getCreatedAt().toString(), "Twitter", status.getUser().getName(), "---------",
						status.getText(), "View"});
					counter++;
				//}
				counterTotal++;
			}
			//System.out.println("-------------\nNº of Results: " + counter + "/" + counterTotal);
			twitterConnect = true;
		} catch (Exception e) {
			twitterConnect = false;
		}
	}
	
	public boolean getTwitterConnect() {
		return twitterConnect;
	}
}
