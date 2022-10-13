/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curso.baptisterio.java;

import static com.curso.baptisterio.java.Constants.*;
import java.util.Scanner;

/**
 *
 * @author dpadilla
 */
public class UserInterface {

    private Scanner keyboard;

    public UserInterface() {
        this.keyboard = new Scanner(System.in);
    }

    public int show() {
        //variable creation
        boolean choosed = false;
        int option = DEFAULTOPTION;
        while (!choosed) {
            System.out.println("Choose an option: ");
            try {
                option = Integer.parseInt(keyboard.nextLine());
                choosed = true;

            } catch (NumberFormatException e) {
                choosed = true;
                System.out.println("Option format not valid");

            }
        }

        return option;
    }

    public User askName() {
        //variable creation
        String line = "|";
        while (line.contains(SEPARE)) {
            System.out.println("Insert a valid name: ");
            line = keyboard.nextLine();

        }

        return new User(line,"","");

    }
    
    public String askDNI() {
        //variable creation
        String line = "|";
        while (line.contains(SEPARE)) {
            System.out.println("Insert a valid dni: ");
            line = keyboard.nextLine();

        }

        return line;

    }

    public String askPasword() {
        //variable creation
        String line = "|";
        while (line.contains(SEPARE)) {
            System.out.println("Insert your pasword: ");
            line = keyboard.nextLine();

        }

        return line;
    }

}
