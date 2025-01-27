
public class HospitalDatabase {
	private MyBSTree<Date, Patient> patients;
	private MyBSTree<Date, Doctor> doctors;
	
	
	public HospitalDatabase() {
		patients= new MyBSTree<>();
		doctors = new MyBSTree<>(); 
		
	}	
	
	
	//This part for only patients
	//--------------------------------------------------------------------------------
	public void addPatient(String pName,String dName, int day,int month,int year) {
		//--------------------------------------------------------
		 // Summary: we need to add a patient and a doctor to the system
		 // name is given and date's parameters are given.
		 // Precondition: pName is a and dName is a string and int for day month year.
		 // Now we have four if situation if there are any patient like given or if doctors are given. ı mean it is combination of this. 
		 // if we have patients like that copy and delete but dont lose the copy add new informations then add back to the list. exactly same for the doctors. or if we dont have for both of them create new one
		 //--------------------------------------------------------
		if(patients.isNotNull()) {
			if(patients.search(pName)==null&&doctors.search(dName)==null) {// if we do not have such patient and doctor.
			Doctor newDoctor= new Doctor(dName);
			Patient newPatient= new Patient(pName,newDoctor, new Date(day, month, year));
			patients.put(newPatient.getDate(),newPatient);
			doctors.put(newPatient.getDate(), newDoctor);
			// ı get patient list that is in Doctor class and put new patient in it.
			newDoctor.getPatients().put(newPatient.getDate(), newPatient);
			System.out.println("INFO: Patient "+pName+" has been added");
			}
			else if(patients.search(pName)!=null&&doctors.search(dName)==null) {// if we have the same patient but there is no such doctor.
			// we are taking the existing patient and deleting from the tree
			Patient tmpPatient=patients.search(pName);
			patients.deleteBySearching(pName);
			// we change the existing Date and change the doctor from existing one to new one
			tmpPatient.setDate(new Date(day, month, year));
			tmpPatient.setDoctor(new Doctor(dName));
			patients.put(tmpPatient.getDate(), tmpPatient);
			// This part seems to a little bit complicated because of my coding style. ı created new date and new doctor in tmpPatient
			// but ı need to get patients tree that ı created in Doctor Class so ı get doctor from patient and get tree from patient tree.
			tmpPatient.getDoctor().getPatients().put(tmpPatient.getDate(), tmpPatient);
			System.out.println("ERROR: Patient "+pName+" overwritten");
			}
			else if(patients.search(pName)==null&&doctors.search(dName)!=null) {// if we do not have such a patient but there is such a doctor.
			Doctor tmpDoctor=doctors.search(dName);
			Patient newPatient= new Patient(pName, tmpDoctor, new Date(day, month, year));
			patients.put(newPatient.getDate(), newPatient);
			tmpDoctor.getPatients().put(newPatient.getDate(), newPatient);
			System.out.println("INFO: Patient "+pName+" has been added");
			}
			else {
			Patient tmpPatient=patients.search(pName);
			// in order to update ı delete it and adding with new date.
			patients.deleteBySearching(pName);
			Doctor tmpDoctor= doctors.search(dName);
			// we have to delete patient list from doctor and add new patient again to update date.
			tmpDoctor.getPatients().deleteBySearching(pName);
			tmpPatient.setDate(new Date(day, month, year));
			patients.put(tmpPatient.getDate() ,tmpPatient);
			System.out.println("ERROR: Patient "+pName+" overwritten");
			}
		}
		else {
			Doctor newDoctor= new Doctor(dName);
			Patient newPatient= new Patient(pName,newDoctor, new Date(day, month, year));
			patients.put(newPatient.getDate(),newPatient);
			doctors.put(newPatient.getDate(), newDoctor);
			// ı get patient list that is in Doctor class and put new patient in it.
			newDoctor.getPatients().put(newPatient.getDate(), newPatient);
			System.out.println("INFO: Patient "+pName+" has been added");
			
		}
	}
	
	public void removePatient(String pName) {
		//--------------------------------------------------------
		 // We have name as parameter which we will use for searching the patients from binarysearchtree. 
		 // but in binary search tree we took date as the key so that ı needed to create an interface for patients and doctors. they are value in binary search tree and they are implement an interafce so that we can acces names
		 // Precondition: pName is name in patient class
		 // if we have such a patient then delete patient from hospital database and delete from bst list that is in doctor class.
		 //--------------------------------------------------------
		if(patients.search(pName)!=null) {
			Patient tmpPatient= patients.search(pName);
			tmpPatient.getDoctor().getPatients().deleteBySearching(pName);
			patients.deleteBySearching(pName);
			System.out.println("INFO: Patient "+pName+" has been removed");
		}
		else {
			System.out.println("ERROR: Patient "+pName+" does not exist");
		}
	}
	
	public void showPatient(String pName) {
		//--------------------------------------------------------
		 // Summary: we have if condition in if statement we use search method 
		 // if we have such a patient then print the given string.
		 //--------------------------------------------------------
		if(patients.search(pName)!=null) {
			Patient tmpPatient =patients.search(pName);
			System.out.println(tmpPatient.getFullName()+"\r"+tmpPatient.getDate()+"\r"+tmpPatient.getDoctor().getFullName());
			tmpPatient.getStaffmembers().displayAscendingOrder();
		}
		else {
			System.out.println("---none---");
		}
		
	}
	
	public void showAllPatients() {
		//--------------------------------------------------------
		 // Summary: I needed to create different displaying methods because some of them wanted to ascending or descending order or different parameters needed
		 // so ı use this method  to here if we have patients then display if no print none.
		 //--------------------------------------------------------
		if(patients.isNotNull()) {
		patients.displayAscendingOrder();
		}
		else {
		System.out.println("---none---");
		}
	}
	
	public void showPatients(int visitYear) {
		//--------------------------------------------------------
		 // Summary: this is one of my special display method
		 // we needed to print some different version of prints and ı did not wanted to create new list for all or array
		 // instead ı wrote a different type of needen methods in my interface which is implemented for my patients doctors or stuffmembers
		 // and gave some condition if it is true do this if not do that.
		 // we have visityear for paramater which gets into date and compares years in it then prints 
		 // that patients informaiton
		 //--------------------------------------------------------
		System.out.println(visitYear);
		patients.diferentDisplay(true,visitYear);
	}
	
	// this part for medical team
	//--------------------------------------------------------------------------------
	public void addMember(String pName,String mName,String roleOfTheMember) {
		//--------------------------------------------------------
		 // Summary: we adding new patients to the database and assign it to a patient. if patient exist ofcourse
		 // Precondition: pname for patient name mname for member name and there is an information for member
		 // if we dont have such that patient then give warning, if there is such a member copy and delete member and change the copied member then add to the patient again
		 // Postcondition: The value of the variable is set and 
		 //--------------------------------------------------------
		if(patients.search(pName)!=null) {
			Patient tmpPatient= patients.search(pName);
			if(tmpPatient.getStaffmembers().get(mName)==null) {
				StaffMember newSMember= new StaffMember(mName, roleOfTheMember);
				tmpPatient.getStaffmembers().put(newSMember.getFullName(), newSMember);
				newSMember.getPatients().put(tmpPatient.getFullName(), tmpPatient);
				System.out.println("INFO: "+newSMember.getFullName()+" has been added to the patient "+tmpPatient.getFullName());
			}
			else {
				StaffMember tmpSMember= new StaffMember(mName, roleOfTheMember);// exactly like the before but still the result is the same. only difference is placement is 
				tmpPatient.getStaffmembers().delete(mName);
				tmpPatient.getStaffmembers().put(tmpSMember.getFullName(), tmpSMember);
				tmpSMember.getPatients().put(tmpPatient.getFullName(), tmpPatient);
				System.out.println("INFO: "+tmpSMember.getFullName()+" has been o to the patient "+tmpPatient.getFullName());
				}
		}
		else {
			System.out.println("ERROR: Patient "+pName+" does not exist");
		}
	}
	
	public void removeMember(String pName, String mName) {
		//--------------------------------------------------------
		 // Summary: exactly like the first we have pname as patient name and mname as member name
		 // these are strings we search if such a patient exist if not give a warning
		 // if we dont have such a member give a warning if not then use delete() method with mname for parameter
		 // Postcondition: it is deleted from the bst in patient class or gave a warnings
		 //--------------------------------------------------------
		if(patients.search(pName)!=null) {
			Patient tmpPatient = patients.search(pName);
			if(tmpPatient.getStaffmembers().get(mName)==null) {
				System.out.println("ERROR: Member "+mName+" does not exist");
			}
			else {
				tmpPatient.getStaffmembers().delete(mName);
				System.out.println("INFO: "+mName+" has been removed from the patient "+pName);
			}
		}
		else {
			System.out.println("ERROR: Patient "+pName+" does not exist");
		}
		
	}
	
	// this part for the doctors
	//--------------------------------------------------------------------------------
	public void showDoctorPatients(String dName) {
		// for showing all the value of the node ı wrote list method but unfortunately ı need rewrite here because my toSting methods should be different
		//--------------------------------------------------------
		 // Summary: ı am searching such a doctor with a name with dname which is string. search if found use
		 // the different method with false parameter. and we write an integer but it is not important because
		 // like ı said before ı did not want to create new list for my bst this is why ı modified this parameter into something like two different method if true print like this if false use it like this. year part is only needed for the searching for the year.
		 //--------------------------------------------------------
		if(doctors.search(dName)!=null) {
			Doctor tmpDoctor =doctors.search(dName);
			System.out.println(tmpDoctor.getFullName());
			tmpDoctor.getPatients().diferentDisplay(false, 0);// here integer parameter is not important I needed to get visityear for some other methods and ı did not want to create any other parameter because we are using generic class for tree.
		}
		else {
			System.out.println("ERROR: Doctor "+dName+" does not exist");
		}
	}

}
