import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;

/**
 * Abstract class Doctor - defines fields and methods to be instantiated and
 * implemented in child classes: String name, Calendar dateOfBirth, String gender
 * String address, int contactNumber, boolean qualifiedInIreland, ArrayList
 * <Qualification> qualifications
 * 
 * 
 * @author Pawel Paszki
 * @version 1.0
 */
public abstract class Doctor {
	
	protected String name;
	protected Calendar dateOfBirth;
	protected String gender;
	protected String address;
	protected int contactNumber;
	protected ArrayList<Qualification> qualifications;

	/**
	 * Constructor for Doctor class
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
	 * qualifications ArrayList instantiated
	 */
	public Doctor(String name, int year, int month, int day, String gender,
			String address, int contactNumber) {
		if (name.length() >= 3 && name.length() <= 20) {
			this.name = name;
		} else if (name.length() > 20) {
			this.name = name.substring(0, 20);
		} else {
			this.name = "!birth cert needed !";
		}
		if (year >= 1915 && year <= 1997 && month >= 1 && month <= 12
				&& day > 0 && day < 31 && isValid(year, month, day)) {
			this.dateOfBirth = new GregorianCalendar(year, (month - 1), day);
		} else {
			this.dateOfBirth = new GregorianCalendar(1915, 11, 31);
		}
		if (gender.equalsIgnoreCase("female")
				|| gender.equalsIgnoreCase("male")) {
			this.gender = gender;
		} else {
			this.gender = "male";
		}
		if (address.length() >= 10 && address.length() <= 60) {
			this.address = address;
		} else if (address.length() > 60) {
			this.address = address.substring(0, 60);
		} else {
			this.address = "to be confirmed";
		}
		if (contactNumber >= 10000000 && contactNumber <= 999999999) {
			this.contactNumber = contactNumber;
		} else {
			this.contactNumber = 999999999;
		}
		this.qualifications = new ArrayList<Qualification>();
	}


	/**
	 * This method is a getter for name field
	 * 
	 * @return name value
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            is passed to this method and field name is assigned new passed
	 *            value, if parameter is between 3 and 20 characters in length
	 *            (both incl)
	 */
	public void setName(String name) {
		if (name.length() >= 3 && name.length() <= 20) {
			this.name = name;
		}
	}

	/**
	 * This method is a getter for dateOfBirth field
	 * 
	 * @return dateOfBirth value
	 */
	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param year
	 * @param month
	 * @param day
	 *            are passed and if valid, dateOfBirth field is assigned this
	 *            value Note month is decreased by 1 because of 0-11 values
	 *            allowed for month
	 */
	public void setDateOfBirth(int year, int month, int day) {
		if (year >= 1915 && year <= 1997 && month >= 1 && month <= 12
				&& day > 0 && day < 31 && isValid(year, month, day)) {
			this.dateOfBirth = new GregorianCalendar(year, (month - 1), day);
		}
	}

	/**
	 * This method is a getter for gender field
	 * 
	 * @return value
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Setter for field gender
	 * 
	 * @param gender
	 *            is passed and validated (male or female are two allowed
	 *            values). if passes validation then field gender is assigned
	 *            this new value
	 */
	public void setGender(String gender) {
		if (gender.equalsIgnoreCase("female")
				|| gender.equalsIgnoreCase("male")) {
			this.gender = gender;
		}
	}

	/**
	 * getter for address field
	 * 
	 * @return address value
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter for field address
	 * 
	 * @param address
	 *            is passed to this method and field address is assigned new
	 *            passed value - if between 10-60 characters in length (both
	 *            incl.)
	 */
	public void setAddress(String address) {
		if (address.length() >= 10 && address.length() <= 60) {
			this.address = address;
		}
	}

	/**
	 * getter for contactNumber field
	 * 
	 * @return contactNumber value
	 */
	public int getContactNumber() {
		return contactNumber;
	}

	/**
	 * Setter for field contactNumber
	 * 
	 * @param contactNumber
	 *            is passed to this method and if passes validation (number 8-10
	 *            digits), field contactNumber is assigned new passed value
	 */
	public void setContactNumber(int contactNumber) {
		try {
			if (contactNumber >= 10000000 && contactNumber <= 999999999) {
				this.contactNumber = contactNumber;
			}
		} catch (InputMismatchException e) {

		}
	}

	/**
	 * getter for qualifications field
	 * 
	 * @return qualifications value
	 */
	public ArrayList<Qualification> getQualifications() {
		return qualifications;
	}

	/**
	 * Setter for field qualifications
	 * 
	 * @param qualifications
	 *            is passed to this method and if valid field qualifications is
	 *            assigned new passed value
	 */
	public void setQualifications(ArrayList<Qualification> qualifications) {
		this.qualifications = qualifications;
	}

	/**
	 * adds qualification to Intern's qualifications
	 * 
	 * @param qualification
	 */
	public void addQualification(Qualification qualification) {
		qualifications.add(qualification);
	}

	/**
	 * sorts qualifications in ascending order based on their names
	 * 
	 * @return
	 */
	public ArrayList<Qualification> sortQualifications() {
		for (int i = qualifications.size() - 1; i >= 0; i--)
	    {
	       int highestIndex = i;
	       for (int j = i; j >= 0; j--)
	       {  
	          if (qualifications.get(j).getDegreeName().compareTo(qualifications.get(highestIndex).getDegreeName()) > 0)
	          {
	             highestIndex = j;    
	          }
	       }
	       swapQualifications(qualifications, i, highestIndex);          
	    }
	    return qualifications;
	}
	
	/**
	 * 
	 * @param qualifications
	 * @param i
	 * @param j are passed and objects with index i and j are swapped in 
	 * qualifications ArrayList
	 */
	private void swapQualifications(ArrayList<Qualification>qualifications, int i, int j) {
		Qualification lowIndex = qualifications.get(i);
		Qualification highIndex = qualifications.get(j);
		qualifications.set(i, highIndex);
		qualifications.set(j, lowIndex);
	}

	/**
	 * @return registration fee - to be implemented in child classes
	 */
	public abstract double calculateRegistrationFee();
	
	/**
	 * This method checks, whether date is valid
	 * 
	 * @param year
	 *            - year
	 * 
	 * @param month
	 *            - month
	 * 
	 * @param day
	 *            - day, which is validated here. for specific months it cannot
	 *            be greater than 28/29 (February), 30 or 31
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
	 * @return String representation of a Doctor class
	 */
	public String toString() {
		return "\nDoctor's full name: " + name + "\nDate of Birth: "
				+ dateOfBirth.get(GregorianCalendar.YEAR) + "/"
				+ ((dateOfBirth.get(GregorianCalendar.MONTH)) + 1) + "/"
				+ dateOfBirth.get(GregorianCalendar.DAY_OF_MONTH) + "\nGender: "
				+ gender + "\nAddress: " + address + "\nContact Number: "
				+ contactNumber;
	}
}
