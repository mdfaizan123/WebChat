var id= document.getElementById('id').value;
var p_id= document.getElementById("p_id").value;
var messageTextArea=document.getElementById("b"); 

var wsUri= "ws://localhost:8084/incapp-project/socketServerEndpoint/"+sortId(id,p_id);
var websocket=new WebSocket (wsUri);

websocket.onopen = function(message) { onOpen(message); };
websocket.onerror = function(message) { onError(message); };
websocket.onclose = function(message) { onClose(message); };

var messageUrl;
websocket.onmessage = function(message){
    var jsonData = JSON.parse(message.data);
    console.log("json:"+jsonData.m_id+"type:"+jsonData.type)
        if(jsonData.m_id!=null){
            messageUrl="SocketMessage?m_id="+jsonData.m_id;
            console.log("onmessage"+messageUrl)
            sendInfo(messageUrl);
        }
        
      
//    console.log(jsonData.message)
};
function sendMessage(){
    var message= document.getElementById("messageText").value;
    var json = JSON.stringify({
        "message": message,
        "id": id,
        "p_id": p_id,
        "type": "message"
        
    });
    websocket.send(json);
    document.getElementById("messageText").value= ""
}
function onOpen(message) {
    console.log("Connected to " + wsUri);
}

function onClose(message){
   websocket.send("disconnected");
}
function onError(message){
    websocket.send("error");
}

function sortId(a ,b){
    var string = [a,b];  
	string.sort(); 
    string =string[0]+"&"+string[1];
	return string;
}



// ajax 
var type= document.getElementById("messageText");
var b=document.getElementById("scroll");
var s=document.getElementById("scroll");
var request;
var url="";
var scrolled = s.scrollHeight - s.clientHeight;


function sendInfo(urlPattern)  
{   
url=urlPattern;  
  
if(window.XMLHttpRequest){  
request=new XMLHttpRequest();  
}  
else if(window.ActiveXObject){  
request=new ActiveXObject("Microsoft.XMLHTTP");  
}  
  
try  
{  
request.onreadystatechange=getInfo;  
request.open("GET",url,true);
request.send();  
}  
catch(e)  
{  
alert("Unable to connect to server");  
}  
}  
   
function getInfo(){  
if(request.readyState==4){  
var val=request.responseText;

if(url === "m"){
   var set=document.getElementById('s');
   set.insertAdjacentHTML("afterend",val); 
   var scrolled1 = s.scrollHeight - s.clientHeight;
   b.scrollTo(0,scrolled1-scrolled);
}

else if(messageUrl === url){
//    console.log(messageUrl);
//    console.log(url);
//    console.log(val);
    messageTextArea.insertAdjacentHTML("beforebegin",val); 
    b.scrollTo(0,b.scrollHeight-b.clientHeight);
    url="";
   
} 
else {
    console.log("else");
}
}  
}  

s.addEventListener('scroll', function(){
        scrolled = s.scrollHeight - s.clientHeight;
        const p=s.scrollTop;
        if(Math.ceil(p) === scrolled){
            }
        if(p===0){
           sendInfo("m"); 
           }
});


b.scrollTo(0,b.scrollHeight-b.clientHeight);
   window.reonload = function(){
  document.location.href = "Message?id="+p_id+"name="+document.getElementById("p_name").value;
};

function scrol(){
    b.scrollTo(0,b.scrollHeight-b.clientHeight);
}