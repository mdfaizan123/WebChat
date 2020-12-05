
package socket;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


public class UserDetails extends HttpServlet {
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
       HttpSession session=request.getSession();
        try{
            HashMap hm=(HashMap)session.getAttribute("userDetails");
            db.DbConnect db=new db.DbConnect();
            
            } catch (Exception ex) {
            session.setAttribute("msg", "Failed: "+ex);
            response.sendRedirect("feed.jsp");
        }
    
    }
}
