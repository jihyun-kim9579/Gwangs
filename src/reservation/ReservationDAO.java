package reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbManager.DBManager;

public class ReservationDAO {
	
// 구장 등록
	public int insertReser(ReservationDTO redto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO reservation VALUES (reser_num_seq.NEXTVAL,"
					+ " ? , ? , ? , ?, ?, ? )";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, redto.getReser_field());
			pstmt.setInt(2, redto.getReser_fieldNum());
			pstmt.setString(3, redto.getReser_date());
			pstmt.setInt(4, redto.getReser_time());
			pstmt.setString(5, redto.getReser_addr());
			pstmt.setString(6, redto.getReser_cost());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return result;
	}
// 탈퇴한 회원이 들고있는 예약 구장을 옮길때 
	public int insert_CK_Reser(ReservationDTO redto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO reservation (reser_num, reser_field, reser_fieldNum, reser_date, reser_time, reser_addr, reser_cost) "
	                   + "VALUES (reser_num_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, redto.getReser_field());
			pstmt.setInt(2, redto.getReser_fieldNum());
			pstmt.setString(3, redto.getReser_date());
			pstmt.setInt(4, redto.getReser_time());
			pstmt.setString(5, redto.getReser_addr());
			pstmt.setString(6, redto.getReser_cost());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return result;
	}
	
	public boolean insert_check_Reser(ReservationDTO redto) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DBManager.getConnection();
	        
	        // 중복 체크 쿼리
	        String checkSql = "SELECT COUNT(*) FROM reservation WHERE reser_fieldNum = ? AND reser_date = ? AND reser_time = ?";
	        pstmt = conn.prepareStatement(checkSql);
	        pstmt.setInt(1, redto.getReser_fieldNum());
	        pstmt.setString(2, redto.getReser_date());
	        pstmt.setInt(3, redto.getReser_time());
	        
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            if (count > 0) {
	                // 중복된 예약이 존재
	                return true;
	            }
	        }
	        
	        // 중복된 예약이 없음
	        return false;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        DBManager.close(conn, pstmt, rs);
	    }
	}
	
	
	// 구장 삭제
	public int deleteReser (int reserNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBManager.getConnection();
			
			String sql = "DELETE FROM reservation WHERE reser_num  = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reserNum);
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		
		return result;
	}
	// 모든 구장 보기
	public List<ReservationDTO> allReser(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReservationDTO> relist = new ArrayList<ReservationDTO>();
		
		try {
			conn = DBManager.getConnection();
			String sql = "select * from reservation  ORDER BY reser_date , reser_time, reser_fieldNum";
			
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
				ReservationDTO redto = new ReservationDTO(reser_num, reser_field, reser_fieldNum, reser_date, reser_time, reser_addr, reser_cost);
				relist.add(redto);
			}

			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		
		return relist;
	}
	// 날짜별 구장 보기.
	public List<ReservationDTO> selectDate(String reserdate){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ReservationDTO> reserlist = new ArrayList<ReservationDTO>();
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "select * from reservation where   reser_date = ? order by reser_time , reser_fieldNum";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reserdate);
			
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
				reserlist.add(reserdto1);
				
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return reserlist;
	}
	// 구장 필드번호 와 시간 선택
	public List<ReservationDTO> selectfelidNum(int felidNum , int reserTime ){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ReservationDTO> reserlist = new ArrayList<ReservationDTO>();
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "select * from reservation where  reser_fieldNum = ? AND reser_time = ? order by reser_time , reser_fieldNum";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,felidNum);
			pstmt.setInt(2,reserTime);
			
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
				reserlist.add(reserdto1);
				
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return reserlist;
	}
	// 구장별 선택
	public List<ReservationDTO> selectfelid(String feild){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ReservationDTO> reserlist = new ArrayList<ReservationDTO>();
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "select * from reservation where  reser_field = ? order by reser_time , reser_fieldNum";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,feild);
			
			
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
				reserlist.add(reserdto1);
				
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return reserlist;
	}
	
	//회원 구장 이름 선택
	public List<ReservationDTO> select_user_felid(String feild){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ReservationDTO> reserlist = new ArrayList<ReservationDTO>();
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "select * from reservation where  reser_field = ? order by reser_time , reser_fieldNum";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,feild);
			
			
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
				reserlist.add(reserdto1);
				
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return reserlist;
	}
}

















