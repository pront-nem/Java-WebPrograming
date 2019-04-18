package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;

public class UserDao{

	public User findByLoginInfo(String loginId, String password) {
		Connection conn = null;
		try {
		//データベースに接続
		conn = DBManager.getConnection();

		//SELECT文を用意
		String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

		//SELECT文を実行して結果表を取得
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1,loginId);
		pStmt.setString(2, PasswordMD5(password));
		ResultSet rs = pStmt.executeQuery();
		
		
		if(!rs.next()) {
			return null;
		}
		
		String loginIdData = rs.getString("login_id");
		String nameData = rs.getString("name");
		return new User(loginIdData,nameData);
		
		}catch(SQLException e) {
			e.printStackTrace();
			return null;

		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	public List<User> findAll(){
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			//データベースに接続
			conn = DBManager.getConnection();
			
			//SELECT文を用意
			String sql = "SELECT * FROM user where login_id not in ('admin')";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);


			while(rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_Date");
				String updateDate = rs.getString("update_Date");
				User user = new User(id,loginId,name,birthDate,password,createDate,updateDate);

				userList.add(user);
			}
			}catch(SQLException e) {
				e.printStackTrace();
				return null;

			}finally {
				if (conn != null) {
					try {
						conn.close();
					} catch(SQLException e) {
					e.printStackTrace();
					return null;
					}
				}
		}
		return userList;
	}
	
	public List<User> FindUser(String loginId, String name,String birthDate,String birthDate1){
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			
			//SELECT文を用意
			String sql = "SELECT * FROM user where login_id not in ('admin')";
			
			//入力された項目に応じてSELECT文に追加
			if(!loginId.equals("")) {
				sql += " and login_id = '" + loginId + "'";
			}
			
			if(!name.equals("")) {
				sql += "and name LIKE '%" + name +"%'";
			}
			
			if(!birthDate.equals("")  ) {
				sql += "and birth_date >= " +  "'" + birthDate + "'"  + "" ;
			}
			
			if(!birthDate1.equals("") ) {
				sql += "and birth_date <=  " +  "'" + birthDate1 + "'"  + "" ;
			}
			
			
			
			System.out.println(sql);
			
			//SELECT文を実行
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();


			while(rs.next()) {
				int id = rs.getInt("id");
				String loginIdDate = rs.getString("login_id");
				String nameDate = rs.getString("name");
				Date birthDateData = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_Date");
				String updateDate = rs.getString("update_Date");
				User user = new User(id,loginIdDate,nameDate,birthDateData,password,createDate,updateDate);

				userList.add(user);
			}

			
			}catch(SQLException e) {
				e.printStackTrace();
				return null;

			}finally {
				if (conn != null) {
					try {
						conn.close();
					} catch(SQLException e) {
					e.printStackTrace();
					}
				}
			}
		return userList;
	}

	public  void UserRegister(String loginId,String password,String name,String birthDate) {
		Connection conn = null;
		try {
			//データベースに接続	
			conn = DBManager.getConnection();
			// INSERT文を用意
			String sql = "INSERT INTO user(login_id,name,birth_date,password,create_date, update_date) VALUES (?,?,?,?, now(), now())";
			
			//INSERT文を実行
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,loginId);
			pStmt.setString(2, name);
			pStmt.setString(3, birthDate);
			pStmt.setString(4, PasswordMD5(password));
			
			int rs = pStmt.executeUpdate();
			pStmt.close();
		
		}catch(SQLException e) {
				e.printStackTrace();
					
		}finally {
			if (conn != null) {
				try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
				}
			}
		}
	}
	
	public User findById(String targetId) {
		Connection conn = null;
		try {
			//データベースに接続
			conn = DBManager.getConnection();
					
			//SELECT文を用意
			String sql = "SELECT *  FROM user WHERE id =?";
			
			//SELECT文を実行
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, targetId);
			ResultSet rs = pStmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			}
			int id = rs.getInt("id");
			String loginId = rs.getString("login_id");
			String name = rs.getString("name");
			Date birthDate = rs.getDate("birth_date");
			String createDate = rs.getString("create_date");
			String updateDate = rs.getString("update_date");
			
			return new User(id,loginId,name,birthDate, createDate, updateDate);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		}finally {
			if(conn != null) {
				try{
				conn.close();
				}catch(SQLException e) {
				 e.printStackTrace();
				}
			}
		}
	}
	
	public User findByloginId(String targetloginId) {
		Connection conn = null;
		try {
			//データベースに接続
			conn = DBManager.getConnection();
					
			//SELECT文を用意
			String sql = "SELECT *  FROM user WHERE login_id =?";
			
			//SELECT文を実行
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, targetloginId);
			ResultSet rs = pStmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			}
			
		int id = rs.getInt("id");
		String loginId = rs.getString("login_id");
		String name = rs.getString("name");
		Date birthDate = rs.getDate("birth_date");
		String createDate = rs.getString("create_date");
		String updateDate = rs.getString("update_date");
			
		return new User(id,loginId,name,birthDate, createDate, updateDate);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		}finally {
			if(conn != null) {
				try{
				conn.close();
				}catch(SQLException e) {
				 e.printStackTrace();
				}
			}
		}
	}
	
	public Void UserDelete(String targetId) {
			Connection conn = null;
			try {
				
			//データベースに接続
			conn = DBManager.getConnection();
				
			//delete文を用意
			String sql = "DELETE FROM user WHERE id =? ";
						
			//DELETE文を実行
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, targetId);
			int rs = pStmt.executeUpdate();
		
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
				
			}finally {
				if(conn != null) {
					try{
					conn.close();
					}catch(SQLException e) {
					 e.printStackTrace();
					}
				}
			}
			return null;
		}
	
	public Void UserUpdate(String password,String name,String birthDate,String targetId) {
		Connection conn = null;
		try {
			
		//データベースに接続
		conn = DBManager.getConnection();
		
		//UPDATE文を用意
		String sql = "UPDATE user SET password = ?, name= ? , birth_date = ? , update_date = now() WHERE id =? ";
		
		//UPDATE文を実行
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setString(1, PasswordMD5(password));
		pStmt.setString(2, name);
		pStmt.setString(3, birthDate);
		pStmt.setString(4, targetId);
		int rs = pStmt.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		}finally {
			if(conn != null) {
				try{
				conn.close();
				}catch(SQLException e) {
				 e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	private String PasswordMD5(String password) {
		
		//ハッシュ生成前にバイト配列に置き換える際のCharset
		Charset charset = StandardCharsets.UTF_8;
		//ハッシュアルゴリズム
		String algorithm = "MD5";

		//ハッシュ生成処理
		byte[] bytes = null;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(password.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String result = DatatypeConverter.printHexBinary(bytes);
		
		return result;
		
	}
}
