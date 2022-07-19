package library;

public class Person {
	private final String name, id;
	
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
