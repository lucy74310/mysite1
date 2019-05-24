package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.vo.UserVo;

public class UserDao {
	

	public Boolean insert(UserVo vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = getConnection();

			
			// 3.statement 객체 생성
			String sql = " insert into user "
					+ "		values(null, ?, ?, ?, ?, now()) ";
			pstmt = conn.prepareStatement(sql);
			
			
			//3-1. 데이터 바인딩
			pstmt.setString(1,  vo.getName());
			pstmt.setString(2,  vo.getGender());
			pstmt.setString(3,  vo.getEmail());
			pstmt.setString(4,  vo.getPassword());
			
			// 4. SQL문 실행
			int count = pstmt.executeUpdate();
			result = (count == 1);
			
			
		} catch (SQLException e) {
			System.out.println("SqlException : " + e );
		} finally {
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public UserVo get(Long no) {
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			
			
			// 3.statement 객체 생성
			String sql = " select no, name " + 
							"from user " + 
							"where no = ? " ; 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			// 4. SQL문 실행
			rs = pstmt.executeQuery();
			
			
			// 5. 결과 가져오기
			if ( rs.next() ) {
				String name = rs.getString(2);
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
			}
		} catch (SQLException e) {
			System.out.println("SqlException : " + e );
		} finally {
			try {
				if( conn != null ) {
					conn.close();
				}
				if( pstmt != null ) {
					pstmt.close();
				}
				if( rs != null ) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	
	public UserVo get(String email, String password) {
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			
			
			// 3.statement 객체 생성
			String sql = " select no, name " + 
							"from user " + 
							"where email = ? " + 
							"and password = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			
			// 4. SQL문 실행
			rs = pstmt.executeQuery();
			
			
			// 5. 결과 가져오기
			if ( rs.next() ) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				result = new UserVo();
				result.setNo(no);
				result.setName(name);
			}
		} catch (SQLException e) {
			System.out.println("SqlException : " + e );
		} finally {
			try {
				if( conn != null ) {
					conn.close();
				}
				if( pstmt != null ) {
					pstmt.close();
				}
				if( rs != null ) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	
	private Connection getConnection() throws SQLException {
		
		Connection conn = null;
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://localhost:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		return conn;
	}
}
