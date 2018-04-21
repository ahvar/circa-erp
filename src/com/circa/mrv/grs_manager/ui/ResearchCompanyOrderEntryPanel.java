package com.circa.mrv.grs_manager.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
	private JPanel pnlStudyDetails;
	/** Label for Study Details order entry the order creation date title */
	private JLabel lblOrderEntryDateTitle = new JLabel("Date: ");
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
	
	
	/** Label for Order Details product list capacity title */
	private JLabel lblProductListCapTitle = new JLabel("Product List Capacity: ");
	/** Label for Order Details remaining product list capacity title */
	private JLabel lblRemainingProductListCapTitle = new JLabel("Remaining List Capacity: ");
	/** Label for Order Details extra capacity title */
	private JLabel lblExtraCapacityTitle = new JLabel("Extra Capacity: ");
	
	
	/** Text Field for Order Details name */
	private JTextField txtFldDate = new JTextField(10);
	/** ComboBox for Order Details order entry research study number */
	private JComboBox cmbBoxStudyNumber = new JComboBox();
	/** ComboBox for Order Details order entry research site number */
	private JComboBox cmbBoxSiteNumber = new JComboBox();
	/** Text Field for Order Details order entry NAV sales order number */
	private JTextField txtFldNAVOrderNumber = new JTextField(10);
	/** Text Field for Order Details customer purchase order number */
	private JTextField txtFldPONumber = new JTextField(10);
	
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
	
	
	/** Label for Course Details delivery date */
	private JLabel lblDeliveryDate = new JLabel("6");
	/** Label for Order Details product list capacity */
	private JLabel lblProductListCapacity = new JLabel("7");
	/** Label for Order Details open product list capacity */
	private JLabel lblProductListOpenCapacity = new JLabel("8");
	/** Label for Order Details product list extra capacity */
	private JLabel lblProductlistExtraCapacity = new JLabel("9");
	
	
	/** Panel for displaying order details */
	/** ID used for object serialization */
	private static final long serialVersionUID = 1L;
	/** JTable for displaying the employee schedule of orders */
	private JTable tableEmployeeSchedule;
	/** Scroll pane for table */
	private JScrollPane scrollEmployeeSchedule;
	/** TableModel for employee schedule of Orders */
	private OpenOrderTableModel employeeScheduleTableModel;
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
	private OrderSchedule schedule;
	
	/**
	 * Constructs the ResearchCompanyOrderSchedulePanel and sets up the GUI 
	 * components.
	 */
	public ResearchCompanyOrderEntryPanel() {
		super(new GridBagLayout());
		
		//RegistrationManager manager = RegistrationManager.getInstance();
		currentUser = (Employee)GRSManager.getInstance().getCurrentUser();
		if (currentUser != null)
			schedule = currentUser.getSchedule();

//		Container con = getContentPane();
//		con.setLayout(new GridBagLayout());
//		setSize(800, 800);
//		studentDirectory = RegistrationManager.getInstance().getStudentDirectory();
		
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Directory Buttons");		

//		pnlButtons.setLayout(new GridLayout(1, 1));
//		btnLogout = new JButton("Logout");
//		btnLogout.addActionListener(this);
//		pnlButtons.add(btnLogout);
		
		//Set up Course Roll table
		productRollTableModel = new ProductRollTableModel();
		tableProductsRoll = new JTable(productRollTableModel);
		tableProductsRoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableProductsRoll.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableProductsRoll.setFillsViewportHeight(true);
		
		scrollProductRoll = new JScrollPane(tableProductsRoll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Product Catalog");
		scrollProductRoll.setBorder(boarder);
		
		//Set up Faculty Schedule table
		employeeScheduleTableModel = new OpenOrderTableModel();
		tableEmployeeSchedule = new JTable(employeeScheduleTableModel);
		tableEmployeeSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEmployeeSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableEmployeeSchedule.setFillsViewportHeight(true);
		
		scrollEmployeeSchedule = new JScrollPane(tableEmployeeSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Border lowerEtched2 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder border = BorderFactory.createTitledBorder(lowerEtched2, "Catalog Buttons");
		border = BorderFactory.createTitledBorder(lowerEtched2, "Open Orders");
		scrollEmployeeSchedule.setBorder(border);
		
		updateTables();
		
		GridBagConstraints c = new GridBagConstraints();
		
		/***************************SET UP OPEN ORDER SCHEDULE TABLE**************************
		 * 																					  *
		 * 																					  *
		 * 																					  *
		 * 																				      *
		 *************************************************************************************/
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollEmployeeSchedule, c);
		
		/*******************************SET UP STUDY PANEL*********************************
		 *																				  *
		 *																				  *
		 *																				  *
		 **********************************************************************************/
		pnlStudyDetails = new JPanel();
		pnlStudyDetails.setLayout(new GridLayout(4, 10));
		pnlStudyDetails.setSize(200,400);
		
		lblOrderEntryDateTitle.setSize(50,50);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.ipadx = 0;
		c.weightx = .5;
		pnlStudyDetails.add(lblOrderEntryDateTitle,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 8;
		c.ipadx = 5;
		c.weightx = 0.0;
		pnlStudyDetails.add(txtFldDate,c);
		
		lblStudyNumberTitle.setSize(50,100);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = .5;
		c.gridwidth = 2;
		pnlStudyDetails.add(lblStudyNumberTitle,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.ipadx = 5;
		c.gridwidth = 8;
		c.weightx = .5;
		pnlStudyDetails.add(cmbBoxStudyNumber,c);
		
		lblSiteNumberTitle.setSize(50,100);
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = .5;
		c.gridwidth = 2;
		pnlStudyDetails.add(lblSiteNumberTitle,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		c.weightx = .5;
		c.gridwidth = 8;
		pnlStudyDetails.add(cmbBoxSiteNumber,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlStudyDetails, c);
		
        
		
		
		/******************** ADD ORDER DETAILS PANEL ********************************
		 * 																			  *	
		 * 																			  *
		 * ***************************************************************************/
		pnlOrderDetails = new JPanel();
		pnlOrderDetails.setLayout(new GridLayout(3,4));
		pnlOrderDetails.setSize(200,200);
		lblNAVOrderNumberTitle.setSize(50,100);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.ipadx = 0;
		c.weightx = .5;
		pnlOrderDetails.add(lblNAVOrderNumberTitle,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 8;
		c.ipadx = 5;
		c.weightx = 0.0;
		pnlOrderDetails.add(txtFldNAVOrderNumber,c);
		
		lblCustomerPOTitle.setSize(50,100);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.ipadx = 0;
		c.weightx = .5;
		pnlOrderDetails.add(lblCustomerPOTitle,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 8;
		c.ipadx = 5;
		c.weightx = 0.0;
		pnlOrderDetails.add(txtFldPONumber,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlOrderDetails, c);
		
		
		/******************************CUSTOMER DETAILS PANEL***********************************
		 * 																						*
		 * 																					    *
		 * 																						*
		 ***************************************************************************************/
		
		pnlCustomerDetails = new JPanel();
		pnlCustomerDetails.setLayout(new GridLayout(3,4));
		pnlCustomerDetails.add(lblAddressTitle);
		pnlCustomerDetails.add(txtFldAddress);
		pnlCustomerDetails.add(lblAddress2Title);
		pnlCustomerDetails.add(txtFldAddress2);
		pnlCustomerDetails.add(lblCityTitle);
		pnlCustomerDetails.add(txtFldCity);
		pnlCustomerDetails.add(lblStateTitle);
		pnlCustomerDetails.add(txtFldState);
		pnlCustomerDetails.add(lblZipCodeTitle);
		pnlCustomerDetails.add(txtFldZipCode);
		
		
		
		
		
		
		TitledBorder borderCourseDetails = BorderFactory.createTitledBorder(lowerEtched, "Course Details");
		pnlStudyDetails.setBorder(borderCourseDetails);
		pnlStudyDetails.setToolTipText("Course Details");				
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlCustomerDetails, c);
		
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
		employeeScheduleTableModel.updateData();
		//initFacultySchedule();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		productRollTableModel.updateData();
		employeeScheduleTableModel.updateData();
		
		this.validate();
		this.repaint();
	}
	
	
//	private void initFacultySchedule(){
//		if (currentUser != null){
//			for (int i = 0 ; i < schedule.getNumScheduledCourses() ; i++)
//				facultyScheduleTableModel.setValueAt(schedule.getScheduledCourses()[i][i], i, i);
//		}
//	}
//	
	
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
			//fireTableCellUpdated(row, col);
		}
		
		/**
		 * Updates the given model with {@link Product} information from the {@link VendorDirectory}.
		 */
		public void updateData() {
			//data = currentUser.getSchedule().getScheduledCourses();
		}
	}

	/**
	 * {@link OpenOrderTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Orders to the user.
	 * @author Arthur Vargas
	 */
	private class OpenOrderTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Study", "Site", "PO Number", "Requested Delivery Date", "User ID"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link OpenOrderTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public OpenOrderTableModel() {
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
		 * Updates the given model with {@link Product} information from the {@link CustomerSchedule}.
		 */
		public void updateData() {
			if (currentUser != null){
				data = schedule.getScheduledOrders();
				//facultyScheduleTableModel.fireTableDataChanged();
				ResearchCompanyOrderEntryPanel.this.repaint();
				ResearchCompanyOrderEntryPanel.this.validate();
			}
		}	
	}
}