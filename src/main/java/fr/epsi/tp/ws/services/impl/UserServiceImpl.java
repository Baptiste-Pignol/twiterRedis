package fr.epsi.tp.ws.services.impl;

import fr.epsi.tp.ws.bd.Bd;
import fr.epsi.tp.ws.bean.User;
import fr.epsi.tp.ws.services.UserService;
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
public class UserServiceImpl implements UserService {
    private Bd bd = Bd.getBd("192.168.56.101", 6379);

    @Override
    public String getUid(String pseudo) {
        String uid = "";
        User user = null;
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            if (pseudo != null) {
                uid = jedis.hget("users", pseudo);
            }
        } finally {
            bd.closeJedis(jedis);
        }
        return uid;

    }

    @Override
    public User getUser(String uid) {
        User user = null;
        Jedis jedis = null;
        try {
            jedis =  bd.getJedis();
            if (uid != null) {
                List<String> userInfo = jedis.hmget(
                        "user:"+uid,
                        "pseudo",
                        "name",
                        "surname",
                        "uid"
                );
                if (userInfo != null && (!userInfo.isEmpty()) && userInfo.size() >= 4) {
                    user = new User(
                            userInfo.get(0),
                            userInfo.get(1),
                            userInfo.get(2),
                            "",
                            userInfo.get(3)
                    );
                }
            }
        } finally {
            bd.closeJedis(jedis);
        }
        return user;
    }

    @Override
    public User getUserWithPseudo(String pseudo) {
        User user = null;
        String uid = getUid(pseudo);
        user = getUser(uid);
        return user;
    }

    @Override
    public boolean createUser(User user) {
        Jedis jedis = null;
        boolean res = false;
        try {
            jedis =  bd.getJedis();
            Map<String, String> m = new HashMap<String, String>();
            m.put("pseudo", user.getPseudo());
            m.put("name", user.getName());
            m.put("surname", user.getSurname());
            m.put("password", user.getPassword());
            m.put("uid", user.getUid());
            jedis.hmset("user:" + user.getUid(), m);
            jedis.hset("users", user.getPseudo(), user.getUid());
            res = true;
        } finally {
            bd.closeJedis(jedis);
        }
        return res;
    }

    @Override
    public List<User> getFollowers(String uid) {
        List<User> list = new ArrayList<User>();
       // todo
        return list;
    }

    @Override
    public List<User> getFollowed(String uid) {
        List<User> list = new ArrayList<User>();
        // todo
        return list;
    }

    @Override
    public void addFollowed(String uidFollower, String uidFollowed) {
        Jedis jedis = null;
        try {
            jedis =  bd.getJedis();
            jedis.lpush("user:"+uidFollowed+"followers", uidFollower);
            jedis.lpush("user:"+uidFollower+"followed", uidFollowed);
        } finally {
            bd.closeJedis(jedis);
        }
    }

}
