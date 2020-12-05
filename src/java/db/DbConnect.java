package db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DbConnect {
    private final Connection c;
    private final Statement st;
    private PreparedStatement checkLogin,checkEmail,getUserDetails,checkId,insertLogin,insertUser,getFriend,getFriend1,getName,insertFollower
            ,getMessage2,getMessage,getMessage1,uploadMessage,insertLastMessage,checLastkMessage,UpdateLastMessage,lastId,findFriend,
            updatePassword,getPost,getUserDetailsId;
    public DbConnect() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        c=DriverManager.getConnection("jdbc:mysql://localhost:3306/incapp-project","root","19142612");
        st=c.createStatement();
        
        getPost=c.prepareStatement("select photo from user_details where id=?");
        updatePassword =c.prepareStatement("update login set password=? where email=?");
        insertLastMessage=c.prepareStatement("insert into last_message (id,f_id,m_id) values(?,?,?)");
        checLastkMessage=c.prepareStatement("select m_id from  last_message where (id=? and f_id=?) or (id=? and f_id=?)");
        UpdateLastMessage=c.prepareStatement("update last_message set m_id=?  where (id=? and f_id=?) or (id=? and f_id=?)");
        lastId=c.prepareStatement("select last_insert_id() as m_id from message where id=? and p_id=? and text=? limit 1");
        uploadMessage=c.prepareStatement("insert into message (id,p_id,text,date) values(?,?,?,now())");
        getMessage2=c.prepareStatement("select *  from  message where m_id=?");
        getMessage1=c.prepareStatement("select *  from  message where (m_id<? and id=? and p_id=?) or (m_id<? and p_id=? and id=?) order by m_id desc limit 10 ");
        getMessage=c.prepareStatement("select *  from  message where (id=? and p_id=?) or (p_id=? and id=?) order by m_id desc limit 10 ");
        insertFollower=c.prepareStatement("insert into follower (id,f_id,date) values(?,?,now())");
        getFriend=c.prepareStatement("select distinct(p_id) from  message where id=?");
        getName=c.prepareStatement("select name from  user_details where id=?");
        checkId=c.prepareStatement("select id from  user_details where id=?");
        getUserDetails=c.prepareStatement("select * from  user_details where email=?");
        findFriend=c.prepareStatement("select * from  user_details where email=? and id!=?");
        getUserDetailsId=c.prepareStatement("select * from  user_details where id=?");
        checkLogin =c.prepareStatement("select * from  login where email=? and password=?");
        checkEmail=c.prepareStatement("select email from  login where email=?");
        insertLogin=c.prepareStatement("insert into login (email,password) values(?,?)");
        insertUser=c.prepareStatement("insert into user_details (email,id,name,number,dob,gender,photo) values(?,?,?,?,?,?,?)");
    } 
    
    public byte[] getPost(String id){
        try{
            
            getPost.setString(1, id);
            ResultSet rs=getPost.executeQuery();
            if(rs.next()){
                byte[] b=rs.getBytes("photo");
                if(b.length!=0)
                    return b;
                else
                    return null;
            }
            else{
                return null;
            }
        }catch(Exception ex){
            return null;
            
        }
    }
    public HashMap findFriend(String e,String uid) throws SQLException{
        findFriend.setString(1,e);
        findFriend.setString(2, uid);
        ResultSet rs=findFriend.executeQuery();
        if(rs.next()){
            HashMap pbuserDetails=new HashMap();
            pbuserDetails.put("id", rs.getString("id"));
            pbuserDetails.put("name", rs.getString("name"));
            return pbuserDetails;
        }
        else{
            return null;
        }
        
    }
    public String checkId(String id) throws SQLException{
        checkId.setString(1, id);
            ResultSet rs=checkId.executeQuery();
            if(rs.next())
                 return "Failed";
            else
                 return "Success"; 
       
        
    }
    public String checkLogin(String e,String p) throws SQLException{
        checkEmail.setString(1,e);
        ResultSet rs=checkEmail.executeQuery();
        if(rs.next()){
            checkLogin.setString(1,e);
            checkLogin.setString(2,p);
            ResultSet rs2=checkLogin.executeQuery();
            if(rs2.next()){
                return "login";
            }
            else{
                
                return "password";
            }
        
        }
        else{
            return "account";
        }
        
    }
    public String checkEmail(String e) throws SQLException{
        checkEmail.setString(1,e);
        ResultSet rs=checkEmail.executeQuery();
        if(rs.next()){
           return "Yes";
        }
        else{
            return "No";
        }
        
    }
    public HashMap getUserDetails(String e) throws SQLException{
        getUserDetails.setString(1,e);
        ResultSet rs=getUserDetails.executeQuery();
        if(rs.next()){
            HashMap userDetails=new HashMap();
            userDetails.put("id", rs.getString("id"));
            userDetails.put("name", rs.getString("name"));
            userDetails.put("gender", rs.getString("gender"));
            userDetails.put("dob", rs.getString("dob"));
            userDetails.put("number", rs.getString("number"));
            userDetails.put("email", rs.getString("email"));
            return userDetails;
        }
        else{
            return null;
        }
        
    }
    public HashMap getUserDetailsId(String e) throws SQLException{
        getUserDetailsId.setString(1,e);
        ResultSet rs=getUserDetailsId.executeQuery();
        if(rs.next()){
            HashMap userDetails=new HashMap();
            userDetails.put("id", rs.getString("id"));
            userDetails.put("name", rs.getString("name"));
            userDetails.put("gender", rs.getString("gender"));
            userDetails.put("dob", rs.getString("dob"));
            userDetails.put("number", rs.getString("number"));
            userDetails.put("email", rs.getString("email"));
            return userDetails;
        }
        else{
            return null;
        }
        
    }
    public String insertUserLogin(HashMap userDetails) throws SQLException{
        try{
            insertUser.setString(1, (String)userDetails.get("email"));
            insertUser.setString(2, (String)userDetails.get("id"));
            insertUser.setString(3, (String)userDetails.get("name"));
            insertUser.setString(4, (String)userDetails.get("number"));
            insertUser.setDate(5, (java.sql.Date)userDetails.get("dob"));
            insertUser.setString(6, (String)userDetails.get("gender"));
            insertUser.setBinaryStream(7, (InputStream)userDetails.get("photo"));
            
            insertLogin.setString(1, (String)userDetails.get("email"));
            insertLogin.setString(2, (String)userDetails.get("password"));
            
            int x=insertUser.executeUpdate();
            int y=insertLogin.executeUpdate();
            if(x!=0 && y!=0)
                 return "Success";
            else
                 return "Failed"; 
        }catch(java.sql.SQLIntegrityConstraintViolationException  ex){
          return "Already";  
        }
        
    }
    public java.util.ArrayList<java.util.HashMap> getFriend(String id) throws SQLException{
        
        getFriend.setString(1,id);
        ResultSet rs=getFriend.executeQuery();
        java.util.ArrayList<java.util.HashMap> getFriend=new java.util.ArrayList();
        
        while(rs.next()){
            java.util.HashMap details=new java.util.HashMap();
            details.put("f_id", rs.getString("p_id"));
            details.put("name", getName(rs.getString("p_id")));
            getFriend.add(details);
        }
        return getFriend;
    }
    public String getName(String i) throws SQLException{
        getName.setString(1,i);
        ResultSet rs=getName.executeQuery();
        if(rs.next()){
            return rs.getString("name");
        }
        else{
            return null;
        }
        
    }
    public java.util.HashMap getMessage(int m_id) throws SQLException{
        getMessage2.setInt(1, m_id);
        ResultSet rs=getMessage2.executeQuery();
        java.util.HashMap message=new java.util.HashMap();
        while(rs.next()){
            message.put("text", rs.getString("text"));
            message.put("date", rs.getString("date"));
            message.put("m_id", rs.getString("m_id"));
            message.put("id", rs.getString("id"));
            message.put("p_id", rs.getString("p_id"));
        }
        return message;
    }
    
    public java.util.ArrayList<java.util.HashMap> getMessage(String id, String p_id) throws SQLException{
        getMessage.setString(1, id);
        getMessage.setString(2, p_id);
        getMessage.setString(3, id);
        getMessage.setString(4, p_id);
        ResultSet rs=getMessage.executeQuery();
        java.util.ArrayList<java.util.HashMap> allMessageMe=new java.util.ArrayList();
        while(rs.next()){
            java.util.HashMap message=new java.util.HashMap();
            message.put("text", rs.getString("text"));
            message.put("date", rs.getString("date"));
            message.put("m_id", rs.getString("m_id"));
            message.put("id", rs.getString("id"));
            allMessageMe.add(message);
        }
        return allMessageMe;
    }
    
    public java.util.ArrayList<java.util.HashMap> getMessage(int m_id, String id, String p_id) throws SQLException{
        getMessage1.setInt(1, m_id);
        getMessage1.setString(2, id);
        getMessage1.setString(3, p_id);
        getMessage1.setInt(4, m_id);
        getMessage1.setString(5, id);
        getMessage1.setString(6, p_id);
        ResultSet rs=getMessage1.executeQuery();
        java.util.ArrayList<java.util.HashMap> allMessageMe=new java.util.ArrayList();
        while(rs.next()){
            java.util.HashMap message=new java.util.HashMap();
            message.put("text", rs.getString("text"));
            message.put("date", rs.getString("date"));
            message.put("m_id", rs.getString("m_id"));
            message.put("id", rs.getString("id"));
            allMessageMe.add(message);
        }
        return allMessageMe;
    }
    public String uploadMessage(HashMap messageDetail) throws SQLException{
        try{
            uploadMessage.setString(1, (String)messageDetail.get("id"));
            uploadMessage.setString(2, (String)messageDetail.get("p_id"));
            uploadMessage.setString(3, (String)messageDetail.get("text"));
            lastId.setString(1, (String)messageDetail.get("id"));
            lastId.setString(2, (String)messageDetail.get("p_id"));
            lastId.setString(3, (String)messageDetail.get("text"));
            int x=uploadMessage.executeUpdate();
            ResultSet rs=lastId.executeQuery();
            String m_id="";
            while(rs.next()){
                m_id=rs.getString("m_id");
                checLastkMessage.setString(1, (String)messageDetail.get("id"));
                checLastkMessage.setString(2, (String)messageDetail.get("p_id"));
                checLastkMessage.setString(3, (String)messageDetail.get("p_id"));
                checLastkMessage.setString(4, (String)messageDetail.get("id"));
                ResultSet rs1=checLastkMessage.executeQuery();
                if(rs1.next()){
                    UpdateLastMessage.setInt(1, Integer.parseInt(m_id));
                    UpdateLastMessage.setString(2, (String)messageDetail.get("id"));
                    UpdateLastMessage.setString(3, (String)messageDetail.get("p_id"));
                    UpdateLastMessage.setString(4, (String)messageDetail.get("p_id"));
                    UpdateLastMessage.setString(5, (String)messageDetail.get("id"));
                    UpdateLastMessage.executeUpdate();
                }
                else{
                    insertLastMessage.setString(1, (String)messageDetail.get("id"));
                    insertLastMessage.setString(2, (String)messageDetail.get("p_id"));
                    insertLastMessage.setInt(3, Integer.parseInt(m_id));
                    insertLastMessage.executeUpdate();
                    
                }
                
                
            }
            if(x!=0)
              return  m_id;
            
            else
                 return "failed"; 
            
            }catch(Exception  ex){
          return "failed";  
        }
        
    }
    public String updatePassword(String e,String p) throws SQLException{
            updatePassword.setString(1,p);
            updatePassword.setString(2,e);
            int x=updatePassword.executeUpdate();
            if(x!=0){
                return "Success";
            }else{
                return "Failed";
            }
        
    }
    
}