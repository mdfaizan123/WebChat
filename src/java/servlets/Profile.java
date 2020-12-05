
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class Profile extends HttpServlet {

    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        try{
            
            String id=request.getParameter("id");
            db.DbConnect db=new db.DbConnect();
            java.util.HashMap publicDetails=db.getUserDetailsId(id);
            if(publicDetails!=null){
                session.setAttribute("publicDetails", publicDetails);
                response.sendRedirect("profile.jsp");  
            }else{
                response.sendRedirect("pagenotavailable.html");
            }
            
            
        }catch(Exception ex){
             session.setAttribute("msg", "Login Failed: "+ex);
             response.sendRedirect("login.jsp");
        }
                
    }
    
}
