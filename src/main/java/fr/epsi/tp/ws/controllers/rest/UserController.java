package fr.epsi.tp.ws.controllers.rest;

import fr.epsi.tp.ws.bean.User;
import fr.epsi.tp.ws.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Baptiste on 17/03/2015.
 */

@Controller
public class UserController {
    Logger logger = Logger.getLogger(UserController.class);

    @Resource
    UserService userService;

    /**
     * get a user
     * @param pseudo the user pseudo
     * @param request http request
     * @param response http response
     * @return a user
     */
    @RequestMapping(value="/users/{pseudo}", method= RequestMethod.GET)
    public @ResponseBody
    User getUser(@PathVariable("pseudo") String pseudo, HttpServletRequest request, HttpServletResponse response) {
        String uid = (String) request.getSession(true).getAttribute("uid");
        if (uid == null || uid.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        logger.info("getUser");
        return userService.getUserWithPseudo(pseudo);
    }

    /**
     * get current user
     * @param request http request
     * @param response http response
     * @return the current user
     */
    @RequestMapping(value="/users", method= RequestMethod.GET)
    public @ResponseBody
    User getUser(HttpServletRequest request, HttpServletResponse response) {
        String uid = (String) request.getSession(true).getAttribute("uid");
        if (uid == null || uid.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        logger.info("getUser");
        return userService.getUser(uid);
    }

    /**
     * create a user
     * @param user the new user
     * @param request http request
     * @param httpServletResponse http response
     */
    @RequestMapping(value="/users", method= RequestMethod.POST)
    public @ResponseBody
    void createUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        logger.info("createUser");
        boolean res = userService.createUser(user);
        if (res) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    /**
     * get follower
     * @param request http request
     * @param response http response
     * @return list of user who follow the current user
     */
    @RequestMapping(value="users/followers", method= RequestMethod.GET)
    public @ResponseBody
    List<User> getFollowers(HttpServletRequest request, HttpServletResponse response) {
        logger.info("getFollowers");
        String uid = (String) request.getSession(true).getAttribute("uid");
        if (uid == null || uid.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        List<User> list = userService.getFollowers(uid);
        if (list == null || list.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return list;
    }

    /**
     *  get followed
     * @param request http request
     * @param response http response
     * @return list of user who are followed by the current user
     */
    @RequestMapping(value="users/followed", method= RequestMethod.GET)
    public @ResponseBody
    List<User> getFollowed(HttpServletRequest request, HttpServletResponse response) {
        logger.info("getFollowed");
        String uid = (String) request.getSession(true).getAttribute("uid");
        if (uid == null || uid.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        List<User> list = userService.getFollowed(uid);
        if (list == null || list.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return list;
    }

    /**
     * get followers
     * @param pseudo user pseudo
     * @param request http request
     * @param response http response
     * @return list of user who follow the user
     */
    @RequestMapping(value="users/followers/{pseudo}", method= RequestMethod.GET)
    public @ResponseBody
    List<User> getFollowers(@PathVariable("pseudo") String pseudo, HttpServletRequest request, HttpServletResponse response) {
        logger.info("getFollowers");
        String uid = userService.getUid(pseudo);
        if (uid == null || uid.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        List<User> list = userService.getFollowers(uid);
        if (list == null || list.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return list;
    }

    /**
     * get followed
    * @param pseudo user pseudo
    * @param request http request
    * @param response http response
    * @return list of user who are followed by the user
    */
    @RequestMapping(value="users/followed/{pseudo}", method= RequestMethod.GET)
    public @ResponseBody
    List<User> getFollowed(@PathVariable("pseudo") String pseudo, HttpServletRequest request, HttpServletResponse response) {
        logger.info("getFollowed");
        String uid = userService.getUid(pseudo);
        if (uid == null || uid.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        List<User> list = userService.getFollowed(uid);
        if (list == null || list.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return list;
    }

    /**
     * add followed
     * @param pseudo pseudo of the user to follow
     * @param request http request
     * @param response http response
     */
    @RequestMapping(value="users/followed", method= RequestMethod.POST)
    public @ResponseBody
    void setFollowed(@RequestBody String  pseudo, HttpServletRequest request, HttpServletResponse response) {
        String uidFollowed = userService.getUid(pseudo);
        String uidFollower = (String) request.getSession(true).getAttribute("uid");
        userService.addFollowed(uidFollower, uidFollowed);

    }
}
