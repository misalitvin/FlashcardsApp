package pl.edu.pja.tpo02;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Entry {
    @Id
    @GeneratedValue
    private int id;
    private String worden;
    private String wordg;
    private String wordpl;

    public Entry() {
    }

    public void setWorden(String worden) {
        this.worden = worden;
    }

    public void setWordg(String wordg) {
        this.wordg = wordg;
    }

    public void setWordpl(String wordpl) {
        this.wordpl = wordpl;
    }

    public String getWorden() {
        return worden;
    }

    public String getWordg() {
        return wordg;
    }

    public String getWordpl() {
        return wordpl;
    }

    public Entry(String worden, String wordg, String wordpl) {
        this.worden = worden;
        this.wordg = wordg;
        this.wordpl = wordpl;
    }


    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Entry{" +
                "idOfEntry=" + id +
                ", worden='" + worden + '\'' +
                ", wordg='" + wordg + '\'' +
                ", wordpl=" + wordpl +
                '}';
    }
}
