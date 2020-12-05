package servlets.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GetMessageOnScroll extends HttpServlet {
    
    private java.util.ArrayList<java.util.HashMap> allMessage1;
    private HashMap hm;
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        PrintWriter out=response.getWriter();
       try{    
                hm=(HashMap)session.getAttribute("userDetails");
                HashMap pb=(HashMap)session.getAttribute("publicDetail");

                String p_id=(String)pb.get("p_id");
                String name=(String)pb.get("name");
                String m_id1=(String)pb.get("m_id");

                int m_id=Integer.parseInt(m_id1);
                java.util.HashMap publicDetail=new java.util.HashMap();
                publicDetail.put("p_id", p_id);
                publicDetail.put("name", name);
                db.DbConnect db=new db.DbConnect();
                allMessage1=db.getMessage(m_id, (String)hm.get("id"), p_id);
                Collections.reverse(allMessage1);
                if(! allMessage1.isEmpty()){
                    HashMap am=allMessage1.get(0);
                    publicDetail.put("m_id",am.get("m_id"));
                    session.setAttribute("allMessage1", allMessage1);
                    session.setAttribute("publicDetail", publicDetail);
                    response.sendRedirect("ajax/scrollmsg.jsp");
//                    message(out);                                               //output message when scroll
                }
           
       }catch(Exception ex){
           session.setAttribute("msg", ex);
       }
    }
}
