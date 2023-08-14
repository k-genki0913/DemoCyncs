package model;

/**
 * ログイン時に必要な情報をまとめたDTO
 * ログイン時の認証やアカウント登録時に利用予定
 * @author k.genki0913
 *
 */
public class UserLoginDTO {
	private int user_id;
	private String password;
	private int invalid_count;
	private boolean login_result;
	
	public UserLoginDTO() {}
	
	public UserLoginDTO(int user_id, String password, int invalid_count) {
		this.user_id = user_id;
		this.password = password;
		this.invalid_count = invalid_count;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getUser_id() {
		return this.user_id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setInvalid_count(int invalid_count) {
		this.invalid_count = invalid_count;
	}
	
	public int getInvalid_count() {
		return this.invalid_count;
	}
	
	public void setLogin_result(boolean login_result) {
		this.login_result = login_result;
	}
	
	public boolean getLogin_result() {
		return this.login_result;
	}
	
	
	
	
}
