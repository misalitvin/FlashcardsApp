package pl.edu.pja.tpo02;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public class EntryRepository {
    ArrayList<Entry> entries = new ArrayList<>();

    public EntryRepository() {

    }

}
