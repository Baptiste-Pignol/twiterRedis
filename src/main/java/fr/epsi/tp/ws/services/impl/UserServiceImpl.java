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
     * @param start start index
     * @param end end index
     * @return a list of user who follow the user
     */
    public List<User> getFollowers(String userId, int start, int end) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getFollowerById(userId, start, end);
    }

    /**
     * get list of 100 first follower of the user
     * @param userId user unique id
     * @return a list of 100 first user who follow the user
     */
    public List<User> getFollowers(String userId) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getFollowerById(userId, 0, 100);
    }

    /**
     * get list of following of the user
     * @param userId user unique id
     * @param start start index
     * @param end end index
     * @return a list of user who are following by the user
     */
    public List<User> getFollowing(String userId, int start, int end) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getFollowingById(userId, start, end);
    }

    /**
     * get list of 100 first following of the user
     * @param userId user unique id
     * @return a list of 100 first user who are following by the user
     */
    public List<User> getFollowing(String userId) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getFollowingById(userId, 0, 100);
    }

    /**
     * add a new following
     * @param uidFollower uid of the user follower
     * @param uidFollowing uid of the user to follow
     */
    public void addFollowing(String uidFollower, String uidFollowing) {
        UserDao userDao = new UserDaoImpl();
        userDao.addFollowingById(uidFollower, uidFollowing);
    }
}
