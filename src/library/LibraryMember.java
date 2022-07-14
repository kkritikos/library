package library;

public class LibraryMember extends Person implements java.io.Serializable{
	private static final long serialVersionUID = 764887563986434491L;
	
	private String memberId;
	
	public LibraryMember(String id, String name, String memberId) {
		super(id,name);
		this.memberId = memberId;
	}
	
	public String getMemberId() {
		return new String(memberId);
	}
	
	public String toString() {
		String msg = super.toString();
		msg += " with member id: " + memberId;
		return msg;
	}
}
