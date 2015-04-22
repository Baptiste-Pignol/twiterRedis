package fr.epsi.tp.ws.dao;

/**
 * Created by Baptiste on 12/04/2015.
 */

import fr.epsi.tp.ws.bean.ConnectInfo;
import fr.epsi.tp.ws.bean.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    public User getUserByPseudo(String pseudo);
    public void setUser(User user);
    public User getUserById(String uid);
    public ConnectInfo getConnectionInfoByPseudo(String pseudo);
    public ConnectInfo getConnectionInfoById(String uid);
    public void setConnectionInfo(ConnectInfo connectInfo);
    public List<User> getFollowerById(String uid);
    public List<User> getFollowerByPseudo(String pseudo);
    public List<User> getFollowingById(String uid);
    public List<User> getFollowingByPseudo(String pseudod);
    public void addFollowingById(String followingId, String userId);
    public void addFollowingByPseudo(String followingPseudo, String userPseudo);
    public void addUser(User user);
    public Set<String> getFollowingIdById(String userId);
    public Set<String> getFollowingIdByPseudo(String pseudo);
    public String getUserIdByPseudo(String pseudo);
    public long getNbFollowingByPseudo(String pseudo);
    public long getNbFollowingById(String id);
    public long getNbFollowersByPseudo(String pseudo);
    public long getNbFollowersById(String id);
}
