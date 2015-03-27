package fr.epsi.tp.ws.bd;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Baptiste on 24/03/2015.
 */
public class Bd {

    private JedisPool pool;
    private Jedis jedis;
    private String host;
    private int port;

    private static Bd bd;

    private Bd (String host, int port) {
        this.host = host;
        this.port = port;
        this.pool = new JedisPool(new JedisPoolConfig(), host, port);
    }

    public final static Bd getBd(String host, int port) {
        if (Bd.bd == null) {
            Bd.bd = new Bd(host, port);
        }
        return Bd.bd;
    }

    public Jedis getJedis() {
        if (this.jedis == null) {
            this.jedis = this.pool.getResource();
        }
        return this.jedis;
    }
}
