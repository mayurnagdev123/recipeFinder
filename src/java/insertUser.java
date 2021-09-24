/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mnagdev
 */
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
public class insertUser {
  Configuration cfg;
 SessionFactory sf;
 Session s;
 Transaction t;
    public insertUser(){
        try{
sf=HIbernateUtil.getSf();
    s=sf.openSession();
    t=s.beginTransaction();
          }
        catch(Exception e){
        System.out.println("an error occured with initialization!.");
        }

        
    }
  
    public static void main(String args[]){
    
    insertUser iUser=new insertUser();
 iUser.saveUser("9424595666", "manish", "9424595666", "mn@ot.com", "123456");
    
    }
    

    public String saveUser(String aadhar, String name, String mobile, String email, String password){
    User x=new User();
    x.setAadhar(aadhar);
    x.setName(name);
    x.setMobile(mobile);
    x.setEmail(email);
    x.setPassword(password);
    try{
    s.save(x);
    t.commit();
    return "Saved";
    }
    catch(Exception e){
        t.rollback();
        e.printStackTrace();
      return e.getMessage();
    }
    finally{
    s.close();
    }
    
    
    
    }

    public boolean checkExistingUser(String aadhar){
  User obj=  s.get(User.class, aadhar);
  //here the first parameter should be the class name and second one should have the id that has to be searched in the table
  	 //Return the persistent instance of the given entity class with the given identifier,
	// or null if there is no such persistent instance.
    if(obj==null)//no matching aadhar found
        return false;
    else 
        return true;
    /*
TypedQuery tq=s.createQuery("from User where aadhar=:a");
tq.setParameter("a", aadhar);
List<String> results=tq.getResultList();
if(results.size()>0){
    System.out.println("user already exists!. Sign up using a different aadhar no. or login to continue.");    
    return false;
    }
else{
    return true;
}*/
    }
    
    
}//class    
    

