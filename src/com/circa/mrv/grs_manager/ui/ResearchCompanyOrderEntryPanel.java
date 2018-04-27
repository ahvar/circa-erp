package com.circa.mrv.grs_manager.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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

import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.user.schedule.OrderSchedule;

/**
 * .
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
	private JComboBox cmbBoxStudyNumber = new JComboBox();
	/** ComboBox for Order Details order entry research site number */
	private JComboBox cmbBoxSiteNumber = new JComboBox();
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
	private JTextField txtFldProductName = new JTextField(20);
	/** Text Field for Product Details product part number */
	private JTextField txtFldProductPartNumber = new JTextField(12);
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
	
	/**
	 * Constructs the ResearchCompanyOrderSchedulePanel and sets up the GUI 
	 * components.
	 */
	public ResearchCompanyOrderEntryPanel() {
		super(new GridBagLayout());
		
		//RegistrationManager manager = RegistrationManager.getInstance();
		currentUser = (Employee)GRSManager.getInstance().getCurrentUser();

		
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
		
		GridBagConstraints c = new GridBagConstraints();
		
		/***************SET UP ORDER ENTRY PANEL CONTAINING DATE AND CUSTOMER NAME*********
		 *																				  *
		 * Define the horizontal and vertical layouts for date and customer information.  *
		 *  																			  *
		 *																				  *
		 **********************************************************************************/
		pnlDateAndCustomer = new JPanel();
		GroupLayout grpLayout = new GroupLayout(pnlDateAndCustomer);
		pnlDateAndCustomer.setLayout(grpLayout);
		grpLayout.setAutoCreateGaps(true);
		grpLayout.setAutoCreateContainerGaps(true);
		
		// define horizontal layout for date and customer information
		grpLayout.setHorizontalGroup(grpLayout.createSequentialGroup()
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				
				//add customer, site, study, and prouduct information 
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
					.addComponent(txtFldProductName))
				.addGroup(grpLayout.createSequentialGroup()
					.addComponent(lblProductPartNumberTitle)
					.addComponent(txtFldProductPartNumber))
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
				.addComponent(txtFldProductName)
				.addComponent(lblBillToNameTitle)
				.addComponent(txtFldBillToName))
			.addGroup(grpLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblProductPartNumberTitle)
				.addComponent(txtFldProductPartNumber)
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
			
		TitledBorder borderCourseDetails = BorderFactory.createTitledBorder(lowerEtched, "Order Details");
		pnlDateAndCustomer.setBorder(borderCourseDetails);
		pnlDateAndCustomer.setToolTipText("Order Details");				
		
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollProductRoll, c);
		//setVisible(true);
	}
	
	/**
	 * Updates the catalog and schedule tables.
	 */
	public void updateTables() {
		//courseRollTableModel.updateData();
		productRollTableModel.updateData();
		//initFacultySchedule();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		productRollTableModel.updateData();
		
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
		private String [] columnNames = {"Part Number", "Description", "Quantity", "$$/Unit"};
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
			//data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		
		/**
		 * Updates the given model with {@link Product} information from the {@link VendorDirectory}.
		 */
		public void updateData() {
			//data = currentUser.getSchedule().getScheduledCourses();
		}
	}

}