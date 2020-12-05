
package servlets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@MultipartConfig
public class GetPost extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException{
            
            HttpSession session=request.getSession();
         try{
            String id= request.getParameter("id");
            System.out.println(id.length() +" name "+id);
            db.DbConnect db=new db.DbConnect();
            byte[] b=db.getPost(id);
            if(b==null){
               System.out.println("d");
              FileInputStream fin=new FileInputStream("E:\\default.jpg");
                    b=new byte[350000];
                    fin.read(b);
            }
            System.out.println(b.length);
            response.getOutputStream().write(b);
            
        }catch(Exception ex){
        }
    }

}
