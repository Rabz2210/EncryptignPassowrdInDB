package com.example.accessingdatamysql;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import org.hibernate.annotations.GenericGenerator;

@Entity // This tells Hibernate to make a table out of this class
public class User {
	

    @Id
    private String id;

    private String name;

    private String email;

  

  public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
