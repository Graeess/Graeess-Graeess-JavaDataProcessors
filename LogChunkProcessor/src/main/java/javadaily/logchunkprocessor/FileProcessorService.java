package javadaily.logchunkprocessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileProcessorService {

    private static final int BATCH_SIZE = 100;

    private final LogReader logReader;
    private final LogWriter logWriter;
    private final ProgressManager progressManager;
    private final FileNameGenerator fileNameGenerator;

    @Autowired
    public FileProcessorService(LogReader logReader, LogWriter logWriter, ProgressManager progressManager, FileNameGenerator fileNameGenerator) {
        this.logReader = logReader;
        this.logWriter = logWriter;
        this.progressManager = progressManager;
        this.fileNameGenerator = fileNameGenerator;
    }

    public void processLogFiles(String inputFile) throws Exception {

        int processedRecords = progressManager.readProgress(inputFile);
        int currentBatch = processedRecords / BATCH_SIZE + 1;


        List<String> records = logReader.readRecords(inputFile);
        int totalRecords = records.size();


        List<String> batch = new ArrayList<>();
        for (int i = processedRecords; i < totalRecords; i++) {
            batch.add(records.get(i));

            if (batch.size() == BATCH_SIZE || i == totalRecords - 1) {
                String outputFile = fileNameGenerator.generateOutputFileName(inputFile, currentBatch);
                logWriter.writeBatch(outputFile, batch);
                currentBatch++;
                batch.clear();
            }


            progressManager.writeProgress(inputFile, i + 1);
        }
    }
}