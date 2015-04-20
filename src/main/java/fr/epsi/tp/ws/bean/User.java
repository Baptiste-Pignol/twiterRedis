package fr.epsi.tp.ws.bean;

import java.rmi.server.UID;

/**
 * Created by Baptiste on 17/03/2015.
 */


/**
 * bean witch represent a user
 */
public class User {
    private String pseudo, fullName, email, password, uid;

    public User() {
        UID uid = new UID();
        this.uid = uid.toString();
    }

    public User(String pseudo, String fullName, String email, String password, String uid) {
        this.pseudo = pseudo;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public User(String pseudo, String fullName, String email, String password) {
        this.pseudo = pseudo;
        this.fullName = fullName;
        this.email = email;
        this.password = password;

        UID uid = new UID();
        this.uid = uid.toString();
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
