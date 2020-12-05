
package servlets;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class checkOTP extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        try{
            String otp1=request.getParameter("otp");
         HashMap o=(HashMap)session.getAttribute("user");
         if(o!=null){
            String otp=(String)o.get("otp");
            if(otp1.equals(otp)){
             session.setAttribute("result", "matched"); 
            response.sendRedirect("forget_password.jsp");
            }else{
                session.setAttribute("msg", "OTP does not matched");
            response.sendRedirect("forget_password.jsp");
        
            }
              
         }
         else{
                session.setAttribute("msg", "Somthing Wrong:");
                response.sendRedirect("login.jsp");
        
            }
        }catch(Exception ex){
             session.setAttribute("msg", "Somthing Wrong: "+ex);
             response.sendRedirect("login.jsp");
        }
                
    }
    
}
