import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextAnalysis {

    //Constructors
    private String fileName;
    private int maxWords;

    private int numOfWords = 0;
    private int numOfUnique = 0;
    private String lastWord = "";
    private int numOfRep = 0;

    private ArrayList<String> words = new ArrayList<>();

    public TextAnalysis(String sourceFileName, int maxNoOfWords){
        this.fileName = sourceFileName;
        this.maxWords = maxNoOfWords;
        readFile();
    }

    //Private methods

    private void readFile(){    // Læser filen
        try {
            File file = new File(this.fileName);
            Scanner scandi = new Scanner(file); // Opretter en scanner "scandi" som scammer filen

            while(scandi.hasNextLine()){ //Løkken kører indtil at antal ord = max ord, ellers break
                if (this.numOfWords == this.maxWords){
                    break;
                }
                // line.split bryder en linje af i individuelle ord
                String line = scandi.nextLine();
                String[] tokens = line.split("[^a-zA-Z]+");

                // Vi konverterer arrayet tokens til en array list, så vi kan fjerne tomme strings.
                ArrayList<String> tokenArray = new ArrayList<>();
                for (int a = 0; a < tokens.length; a++){
                    if (!tokens[a].equals("")){
                        tokenArray.add(tokens[a]);
                    }
                }

                // Antallet af ord
                int newWords = this.numOfWords + tokenArray.size();
                int loopcount;
                if (newWords >= this.maxWords) {
                    loopcount = this.maxWords - newWords;
                } else {
                    loopcount = tokenArray.size();
                }

                // Antallet af unikke ord
                for (int i = 0; i < loopcount; i++) {
                    String theWord = tokenArray.get(i).toLowerCase();
                    if (!this.words.contains(theWord)){
                        this.words.add(theWord);
                        this.numOfUnique++;
                    }

                    // Antallet af repetitions
                    if (this.lastWord.equals(theWord)) {
                        this.numOfRep++;
                    }
                    this.lastWord = theWord;
                    this.numOfWords++;
                }

            }
            scandi.close();
        } catch (FileNotFoundException e) {
            // Programmet fejler og printer "File not found", hvis der ikke er en fil.
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
