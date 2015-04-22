package fr.epsi.tp.ws.services.impl;

import fr.epsi.tp.ws.bean.User;
import fr.epsi.tp.ws.dao.UserDao;
import fr.epsi.tp.ws.dao.impl.UserDaoImpl;
import fr.epsi.tp.ws.services.UserService;

import java.util.List;

/**
 * Created by Baptiste on 17/03/2015.
 */
public class UserServiceImpl implements UserService {
    /**
     * get user uid
     * @param pseudo user pseudo
     * @return uid of user
     */
    public String getUid(String pseudo) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUserIdByPseudo(pseudo);
    }

    /**
     * get user
     * @param uid unique user id
     * @return user
     */
    public User getUser(String uid) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUserById(uid);
    }

    /**
     * get user
     * @param pseudo user pseudo
     * @return user
     */
    public User getUserWithPseudo(String pseudo) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUserByPseudo(pseudo);
    }

    /**
     * create new user
     * @param user user to create
     */
    public void createUser(User user) {
        UserDao userDao = new UserDaoImpl();
        userDao.addUser(user);
    }

    /**
     * get list of follower of the user
     * @param userId user unique id
     * @return a list of user who follow the user
     */
    public List<User> getFollowers(String userId) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getFollowerById(userId);
    }

    /**
     * get list of following of the user
     * @param userId user unique id
     * @return a list of user who are following by the user
     */
    public List<User> getFollowing(String userId) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getFollowingById(userId);
    }

    /**
     * add a new following
     * @param uidFollower uid of the user follower
     * @param uidFollowing uid of the user to follow
     */
    public void addFollowing(String uidFollowing, String uidFollower) {
        UserDao userDao = new UserDaoImpl();
        userDao.addFollowingById(uidFollowing, uidFollower);
    }

    /**
     * get numbers of followers
     * @param pseudo user pseudo
     * @return size of list of followers
     */
    public long getNbFollowers(String pseudo) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getNbFollowersByPseudo(pseudo);
    }

    /**
     * get numbers of following
     * @param pseudo user pseudo
     * @return size of list of following
     */
    public long getNbFollowing(String pseudo) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getNbFollowingByPseudo(pseudo);
    }
}
