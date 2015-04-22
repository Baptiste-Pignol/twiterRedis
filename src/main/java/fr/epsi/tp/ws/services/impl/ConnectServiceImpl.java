package fr.epsi.tp.ws.services.impl;

import fr.epsi.tp.ws.dao.UserDao;
import fr.epsi.tp.ws.dao.impl.UserDaoImpl;
import fr.epsi.tp.ws.services.ConnectService;

/**
 * Created by Baptiste on 17/03/2015.
 */
public class ConnectServiceImpl implements ConnectService{
    /**
     * check the login information
     * @param pseudo user pseudo
     * @param password user password
     * @return true if login information are ok
     */
    public boolean checkLogin(String pseudo, String password) {
        UserDao userDao = new UserDaoImpl();
        boolean checkOk = false;
        if (userDao.getConnectionInfoByPseudo(pseudo) != null) {
            String dbPassword = userDao.getConnectionInfoByPseudo(pseudo).getPassword();
            if (dbPassword != null && dbPassword.equals(password)) {
                checkOk = true;
            }
        }
        return checkOk;
    }

    /**
     * get user uid
     * @param pseudo user pseudo
     * @return uid user unique id
     */
    public String getUid(String pseudo)  {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUserIdByPseudo(pseudo);
    }

}
