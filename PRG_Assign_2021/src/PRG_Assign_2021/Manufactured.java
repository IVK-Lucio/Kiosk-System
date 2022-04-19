package PRG_Assign_2021;

/*This class 
Basic attributes such as CompanyName,streetName and location
Basic Setters and Getter are available and A toString method
*/
public class Manufactured {
    private String CompanyName,streetName,location;

    //Parametized constructer, this is used to allow this called to be "Associated through Composition" to class Item
    public Manufactured(String CompanyName,String streetName, String location) {
        this.CompanyName = CompanyName;
        this.streetName = streetName;
        this.location = location;
    }
    //Default constructor, its empty
    public Manufactured(){
        this.CompanyName = "";
        this.streetName = "";
        this.location = "";
    }
    
    //Setters and Getters provide access control to other classes,  cause our attributes are private
    
    //CompanyName getter
    public String getCompanyName() {
        return CompanyName;
    }
    //CompanyName setter
    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }
    
    //streetName getter
    public String getStreetName() {
        return streetName;
    }
    //streetName setter
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    //location getter
    public String getLocation() {
        return location;
    }
    ///location setter
    public void setLocation(String location) {
        this.location = location;
    }
    // toString method with all the attributes of the Manufactured class
    @Override
    public String toString(){
        return "[Street Name : " + getStreetName()+ "] [Location : " + getLocation()+ "]";
    }
}
