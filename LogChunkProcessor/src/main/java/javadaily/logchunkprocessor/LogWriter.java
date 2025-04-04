package javadaily.logchunkprocessor;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

@Component
public class LogWriter {

    public void writeBatch(String outputFile, List<String> batch) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String record : batch) {
                writer.write(record);
                writer.newLine();
            }
        }
    }
}