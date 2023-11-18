package gr.aegean.library;

import java.util.UUID;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Book implements java.io.Serializable{
	private static final long serialVersionUID = -992069083415874065L;
	
	@Id
	@GeneratedValue
	private UUID id;
	@NaturalId
	private String isbn;
	private String title;
	private int copyNumber = 0;
	
	protected Book() {
		
	}
	
	public Book(String isbn, String title, int copyNumber) {
		this.isbn = isbn;
		this.title = title;
		this.copyNumber = copyNumber;
	}
	
	public UUID getId() {
		return id;
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
