package fr.epsi.tp.ws.services;

import fr.epsi.tp.ws.bean.Tweet;

import java.util.List;

/**
 * Created by Baptiste on 17/03/2015.
 */
public interface TweetService {
    public List<Tweet> getTweets(String userId, int start, int end);
    public List<Tweet> getTweets(String userId);
    public List<Tweet> getTweetsWithPseudo(String pseudo);
    public List<Tweet> getWallTweets(String pseudo);
    public List<Tweet> getWallTweets(String pseudo, int start, int stop);
    public Tweet getTweet(String tweetId);
    public void createTweet(Tweet tweet, String userUid);
    public long getNbTweets(String pseudo);
    void removeTweet(String  uid, String idTweet);
    public List<Tweet> getTweetsByHashtag(String hashtag);
    public List<Tweet> getFavorite(String userId);
    public void addFavorite(String userId, Tweet tweet);
    public void removeFavorite(String userId, Tweet tweet);
}
