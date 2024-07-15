package com.amex.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProductServiceApplication {


    public static void main(String[] args) {

        ApplicationContext ctx=SpringApplication.run(ProductServiceApplication.class, args);
       String arr[] = ctx.getBeanDefinitionNames();
int num = 0;
       for(String beanName : arr){
           System.out.println("The current bean name is  "+beanName);
           System.out.println("The current bean count is  "+num++);
       }
    }

}
