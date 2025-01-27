
public class Date implements Comparable<Date>{
	private int day,month,year;

	public Date(int day,int month,int year) {
		this.day=day;
		this.month=month;
		this.year=year;
	}


	@Override
	public String toString() {
		return day + "/" + month + "/" + year ;
	}


	@Override
	public int compareTo(Date o) {
		//--------------------------------------------------------
		 // Summary: compares date with the date of some date.
		 // Postcondition: if bigger return1 , if smaller -1 if none of them then return 0
		 //--------------------------------------------------------
		
		if (this.year>o.getYear())
			return 1;
		else if (this.year<o.getYear())
			return -1;
		
		if (this.month>o.getMonth())
			return 1;
		else if (this.month<o.getMonth())
			return -1;
		
		if (this.day>o.day)
			return 1;
		else if (this.day<o.getDay())
			return -1;
		
		else
			return 0;
	}
	

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}


	public int getYear() {
		return year;
	}




	
}
