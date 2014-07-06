package net.catchpole.pimpmylight.twitter;

import net.catchpole.silicone.lang.Throw;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.swing.*;

public class Tweet {
    private Twitter twitter = TwitterFactory.getSingleton();

    public Tweet() throws Exception {
    }

    public void tweet(final String status) {
        if (twitter != null) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        twitter.updateStatus(status);
                        System.out.println("tweet > " + status);
                    } catch (Exception e) {
                        throw Throw.unchecked(e);
                    }
                }
            }.start();
        }
    }

    private void accessTokens(String consumerKey, String secretKey) throws Exception {
        twitter.setOAuthConsumer(consumerKey, secretKey);

        RequestToken requestToken = twitter.getOAuthRequestToken();
        System.out.println("Open the following URL...");
        System.out.println(requestToken.getAuthorizationURL());

        AccessToken accessToken = null;
        while (null == accessToken) {
            String pin = JOptionPane.showInputDialog("PIN?");
            try{
                if (pin.length() > 0){
                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                }else{
                    accessToken = twitter.getOAuthAccessToken();
                }
            } catch (TwitterException te) {
                if (401 == te.getStatusCode()){
                    System.out.println("Unable to get the access token.");
                }else{
                    te.printStackTrace();
                }
            }
        }
        System.out.println("token:       " + accessToken.getToken());
        System.out.println("secretToken: " + accessToken.getTokenSecret());
        System.exit(0);
    }
}
