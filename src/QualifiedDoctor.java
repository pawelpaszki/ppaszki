import java.util.ArrayList;

/**
 * Write a description of class QualifiedDoctor here.
 * 
 * @author Pawel Paszki
 * @version 1.0
 */
public abstract class QualifiedDoctor extends Doctor {
	protected ArrayList<Complaint> complaints;
	protected boolean qualifiedInIreland;

	/**
	 * Constructor for QualifiedDoctor class
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
	 *            999999999 value is assigned
	 * 
	 * @param qualifiedInIreland
	 *            - true or false qualifications and complaints ArrayLists
	 *            instantiated
	 */
	public QualifiedDoctor(String name, int year, int month, int day,
			String gender, String address, int contactNumber,
			boolean qualifiedInIreland) {
		super(name, year, month, day, gender, address, contactNumber);
		this.qualifiedInIreland = qualifiedInIreland;
		this.complaints = new ArrayList<Complaint>();
	}

	/**
	 * getter for qualifiedInIreland field
	 * 
	 * @return qualifiedInIreland value
	 */
	public boolean isQualifiedInIreland() {
		return qualifiedInIreland;
	}

	/**
	 * Setter for field qualifiedInIreland
	 * 
	 * @param qualifiedInIreland
	 *            is passed to this method and if passes validation (boolean
	 *            input valid only), field qualifiedInIreland is assigned new
	 *            passed value
	 */
	public void setQualifiedInIreland(boolean qualifiedInIreland) {
		this.qualifiedInIreland = qualifiedInIreland;
	}

	/**
	 * getter for complaints field
	 * 
	 * @return complaints value
	 */
	public ArrayList<Complaint> getComplaints() {
		return complaints;
	}

	/**
	 * Setter for field complaints
	 * 
	 * @param complaints
	 *            is passed to this method and if valid field complaints is
	 *            assigned new passed value
	 */
	public void setComplaints(ArrayList<Complaint> complaints) {
		this.complaints = complaints;
	}

	/**
	 * adds new
	 * 
	 * @param complaint
	 *            to ArrayList of complaints
	 */
	public void addComplaint(Complaint complaint) {
		complaints.add(complaint);
	}

	/**
	 * updates
	 * 
	 * @param status
	 *            of
	 * @param complaint
	 */
	public void updateComplaintStatus(Complaint complaint, String status) {
		int index = searchForComplaint(complaint.getComplaintId());
		complaints.get(index).setResult(status);
	}

	/**
	 * sorts complaints in ascending order based on their id's
	 */
	public void sortComplaints() {
		for (int i = complaints.size() - 1; i >= 0; i--) {
			int highestIndex = i;
			for (int j = i; j >= 0; j--) {
				if (complaints.get(j).getComplaintId() > (complaints
						.get(highestIndex).getComplaintId())) {
					highestIndex = j;
				}
			}
			swapComplaints(complaints, i, highestIndex);
		}
	}

	/**
	 * 
	 * @param qualifications
	 * @param i
	 * @param j
	 *            are passed and objects with index i and j are swapped in
	 *            complaints ArrayList
	 */
	private void swapComplaints(ArrayList<Complaint> complaints, int i, int j) {
		Complaint lowIndex = complaints.get(i);
		Complaint highIndex = complaints.get(j);
		complaints.set(i, highIndex);
		complaints.set(j, lowIndex);
	}

	/**
	 * searches for complaint given a complaint
	 * 
	 * @param id
	 *            . The index number of the complaint is returned if it exists,
	 *            -1 otherwise.
	 */
	public int searchForComplaint(int id) {
		int i = 0;
		while (i < complaints.size()) {
			if (complaints.get(i).getComplaintId() == id)
				return i; // this terminates the method
			i++;
		}
		return -1;

	}

	/**
	 * @return number of complaints of a QualifiedDoctor
	 */
	public abstract int getComplaintsCount();

	/**
	 * @return String representation of a QualifiedDoctor class
	 */
	public String toString() {
		return super.toString();
	}
}
