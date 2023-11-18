package gr.aegean.library;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Entity;

@Entity
public class LibraryMember extends Person implements java.io.Serializable{
	private static final long serialVersionUID = 764887563986434491L;
	
	@NaturalId
	private String memberId;
	
	protected LibraryMember() {
		super();
	}
	
	public LibraryMember(String id, String name, String memberId) {
		super(id,name);
		this.memberId = memberId;
	}
	
	public String getMemberId() {
		return memberId;
	}
	
	public String toString() {
		String msg = super.toString();
		msg += " with member id: " + memberId;
		return msg;
	}
	
	public boolean equals(Object o) {
		if (o instanceof LibraryMember) {
			LibraryMember lm = (LibraryMember)o;
			return lm.getMemberId().equals(memberId);
		}
		return false;
	}
	
	public int hashCode() {
		return memberId.hashCode();
	}
}
