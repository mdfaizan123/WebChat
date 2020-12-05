
var request; 
function sendInfo(urlLink)  
{   
var urlp=urlLink;  
  
if(window.XMLHttpRequest){  
request=new XMLHttpRequest();  
}  
else if(window.ActiveXObject){  
request=new ActiveXObject("Microsoft.XMLHTTP");  
}  
  
try  
{  
request.onreadystatechange=getInfo;  
request.open("GET",urlp,true);  
request.send();  
}  
catch(e)  
{  
alert("Unable to connect to server");  
}  
}

function getInfo(){ 
    var flag=true;
    if(request.readyState==4 && flag==true){  
        var val=request.responseText;
        var list=document.getElementById('list');  
        list.innerHTML=val;
        console.log(val);
        flag=false;
    }  
}

function findFriend(){
    var email=document.getElementById('email').value;
    var url="FindFriend"+"?email="+email;
    sendInfo(url)
}