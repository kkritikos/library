package library;

import java.util.ArrayList;
import java.util.List;

public class SellingBook extends Book{
	
	private int itemsSold = 0;
	private List<LibraryMember> members = new ArrayList<LibraryMember>();
	
	public SellingBook(String isbn, String title, int copyNumber) {
		super(isbn,title,copyNumber);
	}

	public int getItemsSold() {
		return itemsSold;
	}

	public boolean sell(LibraryMember lm) {
		if (itemsSold == getCopyNumber()) return false;
		itemsSold++;
		members.add(lm);
		return true;
	}
	
	public String toString() {
		String msg = super.toString();
		msg = "Selling" + msg + "[" + itemsSold + "]";
		return msg;
	}

}
