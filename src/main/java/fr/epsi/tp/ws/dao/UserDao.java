package fr.epsi.tp.ws.dao;

/**
 * Created by Baptiste on 12/04/2015.
 */

import fr.epsi.tp.ws.bean.ConnectInfo;
import fr.epsi.tp.ws.bean.User;

import java.util.List;

public interface UserDao {
    public User getUserByPseudo(String pseudo);
    public void setUser(User user);
    public User getUserById(String uid);
    public ConnectInfo getConnectionInfoByPseudo(String pseudo);
    public ConnectInfo getConnectionInfoById(String uid);
    public void setConnectionInfo(ConnectInfo connectInfo);
    public List<User> getFollowerById(String uid, int start, int end);
    public List<User> getFollowerByPseudo(String pseudo, int start, int end);
    public List<User> getFollowingById(String uid, int start, int end);
    public List<User> getFollowingByPseudo(String pseudo, int start, int end);
    public void addFollowingById(String followingId, String userId);
    public void addFollowingByPseudo(String followingPseudo, String userPseudo);
    public void addUser(User user);
    public List<String> getFollowingIdById(String userId, int start, int end);
    public List<String> getFollowingIdByPseudo(String pseudo, int start, int end);
    public String getUserIdByPseudo(String pseudo);
    public long getNbFollowingByPseudo(String pseudo);
    public long getNbFollowingById(String id);
    public long getNbFollowersByPseudo(String pseudo);
    public long getNbFollowersById(String id);
}
