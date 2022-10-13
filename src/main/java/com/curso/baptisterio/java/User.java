/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curso.baptisterio.java;

import static com.curso.baptisterio.java.Constants.*;

/**
 *
 * @author dpadilla
 */
public class User {
    private String userName;
    private String pass;
    private String dni;

    public User(String userName, String pass, String dni) {
        this.userName = userName;
        this.pass = pass;
        this.dni=dni;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    
    
    @Override
    public String toString() {
        return this.getUserName() + SEPARE + this.getPass() + SEPARE + this.getDni() + "\n";
    }
    
    
    
    
    
    
    
}
