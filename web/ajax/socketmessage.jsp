<%@page import="java.util.HashMap"%> 
<%
    HashMap hm=(HashMap)session.getAttribute("userDetails");
     if(hm!=null){
         HashMap SocketMessage=(HashMap)session.getAttribute("SocketMessage");
         if(SocketMessage!=null){
%>
                           <%
                           if(SocketMessage.get("id").equals(hm.get("id")))
                                 { %>
                            <div class="chat-r">
					<div class="sp"></div>
					<div class="mess mess-r">
                                                    <%=SocketMessage.get("text")%>
						</p>
						<div class="check">
							<span><%=SocketMessage.get("date")%></span>
						</div>
					</div>
				</div>
                                
                                    <% } else{ %>
                                
				<div class="chat-l">
					<div class="mess">
						<p>
                                                <%=SocketMessage.get("text")%>
						</p>
						<div class="check">
							<span><span><%=SocketMessage.get("date")%></span>
						</div>
					</div>
					<div class="sp"></div>
				</div>
                                 <% } %>
    <%
       
    }
    }
else{
}
%>