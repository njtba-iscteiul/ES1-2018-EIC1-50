package functionalities;

import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;

public class Facebook {
	
	/**
	 * 
	 * Method to search post´s in facebook account with iscte information
	 * 
	 * @param values array to insert post´s of facebook
	 */
	public void viewPosts(List<String[]> values) {
		/* 
		 * Facebook API Tutorials in Java # 1 | Setup Development Environment 
		 * https://www.youtube.com/watch?v=m14hYs1T3FA&index=1&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb
		 */
		/* 
		 * Facebook API Tutorials in Java # 2 | Get User Access Token
		 * https://www.youtube.com/watch?v=GwbO_PdwK_4&index=2&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb
		 */
//		String accessToken2 = "EAAFjnm6XU2EBANmlZB35O7ZBdZAM1kLPHgE1TbNZC5oFmQ15jF44rwX46jaNzz5iZBwPNhb3kuTHHJrh2rQbUPkITf7kaw4iUXvwSfQ7y8BILUmkoegKZAtKmtNi7XfSVBZAbC24c97ud7XVGWEFWvtGBjPweYCiJjF1LBnz4tfaZBNkjDIVFWYpyXYQTVJOJxsmHFbAhbZBL6k8qV0CyI5kdOMCXLcVzL2kZD";
//		FacebookClient fbClient2 = new DefaultFacebookClient(accessToken2);
//		User me2 = fbClient2.fetchObject("me", User.class);
//		System.out.println("Facebook:");
//		System.out.println("Id: " + me2.getId());
//		System.out.println("Name: " + me2.getName());

		/* 
		 * Facebook API Tutorials in Java # 4 | Create Your Own Fb APP & Extend User Access Token  
		 * https://www.youtube.com/watch?v=qFZazZ1JXsM&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb&index=5
		 */
//		String accessToken4 = "EAAFjnm6XU2EBANmlZB35O7ZBdZAM1kLPHgE1TbNZC5oFmQ15jF44rwX46jaNzz5iZBwPNhb3kuTHHJrh2rQbUPkITf7kaw4iUXvwSfQ7y8BILUmkoegKZAtKmtNi7XfSVBZAbC24c97ud7XVGWEFWvtGBjPweYCiJjF1LBnz4tfaZBNkjDIVFWYpyXYQTVJOJxsmHFbAhbZBL6k8qV0CyI5kdOMCXLcVzL2kZD";
//		FacebookClient fbClient4 = new DefaultFacebookClient(accessToken4);
//		AccessToken extendedAccessToken4 = fbClient4.obtainExtendedAccessToken("391007088104289","5f6c7f0e1404b1b355f55990d52b0750");
//		System.out.println("ExtendedAccessToken: "+extendedAccessToken4.getAccessToken());
//		System.out.println("Expires: " + extendedAccessToken4.getExpires());

		/* 
		 * Facebook API Tutorials in Java # 5 | Get User Timeline Posts
		 * https://www.youtube.com/watch?v=wiFif4gOdFE&index=6&list=PLYPFxrXyK0BwiXNe09hTPjFqYbsWv8gxb
		*/ 
		String accessToken ;//= "EAAGZBEccjciEBAJ37ZAIbHKiL1Mo1HHex2pQTcs41dq8azfBvFGgt4eGgKBq12kSssOof51FKO0niKu7AaVKs3dy8W1ilqp4xcjFD1F9mmjJpVyeDnZAffUXRfh7zXL06BuSwQtfHMJbmJ079qCnkT844brHx966cz73JZBZBFy2Bv1rWu7T1rQddZCVpxywZCO6lDxoWDk2gZDZD";
		accessToken = "EAAFjnm6XU2EBAFr0g5stZCNxemoZCSiClUSl8dga4WwFZCQZAd2hJgz0vLX52uPvQ9gjKqZBQZCAfcEhMTW5HMDSigAZCwF4V4fnJwLA7waGEZCuRxndDB7ZCJqFxdIR4GZAnjPKOvURLSPOMM2sQ4fJY2StpNQlGlKt1oHQ9s3ZC4NB7lJ7sZCJkZCyP7ZClb24x1a1OuWYUri1FKny3JsCylYGmbHEW3JwqJKH8ZD";	
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);

		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
//		System.out.println("\nPosts:");
		int counter = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "Inform"
				if (aPost.getMessage() != null && aPost.getMessage().contains("Iscte")) {
//					System.out.println("---- Post "+ counter + " ----");
//					System.out.println("Id: "+"fb.com/" + aPost.getId());
//					System.out.println("Message: " + aPost.getMessage());
//					System.out.println("Created: " + aPost.getCreatedTime());
//					counter++;
					values.add(new String[] { aPost.getCreatedTime().toString() , "Facebook", "From", 
							"------------", aPost.getMessage().toString(), "View" });
				}
//				counterTotal++;
			}
		}
//		System.out.println("-------------\nNº of Results: " + counter + "/" + counterTotal);	
	}
}
