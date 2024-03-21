package pl.edu.pja.tpo02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.*;

@Controller


public class FlashcardsController {
    Scanner scanner = new Scanner(System.in);
    private  final  FileService service;
    Print print;

@Autowired
    public FlashcardsController(Print print, FileService service) {
        this.print = print;
        this.service = service;
    }
    public void start() throws Throwable {
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
                    displayWords();
                    break;
                case 3:
                    test();
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
        service.entryRepository.addEntry(new Entry(eng,ger,pl));
    }
    public void displayWords() {
        System.out.println("English German Polish");
        for(Entry e:service.entryRepository.getList()){
            System.out.println(e.getId()+" "+print.changeWord(e.getWorden())+" "+
                    print.changeWord(e.getWordg())+" "+print.changeWord(e.getWordpl()));
        }
    }
    public void test() {
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
        if (entries.getList().isEmpty()) {
            throw new IllegalArgumentException("There is no words");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(entries.getList().size());
        return entries.getList().get(randomIndex);
    }
    public void searchWord(){
        System.out.println("Enter the word");
        String word = scanner.nextLine();
        word= scanner.nextLine();
        for(Entry e:service.entryRepository.getList()){
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
        printsorted(order, service.sort(lang));
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
        for(Entry e:service.entryRepository.getList()){
            System.out.print(e.getId() + " ");
            System.out.println(print.changeWord(e.getWorden())+" "+
                    print.changeWord(e.getWordg())+" "+print.changeWord(e.getWordpl()));
        }
        System.out.println("Choose the number of the word to delete");
        int num = scanner.nextInt();
        service.entryRepository.deleteById(num);
    }
    public void modify() {
        System.out.println("English German Polish");
        for (Entry e : service.entryRepository.getList()) {
            System.out.print(e.getId() + " ");
            System.out.println(print.changeWord(e.getWorden()) + " " +
                    print.changeWord(e.getWordg()) + " " + print.changeWord(e.getWordpl()));
        }
        System.out.println("Choose the number of the word to modify");
        int num = scanner.nextInt();
        System.out.println("Choose the language of which is word to modify");
        System.out.println("Eng-1, Ger-2, Pl-3");
        int lang = scanner.nextInt();
        System.out.println("Enter the new word");
        String newword = scanner.nextLine();
        newword = scanner.nextLine();
        service.updateWord(lang,num,newword);
    }
}
