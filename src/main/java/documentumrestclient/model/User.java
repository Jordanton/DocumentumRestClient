/**
 * 
 */
package documentumrestclient.model;

import java.io.Serializable;

//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

/**
 * @author 403522
 *
 */
//@Entity
//@Table(name = "security_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Id
//	@Column(name = "user_id")
	private String userID;
	
//	@Basic
//	@Column(name = "documentum_authority")
	private String role;

	public User() {
		
	}
	
	/*
	 * JT - 12/20/19 - Add code to use JDBC Template instead of Hibernate and JPA
	 */
	public User( String userID, String role ) {

		this.userID = userID;
		this.role = role;
	}

	public String getUserID() {
		
		return userID;
	}

	public void setUserID( String userID ) {
		
		this.userID = userID;
	}

	public String getRole() {
		
		return role;
	}

	public void setRole( String role ) {
		
		this.role = role;
	}
	
}
