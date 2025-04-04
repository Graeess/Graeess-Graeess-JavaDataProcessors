package javadaily.logchunkprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/process")
public class FileProcessorController {

    private final FileProcessorService fileProcessorService;

    @Autowired
    public FileProcessorController(FileProcessorService fileProcessorService) {
        this.fileProcessorService = fileProcessorService;
    }

    @PostMapping
    public ResponseEntity<String> processFile(@RequestParam String fileName) {
        try {
            fileProcessorService.processLogFiles(fileName);
            return ResponseEntity.ok("File " + fileName + " processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing file: " + e.getMessage());
        }
    }
}

