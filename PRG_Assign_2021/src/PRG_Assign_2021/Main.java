package PRG_Assign_2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static PRG_Assign_2021.SearchItem.searchResults;
import static PRG_Assign_2021.Teller.loggedTeller;

public class Main {
    // main method begins program execution                                             //imports from java.io
    public static void main(String[] args)throws IOException, FileNotFoundException, ClassNotFoundException, InterruptedException, ParseException{
        Scanner input = new Scanner(System.in);
        
        int logChoice,choice,logInAttemps=0;
        String user,user1,pass,pass1,again = null;
        
        System.out.println((char)27 + "[36m\t\t█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█"+(char)27 + "[0m");
        System.out.println((char)27+ "[0m"+(char)27 + "[34m\t\t                                  WELCOME                               "+(char)27+ "[0m");
        System.out.println((char)27+ "[0m"+(char)27 + "[34m\t\t                                   to the                               "+(char)27+ "[0m");
        System.out.println((char)27+ "[0m"+(char)27 + "[34m\t\t                                 NUST KIOSK                            "+(char)27+ "[0m");
        System.out.println((char)27 + "[36m\t\t█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█"+(char)27+ "[0m");
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat dateformatter = new SimpleDateFormat(" dd MMMMMMMMM',' yyyy ");
        System.out.println((char)27 + "[31m                                           Date - "+ (char)27 + "[0m"+ dateformatter.format(calender.getTime()));
        
        
        for(int chooseLogIn=1;chooseLogIn==1;){
            
        System.out.println((char)27 + "[36m\t\t▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀"+(char)27 + "[0m");
    	System.out.println("\t\t1. Login as Admin                ");
        System.out.println("\t\t2. Login as Teller/Shop assistant");
        System.out.println("\t\t3. Quit                          ");
        System.out.println((char)27 + "[36m\t\t▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀"+(char)27 + "[0m");
    			System.out.print((char)27 +"[31m\t\tEnter Log In Choice: "+(char)27+ "[0m");
    			logChoice = input.nextInt();
                        
        if(logChoice==1){
            while(logInAttemps<3){
                System.out.println((char)27 +"[36m\t\t____________________________________________"+(char)27 +"[0m");
                System.out.println((char)27 +"[36m\t\t_______________LOG IN AS ADMIN______________"+(char)27 +"[0m");
                System.out.print((char)27 +"[36m\t\tEnter Username: "+(char)27 +"[0m");
                user = input.next();
                System.out.print ((char)27 +"[36m\t\tEnter Password: "+(char)27 +"[0m");
                pass = input.next();
                System.out.println((char)27 +"[36m\t\t____________________________________________"+(char)27 +"[0m");
                          
            
                    if(user.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin")){
                        System.out.println("===========================================================================================");
                        System.out.println(" _________________________________________________________________________________________");
                        System.out.println("|                      Successfully Logged in as ADMIN                                    |");
                        System.out.println(" _________________________________________________________________________________________");
                        System.out.println("");
                        
                        for(int admin=1;admin==1;){ 
                            
                        System.out.println(" ___________________________________ADMIN PRIVILEDGES_____________________________________");
                        System.out.println("| 1. Restock                                                                              |");
                        System.out.println("| 2. Change Prices                                                                        |");
                        System.out.println("| 3. Manage Tellers                                                                       |");
                        System.out.println("| 4. Print out a summary of all items in stock                                            |");
                        System.out.println("| 5. Print out only items that need restocking                                            |");
                        System.out.println("| 6. Exit                                                                                 |");
                        System.out.println(" _________________________________________________________________________________________");
                        
                        System.out.print((char)27 +"[31m\nEnter Admin Choice: "+(char)27+ "[0m");
    			choice = input.nextInt();
				System.out.println((char)27 +"[31m\f"+ (char)27 +"[0m");
                        switch (choice) {
                            case 1:
                                do{
                                    System.out.println (" _________________ RESTOCK ___________________");
                                    System.out.println ("|(1) Add new Items to the Shop                |");
                                    System.out.println ("|(2) Exit                                     |");
                                    System.out.println ("|_____________________________________________|");
                                    for(int restock=1;restock==1;){
                                        System.out.print("\nEnter Choice: ");
                                        choice = Integer.parseInt(input.next());
                                        switch (choice) {
                                            case 1:
                                                itemList.clear();
                                                System.out.println((char)27 + "Number of items you want to add:" +(char)27+ "[0m");
                                                int n = input.nextInt();
                                                Item[] Item = new Item[n];      
                                                addITEM(Item);
                                                Save.saveItems(Item);
                                                populateItemList();
                                                SearchItem test = new SearchItem();
                                                String display = "available";
                                                SearchItem.Display(display);
                                                itemList.clear();
                                                restock=0;
                                                again = "n";
                                                break;
                                            case 2:
                                                restock=0;
                                                again="n";
                                                System.out.println(".....Exiting");
                                                break;
                                            default:         
                                                System.out.println("Invalid Input!");
                                                restock=1;
                                                break;
                                        }
                                } 
                    }while(again.equalsIgnoreCase("y"));
                                break;
                                
                    case 2:
                                do{
                                    System.out.println (" _____________ CHANGE PRICES _________________");
                                    System.out.println ("|(1) Change Prices                            |");
                                    System.out.println ("|(2) Exit                                     |");
                                    System.out.println ("|_____________________________________________|");
                                    for(int change=1;change==1;){
                                        System.out.print("\nEnter Choice: ");
                                        choice = Integer.parseInt(input.next());
                                        switch (choice) {
                                            case 1:
                                                itemList.clear();
                                                populateItemList();
                                                int ans = 1;
                                                while(ans!=0) {
                                                    SearchItem test = new SearchItem();
                                                    String display = "available";
                                                    SearchItem.Display(display);
                                                    test.getSearchData();
                                                    boolean found = test.getSearchResults();
                                                    if(found) {
                                                        System.out.println("\n\t\t\tSEARCH RESULTS\n");
                                                        test.displayResults();
                                                        System.out.print("\nNew Price?: ");
                                                        double newPrice =  input.nextDouble();
                                                        test.ChangePrice(newPrice);
                                                        System.out.println("________________________________UPDATED READINGS_______________________________________");
                                                        itemList.clear();
                                                        populateItemList();
                                                        SearchItem.Display(display);
                                                        searchResults.clear();
                                                        ans = 0;
                                                    }else
                                                    {
                                                        System.out.print("Sorry! There are no item with " + test.getName() + " and with the weight of " + test.getWeight() + " with date ");
                                                        System.out.printf("%tD%n", test.getDate());
                                                        System.out.print("\nDo you want to search again?? (Enter 1 to continue or 0 to exit): ");
                                                        ans = input.nextInt();               
                                                    }
                                                }
                                                itemList.clear();
                                                change=0;
                                                again="n";
                                                break;
                                            case 2:
                                                change=0;
                                                again="n";
                                                System.out.println(".....Exiting");
                                                break;
                                            default:         
                                                System.out.println("Invalid Input!");
                                                change=1;
                                                break;
                                        }
                                }
                    }while(again.equalsIgnoreCase("y"));
                                break;            
                    case 3:
                                do{
                                    System.out.println (" ______________ MANAGE TELLER_________________");
                                    System.out.println ("|(1) Add Teller                               |");
                                    System.out.println ("|(2) Delete Teller                            |");
                                    System.out.println ("|(3) Display all Tellers                      |");
                                    System.out.println ("|(4) Exit                                     |");
                                    System.out.println ("|_____________________________________________|");
                                    for(int teller=1;teller==1;){
                                        System.out.print("\nEnter Choice: ");
                                        choice = Integer.parseInt(input.next());
                                        switch (choice) {
                                            case 1:
                                                tellerList.clear();
                                                populateTellerList();
                                                Teller teller1 = new Teller();
                                                teller1.createNewTeller();
                                                tellerList.clear();
                                                teller=0;
                                                again="n";
                                                break;
                                            case 2:
                                                tellerList.clear();
                                                populateTellerList();
                                                Teller test = new Teller();
                                                test.deleteTeller();
                                                tellerList.clear();
                                                teller=0;
                                                again="n";
                                                break;    
                                            case 3:
                                                tellerList.clear();
                                                populateTellerList();
                                                test = new Teller();
                                                test.presentFirst();
                                                tellerList.clear();
                                                teller=0;
                                                again="n";
                                                break;    
                                            case 4:
                                                teller=0;
                                                again="n";
                                                System.out.println(".....Exiting");
                                                break;
                                            default:         
                                                System.out.println("Invalid Input!");
                                                teller=1;
                                                break;
                                        }
                                }
                    }while(again.equalsIgnoreCase("y"));
                                break;
                    case 4:
                                do{
                                    System.out.println (" __Print out a summary of all items in stock__");
                                    System.out.println ("|(1) Print out                                |");
                                    System.out.println ("|(2) Exit                                     |");
                                    System.out.println ("|_____________________________________________|");
                                    for(int summaryAll=1;summaryAll==1;){
                                        System.out.print("\nEnter Choice: ");
                                        choice = Integer.parseInt(input.next());
                                        switch (choice) {
                                            case 1:
                                                itemList.clear();
                                                populateItemList();
                                                String display = "available";
                                                SearchItem.Display(display);
                                                itemList.clear();
                                                summaryAll=0;
                                                again="n";
                                                break;    
                                            case 2:
                                                summaryAll=0;
                                                again="n";
                                                System.out.println(".....Exiting");
                                                break;
                                            default:         
                                                System.out.println("Invalid Input!");
                                                summaryAll=1;
                                                break;
                                        }
                                }
                    }while(again.equalsIgnoreCase("y"));
                                break;
                    case 5:
                                do{
                                    System.out.println (" __Print out only items that need restocking__");
                                    System.out.println ("|(1) Restock Items                            |");
                                    System.out.println ("|(2) Exit                                     |");
                                    System.out.println ("|_____________________________________________|");
                                    for(int needRestock=1;needRestock==1;){
                                        System.out.print("\nEnter Choice: ");
                                        choice = Integer.parseInt(input.next());
                                        switch (choice) {
                                            case 1:
                                                lowList.clear();
                                                populateLow();
                                                String display = "low";
                                                SearchItem.Display(display);
                                                lowList.clear();
                                                needRestock=0;
                                                again="n";
                                                break;   
                                            case 2:
                                                needRestock=0;
                                                again="n";
                                                System.out.println(".....Exiting");
                                                break;
                                            default:         
                                                System.out.println("Invalid Input!");
                                                needRestock=1;
                                                break;
                                        }
                                }
                    }while(again.equalsIgnoreCase("y"));
                    break;
                    case 6:
                        admin = 0;
                        again="n";
                        System.out.println(".....Closing Admin Menu!!");
                        chooseLogIn=1;
                        break;            
                    default:         
                        System.out.println("Invalid Input!");
                        admin=1;
                        break;
                        }
                        }
                        break;    
                    }else{
    		System.out.println((char)27 +"[31m\t\tInvalid"+(char)27 +"[36m Admin "+(char)27 +"[0m"+(char)27 +"[31mUser or Password!!"+(char)27 +"[0m");
    		logInAttemps++;
   		}
            } 
        }
        
        else if(logChoice==2){
            while(logInAttemps<3){
                System.out.println((char)27 +"[33m\t\t______LOG IN AS Teller/Shop assistant_______"+ (char)27 +"[0m");
                System.out.print((char)27 +"[33m\t\tEnter Username: "+(char)27 +"[0m");
                user1 = input.next();
                System.out.print ((char)27 +"[33m\t\tEnter Password: "+(char)27 +"[0m");
                pass1 = input.next();
                System.out.println((char)27 +"[33m\t\t____________________________________________"+(char)27 +"[0m");
                
                boolean isValid = false;
                Teller log = new Teller(user1, pass1);
                populateTellerList();
                isValid = log.isValidTeller();
            
                if(isValid == true){
                        System.out.println(" _________________________________________________________________________________");
                        System.out.println("|                     Successfully Logged in as TELLLER                           |");
                        System.out.println(" _________________________________________________________________________________");
                        System.out.println("");
                        
                        for(int teller=1;teller==1;){                         
                        
                        System.out.println(" _______________________________TELLER PRIVILEDGES________________________________");
                        System.out.println("| 1.Sell Items                                                                    |");
                        System.out.println("| 2.Print out a summary of items sold during all his/her sales                    |");
                        System.out.println("| 3.Exit                                                                          |");  
                        System.out.println(" _________________________________________________________________________________");
                           
                        System.out.print((char)27 +"[31m \nEnter Teller Choice: "+(char)27+ "[0m");
    			choice = Integer.parseInt(input.next());
				System.out.println("\f");
                                System.out.println("\u000c");
                        switch (choice) {
                            case 1:
                                do{
                                    System.out.println (" _____________ POINT OF SALE _________________");
                                    System.out.println ("|(1) Sell Items                               |");
                                    System.out.println ("|(2) Exit                                     |");
                                    System.out.println ("|_____________________________________________|");
                                    for(int sell=1;sell==1;){
                                        System.out.print("\nEnter Choice: ");
                                        choice = Integer.parseInt(input.next());
                                        switch (choice) {
                                            case 1:
                                                itemList.clear();
                                                searchResults.clear();
                                                populateItemList();
                                                int ans = 1,done =0;
                                                while(ans!=0) {
                                                    SearchItem test = new SearchItem();
                                                    String display = "available";
                                                    SearchItem.Display(display);
                                                    System.out.print("No of Items :");
                                                    int no = input.nextInt();
                                                    for(int i = 0; i < no; i++){
                                                        test.getSearchData();
                                                        boolean found = test.getSearchResults();
                                                        if(found) {
                                                        System.out.println("\n\t\t\tSEARCH RESULTS\n");
                                                        test.displayResults(); 
                                                        done = 0;
                                                        }else
                                                        {
                                                        System.out.print("Sorry! There are no item with " + test.getName() + " and with the weight of " + test.getWeight() + " with date ");
                                                        System.out.printf("%tD%n", test.getDate());
                                                        System.out.print("\nDo you want to search again?? (Enter 1 to continue or 0 to exit): ");
                                                        ans = input.nextInt();  
                                                        if(ans == 1 && searchResults.size()>0){
                                                            done = 0;
                                                        }else{
                                                            done = 1;
                                                        }
                                                    }
                                                    }
                                                    if(done==0){
                                                        System.out.println("\n\n________________BUYING__________________\n");
                                                        test.Buying();
                                                        test.saveReceipt();
                                                        test.DecreaseQTY();
                                                        test.addSold();
                                                        System.out.println("---------------------------------------------------------------------------");
                                                        Save.read("Receipt.txt");
                                                        System.out.println("---------------------------------------------------------------------------");
                                                        ans = 0;
                                                    }
                                                }
                                                itemList.clear();
                                                searchResults.clear();
                                                sell=0;
                                                again="n";
                                                break;
                                            case 2:
                                                sell=0;
                                                again="n";
                                                System.out.println(".....Exiting");
                                                break;
                                            default:         
                                                System.out.println("Invalid Input!");
                                                sell=1;
                                                break;
                                        }
                                }                            
                    }while(again.equalsIgnoreCase("y"));
                                break;
                                
                    case 2:
                        do{
                                System.out.println (" _____________ SUMMARY OF ITEMS SOLD__________");
                                System.out.println ("|(1) Items Sold by Specific Cashier           |");
                                System.out.println ("|(2) All Items Sold                           |");
                                System.out.println ("|(2) Exit                                     |");
                                System.out.println ("|_____________________________________________|");
                                
                                    for(int sold=1;sold==1;){
                                        System.out.print("\nEnter Choice: ");
                                        choice = Integer.parseInt(input.next());
                                        
                                        switch (choice) {
                                            case 1:
                                                populateSoldSpecific();
                                                String display = "soldSpecific";
                                                SearchItem.Display(display);
                                                soldListSpecific.clear();
                                                sold=0;
                                                again="n";
                                                break;
                                            case 2:
                                                populateSoldList();
                                                display = "sold";
                                                SearchItem.Display(display);
                                                soldList.clear();
                                                sold=0;
                                                again="n";
                                                break;
                                            case 3:
                                                sold=0;
                                                again="n";
                                                System.out.println(".....Exiting");
                                                break;
                                            default:         
                                                System.out.println("Invalid Input!");
                                                sold=1;
                                                break;
                                        }
                                }
                            }while(again.equalsIgnoreCase("y"));
                                break;            
                    case 3:
                        teller = 0;
                        again = "n";
                        loggedTeller.clear();
                        System.out.println(".......Closing Teller Menu");
                        chooseLogIn = 1;
                        break;
                    default:         
                    System.out.println("Invalid Input!");
                    teller=1;
                    break;
                   }
                   }                        
                   break;    
                   }else{
    	        System.out.println((char)27 +"[31m\t\tInvalid"+(char)27 +"[33m Teller "+ (char)27 +"[0m" + (char)27 +"[31mUser or Password!!" + (char)27 +"[0m");
    	        logInAttemps++;
   		}
            }        
        }
        else if(logChoice==3){
            chooseLogIn=0;
            again="n";
            System.out.println("\t\tThank You and Come Again!!");
        }
	else{
            chooseLogIn=1;
        }
        }
    }
    
    
    private static void addITEM(Item[] Item) throws ParseException{
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < Item.length; i ++){
            int no = i+1;
            System.out.println("-----------------ITEM NO: ["+no+"] -----------------------");
            System.out.print("Item Name: ");
            String name= input.next();
            System.out.print("Item Weight: ");
            String weight= input.next();
            System.out.print("Item Quantity: ");
            int QTY= input.nextInt();
            System.out.print("Item Price: ");
            double price = input.nextDouble();
            System.out.print("Company Name: ");
            String companyName = input.next();
            System.out.print("Street Name: ");
            String streetName = input.next();
            System.out.print("Location: ");
            String location = input.next();
            System.out.print("DATE(YYYY-MM-DD): ");       
            String tempDate = input.next();
            System.out.print("TIME(##:##): ");
            String tempTime = input.next();
            Item[i] = new Item(name,weight, QTY, price, tempDate, tempTime,companyName, streetName, location);
            System.out.println("-------------------------------------------------------");
        }
    }   
    
    static ArrayList<Item> itemList= new ArrayList<>();    
    static ArrayList<Teller> tellerList= new ArrayList<>();
    static ArrayList<Item> lowList= new ArrayList<>();
    static ArrayList<Item> soldList= new ArrayList<>();
    static ArrayList<Item> soldListSpecific= new ArrayList<>();

   static void populateTellerList() {
        String fileName = "tellers.txt";                 
        File file = new File(fileName);
        try {
            Scanner read = new Scanner(file);           
            
            while(read.hasNext()) {                
                String firstName = read.next(); 
                String lastName = read.next();
                String username = read.next();
                String password = read.next();
                
                Teller teller = new Teller(firstName, lastName, username, password);
                tellerList.add(teller);                    
            }
            
        } catch (FileNotFoundException ex) {                //If the file could not be found
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: Item data file not found!");
        }  
    }

    static void populateItemList(){
        String fileName = "items.txt";                 
        File file = new File(fileName);
        try {
            Scanner read = new Scanner(file);           
            
            while(read.hasNext()) {                
                String name = read.next(); 
                String weight = read.next();
                int QTY = read.nextInt();
                double price = read.nextDouble();
                String tempDate = read.next();
                String tempTime = read.next();
                
                if(QTY>0){ //This is to assure that the itemList view is NOT populated with items that have...
                            //....complitely run out
                Item item = new Item(name, weight, QTY, price,tempDate,tempTime);
                itemList.add(item);  
                }
            }
            
        } catch (FileNotFoundException ex) {                //If the file could not be found
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: Item data file not found!");
        } 
    }

    static void populateSoldList() {
        String fileName = "soldItems.txt";                 
        File file = new File(fileName);
        try {
            Scanner read = new Scanner(file);           
            
            while(read.hasNext()) {                
                String name = read.next(); 
                String weight = read.next();
                int QTY = read.nextInt();
                double price = read.nextDouble();
                String tempDate = read.next();
                String tempTime = read.next();
                String sold = read.next();
                String by = read.next();
                String cashier = read.next();
                String lname = read.next();
                String fname = read.next();

                
                Item item = new Item(name, weight, QTY, price,tempDate,tempTime,sold,by,cashier,lname,fname);
                soldList.add(item);                    
            }
            
        } catch (FileNotFoundException ex) {                //If the file could not be found
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: Item data file not found!");
        } 
    }

    static void populateLow() {                 
        File file = new File("items.txt");
        try {
            Scanner read = new Scanner(file);           
            
            while(read.hasNext()) {                
                String name = read.next(); 
                String weight = read.next();
                int QTY = read.nextInt();
                double price = read.nextDouble();
                String tempDate = read.next();
                String tempTime = read.next();
                 
                if(QTY<25){
                Item item = new Item(name, weight, QTY, price,tempDate,tempTime);
                lowList.add(item);
                }
            }
            
        } catch (FileNotFoundException ex) {                //If the file could not be found
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: Item data file not found!");
        } 
    }

    private static void populateSoldSpecific() {
        File file = new File("soldItems.txt");
        try {
            Scanner read = new Scanner(file);           
            
            while(read.hasNext()) {                
                String name = read.next(); 
                String weight = read.next();
                int QTY = read.nextInt();
                double price = read.nextDouble();
                String tempDate = read.next();
                String tempTime = read.next();
                String sold = read.next();
                String by = read.next();
                String cashier = read.next();
                String lname = read.next();
                String fname = read.next();
                
                int s = loggedTeller.size()-1;
                if(fname.equals(loggedTeller.get(s).getFirstName())){
                Item item = new Item(name, weight, QTY, price,tempDate,tempTime,sold,by,cashier,lname,fname);
                soldListSpecific.add(item);}                    
            }
            
        } catch (FileNotFoundException ex) {                //If the file could not be found
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: Item data file not found!");
        }
    }
}

