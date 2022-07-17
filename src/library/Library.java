package library;

public class Library implements java.io.Serializable{
	private static final long serialVersionUID = -813561890224024887L;
	
	private String name, address;
	
	public Library(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public boolean addSellingBook(SellingBook sb) { return DatabaseConnector.insertSellingBook(sb); }
	
	public boolean addLendingBook(LendingBook lb) { return DatabaseConnector.insertLendingBook(lb); }
	
	public boolean addMember(LibraryMember lm) {
		return DatabaseConnector.insertMember(lm);
	}
	
	public boolean sellBook(String isbn, String memberId) {
		return DatabaseConnector.sellBook(isbn,memberId);
	}
	
	public boolean loanBook(String isbn, String memberId) {
		return DatabaseConnector.loanBook(isbn,memberId);
	}
	
	public boolean returnBook(String isbn, String memberId) {
		return DatabaseConnector.returnBook(isbn,memberId);
	}
	
	

	public String getName() { return name; }

	public String getAddress() { return address; }

	public String toString() {
		return "Library(" + name + ", " + address + ")";
	}
	
	public void printStatus() {
		System.out.println("Members:");
		DatabaseConnector.printLibraryMembers();
		System.out.println("Books to Lend:");
		DatabaseConnector.printLendingBooks();
		System.out.println("Books to Sell:");
		DatabaseConnector.printSellingBooks();
	}
}
