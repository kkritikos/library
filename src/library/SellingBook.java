package library;

public class SellingBook extends Book implements java.io.Serializable{
	private static final long serialVersionUID = -6885291462892832940L;
	
	private int itemsSold = 0;
	
	public SellingBook(String isbn, String title, int copyNumber) {
		super(isbn,title,copyNumber);
	}

	public int getItemsSold() {
		return itemsSold;
	}
	
	public void setItemsSold(int itemsSold) {
		this.itemsSold = itemsSold;
	}
	
	public String toString() {
		String msg = super.toString();
		msg = "Selling" + msg + "[" + itemsSold + "]";
		return msg;
	}

}
