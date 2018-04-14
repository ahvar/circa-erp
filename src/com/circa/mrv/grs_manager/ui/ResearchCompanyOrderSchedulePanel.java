package com.circa.mrv.grs_manager.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
public class ResearchCompanyOrderSchedulePanel extends JPanel implements ActionListener {
	
	/** Panel for displaying Order Details */
	private JPanel pnlOrderDetails;
	/** Label for Order Details order entry employee name title */
	private JLabel lblOrderEntryEmployeeNameTitle = new JLabel("Name: ");
	/** Label for Order Details product part number title*/
	private JLabel lblProductPartNumberTitle = new JLabel("Part Number: ");
	/** Label for Order Details order number title*/
	private JLabel lblOrderNumber = new JLabel("Order Number: ");
	/** Label for Order Details order employee title title */
	private JLabel lblOrderEntryEmployeeTitleTitle = new JLabel("Employee Title: ");
	/** Label for Order Details price title */
	private JLabel lblPriceTitle = new JLabel("Price: ");
	/** Label for Order Details delivery date title */
	private JLabel lblDeliveryDateTitle = new JLabel("Delivery date: ");
	/** Label for Order Details product list capacity title */
	private JLabel lblProductListCapTitle = new JLabel("Product List Capacity: ");
	/** Label for Order Details remaining product list capacity title */
	private JLabel lblRemainingProductListCapTitle = new JLabel("Remaining List Capacity: ");
	/** Label for Order Details extra capacity title */
	private JLabel lblExtraCapacityTitle = new JLabel("Extra Capacity: ");
	
	
	/** Label for Order Details name */
	private JLabel lblName = new JLabel("1");
	/** Label for Order Details product part number */
	private JLabel lblProductPartNumber = new JLabel("2");
	/** Label for Order Details order entry employee title */
	private JLabel lblOrderEntryEmployeeTitle = new JLabel("3");
	/** Label for Order Details employee */
	private JLabel lblEmployee = new JLabel("4");
	/** Label for Order Details price */
	private JLabel lblPrice = new JLabel("5");
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
	private EmployeeScheduleTableModel employeeScheduleTableModel;
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
	public ResearchCompanyOrderSchedulePanel() {
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
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Course Roll");
		scrollProductRoll.setBorder(boarder);
		
		//Set up Faculty Schedule table
		employeeScheduleTableModel = new EmployeeScheduleTableModel();
		tableEmployeeSchedule = new JTable(employeeScheduleTableModel);
		tableEmployeeSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEmployeeSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableEmployeeSchedule.setFillsViewportHeight(true);
		
		scrollEmployeeSchedule = new JScrollPane(tableEmployeeSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Border lowerEtched2 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder border = BorderFactory.createTitledBorder(lowerEtched2, "Catalog Buttons");
		border = BorderFactory.createTitledBorder(lowerEtched2, "Faculty Schedule");
		scrollEmployeeSchedule.setBorder(border);
		
		updateTables();
		
		//Set up the course details panel
		pnlOrderDetails = new JPanel();
		pnlOrderDetails.setLayout(new GridLayout(9, 3));
		
		pnlOrderDetails.add(lblOrderEntryEmployeeNameTitle);
		pnlOrderDetails.add(lblName);

		pnlOrderDetails.add(lblProductPartNumberTitle);
		pnlOrderDetails.add(lblProductPartNumber);

		pnlOrderDetails.add(lblOrderNumber);
		pnlOrderDetails.add(lblOrderEntryEmployeeTitleTitle);

		pnlOrderDetails.add(lblOrderEntryEmployeeTitle);
		pnlOrderDetails.add(lblEmployee);

		pnlOrderDetails.add(lblPriceTitle);
		pnlOrderDetails.add(lblPrice);

		pnlOrderDetails.add(lblDeliveryDateTitle);		
		pnlOrderDetails.add(lblDeliveryDate);

		pnlOrderDetails.add(lblProductListCapTitle);
		pnlOrderDetails.add(lblProductListCapacity);

		pnlOrderDetails.add(lblRemainingProductListCapTitle);
		pnlOrderDetails.add(lblProductListOpenCapacity);

		pnlOrderDetails.add(lblExtraCapacityTitle);		
		pnlOrderDetails.add(lblProductlistExtraCapacity);
		
		TitledBorder borderCourseDetails = BorderFactory.createTitledBorder(lowerEtched, "Course Details");
		pnlOrderDetails.setBorder(borderCourseDetails);
		pnlOrderDetails.setToolTipText("Course Details");
		
		
		GridBagConstraints c = new GridBagConstraints();			
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridwidth = 1;
//		c.weightx = 1;
//		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		c.fill = GridBagConstraints.RELATIVE;
//		add(pnlButtons, c);		
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollEmployeeSchedule, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlOrderDetails, c);
		
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
	 * the list of Students to the user.
	 * @author Sarah Heckman
	 */
	private class ProductRollTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"First Name", "Last Name", "Student ID"};
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
	 * {@link EmployeeScheduleTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Orders to the user.
	 * @author Arthur Vargas
	 */
	private class EmployeeScheduleTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Name", "Section", "Title", "Meeting Information", "Open Seats"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link EmployeeScheduleTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public EmployeeScheduleTableModel() {
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
				ResearchCompanyOrderSchedulePanel.this.repaint();
				ResearchCompanyOrderSchedulePanel.this.validate();
			}
		}	
	}
}