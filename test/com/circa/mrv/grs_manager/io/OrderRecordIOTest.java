/**
 * 
 */
package com.circa.mrv.grs_manager.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.circa.mrv.grs_manager.util.LinkedListRecursive;

/**
 * Tests OrderRecordIO
 * @author Arthur Vargas
 */
public class OrderRecordIOTest {
	/** Test order records */
	private static final String orderRecordFileName = "test-files/order-record-test/order-records-least";
	/** test order records */
	private final String someOrderRecords = "test-files/order-record-test/order-record-more";
	/** test order records */
	private final String moreOrderRecords = "test-files/order-record-test/order-record-and-more";
	/** test order records */
	private final String allOrderRecords = "test-files/order-record-test/order-record-all";
	/** Study id 006155 */
	private static final String study006155 = "006155";
	/** Study id 145986 */
	private static final String study145986 = "145986";
	/** Research ship-to on line 5 / column 30 of order-records */
	private final String siteName = "Clinical Research of Gastonia";
	/** fax number on line 5 / column 37 of order-records */
	private final String faxNumber = "0017046712322";
	/** PO number on line 5 / column 48 of order records */
	private String po = "15003907 OD";
	
	/** Research ship-to on line 9 / column 30 of order-records */
	private final String siteNameLine9 = "Clinical Research of Charlotte";
	/** fax number on line 9 / column 37 of order-records */
	private final String faxNumberLine9 = "0017043419996";
	/** PO number on line 9 / column 48 of order records */
	private String poLine9 = "15003912 OD";
	
	
	/** Number of product titles */
	private static final int productTitleCount = 22;
	/** Number of order records */
	private static final int orderRecordCount = 7;
	/** Total number of order record columns */
	private static final int orderRecordColumns = 54;
	/** Test order record titles */
	private static final String orderRecordTitleFileName = "test-files/order-record-test/titles";

	/**
	 * Tests readOrderRecord()
	 */
	@Test
	public void testReadOrderRecord() throws FileNotFoundException,IllegalArgumentException {
		
		String [][] orders = new String[10][orderRecordColumns]; 
		LinkedListRecursive<ProductTitle> ptList = new LinkedListRecursive<ProductTitle>();
		try {
			OrderRecordIO.readOrderRecord(orderRecordFileName,orders,ptList,orderRecordColumns - 1);
		} catch (IOException ioe) {
			fail(ioe.getMessage());
			if(ioe instanceof FileNotFoundException) throw new FileNotFoundException(ioe.getMessage());
			else throw new IllegalArgumentException(ioe.getMessage());
			
		}
		//for(int i = 1; i < orders.length; i++) {
			//System.out.print("row: " + i + " ");
			//for(int j = 0; j < orderRecordColumns; j++) {
				//System.out.print(j + "   " + orders[i][j] + " ");
			//}
			//System.out.println();
		//}
		if(!orders[1][0].equals(study006155) )
			fail();
				
		if(!orders[1][30].equals(siteName))
			fail();
		
		if(!orders[1][48].equals(po) )
			fail();
		
		if(!orders[2][0].equals(study006155) || !orders[2][30].equals("American Clinical Trials") || !orders[2][48].equals("15003909 OD") )
			fail();
		
		if(!orders[3][0].equals(study006155) || !orders[3][30].equals("Sunset Medical Research") || !orders[3][48].equals("15003911 OD") )
			fail();
		if(!orders[4][0].equals("145986") || !orders[4][1].equals("5009") || !orders[4][2].equals("US") || !orders[4][28].equals("Johnston") ||
				!orders[4][30].equals("Clinical Research of Charlotte") )
			fail();
		
		String [][] orders2 = new String[800][orderRecordColumns]; 
		LinkedListRecursive<ProductTitle> ptList2 = new LinkedListRecursive<ProductTitle>();
		try {
			OrderRecordIO.readOrderRecord(allOrderRecords,orders2,ptList2,orderRecordColumns - 1);
		} catch (IOException ioe) {
			fail(ioe.getMessage());
			if(ioe instanceof FileNotFoundException) throw new FileNotFoundException(ioe.getMessage());
			else throw new IllegalArgumentException(ioe.getMessage());
			
		}
		
		if(!orders2[1][0].equals(study006155) )
			fail();
				
		if(!orders2[1][30].equals(siteName))
			fail();
		
		if(!orders2[1][48].equals(po) )
			fail();
		
		if(!orders2[2][0].equals(study006155) || !orders2[2][30].equals("American Clinical Trials") || !orders2[2][48].equals("15003909 OD") )
			fail();
		
		if(!orders2[3][0].equals(study006155) || !orders2[3][30].equals("Sunset Medical Research") || !orders2[3][48].equals("15003911 OD") )
			fail();
		if(!orders2[4][0].equals("145986") || !orders2[4][1].equals("5009") || !orders2[4][2].equals("US") || !orders2[4][28].equals("Johnston") ||
				!orders2[4][30].equals("Clinical Research of Charlotte") )
			fail();
		if(!orders2[5][0].equals("006155") || !orders2[5][1].equals("5015") || !orders2[5][2].equals("US") || !orders2[5][28].equals("Given") ||
				!orders2[5][30].equals("Allergy and Respiratory Center") )
			fail();
		if(!orders2[20][0].equals("006155") || !orders2[20][1].equals("5005") || !orders2[20][2].equals("US") || !orders2[20][28].equals("Pham") ||
				!orders2[20][30].equals("PNTN Inc. DBA Allianz Medical &Research Center") )
			fail();
		if(!orders2[30][0].equals("006155") || !orders2[30][1].equals("1826 2721") || !orders2[30][2].equals("VN") || !orders2[30][28].equals("Ngo") ||
				!orders2[30][30].equals("Bach Mai Hospital") )
			fail();
		if(!orders2[40][1].equals("5023") || !orders2[40][28].equals("Yarbrough") || !orders2[40][48].equals("15004338 OD") || !orders2[40][51].equals("Dec-15") ||
				!orders2[40][46].equals("FedEx") )
			fail();
		if(!orders2[70][1].equals("2297") || !orders2[70][48].equals("no PO number needed -> warranty") || !orders2[70][51].equals("will not be charged (warranty)") || !orders2[70][53].equals("ES") ||
				!orders2[70][36].equals("004980313545726") )
			fail();
		if(!orders2[124][1].equals("5053") || !orders2[124][48].equals("18000647 OD") || !orders2[124][50].equals("Feb-18") || !orders2[124][43].equals("744532955281") ||
				!orders2[124][39].equals("rorr@phoenixmedicalgroup.com") )
			fail();
		if(!orders2[240][1].equals("6366") || !orders2[240][0].equals("006156") || !orders2[240][2].equals("NL") || !orders2[240][25].equals("149608") ||
				!orders2[240][30].equals("Ziekenhuis Rijnstate") )
			fail();
		if(!orders2[426][1].equals("6773") || !orders2[426][0].equals("006156") || !orders2[426][2].equals("SK") || !orders2[426][33].equals("Bratislava") ||
				!orders2[426][30].equals("Univerzitná nemocnica Bratislava Nemocnica Ružinov Klinika funk?nej diagnostiky") )
			fail();

	}

	/**
	 * Tests clearStringBuilder
	 */
	@Test
	public void testClearStringBuilder() {
		StringBuilder sb = new StringBuilder();
		sb.append("a");
		sb.append("b");
		sb.append("c");
		OrderRecordIO.clearStringBuilder(sb);
		assertEquals(sb.length(),0);
	}

	/**
	 * Tests readOrderTitles
	 */
	@Test
	public void testReadOrderTitles() {
		/** These tests must use a full set of product titles */
		String [][] titles = new String[1][orderRecordColumns];
		LinkedListRecursive<ProductTitle> ptList = new LinkedListRecursive<ProductTitle>();
		try {
			OrderRecordIO.readOrderTitles(orderRecordTitleFileName, titles, ptList);
			if(!titles[0][0].equals("study")) fail();
			if(!titles[0][1].equals("Site ID")) fail();
			if(!titles[0][2].equals("Country")) fail();
			if(!titles[0][3].equals("992040 NIOX VERO device (Device handle handle cap power adapter power cord battery manual)")) fail();
			if(!titles[0][4].equals("707170 NIOX VERO test kit 60 (1 sensor for 60 tests 100 filters)")) fail();
			if(!titles[0][5].equals("707171 NIOX VERO test kit 100 (1 sensor for 100 tests 140 filters)")) fail();
			if(!titles[0][6].equals("992045 NIOX VERO breathing handle includes NO scrubber for 1000 measurements; expires after one year")) fail();
			if(!titles[0][7].equals("992046 NIOX VERO Power Adapter")) fail();
			if(!titles[0][8].equals("992044 NIOX VERO Handle Cap")) fail();
			if(!titles[0][53].equals("Warranty exchange Yes/No?"))fail();
			//System.out.println(titles[0][53]);
			if(!titles[0][52].equals("Customer Care ticket ID"))fail();
			if(!titles[0][51].equals("billing month (invoice/month)"))fail();
			if(!titles[0][50].equals("ERT Comment (e.g. ticket number)"))fail();
			if(!titles[0][49].equals("CIRCASSIA COMMENTS")) fail();
			if(!titles[0][48].equals("PO"))fail();
			if(!titles[0][47].equals("Comment")) fail();
			if(!titles[0][46].equals("Forwarder"))fail();
			if(!titles[0][20].equals("NIOX MINO Expiration Date Test Kit 100"))fail();
			if(!titles[0][21].equals("992029 NO scrubber NIOX MINO 2009")) fail();
			if(!titles[0][22].equals("NIOX MINO Expiration Date NO scrubber"))fail();
			if(!titles[0][34].equals("Country"))fail();
			if(!titles[0][35].equals("State (USA and CAN only)")) fail();
			if(!titles[0][36].equals("Telephone"))fail();
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
