package user;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import reservation.ReservationDAO;
import reservation.ReservationDTO;

public class UserMain {
	public static void main(String[] args) {

		UserDAO userdao = new UserDAO();
		Scanner sc = new Scanner(System.in);
		UserDTO loggedInUser = null;
		ReservationDAO reserdao = new ReservationDAO();

		// 회원가입
		while (true) {
			try {

				System.out.println("                      ☆광스에 오신걸 환영합니다!☆                  ");
				System.out.println("");
				System.out.println("              1. 회원가입           2.로그인");
				System.out.print("  원하시는 서비스 번호를 입력해주세요. : ");
				
				int inputNum = sc.nextInt();

				if (inputNum == 1) {
					System.out.println("");
					System.out.println("[   회원가입   ]");
					sc.nextLine();
					System.out.print("이름을 입력해주세요 : ");
					String user_name = sc.nextLine();
					System.out.print("아이디를 입력해주세요 : ");
					String user_id;
					while (true) {
						user_id = sc.nextLine();
						if (user_id.length() >= 6 && user_id.length() <= 12) {
							break;
						} else {
							System.err.print("아이디는 6 ~ 12자이내 입력해주세요.  : ");
						}
					}
					System.out.print("비밀번호를 입력해주세요 : ");
					String user_pwd;

					while (true) {
						user_pwd = sc.nextLine();
						if (user_pwd.length() >= 6 && user_pwd.length() <= 12) {
							break;
						} else {
							System.err.print("비밀번호는 6 ~ 12자이내 사용가능  : ");
						}
					}
					String temp_pwd;
					while (true) {
						System.out.print("비밀번호 재확인 : ");
						temp_pwd = sc.nextLine();
						if (user_pwd.equals(temp_pwd)) {
							System.out.println("      [일치 합니다.]");
							break;
						} else {
							System.out.println("");
							System.err.println("다시 입력해 주십시오.");
							continue;
						}
					}

					System.out.print("전화번호를 입력해주세요 : ");
					String user_phone;
					while (true) {
						user_phone = sc.nextLine();
						if (user_phone.matches("\\d{11}")) {
							break;
						} else {
							System.err.print("11자리 ,  ' - ' 을 제외한 숫자만 입력해주세요 ");
						}
					}

					System.out.print("이메일을 입력해주세요 : ");
					String user_email = sc.nextLine();
					UserDTO userdto = new UserDTO(inputNum, user_name, user_id, user_pwd, user_phone, user_email,
							user_pwd);

					int result = userdao.insertUser(userdto);

					if (result > 0) {
						while (true) {
							System.out.println("[인증번호 발송]");
							int randomNum = (int) (Math.random() * 1000000) + 50000;
							System.out.print(randomNum + "를 정확히 기입하여 주십시오. : ");
							int inputST = sc.nextInt();
							if (randomNum == inputST) {
								System.out.println("인증 성공하셨습니다.");
								break;
							} else {
								System.out.println("");
								System.err.print("번호를 정확히 입력하여 주십시오. : ");
							}
						}
						sc.nextLine();
						System.out.println("");
						System.out.println("축하합니다 회원이 되신걸 축하합니다.");
						System.out.println("");
						System.out.println("처음으로 돌아가셔서 로그인 해주세요!");
						System.out.println("");
						System.out.println("ENTER KEY 를 누르면 처음으로 돌아갑니다.");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						String tempst = sc.nextLine();
						String st = tempst.toUpperCase();
						if (st.equals("ENTER KEY")) {
							continue;
						} else {
							continue;
						}
					} else {
						System.err.println("다시 가입하여 주십시오.");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						continue;
					}
				} else if (inputNum == 2) {
					sc.nextLine();
					System.out.println("[   로그인   ]");
					System.out.print("아이디를 입력해주세요 : ");
					String user_id = sc.nextLine();
					System.out.print("비밀번호를 입력해주세요 : ");
					String user_pwd = sc.nextLine();

					List<UserDTO> userlist = userdao.login(user_id, user_pwd);

					if (userlist != null && userlist.size() > 0) {
						loggedInUser = userlist.get(0);

						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println(userlist.get(0).getUser_name() + "님 반갑습니다.");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						System.out.println("");

					} else {
						System.err.println("아이디 또는 비밀번호가 틀리셨습니다. 다시 입력하여 주십시오.");
						continue;
					}
				}

			} catch (InputMismatchException e) {
				System.out.println("");
				System.err.println("보이는 항목 [번호]만 입력 해주세요.");
				System.out.println("");
				sc.next();
				continue;
			}

			while (true) {					
				List<UserDTO> userlist1 = userdao.loginInfo(loggedInUser);
				System.out.println("");
				System.out.println("1.내정보 2.회원탈퇴 3.예약가능한구장 4. 예약목록확인 5.로그아웃 ");				
				System.out.print("[ " + userlist1.get(0).getUser_name() + " 님 ]" + "  원하시는 서비스 번호를 입력해주세요. : ");
				int inputNum1 = sc.nextInt();
				sc.nextLine();

				if (inputNum1 == 1) {
					List<UserDTO> userlist = userdao.loginInfo(loggedInUser);
					System.out.println("");
					System.out.println("");
					System.out.println("회원님의 이름은 : " + userlist.get(0).getUser_name());
					System.out.println("회원님의 아이디는 :" + userlist.get(0).getUser_id());
					System.out.println("회원님의 패스워드는 :" + userlist.get(0).getUser_pwd());
					System.out.println("회원님의 전화번호는 : " + userlist.get(0).getUser_phone());
					System.out.println("회원님의 이메일은 : " + userlist.get(0).getUser_email());
					System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("1.회원정보 수정 2.뒤로가기");
					System.out.println("원하시는 메뉴를 선택해 주세요.");
					int inputNum2 = sc.nextInt();
					if (inputNum2 == 1) {

						// 내 정보가 담겨있는곳
						userlist = userdao.loginInfo(loggedInUser);
						sc.nextLine();
						System.out.println("[ " + userlist.get(0).getUser_name() + " ] 님의 회원정보수정");
						System.out.println("1. 회원님의 이름은    : " + "\t" + userlist.get(0).getUser_name());
						System.out.println("2. 회원님의 패스워드는 :" + "\t" + userlist.get(0).getUser_pwd());
						System.out.println("3. 회원님의 전화번호는 : " + "\t" + userlist.get(0).getUser_phone());
						System.out.println("4. 회원님의 이메일은  : " + "\t" + userlist.get(0).getUser_email());
						while (true) {
							System.out.print("수정하고 싶으신 항목 번호를 입력해주세요.");
							System.out.println("뒤로 가고 싶으시면 아무를 눌러주세요.");
							String inputNum3 = sc.nextLine();

							UserDTO userdto = userlist.get(0);
							String user_name = userdto.getUser_name();
							String user_id = userdto.getUser_id();
							String user_pwd = userdto.getUser_pwd();
							String user_phone = userdto.getUser_phone();
							String user_email = userdto.getUser_email();

							if (inputNum3.equals("1")) {
								System.out.print("수정하고 싶은 이름은 ? :   ");
								String user_name1 = sc.nextLine();
								System.out.println(user_name1 + "님으로 바꾸시겠습니까?" + "Y / N");
								String inputSt = sc.nextLine();
								if (inputSt.equals("Y")) {
									user_name = user_name1;
								} else {
									continue;
								}

							} else if (inputNum3.equals("2")) {
								System.out.print("새로운 패스워드는 ? :   ");
								String user_pwd1 = sc.nextLine();
								System.out.println(user_pwd1 + "로 바꾸시겠습니까?" + "Y / N");
								String inputSt = sc.nextLine();
								if (inputSt.equals("Y")) {
									user_pwd = user_pwd1;
								} else {
									continue;
								}
							} else if (inputNum3.equals("3")) {
								System.out.print("새로운 전화번호는 ? :   ");
								String user_phone1 = sc.nextLine();
								System.out.println(user_phone1 + "으로 바꾸시겠습니까?" + "Y / N");
								String inputSt = sc.nextLine();
								if (inputSt.equals("Y")) {
									user_phone = user_phone1;
								} else {
									continue;
								}
							} else if (inputNum3.equals("4")) {
								System.out.print("바꾸고 싶은 이메일은 ? :   ");
								String user_email1 = sc.nextLine();
								System.out.println(user_email1 + "으로 바꾸시겠습니까?" + "Y / N");
								String inputSt = sc.nextLine();
								if (inputSt.equals("Y")) {
									user_email = user_email1;
								} else {
									continue;
								}
							}
							userdto = new UserDTO(userdto.getUser_num(), user_name, user_id, user_pwd, user_phone,
									user_email, userdto.getUser_sysdate());
							int result = userdao.updateUser(userdto);

							if (result > 0) {
								System.out.println("정상처리 되셨습니다.");
								break;

							} else {
								System.out.println("정상처리 되지 못했습니다. 다시 시도해 주십시오.");
								continue;
							}
						}
					} else {
						continue;
					}
				}

				if (inputNum1 == 2) {
					System.err.println("정말로 탈퇴 하시겠습니까??   Y / N");
					String inputST = sc.nextLine();
					if (inputST.equals("Y")) {
						// 회원 삭제 기능.
						int userdlist = userdao.insertDuser(loggedInUser);
						int result1 = userdlist;
						if (result1 > 0) {
//							System.out.println("탈퇴 회원으로 옮겨졌습니다.");
						} else {
							System.out.println("실패하였습니다.");
						}
// 				회원탈퇴시 시작되는 로직.
						List<ReservationDTO> reserdto = userdao.selectMyField(loggedInUser.getUser_id());
						for (ReservationDTO reservationDTO : reserdto) {
							reservationDTO.getReser_field();
							reservationDTO.getReser_fieldNum();
							reservationDTO.getReser_date();
							reservationDTO.getReser_time();
							reservationDTO.getReser_addr();
							reservationDTO.getReser_cost();								
							reserdao.insert_CK_Reser(reservationDTO);			
						}
						String result11 = userdao.delete_reser_duser(loggedInUser);
						if (result11 == null) {
							List<UserDTO> userlist = userdao.loginInfo(loggedInUser);							
							int user_num = userlist.get(0).getUser_num();													
							int result = userdao.deleteuser(user_num);
							if (result > 0) {
								System.out.println("");
								System.out.println("");
								System.out.println("탈퇴되셨습니다 다음에 또 뵙겠습니다.");
								System.out.println("");
								System.out.println("");
								System.out.println("");
								break;
							} else {
								System.out.println("실패하였습니다.");
								continue;
							}
						}else if (result11 != null){
							// reservation 으로 다시 올려주기.					
							result11 = userdao.delete_reser_duser(loggedInUser);														
							List<UserDTO> userlist = userdao.loginInfo(loggedInUser);						
							int user_num = userlist.get(0).getUser_num();													
							int result = userdao.deleteuser(user_num);
							
							
							if (result > 0) {
								System.out.println("");
								System.out.println("");
								System.out.println("탈퇴되셨습니다 다음에 또 뵙겠습니다.");
								System.out.println("");
								System.out.println("");
								System.out.println("");
								break;
							}
						}
						
					}
				}
				if (inputNum1 == 3) {
					while (true) {
						List<ReservationDTO> reserList = reserdao.allReser();
						for (ReservationDTO reservationDTO : reserList) {
							System.out.println(
									"────────────────────────────────────[예약가능한 구장]────────────────────────────────────────────────────");
							System.out.println("구장 번호 : [ " + reservationDTO.getReser_fieldNum() + " ]");
							int startTime = reservationDTO.getReser_time();
							int endTime = (startTime + 2) % 24;
							System.out.println("구장 이름 : [" + reservationDTO.getReser_field() + "]" + "\t" + "\t"
									+ "이용 가능한 날짜 : [" + reservationDTO.getReser_date() + "]" + " [" + startTime + "시 ~ "
									+ endTime + "시]");
							System.out.println("구장 주소 : [" + reservationDTO.getReser_addr() + "]" + "\t" + "\t"
									+ "        구장 가격 : [" + reservationDTO.getReser_cost() + "]");
							System.out.println("");
						}
						System.out.println(
								"─────────────────────────────────────────────────────────────────────────────────────────────────────");
						System.out.print("구장을 예약 하시려면 Y , 나가시려면 N을 눌러주세요.");
						String inputST = sc.nextLine();
						if (inputST.equals("Y")) {
							System.out.print("날짜를 입력해주세요 ex)2024-MM-DD  :");
							String user_date;

							while (true) {
								user_date = sc.nextLine();
								if (user_date.length() == 10) {
									break;
								} else {
									System.err.println("날짜 형식이 올바르지 않습니다. ex) 2024-08-15");
									System.out.print("다시 입력해 주십시오. : ");
									continue;
								}
							}
							ReservationDTO reserdto = new ReservationDTO();
							reserdto.setReser_date(user_date);
							List<ReservationDTO> reserlist = reserdao.selectDate(user_date);
							System.out.println("");
							System.out.println("");
							if (reserlist != null && !reserlist.isEmpty()) {
								System.out.println(
										"──────────────────────────────선택하신 날짜[" + reserlist.get(0).getReser_date()
												+ "]────────────────────────────────────────────────────");
							} else {
								System.err.println("없는 날짜 입니다.");
								System.out.println("처음으로 돌아가시려면 ENTER KEY를 누르세요.");
								System.out.println("");
								sc.nextLine();
								continue;
							}
							while (true) {
								List<ReservationDTO> reserlist1 = reserdao.selectDate(user_date);
								for (ReservationDTO reservationDTO : reserlist1) {
									System.out.println("");
									System.out.println("구장 번호 : [ " + reservationDTO.getReser_fieldNum() + " ]");
									int startTime = reservationDTO.getReser_time();
									int endTime = (startTime + 2) % 24;
									System.out.println("구장 이름 : [" + reservationDTO.getReser_field() + "]" + "\t" + "\t"
											+ "이용 가능한 날짜/시간 : [" + reservationDTO.getReser_date() + "]" + " ["
											+ startTime + "시 ~ " + endTime + "시]");
									System.out.println("구장 주소 : [" + reservationDTO.getReser_addr() + "]" + "\t" + "\t"
											+ "        구장 가격 : [" + reservationDTO.getReser_cost() + "]");
									System.out.println("");
									System.out.println(
											"──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
								}
								System.out.print("이용 하고 싶으신 구장 번호를 입력해주세요 :  ");
								int inputFelidNum = sc.nextInt();
								System.out.print("이용 하고 싶으신 구장 시작 시간을 입력해주세요 :  ");
								int inputFelidTime = sc.nextInt();
								System.out.println(
										"──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
								System.out.println("");

								List<ReservationDTO> reserlist2 = reserdao.selectfelidNum(inputFelidNum,
										inputFelidTime);

								for (ReservationDTO reservationDTO1 : reserlist2) {
									System.out.println("    [   선택하신 구장   ]");
									System.out
											.println("        구장 번호 : [ " + reservationDTO1.getReser_fieldNum() + " ]");
									int startTime1 = reservationDTO1.getReser_time();
									int endTime1 = (startTime1 + 2) % 24;
									System.out.println("        구장 이름 : [" + reservationDTO1.getReser_field() + "]"
											+ "\t" + "\t" + "선택한 날짜/시간 : [" + reservationDTO1.getReser_date() + "]"
											+ " [" + startTime1 + "시 ~ " + endTime1 + "시]");
									System.out.println(
											"        구장 주소 : [" + reservationDTO1.getReser_addr() + "]" + "\t" + "\t"
													+ "        구장 가격 : [" + reservationDTO1.getReser_cost() + "]");
									System.out.println("");
									System.out.println("");
									System.out.print("[예약 하시겠습니까 ?    Y / N]");
									sc.nextLine();
									String inputST1 = sc.nextLine();
									if (inputST1.equals("Y")) {
										System.out.println("감사합니다 예약 되셨습니다.");
										System.out.println("처음으로 돌아가시려면 ENTER KEY를 눌러주세요.");
										sc.nextLine();
										ReservationDTO reserdto1 = new ReservationDTO();
										reserdto1.setReser_fieldNum(inputFelidNum);
										reserdto1.setReser_time(inputFelidTime);
										reserdto1.setReser_field(reserlist1.get(0).getReser_field());
										reserdto1.setReser_date(reserlist1.get(0).getReser_date());
										reserdto1.setReser_addr(reserlist1.get(0).getReser_addr());
										reserdto1.setReser_cost(reserlist1.get(0).getReser_cost());
										userdao.deleteReservation(inputFelidNum);
										userdao.insertreservation_chk(reserdto1, loggedInUser);
										break;
									} else if (inputST1.equals("N")) {
										System.out.println("처음으로 돌아갑니다.");
										System.out.println("");
										break;
									}
								}
								break;
								// 4번째 while 문
							}

							break;
						}
						// 3번째 while 문
						else if (inputST.equals("N")) {
							System.out.println("처음으로 돌아갑니다.");
							break;
						} else {
							System.err.println("Y / N 만 눌러주세요.");
							break;
						}
					}

				}
				if (inputNum1 == 4) {
					List<ReservationDTO> reserlist = userdao.selectMyField(loggedInUser.getUser_id());

					System.out.println("                            [ 내가 예약한 목록 ]");
					System.out.println(
							"────────────────────────────────────────────────────────────────────────────────────");
					for (ReservationDTO reservationDTO : reserlist) {
						System.out.println("        구장 이름 : [" + reservationDTO.getReser_field() + "]"
								+ "        구장 번호 : [ " + reservationDTO.getReser_fieldNum() + " ]");
						int startTime1 = reservationDTO.getReser_time();
						int endTime1 = (startTime1 + 2) % 24;
						System.out.println("        선택한 날짜/시간 : [" + reservationDTO.getReser_date() + "]" + " ["
								+ startTime1 + "시 ~ " + endTime1 + "시]" + "\t" + "\t" + "구장 주소 : ["
								+ reservationDTO.getReser_addr() + "]");
						System.out.println("         구장 비용 : [" + reservationDTO.getReser_cost() + "]");
						System.out.println("");
						System.out.println("");
						continue;
					}
				}
				if (inputNum1 == 5) {
					System.out.println("");
					System.out.println("로그아웃 하셨습니다.");
					System.out.println("");
					break;

				}
				// 2번째 while 문
			}
			// while 문 끝
		}
	}
}
