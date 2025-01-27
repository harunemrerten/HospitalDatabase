

public class Patient implements Person{
	private String fullName;
	private Doctor doctor;
	private MyBSTree<String,StaffMember> staffmembers;
	private Date date;
	
	public Patient(String fullName, Doctor doctorOfP,Date date) {
		this.fullName= fullName;
		this.doctor=doctorOfP;
		this.date=date;
		staffmembers= new MyBSTree<>();
	}
	
	public MyBSTree<String, StaffMember> getStaffmembers() {
		return staffmembers;
	}

	
	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return fullName;
	}

	@Override
	public String toString() {
		return  fullName +", "+ date.getYear()+", "+ doctor.getFullName();
	}
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String getNameAndDate(boolean condition) {// we have two query in Hospital Database program and for displayin all ı need two different output so ı get boolean and if true one is output if false the other is output
		//--------------------------------------------------------
		 // Summary: return two different type of string value according to the boolean function for hospitaldatabase 
		 // one for showPatients(int visitYear), one forshowPatients(int visitYear) because the outputs change for each
		 //--------------------------------------------------------
		if (condition) {
			// this is for showPatients(int visitYear)
			return fullName+", "+date.getDay()+"/"+date.getMonth();
		}else
			// this is for showPatients(int visitYear)
		return fullName+", "+date;
	}

	@Override
	public int getYearIfexist(boolean condition) {
		//--------------------------------------------------------
		// this is needen for comparing in my MyBSTree howPatients(int visitYear) with patients visit year however ı needed implement this method for all person interface implementation
		 //--------------------------------------------------------
		if(condition) {
			return getDate().getYear();
		}
		else 
			return -1;
	}
	
	

}
