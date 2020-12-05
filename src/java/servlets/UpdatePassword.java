
package servlets;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdatePassword extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                  HttpSession session=request.getSession();
        try{
         HashMap u=(HashMap)session.getAttribute("user");
            if(u!=null){
            String p1=request.getParameter("password1");
            String p2=request.getParameter("password2");
            if(p1.equals(p2)){
                db.DbConnect db=new db.DbConnect();
                String s=db.updatePassword((String)u.get("email"),p1);
                if(s.equalsIgnoreCase("Success")){
                    session.invalidate();
                    session=request.getSession();
                    session.setAttribute("msg", "Password update Susseccfully!");
                    response.sendRedirect("login.jsp");
                }
                else if(s.equalsIgnoreCase("Failed")){
                    session.setAttribute("msg", "Somthing wrong");
                    response.sendRedirect("login.jsp");
                }
            }
            else{
            session.setAttribute("msg", "password not matched!");
            response.sendRedirect("forget_password.jsp");
             }
           }else{
                   session.setAttribute("msg", "Somthing Wrong");
           response.sendRedirect("login.jsp");
               }
        }catch(Exception ex){
           session.setAttribute("msg", ex);
           response.sendRedirect("login.jsp");
        }
        
    }

}
