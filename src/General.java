
/**
 * Instance of this class represents a general doctor
 * 
 * @author Pawel Paszki
 * @version 1.0
 */
public class General extends QualifiedDoctor {

	/**
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
	 * 
	 * @param gender
	 *            - validated. if validation not passed - default value "male"
	 *            is assigned
	 * @param address
	 *            of the doctor. must be between 10 and 50 characters. if over
	 *            50 characters then trimmed to 50, if below 10 - set to default
	 *            "to be confirmed"
	 * @param contactNumber
	 *            is also validated. if not a numeric 8-9 digits , then default
	 *            999999999 value is assigned
	 * @param qualifiedInIreland
	 *            - true or false. if input is not a boolean - default true
	 *            assigned qualifications and complaints ArrayLists instantiated
	 *            during construction
	 */
	public General(String name, int year, int month, int day, String gender,
			String address, int contactNumber, boolean qualifiedInIreland) {
		super(name, year, month, day, gender, address, contactNumber,
				qualifiedInIreland);
	}

	/**
	 * @return registration fee based on whether qualified in Ireland or not
	 */
	@Override
	public double calculateRegistrationFee() {
		if (qualifiedInIreland) {
			return 194;
		} else {
			return 410;
		}
	}


	/**
	 * return number of complaints
	 */
	@Override
	public int getComplaintsCount() {
		return complaints.size();
	}

	/**
	 * @return String representation of General class object, which consists
	 *         of all fields and their values + qualifications sorted by
	 *         degreeName and complaints sorted by complaintId
	 */
	public String toString() {
		if (qualifications.size() >= 2) {
			sortQualifications();
		}
		String listOfQualifications = "";
		for (Qualification qualification : qualifications) {
			listOfQualifications += qualification.toString() + "\n";
		}
		String listOfComplaints = "";
		for (Complaint complaint : complaints) {
			listOfComplaints += complaint.toString();
		}
		if (listOfComplaints.length() == 0) {
			listOfComplaints = "No Complaints have been recorded";
		}
		return super.toString() + "\nQualified in Ireland: "
				+ qualifiedInIreland + "\n\nQualifications: \n"
				+ listOfQualifications + "\nComplaints:\n" + listOfComplaints;
	}
}
