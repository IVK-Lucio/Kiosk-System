package PRG_Assign_2021;

//imports from packects like "java.util" and "java.io"
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {

    /**
     * The following class is the most important class in this project. It holds all the information
     about an Item such as Name, QTY, Price, expiryDate and expiryTime of Expiry Details
     and also Manufacture-By details through a composition cause every Item obviously is manufactured
     It provides accessor and mutator methods to access this information.aka Setters and Getters
     It also contains a private class called time. Time is a custom class to make it easier to display/read time
     */
    
    private String Name,Weight;                 //Strings that store Name & Weight
    private int QTY;                            //int that stores QTY
    private double Price;                       //double that stores Price of item
    private Time expiryTime;                    //Time object storing ExpiryTime  
    private Date expiryDate;                    //Date object storing ExpiryDate 
    private Manufactured Manu;                  //Manufactured object storing Manufacturer's details
    
    // Parametized Constractor, used when populating our arraylist from data read from text files
    public Item(String Name,String Weight, int QTY, double Price, String DateText,String Time) {
        this.Name = Name;
        this.Weight = Weight;
        this.QTY = QTY;
        this.Price = Price;
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd"); 
            try { 
                this.expiryDate = sdt.parse(DateText);   //to assure that date is of this format
            } 
            catch (ParseException e) { 
                System.out.println("Unparseable using " + sdt);
            }
            
        String[] parts = Time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);            
        this.expiryTime = new Time(hour, minutes);
    }
    //Another parametized constructor...they all serve to populate Arraylists
    public Item(String Name,String Weight, int QTY, double Price, String DateText,String Time,String CompanyName,String streetName, String location) {
        this.Name = Name;
        this.Weight = Weight;
        this.QTY = QTY;
        this.Price = Price;
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
            try { 
                //the constructor can only populate the arraylists with dates of the right format
                this.expiryDate = sdt.parse(DateText);  
            } 
            catch (ParseException e) { 
                System.err.println("Unparseable using " + sdt); // when the exception is caught... "err" instead of "out"...
                //...makes the Warning message red in colour... I have done this alot throught the Project
            }
            
        String[] parts = Time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);            
        this.expiryTime = new Time(hour, minutes);
        this.Manu =  new Manufactured(CompanyName,streetName,location); //Composition
    }
    
    String sold, by,cashier,lname,fname;
    //another parametized constructor that is used to populate the soldItems arraylist from its...
    //..text file
    Item(String name, String weight, int QTY, double price, String tempDate, String tempTime, String sold, String by, String cashier, String lname, String fname) {
        this.Name = name;
        this.Weight = weight;
        this.QTY = QTY;
        this.Price = price;
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
            try { 
                this.expiryDate = sdt.parse(tempDate);   
            } 
            catch (ParseException e) { 
                System.out.println("Unparseable using " + sdt);
            }
            
        String[] parts = tempTime.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);            
        this.expiryTime = new Time(hour, minutes);
        this.sold = sold;
        this.by = by;
        this.cashier = cashier;
        this.lname = lname;
        this.fname = fname;
        
    }
    
    //Default constructor. Stores default values as Item data
    public Item() {
        this.Name = "";
        this.Weight = "";
        this.QTY = 0;
        this.Price = 0;
        expiryDate = new Date();
        expiryTime = new Time();
    }

    //Setters and Getters provide access control to other classes,  cause our attributes are private here
    
    //Name getter
    public String getName() {
        return Name;
    }

    //Name setter
    public void setName(String Name) {
        this.Name = Name;
    }

    //Weight getter
    public String getWeight()
    {
        return Weight;
    }

    //Weight setter
    public void setWeight(String Weight) {
        this.Weight = Weight;
    }
    
    //QTY getter
    public int getQTY() {
        return QTY;
    }

    //Time getter
    public String getTime() {
            String time = expiryTime.getHour()+":"+expiryTime.getMinutes(); //time has a split
            return time;
        }

    //QTY setter
    public void setQTY(int QTY) {
        this.QTY = QTY;
    }
    
    //Price getter    
    public double getPrice() {
        return Price;
    }

    //Price setter
    public void setPrice(double Price) {
        this.Price = Price;
    }
    
    //Date setter
    public boolean setDate(String DateText) {
            SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
            try { 
                this.expiryDate = sdt.parse(DateText);//assure date is a certain format the its Set by the Setter
                return true;
            } 
            catch (ParseException e) { 
                System.err.println("ERROR: Invalid Date Format!");//when the exception is caught
                return false;
            }
    }
    
    //Date getter
    public Date getExpiryDate() {
        return expiryDate;
    }

    //Manufactured getter
    public Manufactured getManu() {
        return Manu;
    }

    //Manufactured setter
    public void setManu(String CompanyName,String streetName, String location) {
        //special cause Manu is a class with its own attributes and thus its Parametized constructor is used
        this.Manu = new Manufactured(CompanyName,streetName, location);
    }
    
    /*This is a very important method in my project.
    It will be called soo many times whether to display Available Items OR Sold Items OR Items running row and many more
    It effortlessly presents data using the "printf" printformarts's many advantages like %s, %d and spacing
    */
    public void display(int n) {
            n += 1;             //for the numbering of items eg "1.", "2." and more...
            System.out.printf("\n %s.  %-20s  | %-13s  | %-10s \t| N$%.2f \t%tB %<te, %<tY _ ",n,Name,Weight, QTY, Price, expiryDate);
            expiryTime.printTime();
        }  
    
    
     /*The following class is a class inside the Item class.
        It makes it easier to display/access time information*/
        private class Time {
            private int hour;               //stores hour in 24 hour format
            private int minutes;            //stores minutes
            
            //default constructor of expiryTime class
            public Time() {
                hour = 0;
                minutes = 0;
            }
            //parameterised constructor
            public Time(int hour, int minutes) {
                this.hour = hour;
                this.minutes = minutes;
            }
            //returns the hour in 24 hour fromat
            public int getHour() {
                return hour;
            }
            //returns minutes
            public int getMinutes() {
                return minutes;
            }
            
            //mutator function to set time to passed values
            public void setTime(int hour, int minutes) {
                this.hour = hour;
                this.minutes = minutes;
            }
            //prints time in 24 hour format
            public void printTime() {
                System.out.printf("%02d:%02d", hour, minutes);
            }
        }   
}
