
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class Login extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        try{
            
            String e=request.getParameter("email");
            String p=request.getParameter("password");
            
            db.DbConnect db=new db.DbConnect();
            String m=db.checkLogin(e,p);
            if(m.equalsIgnoreCase("login")){
                java.util.HashMap userDetails=db.getUserDetails(e);
                        if(userDetails!=null){
                         session.setAttribute("id", userDetails.get("id"));
                         session.setAttribute("userDetails", userDetails);
                         response.sendRedirect("home.jsp");  
                        }else{
                          session.setAttribute("msg","Something wrong");
                          response.sendRedirect("login.jsp");
                        }
            }
            else if(m.equalsIgnoreCase("password")){
                 session.setAttribute("msg", "Wrong password");
                 response.sendRedirect("login.jsp");
            }        
            
            else if(m.equalsIgnoreCase("account")){
                session.setAttribute("msg","account does not exist");
                response.sendRedirect("login.jsp");  
            }
            else{
                session.setAttribute("msg","Something wrong");
                response.sendRedirect("login.jsp");
              }
            
        }catch(Exception ex){
             session.setAttribute("msg", "Login Failed: "+ex);
             response.sendRedirect("login.jsp");
        }
                
    }
    
}
