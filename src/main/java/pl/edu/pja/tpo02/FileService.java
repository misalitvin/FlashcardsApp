package pl.edu.pja.tpo02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.*;
import java.util.Arrays;
import java.util.Comparator;

@Service
public class FileService {
    @Autowired
    public FileService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
        checkFile("jdbc:h2:file:./tpo03","misalitvin","22032005");
    }
    EntryRepository entryRepository;
    public void checkFile(String UrlAdress,String user,String passwoerd){
        try{
            Class.forName("org.h2.Driver");
            String query =  "Select Count (*) from ENTRY";
            Connection con = DriverManager.getConnection(UrlAdress,user,passwoerd);
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(query);
            rs.next();

            if(rs.getInt(1)==0){
                entryRepository.addEntry(new Entry("tree","baum","drzewo"));
                entryRepository.addEntry(new Entry("cat","katze","kot"));
                entryRepository.addEntry(new Entry("bowwow","doggo","pies"));
                entryRepository.addEntry(new Entry("pole","polaed","polpol"));
                entryRepository.addEntry(new Entry("ram","raumpanzer","baran"));
                entryRepository.addEntry(new Entry("jemape","aufwiederzein","alabama"));
            }
            rs.close();
            stat.close();
            con.close();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String[][] sort(String lang){
        String[][] words = new String[entryRepository.getList().size()][3];
        int i = 0;
        for(Entry e:entryRepository.getList()){
            words[i][0] = e.getWorden().toLowerCase();
            words[i][1] = e.getWordg().toLowerCase();
            words[i][2] = e.getWordpl().toLowerCase();
            i++;
        }
        switch (lang){
            case "English": {
                Arrays.sort(words, Comparator.comparing(row -> row[0]));
                break;
            }
            case "German": {
                Arrays.sort(words,Comparator.comparing(row -> row[1]));

                break;
            }
            case "Polish" : {
                Arrays.sort(words,Comparator.comparing(row -> row[2]));
                break;
            }

        }
        return words;
    }
    public void updateWord(int lang,int id,String newword)  {
        entryRepository.update(id,newword,lang);
    }


}

