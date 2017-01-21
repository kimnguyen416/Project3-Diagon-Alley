//Name: Kim Nguyen
//80813737
//Section#:9504
//TA: Val
//Project Number: 3
//Brief Description of File Contents:Shopping Guide for Diagon Alley
import java.util.Scanner;

public class DiagonAlleyGuide {
	static String[] iNList = {"Broom" ,"School robes", "Wand", "The Standard Book of Spells",
			"A History of Magic", "Magical Drafts and Potions", "Cauldron"};
	static boolean[] haveOrNot = {false, false, false, false, false, false, false};
	public static void main(String[] args) {
		
	//Creates Scanner object
	Scanner input = new Scanner(System.in);
		
	//Declaration of variables
	int selection;//general menu
	int bankChoice = 0;//for bank menu
	int shoppesChoice = 0;//for shoppes menu
	int broomstixChoice = 0; //broomstix menu
	int secondHandChoice = 0; //second-hand robes menu
	int olivandersChoice = 0;//olivanders menu
	int flourishBChoice = 0; //Flourish and Blotts menu
	int potageCChoice = 0; //Potage's Cauldron shop menu
	
	boolean y = false;//just to keep code running. 
		
	//currencies
	double USD = 0;//U.S. dollars to exchange to wizard coins at bank, *do the thing where you want a set amount of digits for output
	int knut = 0;
	int galleon = 0;
	int sickle = 0;
	int totalKnut = 0;
	int totalItem = 0;//for selection 4 to exit out completely
		
	System.out.println("Welcome to Diagon Alley!");
	
	//False as in you don't have all the items yet	
	while (y == false){
	//This calls the mainMenu from below
	mainMenu();
	//Selection from choices above
	selection = input.nextInt();

	//Conditions to make sure the 1 < selection >= 5
	if (selection < 1 || selection > 4){
		System.out.println("\nInvalid entry!");
		continue;
	}
		
	//medium loop for bank
	while (selection == 1){
		//while(bankChoice != 3){
		//calls Gringotts bank method from below
		bankGMenu();
		bankChoice = input.nextInt();
		
		int bankChoiceC = 0; //makes sure they have to at least exchange once to get proper check balance
		
		//make sure the 1 < bankChoice >= 3
		if (bankChoice < 1 || bankChoice > 3){
			System.out.println("\nInvalid entry!");
			continue;
		}
			
		//Exchange Money Menu
		if (bankChoice == 1){
			System.out.println("\nHow much would you like to exchange?");
			System.out.print("USD: ");
			USD = input.nextDouble();
			//call exchange methods here
			int exchangeGalleons = 0;
   		 	int exchangeSickles = 0;
   		 	int exchangeKnuts = 0;
   		 	exchangeGalleons += exchangeGalleons(USD);
   		 	exchangeSickles += exchangeSickles(USD - exchangeGalleons * 49.3);
   		 	exchangeKnuts += exchangeKnuts(USD - exchangeGalleons * 49.3 - exchangeSickles * 2.9);
   		    System.out.println("\nTransaction Complete!");
		     galleon += exchangeGalleons;
		     sickle += exchangeSickles;
		     knut += exchangeKnuts;
		     
		     //Watch out for the knuts exceptions
		     if (knut > 29){
		    	 sickle += knut / 29;
		    	 knut = knut % 29;
		     }
		     if (sickle > 17){
		    	 galleon += sickle / 17;
		    	 sickle = sickle % 17;
		     }
		     
		     
		     totalKnut = (galleon * 493) + (sickle * 29) + knut;
		     
		
		     bankChoiceC++;
		}
			
		//Check Balance Menu
		else if (bankChoice == 2){
			if (bankChoiceC > 0){
				System.out.println("\nYou have 0 Galleons, 0 Sickles, and 0 Knuts.");
			}
			else{
				System.out.println();
				if (galleon != 1 && sickle != 1 && knut != 1){
				balance(galleon,sickle, knut);
				}
				else if (galleon == 1 && sickle != 1 && knut != 1){
					System.out.println("You have " + galleon + " Galleon, " +  sickle
					+ " Sickles, and " + knut + " Knuts.");
				}
				else if (galleon != 1 && sickle == 1 && knut != 1){
					System.out.println("You have " + galleon + " Galleons, " +  sickle
					+ " Sickle, and " + knut + " Knuts.");
				}
				else if(galleon != 1 && sickle != 1 && knut == 1){
					System.out.println("You have " + galleon + " Galleons, " +  sickle
					+ " Sickles, and " + knut + " Knut.");
				}
				else if (galleon == 1 && sickle == 1 && knut != 1){
					System.out.println("You have " + galleon + " Galleon, " +  sickle
					+ " Sickle, and " + knut + " Knuts.");
				}
				else if (galleon == 1 && sickle != 1 && knut == 1){
					System.out.println("You have " + galleon + " Galleon, " +  sickle
					+ " Sickles, and " + knut + " Knut.");
				}
				else if (galleon != 1 && sickle == 1 && knut == 1){
					System.out.println("You have " + galleon + " Galleons, " +  sickle
					+ " Sickle, and " + knut + " Knut.");
				}
			}
		}
			
		//The user chooses to exit the bank
		else{
			break;
		}
	}
		
		//Pull up inventory/need list
	while (selection == 2){
			inventoryList();
		System.out.println();
			needList();
			break;
		}
		
		//gigantic loop for all shoppes
	while (selection == 3){
			//calls all of the shoppes method from below
			shoppesMenu();
			shoppesChoice = input.nextInt();
			
			//make sure the 1 < shoppesChoice >= 7
			if (shoppesChoice < 1 || shoppesChoice > 6){
				System.out.println("\nInvalid entry!");
				continue;
			}
			
			//Broomstix shop
			if (shoppesChoice == 1){
				boolean bC = true;
				while (bC != false){
				broomStixMenu();
				System.out.print("Selection: ");
				broomstixChoice = input.nextInt();
				
		    	if(broomstixChoice < 1 || broomstixChoice >= 3){
						//make sure the 1 < broomstixChoice >= 3
						System.out.println("\nInvalid entry!");
						continue;
				}
				if (broomstixChoice == 1){
					int cost = 493;
					if (enoughMoney(galleon, sickle, knut, cost) && haveOrNot[0] != true){
						System.out.println("\nTransaction successful!");
						totalKnut -= cost;
						galleon = kToG(totalKnut);
						sickle = kToS(totalKnut);
						knut = kRemainder(totalKnut);
						haveOrNot[0] = true;	
						totalItem++;
					}
					else if (!enoughMoney(galleon, sickle, knut, cost)){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You do not have enough money!");
					}
					else if (haveOrNot[0] == true){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You already have this!");
					}
					
						
					}
				//User chooses to exit broom shop
				else{
					System.out.println();
					bC = false;
					break;
				}
				}
			}
			
			//Second-Hand Robes shop
			else if (shoppesChoice == 2){
				boolean sC = true;
				while (sC != false){
				System.out.println("\nSecond-Hand Robes");
				System.out.println("1. Buy School robes for 12 Sickles");
				System.out.println("2. Exit\n");
				System.out.print("Selection: ");
				secondHandChoice = input.nextInt();
				
				//make sure the 1 < secondHandChoice >= 3
				if (secondHandChoice < 1 || secondHandChoice >= 3){
					System.out.println("\nInvalid entry!");
					continue;
				}
				
				if (secondHandChoice == 1){
					int cost = 348;
					if (enoughMoney(galleon, sickle, knut, cost) && haveOrNot[1] != true){
						System.out.println("\nTransaction successful!");
						totalKnut -= cost;
						galleon = kToG(totalKnut);
						sickle = kToS(totalKnut);
						knut = kRemainder(totalKnut);
						haveOrNot[1] = true;	
						totalItem++;
					}
					else if (!enoughMoney(galleon, sickle, knut, cost)){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You do not have enough money!");
					}
					else if (haveOrNot[1] == true){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You already have this!");
					}
					
				}
				//user chooses to exit secondhand shop
				else{
					System.out.println();
					sC = false;
					break;
				}
				}
			}
			
			//Olivanders shop
			else if (shoppesChoice == 3){
				boolean oC = true;
				while (oC != false){
				System.out.println("\nOlivanders");
				System.out.println("1. Buy Wand for 7 Sickles");
				System.out.println("2. Exit\n");
				System.out.print("Selection: ");
				olivandersChoice = input.nextInt();
				
				//make sure the 1 < olivandersChoice >= 3
				if (olivandersChoice < 1 || olivandersChoice >= 3){
					System.out.println("\nInvalid entry!");
					continue;
				}
				
				if (olivandersChoice == 1){
					int cost = 203;
					if (enoughMoney(galleon, sickle, knut, cost) && haveOrNot[2] != true){
						System.out.println("\nTransaction successful!");
						totalKnut -= cost;
						galleon = kToG(totalKnut);
						sickle = kToS(totalKnut);
						knut = kRemainder(totalKnut);
						haveOrNot[2] = true;
						totalItem++;
					}
					else if (!enoughMoney(galleon, sickle, knut, cost)){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You do not have enough money!");
					}
					else if (haveOrNot[2] == true){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You already have this!");
					}
					}
					
				//user chooses to exit olivander shop
				else{
					System.out.println();
					oC = false;
					break;
				}
				}
			}
			//Flourish and Blotts shop
			else if (shoppesChoice == 4){
				boolean fC = true;
				while (fC != false){
				//calls Flourish and Blotts menu below
				flourishBMenu();
				flourishBChoice = input.nextInt();
				
				//make sure the 1 < flourishBChoice >= 5
				if (flourishBChoice < 1 || flourishBChoice >= 5){
					System.out.println("\nInvalid entry!");
					continue;
				}
				
				if (flourishBChoice == 1){
					int cost = 145;
					if (enoughMoney(galleon, sickle, knut, cost) && haveOrNot[3] != true){
						System.out.println("\nTransaction successful!");
						totalKnut -= cost;
						galleon = kToG(totalKnut);
						sickle = kToS(totalKnut);
						knut = kRemainder(totalKnut);
						haveOrNot[3] = true;	
						totalItem++;
					}
					else if (!enoughMoney(galleon, sickle, knut, cost)){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You do not have enough money!");
					}
					else if (haveOrNot[3] == true){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You already have this!");
					}
				}
				
				else if (flourishBChoice == 2){
					int cost = 99;
					if (enoughMoney(galleon, sickle, knut, cost) && haveOrNot[4] != true){
						System.out.println("\nTransaction successful!");
						totalKnut -= cost;
						galleon = kToG(totalKnut);
						sickle = kToS(totalKnut);
						knut = kRemainder(totalKnut);
						haveOrNot[4] = true;	
						totalItem++;
					}
					else if (!enoughMoney(galleon, sickle, knut, cost)){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You do not have enough money!");
					}
					else if (haveOrNot[4] == true){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You already have this!");
					}
				}
				
				else if (flourishBChoice == 3){
					int cost = 27;
					if (enoughMoney(galleon, sickle, knut, cost) && haveOrNot[5] != true){
						System.out.println("\nTransaction successful!");
						totalKnut -= cost;
						galleon = kToG(totalKnut);
						sickle = kToS(totalKnut);
						knut = kRemainder(totalKnut);
						haveOrNot[5] = true;
						totalItem++;
					}
					else if (!enoughMoney(galleon, sickle, knut, cost)){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You do not have enough money!");
					}
					else if (haveOrNot[5] == true){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You already have this!");
					}
				}
				
				//user chooses to exit flourishB shop
				else{
					System.out.println();
					fC = false;
					break;
				}
				}
			}
			//Potage's Cauldron shop
			else if (shoppesChoice == 5){
				boolean pC = true;
				while (pC != false){
				System.out.println("\nPotage's Cauldron Shop");
				System.out.println("1. Buy Cauldron for 10 Sickles");
				System.out.println("2. Exit\n");
				System.out.print("Selection: ");
				potageCChoice = input.nextInt();
				
				//make sure the 1 < potageCChoice >= 3
				if (potageCChoice < 1 || potageCChoice >= 3){
					System.out.println("\nInvalid entry!");
					continue;
				}
				
				if (potageCChoice == 1){
					int cost = 290;
					if (enoughMoney(galleon, sickle, knut, cost) && haveOrNot[6] != true){
						System.out.println("\nTransaction successful!");
						totalKnut -= cost;
						galleon = kToG(totalKnut);
						sickle = kToS(totalKnut);
						knut = kRemainder(totalKnut);
						haveOrNot[6] = true;	
						totalItem++;
					}
					else if (!enoughMoney(galleon, sickle, knut, cost)){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You do not have enough money!");
					}
					else if (haveOrNot[6] == true){
						System.out.println("\nTransaction failed!");
			    		System.out.println("You already have this!");
					}
				}
				//user chooses to exit potageC shop
				else {
					System.out.println();
					pC = false;
					break;
				}
				}
			}
				//user chooses to exit out of all shoppes
				else {
					break;
				}	
	}
		
	while (selection == 4){
			if (totalItem == 0){
				System.out.println("\nYou have no supplies!");
				System.out.println();
				break;
			}
			else if (totalItem > 0 && totalItem != 7){
				System.out.println("\nYou are missing some items!");
				System.out.println("Missing:");
				for (int i = 0; i < iNList.length ; i++){
					if(haveOrNot[i] == false){
						System.out.println(iNList[i]);
					}
				}
				System.out.println();
				break;
			}
			else if (totalItem == 7){
				System.out.println("\nHave a nice day!!");
				System.out.println();
				y = true;
				System.exit(1);
			}
	}
	}//closes the big loop
		

	}//main method closing braces
	
	//METHODS TO USE FOR STUFF ABOVE!!!!
	
		//Menu METHODS
		public static void mainMenu(){
			//Main Menu/Start Menu
			System.out.println("\nMain Menu:");
			System.out.println("1. Gringotts Bank");
			System.out.println("2. List of Supplies");
			System.out.println("3. Shoppes");
			System.out.println("4. Leave");
			System.out.println();
			//Selection from choices above
			System.out.print("Selection: ");
		}
		
		public static void shoppesMenu(){
			//pulls up shop menu
			System.out.println("\nShoppes");
			System.out.println("1. Broomstix");
			System.out.println("2. Second-Hand Robes");
			System.out.println("3. Olivanders");
			System.out.println("4. Flourish and Blotts");
			System.out.println("5. Potage's Cauldron Shop");
			System.out.println("6. Exit\n");
			System.out.print("Selection: ");
		}
		
		public static void bankGMenu(){
			//Pulls up bank menu
			System.out.println("\nGringotts Bank");
			System.out.println("1. Exchange Money");
			System.out.println("2. Check Balance");
			System.out.println("3. Exit\n");
			//Selection from choices above
			System.out.print("Selection: ");
		}
		
		public static void flourishBMenu(){
			//calls flourish and Blotts menu when invoked above
			System.out.println("\nFlourish and Blotts");
			System.out.println("1. Buy The Standard Book of Spells for 5 Sickles");
			System.out.println("2. Buy A History of Magic for 3 Sickles and 12 Knuts");
			System.out.println("3. Buy Magical Drafts and Potions for 27 Knuts");
			System.out.println("4. Exit\n");
			System.out.print("Selection: ");
		}
		
		public static void broomStixMenu(){
			System.out.println("\nBroomstix");
			System.out.println("1. Buy Broom for 1 Galleon");
			System.out.println("2. Exit\n");
		}
		
		public static int exchangeGalleons(double USD){
			//gives you correct amount of galleons during bank exchange
			int results = 0;
			results = (int)(USD / 49.3);
			return results;
		}
		
		
		public static int exchangeSickles(double USD){
			//gives you correct amount of sickles during bank exchange
			int results = 0;
			results = (int)(USD / 2.9);
			return results;
		}
		
		
		public static int exchangeKnuts(double USD){
			//gives you correct amount of knuts during bank exchange
			int results = 0;
			results = (int)(USD / 0.1);
			return results;
		}
		
		public static void balance(int galleon, int sickle, int knut){
			//Prints out your balance. Singularity is noted.
			
			System.out.println("You have " + galleon + " Galleons, " +  sickle
			+ " Sickles, and " + knut + " Knuts.");
			
		}
		
		public static boolean enoughMoney(int galleon, int sickle, int knut, int cost){
			if (galleon * 493 + sickle * 29 + knut >= cost){
				return true;
			}
			return false;
		}
		
		public static int kToG(int totalKnut){
			int galleon = totalKnut / 493;
			return galleon;
		}
		
		public static int kToS(int totalKnut){
			int knut = kRemainder(totalKnut);
			int leftOver = totalKnut - knut;
			int sickle = ((leftOver / 29) % 17);
			return sickle;
		}
		
		public static int kRemainder(int totalKnut){
			int knut = ((totalKnut % 493) % 29);
			return knut;
		}
		
		public static void needList(){
			System.out.println("Need:");
			for (int x = 0; x < iNList.length ; x++){
				if(haveOrNot[x] == false){
					System.out.println(iNList[x]);
				}
		}
		}

		public static void inventoryList(){
			System.out.println("\nInventory:");
			for (int x = 0; x < iNList.length; x++){
				if (haveOrNot[x] == true){
					System.out.println(iNList[x]);
				}
			}
		}
}

