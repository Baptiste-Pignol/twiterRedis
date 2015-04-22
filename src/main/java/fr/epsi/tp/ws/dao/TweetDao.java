package fr.epsi.tp.ws.dao;

import fr.epsi.tp.ws.bean.Tweet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baptiste on 12/04/2015.
 */
public interface TweetDao {
    public Tweet getTweetById(String uid);
    public List<Tweet> getTweetsByUserId(String uid, int start, int end);
    public List<Tweet> getTweetsByUserPseudo(String pseudo, int start, int end);
    public List<Tweet> getTweetsByHashtag(String hashtag, int start, int end);
    public List<Tweet> getTweetsByHashtag(String hashtag);
    public List<Tweet> getWallTweetById(String uid, int start, int end);
    public List<Tweet> getWallTweetByPseudo(String pseudo, int start, int end);
    public void addTweetByUserId(String id, Tweet tweet);
    public void addTweetByUserPseudo(String pseudo, Tweet tweet);
    public void addHashtagTweet(String hashtag, Tweet tweet);
    public long getNbTweetByPseudo(String pseudo);
    public long getNbTweetById(String id);
    public void removeTweet(String userId, String idTweet);
    public void addTweetByUserId(String userUid, Tweet tweet, ArrayList<String> hashtags, ArrayList<String> receivers);
}
