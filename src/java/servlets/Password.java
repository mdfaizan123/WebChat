
package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Password extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String otp="";
        String e=request.getParameter("email");
        HttpSession session=request.getSession();
        try {
            db.DbConnect db=new db.DbConnect();
            String result=db.checkEmail(e);
            if(result.equalsIgnoreCase("Yes")){
                Random rand = new Random();
                for(int i=0; i<4; i++){
                int r = rand.nextInt(10); 
		otp=otp+r;
                }
                final String SEmail="onlinestudy80@gmail.com";
                final String SPass="******************";
                final String REmail=e;
                final String Sub="Yur one time OTP is Here from ChatWeb!";
                final String Body="You Email Id: "+e+" and OTP: "+otp;
                
            Properties props=new Properties();
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port","465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.port","465");
            Session ses=Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(SEmail,SPass);
                }
            }
            );
            Message message=new MimeMessage(ses);
            message.setFrom(new InternetAddress(SEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(REmail));
            message.setSubject(Sub);
            message.setContent(Body,"text/html" );
            Transport.send(message);
            HashMap user=new HashMap();
            user.put("email", e);
            user.put("otp", otp);
            session.setAttribute("user",user);
            session.setAttribute("result","yes");
            response.sendRedirect("forget_password.jsp");
            }else{
                session.setAttribute("msg", "Wrong Emial ID");
                response.sendRedirect("forget_password.jsp");
            }
        } catch (Exception ex) {
            session.setAttribute("msg", "Exception: "+ex);
            response.sendRedirect("forget_password.jsp");
        }
    }

}
