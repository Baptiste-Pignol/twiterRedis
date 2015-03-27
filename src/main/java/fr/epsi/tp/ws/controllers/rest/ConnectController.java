/**
 * Created by Baptiste on 17/03/2015.
 */
package fr.epsi.tp.ws.controllers.rest;

import fr.epsi.tp.ws.bean.ConnectInfo;
import fr.epsi.tp.ws.services.ConnectService;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.log4j.Logger;

import java.util.UUID;

@Controller
public class ConnectController {
    Logger logger = Logger.getLogger(ConnectController.class);

    @Resource
    ConnectService connectService;

    @RequestMapping(value="/connect", method=RequestMethod.POST)
    public @ResponseBody
    void connect(@RequestBody ConnectInfo connectInfo, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        logger.info("connection requested");
        String pseudo = connectInfo.getPseudo();
        String password = connectInfo.getPassword();

        boolean connectOk = false;

        if (pseudo != null && password != null && !pseudo.equals("") && !password.equals("")) {
            if (connectService.checkLogin(pseudo, password)) {
                logger.info("connection info ok");
                connectOk = true;
                String tokenValue = UUID.randomUUID().toString();
                String uid = connectService.getUid(pseudo);
                request.getSession(true).setAttribute("token", tokenValue);
                request.getSession(true).setAttribute("uid", uid);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        if (!connectOk) {
            logger.info("connection fail, bad connection info");
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

    }

}
