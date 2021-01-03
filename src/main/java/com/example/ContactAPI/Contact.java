package com.example.ContactAPI;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contact {
	
	//Defining attributes of Object
	private @Id @GeneratedValue Long id;
	private String name;
	private String relation;
	
	//Constructors
	public Contact() {}
	
	
	public Contact(String name, String group)
	{
		this.name = name;
		this.relation = group;
	}

	//Getters + Setters
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return relation;
	}


	public void setRelation(String relation) {
		this.relation = relation;
	}

	
	//Overriding default Object methods
	@Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof Contact))
	      return false;
	    Contact Contact = (Contact) o;
	    return Objects.equals(this.id, Contact.id) && Objects.equals(this.name, Contact.name)
	        && Objects.equals(this.relation, Contact.relation);
	  }

	


	@Override
	  public int hashCode() {
	    return Objects.hash(this.id, this.name, this.relation);
	  }

	  @Override
	  public String toString() {
	    return "Contact{" + "id = " + this.id + ", name = '" + this.name + '\'' + ", group = '" + this.relation + '\'' + '}';
	  }
	
}
