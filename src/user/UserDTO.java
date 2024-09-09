package user;

public class UserDTO {
	
	private int user_num;
	private String user_name;
	private String user_id;
	private String user_pwd;
	private String user_phone;
	private String user_email;
	private String user_sysdate;
	private String temp_pwd;
	
	public UserDTO() {
		
}
	public UserDTO (String temp_pwd) {
		this.setTemp_pwd(temp_pwd);
	}
	
	public UserDTO(int user_num, String user_name, String user_id, String user_pwd, String user_phone,
			String user_email, String user_sysdate) {
		super();
		this.user_num = user_num;
		this.user_name = user_name;
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.user_sysdate = user_sysdate;
	}


	public int getUser_num() {
		return user_num;
	}


	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getUser_pwd() {
		return user_pwd;
	}


	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}


	public String getUser_phone() {
		return user_phone;
	}


	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String getUser_sysdate() {
		return user_sysdate;
	}


	public void setUser_sysdate(String user_sysdate) {
		this.user_sysdate = user_sysdate;
	}

	public String getTemp_pwd() {
		return temp_pwd;
	}
	public void setTemp_pwd(String temp_pwd) {
		this.temp_pwd = temp_pwd;
	}

	@Override
	public String toString() {
		return "userDTO [user_num=" + user_num + ", user_name=" + user_name + ", user_id=" + user_id + ", user_pwd="
				+ user_pwd + ", user_phone=" + user_phone + ", user_email=" + user_email + ", user_sysdate="
				+ user_sysdate + "]";
	
	}
	
	
}
