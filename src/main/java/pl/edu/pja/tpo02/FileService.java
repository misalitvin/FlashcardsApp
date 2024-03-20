package pl.edu.pja.tpo02;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;


@Service
public class FileService {
    EntryRepository entryRepository = new EntryRepository();

    public void setFilename(@Value("${value}") String filename) {
        readFile(filename);
    }

    public void addEntry(String worden, String wordg, String wordpl) {
        entryRepository.entries.add(new Entry(worden, wordg, wordpl));
    }

    private void readFile(String filename) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                String[] row = line.split(";");
                addEntry(row[0], row[1], row[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

