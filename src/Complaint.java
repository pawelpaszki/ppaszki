import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Objects of this class will store information about complaints made
 * in fields: complaintId, complaintDate and result 
 * 
 * @author Pawel Paszki
 * @version 1.0
 */
public class Complaint {

	private int complaintId;
	private Calendar complaintDate;
	private String result;

	/**
	 * Constructor for objects of class Complaint
	 * 
	 * @param complaintId
	 *            (checked if is positive int, default value assigned, if not)
	 * @param complaintDate
	 *            are passed result is assigned default value - "open"
	 */
	public Complaint(int complaintId) {
		if (complaintId >= 0) {
			this.complaintId = complaintId;
		} else {
			this.complaintId = 99999;
		}
		this.complaintDate = new GregorianCalendar();
		this.result = "open";
	}

	/**
	 * getter, which
	 * 
	 * @return the complaintId field value
	 */
	public int getComplaintId() {
		return complaintId;
	}

	/**
	 * @param complaintId
	 *            is passed and if valid then complaintId is assigned this new
	 *            value
	 */
	public void setComplaintId(int complaintId) {
		if (complaintId >= 0) {
			this.complaintId = complaintId;
		}
	}

	/**
	 * getter, which
	 * 
	 * @return the complaintDate field value
	 */
	public Calendar getComplaintDate() {
		return complaintDate;
	}

	/**
	 * @param year
	 * @param month
	 * @param day
	 *            are passed and complaintId is assigned this new value
	 *            if the overall date is a valid date
	 */
	public void setComplaintDate(int year, int month, int day) {
		if (month >= 1 && month <= 12) {
			if (day > 0 && day < 31) {
				if (isValid(year, month, day)) {
					this.complaintDate = new GregorianCalendar(year,
							(month - 1), day);
				}
			}
		}
	}

	/**
	 * getter, which
	 * 
	 * @return the result field value
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            is passed and if valid (upheld, open or rejected) result is
	 *            assigned this new value
	 */
	public void setResult(String result) {
		if (result.equalsIgnoreCase("open")
				|| result.equalsIgnoreCase("upheld")
				|| result.equalsIgnoreCase("rejected")) {
			this.result = result.toLowerCase();
		}

	}

	/**
	 * This method chechs, whether date is valid
	 * 
	 * @param year - year
	 * 
	 * @param month - month
	 * 
	 * @param day - day, which is validated here. for specific months it cannot
	 * be greater than 28/29 (february), 30 or 31
	 */
	private boolean isValid(int year, int month, int day) {
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return true;
		case 4:
		case 6:
		case 9:
		case 11:
			if (day <= 30) {
				return true;
			}
		case 2:
			if (year % 4 == 0) {
				if (day <= 29) {
					return true;
				}
			} else {
				if (day <= 28) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * String representation of Complaint class:
	 * id + date (yyyy/mm/dd). 1 must be added to month to print it correctly
	 * + result
	 */
	public String toString() {
		return "\tComplaint id: " + getComplaintId() + "\n\tComplaint date: "
				+ getComplaintDate().get(Calendar.YEAR) + "/"
				+ ((getComplaintDate().get(Calendar.MONTH))+1) + "/"
				+ getComplaintDate().get(Calendar.DAY_OF_MONTH) + "\n\tResult: "
				+ getResult() + "\n\n";
	}
}
