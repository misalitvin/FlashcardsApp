package pl.edu.pja.tpo02;

import org.springframework.data.repository.CrudRepository;

public interface SpringRep extends CrudRepository<Entry,Integer> {
    Entry findById(int index);
}
