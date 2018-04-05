/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import com.circa.mrv.grs_manager.niox.ProductList;
import com.circa.mrv.grs_manager.niox.ConflictException;
import com.circa.mrv.grs_manager.niox.Mino;

/**
 * Tests for the Activity Class checkConflict method
 * @author Ben
 *
 */
public class ActivityTest {

	Product a1 = new Mino("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 25, "MW", 1330, 1445);
	Product a2 = new Mino("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 25, "TH", 1330, 1445);

	/**
	 * Test method for {@link com.circa.mrv.grs_manager.niox.Product#checkConflict(com.circa.mrv.grs_manager.niox.Product)}.
	 */
	@Test
	public void testCheckConflict() {

		//Test for non conflicting activities
		try{
			a1.checkConflict(a2); 
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}
		
		//Test for non conflicting activities commutative
		try{
			a2.checkConflict(a1); 
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}
		
		//Test for conflicting activities
		//Update a1 with the same meeting days as a2 and a time that starts at the same time a2 ends.
		a1.setMeetingDays("TH");
		a1.setActivityTime(1445, 1530);
		
		try{
			a1.checkConflict(a2);
			fail(); //ConflictException should have been thrown but was not
		} catch (ConflictException e){
		    assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		
		//Test for conflicting activities
		//Update a1 with a meeting day same as first in a2 and a time that starts at the same time a2 ends.
		a1.setMeetingDays("T");
		a1.setActivityTime(1445, 1530);
		
		try{
			a1.checkConflict(a2);
			fail(); //ConflictException should have been thrown but was not
		} catch (ConflictException e){
		    assertEquals("T 2:45PM-3:30PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		
		//Test for conflicting activities
		//Update a1 with a meeting day same as second in a2 and a time that starts at the same time a2 ends.
		a1.setMeetingDays("H");
		a1.setActivityTime(1445, 1530);
		
		try{
			a1.checkConflict(a2);
			fail(); //ConflictException should have been thrown but was not
		} catch (ConflictException e){
		    assertEquals("H 2:45PM-3:30PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		
		//Test for conflicting activities
		//Update a1 with a meeting day same as second in a2 and a time that starts at the same time a2 starts.
		a1.setMeetingDays("H");
		a1.setActivityTime(1330, 1530);
		
		try{
			a1.checkConflict(a2);
			fail(); //ConflictException should have been thrown but was not
		} catch (ConflictException e){
		    assertEquals("H 1:30PM-3:30PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		
		//Test for conflicting activities
		//Update a1 with a meeting day same as second in a2 and a time that starts and stops at the same time as a2.
		a1.setMeetingDays("H");
		a1.setActivityTime(1330, 1445);
		
		try{
			a1.checkConflict(a2);
			fail(); //ConflictException should have been thrown but was not
		} catch (ConflictException e){
		    assertEquals("H 1:30PM-2:45PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		
		//Test for conflicting activities
		//Update a1 with a meeting day same as second in a2 and a time that starts and stops before and after a2.
		a1.setMeetingDays("H");
		a1.setActivityTime(1300, 1500);
		
		try{
			a1.checkConflict(a2);
			fail(); //ConflictException should have been thrown but was not
		} catch (ConflictException e){
		    assertEquals("H 1:00PM-3:00PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		
		//Test for conflicting activities
		//Update a1 with a meeting day same as second in a2 and a time that starts and stops during a2.
		a1.setMeetingDays("H");
		a1.setActivityTime(1345, 1400);
		
		try{
			a1.checkConflict(a2);
			fail(); //ConflictException should have been thrown but was not
		} catch (ConflictException e){
		    assertEquals("H 1:45PM-2:00PM", a1.getMeetingString());
		    assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
		
		try{
			a1.setMeetingDays(null);
		} catch (IllegalArgumentException e){
			assertEquals(e.getMessage(), "Invalid meeting days");
		}
	}
	

}
