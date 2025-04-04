package javadaily.logchunkprocessor;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProgressManager {

    private static final String PROGRESS_FILE = "progress.txt";

    public int readProgress(String inputFile) throws Exception {
        File progressFile = new File(PROGRESS_FILE);
        if (!progressFile.exists()) {
            return 0;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(progressFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(inputFile + ":")) {
                    return Integer.parseInt(line.split(":")[1].trim());
                }
            }
        }
        return 0;
    }

    public void writeProgress(String inputFile, int processedRecords) throws Exception {
        List<String> lines = new ArrayList<>();
        File progressFile = new File(PROGRESS_FILE);
        boolean updated = false;


        if (progressFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(progressFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(inputFile + ":")) {
                        lines.add(inputFile + ":" + processedRecords);
                        updated = true;
                    } else {
                        lines.add(line);
                    }
                }
            }
        }


        if (!updated) {
            lines.add(inputFile + ":" + processedRecords);
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(progressFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}