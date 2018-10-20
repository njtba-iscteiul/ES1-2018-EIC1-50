package functionalities;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.api.TimelinesResources;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter {

	public void viewTweets(List<String[]> values) {
		// http://twitter4j.org
		// http://twitter4j.org/en/code-examples.html
		// https://www.youtube.com/watch?v=uYPmkzMpnxw
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("AJ2JHM7lhqmTd3pCPpg2OMwcm")
					.setOAuthConsumerSecret("6jyKoOZFW2kMJ6C88tGlpeQPol6u1jpGV6t05b7u0H1Ly88daF")
					.setOAuthAccessToken("1053239130975621121-65AtRgxoOtOEsLWwht2Et3gR30R7iV")
					.setOAuthAccessTokenSecret("Ty78bOURzyENLedyr2o7klp8dOixrHScR7m2r4JCXykd3");
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
