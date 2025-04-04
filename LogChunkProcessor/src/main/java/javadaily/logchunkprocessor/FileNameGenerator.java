package javadaily.logchunkprocessor;


import org.springframework.stereotype.Component;

@Component
public class FileNameGenerator {

    public String generateOutputFileName(String inputFile, int batchNumber) {
        String baseName = inputFile.substring(0, inputFile.lastIndexOf(".log"));
        return String.format("%s-%04d.log", baseName, batchNumber);
    }
}