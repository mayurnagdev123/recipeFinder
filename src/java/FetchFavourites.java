
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mnagdev
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;
public class FetchFavourites {
    
    
    List<Recipe> favourites;
SessionFactory sf;
Session s;
Query q;
    public FetchFavourites() {
    favourites=new ArrayList<Recipe>();
    sf=HIbernateUtil.getSf();
    s=sf.openSession();
    
    }
    public static void main(String args[])
    {
    FetchFavourites favourites=new FetchFavourites();
        List<Recipe> fetchFavouriteRecipes = favourites.fetchFavouriteRecipes("9407293930");
    
        for(Recipe r:fetchFavouriteRecipes)
        {
 System.out.println(r.getRecipe_URL());
 System.out.println(r.getRecipe_id());
 System.out.println(r.getRecipe_name());
 System.out.println("---");
        }
     
    }
    public List<Recipe> fetchFavouriteRecipes(String aadhar)
    {
        try{
 q=s.createQuery("from Recipe where user.aadhar=:u");
 q.setParameter("u", aadhar);
favourites=q.getResultList();
        }
        catch(Exception e)
        {
        e.printStackTrace();
        }
        finally{
        s.close();
        }
return favourites;

    }
}//class
