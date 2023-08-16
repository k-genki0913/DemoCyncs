package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Users_DTO;

public class Users_DAO {
	
	public Users_DTO getUser(String user_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		Users_DTO users_DTO = new Users_DTO();
		
		String sql = "SELECT USER_ID, NAME, MAIL_ADDRESS, ADMIN, IS_VALID, UPDATE_DATE, UPDATE_USER FROM USERS WHERE USER_ID = ?";
		
		try {
			con = BaseDAO.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				users_DTO.setUser_id(rs.getString("USER_ID"));
				users_DTO.setName(rs.getString("NAME"));
				users_DTO.setMail_address(rs.getString("MAIL_ADDRESS"));
				users_DTO.setAdmin(rs.getBoolean("ADMIN"));
				users_DTO.setAdmin(rs.getBoolean("IS_VALID"));
				users_DTO.setUpdate_date(rs.getDate("UPDATE_DATE"));
				users_DTO.setUpdate_user(rs.getString("UPDATE_USER"));
			} else {
				users_DTO.setUser_id(user_id);
				users_DTO.setAdmin(false);
			}
		} catch(SQLException e) {
			System.out.println("Usersテーブルからの管理者フラグの取得に失敗しました");
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException ignore) {}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ignore) {}
			}
		}
		return users_DTO;
	}
}
