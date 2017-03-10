/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky;

import javax.persistence.Persistence;

/**
 *
 * @author Ayrat
 */
public class Main {
    
    public static void main(String[] args) {
            
        MessageJpaController controller= new MessageJpaController(
                Persistence.createEntityManagerFactory("JPA_Example_2PU"));
        try{
            Message m=new Message();
            m.setMessage("jpa being added record");
        controller.create(m);
        }catch(Exception e){
            System.out.println(e.getClass().toString()+" : "+e.getMessage());
        }
    }
}
