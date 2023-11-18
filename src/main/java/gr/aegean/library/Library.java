package gr.aegean.library;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Library implements java.io.Serializable{
	private static final long serialVersionUID = -813561890224024887L;
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@NaturalId 
	private String name;
	
	private String address;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SellingBook> sellingBooks = new HashSet<SellingBook>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LendingBook> lendingBooks = new HashSet<LendingBook>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LibraryMember> members = new HashSet<LibraryMember>();
	
	protected Library() {
		
	}
	
	public Library(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public Set<SellingBook> getSellingBooks(){
		return sellingBooks;
	}
	
	public Set<LendingBook> getLendingBooks(){
		return lendingBooks;
	}
	
	public Set<LibraryMember> getMembers(){
		return members;
	}
	
	public String getName() { return name; }

	public String getAddress() { return address; }

	public String toString() {
		return "Library(" + name + ", " + address + ")";
	}
	
	public void printStatus() {
		System.out.println("Members:");
		for (LibraryMember lm: members) System.out.println(lm.toString());
		System.out.println("Books to Lend:");
		for (LendingBook lb: lendingBooks) System.out.println(lb.toString());
		System.out.println("Books to Sell:");
		for (SellingBook sb: sellingBooks) System.out.println(sb.toString());
	}
}
