package servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Message extends HttpServlet {
    

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
       try{    
               HashMap hm=(HashMap)session.getAttribute("userDetails");
               String p_id=request.getParameter("id");
               String name=request.getParameter("name");
               java.util.HashMap publicDetail=new java.util.HashMap();
               publicDetail.put("p_id", p_id);
               publicDetail.put("name", name);
               db.DbConnect db=new db.DbConnect();
               
               java.util.ArrayList<java.util.HashMap> allMessage=db.getMessage((String)hm.get("id"), p_id);
               Collections.reverse(allMessage);
               if(! allMessage.isEmpty()){
               HashMap am=allMessage.get(0);
               publicDetail.put("m_id",am.get("m_id"));
               session.setAttribute("p_id",p_id);
               session.setAttribute("allMessage", allMessage);
               session.setAttribute("publicDetail", publicDetail);
               response.sendRedirect("message.jsp");
               }else{
                    session.setAttribute("allMessage", allMessage);
                    session.setAttribute("publicDetail", publicDetail);
                    response.sendRedirect("message.jsp");
               
               }
           
       }catch(Exception ex){
           session.setAttribute("msg", ex);
            response.sendRedirect("profile.jsp");
       }
    }

}
