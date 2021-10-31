
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;


public class Calculations {
    //Created a dynamically allocated arraylist to store the possible stored combinations
    private ArrayList<Storage> storedcombination;
    public int minPrice = 0;

   //Default constructor
    public Calculations() {
        storedcombination = new ArrayList<>();
    }

    //Getter
    public ArrayList<Storage> getStoredCombination() {
        return storedcombination;
    }


    /**
     * @param origChair
     * @param emptyChair
     * @param index
     * @param legs
     * @param arms
     * @param seat
     * @param cushion
     * @param items
     */
    //Method used to calculate all possible combinations of chair that are of the selected type.
    //Performs a recursion to go through each element on the database table.
    //If the number of requirements (i.e legs,arms,seat,cushion) are greater than the number requested,
    //it becomes a possible combination.
    //Each time a possible combination is found, it is stored in the Storage class which is a class
    //that implements a string array of OfficeFurniture class.
    public void recursiveChairComb(ArrayList<Chair> origChair, ArrayList<Chair> emptyChair, int index, int legs, int arms, int seat, int cushion, int items) {
        if(origChair == null){
            return;
        }
        //Checks that all requirements (i.e legs,arms,seat,cushion) are over the request items amount
        //If it is enough to make a new chair, store that combination
        if (items <= legs  && items <= arms && items <= seat  && items <= cushion ) {
            ArrayList<OfficeFurniture> chaircombfound = new ArrayList<>();
            for(int j = 0; j <emptyChair.size();j++){
                chaircombfound.add(emptyChair.get(j));
            }
            Storage tostore = new Storage(chaircombfound);
            this.storedcombination.add(tostore);
            return;
        }

        //For loop used to iterate into the next element in the database table
        for (int i = index; i < origChair.size(); i++){
            //Check if the database has Y or N for the requirements
            int addleg = 0;
            int addarm = 0;
            int addseat = 0;
            int addcushion = 0;

            emptyChair.add(origChair.get(i));
            if(origChair.get(i).getLegs() == 'Y'){
                addleg = 1;
            }
            if(origChair.get(i).getArms() == 'Y'){
                addarm = 1;

            }
            if(origChair.get(i).getSeat() == 'Y'){
                addseat = 1;

            }
            if(origChair.get(i).getCushion() == 'Y'){
                addcushion = 1;
            }

            //Perform the recursion to check if the current element can make a combination with
            //the elements that are left in the chosen type
            recursiveChairComb(origChair,emptyChair,i+1, legs +addleg, arms+addarm,
                    seat+addseat, cushion+addcushion, items);

            //The last element gets deleted, as it cannot make a combination with itself
            int currentsize = emptyChair.size();
            emptyChair.remove(currentsize - 1);
        }
    }

    /**
     * @param origdesks
     * @param emptyDesks
     * @param index
     * @param legs
     * @param top
     * @param drawers
     * @param items
     */
    //Method used to calculate all possible combinations of desk that are of the selected type.
    //Performs a recursion to go through each element on the database table.
    //If the number of requirements (i.e legs,top,drawer) are greater than the number requested,
    //it becomes a possible combination.
    //Each time a possible combination is found, it is stored in the Storage class which is a class
    //that implements a string array of OfficeFurniture class.
    public void recursiveDeskComb(ArrayList<Desk> origdesks, ArrayList<Desk> emptyDesks, int index, int legs, int top, int drawers, int items) {
        if(origdesks == null){
            return;
        }

        //Checks that all requirements (i.e legs,legs,top,drawer) are over the request items amount
        //If it is enough to make a new desk, store that combination
        if (items <= legs && items <= top  && items <= drawers) {
            ArrayList<OfficeFurniture> deskcombfound = new ArrayList<>();
            for(int j = 0; j <emptyDesks.size();j++){
                deskcombfound.add(emptyDesks.get(j));
            }
            Storage tostore = new Storage(deskcombfound);
            this.storedcombination.add(tostore);
            return;
        }

        //For loop used to iterate into the next element in the database table
        for (int i = index; i < origdesks.size(); i++){
            //Check if the database has Y or N for the requirements
            int addleg = 0;
            int addtop = 0;
            int addDrawer = 0;

            emptyDesks.add(origdesks.get(i));
            if(origdesks.get(i).getLegs() == 'Y'){
                addleg = 1;
            }
            if(origdesks.get(i).getTop() == 'Y'){
                addtop= 1;

            }
            if(origdesks.get(i).getDrawer() == 'Y'){
                addDrawer = 1;

            }

            //Perform the recursion to check if the current element can make a combination with
            //the elements that are left in the chosen type
            recursiveDeskComb(origdesks,emptyDesks,i+1, legs +addleg, top+addtop,
                    drawers+addDrawer, items);

            //The last element gets deleted, as it cannot make a combination with itself
            int currentsize = emptyDesks.size();
            emptyDesks.remove(currentsize - 1);
        }
    }


    /**
     * @param origLamp
     * @param emptyLamp
     * @param index
     * @param base
     * @param bulb
     * @param items
     */
    //Method used to calculate all possible combinations of desk that are of the selected type.
    //Performs a recursion to go through each element on the database table.
    //If the number of requirements (i.e base,bulb) are greater than the number requested,
    //it becomes a possible combination.
    //Each time a possible combination is found, it is stored in the Storage class which is a class
    //that implements a string array of OfficeFurniture class.
    public void recursiveLampComb(ArrayList<Lamp> origLamp, ArrayList<Lamp> emptyLamp, int index, int base, int bulb, int items) {
        if(origLamp == null){
            return;
        }
        //Checks that all requirements (i.e base,bulb) are over the request items amount
        //If it is enough to make a new lamp, store that combination
        if (items <= base && items <= bulb ) {
            ArrayList<OfficeFurniture> lampcombfound = new ArrayList<>();
            for(int j = 0; j <emptyLamp.size();j++) {
                lampcombfound.add(emptyLamp.get(j));
            }
            Storage tostore = new Storage(lampcombfound);
            this.storedcombination.add(tostore);
            return;
        }

        //For loop used to iterate into the next element in the database table
        for (int i = index; i < origLamp.size(); i++) {
            //Check if the database has Y or N for the requirements
            int addbase = 0;
            int addbulb = 0;

            emptyLamp.add(origLamp.get(i));
            if (origLamp.get(i).getBase() == 'Y') {
                addbase = 1;
            }
            if (origLamp.get(i).getBulb() == 'Y') {
                addbulb = 1;
            }

            //Perform the recursion to check if the current element can make a combination with
            //the elements that are left in the chosen type
            recursiveLampComb(origLamp, emptyLamp, i + 1, base + addbase, bulb + addbulb, items);

            //The last element gets deleted, as it cannot make a combination with itself
            int currentsize = emptyLamp.size();
            emptyLamp.remove(currentsize - 1);
        }
    }

    /**
     * @param origFilings
     * @param emptyFiling
     * @param index
     * @param rails
     * @param drawers
     * @param cabinet
     * @param items
     */
    //Method used to calculate all possible combinations of desk that are of the selected type.
    //Performs a recursion to go through each element on the database table.
    //If the number of requirements (i.e rails,drawers,cabinet) are greater than the number requested,
    //it becomes a possible combination.
    //Each time a possible combination is found, it is stored in the Storage class which is a class
    //that implements a string array of OfficeFurniture class.
    public void recursiveFilingComb(ArrayList<Filing> origFilings, ArrayList<Filing> emptyFiling, int index, int rails, int drawers, int cabinet, int items) {
        if(origFilings == null){
            return;
        }
        //Checks that all requirements (i.e rails,drawers,cabinet) are over the request items amount
        //If it is enough to make a new filing, store that combination
        if (items <= rails && items <= drawers && items <= cabinet) {
            ArrayList<OfficeFurniture> filingcombfound = new ArrayList<>();
            for(int j = 0; j <emptyFiling.size();j++){
                filingcombfound.add(emptyFiling.get(j));
            }
            Storage tostore = new Storage(filingcombfound);
            this.storedcombination.add(tostore);
            return;
        }

        //For loop used to iterate into the next element in the database table
        for (int i = index; i < origFilings.size(); i++){
            //Check if the database has Y or N for the requirements
            int addRails = 0;
            int addDrawers = 0;
            int addCabinet = 0;

            emptyFiling.add(origFilings.get(i));
            if(origFilings.get(i).getRails() == 'Y'){
                addRails = 1;
            }
            if(origFilings.get(i).getDrawers() == 'Y'){
                addDrawers= 1;

            }
            if(origFilings.get(i).getCabinet() == 'Y'){
                addCabinet = 1;

            }

            //Perform the recursion to check if the current element can make a combination with
            //the elements that are left in the chosen type
            recursiveFilingComb(origFilings,emptyFiling,i+1, rails +addRails, drawers+addDrawers,
                    cabinet+addCabinet, items);

            //The last element gets deleted, as it cannot make a combination with itself
            int currentsize = emptyFiling.size();
            emptyFiling.remove(currentsize - 1);
        }
    }

}

