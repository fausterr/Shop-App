package app.dto;

public class TimeDTO {

	private int year;
	private int month;
	private int day;
	
	public TimeDTO() {}
	
	public TimeDTO(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public String toString() {
		if (this.getMonth() < 10 && this.getDay() < 10)
			return year + "-" + "0"  + month + "-" + "0"  + day;
		else if (this.getMonth() < 10) 
			return year + "-" + "0" + month + "-" + day;
		else if (this.getDay() < 10)
			return year + "-" + month + "-" + "0" + day;
		else
			return year + "-" + month + "-" + day;	
	}
}
