package fr.epsi.tp.ws.dao.impl;

import fr.epsi.tp.ws.bd.Bd;
import fr.epsi.tp.ws.bean.Tweet;
import fr.epsi.tp.ws.dao.TweetDao;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.ZParams;

import java.util.*;

/**
 * Created by Baptiste on 12/04/2015.
 */
public class TweetDaoImpl implements TweetDao {
    private Bd bd = Bd.getBd("192.168.56.101", 6379);

    /**
     * get a tweet by is id
     * @param tweetId unique id of the tweet
     * @return a tweet
     */
    public Tweet getTweetById(String tweetId) {
        Tweet tweet = null;
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            String message = jedis.hget("tweet:"+tweetId, "message");
            String senderId = jedis.hget("tweet:"+tweetId, "senderId");
            String timestamp = jedis.hget("tweet:"+tweetId, "timestamp");
            String senderPseudo =  jedis.hget("tweet:"+tweetId, "senderPseudo");
            tweet = new Tweet(tweetId, message, senderId, timestamp, senderPseudo);
        } finally {
            bd.closeJedis(jedis);
        }
        return tweet;
    }

    /**
     * get the list of user tweets
     * @param userId unique id of the user
     * @param start start index of the result
     * @param end end index of the result
     * @return list of user tweets
     */
    public List<Tweet> getTweetsByUserId(String userId, int start, int end) {
        List<Tweet> list = new ArrayList<Tweet>();
        Set<String> idTweets = null;
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            idTweets = jedis.zrange("user:"+userId+"/tweets", start, end);
        } finally {
            bd.closeJedis(jedis);
        }
        if (idTweets != null) {
            for (String id : idTweets) {
                Tweet t = getTweetById(id);
                list.add(t);
            }
        }
        return list;
    }

    /**
     * get tweets by user pseudo
     * @param pseudo pseudo of the user
     * @param start start index of the result
     * @param end end index of the result
     * @return tweets
     */
    public List<Tweet> getTweetsByUserPseudo(String pseudo, int start, int end) {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        String uid = userDaoImpl.getUserIdByPseudo(pseudo);
        return getTweetsByUserId(uid, start, end);
    }

    /**
     * add new tweet by user id
     * @param userUid unique id of the user
     * @param tweet tweet to add
     */
    public void addTweetByUserId(String userUid, Tweet tweet) {
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            Map<String, String> m = new HashMap<String, String>();
            m.put("message", tweet.getMessage());
            m.put("timestamp", tweet.getTimestamp());
            m.put("senderId", tweet.getSenderId());
            m.put("senderPseudo", tweet.getSenderPseudo());
            m.put("id", tweet.getId());
            Transaction transaction = jedis.multi();
            transaction.hmset("tweet:" + tweet.getId(), m);
            transaction.zadd("user:" + userUid + "/tweets", Double.parseDouble(tweet.getTimestamp()),tweet.getId());
            transaction.exec();
        } finally {
            bd.closeJedis(jedis);
        }
    }

    /**
     * add new tweet by user pseudo
     * @param pseudo pseudo of the user
     * @param tweet tweet to add
     */
    public void addTweetByUserPseudo(String pseudo, Tweet tweet) {
        UserDaoImpl userDao =  new UserDaoImpl();
        String id = userDao.getUserIdByPseudo(pseudo);
        addTweetByUserId(id, tweet);
    }

    /**
     * get tweets by hashtag
     * @param hashtag hastag to search
     * @param start start index of the result
     * @param end end index of the result
     * @return list of tweets
     */
    public List<Tweet> getTweetsByHashtag(String hashtag, int start, int end) {
        List<Tweet> list = new ArrayList<Tweet>();
        Set<String> idTweets = null;
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            idTweets = jedis.zrange("hashtag:"+hashtag+"/tweets", start, end);
        } finally {
            bd.closeJedis(jedis);
        }
        if (idTweets != null) {
            for (String id : idTweets) {
                Tweet t = getTweetById(id);
                list.add(t);
            }
        }
        return list;
    }

    /**
     * get all tweet to render in user wall by user id
     * @param uid unique id of the user
     * @param start index of the result
     * @param end end index of the result
     * @return list of tweets of user wall
     */
    public List<Tweet> getWallTweetById(String uid, int start, int end) {
        List<Tweet> list = new ArrayList<Tweet>();
        UserDaoImpl userDao = new UserDaoImpl();
        List<String> following = userDao.getFollowingIdById(uid, 0, -1);
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            Transaction transaction = jedis.multi();
            ZParams params = new ZParams();
            transaction.zunionstore("out", params.aggregate(ZParams.Aggregate.MAX),  "user:" + uid + "/tweets", "user:" + uid + "/receive");
            for (String followingId : following) {
                transaction.zunionstore("out", params.aggregate(ZParams.Aggregate.MAX), "out", "user:" + followingId + "/tweets");
            }
            Response<Set<String>> wallTweetsResult =  transaction.zrange("out", start, end);
            transaction.exec();
            Set<String> wallTweets = wallTweetsResult.get();
            if (wallTweets != null) {
                for (String id : wallTweets) {
                    Tweet t = getTweetById(id);
                    list.add(t);
                }
            }
        } finally {
            bd.closeJedis(jedis);
        }
        return list;
    }

    /**
     * get all tweet to render in user wall by user pseudo
     * @param pseudo user pseudo
     * @param start start index of the result
     * @param end end index of the result
     * @return list of tweets of user wall
     */
    public List<Tweet> getWallTweetByPseudo(String pseudo, int start, int end) {
        UserDaoImpl userDao =  new UserDaoImpl();
        String id = userDao.getUserIdByPseudo(pseudo);
        return getWallTweetById(id, start, end);
    }

    /**
     * add tweet with hashtag
     * @param hashtag hashtag to link to the tweet
     * @param tweet tweet to link to the hashtag
     */
    public void addHashtagTweet(String hashtag, Tweet tweet) {
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            jedis.zadd("hashtag:" + hashtag + "/tweets", Double.parseDouble(tweet.getTimestamp()),tweet.getId());
        } finally {
            bd.closeJedis(jedis);
        }
    }

    /**
     * add tweet with receiver by id
     * @param id user unique id
     * @param tweet tweet to link to the receiver user
     */
    public void addReceiverById(String id, Tweet tweet) {
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            jedis.zadd("user:" + id + "/receive", Double.parseDouble(tweet.getTimestamp()),tweet.getId());
        } finally {
            bd.closeJedis(jedis);
        }
    }

    /**
     * add tweet with receiver by pseudo
     * @param pseudo user pseudo
     * @param tweet tweet to link to the receiver user
     */
    public void addReceiverByPseudo(String pseudo, Tweet tweet) {
        UserDaoImpl userDao =  new UserDaoImpl();
        String id = userDao.getUserIdByPseudo(pseudo);
        addReceiverById(id, tweet);
    }

    /**
     * get number of tweets of a user
     * @param pseudo user pseudo
     * @return length of tweet list
     */
    public long getNbTweetByPseudo(String pseudo) {
        long res = -1;
        UserDaoImpl userDao =  new UserDaoImpl();
        String id = userDao.getUserIdByPseudo(pseudo);
        res = getNbTweetById(id);
        return res;
    }

    /**
     * get number of tweets of a user
     * @param id user id
     * @return length of tweet list
     */
    public long getNbTweetById(String id) {
        long res = -1;
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            res = jedis.zcount("user:" + id + "/tweets", "-inf", "+inf");
        } finally {
            bd.closeJedis(jedis);
        }
        return res;
    }


}
