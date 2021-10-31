
package edu.ucalgary.ensf409;

public class OfficeFurniture {
    private String ID;
    private String manuID;
    private String type;
    private int price;

    //Default constructor
    public OfficeFurniture(String ID,String type, int price, String manuID) {
        this.ID = ID;
        this.type = type;
        this.price = price;
        this.manuID = manuID;
    }

    //Getter of ID
    public String getID() {
        return ID;
    }

    //Getting of type
    public String getTYPE() {
        return type;
    }

    //Getter or price
    public int getPrice() {
        return price;
    }

    //Getter of Manufacturer ID
    public String getManuID() {
        return manuID;
    }

    //Setter of ID
    public void setID(String ID){
        this.ID = ID;
    }
    //Setter of type
    public void setTYPE(String type){
        this.type = type;
    }
    //Setter of Price
    public void setPRICE(int price){
        this.price = price;
    }

    //Setter of Manufacturer ID
    public void setManuID(String manuID){
        this.manuID = manuID;
    }

    //Prints the ID in the terminal
    public void print(){
        System.out.println(getID());
    }

}



