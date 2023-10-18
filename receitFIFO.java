package woah;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.InputMismatchException;
import java.text.DecimalFormat;

class Purchase{
		String item;
		double price;
		
	public Purchase(String item, double price) {
			this.item = item;
			this.price = price;
		}
	
	@Override
	public String toString() {
		DecimalFormat df =new DecimalFormat("#.##");
		return item + " | ₱ " + df.format(price);	
	}
}

class ReceiptQueue{
	private Deque<Purchase> queue = new ArrayDeque<>();
	private double totalAmnt = 0.00;
	private DecimalFormat df = new DecimalFormat("#.##");
	
	public void addPurchase(Purchase purchase) {
		queue.add(purchase);
		totalAmnt += purchase.price;
	}
	
	public void generateReceipt() {
		if(!queue.isEmpty()) {
			Purchase nxtPurchase = queue.poll();
			System.out.println("Receipt: " + nxtPurchase);
			totalAmnt -= nxtPurchase.price;
		}
		else {
			System.out.println("There are no purchases to generate.");
		}
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public void displayReceiptQueue() {
		if(queue.isEmpty()) {
			System.out.println("The Receipt Queue is empty.");			
		}
		else {
			System.out.println("Here's the current Receipt Queue: ");
			for(Purchase purchase : queue ) {
				System.out.println(purchase);
			}
			System.out.println("Total Amount: ₱ " + df.format(totalAmnt));
		}
	}
}

public class receitFIFO {
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    ReceiptQueue queue = new ReceiptQueue();

	    System.out.println("~~~Receipt Generator~~~");

	    while (true) {
	        try {
	            System.out.println("\n~~~~~WELCOME TO JYRE GROCERY~~~~");
	            System.out.println("1. Display Receipt Queue.");
	            System.out.println("2. Add Purchase.");
	            System.out.println("3. Generate Receipt.");
	            System.out.println("4. Exit.");
	            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	            System.out.println("Choose from the option:");
	            
	            int pick = sc.nextInt();
	            
	            if (pick == 1) {
	                queue.displayReceiptQueue();
	            } 
	            else if (pick == 2) {
	            	 String response;
	                 do {
	                     sc.nextLine(); // Consume the newline character
	                     System.out.print("Enter the item name: ");
	                     String itemName = sc.nextLine();

	                     System.out.print("Enter the item price: ");
	                     double itemPrice = sc.nextDouble();
	                     System.out.println("");

	                     Purchase purchase = new Purchase(itemName, itemPrice);

	                     queue.addPurchase(purchase);	
	                     System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	                     queue.displayReceiptQueue();
	                     System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	                     
	                     System.out.print("\nDo you want to add another purchase? (Y/N): ");
	                     response = sc.next();
	                 } 
	                 while (response.equalsIgnoreCase("y"));
	            }
	            else if (pick == 3) {
	                queue.generateReceipt();
	            } 
	            else if (pick == 4){
	                System.out.println("Thank you for using Jyre grocery, Please come again!");
	                sc.close();
	                System.exit(0);
	            }
	            else {
	            	System.out.println("Invalid input, Please enter a number from 1-4 only!");
	            }
	        } 
	        catch (InputMismatchException e) {
	            System.out.println("Invalid input, Please enter a number from 1-4 only!");
	            sc.nextLine(); 
	        }
	    }
	}
}