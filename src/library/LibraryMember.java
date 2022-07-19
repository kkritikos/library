package library;

public class LibraryMember extends Person{
	private final String memberId;
	
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
}
