
/**
 * Instance of this class represents an intern
 * 
 * @author Pawel Paszki
 * @version 1.0
 */
public class Internship extends Doctor {

	/**
	 * Constructor for objects of class Internship
	 * 
	 * @param name
	 *            - name of the doctor (between 3-20 characters). If longer then
	 *            String is trimmed to 20 characters, if shorter - then "n/a"
	 *            assigned
	 * @param year
	 *            - validated. year cannot be less than 1915 and greater than
	 *            1997
	 * @param month
	 *            - 1-12 (value decreased by one, because month in the Calendar
	 *            class starts from 0 for January)
	 * @param day
	 *            - depends on month and year (28-31). if any of those three is
	 *            not valid then default date is set as dateOfBirth - 1915/12/31
	 * @param gender
	 *            - validated. if validation not passed - default value "male"
	 *            is assigned
	 * @param address
	 *            of the doctor. must be between 10 and 50 characters. if over
	 *            50 characters then trimmed to 50, if below 10 - set to default
	 *            "to be confirmed"
	 * @param contactNumber
	 *            is also validated. if not a numeric 8-9 digits , then default
	 *            999999999 value is assigned qualifications ArrayList
	 *            instantiated during construction
	 */
	public Internship(String name, int year, int month, int day, String gender,
			String address, int contactNumber) {
		super(name, year, month, day, gender, address, contactNumber);
	}

	

	/**
	 * @return registration fee amount for Intern (fixed value - 310)
	 */
	@Override
	public double calculateRegistrationFee() {
		return 310;
	}


	/**
	 * @return String representation of Internship class object, which consists
	 *         of all fields and their values + qualifications sorted by
	 *         degreeName
	 */
	public String toString() {
		if (qualifications.size() >= 2) {
			sortQualifications();
		}
		String listOfQualifications = "";
		for (Qualification qualification : qualifications) {
			listOfQualifications += qualification.toString();
		}
		return super.toString() + "\n\nQualifications: \n"
				+ listOfQualifications;
	}
}
