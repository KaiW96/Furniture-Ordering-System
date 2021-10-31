
import java.util.ArrayList;

class Storage {
    //String array of an OfficeFurniture combination is stored in furnitures
    private OfficeFurniture[] furnitures;
    private String output = "";
    private int lowestPrice;

    //Default constructor
    public Storage(ArrayList<OfficeFurniture> allCombinations) {
        this.furnitures = allCombinations.toArray(new OfficeFurniture[0]);
        setMinPrice(allCombinations);
    }

    //Calculates the minimum price each time a new combination is stored
    public void setMinPrice(ArrayList<OfficeFurniture> allCombinations){
        for(int j = 0; j < allCombinations.size();j++){
            int tempPrice = furnitures[j].getPrice();
            lowestPrice = lowestPrice+tempPrice;
        }
    }

    //Getter of the list of furnitures in this combination
    public OfficeFurniture[] getFurnitures(){
        return this.furnitures;
    }

    //Print to the console
    public void print() {
        for(int j = 0; j < furnitures.length; j++){
            if(j< furnitures.length-1){
                output = output + furnitures[j].getID()+" and ";
            }
            else if(j == furnitures.length-1){
                output = output + furnitures[j].getID();
            }
        }
        System.out.println("Purchase "+output+" for $"+lowestPrice);
    }

    //Getter of the lowest price of this combination
    public int getPrice(){
        return lowestPrice;
    }
    //Getter of the string output
    public String getOutput(){
        return output;
    }

}
