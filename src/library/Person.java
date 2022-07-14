package library;

public class Person implements java.io.Serializable{
	private static final long serialVersionUID = -9132275263691387159L;
	
	private String name, id;
	
	public Person(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return new String(name);
	}

	public String getId() {
		return new String(id);
	}
	
	public String toString() {
		return "Person(" + id + ", " + name + ")";
	}
	
	public boolean equals(Object o) {
		if (o instanceof Person) {
			Person p = (Person)o;
			if (p.id.equals(id)) return true;
		}
		return false;
	}
	
	public int hashCode() {
		return id.hashCode();
	}

}
