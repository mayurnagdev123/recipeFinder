/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
//@WebServlet("/AddRecipeToCart")
public class AddRecipeToCart extends HttpServlet {
    
//    public static void main(String args[]){
//AddRecipe addRecipe =new AddRecipe();
//String res=addRecipe.insertRecipe("www.chickenchilli.com","chicken chilli","www.google.co.in","1234567890");
//System.out.println("res="+res);
//    }    
    
    
    

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
        PrintWriter out=response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
  String uaadhar=request.getParameter("aadhar");
  String recipeName=request.getParameter("recipe_title");
String recipeURL=request.getParameter("recipe_url");
String recipeSRC=request.getParameter("recipe_SRC");
//out.println("uaadhar="+uaadhar+"\n"+"recipeName="+recipeName+"\nrecipeSRC="+recipeSRC+"\nrecipeURL="+recipeURL);

//System.out.println("data obtained is:"+uaadhar+recipeName+recipeURL+recipeSRC);
AddRecipe addRecipe =new AddRecipe();
String res=addRecipe.insertRecipe(recipeURL, recipeName,recipeSRC,uaadhar);
if(res.contains("Error"))
    out.println("error"+res);
else if(res.contains("Success"))
{
HttpSession session=request.getSession();
Map<String,String[]> recipeMap=(Map)session.getAttribute("favouriteRecipes");
String arr[]=new String[2];
arr[0]=recipeSRC;
        arr[1]=recipeName;
recipeMap.put(recipeURL,arr);
session.setAttribute("favouriteRecipes", recipeMap);    
    out.println("Success"+res);

} else
    out.println("a problem occured"+res);
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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}//class
