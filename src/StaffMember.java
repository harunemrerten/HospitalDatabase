

public class StaffMember implements Person{
	private String fullName,roleOfTheSM;
	private MyBSTree<String,Patient> patients;// this is patients that staffmember is responsibler for the different patients

	public StaffMember(String mName,String role) {
		fullName= mName;
		roleOfTheSM=role;
		patients= new MyBSTree<>();
	}
	
	public String getRoleOfTheSM() {
		return roleOfTheSM;
	}

	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return fullName;
	}

	@Override
	public String toString() {
		return fullName + ", " + roleOfTheSM;
	}
	
	public MyBSTree<String, Patient> getPatients() {
		return patients;
	}

	@Override
	public String getNameAndDate(boolean condition) {
		// this is not required but ı need such method to my interface to implement in Tree Class.
		return null;
	}

	@Override
	public int getYearIfexist(boolean condition) {
		// TODO Auto-generated method stub
		return -1;
	}


}
