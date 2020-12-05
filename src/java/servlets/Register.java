
package servlets;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class Register extends HttpServlet {

   
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException
             {
       HttpSession session=request.getSession();
        try{
           
            
            int i=1;
            String k="Failed";
            Part part=request.getPart("photo");
            String e=request.getParameter("email");
            String n=request.getParameter("name");
            String dt=request.getParameter("dob");
            String no=request.getParameter("number");
            String g=request.getParameter("gender");
            String p=request.getParameter("password");
            String id=n;
            System.out.println(dt);
            java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date=sdf.parse(dt);
            java.sql.Date d=new java.sql.Date(date.getTime());
            
            System.out.println(d);
             //code to convert photo into byte
            InputStream is=null;
            if(part!=null){
                is=part.getInputStream();
            }
             // image compressor
            Image image = ImageIO.read(is);
            if(image!=null){
                int imageWidth = image.getWidth(null);
                int imageHeight = image.getHeight(null);
                if(imageWidth>1080){
                    is= resizeImage(image,1080,imageHeight*1080/imageWidth);
                }
                else{
                    is= resizeImage(image,imageWidth,imageHeight);
                }
                
            }
            
            java.util.HashMap userDetails=new java.util.HashMap();
              
            
            userDetails.put("email",e);
            userDetails.put("name",n);
            userDetails.put("password",p);
            userDetails.put("number",no);
            userDetails.put("gender",g);
            userDetails.put("dob",d);
            userDetails.put("photo",is);
            
            
            db.DbConnect db=new db.DbConnect();
            outer:
            while("Failed".equalsIgnoreCase(k)){
                k=db.checkId(id);
                if("Failed".equalsIgnoreCase(k)){
                    id=n+i;
                    i=i+1;
                }
                continue outer;
            }
            
            userDetails.put("id", id);
            String m=db.insertUserLogin(userDetails);
            if(m.equalsIgnoreCase("Success")){
                userDetails.remove("password");
//                userDetails.remove("email");
                session.setAttribute("userDetails", userDetails);
                response.sendRedirect("home.jsp");   
            }else if(m.equalsIgnoreCase("Already")){
                session.setAttribute("msg", "Email ID Already Exist");
                response.sendRedirect("create_account.jsp");
            }else{
                session.setAttribute("msg", "Registration Failed!");
                response.sendRedirect("create_account.jsp");
            }
            
        }catch (Exception ex) {
            session.setAttribute("msg", "Registration Failed: "+ex);
            response.sendRedirect("create_account.jsp");
        }
    
    }
    private  InputStream resizeImage(Image image, int width, int height) throws IOException {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpeg", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray()); 
        return is;
    }
}
