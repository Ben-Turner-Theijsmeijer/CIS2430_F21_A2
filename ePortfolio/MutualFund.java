package bturnert_a2.ePortfolio;

/**
 * This class creates and edits objects of type MutualFund.
 * <p> 
 * SubClass of the Investment class. 
 * MutualFund contains inforamtion on a MutualFund's name, symbol, price, quantity, and book value
 *
 * @author Ben Turner-Theijsmeijer 
 * @version 2.0
 */
public class MutualFund extends Investment{
    //--------------------------------MutualFund Specific variables--------------------------------
    private static int redemtionFee = 45;

    //--------------------------------Constructors--------------------------------
    /**
     * Create a new MutualFund object, given the data provided.
     * @param symbolOfMutualFund represents the symbol for the MutualFund object. This is required as the only perameter as it must be specfied beforehand in the even the MutualFund already exists.
     */
    public MutualFund(String symbolOfMutualFund){
        super(symbolOfMutualFund);
    }
    /**
     * Create a new MutualFund object, given the data provided.
     * @param newName String passed to method representing the new name for the object.
     * @param newSymbol String passed to method representing the new symbol for the object.
     * @param newQuantity Int passed to method representing the new quantity for the object.
     * @param newPrice Double passed to method representing the new Price for the object.
     * @param newBookValue Double passed to method representing the new book value that has been calcuated elsewhere.
     */
    public MutualFund(String newName, String newSymbol, int newQuantity, double newPrice, double newBookValue){
        super(newName, newSymbol, newQuantity, newPrice, newBookValue);
    }
    
    //--------------------------------toString function--------------------------------
    @Override
    public String toString() {
        return "Type = \"MutualFund\"" + "\nSymbol = \"" + getSymbol() + "\"\nName = \"" + getName() + "\"\nQuantity = \"" + getQuantity() + "\"\nPrice = \"" + getPrice() + "\"\nBookValue = \"" + getBookValue() + "\"\n";
    }

    //--------------------------------Sell Operations on MutualFund--------------------------------
    /**
     * Prompts the user for the quantity of the current MutualFund object to be sold. Scans an integer from system.in and removes what was read from the current MutualFund's quantity. 
     * Prints the gain from selling the specified quantity of the MutualFund object
     * If after selling, the quantity for the current MutualFund object equals 0, returns 1.
     * If after selling, the quantity for the current MutualFund object does not equal 0, updates the book value for the current MutualFund object and returns 0.
     * <p>
     * Loops  until a valid integer value is entered by the user.
     * @return Returns either the interger 0 representing that not all of the MutualFund object was sold or 1 representing all of the MutualFund object was sold.
     */
    public int sellUpdateQuantity(){
        double sellValue = 0;
        double gain = 0;
        double tempBookValue = getBookValue();
        double currentPrice = getPrice();
        int currentQuantity = getQuantity();
        int tempQuantity = sellTempQuantity();
        //selling the whole mutual fund
        if(tempQuantity == currentQuantity){
            sellValue = currentPrice * tempQuantity - redemtionFee;
            gain = sellValue - tempBookValue;
            System.out.printf("You sold all of this mutual fund for a total gain of: $%.2f\n", gain);
            return 1;   //indicating object deletion from arraylist
        }
        //selling only part
        else{
            sellValue = currentPrice * tempQuantity - redemtionFee;
            tempBookValue = tempBookValue * (tempQuantity / currentQuantity);
            gain = sellValue - tempBookValue;
            System.out.printf("You sold part of this mutual fund for a total gain of: $%.2f\n", gain);
            //updating bookvalue
            lowerBookValue(tempBookValue);
        }
        //updating quantity
        lowerQuantity(tempQuantity);
        return 0;   //indicating not to delete the object
    }

    //--------------------------------Get Gain Operation on MutualFund--------------------------------
    /**
     * Calculates the gain aquired if all currently held MutualFund objects were sold completely at their current prices.
     * @return Returns a double representing the gain aquired if all currently held MutualFund objects were sold completely at their current prices.
     */
    public double getGain(){
        int tempQuantity = getQuantity();
        double tempPrice = getPrice();
        double tempBookValue = getBookValue();
        double sellValue = tempPrice * tempQuantity - redemtionFee;
        double gain = sellValue - tempBookValue;
        return gain;
    }
}
