

    <%@page import="java.util.Map"%>

<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="icon" href="recipe-vector-10.png"> 
<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="styles.css">

<script type="text/javascript" src="jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="bootstrap.min.js"></script>
<script type="text/javascript" src="function.js"></script>

</head>
<body style='background-color:rgb(245,245,245);font-family:Comic Sans MS'>
<script type="text/javascript" src="function1.js"></script>

<div class="container">
<input type="text" id="inp" placeholder="search by ingredient,dish or recipe" size="50" style="margin-top:20px" required>
<button id="fetch" class="btn btn-success" >GO</button>
<p  id="userf" style="display:none">p here!</p>
<!--

    String uname=(String)session.getAttribute("uname");
    if(uname == null)
    {
               uname="guest";
        }
    %>
    -->
    
<p style="float:right;" id="welcomeLabel"></p>
<button class="btn btn-primary" style="float:right;margin-top:20px" id="loginSignupButton">Login/Signup</button>

<a href='showFavouriteRecipes.jsp'><button class="btn btn-info" id="favouritesButton">My Favourite Recipes</button></a>
<form action="KillSession" id="logoutForm">
    <button class="btn btn-primary" style="float:right;" id="logoutButton" onclick="killSession()">Logout</button>
  
</form>
<div id="contentDiv" style="margin-top:50px"></div><!--data will be added here-->
       <!-- Trigger the modal with a button -->
<button id="showModal" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" style="visibility:hidden">Open Modal</button>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h5 class="modal-title">Login to Continue</h5>
      </div>
      <div class="modal-body">
          <form action="VerifyUser" method="POST" id="loginForm">
              
              <input type="text" placeholder="Aadhar(10 digits)" size="43" name="aadhar"  id="enteredUsername" required>
              <input type="password" placeholder="Password" size="43" name="password" required id="enteredPassword">
              <button class="btn btn-info btn-block" id="loginButton" onclick="loginUser()">LOGIN</button>
              
          </form>
          <p>Don't have an account yet?<a href='addUser.jsp'>Sign Up</a></p>
      </div>
      <div class="modal-footer">
        <button type="button" id="modalCancel" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
    
</div>

</body>

<script type='text/javascript'>
    $(document).ready(function(){

$("#loginSignupButton").click(function(){
    console.log("login/signup button clicked!");
    $("#showModal").click();
});


var uname='<%= session.getAttribute("username")%>';
console.log(uname);
//delete


//delete
if(uname.trim().localeCompare("null") ==0){
    document.getElementById("userf").innerText="";
    console.log("inside if");
    $("#loginSignupButton").show();
    $("#logoutButton").hide();    
    $("#favouritesButton").hide();
}

else{
    let recipes=new Map();
    console.log("recipes is:"+typeof(recipes));
    recipes='<%=(Map)session.getAttribute("favouriteRecipes")%>';
console.log("recipes are:"+recipes);
document.getElementById("userf").innerText=recipes;
//var arr=recipes.split(/[=,]+/);

    $("#welcomeLabel").text("Welcome "+uname);
    
    $("#loginSignupButton").hide();
    $("#logoutButton").show();    
    $("#favouritesButton").show();
    
    var recipeMap='<%=(Map) session.getAttribute("favouriteRecipes")%>';
}
});
</script>
</html>