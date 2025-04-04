package javadaily.javadaily;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;

@Component
public class FileLogger {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public void log(String type, String dateStr, String json) throws Exception {
        String fileName = type + "-" + DATE_FORMAT.format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX")
                .parse(dateStr)) + ".log";
        File file = new File(fileName);

        synchronized (FileLogger.class) {
            if (!file.exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("Record count: 1\n");
                    writer.write(json + "\n");
                }
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String firstLine = reader.readLine();
                    int currentCount = Integer.parseInt(firstLine.split(": ")[1]);
                    currentCount++;

                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    content.append(json).append("\n");

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write("Record count: " + currentCount + "\n");
                        writer.write(content.toString());
                    }
                }
            }
        }
    }
}