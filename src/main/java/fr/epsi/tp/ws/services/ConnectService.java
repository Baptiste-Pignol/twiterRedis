package fr.epsi.tp.ws.services;

/**
 * Created by Baptiste on 17/03/2015.
 */
public interface ConnectService {
    public boolean checkLogin(String pseudo, String password);
    public String getUid(String pseudo);
}
