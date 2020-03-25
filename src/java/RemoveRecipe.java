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
import org.hibernate.Transaction;
import javax.persistence.TypedQuery;
public class RemoveRecipe {

    SessionFactory sf;
    Session s;
    Transaction t;
    TypedQuery tq;
    
    
    public RemoveRecipe(){
sf=HIbernateUtil.getSf();
    s=sf.openSession();
    t=s.beginTransaction();
        }

    public String removeRecipe(String uaadhar, String recipe_url){
    User u=new User();
    Recipe r=new Recipe();
    u.setAadhar(uaadhar);
    r.setRecipe_id(recipe_url);
    tq=s.createQuery("delete from Recipe where user.aadhar=:u and recipe_id=:r");
//in our Recipe class, we have Private User user; therefore we use this case sensitive "user" property to get aadhar in @Entity User
    tq.setParameter("u", uaadhar);
    tq.setParameter("r", recipe_url);
    try{
    int n=tq.executeUpdate();
      if(n<=0)
      {
          t.rollback();
    return "Failed";
    
      }else
      {
          t.commit();
        return "Success";
     }
    }
    catch(Exception e)
    {
        t.rollback();
    return e.getMessage();
    }
    finally{
    s.close();
    }
   
    }//func
    
    public static void main(String args[]){
    
    RemoveRecipe rr=new RemoveRecipe();

    RemoveRecipe r1=new RemoveRecipe();
    
    r1.removeRecipe("mayur", "www.ot.com");
    
    
    }//main
    
}//class
