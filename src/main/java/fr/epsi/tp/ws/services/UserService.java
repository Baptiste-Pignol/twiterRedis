package fr.epsi.tp.ws.services;

import fr.epsi.tp.ws.bean.User;

import java.util.List;

/**
 * Created by Baptiste on 17/03/2015.
 */
public interface UserService {
    public String getUid(String pseudo);
    public User getUser(String userId);
    public User getUserWithPseudo(String pseudo);
    public void createUser(User user);
    public List<User> getFollowers(String uid);
    public List<User> getFollowers(String userId, int start, int end);
    public List<User> getFollowing(String uid);
    public List<User> getFollowing(String userId, int start, int end);
    public void addFollowing(String uidFollower, String uidFollowing);
}
