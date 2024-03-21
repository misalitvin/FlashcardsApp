package pl.edu.pja.tpo02;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EntryRepository {

    private final EntityManager entityManager;
    public EntryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public List<Entry> getList(){
        return entityManager.createQuery("select entry from Entry entry",Entry.class).getResultList();
    }
    @Transactional
    public void addEntry(Entry entry){
        entityManager.persist(entry);
    }
    @Transactional
    public Entry findById(int id){
        return entityManager.find(Entry.class,id);
    }

    @Transactional
    public void deleteById(int id){
        Optional.ofNullable(entityManager.find(Entry.class, id)).ifPresent(entityManager::remove);
    }

    @Transactional
        public void update(int id,String updatedword,int lang)  {
        switch (lang){
            case 1 : findById(id).setWorden(updatedword);
                System.out.println(findById(id).toString());
            break;
            case 2 : findById(id).setWordg(updatedword);
            break;
            case 3:findById(id).setWordpl(updatedword);
            break;
        }
    }

}
