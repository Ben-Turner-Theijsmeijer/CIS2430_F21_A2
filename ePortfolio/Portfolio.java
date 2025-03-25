package bturnert_a2.ePortfolio;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Portfolio {

    private static String symbol;

    static Scanner scan = new Scanner(System.in);

    static ArrayList <Investment> InvestmentList = new ArrayList <Investment>();
    static HashMap <String, ArrayList<Integer>> NameKeys = new HashMap <String, ArrayList<Integer>>();

    //------------------------------------------------coomand line arguments------------------------------------------------
    /**
     * Checks if a command line argument was provided or not.
     * @param args String array passed to method representing the command line arguments.
     * @return Returns either true if there is a command line argument or false if not.
     */
    private static boolean isCommandLineArgument(String[] args){
        if(args.length == 0){
            System.out.println("No file specified to load data from");
            return false;
        }
        else{
            System.out.println("Attempting to read from file: " + args[0]);
            return true;
        } 
    }


    //------------------------------------------------display the menu------------------------------------------------
    /**
     * Displays the command loop option menu; with options for buy, sell, update, getGain, search, and quit.
     */
    private static void dispalyMenu(){
        System.out.println("============================================MENU============================================");
        System.out.println("Options:");
        System.out.println("1) buy:        make a new investment or add to an existing one");
        System.out.println("2) sell:       sell all or part of an exisitng invesment");
        System.out.println("3) update:     refresh the invesment prices");
        System.out.println("4) getGain:    view total gain if all existing investments were sold at their current prices");
        System.out.println("5) search:     find all investments matching search request");
        System.out.println("6) quit:       end the program and save data to a file");
        System.out.println("============================================================================================");
    }
    
    //------------------------------------------------get command loop choice------------------------------------------------
    /**
     * Prompts the user to enter an option from the menu. Checks the entered string against the possible valid entires and returns a different intger based on what the entered string matched with.
     * Possible integeres include: 0 == unset, 1 == buy, 2 == sell, 3 == update, 4 == getaGain, 5 == search, and 6 == quit.
     * <p>
     * Loops until a valid String is entered by the user.
     * @return Returns a value from 0 to 6 indicating the user's operation choice from the menu's options.
     */
    private static int getCommandLoopOption(){
        int menuOption = 0; // 0 = unset, 1 = buy, 2 = sell, 3 = update, 4 = getGain, 5 = search, 6 = exit
        boolean isValid = false;
        String[] validInputBuy = {"buy", "B", "1"};
        String[] validInputSell = {"sell", "S", "2"};
        String[] validInputUpdate = {"update", "U", "3"};
        String[] validInputGetGain = {"getGain", "getGains", "get", "gain", "GG", "G", "4"};
        String[] validInputSearch = {"search", "SCH", "SH", "5"};
        String[] validInputQuit = {"quit", "Q", "6"};

        System.out.print("Choose option: ");
        String commandLoopInput = scan.nextLine();

        do{
            for(String temp: validInputBuy){
                if(commandLoopInput.equalsIgnoreCase(temp)){
                    menuOption = 1;
                    isValid = true;
                }
            }
            if(isValid == true) break;
            for(String temp: validInputSell){
                if(commandLoopInput.equalsIgnoreCase(temp)){
                    menuOption = 2;
                    isValid = true;
                }
            }
            if(isValid == true) break;
            for(String temp: validInputUpdate){
                if(commandLoopInput.equalsIgnoreCase(temp)){
                    menuOption = 3;
                    isValid = true;
                }
            }
            if(isValid == true) break;
            for(String temp: validInputGetGain){
                if(commandLoopInput.equalsIgnoreCase(temp)){
                    menuOption = 4;
                    isValid = true;
                }
            }
            if(isValid == true) break;
            for(String temp: validInputSearch){
                if(commandLoopInput.equalsIgnoreCase(temp)){
                    menuOption = 5;
                    isValid = true;
                }
            }
            if(isValid == true) break;
            for(String temp: validInputQuit){
                if(commandLoopInput.equalsIgnoreCase(temp)){
                    menuOption = 6;
                    isValid = true;
                }
            }
            if(isValid == true) break;
            else{
                System.out.println("Sorry: Choice not recognized, try again");
                System.out.print("Choose menu option: ");
                commandLoopInput = scan.nextLine();
            }
        }while(isValid == false);

        return menuOption;
    }
    
    //------------------------------------------------check if Investment ArrayList is empty------------------------------------------------
    /**
     * Checks if the Investments ArrayList is empty or not, returning a boolean value representing if the list is empty or not.
     * @return Returns either true if the ArrayList is empty or false if it is not empty.
     */
    private static boolean investmentsIsEmpty(){
        boolean isEmpty = false;
        if(InvestmentList.isEmpty()){
            isEmpty = true;
        }
        return isEmpty;
    }
    
    //------------------------------------------------get investment type------------------------------------------------
    /**
     * Prompts the user to choose an option from the displayed sub menu. Checks the entered string against the possible valid entires and returns a different integer based on what the entered string matched with.
     * Possible integeres include: 0 == unset, 1 == Stock, 2 == MutualFund.
     * <p>
     * Loops until a valid String is entered by the user.
     * @return Returns a value from 0 to 2 indicating the user's choice from the sub menu's options.
     */
    private  static int getInvestmentType(){
        int type = 0; // 0 = unset, 1 = stock, 2 = mutual fund
        boolean isValid = false;
        String[] validInputsStock = {"Stock", "S", "1"};
        String[] validInputMututalFund = {"Mutual Fund", "MutualFund", "Mutual", "Fund", "MF", "M", "F", "2"};

        System.out.println("=====================================Invetestment Types=====================================");
        System.out.println("1) Stock");
        System.out.println("2) Mutual Fund");
        System.out.println("============================================================================================");
        System.out.print("Choose investment type: ");
        String investmentType = scan.nextLine();
        
        do{
            for(String temp: validInputsStock){
                if(investmentType.equalsIgnoreCase(temp)){
                    type = 1;
                    isValid = true;
                }
            }
            for(String temp: validInputMututalFund){
                if(investmentType.equalsIgnoreCase(temp)){
                    type = 2;
                    isValid = true;
                }
            }
            if(isValid == false){
                System.out.println("Sorry: Investment type not recognized, try again");
                System.out.print("Choose investment type: ");
                investmentType = scan.nextLine();
            }
        }while(isValid == false);

        return type;
    }
    
    //------------------------------------------------enter symbol for Investment------------------------------------------------
    /**
     * Prompts the user to enter a String representing the symbol for either a Stock object or a MutualFund object.
     */
    private static void enterSymbol(){
        do {
            System.out.print("Enter the Symbol representing the investment: ");
            symbol = scan.nextLine();
        } while (symbol.isBlank());
    }
    
    //------------------------------------------------check if symbol is in Investment ArrayList------------------------------------------------
    /**
     * Iterates trhough all Investment objects in the InvestmentList ArrayList. Checks if the symbol matches symbol in the current Investment object. Returning the position in the ArrayList if found.
     * @return Returns an integer representing  either -1 if the symbol is not found in any of the Investment objects, or if found in the ArrayList indicating the position in the InvestmentList ArrayList where the symbol was matched. 
     */
    private static int inInvestments(){
        int counter = 0;
        for(Investment temp: InvestmentList){
            if(temp.getSymbol().equalsIgnoreCase(symbol)){ 
                return counter;
            }
            counter++;
        }
        return -1; // not in list
    }
    
    //------------------------------------------------get search key------------------------------------------------
    /**
     * Scans a line from system.in and stores it as a string.
     * @return Returns a String representing the line that was read.
     */
    private static String searchWord(){
        String searchWord = scan.nextLine();
        searchWord = searchWord.trim();
        searchWord = searchWord.toLowerCase();
        return searchWord;
    }

    //------------------------------------------------separate search keywords------------------------------------------------
    /**
     * Splits the keywords string into sepaerate words and returns them as an array of strings.
     * @param enteredString String passed to method representing the key words to search for.
     * @return Reurns an array of strings representing the enteredString after parsing it wherever there is a space.
     */
    private static String[] separate (String enteredString){
        String [] parts = enteredString.split("[ ]+");
        return parts;
    }

    //------------------------------------------------OK to show based off keywords------------------------------------------------
    /**
     * Decides if it is okay to display an investment based on the keywords matching.
     * @param keys String array passed to method representing the enteredString after parsing it wherever there is a space.
     * @param currentName String representing the name of the current investment.
     * @return Returns a boolean value representing okay to display investment or not; true = okay, flase = not.
     */
    private static boolean showKeyword (String []keys, String currentName){
        boolean keywordMatch = true;
        for(String temp: keys){
            if(!currentName.contains(temp.concat(" "))){
                keywordMatch = false;
            }
        }
        return keywordMatch;
    }
    
    //------------------------------------------------get valid priceRange------------------------------------------------
    /**
     * Checks if the entered range is valid.
     * @param enteredRange String passed to method representing the range for the search method entered by the user. 
     * @return Returns a value in the range 0-5, where the value returned indicates the specific case. 0 = invalid, 1 = no range, 2 = specific price, 3 = specific price or higher, 4 = specific price or lower, 5 = price range.
     */
    private static int validRange(String enteredRange){
        if(enteredRange.isBlank()){
            return 1;
        }
        else if(enteredRange.matches("[0-9.]+")){
            return 2;
        }
        else if(enteredRange.matches("[0-9.]+" + "-")){
           return 3;
        }
        else if(enteredRange.matches("-" + "[0-9.]+")){
            return 4;
        }
        else if(enteredRange.matches("[0-9.]+" + "-" + "[0-9.]+")){
            return 5;
        }
        else{
            return 0;
        }
    }

    //------------------------------------------------OK to show based off price range------------------------------------------------
    /**
     * Takes in all parameters relating to search price range and computes wether or not it is okay to display the investment based on the price range matching.
     * @param min Double representing the minimum value that price can be to match in the even the entered range was specific price or higher, or full price range. -1 if not set.
     * @param max Double representing the maxmum value that price can be to match in the event the entered range was specific price or lower, or full price range. -1 if not set.
     * @param specificPrice Double representing the value that price must match in the event the entered range was specific price. -1 if not set.
     * @param rangeType Integer representing the value in the range 0-5, where the value indicates the specific case. 0 = invalid, 1 = no range, 2 = specific price, 3 = specific price or higher, 4 = specific price or lower, 5 = price range.
     * @param currentPrice Double representing the price of the current investment.
     * @return Returns a boolean represetning if it is okay to display investment or not; true = okay, false = not.
     */
    private static boolean showRange(double min, double max, double specificPrice, int rangeType, double currentPrice){
        boolean rangeMatch = false;
        switch (rangeType){
            case 1:     //no price range search (left blank)
                break;
            case 2:     //specific price search (1000.00)
                if(specificPrice == currentPrice){
                    rangeMatch = true;
                }
                break;
            case 3:     //specific price or higher search (1000.00-)
                if(min <= currentPrice){
                    rangeMatch = true;
                }
                break;
            case 4:     //specific price or lower search (-1000.00)
                if(max >= currentPrice){
                    rangeMatch = true;
                }
                break;
            case 5:     //price range search (100.00-999.99)
                if(min <= currentPrice && max >= currentPrice){
                    rangeMatch = true;
                }
                break;
        }
        return rangeMatch;
    }

    //------------------------------------------------Add or update element in the HashMap------------------------------------------------
    /**
     * Adds an element or updates an exisitng element in the HashMap.
     * @param currentName String representing the name of the current Investment.
     * @param index Integer representing the position in the ArrayList.
     */
    private static void addToHashMap(String currentName, Integer index){
        // ArrayList <Integer> newValue = new ArrayList <Integer>();
        String[] nameParts = currentName.split("[ ]+");
        for(String temp: nameParts){
            ArrayList <Integer> foundAt = new ArrayList <Integer>();
            // System.out.println("|" + temp + "|");
            foundAt.clear();
            // newValue.clear();
            //name part is in HashMap
            if(NameKeys.containsKey(temp)){
                foundAt = NameKeys.get(temp);
                // System.out.println("adding to exisitng element " + temp + " arraylist before = " + foundAt);
                foundAt.add(index);
                // System.out.println("adding to exisitng element " + temp + " arraylist after = " + foundAt);
                NameKeys.put(temp, foundAt);
                // System.out.println(temp + " values = " + NameKeys.get(temp));
            }
            //Name part is not in HashMap
            else{
                foundAt.add(index);
                // System.out.println("creating new element " + temp  + " arraylist after = " + foundAt);
                NameKeys.put(temp, foundAt);
            }
        }
        // System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        // for(String key: NameKeys.keySet()){
        //     System.out.println("Key = |" + key + "| values = " + NameKeys.get(key));
        // }
    }

    //------------------------------------------------remove element from the HashMap------------------------------------------------
    /**
     * Removes elements form the HashMap when they are completely sold off.
     * @param currentName String representing the name of the current Investment.
     * @param index Integer representing the position in the ArrayList.
     */
    private static void removeFromHashMap(String currentName, Integer index){
        String[] nameParts = currentName.split("[ ]+");

        System.out.println("investment placement = " + index);

        for(String temp: nameParts){
            ArrayList <Integer> foundAt = new ArrayList <Integer>();
            // System.out.println("|" + temp + "|");
            foundAt.clear();
            //name part is in HashMap
            if(NameKeys.containsKey(temp)){
                foundAt = NameKeys.get(temp);
                if(foundAt.contains(index)){
                    if(foundAt.size() == 1){
                        NameKeys.remove(temp);
                    }
                    for(int i = 0; i < foundAt.size(); i++){
                        if(foundAt.get(i) == index){
                            foundAt.remove(i);
                        }
                    }
                }
                NameKeys.replace(temp, foundAt);
            }
        }
        // System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        // for(String key: NameKeys.keySet()){
        //     System.out.println("Key = |" + key + "| values = " + NameKeys.get(key));
        // }
    }

    //------------------------------------------------search using the HashMap------------------------------------------------
    /**
     * Searches for element in ArrayList using HashMap.
     * @param searchWord String representing the specified search words.
     * @param searchSymbol String representing the specified search symbol.
     * @param rangeType Integer representing the type of seach Price range was inputed.
     * @param min Double representing the minimum value the price range must be to match.
     * @param max Double representing the maximim value the price range can be to match.
     * @param specificPrice Double representing the specific price that must be match.
     */
    private static void searchUsingHashMap(String searchWord, String searchSymbol, int rangeType, double min, double max, double specificPrice){
        String[] nameParts = searchWord.split("[ ]+");
        //NOT DONE

    }

    /*================================================================================================================================================
    ||                                                        COMMAND LOOP OPERATIONS                                                               ||
    ================================================================================================================================================*/
    //------------------------------------------------buy operation------------------------------------------------
    /**
     * Creates Stock and MutualFund objects and adds them to their associated ArrayLists. 
     * <p>
     * Alternatively increases the varaibles in pre-existing Stock and MutualFund objects found in their associated ArrayLists.
     */
    private static void buy(){
        int investmentType = getInvestmentType();
        enterSymbol();
        int investmentLocation = inInvestments();
        //if the investment does not already exist creating a new investment
        if(investmentLocation == -1){       //NEW INVESTMENT
            //new Stock investment
            if(investmentType == 1){
                System.out.println("New Stock investment:");
                Stock newStock = new Stock(symbol);
                addToHashMap(newStock.getName().toLowerCase(), InvestmentList.size());
                InvestmentList.add(newStock);
            }
            //new Mutual Fund investment
            else if(investmentType == 2){
                System.out.println("New Mutual Fund investment:");
                MutualFund newMutualFund = new MutualFund(symbol);
                addToHashMap(newMutualFund.getName().toLowerCase(), InvestmentList.size());
                InvestmentList.add(newMutualFund);
            }
            //getInvestmentType did not work properly
            else{
                System.out.println("program is broken, did not read investment type properly, investment type = " + investmentType);
            }
        }
        //if the investment does exist retreiving the investment from the arrayList
        else if(investmentLocation > -1){       //PRE-EXISTING INVESTMENT
            //is an exisitng Stock investment
            if((investmentType == 1) && (InvestmentList.get(investmentLocation) instanceof Stock)){
                Stock existingStock = (Stock)InvestmentList.get(investmentLocation);
                System.out.println("Buying more " + existingStock.getName() + " Stock:");
                existingStock.buyUpdate();
                InvestmentList.set(investmentLocation, existingStock);
            }
            //is an exisitng MutualFund investment
            if((investmentType == 2) && (InvestmentList.get(investmentLocation) instanceof MutualFund)){
                MutualFund existingMutualFund = (MutualFund)InvestmentList.get(investmentLocation);
                System.out.println("Buying more " + existingMutualFund.getName() + " Mutual Fund:");
                existingMutualFund.buyUpdate();
                InvestmentList.set(investmentLocation, existingMutualFund);
            }
            //is an exisitng Investment, but Investment type and buy type did not match
            else{
                System.out.println("Sorry, you are trying to buy an Investment with the symbol: "+ symbol);
                System.out.println("This symbol has already been used to specify an existing Investment of a different type.");
                System.out.println("No two investments may be represented by the same symbol.");
                System.out.println("Aborting buy operation, please try again.");
            }
        }
        else{   //something is wrong if this prints
            System.out.println("program is broken, did not perform buy operation properly, investment type = " + investmentType);
        }

    }

    //------------------------------------------------sell operation------------------------------------------------
    /**
     * Deletes pre-existing Stock and MutualFund objects form their associated ArrayLists.
     * <p>
     * Alternatively lowers the variables in pre-existing Stock and MutualFund objects found in their associated ArrayLists.
     */
    private static void sell(){
        //checking if there are investments that can be sold
        if(investmentsIsEmpty() == true){
            System.out.println("Error: Cannot sell any investments at this time as you current have none");
            return;
        }
        //varaiable declaration
        enterSymbol();                              //set symbol for selling
        int remove = 0;
        int investmentLocation = inInvestments();   //check if entered symbol is in InvestmentsList
        //symbol is not found in ArrayList
        if(investmentLocation == -1){
            System.out.println("Error: Cannot sell specified investment as it does not currently exist");
        }
        //symbol is found in InvestmentsList
        else if(investmentLocation > -1){
            //Investment is of type Stock
            if(InvestmentList.get(investmentLocation) instanceof Stock){
                Stock stockForSale = (Stock)InvestmentList.get(investmentLocation);
                System.out.println("selling " + stockForSale.getName() + " Stock:");
                stockForSale.setPrice();
                remove = stockForSale.sellUpdateQuantity();
                InvestmentList.set(investmentLocation, stockForSale);
            }
            //Investment is of type MutualFund
            else if(InvestmentList.get(investmentLocation) instanceof MutualFund){
                MutualFund mutualFundForSale = (MutualFund)InvestmentList.get(investmentLocation);
                System.out.println("selling " + mutualFundForSale.getName() + " Mutual Fund:");
                mutualFundForSale.setPrice();
                remove = mutualFundForSale.sellUpdateQuantity();
                InvestmentList.set(investmentLocation, mutualFundForSale);
            }
            if(remove == 1){
                removeFromHashMap(InvestmentList.get(investmentLocation).getName(), investmentLocation);
                InvestmentList.remove(investmentLocation);  //removing object from list if all of it was sold
            } 
        }
    }

    //------------------------------------------------update operation------------------------------------------------
    /**
     * Updates the Price associated with pre-existing Stock and MutualFund objects found in their associated ArrayLists.
     */
    private static void update(){
        //checking if there are investments that can be updated
        if(investmentsIsEmpty() == true){
            System.out.println("Error: Cannot sell any investments at this time as you current have none");
            return;
        }
        //there are investments to be updated
        for(int index = 0; index < InvestmentList.size(); index++){
            //current element is of type Stock
            if(InvestmentList.get(index) instanceof Stock){
                Stock currentStock = (Stock)InvestmentList.get(index);
                System.out.println("Updating " + currentStock.getName() + " stock price:");
                currentStock.setPrice();
                System.out.printf("%s updated to $%.2f per unit\n", currentStock.getName(), currentStock.getPrice());
                InvestmentList.set(index, currentStock);
            }
            //current element is of type MutualFund
            else if(InvestmentList.get(index) instanceof MutualFund){
                MutualFund currentMutualFund = (MutualFund)InvestmentList.get(index);
                System.out.println("Updating " + currentMutualFund.getName() + " mutual fund price:");
                currentMutualFund.setPrice();
                System.out.printf("%s updated to $%.2f per unit\n", currentMutualFund.getName(), currentMutualFund.getPrice());
                InvestmentList.set(index, currentMutualFund);
            }
        }
    }

    //------------------------------------------------getGains operation------------------------------------------------
    /**
     * Outputs the theoretical gain that could be obtained by the user if they completely sold all Stock and MutualFund investments in their portfolio at their current prices.
     */
    private static void getGain(){
        //checking if there are investments
        if(investmentsIsEmpty() == true){
            System.out.println("Error: Cannot sell any investments at this time as you current have none");
            return;
        }
        double totalGain = 0;
        //there are investments
        for(int index = 0; index < InvestmentList.size(); index++){
            //current element is of type Stock
            if(InvestmentList.get(index) instanceof Stock){
                Stock currentStock = (Stock)InvestmentList.get(index);
                totalGain += currentStock.getGain();
            }
            //current element is of type MutualFund
            else if(InvestmentList.get(index) instanceof MutualFund){
                MutualFund currentMutualFund = (MutualFund)InvestmentList.get(index);
                totalGain += currentMutualFund.getGain();
            }
        }
        System.out.println("Current gain if all held investments were sold at their current prices:");
        System.out.printf("Gain = $%.2f\n", totalGain);

    }

    //------------------------------------------------search operation------------------------------------------------
    /**
     * Searches the Stock and MutualFund Arraylists for specific objects based on three search paramiters. Paramiters are as follows: 1) symbol 2) key words 3) a price range.
     * Prints all objects that meet search requirements and  does not print thosee that do not. 
     */
    private static void search(){
        //checking if there are investments
        if(investmentsIsEmpty() == true){
            System.out.println("Error: Cannot sell any investments at this time as you current have none");
            return;
        }

        //getting search options
        int rangeType = 0;
        String searchSymbol, searchKeyWord, searchPriceRange;
        
        System.out.println("You many use up to three search specifiers: a symbol, key word(s), and price range.");
        System.out.println("Enter: search specifications, if you do not wish to use a specifier leave it blank:");
        System.out.print("1) Enter the symbol to search for:");
        searchSymbol = searchWord();
        System.out.print("2) Enter the key word(s) to search for:");
        searchKeyWord = searchWord();
        String []keywords = separate(searchKeyWord);
        System.out.print("3) Enter the price range to search for:");
        //loop until valid price range is entered
        do{
            searchPriceRange = searchWord();
            rangeType = validRange(searchPriceRange);
            if(rangeType == 0){
                System.out.print("Sorry, the entered price range was invalid. Try again:");
            }
        }while(rangeType == 0);
        
        //computing values for price range
        double min = -1;
        double max = -1;
        double specificPrice = -1;
        String []splitRange = searchPriceRange.split("-");
        searchPriceRange = searchPriceRange.replaceAll("-", "");
        //parsing the search range if it is not empty
        switch (rangeType){
            case 1:     //no price range search (left blank)
                break;
            case 2:     //specific price search (1000.00)
                specificPrice = Double.parseDouble(searchPriceRange);
                break;
            case 3:     //specific price or higher search (1000.00-)
                min = Double.parseDouble(searchPriceRange);
                break;
            case 4:     //specific price or lower search (-1000.00)
                max = Double.parseDouble(searchPriceRange);
                break;
            case 5:     //price range search (100.00-999.99)
                min = Double.parseDouble(splitRange[0]);
                max = Double.parseDouble(splitRange[1]);
                break;
        }      

        boolean keywordMatch, rangeMatch;
        String currentName;
        Double currentPrice;
        //loop to display all Stock metting search criteria
        for(Investment current: InvestmentList){
            keywordMatch = true;
            rangeMatch = false;
            currentName = current.getName().toLowerCase().concat(" ");
            currentPrice = current.getPrice();
            //display all investments
            if(searchSymbol.isBlank() && searchKeyWord.isBlank() && searchPriceRange.isBlank()){                                        //no symbol,    no word,    no range
                System.out.println(current.toString());
            }
            //search with only symbol specified
            else if(searchSymbol.equalsIgnoreCase(current.getSymbol()) && searchKeyWord.isBlank() && searchPriceRange.isBlank()){       //symbol,       no word,    no range
                System.out.println(current.toString());
            }
            //search with only keywords specified
            else if(searchSymbol.isBlank() && !searchKeyWord.isBlank() && searchPriceRange.isBlank()){                                  //no symbol,    word,       no range
                keywordMatch = showKeyword(keywords, currentName);
                if(keywordMatch == true) System.out.println(current.toString());
            }
            //search with symbol and keywords specified
            else if(searchSymbol.equalsIgnoreCase(current.getSymbol()) && !searchKeyWord.isBlank() && searchPriceRange.isBlank()){      //symbol,       word,       no range
                keywordMatch = showKeyword(keywords, currentName);
                if(keywordMatch == true) System.out.println(current.toString());
            }
            //search with just range specified
            else if(searchSymbol.isBlank() && searchKeyWord.isBlank() && !searchPriceRange.isBlank()){                                  //no symbol,    no word,    range
                rangeMatch = showRange(min, max, specificPrice, rangeType, currentPrice);
                if(rangeMatch == true) System.out.println(current.toString());
            }
            //search with symbol and range specified
            else if(searchSymbol.equalsIgnoreCase(current.getSymbol()) && searchKeyWord.isBlank() && !searchPriceRange.isBlank()){      //symbol,       no word,    range
                rangeMatch = showRange(min, max, specificPrice, rangeType, currentPrice);
                if(rangeMatch == true) System.out.println(current.toString());
            }
            //search with keywords and range specified
            else if(searchSymbol.isBlank() && !searchKeyWord.isBlank() && !searchPriceRange.isBlank()){                                 //no symbol,    word,       range
                keywordMatch = showKeyword(keywords, currentName);
                rangeMatch = showRange(min, max, specificPrice, rangeType, currentPrice);
                if(keywordMatch == true && rangeMatch == true) System.out.println(current.toString());
            }
            //search with all conditions specified
            else if(searchSymbol.equalsIgnoreCase(current.getSymbol()) && !searchKeyWord.isBlank() && !searchPriceRange.isBlank()){     //symbol,       word,       range
                keywordMatch = showKeyword(keywords, currentName);
                rangeMatch = showRange(min, max, specificPrice, rangeType, currentPrice);
                if(keywordMatch == true && rangeMatch == true) System.out.println(current.toString());
            }

        }
    }

    //------------------------------------------------write to file operation------------------------------------------------
    /**
     * Attempts to read from a file specified as the first command line param.
     * @param isInputFile Boolean representing if there was a command line argument or not.
     * @param args String array representing the command line arguments.
     */
    private static void readFromFile(boolean isInputFile, String[] args){
        //returns if there was no input file
        if(isInputFile == false){
            return;
        }
        //temporary values to hold information pulled from file
        String objectType, tempName, tempSymbol, fullLine;
        String[] partsOfLine;
        int tempQuantity;
        double tempPrice, tempBookValue;

        //try to read input file
        try {
            File inputFile = new File(args[0]);
            Scanner scanFile = new Scanner(inputFile);
            //loop through file until the end is reached, read in inforamtion for each investment
            while(scanFile.hasNextLine()){
                fullLine = scanFile.nextLine();
                partsOfLine = fullLine.split("\"");
                objectType = partsOfLine[1];                         //get objectType
                fullLine = scanFile.nextLine();
                partsOfLine = fullLine.split("\"");
                tempSymbol = partsOfLine[1];                        //get symbol
                fullLine = scanFile.nextLine();
                partsOfLine = fullLine.split("\"");
                tempName = partsOfLine[1];                          //get name
                fullLine = scanFile.nextLine();
                partsOfLine = fullLine.split("\"");
                tempQuantity = Integer.parseInt(partsOfLine[1]);    //get quantity
                fullLine = scanFile.nextLine();
                partsOfLine = fullLine.split("\"");
                tempPrice = Double.parseDouble(partsOfLine[1]);     //get price
                fullLine = scanFile.nextLine();
                partsOfLine = fullLine.split("\"");
                tempBookValue = Double.parseDouble(partsOfLine[1]); //get bookValue
                fullLine = scanFile.nextLine();

                //if Investment is of type Stock, create and add a new Stock object to ArrayList
                if(objectType.equalsIgnoreCase("Stock")){
                    Stock newStock = new Stock(tempName, tempSymbol, tempQuantity, tempPrice, tempBookValue);
                    InvestmentList.add(newStock);

                }
                //if Investment is of type MutualFund, create and add a new MutualFund object to ArrayList
                else if(objectType.equalsIgnoreCase("MutualFund")){
                    MutualFund newMutualFund = new MutualFund(tempName, tempSymbol, tempQuantity, tempPrice, tempBookValue);
                    InvestmentList.add(newMutualFund);
                }
                //if it is neither, create an add a new Investment object to ArrayList
                else{
                    Investment newInvestment = new Investment(tempName, tempSymbol, tempQuantity, tempPrice, tempBookValue);
                    InvestmentList.add(newInvestment);
                }
                addToHashMap(tempName.toLowerCase(), InvestmentList.size() - 1);

            }
            scanFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file. Running program without input data");
        }


    }

    //------------------------------------------------write to file operation------------------------------------------------
    /**
     * Writes to a file specified as the first command line param or if none was specified creating a new file.
     * @param isInputFile Boolean representing if there was a command line argument or not.
     * @param args String array representing the command line arguments.
     */
    private static void writeToFile(boolean isInputFile, String[] args){
        //checking if there are investments
        if(investmentsIsEmpty() == true && isInputFile == false){
            System.out.println("No investements in portfolio at this time and no file was specified to read from. Therefore: no file will be written to");
            return;
        }
        //check if there was information in Investments arraylist but no input file specified
        //if there was, get a file name from the user to store data in
        String fileName;
        if(investmentsIsEmpty() == false && isInputFile == false){
            System.out.println("No file was specified for input.");
            do {
                System.out.println("Enter the name of the file to save portfolio to:");
                fileName = scan.nextLine();
            } while (symbol.isBlank());
        }
        //if there was a file specified, set fileName to file specified
        else{
            fileName = args[0];
        }
        
        //try to write to file specified by fileName
        try {
            PrintWriter fileWriter = new PrintWriter(fileName);
            for(Investment temp: InvestmentList){                             //iterate though all objects in array list
                fileWriter.println(temp.toString());
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: could write to file " + args[0]);
            return;
        }

    }
    /**
     * Runs the ePortfolio program loop until such a time as the exit condition is entered.
    * @param args
    */
    public static void main(String[] args){
        int menuOption = 0;
        boolean readComandLineArgument = isCommandLineArgument(args);
        readFromFile(readComandLineArgument, args);

        while(menuOption != 6){
            
            dispalyMenu();
            menuOption = getCommandLoopOption();
            switch(menuOption){
                case 1:
                    buy();
                    break;
                case 2:
                    sell();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    getGain();
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    //ends the program
                    writeToFile(readComandLineArgument, args);
                    System.out.println("closing Program");
                    break;
            }
        }
    }
}
