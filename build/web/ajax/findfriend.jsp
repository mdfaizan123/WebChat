<%@page import="java.util.HashMap"%> 
<%  
     HashMap hm=(HashMap)session.getAttribute("userDetails");
     HashMap pb=(HashMap)session.getAttribute("pbuserDetails");
     if(hm!=null){
         if(pb!=null){
%>
        <div class="margin">
            <div class="fjumbotron">
                    <div class="you">
                        <div class="x">
                            <a href="Message?id=<%=pb.get("id")%>&name=<%=pb.get("name")%>" accesskey="b"><img src="GetPost?id=<%= pb.get("id") %>" alt="images" >
                                <p class="last-n"><%=pb.get("name")%></p>
                        </div>
                    </div>
            </div>
        </div>

 <%   
     }
else{
%>
    <div class="margin">
            <div class="fjumbotron">
                    <div class="you">
                        <div class="x">
                                <p class="last-n">Nothing have found</p>
                        </div>
                    </div>
            </div>
        </div>
                        <%
}
    }
else{
 session.setAttribute("msg", "Please Login First");
                response.sendRedirect("login.jsp");
}
%>
