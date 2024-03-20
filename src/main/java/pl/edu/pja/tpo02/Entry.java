package pl.edu.pja.tpo02;

public class Entry {
    private String worden;
    private String wordg;

    public void setWorden(String worden) {
        this.worden = worden;
    }

    public void setWordg(String wordg) {
        this.wordg = wordg;
    }

    public void setWordpl(String wordpl) {
        this.wordpl = wordpl;
    }

    private String wordpl;

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
}
