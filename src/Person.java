
public interface Person {
	// it is needed for all
	String getFullName();
	// it is needed only for patient
	String getNameAndDate(boolean condition);
	// it is needed only for patient
	int getYearIfexist(boolean condition);
}
