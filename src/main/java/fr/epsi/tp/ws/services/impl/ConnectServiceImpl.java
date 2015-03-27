package fr.epsi.tp.ws.services.impl;

import fr.epsi.tp.ws.bd.Bd;
import fr.epsi.tp.ws.services.ConnectService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Baptiste on 17/03/2015.
 */
public class ConnectServiceImpl implements ConnectService{
    private Bd bd = Bd.getBd("192.168.56.101", 6379);

    @Override
    public boolean checkLogin(String pseudo, String password) {
        boolean checkOk = false;
        String uid = getUid(pseudo);
        if (uid != null) {
            Jedis jedis = bd.getJedis();
            String dbPassowrd = jedis.hget("user:"+uid, "password");
            if (dbPassowrd != null && dbPassowrd.equals(password)) {
                checkOk = true;
            }
        }
        return checkOk;
    }

    @Override
    public String getUid(String pseudo)  {
        String uid = null;
        Jedis jedis = bd.getJedis();
        uid = jedis.hget("users", pseudo);
        return uid;
    }

}
