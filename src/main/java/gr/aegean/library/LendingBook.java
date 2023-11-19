package gr.aegean.library;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class LendingBook extends Book implements java.io.Serializable{
	private static final long serialVersionUID = -7321998230747812219L;
	
	private int itemsLended = 0;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<LibraryMember> lenders = new HashSet<LibraryMember>();
	
	public LendingBook() {
		
	}
	
	public LendingBook(String isbn, String title, int copyNumber) {
		super(isbn,title,copyNumber);
	}

	public int getItemsLended() {
		return itemsLended;
	}
	
	public void setItemsLended(int itemsLended) {
		this.itemsLended = itemsLended;
	}
	
	public boolean addLender(LibraryMember member) {
		if (super.getCopyNumber() > itemsLended && lenders.add(member)) {
			itemsLended++;
			System.out.println("Items lended: " + itemsLended);
			return true;
		}
		return false;
	}
	
	public boolean removeLender(LibraryMember member) {
		if (super.getCopyNumber() > 0 && lenders.remove(member)) {
			itemsLended--;
			return true;
		}
		return false;
	}
	
	public String toString() {
		String msg = super.toString();
		msg = "Lending" + msg + "[" + itemsLended + "]";
		return msg;
	}
	
	public boolean equals(Object o) {
		if (o instanceof LendingBook) {
			LendingBook sb = (LendingBook)o;
			return sb.getIsbn().equals(super.getIsbn());
		}
		return false;
	}
	
	public int hashCode() {
		return super.getIsbn().hashCode();
	}

}
