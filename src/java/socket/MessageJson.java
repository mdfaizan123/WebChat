
package socket;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;


public class MessageJson {

    public MessageJson(JsonObject json) {
        this.json = json;
    }
    public void setJson(JsonObject json) {
        this.json = json;
    }

    public JsonObject getJson() {
        return json;
    }
    private JsonObject json;
    
    @Override
    public String toString() {
        StringWriter writer = new StringWriter();
        Json.createWriter(writer).write(json);
        return writer.toString();
    }
}
