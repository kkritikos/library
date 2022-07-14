package library.menu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import library.Book;
import library.LendingBook;
import library.Library;
import library.LibraryMember;
import library.SellingBook;

public class Menu {
	
	public static void writeLibraryState(Library l, String fileName) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
		}
		catch(Exception e) {
			System.out.println("Something went wrong while opening file for writing");
			System.out.println("Cause: " + e.getMessage());
			System.out.println("Exiting!!!");
			System.exit(-1);
		}
		
		try {
			oos.writeObject(l);
			oos.close();
		}
		catch(Exception e) {
			System.out.println("Something went wrong while attempting to write the state");
			System.out.println("Cause: " + e.getMessage());
			System.out.println("Exiting!!!");
			System.exit(-1);
		}
	}
	
	public static Library readLibraryState(String fileName) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fileName));
		}
		catch(Exception e) {
			System.out.println("Something went wrong while opening file for reading");
			System.out.println("Cause: " + e.getMessage());
			return null;
		}
		
		try {
			Library l = (Library)ois.readObject();
			ois.close();
			return l;
		}
		catch(Exception e) {
			System.out.println("Something went wrong while attempting to read the state");
			System.out.println("Cause: " + e.getMessage());
			System.out.println("Exiting!!!");
			System.exit(-1);
		}
		return null;
	}
	
	private static void getLibraryMember(Scanner input, Library library) {
		System.out.print("Please give id: ");
		String id = input.next();
		System.out.print("Please give name: ");
		String name = input.next();
		System.out.print("Please give member id: ");
		String memberId = input.next();
		
		LibraryMember lm = new LibraryMember(id,name,memberId);
		boolean result = library.addMember(lm);
		if (result) System.out.println(lm + " added successfully");
		else System.out.println("Probably " + lm + " already in library");
	}
	
	private static int getIntInput(Scanner input, String msg) {
		int value = 0;
		do {
			System.out.print(msg);
			try {
				value = input.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("Need to provide a positive integer!");
				input.next();
			}
		} while (value <= 0);
		
		return value;
	}
	
	private static Book getBook(Scanner input, boolean lending) {
		System.out.print("Please give isbn: ");
		String isbn = input.next();
		System.out.print("Please give title: ");
		String title = input.next();
		int copyNum = getIntInput(input, "Please give number of copies: ");
		
		if (lending) return new LendingBook(isbn,title,copyNum);
		else return new SellingBook(isbn,title,copyNum);
	}
	
	private static void getAddBook(Scanner input, boolean lending, Library library) {
		Book book = getBook(input,lending);
		boolean result = false;
		if (lending) result = library.addLendingBook((LendingBook)book);
		else result = library.addSellingBook((SellingBook)book);
		if (result) System.out.println("Book was succesfully added");
		else System.out.println("Book probably already added in library");
	}
	
	private static void lendOrSellBook(Scanner input, boolean lending, Library library) {
		System.out.println("Please give isbn: ");
		String isbn = input.next();
		System.out.println("Please give member id: ");
		String memberId = input.next();
		
		boolean result = false;
		String word1 = "lended";
		String word2 = "lend";
		if (lending) result = library.loanBook(isbn, memberId);
		else {
			result = library.sellBook(isbn, memberId);
			word1 = "sold";
			word2 = "sell";
		}
		if (result) System.out.println("Book was successfully " + word1);
		else System.out.println("Something went wrong while trying to " + word2 + " book "
				+ "with isbn: " + isbn + " to member with id: " + memberId);
	}
	
	private static void returnBook(Scanner input, Library library) {
		System.out.println("Please give isbn: ");
		String isbn = input.next();
		System.out.println("Please give member id: ");
		String memberId = input.next();
		
		boolean result = library.returnBook(isbn, memberId);
		if (result) System.out.println("Book was successfully returned");
		else System.out.println("Something went wrong while trying to return book");
	}

	public static void main(String[] args) {
		//Checking if library state exists so as to be loaded
		String fileName = "state.dat";
		Library library = readLibraryState(fileName);
		if (library == null) library = new Library("MyLibrary", "Address1");
		
		System.out.println("MENU for Library Management");
		int choice = 0;
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("\nPress 1 for adding new library member");
			System.out.println("Press 2 for adding new lending book");
			System.out.println("Press 3 for adding new selling book");
			System.out.println("Press 4 for lending a book");
			System.out.println("Press 5 for selling a book");
			System.out.println("Press 6 for returning back a book");
			System.out.println("Press 7 for printing library status");
			System.out.println("Press any other number to leave");
			System.out.print("Your choice: ");
			try {
				choice = input.nextInt();
			}
			catch(InputMismatchException e) {
				System.out.println("Did not give an integer!");
			}
			System.out.println();
			switch(choice) {
				case 1: getLibraryMember(input,library); break;
				case 2: getAddBook(input,true,library); break;
				case 3: getAddBook(input,false,library); break;
				case 4: lendOrSellBook(input, true, library); break;
				case 5: lendOrSellBook(input, false, library); break;
				case 6: returnBook(input,library); break;
				case 7: library.printStatus(); break;
			}
			//Store library state per each method that updates it
			if (choice >= 1 && choice <= 6) writeLibraryState(library,fileName); 
		} while (choice >= 1 && choice <= 7);
		
		input.close();
		System.out.println("Exited!!!");
	}

}
