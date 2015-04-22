package fr.epsi.tp.ws.dao.impl;

import fr.epsi.tp.ws.bd.Bd;
import fr.epsi.tp.ws.bean.ConnectInfo;
import fr.epsi.tp.ws.bean.User;
import fr.epsi.tp.ws.dao.UserDao;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Baptiste on 12/04/2015.
 */
public class UserDaoImpl implements UserDao {
    private Bd bd;

    public UserDaoImpl() {
        this.bd = Bd.getBd("192.168.56.101", 6379);
    }
    /**
     * get a user by his id
     * @param uid unique user id
     * @return user user object
     */
    public User getUserById(String uid) {
        User user = null;
        Jedis jedis = null;
        try {
            jedis =  this.bd.getJedis();
            if (uid != null) {
                List<String> userInfo = jedis.hmget(
                        "user:"+uid+"/data",
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

    /**
     * get user id by his pseudo
     * @param pseudo user pseudo
     * @return user id
     */
    public String getUserIdByPseudo(String pseudo) {
        String uid = "";
        Jedis jedis = null;
        try {
            jedis = this.bd.getJedis();
            if (pseudo != null) {
                uid = jedis.hget("users", pseudo);
            }
        } finally {
            this.bd.closeJedis(jedis);
        }
        return uid;
    }

    /**
     * get user by his pseudo
     * @param pseudo user pseudo
     * @return user
     */
    public User getUserByPseudo(String pseudo) {
        String uid = getUserIdByPseudo(pseudo);
        return getUserById(uid);
    }

    /**
     * add new user in database
     * @param user user to add
     */
    public void addUser(User user) {
        Jedis jedis = null;
        try {
            jedis =  this.bd.getJedis();
            Map<String, String> m = new HashMap<String, String>();
            m.put("pseudo", user.getPseudo());
            m.put("name", user.getFullName());
            m.put("surname", user.getEmail());
            m.put("password", user.getPassword());
            m.put("uid", user.getUid());
            Transaction transaction = jedis.multi();
            transaction.hmset("user:" + user.getUid() + "/data", m);
            transaction.hset("users", user.getPseudo(), user.getUid());
            transaction.exec();
        } finally {
            this.bd.closeJedis(jedis);
        }
    }

    /**
     * get connection information of a user by his uid
     * @param uid unique user id
     * @return connection information of a user
     */
    public ConnectInfo getConnectionInfoById(String uid) {
        ConnectInfo connectInfo = null;
        Jedis jedis = null;
        try {
            jedis =  this.bd.getJedis();
            if (uid != null) {
                List<String> userInfo = jedis.hmget(
                        "user:"+uid+"/data",
                        "pseudo",
                        "password"
                );
                if (userInfo != null && (!userInfo.isEmpty()) && userInfo.size() >= 2) {
                    connectInfo = new ConnectInfo(
                            userInfo.get(0),
                            userInfo.get(1)
                    );
                }
            }
        } finally {
            this.bd.closeJedis(jedis);
        }
        return connectInfo;
    }

    /**
     * get connection information of a user by his pseudo
     * @param pseudo user pseudo
     * @return connection information of a user
     */
    public ConnectInfo getConnectionInfoByPseudo(String pseudo) {
        String uid = getUserIdByPseudo(pseudo);
        return getConnectionInfoById(uid);
    }

    /**
     * get followers by user id
     * @param userId unique user id
     * @param start start index of result list
     * @param end end index of result list
     * @return followers list of follower user
     */
    public List<User> getFollowerById(String userId, int start, int end) {
        List<User> followers = new ArrayList<User>();
        List<String> idFollowers = null;
        Jedis jedis = null;
        try {
            jedis = this.bd.getJedis();
            idFollowers = jedis.lrange("user:"+userId+"/follower", start, end);
        } finally {
            this.bd.closeJedis(jedis);
        }
        if (idFollowers != null) {
            for (String id : idFollowers) {
                User user = getUserById(id);
                if (user != null) {
                    followers.add(user);
                }
            }
        }
        return followers;
    }

    /**
     * get followers by user pseudo
     * @param pseudo user pseudo
     * @param start start index of result list
     * @param end end index of result list
     * @return followers list of follower user
     */
    public List<User> getFollowerByPseudo(String pseudo, int start, int end) {
        String uid = getUserIdByPseudo(pseudo);
        return getFollowerById(uid, start, end);
    }

    /**
     * get following by user id
     * @param userId user unique id
     * @param start start index of result list
     * @param end end index of result list
     * @return following
     */
    public List<User> getFollowingById(String userId, int start, int end) {
        List<User> followings = new ArrayList<User>();
        List<String> idFollowings = null;
        Jedis jedis = null;
        try {
            jedis = this.bd.getJedis();
            idFollowings = jedis.lrange("user:"+userId+"/following", start, end);
        } finally {
            this.bd.closeJedis(jedis);
        }
        if (idFollowings != null) {
            for (String id : idFollowings) {
                User user = getUserById(id);
                if (user != null) {
                    followings.add(user);
                }
            }
        }
        return followings;
    }

    /**
     * get following by user pseudo
     * @param pseudo user pseudo
     * @param start start index of result list
     * @param end end inde of result list
     * @return following
     */
    public List<User> getFollowingByPseudo(String pseudo, int start, int end) {
        String uid = getUserIdByPseudo(pseudo);
        return getFollowingById(uid, start, end);
    }

    /**
     * get list of following id by id
     * @param userId user unique id
     * @param start start index of result list
     * @param end end index of result list
     * @return list of following id
     */
    public List<String> getFollowingIdById(String userId, int start, int end) {
        List<String> idFollowings = null;
        Jedis jedis = null;
        try {
            jedis = this.bd.getJedis();
            idFollowings = jedis.lrange("user:"+userId+"/following", start, end);
        } finally {
            this.bd.closeJedis(jedis);
        }
        return idFollowings;
    }

    /**
     * get list of following id by pseudo
     * @param pseudo user pseudo
     * @param start start index of result list
     * @param end end index of result list
     * @return list of following id
     */
    public List<String> getFollowingIdByPseudo(String pseudo, int start, int end) {
        String uid = getUserIdByPseudo(pseudo);
        return getFollowingIdById(uid, start, end);
    }

    /**
     * add following by id
     * @param followingId following user unique id
     * @param userId user unique id
     */
    public void addFollowingById(String followingId, String userId) {
        Jedis jedis = null;
        try {
            jedis =  this.bd.getJedis();
            Transaction transaction = jedis.multi();
            transaction.lpush("user:" + followingId + "/follower", userId);
            transaction.lpush("user:" + userId + "/following", followingId);
            transaction.exec();
        } finally {
            this.bd.closeJedis(jedis);
        }
    }

    /**
     * add following by pseudo
     * @param followingPseudo following pseudo
     * @param userPseudo user pseudo
     */
    public void addFollowingByPseudo(String followingPseudo, String userPseudo) {
        String followingId = getUserIdByPseudo(followingPseudo);
        String userId = getUserIdByPseudo(userPseudo);
        addFollowingById(followingId, userId);
    }

    /**
     * get number of followers of user
     * @param pseudo user pseudo
     * @return size of follower list of user
     */
    public long getNbFollowersByPseudo(String pseudo) {
        String id = getUserIdByPseudo(pseudo);
        return getNbFollowersById(id);
    }

    /**
     * get number of followers of user
     * @param id user id
     * @return size of followers list of user
     */
    public long getNbFollowersById(String id) {
        long res = -1;
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            res = jedis.llen("user:" + id + "/follower");
        } finally {
            bd.closeJedis(jedis);
        }
        return res;
    }

    /**
     * get number of following of user
     * @param pseudo user pseudo
     * @return size of following list of user
     */
    public long getNbFollowingByPseudo(String pseudo) {
        String id = getUserIdByPseudo(pseudo);
        return getNbFollowingById(id);
    }

    /**
     * get number of following of user
     * @param id user id
     * @return size of following list of user
     */
    public long getNbFollowingById(String id) {
        long res = -1;
        Jedis jedis = null;
        try {
            jedis = bd.getJedis();
            res = jedis.llen("user:" + id + "/following");
        } finally {
            bd.closeJedis(jedis);
        }
        return res;
    }

    public void setConnectionInfo(ConnectInfo connectInfo) {
        //todo: setConnectionInfo
    }

    public void setUser(User user) {
        //todo: setUser
    }
}
