package fr.epsi.tp.ws.controllers.rest;

import fr.epsi.tp.ws.bean.Tweet;
import fr.epsi.tp.ws.services.TweetService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Baptiste on 17/03/2015.
 */

@Controller
public class TweetController {
    Logger logger = Logger.getLogger(TweetController.class);

    @Resource
    TweetService tweetService;

    /**
     * get all tweets of current user
     * @param request http request
     * @param response http response
     * @return list of current user tweets
     */
    @RequestMapping(value="/tweets", method= RequestMethod.GET)
    public @ResponseBody
    List<Tweet> getTweets(HttpServletRequest request, HttpServletResponse response) {
        logger.info("getTweets");
        String uid = (String) request.getSession(true).getAttribute("uid");
        if (uid == null || uid.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
        List<Tweet> list = tweetService.getTweets(uid);
        if (list == null || list.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return list;
    }

    /**
     * get all tweets of a user
     * @param pseudo user pseudo
     * @param request http request
     * @param response http response
     * @return list of user tweets
     */
    @RequestMapping(value="/users/{pseudo}/tweets", method= RequestMethod.GET)
    public @ResponseBody
    List<Tweet> getTweets(@PathVariable("pseudo") String pseudo, HttpServletRequest request, HttpServletResponse response) {
        logger.info("getTweets");
        String uid = (String) request.getSession(true).getAttribute("uid");
        if (uid == null || uid.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        List<Tweet> list = tweetService.getTweetsWithPseudo(pseudo);
        if (list != null && list.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return list;
    }

    /**
     * get number of tweets of a user
     * @param pseudo user pseudo
     * @param request http request
     * @param response http response
     * @return user tweets number
     */
    @RequestMapping(value="/users/{pseudo}/tweets/size", method= RequestMethod.GET)
    public @ResponseBody
    Map<String, Long> getTweetsSize(@PathVariable("pseudo") String pseudo, HttpServletRequest request, HttpServletResponse response) {
        logger.info("getTweetsSize");
        String uid = (String) request.getSession(true).getAttribute("uid");
        if (uid == null || uid.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        Map<String, Long> nb = new HashMap<String, Long>();
        nb.put("nbTweet", tweetService.getNbTweets(pseudo));
        if (nb.get("nbTweet") == -1) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return nb;
    }

    /**
     * get a tweet
     * @param id tweet uid
     * @param request http request
     * @param response http response
     * @return a tweet
     */
    @RequestMapping(value="/tweets/{tweetId}", method= RequestMethod.GET)
    public @ResponseBody
    Tweet getTweet(@PathVariable("tweetId") String id, HttpServletRequest request, HttpServletResponse response) {
        logger.info("getTweet");
        String uid = (String) request.getSession(true).getAttribute("uid");
        if (uid == null || uid.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return tweetService.getTweet(id);
    }

    /**
     * create a new tweet
     * @param tweet the new tweet
     * @param request http request
     * @param response http response
     */
    @RequestMapping(value="/tweets", method = RequestMethod.POST)
    public @ResponseBody
    void createTweet(@RequestBody Tweet tweet, HttpServletRequest request, HttpServletResponse response) {
        logger.info("setTweets");
        String uid = (String) request.getSession(true).getAttribute("uid");
        if (uid == null || uid.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        tweetService.createTweet(tweet, uid);
    }

    /**
     * get all tweets to show in user wall
     * @param pseudo user pseudo
     * @param request http request
     * @param response http response
     * @return list of tweet to show in user wall
     */
    @RequestMapping(value="/users/{pseudo}/wallTweets", method= RequestMethod.GET)
    public @ResponseBody
    List<Tweet> getWallTweets(@PathVariable("pseudo") String pseudo,HttpServletRequest request, HttpServletResponse response) {
        logger.info("getWallTweets");
        String uid = (String) request.getSession(true).getAttribute("uid");
        if (uid == null || uid.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        List<Tweet> list = tweetService.getWallTweets(pseudo);
        if (list == null || list.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return list;
    }


}
