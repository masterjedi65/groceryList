package groceryListManager;

import java.util.Scanner;
import java.util.ArrayList;
//Alex Chapa Jr

public class groceryList {
public static void main(String[] args) {
	
	Scanner iScan = new Scanner(System.in);
	String userInput = "null";
	
	// Create our arraylists
	ArrayList<String> itemArray = new ArrayList<String>();
	ArrayList<Boolean> checkOff = new ArrayList<Boolean>();
	
	
	// Enter in a loop and provide menu options to our user
	
	while(!userInput.equals("Exit")) {
	System.out.println("Welcome to Grocery List Management!");
	System.out.println("1.\tAdd Item to your Grocery List");
	System.out.println("2.\tRemove item from your Grocery List");
	System.out.println("3.\t\"Check Off\" an Item from your Grocery List");
	System.out.println("4.\tDisplay your Grocery List");
	System.out.println("5.\tExit");

	System.out.println();
	System.out.print("Please enter your desired option in the format: number (ex: 1): ");
	
	
	// Here we will accept user input
	
	userInput = iScan.next();
	String nextFoodItem = null;
	boolean checked = false;

	// Generate menus and, based on user response, provide the method
	if(userInput.equals("1")) {
		System.out.println();
		addItem(iScan, nextFoodItem, itemArray, checkOff, checked);
	}else if(userInput.equals("2")) {
		System.out.println();
		removeItem(iScan, nextFoodItem, itemArray, checkOff, checked);
	}else if(userInput.equals("3")) {
		System.out.println();
		checkOff(iScan, nextFoodItem, itemArray, checkOff, checked);
	}else if(userInput.equals("4")){
		System.out.println();
		printList(iScan, nextFoodItem, itemArray, checkOff, checked);
	}else if(userInput.equals("5")) {
		System.out.println();
		System.out.println("You have exited the application, here is the final list of Grocery Items:");
		printList(iScan, nextFoodItem, itemArray, checkOff, checked);
		break;
	}else {
		System.out.println();
		System.out.println("Invalid Input.");
	}
	}
	// Close Scanner
	iScan.close();
}

// Our addItem method where we add items to our grocery list and check for case sensitivity
	public static void addItem(Scanner iScan, String nextFoodItem, ArrayList<String> itemArray, ArrayList<Boolean> checkOff, boolean checked) {
		System.out.print("Please add the items you would wish to add to the list: ");
		System.out.println();
		nextFoodItem = iScan.next();
		
		// Iterator to check if, even in consideration of case, a item exists
		boolean inIt = false;
		for(String item : itemArray) {
			if(item.equalsIgnoreCase(nextFoodItem)) {
				inIt = true;
				break;
			}
		}
		
		// Here we will check if the item both is not in the array
		
		if(!itemArray.contains(nextFoodItem) && !inIt == true) {
			itemArray.add(nextFoodItem);
			checkOff.add( false);
			System.out.println("The item has been added\n");
		}else {
			System.out.println("The item is already in the list!");
		}
		}
		
	// Here we will remove items from our list with our removeItem method
	
	public static void removeItem(Scanner iScan, String nextFoodItem, ArrayList<String> itemArray, ArrayList<Boolean> checkOff, boolean checked) {
		System.out.println();
	// Prompt user for number (ref. to index) or name of item to remove
	// Also, we create and set index to -1
	System.out.println("Please enter the name or number of the item you would like to remove: ");
	int index = -1;
	nextFoodItem = iScan.next();
	
	// Try and catch method for elements (treated as int) and strings passed for item to be removed
	
	try {
		
	// Obtaining index for variable
		
	index = Integer.parseInt(nextFoodItem) - 1;
	
	// Checking if index exists
	
	if(index >= 0 && index < itemArray.size()){
	itemArray.remove(index);
	checkOff.remove(index);
	System.out.println("Item has been removed!");
	}else {
	System.out.println("Item has not been removed!");
	}
	}catch(NumberFormatException e) {	// Our catch for strings and we also iterate to disregard case sensitivity to remove items
		for(int i = 0; i < itemArray.size(); i++) {
		if(itemArray.get(i).equalsIgnoreCase(nextFoodItem)) {
		index = i;
		break;
		}
		}
		
		// Checking if index exists in our arrayList and properly removing the index and its value from both arrayLists
		
		if(index >= 0 && index < itemArray.size()){
		itemArray.remove(index);
		checkOff.remove(index);
		System.out.println("Item has been removed!");
		System.out.println();
		}else {
			System.out.println("Invalid Input");
		}
	}
	}
	
	// Here is our checkOff method, where we will check items from our grocery list
	
	public static void checkOff(Scanner iScan, String nextFoodItem, ArrayList<String> itemArray, ArrayList<Boolean> checkOff, boolean checked) {
		
		// Prompting user for item/index to check off and setting index = -1
		
		System.out.println();
		System.out.println("Please enter the name of the item or number you wish to check off: ");
		nextFoodItem = iScan.next();
		int index = -1;
		
		// Try and catch method for element and string passed
		
		try {
			index = Integer.parseInt(nextFoodItem) - 1;
			
			// Checking if index exists in our arrayList exists
			
			if(index>= 0 && index < itemArray.size()) {
			if(checkOff.get(index) == true) {
				System.out.println("It's in our array and has been checked off.");
			}else if(checkOff.get(index) == false){
				System.out.println("The item is in our array and has now been checked off.");
				checkOff.set(index, true);
			}else {
				System.out.println("Invalid");
			}
					System.out.println();
			}
	}catch(NumberFormatException e ){			// Here we will disregard case sensitivity and check if a string exists in our arrayList
		for(int i = 0; i < itemArray.size(); i++) {
		if(itemArray.get(i).equalsIgnoreCase(nextFoodItem)){
			index = i;
			break;
		}
		}
		
		// Testing if index is within our array and checked off or not and providing results accordingly
		
		if(index >= 0 && index < itemArray.size()) 
		{
			if(checkOff.get(index)){
				System.out.println(nextFoodItem + " is in our array.");
				System.out.println("The item is already checked off.");
			}else{
				System.out.println(nextFoodItem + " is in our array.");
				checkOff.set(index, true);
				System.out.println("The item is checked off.");
			}
		}else {
				System.out.println("Invalid");
			}
		System.out.println();
		}
	}
	
	// Here we will print our values in our printList method based on if they are checked and outputting them formatted
	
	public static void printList(Scanner iScan, String nextFoodItem, ArrayList<String> itemArray, ArrayList<Boolean> checkOff, boolean checked) {
	System.out.println("Print List:");
	for(int i = 0; i < itemArray.size(); i++) {
		if(checkOff.get(i) == false) {
		System.out.print((i + 1) + " - " + itemArray.get(i));
	}else if(checkOff.get(i) == true) {
		System.out.print((i + 1) + " x " + itemArray.get(i));
	}else{
		System.out.println("Error");
	}
		System.out.println();
	}
	}
}

