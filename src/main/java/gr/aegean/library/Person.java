package gr.aegean.library;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Person implements java.io.Serializable{
	private static final long serialVersionUID = -9132275263691387159L;
	
	@Id
	private String id;
	private String name;
	
	protected Person() {
		
	}
	
	public Person(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
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
