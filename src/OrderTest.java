package edu;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.JUnitCore;
import java.lang.*;


public class OrderTest {
    public String userName = "scm"; //Change to scm
    public String passWord = "ensf409"; //Change to ensf409
    public final static File outfile = new File("testoutput.txt");
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    public ArrayList<String> results;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    //Test 1, calculating the minimum cost of 1 mesh chair
    public void test1_ChairMeshCost() throws Exception {
        Order testCharMesh = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testCharMesh.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Chair";
        String type = "Mesh";
        int items = 1;
        testCharMesh.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testCharMesh.testOptimal(findCombinations);

        //get the minimum price of the combination
        String testPrice = Integer.toString(testComb.getPrice());
        testPrice = "$" + testPrice; //Add a dollar sign

        String expected = "$200"; //Hand Calculated
        //Checks if the algorithm behaves as expected
        assertEquals(expected, testPrice);
    }

    @Test
    //Test 2, getting the ID's of 1 mesh chair combination
    public void test2_ChairMeshID() throws Exception {
        Order testCharMesh = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testCharMesh.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Chair";
        String type = "Mesh";
        int items = 1;
        testCharMesh.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testCharMesh.testOptimal(findCombinations);
        ArrayList<String> storedIDsArray = new ArrayList<>();
        for (int i = 0; i < testComb.getFurnitures().length; i++) {
            String temp = testComb.getFurnitures()[i].getID();
            storedIDsArray.add(temp);
        }
        //Converting the ArrayList of IDs back into string for easy comparison
        String[] storedIDs = new String[storedIDsArray.size()];
        for (int j = 0; j < storedIDsArray.size(); j++) {
            storedIDs[j] = storedIDsArray.get(j);
        }

        String[] expectedIDs = {"C6748","C8138","C9890"};


        // See if the arrays are the same
        assertTrue("The expected stored Mesh Chair IDs does not match tested output IDs", Arrays.equals(expectedIDs, storedIDs));
    }


    @Test
    //Test 3, calculating the minimum cost of 3 Adjustable Desks
    public void test3_DeskAdjustableCost() throws Exception {
        Order testDeskAdj = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testDeskAdj.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Desk";
        String type = "Adjustable";
        int items = 3;
        testDeskAdj.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testDeskAdj.testOptimal(findCombinations);

        //get the minimum price of the combination
        String testPrice = Integer.toString(testComb.getPrice());
        testPrice = "$" + testPrice; //Add a dollar sign

        String expected = "$1200"; //Hand Calculated
        //Checks if the algorithm behaves as expected
        assertEquals(expected, testPrice);
    }


    @Test
    //Test 4, getting the multiple ID's of 3 Adjustable desk combination
    public void test4_DeskAdjustableID() throws Exception {
        Order testDeskAdj = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testDeskAdj.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Desk";
        String type = "Adjustable";
        int items = 3;
        testDeskAdj.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testDeskAdj.testOptimal(findCombinations);
        ArrayList<String> storedIDsArray = new ArrayList<>();
        for (int i = 0; i < testComb.getFurnitures().length; i++) {
            String temp = testComb.getFurnitures()[i].getID();
            storedIDsArray.add(temp);
        }
        //Converting the ArrayList of IDs back into string for easy comparison
        String[] storedIDs = new String[storedIDsArray.size()];
        for (int j = 0; j < storedIDsArray.size(); j++) {
            storedIDs[j] = storedIDsArray.get(j);
        }

        String[] expectedIDs = {"D1030","D2746","D3682","D4475","D5437","D7373"};
        // See if the arrays are the same
        assertTrue("The expected output does not match tested output", Arrays.equals(expectedIDs, storedIDs));
    }

    @Test
    //Test 5, calculating the minimum cost of 1 Desk Lamp
    public void test5_LampDeskCost() throws Exception {
        Order testLampDesk = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testLampDesk.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Lamp";
        String type = "Desk";
        int items = 1;
        testLampDesk.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testLampDesk.testOptimal(findCombinations);

        //get the minimum price of the combination
        String testPrice = Integer.toString(testComb.getPrice());
        testPrice = "$" + testPrice; //Add a dollar sign

        String expected = "$20"; //Hand Calculated
        //Checks if the algorithm behaves as expected
        assertEquals(expected, testPrice);
    }
    @Test
    //Test 6 Testing ordering 1 Desk Lamp ID
    public void test6_LampDeskID() throws Exception {
        Order testLampDesk = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testLampDesk.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Lamp";
        String type = "Desk";
        int items = 1;
        testLampDesk.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testLampDesk.testOptimal(findCombinations);
        ArrayList<String> storedIDsArray = new ArrayList<>();
        for (int i = 0; i < testComb.getFurnitures().length; i++) {
            String temp = testComb.getFurnitures()[i].getID();
            storedIDsArray.add(temp);
        }
        //Converting the ArrayList of IDs back into string for easy comparison
        String[] storedIDs = new String[storedIDsArray.size()];
        for (int j = 0; j < storedIDsArray.size(); j++) {
            storedIDs[j] = storedIDsArray.get(j);
        }

        String[] expectedIDs = {"L013","L208"};
        // See if the arrays are the same
        assertTrue("The expected output does not match tested output", Arrays.equals(expectedIDs, storedIDs));
    }

    @Test
    //Test 7, calculating the minimum cost of 2 Desk Lamp
    public void test7_2LampDeskCost() throws Exception {
        Order testLampDesk = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testLampDesk.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Lamp";
        String type = "Desk";
        int items = 2;
        testLampDesk.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testLampDesk.testOptimal(findCombinations);

        //get the minimum price of the combination
        String testPrice = Integer.toString(testComb.getPrice());
        testPrice = "$" + testPrice; //Add a dollar sign

        String expected = "$40"; //Hand Calculated
        //Checks if the algorithm behaves as expected
        assertEquals(expected, testPrice);
    }
    @Test
    //Test 8 Testing ordering 1 Desk Lamp ID
    public void test8_2LampDeskID() throws Exception {
        Order testLampDesk = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testLampDesk.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Lamp";
        String type = "Desk";
        int items = 2;
        testLampDesk.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testLampDesk.testOptimal(findCombinations);
        ArrayList<String> storedIDsArray = new ArrayList<>();
        for (int i = 0; i < testComb.getFurnitures().length; i++) {
            String temp = testComb.getFurnitures()[i].getID();
            storedIDsArray.add(temp);
        }
        //Converting the ArrayList of IDs back into string for easy comparison
        String[] storedIDs = new String[storedIDsArray.size()];
        for (int j = 0; j < storedIDsArray.size(); j++) {
            storedIDs[j] = storedIDsArray.get(j);
        }

        String[] expectedIDs = {"L013","L112","L208","L342"};
        // See if the arrays are the same
        assertTrue("The expected output does not match tested output", Arrays.equals(expectedIDs, storedIDs));
    }

    @Test
    //Test 9, calculating the minimum cost of 1 small filing
    public void test9_FilingSmallCost() throws Exception {
        Order testFilingSmall = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testFilingSmall.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Filing";
        String type = "Small";
        int items = 1;
        testFilingSmall.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testFilingSmall.testOptimal(findCombinations);

        //get the minimum price of the combination
        String testPrice = Integer.toString(testComb.getPrice());
        testPrice = "$" + testPrice; //Add a dollar sign

        String expected = "$100"; //Hand Calculated
        //Checks if the algorithm behaves as expected
        assertEquals(expected, testPrice);
    }
    @Test
    //Test 10, getting the IDs of ordering 1 small filing
    public void test10_FilingSmallID() throws Exception {
        Order testFilingSmall = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testFilingSmall.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Filing";
        String type = "Small";
        int items = 1;
        testFilingSmall.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testFilingSmall.testOptimal(findCombinations);
        ArrayList<String> storedIDsArray = new ArrayList<>();
        for (int i = 0; i < testComb.getFurnitures().length; i++) {
            String temp = testComb.getFurnitures()[i].getID();
            storedIDsArray.add(temp);
        }
        //Converting the ArrayList of IDs back into string for easy comparison
        String[] storedIDs = new String[storedIDsArray.size()];
        for (int j = 0; j < storedIDsArray.size(); j++) {
            storedIDs[j] = storedIDsArray.get(j);
        }

        String[] expectedIDs = {"F001","F013"};
        // See if the arrays are the same
        assertTrue("The expected output does not match tested output", Arrays.equals(expectedIDs, storedIDs));
    }

    @Test
    //Test 11, calculating the minimum cost of 3 small filing
    public void test11_3FilingSmallCost() throws Exception {
        Order testFilingSmall = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testFilingSmall.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Filing";
        String type = "Small";
        int items = 3;
        testFilingSmall.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testFilingSmall.testOptimal(findCombinations);

        //get the minimum price of the combination
        String testPrice = Integer.toString(testComb.getPrice());
        testPrice = "$" + testPrice; //Add a dollar sign

        String expected = "$300"; //Hand Calculated
        //Checks if the algorithm behaves as expected
        assertEquals(expected, testPrice);
    }
    @Test
    //Test 12, getting the IDs of ordering 3 small filing
    public void test12_3FilingSmallID() throws Exception {
        Order testFilingSmall = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testFilingSmall.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Filing";
        String type = "Small";
        int items = 3;
        testFilingSmall.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testFilingSmall.testOptimal(findCombinations);
        ArrayList<String> storedIDsArray = new ArrayList<>();
        for (int i = 0; i < testComb.getFurnitures().length; i++) {
            String temp = testComb.getFurnitures()[i].getID();
            storedIDsArray.add(temp);
        }
        //Converting the ArrayList of IDs back into string for easy comparison
        String[] storedIDs = new String[storedIDsArray.size()];
        for (int j = 0; j < storedIDsArray.size(); j++) {
            storedIDs[j] = storedIDsArray.get(j);
        }

        String[] expectedIDs = {"F001","F004","F005","F006","F013"};
        // See if the arrays are the same
        assertTrue("The expected output does not match tested output", Arrays.equals(expectedIDs, storedIDs));
    }


    @Test
    //Test 13, getting the IDs of ordering a chair without enough inventory
    public void test13_ChairNoInventory() throws Exception {
        Order testNoInv = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testNoInv.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Chair";
        String type = "Mesh";
        int items = 2;
        final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
        testNoInv.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testNoInv.testOptimal(findCombinations);
        ArrayList<String> storedIDsArray = new ArrayList<>();


        String[] expectedIDs = {"Order cannot be fulfilled based on current inventory. Suggested manufacturers are Fine Office Supplies, Chairs R Us."};
        assertEquals(expectedIDs[0], outContent.toString().trim());

    }

    @Test
    //Test 14, getting the IDs of ordering 100 desks
    public void test14_DeskNoInventory() throws Exception {
        Order testNoInv = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testNoInv.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Desk";
        String type = "Adjustable";
        int items = 100;
        final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
        testNoInv.calculationsSetup(findCombinations, category, type, items, outfile);
        Storage testComb = testNoInv.testOptimal(findCombinations);
        ArrayList<String> storedIDsArray = new ArrayList<>();


        String[] expectedIDs = {"Order cannot be fulfilled based on current inventory. Suggested manufacturers are Office Furnishings, Furniture Goods, Fine Office Supplies, Academic Desks."};
        assertEquals(expectedIDs[0], outContent.toString().trim());

    }


    @Test
    // test 15, expected System.exit(1) by entering a negative quantity of furniture requested
    public void test15_InvalidItemsRequested() throws Exception {
        // We are expecting exit(1), using a negative number of items
        exit.expectSystemExitWithStatus(1);
        Order testInvRequest = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testInvRequest.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Chair";
        String type = "Mesh";
        int items = -1;
        //Test perform System.exit(1)
        testInvRequest.calculationsSetup(findCombinations, category, type, items, outfile);
    }

    @Test
    // test 16, expected System.exit(1) by entering an invalid category
    public void test16_InvalidCategory() throws Exception {
        // We are expecting exit(1), using a negative number of items
        exit.expectSystemExitWithStatus(1);
        Order testInvCategory = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testInvCategory.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Cherrios";
        String type = "Mesh";
        int items = 2;
        //Test perform System.exit(1)
        testInvCategory.calculationsSetup(findCombinations, category, type, items, outfile);
    }

    @Test
    // test 17, expected System.exit(1) by entering an invalid category
    public void test17_RequestZeroItems() throws Exception {
        // We are expecting exit(1), using a negative number of items
        exit.expectSystemExitWithStatus(1);
        Order testRequestZeroItems = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testRequestZeroItems.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Filing";
        String type = "Medium";
        int items = 0;
        //Test perform System.exit(1)
        testRequestZeroItems.calculationsSetup(findCombinations, category, type, items, outfile);
    }


    @Test(expected = Exception.class)
    //test18 testing unreachable SQL database exception due to non existing username
    public void test18_userNamecatchException() throws Exception {
        // We are expecting exit(1), using a negative number of items
        exit.expectSystemExitWithStatus(1);
        Order testUserNameError = new Order("jdbc:mysql://localhost/inventory", "fail", passWord);
        testUserNameError.initializeConnection("fail", passWord);
        Calculations findCombinations = new Calculations();
        String category = "Desk";
        String type = "Unreachable";
        int items = 1;
        testUserNameError.calculationsSetup(findCombinations, category, type, items, outfile);
    }

    @Test(expected = Exception.class)
    //test19 testing unreachable SQL database exception due to bad password
    public void test19_passWordcatchException() throws Exception {
        // We are expecting exit(1), using a negative number of items
        exit.expectSystemExitWithStatus(1);
        Order testPassWordError = new Order("jdbc:mysql://localhost/inventory", userName, "fail");
        testPassWordError.initializeConnection(userName, "fail");
        Calculations findCombinations = new Calculations();
        String category = "Desk";
        String type = "Unreachable";
        int items = 1;
        testPassWordError.calculationsSetup(findCombinations, category, type, items, outfile);
    }


    @Test(expected = SQLException.class)
    //test20 testing unreachable SQL database exception
    public void test20_databaseException() throws Exception {
        // We are expecting exit(1), using a negative number of items
        Order testSQLException = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        testSQLException.initializeConnection(userName, passWord);
        Calculations findCombinations = new Calculations();
        String category = "Chair";
        String type = "Mesh";
        int items = 1;
        String test = "C8138";
        testSQLException.calculationsSetup(findCombinations, category, type, items, outfile);
        //Now testing reaching the SQL database to delete, throws SQLException error
        String query = "select FROM "+category+" WHERE ID = ?";
        testSQLException.testQuery(category,test,query);
    }



    @Test // Testing for a scenario where no combination is found
    public void test21_InvalidItems() throws Exception{
        Order orderRequest = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        orderRequest.initializeConnection(userName, passWord);
        Calculations combos = new Calculations();
        ArrayList<Chair> selectChair = orderRequest.selectChair("Task");
        //Perform recursion to find the optimal combination
        combos.recursiveChairComb(selectChair,orderRequest.emptyChair,0,0,0,0,0,5);
        //Store the optimal combination
        Storage result = orderRequest.testOptimal(combos);
        String expected = null;
        assertEquals("The program found a possible combination when it shouldn't have",expected,result);
        orderRequest.close();
    }

    @Test
    //Testing the deleteFurniture method
    public void test22_DeleteFurniture() throws Exception {
        Order orderRequest = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        orderRequest.initializeConnection(userName, passWord);
        orderRequest.deleteFurniture("Desk","D5437");
        ArrayList<Desk> selectDesk = orderRequest.selectDesk("Adjustable");
        boolean result = true;
        Iterator<Desk> search = selectDesk.iterator();
        while (search.hasNext()) {
            String id = search.next().getID();
            if (id.equals("D5437")){
                result = false;
            }
        }
        boolean expected = true;
        assertEquals("Deletion unsuccesful, check database. ",expected,result);
        orderRequest.close();
    }

    @Test
    //Testing a case to make sure that the inventory is updated after making an order
    public void test23_InventoryPostOrder() throws Exception {
        Order orderRequest = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        orderRequest.initializeConnection(userName, passWord);
        File output = new File("orderform.txt");
        Calculations combos = new Calculations();
        orderRequest.calculationsSetup(combos,"Filing", "Medium", 1, output);
        ArrayList<Filing> selectFiling = orderRequest.selectFiling("Medium");
        boolean result = true;
        Iterator<Filing> search = selectFiling.iterator();
        while (search.hasNext()) {
            String id = search.next().getID();
            if (id.equals("F001") || id.equals("F013")){
                result = false;
            }
        }
        boolean expected = true;
        assertEquals("The inventory after order 1 Small Filing should not contain F001 or F013.",expected,result);
        orderRequest.close();
    }

    @Test
    //Testing the selectLamp method. Only the lamps that are of type requested should be selected
    public void test24_SelectLamp() throws Exception{
        Order orderRequest = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        orderRequest.initializeConnection(userName, passWord);
        ArrayList<Lamp> selectLamp = orderRequest.selectLamp("Swing Arm");
        boolean result = false;
        Iterator<Lamp> search = selectLamp.iterator();
        while (search.hasNext()) {
            String id = search.next().getID();
            if (id.equals("L487") || id.equals("L879") || id.equals("L053") || id.equals("L096")){
                result = true;
            }
        }
        boolean expected = true;
        assertEquals("Another Lamp type has been found.",expected,result);
        orderRequest.close();
    }


    @Test // Checking that the code is suggesting to the correct manufacturers
    public void test25_SuggestManufactuer2() {
        Order orderRequest = new Order("jdbc:mysql://localhost/inventory", userName, passWord);
        orderRequest.initializeConnection(userName, passWord);
        String result = orderRequest.suggestManufacturer(orderRequest.referToManufacturers("Filing","Large"));
        String expected = "Suggested manufacturers are Office Furnishings, Fine Office Supplies, Furniture Goods.";
        assertEquals("The actual manufacturer suggested is different than expected.",expected,result);
        orderRequest.close();
    }
}
