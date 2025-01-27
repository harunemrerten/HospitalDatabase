
public class Doctor implements Person{
	private String fullName;
	private MyBSTree<Date,Patient> patients;
	
	
	public Doctor(String fullName) {
		// TODO Auto-generated constructor stub
		this.fullName=fullName;
		patients= new MyBSTree<>();
	}
	
	public MyBSTree<Date, Patient> getPatients()
	//--------------------------------------------------------
	 //this is just for getting BSTree objects because it is private
	 //--------------------------------------------------------
	{
		return patients;
	}

	
	@Override
	public String getFullName() 
	//--------------------------------------------------------
	 // this is only for getting fullname and also this is a implemented from person interface so that ı can use it in generic class
	 //--------------------------------------------------------
	{
		
		return fullName;
	}

	@Override
	public String toString() {
		return "Doctor: "+fullName ;
	}

	@Override
	public String getNameAndDate(boolean condition) {
		// this is not for usage ı need this method because of interface and i need it to implement in tree class.
		return null;
	}

	@Override
	public int getYearIfexist(boolean condition) {
		//--------------------------------------------------------
		 // I write this method because To String, getnameandDate methods are not enough for some database method this is why ı have this method
		 // and also it needs condition because in some method in HospitalDatabase class I need to get Date condition but in some part ı dont in generic class this was hard to deal with it so 
		 // I created a condition such that if it is true get date and print like this if not then print like this and you do not need date 
 		 //--------------------------------------------------------
		return -1;
	}
	
	
	
}
