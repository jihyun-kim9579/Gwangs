package reservation;

public class ReservationDTO {
	private int    reser_num;
	private String reser_field;
	private int	   reser_fieldNum;
	private String reser_date;
	private int reser_time;
	private String reser_addr;
	private String reser_cost;
	
	public  ReservationDTO() {
	
}

	public ReservationDTO(int reser_num, String reser_field, int reser_fieldNum, String reser_date, int reser_time,
			String reser_addr, String reser_cost) {
		super();
		this.reser_num = reser_num;
		this.reser_field = reser_field;
		this.reser_fieldNum = reser_fieldNum;
		this.reser_date = reser_date;
		this.reser_time = reser_time;
		this.reser_addr = reser_addr;
		this.reser_cost = reser_cost;
	}

	public int getReser_num() {
		return reser_num;
	}

	public void setReser_num(int reser_num) {
		this.reser_num = reser_num;
	}

	public String getReser_field() {
		return reser_field;
	}

	public void setReser_field(String reser_field) {
		this.reser_field = reser_field;
	}

	public int getReser_fieldNum() {
		return reser_fieldNum;
	}

	public void setReser_fieldNum(int reser_fieldNum) {
		this.reser_fieldNum = reser_fieldNum;
	}

	public String getReser_date() {
		return reser_date;
	}

	public void setReser_date(String reser_date) {
		this.reser_date = reser_date;
	}

	public int getReser_time() {
		return reser_time;
	}

	public void setReser_time(int reser_time) {
		this.reser_time = reser_time;
	}

	public String getReser_addr() {
		return reser_addr;
	}

	public void setReser_addr(String reser_addr) {
		this.reser_addr = reser_addr;
	}

	public String getReser_cost() {
		return reser_cost;
	}

	public void setReser_cost(String reser_cost) {
		this.reser_cost = reser_cost;
	}
	
	
	
	
}
