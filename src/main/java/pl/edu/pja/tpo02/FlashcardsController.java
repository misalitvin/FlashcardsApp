package pl.edu.pja.tpo02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.*;

@Controller
@Service

public class FlashcardsController {
    Scanner scanner = new Scanner(System.in);
    FileService service;
    Print print;


    public FlashcardsController(Print print) {
        this.print = print;
        this.service = new FileService();
    }

    @Autowired
    public void setFilename(@Value("${value}") String filename) {
        this.service.setFilename(filename);
    }
    public void start(){
        boolean exit = false;
        while(!exit) {
            System.out.println("Choose an action you want to perform");
            System.out.println("1 - Add a word");
            System.out.println("2 - Display all words");
            System.out.println("3 - Get a test");
            System.out.println("4 - Search a word");
            System.out.println("5 - Sort and display");
            System.out.println("6 - Delete entry");
            System.out.println("7 - Modify entry");
            System.out.println("8 - Exit");
            int num = scanner.nextInt();
            switch (num) {
                case 1:
                    addWord();
                    break;
                case 2:
                    displayWords(service);
                    break;
                case 3:
                    test(service);
                    break;
                case 4:
                    searchWord();
                    break;
                case 5:
                    displaySort();
                    break;
                case 6:
                    deleteEntry();
                    break;
                case 7:
                    modify();
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("You have selected wrong number");
            }
        }
    }
    public void addWord(){
        System.out.println("Enter the word in English");
        String eng = scanner.nextLine();
        eng =scanner.nextLine();
        System.out.println("Enter the word in German");
        String ger;
        ger = scanner.nextLine();
        System.out.println("Enter the word in Polish");
        String pl;
        pl = scanner.nextLine();
        service.entryRepository.entries.add(new Entry(eng,ger,pl));
    }
    public void displayWords(FileService service) {
        System.out.println("English German Polish");
        for(Entry e:service.entryRepository.entries){
            System.out.println(print.changeWord(e.getWorden())+" "+
                    print.changeWord(e.getWordg())+" "+print.changeWord(e.getWordpl()));
        }
    }
    public void test(FileService service) {
        Scanner scanner = new Scanner(System.in);
        Entry word = pickRandomWord(service.entryRepository);
        Random random = new Random();
        int randomIndex = random.nextInt(3);

        switch (randomIndex) {
            case 0 :{
                System.out.println("Your word is english: " + print.changeWord(word.getWorden()) );
                System.out.println("Enter German translation");
                String german = scanner.nextLine().toLowerCase();
                german = scanner.nextLine().toLowerCase();
                System.out.println("Enter Polish translation");
                String polish = scanner.nextLine().toLowerCase();

                if(german.equals(word.getWordg().toLowerCase()) && polish.equals(word.getWordpl().toLowerCase()))
                    System.out.println("Correct");
                else System.out.println("Incorrect");
                break;
            }
            case 1 : {
                System.out.println("Your word is german: " + print.changeWord(word.getWordg()));
                System.out.println("Enter English translation");
                String english = scanner.nextLine().toLowerCase();
                english = scanner.nextLine().toLowerCase();
                System.out.println("Enter Polish translation");
                String polish = scanner.nextLine().toLowerCase();

                if(english.equals(word.getWorden().toLowerCase()) && polish.equals(word.getWordpl().toLowerCase()))
                    System.out.println("Correct");
                else System.out.println("Incorrect");
                break;
            }
            case 2 :{
                System.out.println("Your word is polish: " + print.changeWord(word.getWordpl()) );
                System.out.println("Enter English translation");
                String english = scanner.nextLine().toLowerCase();
                english = scanner.nextLine().toLowerCase();
                System.out.println("Enter German translation");
                String german = scanner.nextLine().toLowerCase();

                if(english.equals(word.getWorden().toLowerCase()) && german.equals(word.getWordg().toLowerCase()))
                    System.out.println("Correct");
                else System.out.println("Incorrect");
            }
        }
    }

    public Entry pickRandomWord(EntryRepository entries) {
        if (entries.entries.isEmpty()) {
            throw new IllegalArgumentException("There is no words");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(entries.entries.size());
        return entries.entries.get(randomIndex);
    }
    public void searchWord(){
        System.out.println("Enter the word");
        String word = scanner.nextLine();
        word= scanner.nextLine();
        for(Entry e:service.entryRepository.entries){
            if(word.equalsIgnoreCase(e.getWorden())||word.equalsIgnoreCase(e.getWordg())
                    ||word.equalsIgnoreCase(e.getWordpl())){
                System.out.println(print.changeWord(e.getWorden())+" "+
                        print.changeWord(e.getWordg())+" "+print.changeWord(e.getWordpl()));
            }
        }
    }
    public void displaySort(){
        System.out.println("Choose according to which language sort ");
        String lang = scanner.nextLine();
        lang = scanner.nextLine();
        System.out.println("Choose in which order");
        String order = scanner.nextLine();
        String[][] words = new String[service.entryRepository.entries.size()][3];
        int i = 0;
        for(Entry e:service.entryRepository.entries){
            words[i][0] = e.getWorden().toLowerCase();
            words[i][1] = e.getWordg().toLowerCase();
            words[i][2] = e.getWordpl().toLowerCase();
            i++;
        }
        switch (lang){
            case "English": {
                Arrays.sort(words,Comparator.comparing(row -> row[0]));
                printsorted(order, words);
                break;
            }
            case "German": {
                Arrays.sort(words,Comparator.comparing(row -> row[1]));
                printsorted(order, words);
                break;
            }
            case "Polish" : {
                Arrays.sort(words,Comparator.comparing(row -> row[2]));
                printsorted(order, words);
                break;
            }
            default:
                System.out.println("You have entered wrong language");
        }

    }

    public void printsorted(String order, String[][] words) {
        if(order.equalsIgnoreCase("asc")){
            for (String[] word : words) {
                System.out.println(print.changeWord(word[0]) + " " +
                        print.changeWord(word[1]) + " " + print.changeWord(word[2]));
            }
        }else if(order.equalsIgnoreCase("desc")){
            for(int k = words.length-1;k>=0;k--){
                System.out.println(print.changeWord(words[k][0])+" "+
                        print.changeWord(words[k][1])+" "+print.changeWord(words[k][2]));
            }
        }
    }
    public void deleteEntry(){
        System.out.println("English German Polish");
        int i = 0;
        for(Entry e:service.entryRepository.entries){
            System.out.print(i + " ");
            System.out.println(print.changeWord(e.getWorden())+" "+
                    print.changeWord(e.getWordg())+" "+print.changeWord(e.getWordpl()));
            i++;
        }
        System.out.println("Choose the number of the word to delete");
        int num = scanner.nextInt();
        i=0;
        for(Entry e:service.entryRepository.entries){
            if(num==i){
                service.entryRepository.entries.remove(e);
                break;
            }
            i++;
        }
    }
    public void modify() {
        System.out.println("English German Polish");
        int i = 0;
        for (Entry e : service.entryRepository.entries) {
            System.out.print(i + " ");
            System.out.println(print.changeWord(e.getWorden()) + " " +
                    print.changeWord(e.getWordg()) + " " + print.changeWord(e.getWordpl()));
            i++;
        }
        System.out.println("Choose the number of the word to modify");
        int num = scanner.nextInt();
        System.out.println("Choose the language of which is word to modify");
        String lang = scanner.nextLine();
        lang = scanner.nextLine();
        System.out.println("Enter the new word");
        String newword = scanner.nextLine();
        switch (lang) {
            case "English":
                service.entryRepository.entries.get(num).setWorden(newword);
                break;
            case "German":
                service.entryRepository.entries.get(num).setWordg(newword);
                break;
            case "Polish":
                service.entryRepository.entries.get(num).setWordpl(newword);
                break;
            default:
        }
    }
}
