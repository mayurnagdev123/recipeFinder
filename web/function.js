
$(document).ready(function(){
    var arr;
    var recipes;
		var extractedLink="";
		var imageURL="";
console.log("jquery is working!!!");
console.log("hello!");
window.onload=function abc(){
recipes=document.getElementById("userf").innerText;
console.log("mayur recipes="+recipes);
arr=recipes.split(/[=,]+/);
}
//console.log('2='+$("#storeUserFavourites").text());
//console.log("3="+document.getElementById('storeUserFavourites').innerHTML);
//document.getElementById("showUserFavourites").innerText="mayur------------";
//----------------------------------------------------------------------------------------------------------
function registerUser(){
    alert("the button has been clicked!");
    $.ajax({
      async:false,
      cache:true,
      url:"/recipeFinder/saveNewUser",
            type: "POST",
      success:function(data){
          
          alert("the data is:"+data);
          console.log(data);
          
      },
      error:function(data)
      {
alert("an error occured! please try again!");          
      }

    });

}//func
    
 
//----------------------------------------------------------------------------------------------------------
function fetchImageLink(searchTitle){
	//code to get image url
//var extractedLink="mayur";
	
	var title = searchTitle.replace(/ /g, '+');
	var link1="https://www.googleapis.com/customsearch/v1?key=AIzaSyDrj5r4QDDPpSVlM9qSpHJcZD97PcaU6XQ&cx=016853084978058317036:qreyl4b5xxt&q="+title+"&searchType=image&fileType=jpg&imgSize=medium";

	console.log(link1);
	$.ajax({
		async:false,
		cache:true,
		url:link1,
		type:"GET",
		dataType:"JSON",
		success:function(data){
			console.log("success");
			console.log("title is--------------"+data.items[0].title);
			console.log("img link="+data.items[0].image.thumbnailLink);
				extractedLink=data.items[0].image.thumbnailLink;
			//	extractedLink="nagdev";
extractedLink= data.items[0].image.thumbnailLink;

	console.log("extractedLink is:"+extractedLink);
		},
		error:function(failure){
extractedLink="null";
//extractedLink="nagdev";		
		console.log("an error occured while trying to fetch image!");
	extractedLink="null";
	
	console.log("extractedLink is:"+extractedLink);
		}
		
		
	});//ajax
	console.log("the value being returned is:"+extractedLink);
	return extractedLink;

}//fetchImageLink

//----------------------------------------------------------------------------------------------------------

$("#fetch").on("click",function(){
 
    var requestedItem=document.getElementById("inp").value;
    var searchItem = requestedItem.replace(/ /g, '+');
    searchItem+="+recipe";
    var link1="https://www.googleapis.com/customsearch/v1?key=AIzaSyDrj5r4QDDPpSVlM9qSpHJcZD97PcaU6XQ&cx=016853084978058317036:qreyl4b5xxt&q="+searchItem+"&searchType=image&fileType=jpg&imgSize=medium";

	console.log(link1);
        $("#contentDiv").html("");
        var mainRow=$("<table></table>");
        mainRow.append("<tr>");
        mainRow.append("<th><h5 class='text-center' style='font-weight:bold'>Snapshot</h5></th>");
        mainRow.append("<th><h5 class='text-center' style='font-weight:bold'>RecipeName</h5></th>");
        mainRow.append("<th><h5 class='text-center' style='font-weight:bold'>Find_it_here</h5></th>");
        mainRow.append("<th><h5 class='text-center' style='font-weight:bold'>Favourite</h5></th>");
        mainRow.append("</tr>");
        mainRow.append("<br>");
        
    $.ajax({
            async: true,
            cache:true,
            url:link1,
            type:"GET",
            dataType: "JSON",
        success:function(data){

for(var k=0;k<arr.length;k++)
    console.log(arr[k]);
            var items=data.items;
                for(var i=0;i<items.length;i++){
                var row=$("<tr></tr>"); 
            var recipeImg=items[i].link;
            var recipeTitle=items[i].title;
            var recipeURL=items[i].image.contextLink;
                 row.append("<td><img src='"+recipeImg+"' id='img"+i+"' height='300' width='300'></td>");
                 row.append("<td><h5 class='text-center' id='title"+i+"'>"+recipeTitle+"</h5></td>");
                 row.append("<td><a href='"+recipeURL+"' target='_blank'>"+"<h5 id='url"+i+"'>"+recipeURL+"</h5></a> </td>");
                 var n=recipes.includes(recipeURL.replace("%20"," "));
                 console.log("recipeURL="+recipeURL+"    does list have this?"+n);
                 if(!n)
                 row.append("<td><button onclick="+'"add(this.id)"'+" class='btn btn-success' id='Fav"+i+"'>Favourite</button></td>");
                else
                 row.append("<td><button onclick="+'"add(this.id)"'+" class='btn btn-danger' id='Fav"+i+"'>Remove</button></td>");
                row.append("<br><br>");
                 row.append("<br>");
                 mainRow.append(row);
                 mainRow.append("<br/>");
                }//for
                $("#contentDiv").append(mainRow);
            },
            error:function(message){
                var row=$("<tr></tr>");
                row.append("<td>image not found :(</td>");
                row.append("<td>Not Found:(</td>");
                row.append("<td>Not Found:(</td>");
                row.append("<td>Not Found:(</td>");
                console.log(message);
                mainRow.append(row);
                $("#contentDiv").append(mainRow);
            }
        
        
        
    });
    
    
    
    
});
/*just in case this starts up again*/
/*
$("#fetch1").on("click",function(){
	$("#contentDiv").html("");
var x=document.getElementById("inp").value;
console.log(x);
var url1="https://recipe-puppy.p.rapidapi.com?q="+x;
//alert("the url is:"+url1);
$.ajax({
async:true,
cache:true,
url:url1,
type:"GET",
dataType:"JSON",
headers:{
		"x-rapidapi-host": "recipe-puppy.p.rapidapi.com",
		"x-rapidapi-key": "7oFuxAQ2oDmsh1guHiXxHFlq3DPEp1Uf3aYjsn1QxDcuWC8LoS"
},
success:function(data){
$("#contentDiv").html("");
//console.log(data);
var row1=$("<table style='width:100%;background-color:silver;border:3px solid black;margin-bottom:20px'></table>");
row1.append("<tr>");
 row1.append("<th style='text-align:center;width:18%'>Snapshot</th>");
row1.append("<th style='text-align:center;width:57%;'>Recipe Details</th>");
row1.append("<th style='text-align:center;width:25%;'>Ingredients</th>");
row1.append("</tr>");
row1.append("</table>");
row1.append("<br/>");

//append table headings to the contentDiv
$("#contentDiv").append(row1);
var results=data.results;
//var altImage="C:\Users\mnagdev\Desktop\New folder\image-not-found.jpg";
for(var i=0;i<results.length;i++){
	try{


//console.log(results[i].title);
var row=$("<tr style='border:1px solid black'></tr>");

		if(results[i].thumbnail.length==""){//thumbnail not present in the current api
				var imgLink=fetchImageLink(results[i].title);//search google api
	console.log("img link obtained is:"+imgLink);
	if(imgLink!="null")//image is obtained from google api
	row.append("<td><img src="+"'"+imgLink+"'"+" id='img"+i+"' style='border-radius:20px'></td>");
	else//image could not be obtained from google api
	continue;//skip the current item
//	row.append("<td><img src='C:\\Users\\mnagdev\\Desktop\\New folder\\image-not-found.jpg' style='height:150px;width:200px'></td>");
		}
else{//thumbnail from the current api can directly be used

	row.append("<td><img src="+"'"+results[i].thumbnail+"'"+" style='height:150px;width:200px;border-radius:20px' id='img"+i+"' ></td>");	
}
	

		

row.append("<td><h6 id='details"+i+"'>"+results[i].title+"<br/><a href='"+results[i].href+"' target='_blank'>"+results[i].href+"</a><br><button onclick="+'"add(this.id)"'+" class='btn btn-primary' id='btn"+i+"'  >Favourite</button></h6></td>");
var ingredientList=results[i].ingredients.split(",");
var str="";
for(var j=0;j<ingredientList.length;j++){
	str+="@"+ingredientList[j]+"<br/>";
}
row.append("<td><h6 id='ing"+i+"'>"+str+"</h6></td>");

row.append("<br/");
//row.append("<tr style='border:1px solid black'></tr>");
//row1.append(row);
$("#contentDiv").append(row);
	}//try
	catch(err){}
}//for
},
error:function(xhr){
console.log("an error occured!"+xhr.message);
}
});//ajax
});//click
*/

});//document.ready end
