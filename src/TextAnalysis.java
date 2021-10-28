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
    private void readFile(){ // Læser filen
        try {
            File file = new File(this.fileName);
            Scanner scandi = new Scanner(file); // Opretter en scanner "scandi" som scammer filen

            while(scandi.hasNextLine()){ //Løkken kører indtil at antal ord = max ord, ellers break
                if (this.numOfWords == this.maxWords){
                    break;
                }
                // line.split bryder en string op i individuelle ord
                String line = scandi.nextLine();
                String[] tokens = line.split("[^a-zA-Z]+");

                // Vi konverterer arrayet tokens til en array list, så vi kan fjerne tomme strings.
                ArrayList<String> tokenArray = new ArrayList<>();
                for (int a = 0; a < tokens.length; a++){
                    if (!tokens[a].equals("")){ //Hvis næste token ikke er en tom string
                        tokenArray.add(tokens[a]); //Tilføj til tokenArray
                    }
                }

                // Antallet af ord
                int newWords = this.numOfWords + tokenArray.size();
                int loopcount; //Hvor mange ord der mangler til 'numOfWords' når 'maxWords'
                if (newWords >= this.maxWords) {
                    loopcount = this.maxWords - newWords;
                } else {
                    loopcount = tokenArray.size(); //.size() giver længden
                }

                // Antallet af unikke ord
                for (int i = 0; i < loopcount; i++) {
                    String theWord = tokenArray.get(i).toLowerCase(); //Konverter til kun små bogstaver (non-case-sensitive)
                    if (!this.words.contains(theWord)){
                        this.words.add(theWord);
                        this.numOfUnique++;
                    }

                    // Antallet af gentagelser
                    if (this.lastWord.equals(theWord)) { //Hvis sidste ord er ens
                        this.numOfRep++;
                    }
                    this.lastWord = theWord; //Gemmer dette ord til næste omgang af løkken
                    this.numOfWords++;
                }

            }
            scandi.close(); //Lukker scanneren
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
