package PRG_Assign_2021;
/*imports from the package "java.io" cause in this class tellers are create and saved into the text file, they are
also deleted from the text file. Thus the Java input/output package is useful for writing*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import static PRG_Assign_2021.Main.tellerList;

/*This is a Child class of the parent class "Person", thus the two classes are form an inheritance
*/
public class Teller extends Person{
    private String username,password; //Strings that  store attributes usernam and password
    static ArrayList<Teller> loggedTeller= new ArrayList<>();/* Arraylist will store the teller attributes of the teller 
                                                             whose logged on*/
    
    //Parametized constractor, special one though cause it also passes the parameters of the parent class
    public Teller(String firstName, String lastName, String username,  String password) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
    }
    //Parametized constractor, passes only parameters of this Class's attributes
    public Teller(String username,  String password) {
        this.username = username;
        this.password = password;
    }
    
    //Default constractor
    public Teller() {}

    //Setters and Getters provide access control to other classes,  cause our attributes are private here
        
    // Username getter
    public String getUsername() {
        return username;
    }

    //Username setter
    public void setUsername(String username) {
        this.username = username;
    }

    //Password getter
    public String getPassword() {
        return password;
    }

    //Password setter
    public void setPassword(String password) {
        this.password = password;
    }
    
    /*this method is called whenever we are about to display the tellers in the tellerList array
    inside the method is a "for loop", to loop for the number of times the tellerList array size is*/
    public void presentFirst() {
            //below are headings. Cause presentation is everything
            System.out.println("\nList of all the TELLERS:\n");
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("FIRST NAME            |LAST NAME              |USERNAME              |PASSWORD");
            System.out.print("------------------------------------------------------------------------------------------");
            
            for(int i = 0; i < tellerList.size(); i++) {
                tellerList.get(i).display();
            }
            //closing off the table with a simple line
            System.out.println("\n------------------------------------------------------------------------------------------");        
        }
    
    /*this display method will effortlessly presents data using the "printf" printformarts's many 
    advantages like %s, %d and spacing
    the parameters passed to the formats are from this Child Class and from the Main Class
    */
    public void display() {
        /*By choice I could have have replaced the password with Astericks  "*" but since this is
          only called when an admin is logged in, it doesn't matter______ eg below*/
        //System.out.printf("\n%-20s  | %-20s  | %-20s | ******** ",super.getFirstName(),super.getLastName(),username);
        System.out.printf("\n%-20s  | %-20s  | %-20s | %-20s ",super.getFirstName(),super.getLastName(),username,password);
        }
    
    /*This method prompts the admin to enter the details of the new Teller they ae about to create
      Receiving all teller credentials and double checking password entry*/
    public void receiveCredentials(){
        Scanner input = new Scanner(System.in);
        System.out.println("\nCreate New Teller Account\n_________________________________________________________________________________________________");
        System.out.print("Enter Teller First Name: ");
        super.setFirstName(input.nextLine());
        
        System.out.print("Enter Teller Last Name: ");
        super.setLastName(input.nextLine());
        
        System.out.print("Enter new Username: ");
        username = input.nextLine();//attribute username is declared in this class
        
        //for tightening security, the password should atleast be strong
        System.out.print("Set a password ( \n- minimum 8 chars; \n- minimum 1 digit, \n- 1 lowercase, \n- 1 uppercase, \n- 1 special character[!@#$%^&*_]) :");
        String passwordFirstTyped = input.next();
        input.nextLine();
        
        //a "while" statement to assure that the password if of the required conditions
        while(!passwordFirstTyped.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}"))))
        {
            System.err.println("Invalid password condition. Set again :");//"err" instead of "out" to emphiseze the warning
            passwordFirstTyped=input.next();//you're prompted to re-enter
        }
        System.out.print("Retype new password: ");
        String passwordRetyped = input.next();
        input.nextLine();
        
        //another "while" to exception handle an incorrect password
        while(!passwordRetyped.matches((("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]).{8,}"))))
        {
            System.err.println("Invalid password condition. Set again :");
            passwordRetyped=input.next();//you're prompted to re-enter
        }
        
        //another while if the password doesnt match the retyped one
        while(!passwordFirstTyped.equals(passwordRetyped)){
            System.out.println("__________________________________________________________");
            System.err.println("     Retyped password does not match! Try again.         ");
            System.out.println("__________________________________________________________");
            System.out.print("Enter new password: ");//your prompted to re-enter the new password
            passwordFirstTyped = input.nextLine();
            System.out.print("Retype new password: ");
            passwordRetyped = input.nextLine();////your prompted to re-enter the typed password
            System.out.println("__________________________________________________________");
        }
        
        //only after all this whiles are passed is then the password accepted
        password = passwordFirstTyped;
    }
    
    /*this method is called after the teller credentials are entered
    It will only save the new Teller details if the teller credentials do not already exist*/
    public void createNewTeller(){

        receiveCredentials(); //Receive input for new teller credentials

        boolean isTellerExist = false;
        for(Teller teller: tellerList){// for loop without the classic "int i" and "i++" increment___ eg for
            if (teller.username.equals(username)&&teller.password.equals(password)){
                isTellerExist = true;
                System.out.println("\nTeller Account Already Exists!\n");
                break;
            }
        } //Checking whether new teller credentials match with existing teller
        if (!isTellerExist){
            tellerList.add(this);
            
        File file = new File("tellers.txt");
        try (final FileWriter w = new FileWriter(file.getAbsoluteFile(),true);
                final BufferedWriter buw = new BufferedWriter(w);
                final PrintWriter out = new PrintWriter(buw)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            int s = tellerList.size()-1;
            out.println(tellerList.get(s).getFirstName() + " " + tellerList.get(s).getLastName() + " " + tellerList.get(s).getUsername() + " " +tellerList.get(s).getPassword());
            
        }catch (IOException e) {
        }
            System.out.println((char)27 +"[33mNew Teller Account Successfully Created."+(char)27 +"[0m");
            System.out.println("_________________________________________________________________________________________________");
        } //Create new teller and add to teller's list if the teller does not exist
    }
    
    public boolean isValidTeller(){
        boolean isValid = false;
        for (int i = 0; i < tellerList.size(); i++){
            if (tellerList.get(i).username.equals(username) && tellerList.get(i).password.equals(password)) {
                isValid = true;
                loggedTeller.add(tellerList.get(i));
            }
        }
        return isValid; //Validating teller credentials through managers list
    }
    
    public void deleteTeller(){
        Scanner input = new Scanner(System.in);
        //Checking whether specified teller exist and deleting the tellers from tellers list if exist
        boolean isExist = false;
        Teller teller = null;
        if (tellerList.size() > 1){
            System.out.print("\nEnter Teller First Name: ");
            String fname = input.nextLine();
            for (Teller tellerL: tellerList){
                if (super.getFirstName().equals(fname)){
                    isExist = true;
                    teller = tellerL;
                    break;
                }
            }
        }
        else{
            System.err.println("Teller Account Deletion aborted! Only one teller account exist.");
            return;
        } //If only one teller exist, deletion aborted
        if (isExist){
            tellerList.remove(teller);
            File file = new File("tellers.txt");
            try (final FileWriter w = new FileWriter(file.getAbsoluteFile());
                final BufferedWriter buw = new BufferedWriter(w);
                final PrintWriter out = new PrintWriter(buw)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            for (int i = 0; i < tellerList.size(); i++) {
            out.println(tellerList.get(i).getFirstName() + " " + tellerList.get(i).getLastName() + " " + tellerList.get(i).getUsername() + " " +tellerList.get(i).getPassword());
            }
        }catch (IOException e) {
        }
            System.out.println("Specified Teller Account Successfully Deleted.");
        }
        else {
            System.err.println("Specified Teller Account Does Not Exist!");
        }
    }
}
