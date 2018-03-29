/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.postgres.jdbc.spring;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class App {
    
    public App() {
        
    }
    
    @Autowired
    JdbcRepository dataRep;
    
    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext ctx = 
                new AnnotationConfigApplicationContext();
        ctx.register(JdbcConfig.class);
        ctx.scan("ru.waytosky.postgres.jdbc.spring");
        ctx.refresh();

        App app = (App) ctx.getBean("app");
       
        app.getBookFromBase();
        ctx.close();
    }
    
    private void getBookFromBase(){
        List<Book> books = dataRep.getAllBooks();
        for(Book b: books){
            System.out.println(b);
        }
    }
}
