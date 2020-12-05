package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Friend extends HttpServlet {
    

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
       try{    
               String id=request.getParameter("id");
               
               db.DbConnect db=new db.DbConnect();
               java.util.ArrayList<java.util.HashMap> allFriend=db.getFriend(id);
               session.setAttribute("allFriend", allFriend);
               response.sendRedirect("friends.jsp");
                            
           
       }catch(Exception ex){
       }
    }

}
