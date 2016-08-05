import javax.inject.Named;
import java.io.*;

/**
 * Created by Elev1 on 2016-08-03.
 */

import javax.enterprise.context.SessionScoped;


import java.util.*;

/**
 * Created by Elev1 on 2016-08-02.
 */
@Named()
@SessionScoped
public class Reader implements Serializable{

    private String displayString = "";

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public Reader() {

    }
    //Metod som läser från texten



    /**
     Lösningsförslag till övning 5.4.


     Skapa ett program, MessageOfTheDay, som läser in en textfil med ett
     okänt antal rader, lagrar innehållet i en datastruktur av typen
     ArrayList och sedan matar ut en slumpvist vald rad. Varje rad i
     textfilen skall bestå av ett citat eller bevingat ord. Ge vid
     instantieringen ArrayList-objektet en kapacitet, som gör att något
     nytt fält inte behöver skapas under inläsningen. Uppskatta den
     förväntade storleken med hjälp av metoden length() i klassen File.

     Usage:

     java MessageOfTheDay <proverb file>
     */
    public List<String> MessageReader() {
         int ROW_LENGTH_ESTIMATE = 50;


        File file = new File("C:\\Users\\Elev1\\Databaser\\Kanjidic\\Kanjidic.txt");

        List<String> fileList = new ArrayList<String>((int) (file.length() / ROW_LENGTH_ESTIMATE) * 2);
        try{
        BufferedReader reader = new BufferedReader(new FileReader(file));
            for (String line = reader.readLine(); line != null;
                 line = reader.readLine())
                fileList.add(line);

	    /*
	       Random::nextInt returns a pseudorandom, uniformly
	       distributed int value between zero (inclusive) and
	       bound (exclusive).
	    */
            //System.out.format("%s%n", fileList.get(new Random().nextInt(fileList.size())));
        }catch(IOException e){

            //System.err.format("Could not read %s: %s%n", file, e);
            //System.exit(1);
        }
        return fileList;
        //Metod som tar ut en slumpmässig tecken från listan.
    }

    public String setMessage(){

        Random ran = new Random();
        int x = ran.nextInt(1000);
        String strToDisplay;
        List<String> theText = MessageReader();
        String anotherString= theText.toString();
        String [] words = anotherString.split(" ");
        ArrayList<String> result = new ArrayList<String>();



        for(int i = 0; i< words.length; i++) {
            if (words[i].substring(0, 1).startsWith("U")) {
                result.add(words[i]);
            }
        }
        strToDisplay = result.get(x);
        strToDisplay= strToDisplay.replaceFirst(String.valueOf(strToDisplay.charAt(0)),"");


        int unicodeNum = Integer.parseInt(strToDisplay, 16);


        String unicodeDisplay = Character.toString((char)unicodeNum);

      //  this.displayString = "&#x" + strToDisplay + ";";
        this.displayString = unicodeDisplay;
        return"index.xhtml";
    }

}
