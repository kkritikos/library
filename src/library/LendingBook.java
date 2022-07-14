package library;

import java.util.HashSet;
import java.util.Set;

public class LendingBook extends Book implements java.io.Serializable{
	private static final long serialVersionUID = -7321998230747812219L;
	
	private int itemsLended = 0;
	private Set<LibraryMember> members = new HashSet<LibraryMember>();
	
	public LendingBook(String isbn, String title, int copyNumber) {
		super(isbn,title,copyNumber);
	}

	public int getItemsLended() {
		return itemsLended;
	}
	
	public boolean loan(LibraryMember lm) {
		if (members.contains(lm) || itemsLended == getCopyNumber()) return false;
		itemsLended++;
		members.add(lm);
		return true;
	}
	
	public boolean returnBack(LibraryMember lm) {
		if (!members.contains(lm) || itemsLended == 0) return false;
		itemsLended--;
		members.remove(lm);
		return true;
	}
	
	public String toString() {
		String msg = super.toString();
		msg = "Lending" + msg + "[" + itemsLended + "]";
		return msg;
	}

}
