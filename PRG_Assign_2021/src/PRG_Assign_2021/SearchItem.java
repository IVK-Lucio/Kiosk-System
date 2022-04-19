package PRG_Assign_2021;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Time;
import static PRG_Assign_2021.Main.itemList;
import static PRG_Assign_2021.Main.soldList;
import static PRG_Assign_2021.Main.lowList;
import static PRG_Assign_2021.Main.soldListSpecific;
import static PRG_Assign_2021.SearchItem.searchResults;
import static PRG_Assign_2021.Teller.loggedTeller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*This class is very vital to the operations of my assignment. 
This class doesnt have constructors. they would be quite useless because the attributes that are in this class
are already in the Item class. the attribues are just used here for solely searching. ( the substitute for a Bar Code 
Scanning in reality, but in this console project searching will do)
*/
public class SearchItem {
    String Name,Weight;
    Date Date;
    static ArrayList<Item> searchResults= new ArrayList<>();//filled by the items found to the specification the...
                                                            //...kind of items were being searched for in the first place
    
    //Setters and Getters provide access control to other classes,  cause our attributes are private here
    
    //Name getter
    public String getName() {
        return Name;
    }
    
    //Weight getter
    public String getWeight() {
        return Weight;
    }
    
    //Date getter    
    public Date getDate() {
        return Date;
    }
    
    //Date setter
    public boolean setDate(String DateText) {
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        try { 
            this.Date = sdt.parse(DateText);
            return true;
        } 
        catch (ParseException e) {
            System.err.println("ERROR: Invalid Date Format!");
            return false;
        }
    }
    
    //this method is for presenting heading titles BEFORE the "display method" in item class is called
    public static void presentFirst(String N){
        String A ="\n\n\t\t\tList of all the ";
        System.out.println(A.concat(N));
        System.out.println((char)27 + "[36m----------------------------------------------------------------------------------------------------------"+(char)27 + "[0m");
        System.out.println(" NO  ITEM MAME             | WEIGHT         | QUANTITY          | PRICE            DATE & TIME");
        System.out.println((char)27 + "[36m----------------------------------------------------------------------------------------------------------"+(char)27 + "[0m");
    }
      
    //a simple line to finish off the table AFTER the "display method" in item class is called
    public static void presentLast(){
        System.out.println("\n----------------------------------------------------------------------------------------------------------");
    }
    
    /*this is called from the Main class, where theres a menu.
    - this method is so that the right title is passed through the "presentFirst method" according to the "if()"statements
    - inside this method the "display" method in Item class is called depended on the "if()" still
    */    
    public static void Display(String display) {
        if("available".equals(display)){
            String N = "ITEMS AVAILABLE";//a possible title dependent on the if
            presentFirst(N);
            
            for(int i = 0; i < itemList.size(); i++) {
                itemList.get(i).display(i);
            }
            presentLast();
        }else if("sold".equals(display)){
            String N = "ITEMS SOLD"; //a possible title
            presentFirst(N);
            
            for(int i = 0; i < soldList.size(); i++) {
                soldList.get(i).display(i);
            }
                presentLast();
        }else if("low".equals(display)){
            String N = "ITEMS RUNNING LOW"; //a possible title
            presentFirst(N);
            
            for(int i = 0; i < lowList.size(); i++) {
                lowList.get(i).display(i);
            }
                presentLast();
        }else{
            String A =" "+loggedTeller.get(0).getLastName() + " " +loggedTeller.get(0).getFirstName();
            String N = "ITEMS SOLD BY"; //a possible title
            presentFirst(N+A); // this is to list items sold, but specifying by the logged teller
            
            for(int i = 0; i < soldListSpecific.size(); i++) {
                soldListSpecific.get(i).display(i);
            }
                presentLast();
        }
    }
    
    //this method simply prompts the user to enter the details of the desired item    
    public void getSearchData() {
        Scanner cin = new Scanner(System.in);          
        System.out.println("\n\n\t\tSELECT ITEM");
        System.out.print("Item Name: ");                        
        Name = cin.next();
        System.out.print("Weight: ");                          
        Weight = cin.next(); 
        System.out.print("DATE(YYYY-MM-DD): ");        
        String tempDate = cin.next();
        while(!setDate(tempDate)) {//to assure that you enter the date in the right format
            System.out.print("DATE(YYYY-MM-DD): ");       
            tempDate = cin.next();
        }
    }
    
    /*Weve just gotten the Search data, so we are going to use it to s if we find any items with that criteria
    the items found will be "added" to the searchResults arraylist */    
    public boolean getSearchResults() {
        for(int i = 0; i < itemList.size(); i++) {
            if(itemList.get(i).getName().equalsIgnoreCase(Name) && itemList.get(i).getWeight().equalsIgnoreCase(Weight)) {
                if(itemList.get(i).getExpiryDate().equals(Date)) {
                    searchResults.add(itemList.get(i));               
                }
            }
        }
        if(searchResults.isEmpty()) {                
            return false;
        } else {
            return true;
        }
    }
    
    /*only if the "boolean getSearchResults" returns true, true that the searchResults array is not empty. 
    that something is found... only then is this method called.*/
    public void displayResults() {
        for(int i = 0; i < searchResults.size(); i++) {
                System.out.print((i+1) + ". ");
                searchResults.get(i).display(i);     
        }                
    } 
        
    int buyQTY;
    double tendered;
    int noArray[]= new int[5];    
    
    /*
    
    */
    public void Buying(){
        Scanner input = new Scanner(System.in);
        double total = 0, total1 = 0;
            
        System.out.println("Name        QTY     Price     Total");
        for(int i = 0; i < searchResults.size(); i++) {
            System.out.println("How much "+ searchResults.get(i).getName() +" do you want to purchase?");
            buyQTY = input.nextInt();
            while(searchResults.get(i).getQTY()<buyQTY){
                System.err.printf("We do not have that much %s \n",searchResults.get(i).getName());
                System.err.printf("Maximum Limit is %d \n",searchResults.get(i).getQTY());
                System.out.println("How much "+ searchResults.get(i).getName() +" do you want to purchase?");
                buyQTY = input.nextInt();
            }
            while(buyQTY<=0){
                System.err.printf("You cannot Purchase 0 Quantity of %s \n",searchResults.get(i).getName());
                int n =1;
                System.err.printf("Start from %d Quantity \n",n);
                System.out.println("How much "+ searchResults.get(i).getName() +" do you want to purchase?");
                buyQTY = input.nextInt();
            }
            noArray[i] = buyQTY;
            int x = searchResults.get(i).getQTY() - buyQTY;
            System.out.println("New Quantity of "+searchResults.get(i).getName()+" :" + x);
            total += (searchResults.get(i).getPrice() * buyQTY);
            total1 += (searchResults.get(i).getPrice() * buyQTY);
            System.out.printf("%-11s %-7s %-9s %.2f\n",searchResults.get(i).getName(),buyQTY,searchResults.get(i).getPrice(),total1);
            total1 = 0;
               
        }
        System.out.println("________________________________________");
        double vat = total * 15/100;
        System.out.print("               VAT@15%        ");
        System.out.printf("%.2f\n",vat);
        double finaltotal = total + vat;
        System.out.printf("Total                         %.2f\n",finaltotal);
        System.out.print("Tendered                     :");
        tendered = input.nextDouble();
        while(tendered<finaltotal)
        {
            System.err.println("Amount not Enough.");
            System.out.print("Tendered                     :");
            tendered=input.nextDouble();
        }
        double change = tendered - finaltotal;
        System.out.println("                              ==========");
        System.out.printf("Change                        %.2f\n",change);
        System.out.println("________________________________________");
        
        /*Simple if statements below with "do-while" inside them
        I have used this for the change dispersion*/
        if(change>100){
            do{change -= 100;
            hundreds += 1;}while (change>100);
        }
        if(change>10){
            do{change -= 10;
            tens += 1;}while (change>10);
        }
        if(change>5){
            do{change -= 5;
            fives += 1;}while (change>5);
        }
        if(change>1){
            do{change -= 1;
            ones += 1;}while (change>1);
        }
        if(change>0.50){
            do{change -= 0.50;
            fifthyCents += 1;}while (change>0.50);
        }
        if(change>0.10){
            do{change -= 0.10;
            tenCents += 1;}while (change>0.10);
        }
        if(change>0.05){
            do{change -= 0.05;
            fiveCents += 1;}while (change>0.05);
        }
      
    }
    int hundreds = 0,tens = 0,fives = 0,ones = 0,fifthyCents = 0,tenCents = 0,fiveCents = 0;
    
    public  void addSold() {
        File file = new File("soldItems.txt");
        try (FileWriter w = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter buw = new BufferedWriter(w);
            PrintWriter out = new PrintWriter(buw))
        {
            if (!file.exists()) {
                file.createNewFile();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < searchResults.size(); i++) {
            out.println(); 
            int s = loggedTeller.size()-1;
            out.printf("%s %s %s %.2f %s %s SOLD BY CASHIER %s %s",searchResults.get(i).getName(),searchResults.get(i).getWeight(),buyQTY, searchResults.get(i).getPrice(), sdf.format(calender.getTime()), t.toLocalTime().format(DateTimeFormatter.ISO_TIME), loggedTeller.get(s).getLastName(),  loggedTeller.get(s).getFirstName());
            }
            
            buw.close();
        } catch (IOException e) {
        }
    }
    
    public  void DecreaseQTY() {
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("items.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            
            String strLine;
            StringBuilder fileContent = new StringBuilder();
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                
                String tokens[] = strLine.split(" ");
                if (tokens.length > 0) {
                    // Here tokens[0] will have value of ID
                    int s = searchResults.size()-1;
                    int x = searchResults.size();
                    if (tokens[0].equals(searchResults.get(s).getName())) {
                        String newLine = null;
                        for(int i = 0; i < searchResults.size(); i++){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        int newQTY = searchResults.get(i).getQTY() - noArray[i];
                        newLine = searchResults.get(i).getName() + " " + searchResults.get(i).getWeight() + " " + newQTY + " " + searchResults.get(i).getPrice() + " " + sdf.format(searchResults.get(i).getExpiryDate()) + " " + searchResults.get(i).getTime();
                        }
                        fileContent.append(" \n").append(newLine);
                        fileContent.append("\n");
                    } else {
                        // update content as it is
                        fileContent.append(" \n").append(strLine);
                        fileContent.append("\n");
                    }
                }
            }
   
        // Now fileContent will have updated content , which you can override into file
            FileWriter fstreamWrite = new FileWriter("items.txt");
            try (BufferedWriter out = new BufferedWriter(fstreamWrite)) {
                out.write(fileContent.toString());
                out.close();
            }
        } catch (IOException | NoSuchElementException e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    /*After the buying method is called its only best the Receipt is saved and generated ofcourse
    So most of the values to be saved in this method have already been set in the Buying method
    */
    
    void saveReceipt() {
        File file = new File("Receipt.txt");
        try (FileWriter w = new FileWriter(file.getAbsoluteFile(),false);//"false" overwrites the text file witha new....
            BufferedWriter buw = new BufferedWriter(w); //...that is good cause we've already got a text file for sold items
            PrintWriter out = new PrintWriter(buw))
        {
            if (!file.exists()) {//if the "Receipt.txt" doesn't exist, its automatically created
                file.createNewFile();
            }
            
            double total = 0,total1 = 0;
            
            out.println("*          Target Kiosk           *");//Receipt layout of choice
            out.println("*      Groove Mall Shop 24        *");
            out.println("***********************************");
            out.println("Name        QTY     Price     Total");
            
            for(int i = 0; i < searchResults.size(); i++) {
                total += (searchResults.get(i).getPrice() * noArray[i]);
                total1 += (searchResults.get(i).getPrice() * noArray[i]);
                out.printf("%-11s %-7s %-9s %.2f",searchResults.get(i).getName(),noArray[i],searchResults.get(i).getPrice(),total1);  
                total1 = 0;
                out.println("");
            }
            
            out.println("------------------------------------");
            double vat = total * 15/100;//vat is 15 percent of the total
            out.print("               VAT@15%        ");
            out.printf("%.2f",vat);
            out.println("");
            double finaltotal = total + vat; //the vat is added to the total
            out.printf("Total                         %.2f",finaltotal);
            out.println("");
            out.printf("Tendered                      %.2f",tendered);
            out.println("");
            double change = tendered - finaltotal;//there's no way the change will ever be negative. that is made sure...
                                                  //...through the buying method through exception handling with a "while"
            out.printf("Change                        %.2f",change);//change to 2decimal places
            out.println("");
            out.println("************************************");
            out.println("");
            //the date and time the Receipt is being generated
            out.println("  Date: " + dateSlipformatter.format(calender.getTime()) +"/" + t.toLocalTime().format(DateTimeFormatter.ISO_TIME));
            
            //loggedTeller array is populated when the teller logs in, the receipt needs Teller details obviously
            int s = loggedTeller.size()-1;
            out.println("  Cashier: "+ loggedTeller.get(s).getLastName() + " "+ loggedTeller.get(s).getFirstName());
            out.println("************************************");
            out.println("");
            out.println("     Thank You For Your Support     ");
            out.println("         Please call Again          ");
            out.println("************************************");
            out.println("Your change is disbursed as follows: N$100 X "+hundreds+", N$10 X"+ tens + ", N$5 X "+ fives + ", N$1 X "+ones + ", 50Cents X "+fifthyCents + ", 10Cents X "+tenCents + ", 5Cents X "+fiveCents);
            buw.close();
        } catch (IOException e) {
        }
    }
    
    Time t = Time.valueOf(LocalTime.now());
    Calendar calender = Calendar.getInstance();
    SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd"); 
    SimpleDateFormat dateSlipformatter = new SimpleDateFormat(" dd' - 'MMMMMMMMM' - 'yyyy");

    
    
    /*this method is for changing the price. But of course of only the item of chosen to change. You will select the item
    by searching it, and using the searchResults array then can the BufferWriter be able to append the "item.txt" file
    */
    public void ChangePrice(double newPrice) {
        try{
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream("items.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            
            String strLine;
            StringBuilder fileContent = new StringBuilder();
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                
                String tokens[] = strLine.split(" ");
                if (tokens.length > 0) {
                    
                    int s = searchResults.size() - 1;//here i needed a function of the "int i = 0" found in a "for" loop
                                                     //...and thus instead this achieved that
                                                     
                    // Here tokens[0] will have value of Name
                    //token[1] of QTY and so on. this depends on how the items' attributes were saved in the first place                             
                    if (tokens[0].equals(searchResults.get(s).getName())) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        tokens[1] = searchResults.get(s).getWeight();//token[1] is QTY...i do not further do this though... instead I used getters of the searchResults. Diversity, the beauty of Java
                        String newLine = tokens[0] + " " + tokens[1] + " " + searchResults.get(s).getQTY() + " " + newPrice + " " + sdf.format(searchResults.get(s).getExpiryDate()) + " " + searchResults.get(s).getTime();
                        fileContent.append(" \n").append(newLine);//the new line is inserted, replacing tthe old Price
                        fileContent.append("\n");
                    } else {
                        // update content as it is
                        fileContent.append(" \n").append(strLine);//the old lines are kept as they are if "token[0]"
                        fileContent.append("\n");                //...did not equal to Name
                    }
                }
            }
            // Now fileContent will have updated content , which you can override into file
            FileWriter fstreamWrite = new FileWriter("items.txt");
            try (BufferedWriter out = new BufferedWriter(fstreamWrite)) {
                out.write(fileContent.toString());
                out.close();
            }
        } catch (IOException | NoSuchElementException e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
