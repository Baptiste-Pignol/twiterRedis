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
    public boolean createUser(User user);
    public List<User> getFollowers(String uid);
    public List<User> getFollowed(String uid);
    public void addFollowed(String uidFollower, String uidFollowed);
}
