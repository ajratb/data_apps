/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Persistence;

/**
 *
 * @author Ayrat
 */
public class Main {
    
    public static void main(String[] args) {
       Map<String, String> properties = new HashMap<String, String>();
//            properties.put("javax.persistence.jdbc.user", login);
            properties.put("javax.persistence.jdbc.password", "star");
        ChatMessageJpaController controller= new ChatMessageJpaController(
                Persistence.createEntityManagerFactory("PUnit",properties));
        controller.create(new ChatMessage("jpa being added record"));
    }
}
