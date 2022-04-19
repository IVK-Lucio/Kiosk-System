package PRG_Assign_2021;

/*a whole lot of imports in here, because obviously this class is for saving...into text files to be specific
yes and also reading from the text files. So most imports are of the package "java.io" which is basically input and output
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class Save {
    
    /*This "saveItems" method is for when we are RE_Stocking. This method has the Item Array as its parameters
    The item is entered by first mentioning how many items you may want to enter. that obviuosly being made
    possible by an Item array of the size you wish    
    */
    public static void saveItems(Item[]Item) throws IOException {
        File file = new File("items.txt");
        try (FileWriter w = new FileWriter(file.getAbsoluteFile(),true);//the "true" part lets you continue writing in the file
            BufferedWriter buw = new BufferedWriter(w);                 //....instead of overwriting everything
            PrintWriter out = new PrintWriter(buw))
        {
            if (!file.exists()) {//if the "items.txt" doesnt exist it automatically creates it
                file.createNewFile();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//for the format of the expiryDate
            for (int i = 0; i < Item.length; i++) {
            out.println();    
            out.printf("%s %s %s %.2f %s %s",Item[i].getName(),Item[i].getWeight(),Item[i].getQTY(), Item[i].getPrice(), sdf.format(Item[i].getExpiryDate()), Item[i].getTime());
            }
            
            buw.close();//closes the BufferedWriter
        } catch (IOException e) {
        }
    }
    
    /*This read method reads the content straight out of the specified "filepath" which is passed through its 
    parameter. I've called this method only once when wanting to read the Saved "Receipt.txt" of the customer
    
    "But how was the receipt saved?" I found it best if it is saved in the "SearchItem Class", afterall the
    item to be bought is search, realistically a BARCODE SCANNER is used but since this is a "Console" application, 
    searching for the item will just do
    */
    public static void read(String filePath) throws FileNotFoundException, ClassNotFoundException, IOException{
        BufferedReader br = null;

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(filePath));//text file read by the buffered reader
            while ((sCurrentLine = br.readLine()) != null) {//it will only print "out" something to the System thus the...
                System.out.println(sCurrentLine);           //....project's console if the readline is not null
            }
        } catch (IOException e) {
        }          
    }
}