package fr.epsi.tp.ws.bean;

/**
 * Created by Baptiste on 18/03/2015.
 */

/**
 * bean witch contain connect information (password and pseudo)
 */
public class ConnectInfo {
    private String pseudo, password;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ConnectInfo() {}

    public ConnectInfo(String pseudo, String password) {

        this.pseudo = pseudo;
        this.password = password;
    }
}
