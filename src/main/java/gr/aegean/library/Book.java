package gr.aegean.library;

import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.NaturalId;

@MappedSuperclass
public abstract class Book implements java.io.Serializable{
	private static final long serialVersionUID = -992069083415874065L;
	
	@Id
	@GeneratedValue
	private UUID id;
	@NaturalId
	private String isbn;
	//@Basic(optional=false)
	private String title;
	//@Basic(optional=false)
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
