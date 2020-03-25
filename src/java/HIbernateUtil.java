/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mnagdev
 */

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Configuration;
public class HIbernateUtil {
    
    private static SessionFactory sf;

    public static SessionFactory getSf() {
        try{
        if(sf==null)
        {
//        Configuration cfg=new Configuration().addResource("hibernate.cfg.xml").configure();
//        cfg.addClass(Recipe.class);
//            ServiceRegistry sr=new StandardServiceRegistryBuilder().
//                    applySettings(cfg.getProperties()).build();
//            sf=cfg.buildSessionFactory(sr);
            Configuration cfg = new Configuration();
            cfg.configure("hibernate.cfg.xml");
            sf=cfg.buildSessionFactory();
        
        }
               
        }
    catch(Exception e)
    {
        e.printStackTrace();
    System.out.println("an error occured with initialization of session factory");
    }
     return sf;
    }//getSF

    public static void setSf(SessionFactory sf) {
        HIbernateUtil.sf = sf;
    }
    public static void closeSF(){
    if(sf!=null)
        sf.close();
    }
    
    
    
}//class
