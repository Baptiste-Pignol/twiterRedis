package fr.epsi.tp.ws.bean;

import java.rmi.server.UID;

/**
 * Created by Baptiste on 17/03/2015.
 */


/**
 * bean witch represent a user
 */
public class User {
    private String pseudo, name, surname, password, uid;

    public User() {
        UID uid = new UID();
        this.uid = uid.toString();
    }

    public User(String pseudo, String name, String surname, String password, String uid) {
        this.pseudo = pseudo;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.uid = uid;
    }

    public User(String pseudo, String name, String surname, String password) {
        this.pseudo = pseudo;
        this.name = name;
        this.surname = surname;
        this.password = password;

        UID uid = new UID();
        this.uid = uid.toString();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPseudo() {

        return pseudo;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUid() {
        return uid;
    }
}
