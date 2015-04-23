package fr.epsi.tp.ws.services.impl;

import fr.epsi.tp.ws.bean.Tweet;
import fr.epsi.tp.ws.bean.User;
import fr.epsi.tp.ws.dao.TweetDao;
import fr.epsi.tp.ws.dao.UserDao;
import fr.epsi.tp.ws.dao.impl.TweetDaoImpl;
import fr.epsi.tp.ws.dao.impl.UserDaoImpl;
import fr.epsi.tp.ws.services.TweetService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Baptiste on 17/03/2015.
 */
public class TweetServiceImpl implements TweetService {
    /**
     * get tweets
     * @param userId user unique id
     * @param start start index
     * @param end end index
     * @return return all tweets of user behind start and end index
     */
    public List<Tweet> getTweets(String userId, int start, int end) {
        TweetDao tweetDao = new TweetDaoImpl();
        return tweetDao.getTweetsByUserId(userId, start, end);
    }

    /**
     * get 100 first tweets
     * @param userId user unique id
     * @return 100 first user tweet
     */
    public List<Tweet> getTweets(String userId) {
        TweetDao tweetDao = new TweetDaoImpl();
        return tweetDao.getTweetsByUserId(userId, 0, 100);
    }

    /**
     * get 100 first tweets
     * @param pseudo user pseudo
     * @return 100 first user tweet
     */
    public List<Tweet> getTweetsWithPseudo(String pseudo) {
        TweetDao tweetDao = new TweetDaoImpl();
        return tweetDao.getTweetsByUserPseudo(pseudo, 0, 100);
    }

    /**
     * get tweet
     * @param tweetId tweet unique id
     * @return tweet
     */
    public Tweet getTweet(String tweetId) {
        TweetDao tweetDao = new TweetDaoImpl();
        return tweetDao.getTweetById(tweetId);
    }

    /**
     * create a new tweet
     * @param tweet tweet to create
     * @param userUid unique user id
     */
    public void createTweet(Tweet tweet, String userUid) {
        tweet.setSenderId(userUid);
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserById(userUid);
        tweet.setSenderPseudo(user.getPseudo());
        Calendar calendar = Calendar.getInstance();
        String timestamp = String.valueOf(calendar.getTime().getTime());
        tweet.setTimestamp(timestamp);
        ArrayList<String> hashtag = findHastag(tweet.getMessage());
        ArrayList<String> receiver = findReceiver(tweet.getMessage());
        TweetDao tweetDao = new TweetDaoImpl();
        tweetDao.addTweetByUserId(userUid, tweet, hashtag, receiver);
    }

    private ArrayList<String> findHastag(String message) {
        ArrayList<String> res = new ArrayList<String>();
        Pattern tag = Pattern.compile("(?:^|\\s|[\\p{Punct}&&[^/]])(#[\\p{L}0-9-_]+)");
        Matcher matcher = tag.matcher(message);
        while(matcher.find())
            res.add(matcher.group(1));
        return  res;
    }

    private ArrayList<String> findReceiver(String message) {
        ArrayList<String> res = new ArrayList<String>();
        Pattern tag = Pattern.compile("(?:^|\\s|[\\p{Punct}&&[^/]])(@[\\p{L}0-9-_]+)");
        Matcher matcher = tag.matcher(message);
        while(matcher.find())
            res.add(matcher.group(1));
        return  res;
    }

    /**
     * get wall tweets
     * @param pseudo user pseudo
     * @return list of wall tweets
     */
    public List<Tweet> getWallTweets(String pseudo) {
        TweetDao tweetDao = new TweetDaoImpl();
        return tweetDao.getWallTweetByPseudo(pseudo, 0, 100);
    }

    /**
     * get wall tweets
     * @param pseudo user pseudo
     * @param start start index
     * @param end end index
     * @return list of wall tweets
     */
    public List<Tweet> getWallTweets(String pseudo, int start, int end) {
        TweetDao tweetDao = new TweetDaoImpl();
        return tweetDao.getWallTweetByPseudo(pseudo, start, end);
    }

    /**
     * get numbers of tweets
     * @param pseudo user pseudo
     * @return size of list of tweets
     */
    public long getNbTweets(String pseudo) {
        TweetDao tweetDao = new TweetDaoImpl();
        return tweetDao.getNbTweetByPseudo(pseudo);
    }


    /**
     * remove tweet
     * @param idTweet id of tweet
     * @param idTweet uid id of user
     */
    public void removeTweet(String  uid, String idTweet) {
        TweetDao tweetDao = new TweetDaoImpl();
        tweetDao.removeTweet(uid, idTweet);
    }

    /**
     * get tweets by hashtag
     * @param hashtag
     * @return tweets
     */
    public List<Tweet> getTweetsByHashtag(String hashtag) {
        TweetDao tweetDao = new TweetDaoImpl();
        return tweetDao.getTweetsByHashtag(hashtag);
    }

    public List<Tweet> getFavorite(String pseudo) {
        TweetDao tweetDao = new TweetDaoImpl();
        UserDao userDao = new UserDaoImpl();
        String userId = userDao.getUserIdByPseudo(pseudo);
        return tweetDao.getFavorite(userId);
    }
    public void addFavorite(String userId, Tweet tweet) {
        TweetDao tweetDao = new TweetDaoImpl();
        tweetDao.addFavorite(userId, tweet);
    }
    public void removeFavorite(String userId, Tweet tweet) {
        TweetDao tweetDao = new TweetDaoImpl();
        tweetDao.removeFavorite(userId, tweet);
    }

}
