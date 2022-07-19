package library;

public abstract class Book implements java.io.Serializable{
	private static final long serialVersionUID = -992069083415874065L;
	
	private final String isbn;
	private final String title;
	private int copyNumber = 0;
	
	public Book(String isbn, String title, int copyNumber) {
		this.isbn = isbn;
		this.title = title;
		this.copyNumber = copyNumber;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}
	
	public int getCopyNumber() {
		return copyNumber;
	}
	
	public void setCopyNumber(int copyNumber) {
		this.copyNumber = copyNumber;
	}
	
	public String toString() {
		return "Book(" + isbn + ", " + title + ", " + copyNumber + ")";
	}
	
	public boolean equals(Object o) {
		if (o instanceof Book) {
			Book b = (Book)o;
			if (b.isbn.equals(isbn)) return true;
		}
		return false;
	}
	
	public int hashCode() {
		return isbn.hashCode();
	}

}
