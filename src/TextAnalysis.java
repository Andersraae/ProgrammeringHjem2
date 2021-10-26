import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TextAnalysis {

    //Constructors
    private String fileName;
    private int maxWords;

    private int numOfWords = 0;
    private int numOfUnique = 0;
    private String lastWord = "";
    private int numOfRep = 0;

    public TextAnalysis(String sourceFileName, int maxNoOfWords){
        this.fileName = sourceFileName;
        this.maxWords = maxNoOfWords;
        readFile();
    }

    //Private methods

    public static void main(String[] a){
        TextAnalysis ta = new TextAnalysis("text17_00.txt", 50);
        System.out.println("word count = " + ta.wordCount());
        System.out.println("different words = " + ta.getNoOfDifferentWords());
        System.out.println("repetitions = " + ta.getNoOfRepetitions());
    }

    private void readFile(){    // Læser filen
        try {
            File file = new File(this.fileName);
            Scanner scandi = new Scanner(file); // Opretter en scanner "scandi" som scammer filen
            while(scandi.hasNextLine()){
                if (this.numOfWords == this.maxWords){
                    break;
                }
                String line = scandi.nextLine();
                String[] tokens = line.split("[^a-zA-Z]+");

                //Number of words, repetitions and unique words
                int newWords = this.numOfWords + tokens.length;
                int loopcount;
                if (newWords >= this.maxWords) {
                    loopcount = this.maxWords - newWords;
                } else {
                    loopcount = tokens.length;
                }


                ArrayList<String> words = new ArrayList<>();
                for (int i = 0; i < loopcount; i++) {
                    String theWord = tokens[i].toLowerCase();
                    if (!words.contains(theWord)){
                        words.add(theWord);
                        this.numOfUnique++;
                    }
                    if (this.lastWord.equals(theWord)) {
                        this.numOfRep++;
                    }
                    this.lastWord = theWord;
                    this.numOfWords++;
                }

            }
            scandi.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    //Public methods

    public int wordCount(){
        return this.numOfWords;
    }

    public int getNoOfDifferentWords(){
        return this.numOfUnique;
    }
    public int getNoOfRepetitions(){
        return this.numOfRep;
    }
}
