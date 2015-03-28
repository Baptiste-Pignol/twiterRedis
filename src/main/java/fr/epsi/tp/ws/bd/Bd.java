package fr.epsi.tp.ws.bd;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Baptiste on 24/03/2015.
 */

/**
 * database connection class
 */
public class Bd {


    private JedisPool pool;
    private Jedis jedis;
    private String host;
    private int port;
    private static Bd bd;

    /**
     *
     * @param host
     * @param port
     */
    private Bd (String host, int port) {
        this.host = host;
        this.port = port;
        this.pool = new JedisPool(new JedisPoolConfig(), host, port);
    }

    /**
     *
     * @param host
     * @param port
     * @return
     */
    public final static Bd getBd(String host, int port) {
        if (Bd.bd == null) {
            Bd.bd = new Bd(host, port);
        }
        return Bd.bd;
    }

    /**
     * create a jedis database connection
     * @return jedis connection
     */
    public Jedis getJedis() {
        /*if (this.jedis == null) {
            this.jedis = this.pool.getResource();
        }
        return this.jedis;*/
        return this.pool.getResource();
    }

    /**
     * Close a jedis database connection
     * @param jedis
     */
    public void closeJedis(Jedis jedis) {
        jedis.close();
    }
}
