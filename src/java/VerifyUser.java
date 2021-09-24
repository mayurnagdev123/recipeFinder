/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mnagdev
 */
//@WebServlet("/VerifyUser")
public class VerifyUser extends HttpServlet {

    
    
    public static void main(String args[])
    {
        
          List<String> userRecipes=new ArrayList<String>();
         String uaadhar="1234567890";
     String pwd="mayur123";
     RetrieveUserHB retrieveUserHB=new RetrieveUserHB();
 String answer=retrieveUserHB.retrieveDetails(uaadhar,pwd);
     if(answer.contains("Success")){//login is successful
         FetchFavourites favourites=new FetchFavourites();
        List<Recipe>favouriteRecipes=favourites.fetchFavouriteRecipes(answer.substring(7));

        for(Recipe r:favouriteRecipes)
        {
        userRecipes.add(r.getRecipe_URL());
        userRecipes.add(r.getRecipe_id());
        userRecipes.add(r.getRecipe_name());
        userRecipes.add("---");
        }
     }
     System.out.println(userRecipes.toArray().toString());
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        String s=(String)session.getAttribute("uname");
        List<String> userRecipes=new ArrayList<String>();
     if(s!=null)
         out.println("It seems you're already logged in!");
     else{
     String uaadhar=request.getParameter("aadhar");
     String pwd=request.getParameter("password");
     RetrieveUserHB retrieveUserHB=new RetrieveUserHB();
     String answer=retrieveUserHB.retrieveDetails(uaadhar,pwd);
     if(answer.contains("Success")){//login is successful
         FetchFavourites favourites=new FetchFavourites();
              String responses[]=answer.split(" ");
        List<Recipe>favouriteRecipes=favourites.fetchFavouriteRecipes(responses[1]);
        Map<String,String[]>recipeMap=new HashMap<String,String[]>();
        for(Recipe r:favouriteRecipes)
      {
//        userRecipes.add(r.getRecipe_URL());
//        userRecipes.add(r.getRecipe_id());
//        userRecipes.add(r.getRecipe_name());
//        userRecipes.add("---");
String arr[]=new String[2];
arr[0]=r.getRecipe_URL();
arr[1]=r.getRecipe_name();
recipeMap.put(r.getRecipe_id(),arr);
      }
        session.setAttribute("favouriteRecipes", recipeMap);
//     session.setAttribute("uname", answer.substring(7));
//     session.setAttribute("username", out);

     session.setAttribute("uname", responses[1]);
     session.setAttribute("username", responses[2]);
     }

     
     out.println(answer);
     
     
     
     }//else
    }//doPost

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
