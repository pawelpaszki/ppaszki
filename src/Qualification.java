

/**
 * This class has four instance variables, which desribe qualification of a
 * doctor, namely: String degreeType, String degreeName, String collegeName, int
 * conferringYear
 * 
 * @author Pawel Paszki
 * @version 1.0
 */
public class Qualification {
	private String degreeType;
	private String degreeName;
	private String collegeName;
	private int conferringYear;

	/**
	 * Constructor for Qualification class. following parameters are passed 
	 * and fields are assigned values  
	 * @param degreeType - must be between 10 and 50 characters (incl). if shorter - 
	 * String "!! needs to be updated !!" is assigned. if longer - trimmed to 50 characters
	 * @param degreeName - must be between 10 and 30 characters (incl). if shorter - 
	 * String "!! needs to be updated !!" is assigned. if longer - trimmed to 40 characters
	 * @param collegeName - must be between 10 and 50 characters (incl). if shorter - 
	 * String "!! needs to be updated !!" is assigned. if longer - trimmed to 50 characters
	 * @param conferringYear - if not between 
	 * 1935 and 2015, then default value of 1935 is assigned
	 */
	public Qualification(String degreeType, String degreeName,
			String collegeName, int conferringYear) {
		if (degreeType.length() < 10) {
			this.degreeType = "!! needs to be updated !!";
		} else if (degreeType.length() >= 10 && degreeType.length() <= 50) {
			this.degreeType = degreeType;
		} else {
			this.degreeType = degreeType.substring(0, 50);
		}
		if (degreeName.length() < 10) {
			this.degreeName = "!! needs to be updated !!";
		} else if (degreeName.length() >= 10 && degreeName.length() <= 40) {
			this.degreeName = degreeName;
		} else {
			this.degreeName = degreeName.substring(0, 40);
		}
		if (collegeName.length() < 10) {
			this.collegeName = "!! needs to be updated !!";
		} else if (collegeName.length() >= 10 && collegeName.length() <= 50) {
			this.collegeName = collegeName;
		} else {
			this.collegeName = collegeName.substring(0, 50);
		}
		if (conferringYear >= 1935 && conferringYear <= 2015) {
			this.conferringYear = conferringYear;
		} else  {
			this.conferringYear = 1935;
		}
	}

	/**
	 * This getter
	 * 
	 * @return degreeType field value
	 */
	public String getDegreeType() {
		return degreeType;
	}

	/**
	 * this method takes
	 * 
	 * @param degreeType
	 *            and passes it to field degreeType
	 */
	public void setDegreeType(String degreeType) {
		if (degreeType.length() >= 10 && degreeType.length() <= 50) {
			this.degreeType = degreeType;
		}
	}

	/**
	 * This getter
	 * 
	 * @return degreeName field value
	 */
	public String getDegreeName() {
		return degreeName;
	}

	/**
	 * this method takes
	 * 
	 * @param degreeName
	 *            and passes it to field degreeType
	 */
	public void setDegreeName(String degreeName) {
		if (degreeName.length() >= 10 && degreeName.length() <= 30) {
			this.degreeName = degreeName;
		}
	}

	/**
	 * This getter
	 * 
	 * @return collegeName field value
	 */
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * this method takes
	 * 
	 * @param collegeName
	 *            and passes it to field degreeType
	 */
	public void setCollegeName(String collegeName) {
		if (collegeName.length() >= 10 && collegeName.length() <= 50) {
			this.collegeName = collegeName;
		}
	}

	/**
	 * This getter
	 * 
	 * @return conferringYear field value
	 */
	public int getConferringYear() {
		return conferringYear;
	}

	/**
	 * this method takes
	 * 
	 * @param conferringYear 
	 *            and passes it to field degreeType, if valid,
	 *            ie (within range 1935 - 2015 incl)
	 */
	public void setConferringYear(int conferringYear) {
		if (conferringYear >= 1935 && conferringYear <= 2015) {
			this.conferringYear = conferringYear;
		}
	}
	
	/**
	 * String representation of Qualification class, consisting 
	 * of values of all fields preceded by headings indicating those fields  
	 * 
	 */
	public String toString() {
		return "\tDegree Type: " + degreeType + 
		"\n\tDegree Name: " + degreeName +
		"\n\tCollege Name: " + collegeName + 
		"\n\tConferring Year: " + conferringYear + "\n";
	}

}
