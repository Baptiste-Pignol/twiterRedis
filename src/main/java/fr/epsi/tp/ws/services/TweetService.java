package fr.epsi.tp.ws.services;

import fr.epsi.tp.ws.bean.Tweet;

import java.util.List;

/**
 * Created by Baptiste on 17/03/2015.
 */
public interface TweetService {
    public List<Tweet> getTweets(String userId);
    public Tweet getTweet(String tweetId);
    public Tweet createTweet(Tweet tweet, String userUid);
}
