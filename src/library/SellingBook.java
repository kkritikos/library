package library;

import java.util.ArrayList;
import java.util.List;

public class SellingBook extends Book implements java.io.Serializable{
	private static final long serialVersionUID = -6885291462892832940L;
	
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
