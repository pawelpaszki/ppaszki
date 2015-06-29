import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * QualifiedDoctor class tester. tests constructor, setters and getters, toString, 
 * calculateRegistrationFee, add, set and getQualifications and
 * get and setComplaints, cgetCmplaintsCount methods
 * Setters and constructor are both tested with valid and invalid data
 * 
 * @author Pawel Paszki
 * @version 1.0
 */
public class QualifiedDoctorTest {

	//fields to be used in the test
	private QualifiedDoctor doctor1, doctor2, invalidDoctor, anotherInvalidDoctor;
	private Qualification qualification1, qualification2;
	private Complaint complaint1, complaint2;
	private ArrayList<Qualification> newQualifications;
	private ArrayList<Complaint> newComplaints;

	/**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
	@Before
	public void setUp() {
		doctor1 = new General("Bobby Bob", 1960, 6, 10, "male",
				"The Quay, Waterford", 12345678, true);
		doctor2 = new Specialist("Chrissy Chris", 1940, 11, 20, "female",
				"Manor Street, Waterford", 123456789, true, "specialist");
		invalidDoctor = new General("AB", 2000, 13, 32, "myself", "here",
				12345, false);
		anotherInvalidDoctor = new Specialist(
				"This is way too long name",
				1900,
				10,
				10,
				"hyhy",
				"123456789012345678901234567890123456789012345678901234567890 111112222233333",
				123, false, "invalid specialist            - namely - too long");
		qualification1 = new Qualification("Correct length of degreeType",
				"Correct length of degreeName",
				"Correct length of collegeName", 1990);
		qualification2 = new Qualification("Also Correct length of degreeType",
				"Also Correct length of degreeName",
				"Also Correct length of collegeName", 1980);
		newQualifications = new ArrayList<Qualification>();
		newQualifications.add(qualification1);
		newQualifications.add(qualification2);
		complaint1 = new Complaint(0);
		complaint2 = new Complaint(1);
		newComplaints = new ArrayList<Complaint>();
		newComplaints.add(complaint1);
		newComplaints.add(complaint2);
	}

	/**
	 * constuctor test
	 */
	@Test
	public void testQualifiedDoctorConstructor() {
		// valid values
		assertEquals(doctor1.getName(), "Bobby Bob");
		assertEquals(doctor1.dateOfBirth.get(GregorianCalendar.YEAR), 1960);
		assertEquals(doctor1.dateOfBirth.get(GregorianCalendar.MONTH), 5);
		assertEquals(doctor1.dateOfBirth.get(GregorianCalendar.DAY_OF_MONTH),
				10);
		assertEquals(doctor1.getGender(), "male");
		assertEquals(doctor1.getAddress(), "The Quay, Waterford");
		assertEquals(doctor1.getContactNumber(), 12345678);
		assertEquals(doctor1.isQualifiedInIreland(), true);
		

		assertEquals(doctor2.getName(), "Chrissy Chris");
		assertEquals(doctor2.dateOfBirth.get(GregorianCalendar.YEAR), 1940);
		assertEquals(doctor2.dateOfBirth.get(GregorianCalendar.MONTH), 10);
		assertEquals(doctor2.dateOfBirth.get(GregorianCalendar.DAY_OF_MONTH),
				20);
		assertEquals(doctor2.getGender(), "female");
		assertEquals(doctor2.getAddress(), "Manor Street, Waterford");
		assertEquals(doctor2.getContactNumber(), 123456789);
		assertEquals(doctor2.isQualifiedInIreland(), true);
		assertEquals(((Specialist)doctor2).getSpecialist(), "specialist");

		// invalid values
		assertEquals(invalidDoctor.getName(), "!birth cert needed !");
		assertEquals(invalidDoctor.dateOfBirth.get(GregorianCalendar.YEAR),
				1915);
		assertEquals(invalidDoctor.dateOfBirth.get(GregorianCalendar.MONTH), 11);
		assertEquals(
				invalidDoctor.dateOfBirth.get(GregorianCalendar.DAY_OF_MONTH),
				31);
		assertEquals(invalidDoctor.getGender(), "male");
		assertEquals(invalidDoctor.getAddress(), "to be confirmed");
		assertEquals(invalidDoctor.getContactNumber(), 999999999);
		assertEquals(invalidDoctor.isQualifiedInIreland(), false);
		assertNotEquals(((Specialist)doctor2).getSpecialist(), "invalid specialist            - namely - too long");
		assertEquals(((Specialist)anotherInvalidDoctor).getSpecialist(), "invalid specialist            ");
		assertEquals(anotherInvalidDoctor.getName(), "This is way too long");
		assertEquals(
				anotherInvalidDoctor.dateOfBirth.get(GregorianCalendar.YEAR),
				1915);
		assertEquals(
				anotherInvalidDoctor.dateOfBirth.get(GregorianCalendar.MONTH),
				11);
		assertEquals(
				anotherInvalidDoctor.dateOfBirth
						.get(GregorianCalendar.DAY_OF_MONTH),
				31);
		assertEquals(anotherInvalidDoctor.getGender(), "male");
		assertEquals(anotherInvalidDoctor.getAddress(),
				"123456789012345678901234567890123456789012345678901234567890");
		assertEquals(anotherInvalidDoctor.getContactNumber(), 999999999);
		assertEquals(anotherInvalidDoctor.isQualifiedInIreland(), false);
	}

	/**
	 * getters and setters test
	 */
	@Test
	public void testSettersAndGetters() {
		// valid name
		assertEquals(doctor1.getName(), "Bobby Bob");
		doctor1.setName("New valid name");
		assertNotEquals(doctor1.getName(), "Bobby Bob");
		assertEquals(doctor1.getName(), "New valid name");

		// invalid name
		assertEquals(invalidDoctor.getName(), "!birth cert needed !");
		invalidDoctor.setName("AB");
		assertNotEquals(invalidDoctor.getName(), "AB");
		assertEquals(invalidDoctor.getName(), "!birth cert needed !");
		
		assertEquals(doctor2.getName(), "Chrissy Chris");
		doctor2.setName("New tooooooooooo long name");
		assertNotEquals(doctor2.getName(), "New tooooooooooo long name");
		assertEquals(doctor2.getName(), "Chrissy Chris");

		// valid dateOfBirth
		assertEquals(doctor2.dateOfBirth.get(GregorianCalendar.YEAR), 1940);
		assertEquals(doctor2.dateOfBirth.get(GregorianCalendar.MONTH), 10);
		assertEquals(doctor2.dateOfBirth.get(GregorianCalendar.DAY_OF_MONTH),
				20);
		doctor2.setDateOfBirth(1950, 10, 15);
		assertEquals(doctor2.dateOfBirth.get(GregorianCalendar.YEAR), 1950);
		assertEquals(doctor2.dateOfBirth.get(GregorianCalendar.MONTH), 9);
		assertEquals(doctor2.dateOfBirth.get(GregorianCalendar.DAY_OF_MONTH),
				15);
		assertNotEquals(doctor2.dateOfBirth.get(GregorianCalendar.YEAR), 1940);
		assertNotEquals(doctor2.dateOfBirth.get(GregorianCalendar.MONTH), 10);
		assertNotEquals(
				doctor2.dateOfBirth.get(GregorianCalendar.DAY_OF_MONTH), 20);

		// invalid dateOfBirth
		assertEquals(invalidDoctor.dateOfBirth.get(GregorianCalendar.YEAR),
				1915);
		assertEquals(invalidDoctor.dateOfBirth.get(GregorianCalendar.MONTH), 11);
		assertEquals(
				invalidDoctor.dateOfBirth.get(GregorianCalendar.DAY_OF_MONTH),
				31);
		invalidDoctor.setDateOfBirth(1900, 10, 15);
		assertNotEquals(invalidDoctor.dateOfBirth.get(GregorianCalendar.YEAR),
				1900);
		assertNotEquals(invalidDoctor.dateOfBirth.get(GregorianCalendar.MONTH),
				9);
		assertNotEquals(
				invalidDoctor.dateOfBirth.get(GregorianCalendar.DAY_OF_MONTH),
				15);
		assertEquals(invalidDoctor.dateOfBirth.get(GregorianCalendar.YEAR),
				1915);
		assertEquals(invalidDoctor.dateOfBirth.get(GregorianCalendar.MONTH), 11);
		assertEquals(
				invalidDoctor.dateOfBirth.get(GregorianCalendar.DAY_OF_MONTH),
				31);

		// valid gender
		assertEquals(doctor2.getGender(), "female");
		doctor2.setGender("male");
		assertNotEquals(doctor2.getGender(), "female");
		assertEquals(doctor2.getGender(), "male");

		// invalid gender
		assertEquals(invalidDoctor.getGender(), "male");
		invalidDoctor.setGender("unknown");
		assertNotEquals(invalidDoctor.getGender(), "unknown");
		assertEquals(invalidDoctor.getGender(), "male");
		
		// valid address
		assertEquals(doctor1.getAddress(), "The Quay, Waterford");
		doctor1.setAddress("The Quay, Wexford");
		assertNotEquals(doctor1.getAddress(), "The Quay, Waterford");
		assertEquals(doctor1.getAddress(), "The Quay, Wexford");
		
		// invalid address
		assertEquals(doctor2.getAddress(), "Manor Street, Waterford");
		doctor2.setAddress("abc");
		assertNotEquals(doctor2.getAddress(), "abc");
		assertEquals(doctor2.getAddress(), "Manor Street, Waterford");
		
		assertEquals(invalidDoctor.getAddress(), "to be confirmed");
		invalidDoctor.setAddress("1234567890123456789012345678901234567890123456789012345678901234567890");
		assertNotEquals(invalidDoctor.getAddress(), "1234567890123456789012345678901234567890123456789012345678901234567890");
		assertEquals(invalidDoctor.getAddress(), "to be confirmed");
		
		// valid contactNumber
		assertEquals(doctor1.getContactNumber(), 12345678);
		doctor1.setContactNumber(123456789);
		assertNotEquals(doctor1.getContactNumber(), 12345678);
		assertEquals(doctor1.getContactNumber(), 123456789);
		
		// invalid contactNumber
		assertEquals(doctor2.getContactNumber(), 123456789);
		doctor2.setContactNumber(12345);
		assertNotEquals(doctor2.getContactNumber(), 12345);
		assertEquals(doctor2.getContactNumber(), 123456789);
		
		// qualifiedInIreland
		assertEquals(doctor1.isQualifiedInIreland(), true);
		doctor1.setQualifiedInIreland(false);
		assertNotEquals(doctor1.isQualifiedInIreland(), true);
		assertEquals(doctor1.isQualifiedInIreland(), false);
		
		assertEquals(invalidDoctor.isQualifiedInIreland(), false);
		invalidDoctor.setQualifiedInIreland(true);
		assertEquals(invalidDoctor.isQualifiedInIreland(), true);
		assertNotEquals(invalidDoctor.isQualifiedInIreland(), false);
	}
	
	/**
	 * calculateRegistrationFee test
	 */
	@Test
	public void testCalculateRegistrationFee() {
		assertEquals(doctor1.calculateRegistrationFee(), 194, 0.1);
		assertEquals(doctor2.calculateRegistrationFee(), 425, 0.1);
		assertEquals(invalidDoctor.calculateRegistrationFee(), 410, 0.1);
		assertEquals(anotherInvalidDoctor.calculateRegistrationFee(), 641, 0.1);
	}

	/**
	 * addQualifications test
	 */
	@Test
	public void testAddQualification(){
		assertEquals(doctor1.getQualifications().size(), 0);
		doctor1.getQualifications().add(qualification1);
		assertEquals(doctor1.getQualifications().size(), 1);
		doctor1.getQualifications().add(qualification2);
		assertEquals(doctor1.getQualifications().size(), 2);
	}
	
	/**
	 * set and getQualifications test
	 */
	@Test
	public void testSetAndGetQualifications(){
		assertEquals(doctor1.getQualifications().size(), 0);
		doctor1.setQualifications(newQualifications);
		assertEquals(doctor1.getQualifications().size(), 2);
	}
	
	/**
	 * get, setComplaints, getComplaintsCounrt test
	 */
	@Test
	public void testGetAndSetComplaintsAndComplaintsCount() {
		assertEquals(doctor1.getComplaints().size(), 0);
		doctor1.getComplaints().add(complaint1);
		assertEquals(doctor1.getComplaints().size(), 1);
		doctor1.getComplaints().add(complaint2);
		assertEquals(doctor1.getComplaints().size(), 2);
		assertEquals(doctor1.getComplaintsCount(), 2);
	}
	
	/**
	 * toString test
	 */
	@Test
	public void testToString() {
		assertEquals(doctor1.toString(), "\nDoctor's full name: Bobby Bob"
				+ "\nDate of Birth: 1960/6/10" + "\nGender: male"
				+ "\nAddress: The Quay, Waterford"
				+ "\nContact Number: 12345678" + "\nQualified in Ireland: true"
				+ "\n\nQualifications: \n" + "\nComplaints:\n" + "No Complaints have been recorded");
	}
	
	@After
	public void tearDown() {
	}
}
