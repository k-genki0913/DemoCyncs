package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.UserLoginDTO;

public class User_Password_DAO {
	
	/*user_idを用いてUser_Passwordテーブルからpassword、invalid_countを取得し
	 * UserLoginDTOに格納して戻す
	 */
	public UserLoginDTO getUser_Password(int user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		UserLoginDTO userLoginDTO = new UserLoginDTO();
		
		String sql = "SELECT USER_ID, PASSWORD, INVALID_COUNT FROM USER_PASSWORD WHERE USER_ID = ?";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			userLoginDTO.setUser_id(rs.getInt("USER_ID"));
			userLoginDTO.setPassword(rs.getString("PASSWORD"));
			userLoginDTO.setInvalid_count(rs.getInt("INVALID_COUNT"));
		} catch(SQLException e) {
			System.out.println("User_Passwordテーブルからの情報取得に失敗しました");
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException ignore) {
					
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ignore) {
					
				}
			}
		}
		return userLoginDTO;
	}
	
	public void setInvalid_Count(int user_id, int count) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE USER_PASSWORD SET INVALID_COUNT = ? WHERE USER_ID = ?";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setInt(2, user_id);
			
			int result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException e) {
					
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException e) {
					
				}
			}
		}
	}
	
	
}
