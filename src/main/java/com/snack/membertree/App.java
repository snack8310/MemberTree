package com.snack.membertree;

import com.snack.membertree.controller.NodeController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 */
@ComponentScan("com.snack.membertree")
@Configuration
@EnableAspectJAutoProxy
public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        NodeController nc = context.getBean(NodeController.class);
        nc.createNewTree();
    }

}
