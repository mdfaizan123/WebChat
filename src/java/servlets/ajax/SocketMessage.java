package servlets.ajax;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SocketMessage extends HttpServlet {
    

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
       try{    
//                int m_id=Integer.parseInt(request.getParameter("m_id"));
                db.DbConnect db=new db.DbConnect();
                java.util.HashMap SocketMessage=db.getMessage(Integer.parseInt(request.getParameter("m_id")));
                session.setAttribute("SocketMessage", SocketMessage);
                response.sendRedirect("ajax/socketmessage.jsp");
                
           
       }catch(Exception ex){
           session.setAttribute("msg", ex);
       }
    }

}
