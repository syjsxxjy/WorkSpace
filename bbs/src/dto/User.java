package dto;


/**
 * 	FileName: User.java
 *	desc: 用户实体类.

 */
public class User extends IdEntity {

	
	private String username;
	private String password;
	private String email;
	private String avatarurl;


	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [name=" + username + ", password=" + password + ", email=" + email + ", id=" + id +"]";
	}

	public String getAvatarurl() {
		return avatarurl;
	}

	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}

	}
