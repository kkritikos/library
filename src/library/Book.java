package library;

public abstract class Book {
	private String isbn;
	private String title;
	private int copyNumber = 0;
	
	public Book(String isbn, String title, int copyNumber) {
		this.isbn = isbn;
		this.title = title;
		this.copyNumber = copyNumber;
	}
	
	public String getIsbn() {
		return new String(isbn);
	}

	public String getTitle() {
		return new String(title);
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
