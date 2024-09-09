package reservation;

import java.util.List;
import java.util.Scanner;

public class ReservationMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
// 관리자가 올리는 구장		
		ReservationDAO redao = new ReservationDAO();
		while (true) {
			
			String field_name = "서구 챔피언스 필드";
			String field_addr = "상무로 135-21";
			String field_cost = "120000";
			System.out.println("1.구장등록 2.구장목록  Q를 누르시면 종료.");			
			String inputNum = sc.nextLine();
			
			if (inputNum.equals("1")) {				
//				System.out.print("등록할 구장 이름  : ");  // 서구챔피언스 필드
				String reser_field = field_name;
				System.out.print("구장 번호 :  ");
				int	 reser_fieldNum = sc.nextInt();			
				sc.nextLine();
				System.out.print("등록할 구장 날짜 ex:2024-09-05 : ");
				String reser_date;
				while (true) {
					reser_date = sc.nextLine();			
					if (reser_date.length() == 10) {
						break;
					}else {
						System.err.println("형식에 올바르지 않습니다.");
					}
				}
				System.out.print("등록할 구장 시작 시간  : ");
				int reser_time = sc.nextInt();
				sc.nextLine();
//				System.out.print("등록할 구장 주소  : ");  // 상무로 135-21
				String reser_addr = field_addr;
//				System.out.print("등록할 구장 비용  : ");
				String reser_cost = field_cost;
				
				
				ReservationDTO redto = new ReservationDTO(reser_fieldNum, reser_field, reser_fieldNum, reser_date, reser_time, reser_addr, reser_cost);											
				if (redao.insert_check_Reser(redto)) {
					System.err.println("이미 있는 구장입니다. 다시 시도해 주세요.");
					continue;
				}
				int result = redao.insertReser(redto);
				
				
				if (result > 0) {
					
					System.out.println("정상처리 되셨습니다.");
					System.out.println("");
					System.out.println("");
					System.out.println("");
					
				}else {
					System.out.println("다시 시도하여 주십시오.");
				}
			}
			if (inputNum.equals("2")) {
				 
				List<ReservationDTO> reserList = redao.selectfelid(field_name);
				
				for (ReservationDTO reservationDTO : reserList) {
					System.out.println("[ 구장 번호 ]:"+ " ["+reservationDTO.getReser_fieldNum()+"]");
					System.out.print("[ 구장 이름 ]:"+ " ["+reservationDTO.getReser_field()+"]" + "\t" );
					int startTime = reservationDTO.getReser_time();
					int endTime = (startTime + 2) % 24;
					System.out.println("[ 등록된 날짜 / 시간]:" + " ["+reservationDTO.getReser_date()+"]" + " ["+ startTime + "시 ~ " +  endTime +"시]");				 
					System.out.print("[ 구장 주소 ]: " + " ["+ reservationDTO.getReser_addr()+"]"+ "\t");
					System.out.println("[ 구장 이용 비용 ]: "+ " ["+reservationDTO.getReser_cost()+"]");
					System.out.println("───────────────────────────────────────────────────────────");
					System.out.println("");
				}
			}
			if (inputNum.equals("Q")) {
				System.out.println("안녕히 가십시오.");
				break;
			}
			
		} 
	}
}










