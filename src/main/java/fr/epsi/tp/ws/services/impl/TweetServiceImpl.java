package fr.epsi.tp.ws.services.impl;

import fr.epsi.tp.ws.bd.Bd;
import fr.epsi.tp.ws.bean.Tweet;
import fr.epsi.tp.ws.bean.User;
import fr.epsi.tp.ws.services.TweetService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Baptiste on 17/03/2015.
 */
public class TweetServiceImpl implements TweetService {
    private Bd bd = Bd.getBd("192.168.56.101", 6379);

    @Override
    public List<Tweet> getTweets(String userId) {
        List<Tweet> list = new ArrayList<Tweet>();
        List<String> idTweets = null;
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            idTweets = jedis.lrange("user:"+userId+"tweets", 0, 100);
        } finally {
            bd.closeJedis(jedis);
        }
        if (idTweets != null) {
            for (String id : idTweets) {
                Tweet t = getTweet(id);
                list.add(t);
            }
        }
        return list;
    }

    @Override
    public Tweet getTweet(String tweetId) {
        Tweet tweet = null;
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            String message = jedis.hget("tweet:"+tweetId, "message");
            tweet = new Tweet(tweetId, message);
        } finally {
            bd.closeJedis(jedis);
        }
        return tweet;
    }

    @Override
    public Tweet createTweet(Tweet tweet, String userUid) {
        Tweet newTweet = tweet;
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            Map<String, String> m = new HashMap<String, String>();
            m.put("message", tweet.getMessage());
            jedis.hmset("tweet:" + tweet.getId(), m);
            jedis.lpush("user:" + userUid + "tweets", tweet.getId());
        } catch (JedisException ex) {
            return null;
        } finally {
            bd.closeJedis(jedis);
        }
        return tweet;
    }
}
