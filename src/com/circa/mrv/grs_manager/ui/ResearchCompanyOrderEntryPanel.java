package com.circa.mrv.grs_manager.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import com.circa.mrv.grs_manager.niox.Component;
import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.catalog.NioxCatalog;
import com.circa.mrv.grs_manager.catalog.OrderRecord;
import com.circa.mrv.grs_manager.document.Order;
import com.circa.mrv.grs_manager.location.BillTo;
import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.user.schedule.OrderSchedule;

/**
 * Order entry interface for a research company. 
 * 
 * @author Arthur Vargas.
 */
public class ResearchCompanyOrderEntryPanel extends JPanel implements ActionListener {
	/** Panel for displaying Study Details */
	private JPanel pnlDateAndCustomer;
	/** Label for Study Details order entry the order creation date title */
	private JLabel lblOrderEntryDateTitle = new JLabel("Date: ");
	/** Label for Order Details delivery date title */
	private JLabel lblDeliveryDate = new JLabel("Delivery Date: ");

	/** Label for Study Details order entry the study number title */
	private JLabel lblStudyNumberTitle = new JLabel("Study: ");
	/** Label for Study Details order entry the site number title*/
	private JLabel lblSiteNumberTitle = new JLabel("Site: ");
	
	/** Panel for displaying Order Details */
	private JPanel pnlOrderDetails;
	/** Label for Order Details order entry the NAV sales order number title*/
	private JLabel lblNAVOrderNumberTitle = new JLabel("NAV Sales Order Number: ");
	/** Customer PO number */
	private JLabel lblCustomerPOTitle = new JLabel("PO Number: ");
	/** GRS Order Number */
	private JLabel lblGRSOrderNumberTitle = new JLabel("GRS Order Number: ");
	/** Button for submitting order */
	private JButton submit = new JButton("Submit");
	/** Button for clearing order entry panel */
	private JButton clear = new JButton("Clear");
	/** Button for adding a product to the product list */
	private JButton add = new JButton("Add");
	
	/** Panel for displaying customer details */
	private JPanel pnlCustomerDetails;
	/** Label for Order Details customer name title */
	private JLabel lblCustomerNameTitle = new JLabel("Customer Name: ");
	/** Label for Order Details address title */
	private JLabel lblAddressTitle = new JLabel("Address: ");
	/** Label for Order Details address title */
	private JLabel lblAddress2Title = new JLabel("Address: ");
	/** Label for Customer Details the city title */
	private JLabel lblCityTitle = new JLabel("City: ");
	/** Label for Customer Details the state title */
	private JLabel lblStateTitle = new JLabel("State: ");
	/** Label for Customer Details the zip code title */
	private JLabel lblZipCodeTitle = new JLabel("Zip Code: ");
	
	/** Label for Order Details ship-to location name title */
	private JLabel lblShipToNameTitle = new JLabel("Shipping Location: ");
	/** Label for Order Details address title */
	private JLabel lblShipToAddressTitle = new JLabel("Address: ");
	/** Label for Order Details address title */
	private JLabel lblShipToAddress2Title = new JLabel("Address: ");
	/** Label for Customer Details the city title */
	private JLabel lblShipToCityTitle = new JLabel("City: ");
	/** Label for Customer Details the state title */
	private JLabel lblShipToStateTitle = new JLabel("State: ");
	/** Label for Customer Details the zip code title */
	private JLabel lblShipToZipCodeTitle = new JLabel("Zip Code: ");
	
	/** Label for Customer Details bill-to location name title */
	private JLabel lblBillToNameTitle = new JLabel("Billing Location: ");
	/** Label for Customer Details bill-to address title */
	private JLabel lblBillToAddressTitle = new JLabel("Address: ");
	/** Label for Customer Details bill-to address title */
	private JLabel lblBillToAddress2Title = new JLabel("Address: ");
	/** Label for Customer Details the bill-to city title */
	private JLabel lblBillToCityTitle = new JLabel("City: ");
	/** Label for Customer Details the bill-to state title */
	private JLabel lblBillToStateTitle = new JLabel("State: ");
	/** Label for Customer Details the bill-to zip code title */
	private JLabel lblBillToZipCodeTitle = new JLabel("Zip Code: ");
	
	
	/** Label for Order Details product list capacity title */
	private JLabel lblProductListCapTitle = new JLabel("Product List Capacity: ");
	/** Label for Order Details remaining product list capacity title */
	private JLabel lblRemainingProductListCapTitle = new JLabel("Remaining List Capacity: ");
	/** Label for Order Details extra capacity title */
	private JLabel lblExtraCapacityTitle = new JLabel("Extra Capacity: ");
	/** Label for Product Details name title */
	private JLabel lblProductNameTitle = new JLabel("Product: ");
	/** Label for Product Details part number title */
	private JLabel lblProductPartNumberTitle = new JLabel("Part Number: ");
	/** Label for Product Details description title */
	private JLabel lblProductDescriptionTitle = new JLabel("Description: ");
	/** Label for Product Details quantity title */
	private JLabel lblProductQuantityTitle = new JLabel("Quantity: ");
	/** Label for Product Details Unit Price */
	private JLabel lblProductCostTitle = new JLabel("Price: ");
	
	
	/** ComboBox for Order Details order entry research study number */
	private JComboBox<Object> cmbBoxStudyNumber;
	/** ComboBoxModel for study numbers */
	private DefaultComboBoxModel<Object> studyModel;
	/** ComboBox for Order Details order entry research site number */
	private JComboBox<Object> cmbBoxSiteNumber;
	/** ComboBoxModel for site numbers */
	private DefaultComboBoxModel<Object> siteModel;
	/** Text Field for Order Details order entry NAV sales order number */
	private JTextField txtFldNAVOrderNumber = new JTextField(10);
	/** Text Field for Order Details customer purchase order number */
	private JTextField txtFldPONumber = new JTextField(10);
	/** Text Field for Order Details customer name */
	private JTextField txtFldCustomerName = new JTextField(15);
	
	/** Text Field for Order Entry customer details address */
	private JTextField txtFldAddress = new JTextField(20);
	/** Text Field for Order Entry customer details address */
	private JTextField txtFldAddress2 = new JTextField(10);
	/** Text Field for Order Entry customer details city */
	private JTextField txtFldCity = new JTextField(15);
	/** Text Field for Order Entry customer details state */
	private JTextField txtFldState = new JTextField(4);
	/** Text Field for Order Entry customer details zip code */
	private JTextField txtFldZipCode = new JTextField(8);
	
	/** Text Field for Order Details ship-to location name */
	private JTextField txtFldShipToName = new JTextField(20);
	/** Text Field for Order Details ship-to street address */
	private JTextField txtFldShipToAddress = new JTextField(50);
	/** Text Field for Order Details ship-to building, suite, or room number */
	private JTextField txtFldShipToAddress2 = new JTextField(15);
	/** Text Field for Order Details ship-to city */
	private JTextField txtFldShipToCity = new JTextField(15);
	/** Text Field for Order Details the state */
	private JTextField txtFldShipToState = new JTextField(15);
	/** Text Field for Order Details the zip code */
	private JTextField txtFldShipToZipCode = new JTextField(10);
	
	/** Text Field for Order Details bill-to location name */
	private JTextField txtFldBillToName = new JTextField(20);
	/** Text Field for Order Details bill-to street address */
	private JTextField txtFldBillToAddress = new JTextField(50);
	/** Text Field for Order Details bill-to building, suite, or room number */
	private JTextField txtFldBillToAddress2 = new JTextField(15);
	/** Text Field for Order Details bill-to city */
	private JTextField txtFldBillToCity = new JTextField(15);
	/** Text Field for Order Details the bill-to state */
	private JTextField txtFldBillToState = new JTextField(15);
	/** Text Field for Order Details the bill-to zip code */
	private JTextField txtFldBillToZipCode = new JTextField(10);
	
	/** Text Field for Order Details order entry date */
	private JTextField txtFldOrderEntryDate = new JTextField(10);
	/** Text Field for Order Details order delivery date */
	private JTextField txtFldOrderDeliveryDate = new JTextField(10);
	
	/** Text Field for Product Details product name */
	private JComboBox<Object> cmbBoxProductName;
	/** ComboBoxModel for product names */
	private DefaultComboBoxModel<Object> nameModel;
	/** Text Field for Product Details product part number */
	private JComboBox<Object> cmbBoxProductPartNumber;
	/** ComboBoxModel for product part numbers */
	private DefaultComboBoxModel<Object> numberModel;
	/** Text Field for Product Details product description */
	private JTextField txtFldProductDescription = new JTextField(30);
	/** Text Field for Product Details product quantity */
	private JTextField txtFldProductQuantity = new JTextField(2);
	/** Text Field for Product Details product unit cost */
	private JTextField txtFldProductUnitCost = new JTextField(8);
	
	/** Panel for displaying order details */
	/** ID used for object serialization */
	private static final long serialVersionUID = 1L;

	/** JTable for displaying the roll of Products */
	private JTable tableProductsRoll;
	/** Scroll pane for table */
	private JScrollPane scrollProductRoll;
	/** TableModel for roll of Products */
	private ProductRollTableModel productRollTableModel;
//	/** Button to logout */
//	private JButton btnLogout;
//	JPanel pnlButtons = new JPanel();
	/** Current user */
	private Employee currentUser;
	/** Catalog of all available NIOX products */
	private NioxCatalog catalog;
	/** Order records */
	private OrderRecord orderRecord;
	/** The Order the customer is currently entering */
	Order order;
	
	/**
	 * Constructs the ResearchCompanyOrderSchedulePanel and sets up the GUI 
	 * components.
	 */
	public ResearchCompanyOrderEntryPanel() {
		super(new GridBagLayout());
		pnlDateAndCustomer = new JPanel();
		currentUser = (Employee)GRSManager.getInstance().getCurrentUser();
		catalog = GRSManager.getInstance().getNioxCatalog();
		orderRecord = GRSManager.getInstance().getOrderRecord();
		try {
		order = new Order(GRSManager.getInstance().getOrderRecord().getLastOrder().getNumber() + 1);
		}catch(NullPointerException npe) {
			order = new Order(1);
		}
		
		submit.addActionListener(this);
		clear.addActionListener(this);
		add.addActionListener(this);
		
		//System.out.println(orderRecord.getOrderRecordList().size());
		GroupLayout grpLayout = new GroupLayout(pnlDateAndCustomer);
		pnlDateAndCustomer.setLayout(grpLayout);
		grpLayout.setAutoCreateGaps(true);
		grpLayout.setAutoCreateContainerGaps(true);
		
		GridBagConstraints c = new GridBagConstraints();
		
		/**********SET UP ORDER ENTRY PANEL CONTAINING DATE AND CUSTOMER NAME**************
		 *																				  *
		 * Define the horizontal and vertical layouts for date and customer information.  *
		 *  																			  *
		 *																				  *
		 **********************************************************************************/
			
		try {
			numberModel = new DefaultComboBoxModel<Object>(catalog.getProductPartNumbers());
			nameModel = new DefaultComboBoxModel<Object>(catalog.getProductNames());
			studyModel = new DefaultComboBoxModel<Object>(orderRecord.getStudyList().toArray());
			siteModel = new DefaultComboBoxModel<Object>(orderRecord.getSiteList().toArray());
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println("Order entry panel: an exception was caught in order entry");
			numberModel = new DefaultComboBoxModel<Object>(NioxCatalog.getDefaultProductPartNumbers());
			nameModel = new DefaultComboBoxModel<Object>(NioxCatalog.getDefaultProductNames());
			studyModel = new DefaultComboBoxModel<Object>(OrderRecord.getDefaultStudyNumbers());
			siteModel = new DefaultComboBoxModel<Object>(OrderRecord.getDefaultStiteNumbers());
		}
		
		cmbBoxProductPartNumber = new JComboBox<Object>(numberModel);
		cmbBoxProductPartNumber.setSelectedIndex(-1);		
		cmbBoxProductName = new JComboBox<Object>(nameModel);
		cmbBoxProductName.setSelectedIndex(-1);
		cmbBoxStudyNumber = new JComboBox<Object>(studyModel);
		cmbBoxStudyNumber.setSelectedIndex(-1);
		cmbBoxSiteNumber = new JComboBox<Object>(siteModel);
		cmbBoxSiteNumber.setSelectedIndex(-1);
		
		//DefaultComboBoxModel<String> siteModel = new DefaultComboBoxModel<String>(orderRecord.getSites());
		grpLayout.setHorizontalGroup(grpLayout.createSequentialGroup()
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				
				//add customer, site, study, and product information 
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblCustomerNameTitle)
					.addComponent(txtFldCustomerName))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblStudyNumberTitle)
					.addComponent(cmbBoxStudyNumber))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblSiteNumberTitle)
					.addComponent(cmbBoxSiteNumber))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblOrderEntryDateTitle)
					.addComponent(txtFldOrderEntryDate))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblDeliveryDate)
					.addComponent(txtFldOrderDeliveryDate))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblProductNameTitle)
					.addComponent(cmbBoxProductName,0,javax.swing.GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblProductPartNumberTitle)
					.addComponent(cmbBoxProductPartNumber,0,javax.swing.GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblProductDescriptionTitle)
					.addComponent(txtFldProductDescription))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblProductQuantityTitle)
					.addComponent(txtFldProductQuantity))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblProductCostTitle)
					.addComponent(txtFldProductUnitCost)))
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(submit)
				.addComponent(clear)
				.addComponent(add))
			
			// add ship to and bill to information for the customer
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblShipToNameTitle)
					.addComponent(txtFldShipToName))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblShipToAddressTitle)
					.addComponent(txtFldShipToAddress))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblShipToAddress2Title)
					.addComponent(txtFldShipToAddress2) )
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblShipToCityTitle)
					.addComponent(txtFldShipToCity) )
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblShipToStateTitle)
					.addComponent(txtFldShipToState))
				.addGroup(grpLayout.createSequentialGroup()//.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(lblShipToZipCodeTitle)
					.addComponent(txtFldShipToZipCode))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblBillToNameTitle)
					.addComponent(txtFldBillToName))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblBillToAddressTitle)
					.addComponent(txtFldBillToAddress))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblBillToAddress2Title)
					.addComponent(txtFldBillToAddress2))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblBillToCityTitle)
					.addComponent(txtFldBillToCity))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblBillToStateTitle)
					.addComponent(txtFldBillToState))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblBillToZipCodeTitle)
					.addComponent(txtFldBillToZipCode)))
		);
		
		// link buttons so they are the same size
		grpLayout.linkSize(SwingConstants.HORIZONTAL, submit, clear);
		
		// define vertical layout for date and customer information
		grpLayout.setVerticalGroup(grpLayout.createSequentialGroup()
			// customer study, site, and ship-to information	
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblCustomerNameTitle)
				.addComponent(txtFldCustomerName)
				.addComponent(submit)
				.addComponent(lblShipToNameTitle)
				.addComponent(txtFldShipToName))
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)	
				.addComponent(lblStudyNumberTitle)
				.addComponent(cmbBoxStudyNumber)
				.addComponent(clear)
				.addComponent(lblShipToAddressTitle)
				.addComponent(txtFldShipToAddress))
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblSiteNumberTitle)
				.addComponent(cmbBoxSiteNumber)
				.addComponent(add)
				.addComponent(lblShipToAddress2Title)
				.addComponent(txtFldShipToAddress2))
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblOrderEntryDateTitle)
			    .addComponent(txtFldOrderEntryDate)
				.addComponent(lblShipToCityTitle)
				.addComponent(txtFldShipToCity))
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblDeliveryDate)
				.addComponent(txtFldOrderDeliveryDate)
				.addComponent(lblShipToStateTitle)
				.addComponent(txtFldShipToState))
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblShipToZipCodeTitle)
				.addComponent(txtFldShipToZipCode))
			
			// product information
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblProductNameTitle)
				.addComponent(cmbBoxProductName,javax.swing.GroupLayout.PREFERRED_SIZE,javax.swing.GroupLayout.DEFAULT_SIZE,javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(lblBillToNameTitle)
				.addComponent(txtFldBillToName))
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblProductPartNumberTitle)
				.addComponent(cmbBoxProductPartNumber,javax.swing.GroupLayout.PREFERRED_SIZE,javax.swing.GroupLayout.DEFAULT_SIZE,javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(lblBillToAddressTitle)
				.addComponent(txtFldBillToAddress))
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblProductDescriptionTitle)
				.addComponent(txtFldProductDescription)
				.addComponent(lblBillToAddress2Title)
				.addComponent(txtFldBillToAddress2))
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblProductQuantityTitle)
				.addComponent(txtFldProductQuantity)
				.addComponent(lblBillToCityTitle)
				.addComponent(txtFldBillToCity))
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblProductCostTitle)
				.addComponent(txtFldProductUnitCost)
				.addComponent(lblBillToStateTitle)
				.addComponent(txtFldBillToState))
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblBillToZipCodeTitle)
				.addComponent(txtFldBillToZipCode))
		);
		
		c.gridy = GridBagConstraints.RELATIVE;
		c.weightx = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlDateAndCustomer, c);
		
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Directory Buttons");		

		productRollTableModel = new ProductRollTableModel();
		tableProductsRoll = new JTable(productRollTableModel);
		tableProductsRoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableProductsRoll.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableProductsRoll.setFillsViewportHeight(true);
		
		scrollProductRoll = new JScrollPane(tableProductsRoll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Product Catalog");
		scrollProductRoll.setBorder(boarder);
		
		updateTables();
			
		TitledBorder borderCourseDetails = BorderFactory.createTitledBorder(lowerEtched, "Order Details");
		pnlDateAndCustomer.setBorder(borderCourseDetails);
		pnlDateAndCustomer.setToolTipText("Order Details");				
		//System.out.println("catalog size: " + catalog.allProducts());
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollProductRoll, c);
		setVisible(true);
		
	}
	
	/**
	 * Updates the catalog and schedule tables.
	 */
	public void updateTables() {
		productRollTableModel.updateData();
		
		this.validate();
		this.repaint();
	}
	
	/**
	 * Updates the combo boxes for study, site, product #, product name
	 */
	public void updateComboBoxes() {
		
		DefaultComboBoxModel<Object> studies = new DefaultComboBoxModel<Object>();
		for(int i = 0; i < GRSManager.getInstance().getOrderRecord().getStudyList().size();i++) {
			studies.addElement(GRSManager.getInstance().getOrderRecord().getStudyList().get(i));
		}
		cmbBoxStudyNumber.setModel(studies); 
		cmbBoxStudyNumber.setSelectedIndex(-1);
		
		DefaultComboBoxModel<Object> sites = new DefaultComboBoxModel<Object>();
		for(int i = 0; i < GRSManager.getInstance().getOrderRecord().getSiteList().size();i++) {
			sites.addElement(GRSManager.getInstance().getOrderRecord().getSiteList().get(i));
		}
		cmbBoxSiteNumber.setModel(sites); 
		cmbBoxSiteNumber.setSelectedIndex(-1);
		
		DefaultComboBoxModel<Object> parts = new DefaultComboBoxModel<Object>();
		for(int i = 0; i < GRSManager.getInstance().getNioxCatalog().getProductPartNumbers().length; i++) {
			parts.addElement(GRSManager.getInstance().getNioxCatalog().getProductPartNumbers()[i]);
		}
		cmbBoxProductPartNumber.setModel(parts); 
		cmbBoxProductPartNumber.setSelectedIndex(-1);
		
		DefaultComboBoxModel<Object> names = new DefaultComboBoxModel<Object>();
		for(int i = 0; i < GRSManager.getInstance().getNioxCatalog().getProductNames().length; i++) {
			names.addElement(GRSManager.getInstance().getNioxCatalog().getProductNames()[i]);
		}
		cmbBoxProductName.setModel(names); 
		cmbBoxProductName.setSelectedIndex(-1);

		cmbBoxStudyNumber.addActionListener(this);
		cmbBoxSiteNumber.addActionListener(this);
		cmbBoxProductPartNumber.addActionListener(this);
		cmbBoxProductName.addActionListener(this);
		
		pnlDateAndCustomer.validate();
		pnlDateAndCustomer.repaint();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cmbBoxStudyNumber) {
			try{
			String number = (String) cmbBoxStudyNumber.getItemAt(cmbBoxStudyNumber.getSelectedIndex());
			Object[] sites = GRSManager.getInstance().getOrderRecord().getTheseStudySites(number);
			DefaultComboBoxModel<Object> siteModel = new DefaultComboBoxModel<Object>();
			for(int i = 0; i < sites.length; i++) {
				siteModel.addElement(sites[i]);
			}
			cmbBoxSiteNumber.setModel(siteModel);
			cmbBoxSiteNumber.addActionListener(this);
			}catch(NullPointerException npe) {
				
			}
		}
		if(e.getSource() == cmbBoxSiteNumber) {
			try {
			String study = (String) cmbBoxStudyNumber.getItemAt(cmbBoxStudyNumber.getSelectedIndex());
			String site = (String) cmbBoxSiteNumber.getItemAt(cmbBoxSiteNumber.getSelectedIndex());
			String[] address = GRSManager.getInstance().getOrderRecord().getThisResearchSite(study, site);
			txtFldCustomerName.setText(BillTo.getErt());
			txtFldShipToName.setText(address[0]);
			txtFldShipToAddress.setText(address[1]);
			txtFldShipToCity.setText(address[2]);
			txtFldShipToState.setText(address[3]);
			txtFldShipToZipCode.setText(address[4]);
			txtFldBillToName.setText(BillTo.getErt());
			txtFldBillToAddress.setText(BillTo.getErtStreet());
			txtFldBillToAddress2.setText(BillTo.getErtStreet2());
			txtFldBillToState.setText(BillTo.getErtCountry());
			}catch(NullPointerException npe) {
				
			}
			
		}
		if(e.getSource() == cmbBoxProductName) {
			try {
			String name = (String) cmbBoxProductName.getItemAt(cmbBoxProductName.getSelectedIndex());
			//System.out.println(name);
			Scanner scan = new Scanner(name);
			String fam = null; String gen = null; String desc = null;
			fam = scan.next(); gen = scan.next(); 
			while(scan.hasNext()) {
				if(desc == null) desc = scan.next();
				else desc = desc + " " + scan.next();
			}
			//System.out.println("scanned fam/gen/desc: " + fam + " " + gen + " " + desc);
			Object[][] products = GRSManager.getInstance().getNioxCatalog().getNioxCatalog();
			//DefaultComboBoxModel<Object> productModel = new DefaultComboBoxModel<Object>();
			for(int i = 0; i < products.length; i++) {
				if(products[i][1] == null || products[i][1].equals("") || products[i][2] == null || products[i][2].equals("") ||
						products[i][3] == null || products[i][3].equals("")) continue;
				//System.out.println("fam: " + products[i][1] + " " + "gen: " + products[i][2] + " " + products[i][3]);
				if(products[i][1].equals(fam) && products[i][2].equals(gen) && products[i][3].equals(desc)) {
					//System.out.println("match");
					cmbBoxProductPartNumber.setSelectedIndex(i); break;
				}
			}
			scan.close();
			txtFldProductDescription.setText(desc);
			//cmbBoxProductPartNumber.setModel(productModel);
			//cmbBoxProductPartNumber.addActionListener(this);
			}catch(NullPointerException npe) {
				
			}
		} else if (e.getSource() == cmbBoxProductPartNumber) {
			try {
			String number = (String) cmbBoxProductPartNumber.getItemAt(cmbBoxProductPartNumber.getSelectedIndex());
			Scanner scan = new Scanner(number);
			String numscan = scan.next();
			Object[][] catalog = GRSManager.getInstance().getNioxCatalog().getNioxCatalog();
			//DefaultComboBoxModel<Object> nameModel = new DefaultComboBoxModel<Object>();
			for(int i = 0; i < catalog.length; i++) {
				if(catalog[i][1] == null || catalog[i][1].equals("") || catalog[i][2] == null || catalog[i][2].equals("") ||
						catalog[i][3] == null || catalog[i][3].equals("") || catalog[i][0] == null || catalog[i][0].equals("")) continue;
				if(catalog[i][0].equals(numscan)) { 
					cmbBoxProductName.setSelectedIndex(i); 
					txtFldProductUnitCost.setText((String)catalog[i][4]);
					break;
				}
			}
			scan.close();
			}catch(NullPointerException npe) {
				
			}
			//cmbBoxProductName.setModel(nameModel);
			//cmbBoxProductName.addActionListener(this);
		}
		
		if(e.getSource() == add) {
			int pdQty = 0;
			if(txtFldProductQuantity.getText() == null || txtFldProductQuantity.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Please enter a quantity");
			} else {
				try {
					pdQty = Integer.parseInt(txtFldProductQuantity.getText());
				} catch(Exception exc) { 
					JOptionPane.showMessageDialog(this, exc.getMessage());
					txtFldProductQuantity.setText("");
				}
				String pdName = (String) cmbBoxProductName.getSelectedItem();
				Scanner scan = new Scanner(pdName);
				String fam = scan.next(); 
				scan.close();
				String desc = txtFldProductDescription.getText();
				String part = (String) cmbBoxProductPartNumber.getSelectedItem();
				double price = Double.parseDouble(txtFldProductUnitCost.getText());
				Component c = new Component(fam,desc,part,price);
				order.addProduct(c,pdQty);
				txtFldProductDescription.setText("");
				txtFldProductQuantity.setText("");
				txtFldProductUnitCost.setText("");
				cmbBoxProductPartNumber.setSelectedIndex(-1);
				cmbBoxProductName.setSelectedIndex(-1);
				productRollTableModel.updateData();
				scrollProductRoll.revalidate();
				scrollProductRoll.repaint();
				productRollTableModel.fireTableDataChanged();
			}
			
			
		} else if(e.getSource() == submit) {
			
			/* it is either a new site for an existing study, a new study with a new site
			 * a new study with the same site number as another ongoing study
			 * an existing study and site
			 */
			String study = (String) cmbBoxStudyNumber.getSelectedItem();
			String site = (String) cmbBoxSiteNumber.getSelectedItem();
			
			
			String shpToName = txtFldShipToName.getText();
			String shpToAdd1 = txtFldShipToAddress.getText();
			String shpToAdd2 = txtFldShipToAddress2.getText();
			String shpToCity = txtFldShipToCity.getText();
			String shpToState = txtFldShipToState.getText();
			String shpToZip = txtFldShipToZipCode.getText();
		} else if(e.getSource() == clear) {
			
		}
		
		productRollTableModel.updateData();
		
		pnlDateAndCustomer.validate();
		pnlDateAndCustomer.repaint();
		this.validate();
		this.repaint();
	}
	
	/**
	 * {@link ProductRollTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of NIOX Products
	 * @author Arthur Vargas
	 */
	private class ProductRollTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Part Number", "Family", "Description", "$$/Unit"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link ProductRollTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public ProductRollTableModel() {
			updateData();
		}

		/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
		public int getColumnCount() {
			return columnNames.length;
		}

		/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		public int getRowCount() {
			if (data == null) 
				return 0;
			return data.length;
		}
		
		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		public String getColumnName(int col) {
			return columnNames[col];
		}

		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		public Object getValueAt(int row, int col) {
			if (data == null)
				return null;
			return data[row][col];
		}
		
		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		
		/**
		 * Updates the given model with {@link Product} information from the {@link VendorDirectory}.
		 */
		public void updateData() {
			try{
			data = order.getProductDisplay();
			}catch(NullPointerException npe) {
				
				data = new String[50][4];
			}
		}
	}

}