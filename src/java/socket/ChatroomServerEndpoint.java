package socket;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServlet;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;

@ServerEndpoint(value = "/socketServerEndpoint/{id}", configurator = Handshake.class, encoders = {FigureEncoder.class}, decoders = {FigureDecoder.class})
public class ChatroomServerEndpoint extends HttpServlet {
    
    static Map<String, Set<Session>> ids = Collections.synchronizedMap(new HashMap<>());
    private String resultId;

    private Set<Session> getId(String idName) {
        Set<Session> id = ids.get(idName);
        if (id == null) {
            id = Collections.synchronizedSet(new HashSet<>());
            ids.put(idName, id);
        }
        return id;
    }

    @OnMessage
    public void handleMessage(MessageJson json, Session userSession) throws Exception{
        System.out.println("hello:"+json.getJson() );
        String type=json.getJson().getString("type");
        if(type.equalsIgnoreCase("message")){
            sendMessage(json,userSession,type);                                 //send message to database and both client
            
        }
        
    }
    @OnOpen
    public void handleOpen(EndpointConfig config, Session userSession, @PathParam("id") String id){
            String session_id=(String)config.getUserProperties().get("id");
            if(session_id!=null){
                userSession.getUserProperties().put("id", id);
                userSession.getUserProperties().put("session_id", session_id);
                Set<Session> idUser = getId(id);
                idUser.add(userSession);
            }
            
       
        
    }

    @OnClose
    public void handleClose(Session userSession) {
        String id = (String) userSession.getUserProperties().get("id");
        Set<Session> idUsers = getId(id);
        idUsers.remove(userSession);
    }
    private String buildJsonData(String m_id, String type) {
        JsonObjectBuilder jsonObject = Json.createObjectBuilder();
        jsonObject.add("m_id", m_id);
        jsonObject.add("type", type);
        JsonObject json=jsonObject.build();
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.write(json);
        }
        return stringWriter.toString();
    }
    
    private void sendMessage(MessageJson json, Session userSession, String type) throws Exception{
                java.util.HashMap messageDetail=new java.util.HashMap();
                db.DbConnect db=new db.DbConnect();
                messageDetail.put("id", json.getJson().getString("id"));
                messageDetail.put("p_id", json.getJson().getString("p_id"));
                messageDetail.put("text", json.getJson().getString("message"));
               
                resultId=db.uploadMessage(messageDetail);
                String id = (String) userSession.getUserProperties().get("id");
                Set<Session> idUsers = getId(id);
                idUsers.stream().forEach(new Consumer<Session>() {
                    @Override
                    public void accept(Session x) {
                        try {
                            x.getBasicRemote().sendText(buildJsonData(resultId,type));
                        }catch (IOException ex) {
                             ex.printStackTrace();
                        }
                    }
                });
    }
    
}