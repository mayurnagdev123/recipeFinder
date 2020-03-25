<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel='icon' href='recipe-vector-10.png'>

<link rel="stylesheet" href="bootstrap.min.css">
<link rel="stylesheet" href="styles.css">
<script type="text/javascript" src="jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="bootstrap.min.js"></script>
<script type="text/javascript" src="function.js"></script>
<script type='text/javascript'>
    $(document).ready(function(){
        
        var uname='<%=(String)session.getAttribute("uname") %>';
  console.log("uname is:"+uname);
  console.log(uname!="null");
  
        if(uname != "null")//if user is already logged in then he shouldn't see this page
        {
            window.setTimeout(function(){
                           window.location.href="index.jsp"; 
               
            });
        }
   });;//documentReady

  function registerMe(){
    $("#registrationForm").submit(function(e){
       // alert("here!");
       
        e.preventDefault();
        e.stopImmediatePropagation();
        var form=$("#registrationForm");
        var url=form.attr('action');
        
        var pwd=document.getElementById("password1").value;
        var cpwd=document.getElementById("confirmPassword1").value;
        var method=form.attr("method");

        var n=pwd.localeCompare(cpwd);
        
        if(n!=0)
            alert("passwords do not match!");
       else{   
        $.ajax({
            
            async:true,
            cache:true,
            type:form.attr("method"),
            url:url,
            data:form.serialize(),
                    success:function(data)
            {
                console.log("data obtained from server is:"+data);
                if(data.trim().localeCompare("Already registered")==0)
                    alert("This aadhar no. is already registered with us. Please enter a different aadhar or login to continue.");
                else if(data.trim().localeCompare("Saved")==0){
                 alert("Registration is Successful. You must Login to continue"+"\n"+"You will now be redirected to the home page.");
                
                window.setTimeout(function(){
                    window.location.href="index.jsp";
                },3000);
            }//elseif
            },
            error:function(xhr){
                
                alert("error"+xhr);
            }
            
        });//ajax
    }//else
        
    });//submitForm
    
    
    }//registerMe


</script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New User Registration</title>
    </head>
    <body style='background-color:rgb(245,245,245);font-family:Comic Sans MS'>
        
        <div class='container'>
            <h3 class='text-center'>Registration Form</h3>
       
            <form method="POST" action="saveNewUser" id="registrationForm" >
            <div class='form-group'>
                <label for='aadhar'>Aadhar No:(10 digit)</label>
                <input id="aadhar1" class='form-control' name='aadhar' type="number" min="1000000000"  max="9999999999" required >
            </div>
            <div class='form-group'>
                <label for='name'>Name:</label>
                <input id="name1" type='text' name='name' class='form-control'  pattern=".{1,15}" required>
            </div>
            
            <div class='form-group'>
                <label for='mobile'>Mobile No:</label>
                <input id="mobile1" type='number' name='mobile' class='form-control' min="1000000000" max="9999999999" required>
      
            </div>
            <div class='form-group'>
                <label for='email'>Email:</label>
                <input type='email' id="email1" name="email" class='form-control' required>
            </div>
                <div class='form-group'>
                    <label for='password'>Password:</label>
                    <input type='password' placeholder='6-15 characters' id='password1' name='password' class="form-control" required pattern=".{6,15}" title="password should have 6-15 characters">
                    
                </div>
                <div class='form-group'>
                    <label for='confirm'>Confirm password:</label>
                    <input type='password' id='confirmPassword1' name='confirmPassword' required class='form-control'>
                                    </div>
                <button  class="form-control btn btn-info btn-block" id="submitBtn" onclick="registerMe()">REGISTER!</button>
        </form>

 
        
        </div><!--container div-->
    </body>
</html>
