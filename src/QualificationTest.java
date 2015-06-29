import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Qualification class tester. tests constructor, setters and getters, toString, 
 * calculateRegistrationFee, add, set and getQualifications and
 * get and setComplaints, cgetCmplaintsCount methods
 * Setters and constructor are both tested with valid and invalid data
 * 
 * @author Pawel Paszki
 * @version 1.0
 */
public class QualificationTest {

	//fields to be used in the test
	private Qualification qualification1, qualification2, invalidQualification,
			anotherInvalidQualification;
	
	/**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
	@Before
	public void setUp() {
		qualification1 = new Qualification("Correct length of degreeType",
				"Correct length of degreeName",
				"Correct length of collegeName", 1990);
		qualification2 = new Qualification("Also Correct length of degreeType",
				"Also Correct length of degreeName",
				"Also Correct length of collegeName", 1980);
		invalidQualification = new Qualification("too short", "shortName",
				"college", 1900);
		anotherInvalidQualification = new Qualification(
				"wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to longgggggggggggggggggggggggggggggggggg",
				"wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to longgggggggggggggggggggggggggggggggggg",
				"wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to longgggggggggggggggggggggggggggggggggg",
				1900);

	}

	/**
	 * constuctor test
	 */
	@Test
	public void testQualificationConstructor() {
		// valid data
		assertEquals(qualification1.getDegreeType(),
				"Correct length of degreeType");
		assertEquals(qualification1.getDegreeName(),
				"Correct length of degreeName");
		assertEquals(qualification1.getCollegeName(),
				"Correct length of collegeName");
		assertEquals(qualification1.getConferringYear(), 1990);
		assertEquals(qualification2.getDegreeType(),
				"Also Correct length of degreeType");
		assertEquals(qualification2.getDegreeName(),
				"Also Correct length of degreeName");
		assertEquals(qualification2.getCollegeName(),
				"Also Correct length of collegeName");
		assertEquals(qualification2.getConferringYear(), 1980);

		// invalid data
		assertNotEquals(invalidQualification.getDegreeType(), "too short");
		assertNotEquals(invalidQualification.getDegreeName(), "shortName");
		assertNotEquals(invalidQualification.getCollegeName(), "college");
		assertNotEquals(invalidQualification.getConferringYear(), 1900);

		assertNotEquals(
				anotherInvalidQualification.getDegreeType(),
				"wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to longgggggggggggggggggggggggggggggggggg");
		assertNotEquals(
				anotherInvalidQualification.getDegreeName(),
				"wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to longgggggggggggggggggggggggggggggggggg");
		assertNotEquals(
				anotherInvalidQualification.getCollegeName(),
				"wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to longgggggggggggggggggggggggggggggggggg");
		assertNotEquals(anotherInvalidQualification.getConferringYear(), 1900);

		assertEquals(anotherInvalidQualification.getDegreeType(),
				"wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to long");
		assertEquals(anotherInvalidQualification.getDegreeName(),
				"wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		assertEquals(anotherInvalidQualification.getCollegeName(),
				"wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to long");
		assertEquals(anotherInvalidQualification.getConferringYear(), 1935);
	}

	/**
	 * getters and setters test
	 */
	@Test
	public void testSettersAndGetters() {
		// valid degreeType
		assertEquals(qualification1.getDegreeType(),
				"Correct length of degreeType");
		qualification1.setDegreeType("valid degree type");
		assertNotEquals(qualification1.getDegreeType(),
				"Correct length of degreeType");
		assertEquals(qualification1.getDegreeType(), "valid degree type");

		// invalid degreeType
		assertEquals(qualification2.getDegreeType(),
				"Also Correct length of degreeType");
		qualification2.setDegreeType("too short");
		assertNotEquals(qualification2.getDegreeType(), "too short");
		assertEquals(qualification2.getDegreeType(),
				"Also Correct length of degreeType");
		qualification2
				.setDegreeType("wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to longgggggggggggggggggggggggggggggggggg");
		assertEquals(qualification2.getDegreeType(),
				"Also Correct length of degreeType");
		assertNotEquals(
				qualification2.getDegreeType(),
				"wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to longgggggggggggggggggggggggggggggggggg");

		// valid degreeName
		assertEquals(qualification1.getDegreeName(),
				"Correct length of degreeName");
		qualification1.setDegreeName("valid degree name");
		assertNotEquals(qualification1.getDegreeName(),
				"Correct length of degreeName");
		assertEquals(qualification1.getDegreeName(), "valid degree name");

		// invalid degreeName
		assertEquals(qualification2.getDegreeName(),
				"Also Correct length of degreeName");
		qualification2.setDegreeName("too short");
		assertNotEquals(qualification2.getDegreeName(), "too short");
		assertEquals(qualification2.getDegreeName(),
				"Also Correct length of degreeName");
		qualification2
				.setDegreeName("wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to longgggggggggggggggggggggggggggggggggg");
		assertEquals(qualification2.getDegreeName(),
				"Also Correct length of degreeName");
		assertNotEquals(
				qualification2.getDegreeName(),
				"wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to longgggggggggggggggggggggggggggggggggg");

		// valid collegeName
		assertEquals(qualification1.getCollegeName(),
				"Correct length of collegeName");
		qualification1.setCollegeName("valid college name");
		assertNotEquals(qualification1.getCollegeName(),
				"Correct length of collegeName");
		assertEquals(qualification1.getCollegeName(), "valid college name");

		// invalid collegeName
		assertEquals(qualification2.getCollegeName(),
				"Also Correct length of collegeName");
		qualification2.setCollegeName("too short");
		assertNotEquals(qualification2.getCollegeName(), "too short");
		assertEquals(qualification2.getCollegeName(),
				"Also Correct length of collegeName");
		qualification2
				.setCollegeName("wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to longgggggggggggggggggggggggggggggggggg");
		assertEquals(qualification2.getCollegeName(),
				"Also Correct length of collegeName");
		assertNotEquals(
				qualification2.getCollegeName(),
				"wayyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy to longgggggggggggggggggggggggggggggggggg");

		// valid conferringYear
		assertEquals(qualification1.getConferringYear(), 1990);
		qualification1.setConferringYear(1970);
		assertEquals(qualification1.getConferringYear(), 1970);
		assertNotEquals(qualification1.getConferringYear(), 1990);

		// invalid conferringYear
		assertEquals(qualification2.getConferringYear(), 1980);
		qualification2.setConferringYear(1900);
		assertEquals(qualification2.getConferringYear(), 1980);
		assertNotEquals(qualification2.getConferringYear(), 1900);
	}

	/**
	 * toString test
	 */
	@Test
	public void testToString() {
		assertEquals(qualification1.toString(),
				"\tDegree Type: Correct length of degreeType"
						+ "\n\tDegree Name: Correct length of degreeName"
						+ "\n\tCollege Name: Correct length of collegeName"
						+ "\n\tConferring Year: " + 1990 + "\n");
	}

	@After
	public void tearDown() {
	}

}
