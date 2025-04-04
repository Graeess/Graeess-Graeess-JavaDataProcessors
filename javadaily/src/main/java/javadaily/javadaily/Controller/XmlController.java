package javadaily.javadaily.Controller;
import javadaily.javadaily.DataModel;
import javadaily.javadaily.FileLogger;
import javadaily.javadaily.JsonConverter;
import javadaily.javadaily.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class XmlController {

    private final XmlParser xmlParser;
    private final JsonConverter jsonConverter;
    private final FileLogger fileLogger;

    @Autowired
    public XmlController(XmlParser xmlParser, JsonConverter jsonConverter, FileLogger fileLogger) {
        this.xmlParser = xmlParser;
        this.jsonConverter = jsonConverter;
        this.fileLogger = fileLogger;
    }

    @PostMapping
    public ResponseEntity<String> handleXml(@RequestBody String xml) {
        try {
            DataModel data = xmlParser.parse(xml);
            String json = jsonConverter.convertToJson(data);
            fileLogger.log(data.type(), data.creation().date(), json);

            return ResponseEntity.ok("Received and processed: " + json);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing XML: " + e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<String> handleGet() {
        return ResponseEntity.ok("Welcome to the XML to JSON Converter! Please send a POST request with XML data to this endpoint.");
    }
}

