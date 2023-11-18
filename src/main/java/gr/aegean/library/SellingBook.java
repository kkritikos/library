package gr.aegean.library;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class SellingBook extends Book implements java.io.Serializable{
	private static final long serialVersionUID = -6885291462892832940L;
	
	private int itemsSold = 0;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<LibraryMember> buyers = new HashSet<LibraryMember>();
	
	protected SellingBook() {
		
	}
	
	public SellingBook(String isbn, String title, int copyNumber) {
		super(isbn,title,copyNumber);
	}

	public int getItemsSold() {
		return itemsSold;
	}
	
	public boolean addBuyer(LibraryMember member) {
		if (super.getCopyNumber() > itemsSold && buyers.add(member)) {
			itemsSold++;
			return true;
		}
		return false;
	}
	
	public String toString() {
		String msg = super.toString();
		msg = "Selling" + msg + "[" + itemsSold + "]";
		return msg;
	}
	
	public boolean equals(Object o) {
		if (o instanceof SellingBook) {
			SellingBook sb = (SellingBook)o;
			return sb.getIsbn().equals(super.getIsbn());
		}
		return false;
	}
	
	public int hashCode() {
		return super.getIsbn().hashCode();
	}

}
