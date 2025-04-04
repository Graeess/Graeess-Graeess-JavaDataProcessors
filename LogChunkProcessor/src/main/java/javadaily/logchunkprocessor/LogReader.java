package javadaily.logchunkprocessor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class LogReader {

    public List<String> readRecords(String inputFile) throws Exception {
        List<String> records = new ArrayList<>();
        StringBuilder currentRecord = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            boolean isFirstLine = true;
            boolean insideRecord = false;

            while ((line = reader.readLine()) != null) {

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                line = line.trim();
                if (line.startsWith("{")) {
                    insideRecord = true;
                    currentRecord = new StringBuilder(line);
                } else if (line.endsWith("}")) {
                    currentRecord.append("\n").append(line);
                    records.add(currentRecord.toString());
                    insideRecord = false;
                } else if (insideRecord) {
                    currentRecord.append("\n").append(line);
                }
            }
        }

        return records;
    }
}