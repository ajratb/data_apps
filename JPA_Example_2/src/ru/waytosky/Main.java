/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky;

import java.util.Date;
import javax.persistence.Persistence;

/**
 *
 * @author Ayrat
 */
public class Main {

    public static void main(String[] args) {

        MessageJpaController controller = new MessageJpaController(
                Persistence.createEntityManagerFactory("PUnit"));
        try {
//            Message m=new Message();
//            m.setMessage("jpa being added record");
//        controller.create(m);
            controller.create(new Message("jpa being added record"+(new Date())));
        } catch (Exception e) {
            System.out.println(e.getClass().toString() + " : " + e.getMessage());
        }
    }
}
