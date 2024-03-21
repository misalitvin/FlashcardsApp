package pl.edu.pja.tpo02;

public class EntryNotFoundException extends Exception{
    public EntryNotFoundException() {
        System.out.println("This Entry is not found");
    }
}
