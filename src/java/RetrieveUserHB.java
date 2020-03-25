/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mnagdev
 */
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import java.util.List;

public class RetrieveUserHB {
  
    SessionFactory sf;
    Session s;
    TypedQuery tq;
    Query q;
    public RetrieveUserHB(){

    sf=HIbernateUtil.getSf();
    s=sf.openSession();
        }
    
    public String retrieveDetails(String uaadhar,String pwd){
        try{
    q=s.createQuery("from User where aadhar=:a and password=:p");
    q.setParameter("a", uaadhar);
    q.setParameter("p", pwd);
    List<User> results=q.getResultList();
    System.out.println("printing list:");
    
    if(results.size()==0)
        return "Invalid";
    else if(results.size()==1){
    User user=results.get(0);
    String res="Success "+user.getAadhar()+" "+user.getName();
    System.out.println(res.substring(7));
        
        return res;
    
    }    
    }//try
        catch(Exception e){    
            e.printStackTrace();
            return "Error"+e.getMessage();
            }
        finally{
        s.close();
        }
        System.out.println("returning null!");
        return null;
    }//func
    
    public static void main(String args[]){
    RetrieveUserHB retrieveUserHB=new RetrieveUserHB();
    String ans=retrieveUserHB.retrieveDetails("1234567890", "mayur123");
    System.out.println(ans+"------------------------------");
    
    }
}//class
