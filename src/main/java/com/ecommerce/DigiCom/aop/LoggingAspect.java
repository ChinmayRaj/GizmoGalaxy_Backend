package com.ecommerce.DigiCom.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger=  LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.ecommerce.DigiCom.controller.ProductController.getProducts(..))")
    public void logMethod(JoinPoint jp) {
        String name=jp.getSignature().getName();
        logger.info("Before log. Name is :"+name);
    }

    @After("execution(* com.ecommerce.DigiCom.controller.ProductController.getProductById(..)) && args(id)")
    public void logMethodForProductById(JoinPoint jp,int id) {
        String name = jp.getSignature().getName();
        if (id > 0) {
            logger.info("Product fetched for method " + name + " and product id is :" + id);

        } else {
            logger.error("Product not fetched for method " + name + " and product id is negative");
        }
    }


        @Around("execution(* com.ecommerce.DigiCom.controller.ProductController.getProducts(..))")
        public Object monitorTime(ProceedingJoinPoint jp) throws Throwable {

            long start=System.currentTimeMillis();

            Object obj= jp.proceed();
            long end=System.currentTimeMillis();

            logger.info("Time taken by: "+jp.getSignature().getName()+" "+(end-start)+" ms");
            return obj;
        }
        @After("execution (* com.ecommerce.DigiCom.controller.ProductController.getProductsByName(..) )&& args(searchwords)")
    public void printSearchedKeyword(JoinPoint jp, String searchwords) throws Throwable {
        String name=jp.getSignature().getName();

        System.out.println("User searched the keyword :"+searchwords+" from the method :"+name);
        }










}
