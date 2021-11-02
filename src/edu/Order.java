package edu;
import java.sql.*;
import java.util.*;
import java.io.*;

/* Order is the main file used for the program
 * The Program asks for user inputs such as username, password to initialize the program
 * The order requires user inputs of category, type and items requested
 * The program performs a call to the Calculations class which handles the possible combinations
 * depending on the current database inventory.
 * Order then selects the most optimal combination based on the lowest price
 * Finally, Order will print the actual order form in .txt format
*/
public class Order {
    public final String DBURL;
    public final String USERNAME; 
    public final String PASSWORD;
    private ResultSet results;
    private Connection dbConnect;

    //Creating empty arraylists of furniture objects
    ArrayList<Chair> emptyChair = new ArrayList<>();
    ArrayList<Desk> emptyDesk = new ArrayList<>();
    ArrayList<Lamp> emptyLamp = new ArrayList<>();
    ArrayList<Filing> emptyFiling = new ArrayList<>();

    //Index used for recursion count
    final int INDEX = 0;

    //Initial count for chair requirements
    final int CHAIRLEGS = 0;
    final int CHAIRARMS = 0;
    final int CHAIRSEAT = 0;
    final int CHAIRCUSHION = 0;

    //Initial count for desk requirements
    final int DESKLEGS = 0;
    final int DESKTOP = 0;
    final int DESKDRAWER = 0;

    //Initial count for lamp requirements
    final int BASE = 0;
    final int BULB = 0;

    //Initial count for Filing requirements
    final int LAMPRAILS = 0;
    final int LAMPDRAWERS = 0;
    final int LAMPCABINET = 0;

    public Order(String url, String userName, String passWord){
        DBURL=url;
        USERNAME=userName;
        PASSWORD=passWord;
    }
    //Getter of the Database URL
    public String getDBURL(){
        return this.DBURL;

    }

    //Getter of the Username
    public String getUSERNAME(){
        return this.USERNAME;

    }

    //Getter of the password
    public String getPASSWORD(){
        return this.PASSWORD;

    }

    //Initializing connection
    public void initializeConnection(String username, String password){
        try{
            dbConnect= DriverManager.getConnection("jdbc:mysql://localhost/inventory", username, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Getter of lowest price of stored combination and price
    public int getLowestPrice(Storage stored){
        return stored.getPrice();
    }

    //Main used to retrieve user input
    //Makes a call to the order object, initializes connection, throws exception if connection cannot be established
    //Retrieves the user input on what the order will be. Requires furniture category, type and items requested
    //A new orderform.txt file is created and the order is printed in that file
    //The program allows the user to make subsequent orders
    //Exits the program when the user types "no" in console
    public static void main(String[] args) throws Exception {
        Scanner scannerObj = new Scanner(System.in);
        System.out.println("Please enter your username.");
        String userName = scannerObj.nextLine();
        System.out.println('\n');
        System.out.println("Please enter your password.");

        String passWord = scannerObj.nextLine();

        Order myJDBC = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        myJDBC.initializeConnection(userName, passWord);

        String order = "yes";
        System.out.println('\n');
        System.out.println("Let's start creating an order");

        while (order.equals("yes")) {
            System.out.println('\n');
            System.out.println("What category of furniture would you like? i.e Chair");
            String Category = scannerObj.nextLine();
            Category = Category.trim();
            System.out.println("What type would you like? i.e Mesh");
            String Type = scannerObj.nextLine();
            Type = Type.trim();

            System.out.println("Enter the quantity of " + Type + " " + Category + " you would like to order");
            String itemstemp = scannerObj.nextLine();
            itemstemp = itemstemp.trim();
            int items = Integer.parseInt(itemstemp);

            System.out.println('\n');
            if(!Category.equals("Chair")&&!Category.equals("Desk")&&!Category.equals("Lamp")&&!Category.equals("Filing")){
                System.out.println("Invalid Category.");
                System.exit(1);
            }

            System.out.println("User request: " + Type + " " + Category + ", " + items);

            File output = new File("orderform.txt");
            Calculations findCombinations = new Calculations();
            myJDBC.calculationsSetup(findCombinations, Category, Type, items, output);
            System.out.println('\n');
            System.out.println("Would you like to make another request? (yes/no)");
            order = scannerObj.nextLine();
        }
    }
    /**
     *
     * @param category
     * @param type
     * @param items
     * @param minPrice
     * @param optimal
     * @param output
     * @throws IOException
     *
     * Function used to print the order to the output fileTakes in category, type, items, minimum price, optimal combination and output file as inputs
     * The printOrder function prints each line one by one through the writeToOutput method
     */
    public void printOrder(String category, String type, int items, int minPrice, Storage optimal,File output) throws IOException {
        clearOutput(output,"");
        writeToOutput(output,"Furniture Order Form");
        writeToOutput(output,"\n");
        writeToOutput(output,"\n");
        writeToOutput(output,"Faculty Name: ");
        writeToOutput(output,"\n");
        writeToOutput(output,"Contact: ");
        writeToOutput(output,"\n");
        writeToOutput(output,"Date: ");
        writeToOutput(output,"\n");
        writeToOutput(output,"\n");

        writeToOutput(output,"Original Request: " + type + " " + category + ", " + items);
        writeToOutput(output,"\n");
        writeToOutput(output,"\n");
        writeToOutput(output,"Items Ordered: ");
        writeToOutput(output,"\n");

        for (int i = 0; i < optimal.getFurnitures().length; i++) {
            writeToOutput(output,"ID: " + optimal.getFurnitures()[i].getID());
            writeToOutput(output,"\n");
        }
        writeToOutput(output,"\n");
        writeToOutput(output,"Total Price: $" + minPrice);

    }

    /**
     *
     * @param output
     * @param written
     * @throws IOException
     * The method directly writes to the output file
     * Takes in the output file and a to be written string as inputs
     */
    public void writeToOutput(File output, String written) throws IOException {
        FileWriter writer = new FileWriter(output, true);
        BufferedWriter bw = new BufferedWriter(writer);
        //When content is present, write a newline before writing to the output
        if(output.length() >= 1){
            bw.write(written);
        }
        //When the file output is empty, write without creating a new line
        else if(output.length() < 1) {
            bw.write(written);
        }
        bw.close();
    }

    /**
     *
     * @param output
     * @param written
     * @throws IOException
     * Method used to clear the output file from its previous order
     */

    public void clearOutput(File output, String written) throws IOException{
        //In FileWriter, select Append as false
        //This clears out the previous written information on output
        FileWriter writer = new FileWriter(output,false);
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write(written);
        bw.close();
    }


    /**
     * @param findCombinations
     * @param category
     * @param type
     * @param items
     * @param output
     * @throws Exception
     * Setting up the order
     * Takes inputs of an empty Calculation object used to store found combinations,
     * furniture category, type, items requested and output file
     * Method handles boundary errors such as requesting negative number of items
     */
    public void calculationsSetup(Calculations findCombinations, String category, String type, int items, File output) throws Exception {
        if(items <= 0){
            System.out.println("Must select number of items greater than 0.");
            System.exit(1);
        }
        //If the category requested is chair
        //select only the elements whos type match and store that in ArrayList
        //Makes a call to the recursive function
        if(category.equals("Chair")){
            if(!type.equals("Mesh") && !type.equals("Ergonomic") && !type.equals("Executive") && !type.equals("Kneeling") && !type.equals("Task")){
                System.out.println("Invalid type for Chair.");
                System.exit(1);
            }
            ArrayList<Chair> selectedchairs = selectChair(type);
            findCombinations.recursiveChairComb(selectedchairs, emptyChair,INDEX,CHAIRLEGS,CHAIRARMS,CHAIRSEAT,CHAIRCUSHION,items);
            Storage optimal = testOptimal(findCombinations);
            int minPrice = checkPrice(findCombinations);

            //If no combinations are stored after the recursion function
            //suggest manufacturers
			if (optimal == null) {
                String manulist = referToManufacturers(category,type);
                String suggestmanu = suggestManufacturer(manulist);
                System.out.println("Order cannot be fulfilled based on current inventory. "+suggestmanu);
			}
			else if(minPrice < 0){
			    System.out.println("Price cannot be less than 0, check database");
            }
			else {
				optimal.print();
                printOrder(category, type, items, minPrice, optimal,output);

                for(int j = 0; j < optimal.getFurnitures().length; j++){
                    String ID = optimal.getFurnitures()[j].getID();
                    deleteFurniture(category,ID);
                }
			}
        }
        //If the category requested is Desk
        //select only the elements whos type match and store that in ArrayList
        //Makes a call to the recursive function
        else if(category.equals("Desk")){
            if(!type.equals("Standing") && !type.equals("Adjustable") && !type.equals("Traditional")){
                System.out.println("Invalid type for Desk.");
                System.exit(1);
            }
            //Selecting the desks with the chosen type
            ArrayList<Desk> selectedDesks = selectDesk(type);

            findCombinations.recursiveDeskComb(selectedDesks,emptyDesk,INDEX,DESKLEGS,DESKTOP,DESKDRAWER,items);
            Storage optimal = testOptimal(findCombinations);
            int minPrice = checkPrice(findCombinations);

            //If no combinations are stored after the recursion function
            //suggest manufacturers
			if (optimal == null) {
                String manulist = referToManufacturers(category,type);
                String suggestmanu = suggestManufacturer(manulist);
                System.out.println("Order cannot be fulfilled based on current inventory. "+suggestmanu);
			}
            else if(minPrice < 0){
                System.out.println("Price cannot be less than 0, check database");
            }
			else {
				optimal.print();
                printOrder(category, type, items, minPrice,optimal,output);
                for(int j = 0; j < optimal.getFurnitures().length; j++){
                    String ID = optimal.getFurnitures()[j].getID();
                    deleteFurniture(category,ID);
                }
			}
        }
        //If the category requested is Lamp
        //select only the elements whos type match and store that in ArrayList
        //Makes a call to the recursive function
        else if (category.equals("Lamp")){
            if(!type.equals("Desk") && !type.equals("Study") && !type.equals("Swing Arm")){
                System.out.println("Invalid type for Lamp.");
                System.exit(1);
            }
            //Select lamps of the chosen type
            ArrayList<Lamp> selectedlamp = selectLamp(type);
            findCombinations.recursiveLampComb(selectedlamp,emptyLamp,INDEX,BASE,BULB,items);
            Storage optimal = testOptimal(findCombinations);
            int minPrice = checkPrice(findCombinations);

            //If no combinations are stored after the recursion function
            //suggest manufacturers
            if (optimal == null) {
                String manulist = referToManufacturers(category,type);
                String suggestmanu = suggestManufacturer(manulist);
                System.out.println("Order cannot be fulfilled based on current inventory. "+suggestmanu);
            }
            else if(minPrice < 0){
                System.out.println("Price cannot be less than 0, check database");
            }
            else {
                optimal.print();
                printOrder(category, type, items, minPrice, optimal,output);

                for(int j = 0; j < optimal.getFurnitures().length; j++){
                    String ID = optimal.getFurnitures()[j].getID();
                    deleteFurniture(category,ID);
                }


            }
        }
        //If the category requested is Filing
        //select only the elements whos type match and store that in ArrayList
        //Makes a call to the recursive function
        else if(category.equals("Filing")){
            if(!type.equals("Small") && !type.equals("Medium") && !type.equals("Large")){
                System.out.println("Invalid type for Filing.");
                System.exit(1);
            }
            //Chosen filing from a specific type
            ArrayList<Filing> selectedfiling = selectFiling(type);
            findCombinations.recursiveFilingComb(selectedfiling,emptyFiling,INDEX,LAMPRAILS,LAMPDRAWERS,LAMPCABINET, items);
            Storage optimal = testOptimal(findCombinations);
            int minPrice = checkPrice(findCombinations);

            //If no combinations are stored after the recursion function
            //suggest manufacturers
			if (optimal == null) {
			    String manulist = referToManufacturers(category,type);
			    String suggestmanu = suggestManufacturer(manulist);
				System.out.println("Order cannot be fulfilled based on current inventory. "+suggestmanu);
			}
            else if(minPrice < 0){
                System.out.println("Price cannot be less than 0, check database");
            }
			else {
				optimal.print();
                printOrder(category, type, items, minPrice, optimal,output);
                for(int j = 0; j < optimal.getFurnitures().length; j++){
                    String ID = optimal.getFurnitures()[j].getID();
                    deleteFurniture(category,ID);
                }

			}
        }
    }
    /**
     * @param type
     * @return
     * @throws Exception
     * The method selects from the chair table where type matches
     * Returns an arraylist of object chair
     */
    public ArrayList<Chair> selectChair (String type) throws Exception{
        ArrayList<Chair> ChairArray = new ArrayList<>();
        try {
            Statement stmt = dbConnect.createStatement();
            this.results = stmt.executeQuery("SELECT * FROM Chair WHERE Type = '" + type + "'");
            String strlegs, strarms, strseat, strcushion;
            int strPrice;
            while(results.next()) {
                strlegs = results.getString("Legs");
                strarms = results.getString("Arms");
                strseat = results.getString("Seat");
                strcushion = results.getString("Cushion");
                strPrice = results.getInt("Price");
                //Converting the string into characters of 'Y' and 'N'
                char legs = strlegs.charAt(0);
                char arms = strarms.charAt(0);
                char seat = strseat.charAt(0);
                char cushion = strcushion.charAt(0);

                //Building a new chair
                Chair a = new Chair(results.getString("ID"), results.getString("Type"), strPrice,
                        results.getString("ManuID"), legs, arms, seat, cushion);

                ChairArray.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ChairArray;
    }
    /**
     * @param type
     * @return
     * @throws Exception
     * Select from the Desk table where type matches
     * Returns an arraylist of object desk
     */
    public ArrayList<Desk> selectDesk (String type) throws Exception{
        ArrayList<Desk> DeskArray = new ArrayList<>();
        try {
            Statement stmt = dbConnect.createStatement();
            this.results = stmt.executeQuery("SELECT * FROM Desk WHERE Type = '" + type + "'");
            String strLegs, strTop, strDrawer;
            int strPrice;
            while (results.next()) {
                strLegs = results.getString("Legs");
                strTop = results.getString("Top");
                strDrawer = results.getString("Drawer");
                strPrice = results.getInt("Price");

                //Storing the Y and N characters
                char Legs = strLegs.charAt(0);
                char Top = strTop.charAt(0);
                char Drawer = strDrawer.charAt(0);

                //Building a new Desk
                Desk a = new Desk(results.getString("ID"), results.getString("Type"), strPrice,
                        results.getString("ManuID"), Legs, Top, Drawer);
                DeskArray.add(a);
            }
        } catch (SQLException e){
            System.out.println("testing exception");
            throw new SQLException();
           // e.printStackTrace();
        }
        return DeskArray;
    }
    /**
     * @param type
     * @return
     * @throws Exception
     * Select from the Lamp table where type matches
     * Returns an arraylist of object lamp
     */
    public ArrayList<Lamp> selectLamp(String type) throws Exception {

        ArrayList<Lamp> LampArray = new ArrayList<>();
        try {
            Statement stmt = dbConnect.createStatement();
            this.results = stmt.executeQuery("SELECT * FROM Lamp WHERE Type = '" + type +"'");
            String strBase, strBulb;
            int strPrice;
            while(results.next()) {
                strBase = results.getString("Base");
                strBulb = results.getString("Bulb");
                strPrice =results.getInt("Price");

                //Converting the string into characters of 'Y' and 'N'
                char base = strBase.charAt(0);
                char bulb = strBulb.charAt(0);
                //Building a new Lamp
                Lamp a = new Lamp (results.getString("ID"), results.getString("Type"), strPrice,
                        results.getString("ManuID"), base, bulb);
                LampArray.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return LampArray;
    }

    /**
     * @param type
     * @return
     * @throws Exception
     * Select from the Filing table where type matches
     * Returns an arraylist of object filing
     */
    public ArrayList<Filing> selectFiling(String type) throws Exception {
        ArrayList<Filing> FilingArray = new ArrayList<>();
        try {
            Statement stmt = dbConnect.createStatement();
            this.results = stmt.executeQuery("SELECT * FROM Filing WHERE Type = '" + type + "'");
            String strRails, strDrawers, strCabinet;
            int strPrice;
            while(results.next()) {
                strRails = results.getString("Rails");
                strDrawers = results.getString("Drawers");
                strCabinet = results.getString("Cabinet");
                strPrice = results.getInt("Price");
                //Converting the string into characters of 'Y' and 'N'
                char rails = strRails.charAt(0);
                char drawers = strDrawers.charAt(0);
                char cabinet = strCabinet.charAt(0);
                //Building a new Filing
                Filing a = new Filing(results.getString("ID"), results.getString("Type"), strPrice,
                        results.getString("ManuID"), rails, drawers, cabinet);
                FilingArray.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FilingArray;
    }

    /**
     * @param suggestion
     * @return string
     * Suggest a manufacturer
     * Creates the output string which will be printed in the console
     */
    public String suggestManufacturer (String suggestion) {
        return "Suggested manufacturers are " + suggestion + ".";
    }

    /**
     * @param category
     * @param type
     * @return String
     * Method used to extract the manufacturer Id
     * The function then performs a call to getManufacturer to get the actual name
     * Returns a string which will suggest potential manufacturers in the terminal
     */
	public String referToManufacturers(String category, String type) {
        //Creating a dynamically allocated string array to store manufacturer IDs
        ArrayList<String> manuIDList= new ArrayList<>();
        String outputstring= "";
		String temp= "";
		int count = 0;
        try {
            Statement stmt = dbConnect.createStatement();
            this.results = stmt.executeQuery("SELECT * FROM " + category +" WHERE Type = '" + type + "'");
            while(results.next()){
                String manuID = results.getString("ManuID");
                //If the manufacture ID is not in the stored list, add manufacturer name
                while(!manuIDList.contains(manuID)){
					manuIDList.add(manuID);
					//Makes a call to getManufacturer to get the actual name of the manufacturer
                    //Append the string as one single string which is used in the output
					temp = temp + getManufacturer(manuID);
                    temp = temp + ", ";
				}
            }
            // Get rid of the last ", "
            // length > 2 handles the case where no manufacturers are available
            if(temp.length()>2) {
                outputstring = temp.substring(0, temp.length() - 2);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return outputstring;
    }

    /**
     * @param  manuID
     * @return String
     * Get the actual manufacturer name from the Mannufacturer table
     * Returns manufacturer name as a string
     */
	public String getManufacturer (String manuID) {
		String manuName = "";
		try {
			Statement stmt = dbConnect.createStatement();
            ResultSet resultset = stmt.executeQuery("SELECT * FROM Manufacturer WHERE ManuID = '" + manuID + "'");
			while(resultset.next()){
				manuName = resultset.getString("Name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return manuName;
	}

    /**
     * @param input
     * @return Storage
     * Method used to find the optimal combination of furnitures in the array list of stored combinations
     * Returns one storage class which is a single combination of OfficeFurniture objects
     */
    public Storage testOptimal (Calculations input){
        Calculations out = null;
        Storage optimal = null;
        int minPrice = 0;
        if (input.getStoredCombination() == null){
            return null;
        }
        for(int j = 0; j <input.getStoredCombination().size();j++) {
            if(optimal == null){
                optimal = input.getStoredCombination().get(j);
            }
            if(input.getStoredCombination().get(j).getPrice() < optimal.getPrice()){
                optimal = input.getStoredCombination().get(j);
            }
        }
        return optimal;
    }

    /**
     * @param input
     * @return int
     * Method used to find the optimal combination of furnitures in the array list of stored combinations
     * Retrieves the private variable lowestPrice using getPrice() to return the minimum price
     * Returns an integer value
     */
    public int checkPrice (Calculations input){
        Calculations out = null;
        Storage optimal = null;
        int minPrice = 0;
        if (input.getStoredCombination() == null){
            return 0;
        }
        for(int j = 0; j <input.getStoredCombination().size();j++) {
            if(optimal == null){
                optimal = input.getStoredCombination().get(j);
            }
            if(input.getStoredCombination().get(j).getPrice() < optimal.getPrice()){
                optimal = input.getStoredCombination().get(j);
            }
        }

        if(optimal!= null) {
            minPrice = optimal.getPrice();
        }
        return minPrice;
    }

    /**
     * @param category
     * @param id
     * Method used to delete the furniture after its selected
     * Takes in user inputs of category (i.e Chair) and ID
     */
    public void deleteFurniture(String category, String id){
        try {
            String query = "DELETE FROM "+category+" WHERE ID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, id);
            myStmt.executeUpdate();
            myStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param category
     * @param id
     * @param query
     * @throws SQLException
     * Method used to delete the furniture after its selected
     * Method used to delete the furniture after its selected
     */
    void testQuery(String category, String id, String query)throws SQLException{
        try {
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            myStmt.setString(1, id);
            myStmt.executeUpdate();
            myStmt.close();
        } catch (SQLException e) {
            throw new SQLException();
           // e.printStackTrace();
        }
    }

    /**
     * @returns void
     *Closing the connection
     */
    public void close(){
        try{
            results.close();
            dbConnect.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
