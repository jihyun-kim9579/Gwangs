package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbManager.DBManager;
import reservation.ReservationDTO;

public class UserDAO {

	// 회원 가입
	public int insertUser(UserDTO userdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO myuser VALUES " + "(user_num_seq.NEXTVAL, ?, ?, ?" + ", ?, ?, SYSDATE)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userdto.getUser_name());
			pstmt.setString(2, userdto.getUser_id());
			pstmt.setString(3, userdto.getUser_pwd());
			pstmt.setString(4, userdto.getUser_phone());
			pstmt.setString(5, userdto.getUser_email());

			result = pstmt.executeUpdate();

		} catch (Exception e) {			
//			e.printStackTrace();
			System.err.println("이미 동일한 회원이 있습니다.");			
		}finally {
			DBManager.close(conn, pstmt);
		}
		

		return result;
	}

	// 로그인
	public List<UserDTO> login(String temp_id, String temp_pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<UserDTO> userlist = new ArrayList<UserDTO>();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT  * FROM    myuser " + "WHERE   user_id = ? " + "AND     user_pwd = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, temp_id);
			pstmt.setString(2, temp_pwd);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int user_num = rs.getInt("user_num");
				String user_name = rs.getString("user_name");
				String user_id = rs.getString("user_id");
				String user_pwd = rs.getString("user_pwd");
				String user_phone = rs.getString("user_phone");
				String user_email = rs.getString("user_email");
				String user_sysdate = rs.getString("user_sysdate");

				UserDTO userdto = new UserDTO(user_num, user_name, user_id, user_pwd, user_phone, user_email,
						user_sysdate);
				userlist.add(userdto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return userlist;
	}
	// 로그인 체크
	public boolean loginChk (List<UserDTO> loginInfo) {
		if (loginInfo == null) {
			return true;
		}
		return false;
	}
	
	public List<UserDTO> my_info(String userid , String userpwd){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<UserDTO> userlist = new ArrayList<UserDTO>();
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT * FROM myuser "
					+ "WHERE user_id = ?";
					
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int user_num = rs.getInt("user_num");
				String user_name = rs.getString("user_name");
				String user_id = rs.getString("user_id");
				String user_pwd = rs.getString("user_pwd");
				String user_phone = rs.getString("user_phone");
				String user_email = rs.getString("user_email");
				String user_sysdate = rs.getString("user_sysdate");

				UserDTO userdto = new UserDTO(user_num, user_name, user_id, user_pwd, user_phone, user_email,
						user_sysdate);
				userlist.add(userdto);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return userlist;
	}
	// 상품 정보 수정
	public int updateUser(UserDTO userdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "UPDATE myuser SET user_name = ? , user_id = ? ,"
					+ " user_pwd = ? , user_phone = ? ,"
					+ " user_email = ? where user_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userdto.getUser_name());
			pstmt.setString(2, userdto.getUser_id());
			pstmt.setString(3, userdto.getUser_pwd());
			pstmt.setString(4, userdto.getUser_phone());
			pstmt.setString(5, userdto.getUser_email());
			pstmt.setInt(6, userdto.getUser_num());
			
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
		
		return result;
	}
	// 내정보 
	public List<UserDTO> loginInfo(UserDTO userdto){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<UserDTO> userlist = new ArrayList<UserDTO>();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT  * FROM    myuser  WHERE user_num = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userdto.getUser_num());
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int user_num = rs.getInt("user_num");
				String user_name = rs.getString("user_name");
				String user_id = rs.getString("user_id");
				String user_pwd = rs.getString("user_pwd");
				String user_phone = rs.getString("user_phone");
				String user_email = rs.getString("user_email");
				String user_sysdate = rs.getString("user_sysdate");

				UserDTO userdto1 = new UserDTO(user_num, user_name, user_id, user_pwd, user_phone, user_email,
						user_sysdate);
				userlist.add(userdto1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return userlist ;
	}
	// 회원 탈퇴기능.
	public int deleteuser(int usernum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBManager.getConnection();
			
			String sql = "DELETE FROM myuser WHERE user_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, usernum);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return result;
	}
	//탈퇴한 회원 테이블에 추가
	public int insertDuser(UserDTO userdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO user_dropout VALUES " 
			+ "(userd_num_seq.NEXTVAL, ?, ?, ?" + ", ?, ?, SYSDATE)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userdto.getUser_name());
			pstmt.setString(2, userdto.getUser_id());
			pstmt.setString(3, userdto.getUser_pwd());
			pstmt.setString(4, userdto.getUser_phone());
			pstmt.setString(5, userdto.getUser_email());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();			
		}finally {
			DBManager.close(conn, pstmt);
		}
		

		return result;
	}
	// 탈퇴한 회원 중에서 예약 목록을 들고 있으면 먼저 처리 되는 로직.
	public String delete_reser_duser(UserDTO userdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM reservation_ck where user_id = ?";

			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, userdto.getUser_id());


			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();			
		}finally {
			DBManager.close(conn, pstmt);
		}
		

		return null;
	}
	
	
	
	// 모든 구장 보기
	public List<UserDTO> selectReser(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ReservationDTO> reserList = new ArrayList<ReservationDTO>();
		try {
			conn = DBManager.getConnection();
			String sql = "select * from reservation";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int reser_num = rs.getInt("reser_num");
				String reser_field = rs.getString("reser_field");
				int reser_fieldNum = rs.getInt("reser_fieldNum");
				String reser_date = rs.getString("reser_date");
				int reser_time = rs.getInt("reser_time");
				String reser_addr = rs.getString("reser_addr");
				String reser_cost = rs.getString("reser_cost");
				
				ReservationDTO reserdto1 = new ReservationDTO(reser_num, reser_field, reser_fieldNum, reser_date, reser_time, reser_addr, reser_cost);
				reserList.add(reserdto1);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return null;
	}
	// 예약 완료 기능
	public int deleteReservation(int reser_fieldNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBManager.getConnection();
			
			String sql = "DELETE FROM reservation WHERE reser_fieldNum = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reser_fieldNum);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return result;
	}
	// 예약 확인 테이블에 추가
	public int insertreservation_chk(ReservationDTO reserdto , UserDTO userdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO reservation_ck VALUES " 
			+ "(reser_ck_num_seq.NEXTVAL, ?, ?, ?, ?, ?, ?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reserdto.getReser_field());
			pstmt.setInt(2, reserdto.getReser_fieldNum());
			pstmt.setString(3, reserdto.getReser_date());
			pstmt.setInt(4, reserdto.getReser_time());
			pstmt.setString(5, reserdto.getReser_addr());
			pstmt.setString(6, reserdto.getReser_cost());
			pstmt.setString(7, userdto.getUser_id());			
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();			
		}finally {
			DBManager.close(conn, pstmt);
		}
		

		return result;
	}
	// 내가 예약한 구장 보기
	public List<ReservationDTO> selectMyField (String userid){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ReservationDTO> reserlist = new ArrayList<ReservationDTO>();
		
		String sql = "SELECT * FROM reservation_ck WHERE user_id = ?";
				
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			int reser_ck_num = 0;
			String reser_ck_field = null;
			int reser_ck_fieldNum = 0;
			String reser_ck_date = null;
			int reser_ck_time = 0;
			String reser_ck_addr = null;		
			String reser_ck_cost = null;
			String user_id = null;
			while (rs.next()) {
				reser_ck_num = rs.getInt("reser_ck_num");
				reser_ck_field = rs.getString("reser_ck_field");
				reser_ck_fieldNum = rs.getInt("reser_ck_fieldNum");
				reser_ck_date = rs.getString("reser_ck_date");
				reser_ck_time = rs.getInt("reser_ck_time");
				reser_ck_addr = rs.getString("reser_ck_addr");
				reser_ck_cost = rs.getString("reser_ck_cost");
				user_id = rs.getString("user_id");	
				ReservationDTO reserdto = new ReservationDTO(reser_ck_num, reser_ck_field, reser_ck_fieldNum, reser_ck_date, reser_ck_time, reser_ck_addr, reser_ck_cost);
				reserlist.add(reserdto);
			}												
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return reserlist;
	}
	

	
	
}
















