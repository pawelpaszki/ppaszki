import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Complaint class tester. tests constructor, setters and getters and toString
 * method. Setters and constructor are both tested with valid and invalid data
 * 
 * @author Pawel Paszki
 * @version 1.0
 */
public class ComplaintTest {

	//fields to be used in the test
	private Complaint complaint1, complaint2, invalidIdComplaint;

	/**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
	@Before
	public void setUp()
    { 
        complaint1 = new Complaint(0);
        complaint2 = new Complaint(2);
        invalidIdComplaint = new Complaint(-1);
    }
	
	/**
	 * constructor test. 
	 */
	@Test
	public void testComplaintConstructor()   
    { 
        //valid data
        assertEquals(complaint1.getComplaintId(), 0);
        assertEquals(complaint2.getComplaintId(), 2);
        assertEquals(complaint1.getResult(),"open");
        assertEquals(complaint2.getResult(),"open");
        assertNotNull(complaint1.getComplaintDate());
        assertNotNull(complaint2.getComplaintDate());
      
        //invalid data 
        assertNotEquals(invalidIdComplaint.getComplaintId(),-1);  
        assertEquals(invalidIdComplaint.getComplaintId(),99999); // default value
        assertEquals(invalidIdComplaint.getResult(),"open");
        assertNotNull(invalidIdComplaint.getComplaintDate());
    }
	
	/**
	 * getters and setters test
	 */
	@Test
	public void testSettersAndGetters()   
    { 
		//valid id
		assertEquals(complaint1.getComplaintId(),0);
        complaint1.setComplaintId(5);
        assertNotEquals(complaint1.getComplaintId(),0);  
        assertEquals(complaint1.getComplaintId(),5);
        
        //invalid id
        assertEquals(complaint2.getComplaintId(),2);
        complaint2.setComplaintId(-10);
        assertNotEquals(complaint2.getComplaintId(),-10);  
        assertEquals(complaint2.getComplaintId(),2);
        
        //valid result
        assertEquals(complaint2.getResult(),"open");
        complaint2.setResult("upheld");
        assertNotEquals(complaint2.getResult(),"open");
        assertEquals(complaint2.getResult(),"upheld");
        
        //invalid result
        assertEquals(complaint1.getResult(),"open");
        complaint1.setResult("invalid");
        assertNotEquals(complaint1.getResult(),"invalid");
        assertEquals(complaint1.getResult(),"open");
        
        //valid date
        complaint1.setComplaintDate(2014, 1, 2);
        assertEquals(complaint1.getComplaintDate().get(Calendar.YEAR),2014);
        assertEquals(complaint1.getComplaintDate().get(Calendar.MONTH),0);
        assertEquals(complaint1.getComplaintDate().get(Calendar.DAY_OF_MONTH),2);
        
        //invalid date
        complaint1.setComplaintDate(2015, 14, 40);
        assertNotEquals(complaint1.getComplaintDate().get(Calendar.YEAR),2015);
        assertNotEquals(complaint1.getComplaintDate().get(Calendar.MONTH),13); // month - 1
        assertNotEquals(complaint1.getComplaintDate().get(Calendar.DAY_OF_MONTH),40);
        assertEquals(complaint1.getComplaintDate().get(Calendar.YEAR),2014);
        assertEquals(complaint1.getComplaintDate().get(Calendar.MONTH),0);
        assertEquals(complaint1.getComplaintDate().get(Calendar.DAY_OF_MONTH),2);
        
    }
	
	/**
	 * toString test
	 */
	@Test
	public void testToString(){
		complaint1.setComplaintDate(2014, 1, 2);
		assertEquals(complaint1.toString(), "\tComplaint id: 0" +  "\n\tComplaint date: 2014/1/2" + "\n\tResult: open"
				+ "\n\n");
	}
	
	@After
    public void tearDown()
    {
    }

}
