package servlets.ajax;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FindFriend extends HttpServlet {
    

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
       try{   
            HashMap hm=(HashMap)session.getAttribute("userDetails");
            String email=request.getParameter("email");

            db.DbConnect db=new db.DbConnect();
            java.util.HashMap pbuserDetails=db.findFriend(email, (String) hm.get("id"));
            session.setAttribute("pbuserDetails", pbuserDetails);
            response.sendRedirect("ajax/findfriend.jsp");
                            
           
       }catch(Exception ex){
            session.setAttribute("msg", ex);
       }
    }

}
