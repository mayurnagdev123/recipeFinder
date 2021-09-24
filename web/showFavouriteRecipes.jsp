<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@page import="java.util.List" %>
    <%@page import="java.util.ArrayList"%>
    <%@page import="java.util.Map"%>
    <%@page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="icon" href="recipe-vector-10.png"> 
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="styles.css">

<script type="text/javascript" src="jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="bootstrap.min.js"></script>
<script type="text/javascript" src="function1.js"></script>

         </head>
    <body style="background-color:#FF9A33;font-family:Comic Sans MS">
        <a href='index.jsp'><button class='btn btn-primary' style='float:right;margin:30px'>HOME</button></a>
           <script type='text/javascript'>
               function removeFromMyFavourites(recipeId)
               {
                   
                   console.log(recipeId);
                   var aadhar='<%=session.getAttribute("uname")%>';
                   console.log("aadhar="+aadhar);
                           var response=removeFromFavourites(aadhar,recipeId);
        console.log("response obtained from removal of recipe is:"+response);
        if(response.trim().localeCompare("Success")==0)
        {
            alert("recipe removed from favourites!");
                            setTimeout(function(){
                    
                    window.location.reload();
                    
                });
        }
        else if(response.trim().localeCompare("Failure"))
                    alert("recipe could not be removed!");            
        else if(response.trim().localeCompare("Error"))
            alert("an error occured!.");
        else
            alert("something's wrong!");
               }//func
            
            function appendToDiv(id,url,name)
            {
                console.log("id="+id);
                console.log("url="+url);
                console.log("name="+name);
   var mainRow=$('<center><table></table></center>');
        mainRow.append("<tr>");
        mainRow.append("<th class='text-center'><h5 class='text-center' style='font-weight:500'>"+name+"</h5></th>");
        mainRow.append("<br>");
        mainRow.append("<th class='text-center'><h5 class='text-center' style='font-weight:300'><img src='"+url+"' alt='img not available'></h5></th>");
        mainRow.append("<br>");
        mainRow.append("<th class='text-center'><h5 class='text-center' style='font-weight:300'><a href='"+id+"' target='_blank'>"+id+"</h5></a></th>");
        mainRow.append("<br>");
        mainRow.append("<th class='text-center'><h5 class='text-center' style='font-weight:300'><button id='"+id+"' onclick='removeFromMyFavourites(this.id)'> Remove</button></h5></th>");
        mainRow.append("</tr>");
        mainRow.append("<br><br><br>");
                $("#myFavourites").append(mainRow);
                console.log("nagdev here!");
                
            }
            
        </script>    
  <h3 class="text-center"  >Hi <%=session.getAttribute("username")%> </h3>
  <h3 class='text-center' id="favouritesText"></h3>
  <div id="myFavourites" style='background-color:rgb(245,245,245);text-align:center;margin-top:50px;margin-bottom:60px'></div>          
    </body>
</html>

        <%
//            
//            List<String>favouriteRecipes=new ArrayList<String>();
//            favouriteRecipes=(List)session.getAttribute("favouriteRecipes");

            Map<String,String[]>favouriteRecipes=new HashMap<String,String[]>();
            favouriteRecipes=(Map)session.getAttribute("favouriteRecipes");

            if(favouriteRecipes!=null || !favouriteRecipes.isEmpty())
          {

for(Map.Entry<String,String[]> recipe:favouriteRecipes.entrySet())
{
   // out.println(recipe.getKey()+"\t"+recipe.getValue()[0]+"\n\n"+recipe.getValue()[1]);
    
    %>
          <script type="text/javascript">
          document.getElementById("favouritesText").innerHTML="here are your favourites...";
    var recipe_id='<%=recipe.getKey() %>';
    var recipe_url='<%=recipe.getValue()[0] %>';
    var recipe_name='<%=recipe.getValue()[1] %>';
     appendToDiv(recipe_id,recipe_url,recipe_name);
      
  </script>
   <%
}
  }     
            if(favouriteRecipes.size()==0){
                %>
                <script type="text/javascript">
                    console.log("no favourites:(");
document.getElementById("favouritesText").innerHTML="seems like you do not have any favourites :(";
                    
                </script>           
                
                <%
            }//if
            
   
   
   %>

