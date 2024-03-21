package pl.edu.pja.tpo02;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntryRepository {

    public final SpringRep springRep;
    public EntryRepository(SpringRep springRep) {
        this.springRep = springRep;
    }
    public List<Entry> getList(){
        return (List<Entry>) springRep.findAll();
    }
    public void addEntry(Entry entry){
        springRep.save(entry);
    }

    public void deleteById(int id){
        springRep.deleteById(id);
    }

        public void update(int id,String updatedword,int lang)  {
        switch (lang){
            case 1 :
                Entry temp = springRep.findById(id);
                temp.setWorden(updatedword);
                springRep.save(temp);
            break;
            case 2 : Entry t = springRep.findById(id);
                t.setWordg(updatedword);
                springRep.save(t);
            break;
            case 3: Entry g = springRep.findById(id);
                g.setWordpl(updatedword);
                springRep.save(g);
            break;
        }
    }

}
