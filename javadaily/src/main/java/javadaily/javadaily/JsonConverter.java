package javadaily.javadaily;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonConverter {
    public String convertToJson(DataModel data) {
        JSONObject json = new JSONObject();
        JSONObject dataJson = new JSONObject();


        JSONObject methodJson = new JSONObject();
        methodJson.put("Name", data.method().name());
        methodJson.put("Type", data.method().type());
        methodJson.put("Assembly", data.method().assembly());
        dataJson.put("Method", methodJson);


        JSONObject processJson = new JSONObject();
        processJson.put("Name", data.process().name());
        processJson.put("Id", data.process().id());
        JSONObject startJson = new JSONObject();
        startJson.put("Epoch", data.process().start().epoch());
        startJson.put("Date", data.process().start().date());
        processJson.put("Start", startJson);
        dataJson.put("Process", processJson);


        dataJson.put("Layer", data.layer());


        JSONObject creationJson = new JSONObject();
        creationJson.put("Epoch", data.creation().epoch());
        creationJson.put("Date", data.creation().date());
        dataJson.put("Creation", creationJson);


        dataJson.put("Type", data.type());

        json.put("Data", dataJson);
        return json.toString(2);
    }
}