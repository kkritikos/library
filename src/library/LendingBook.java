package library;

public class LendingBook extends Book implements java.io.Serializable{
	private static final long serialVersionUID = -7321998230747812219L;
	
	private int itemsLended = 0;
	
	public LendingBook(String isbn, String title, int copyNumber) {
		super(isbn,title,copyNumber);
	}

	public int getItemsLended() {
		return itemsLended;
	}
	
	public void setItemsLended(int itemsLended) {
		this.itemsLended = itemsLended;
	}
	
	public String toString() {
		String msg = super.toString();
		msg = "Lending" + msg + "[" + itemsLended + "]";
		return msg;
	}

}
