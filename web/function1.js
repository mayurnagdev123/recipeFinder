/*var script=document.createElement("script");
script.src="jquery-3.4.1.min.js";
script.type="text/javascript";
document.getElementsByTagName("head")[0].appendChild(script);
*/
function killSession(){
    $("#logoutForm").submit(function(e){
        
        e.preventDefault();
        e.stopImmediatePropagation();
        var form=$("#logoutForm");
        var method=form.attr("method");
        var action=form.attr("action");
        $.ajax({
            cache:true,
            async:true,
            url:action,
            type:method,
            success:function(message){
                if(message.trim().localeCompare("loggedOut")==0)
                    console.log("logged out successfully!");
                setTimeout(function(){
                    
                    window.location.reload();
                    
                });
            },
            error:function(xhr){
                console.log(xhr+"logout failed!");
            }
            
        });
    });//logoutFormSubmit
    
    
}//killSession

function loginUser(){
    $("#loginForm").submit(function(e){
        e.preventDefault();
        $("#loginButton").prop("disabled",true);
        $("#loginButton").attr("disabled","disabled");
       e.stopImmediatePropagation();
        var form=$("#loginForm");
        var method=form.attr('method');
        var url=form.attr('action');
        $.ajax({
            cache:true,
            async:true,
            type:method,
            url:"/recipeFinder/VerifyUser",
            data:form.serialize(),
            success:function(message){
                if(message.trim().includes("Success")==true)
                {
                    console.log("login success!");
                //    alert("login success");
                    
                    $("#modalCancel").click();
       
   
setTimeout(function(){
    window.location.reload();
},100); 
                 $("#logoutButton").show();
            $("#loginSignupButton").hide();
              
                }
                else if(message.trim().localeCompare("Invalid")==0)
                    alert("invalid username/password entered.please try again!");
                else if(message.trim().localeCompare("Error"))
                    alert("an error occured while processing your request."+message);

        $("#loginButton").prop("disabled",false);        
            },
            error:function(error_message){
                alert("an error occured!")
        $("#loginButton").prop("disabled",false);        
            }
            
            
            
        });
        
        
        
    });//submit
    
    
    
}//loginUser
var ans="";
function checkLogin(){
    
    $.ajax({
        async:false,
        cache:true,
        url:"/recipeFinder/checkLoggedIn",
        type:"GET",
        success:function(reply){ 
            ans=reply;
            console.log("the reply is:"+ans);
        },//success
        error:function(failure){
            ans="error";
            console.log("call to servlet failed");
        }
                
    });
    console.log("the value of ans is:"+ans);
    return ans;
}//checkLogin
function add(ID){
    console.log("i have been called!!!");
var y=ID.substr(3);
console.log(ID+"\t"+y);
var titleID="title"+y;
var imgID="img"+y;
var urlID="url"+y;
var buttonID="Fav"+y;
console.log(titleID+"\t"+imgID+"\t"+urlID+"\t"+buttonID);
var imgSRC=document.getElementById(imgID).getAttribute("src");
var itemTitle=document.getElementById(titleID).innerText;
var itemURL=document.getElementById(urlID).innerText;
var buttonText=document.getElementById(buttonID).innerText;
console.log(itemTitle+"\n"+imgSRC+"\n"+itemURL+"\n"+buttonText);

var userName=checkLogin();
if(userName.trim().localeCompare("EMPTY_SESSION")==0)//no user is logged in
{
        //show modal with login and signup buttons
 document.getElementById("showModal").click();
}
else if(userName.trim().localeCompare("error")==0)
alert("an error occured.please retry later!");
else//add/remove from cart
{
    if(buttonText.localeCompare("Favourite")==0)//item has to be added to cart
    {
        var response=addToCart(userName,itemURL,itemTitle,imgSRC);
        
        console.log("response="+response);
        if(response.trim().localeCompare("Success")==0)//insertion into cart success
        {
            console.log("added to favourites!");
            alert("added to favourites!");
  document.getElementById("Fav"+y).innerHTML="Remove";

  document.getElementById("Fav"+y).style.background="#dc3545";
            
        }
    }
    else//item has to be removed from cart
    {
        var response=removeFromFavourites(userName,itemURL);
        console.log("response obtained from removal of recipe is:"+response);
        if(response.trim().localeCompare("Success")==0)
        {
            alert("recipe removed from favourites!");
            document.getElementById("Fav"+y).innerHTML="Favourite";
          document.getElementById("Fav"+y).style.background="#28a745";
            
        }
        else if(response.trim().localeCompare("Failure"))
                    alert("recipe could not be removed!");            
        else if(response.trim().localeCompare("Error"))
            alert("an error occured!.");
        else
            alert("something's wrong!");
        
        
    }//removalFromFavourites
    
    
}//else
    
}//add

/////////////////////////////
var response="";
function addToCart(aadhar,itemURL,itemTitle,imgSRC)
{
    
    $.ajax({
        cache:true,
        async:false,
        method:"GET",
        url:"AddRecipeToCart?aadhar="+aadhar+"&recipe_url="+itemURL+"&recipe_title="+itemTitle+"&recipe_SRC="+imgSRC,
        success:function(data){
            console.log("data="+data);
            if(data.trim().includes("Success"))
                response="Success";
            else if(data.trim().includes("error"))
                response="Error";
       },
        error:function(xhr){
response="error";        
        }
       
    });
   
    return response;
}//func
///////////////////////
var answer="";
function removeFromFavourites(aadhar,itemURL){
     console.log("for removal:,aadhar="+aadhar+"and itemURL is:"+itemURL);
    $.ajax({
        async:false,
        cache:true,
        method:"GET",
       url:"RemoveFromFavourites?aadhar="+aadhar+"&recipe_url="+itemURL,
        success:function(data)
        {
            
                     console.log(data);
            if(data.trim().includes("Success"))
            answer="Success";
            else if(data.trim().includes("Failure"))
            answer="Failure";    
        },
        error:function(xhr)
        {
            console.log("error="+xhr);
            answer="Error";
        }
        
        
        
    });
    
    
    return answer;
    
}//removeFromFav