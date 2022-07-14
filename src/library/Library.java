package library;

import java.util.HashSet;
import java.util.Set;

public class Library implements java.io.Serializable{
	private static final long serialVersionUID = -813561890224024887L;
	
	private String name, address;
	private Set<Book> sellingBooks = new HashSet<Book>(), lendingBooks = new HashSet<Book>();
	private Set<LibraryMember> members = new HashSet<LibraryMember>();
	
	public Library(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public boolean addSellingBook(SellingBook sb) { return sellingBooks.add(sb); }
	
	public boolean addLendingBook(LendingBook lb) { return lendingBooks.add(lb); }
	
	public boolean addMember(LibraryMember lm) {
		//New member id should be unique
		for (LibraryMember member: members)
			if (member.getMemberId().equals(lm.getMemberId())) {
				return false;
			}
		
		return members.add(lm);
	}
	
	private LibraryMember getMember(String memberId) {
		for (LibraryMember lm: members) if (lm.getId().equals(memberId)) return lm;
		return null;
	}
	
	private Book getSellingBook(String isbn) {
		Book book = new SellingBook(isbn,"",1);
		for (Book b: sellingBooks) {
			if (b.equals(book)) {
				return b;
			}
		}
		return null;
	}
	
	private Book getLendingBook(String isbn) {
		Book book = new LendingBook(isbn,"",1);
		for (Book b: lendingBooks) {
			if (b.equals(book)) {
				return b;
			}
		}
		return null;
	}
	
	public boolean sellBook(String isbn, String memberId) {
		Book b = getSellingBook(isbn);
		LibraryMember lm = getMember(memberId);
		if (b != null && lm != null) {
			SellingBook sb = (SellingBook)b;
			return sb.sell(lm);
		}
		return false;
	}
	
	public boolean loanBook(String isbn, String memberId) {
		Book b = getLendingBook(isbn);
		LibraryMember lm = getMember(memberId);
		if (b != null && lm != null) {
			LendingBook lb = (LendingBook)b;
			return lb.loan(lm);
		}
		return false;
	}
	
	public boolean returnBook(String isbn, String memberId) {
		Book b = getLendingBook(isbn);
		LibraryMember lm = getMember(memberId);
		if (b != null && lm != null) {
			LendingBook lb = (LendingBook)b;
			return lb.returnBack(lm);
		}
		return false;
	}
	
	

	public String getName() { return name; }

	public String getAddress() { return address; }

	public String toString() {
		return "Library(" + name + ", " + address + ")";
	}
	
	public void printStatus() {
		System.out.println("Members:");
		for (LibraryMember lm: members) System.out.println(lm);
		System.out.println("Books to Lend:");
		for (Book lb: lendingBooks) System.out.println(lb);
		System.out.println("Books to Sell:");
		for (Book lb: sellingBooks) System.out.println(lb);
	}
}
