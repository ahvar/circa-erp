package com.circa.mrv.grs_manager.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import com.circa.mrv.grs_manager.catalog.NioxCatalog;
import com.circa.mrv.grs_manager.catalog.OrderRecord;
import com.circa.mrv.grs_manager.directory.UserDirectory;
import com.circa.mrv.grs_manager.document.Order;
import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.user.Employee;


/**
 * Creates a user interface for the administrator to assign/remove employees from vendor and research companies.
 * 
 * @author Arthur Vargas
 */
public class OrderRecordsPanel  extends JPanel implements ActionListener {
	/** ID number used for object serialization. */
	private static final long serialVersionUID = 1L;
	
	/** Button for loading order records */
	private JButton btnLoadOrderRecords;
	/** Button for loading user records */
	private JButton btnLoadUserRecords;
	/** Button clear order records */
	private JButton btnClearOrderRecords;
	
	/** JTable for displaying the list of orders in the system */
	private JTable tableOrder;
	/** JTable for displaying the directory of system users */
	private JTable tableUser;
	/** TableModel for orders */
	private OrderDirectoryTableModel orderTableModel;
	/** TableModel for users */
	//private UserDirectoryTableModel userTableModel;
	
	/** Panel for displaying User Details */
	private JPanel pnlUserDetails;
	/** Label for Company Details name title */
	private JLabel lblNameTitle = new JLabel("Company: ");
	/** Label for Company Details address title */
	private JLabel lblAddress1Title = new JLabel("Address: ");
	/** Label for Company Details second address title */
	private JLabel lblAddress2Title = new JLabel("Address: ");
	/** Label for Company Details city title */
	private JLabel lblCityTitle = new JLabel("City: ");
	/** Label for Company Details state title */
	private JLabel lblStateTitle = new JLabel("State: ");
	/** Label for Company Details zip code title */
	private JLabel lblZipCodeTitle = new JLabel("Zip: ");
	/** Lable for Company Details country title */
	private JLabel lblCountryTitle = new JLabel("Country: ");
	/** Label for Employee Details employee title */
	//private JLabel lblCityTitle = new JLabel("Employee: ");
	
	
	/** Label for ProductList Details price title */
	private JLabel lblPriceTitle = new JLabel("Price: ");
	/** Label for Product delivery Date Details delivery title */
	private JLabel lblDeliveryDateTitle = new JLabel("Delivery Date: ");
	/** Label for Product Details enrollment cap title */
	private JLabel lblEnrollmentCapTitle = new JLabel("Enrollment Cap: ");
	/** Label for Product Details open seats title */
	private JLabel lblOpenSeatsTitle = new JLabel("Open Seats: ");
	
	/** Label for Company Details name */
	private JLabel lblPONumber = new JLabel("");
	/** Label for Company Details address */
	private JLabel lblAddress1 = new JLabel("");
	/** Label for Company Details address */
	private JLabel lblAddress2 = new JLabel("");
	/** Label for Company Details city */
	private JLabel lblCity = new JLabel("");
	/** Label for Company Details state */
	private JLabel lblState = new JLabel("");
	/** Label for Company Details zip */
	private JLabel lblZipCode = new JLabel("");
	/** Label for Company Details country */
	private JLabel lblCountry = new JLabel("");
	
	
	/** Label for Company Details price */
	private JLabel lblTotal = new JLabel("");
	/** Label for Product Details delivery */
	private JLabel lblDelivery = new JLabel("");
	/** Label for Product Details enrollment cap */
	private JLabel lblEnrollmentCap = new JLabel("");
	/** Label for Product Details space on this product list */
	private JLabel lblProductSpaces = new JLabel("");
	
	
	/** Panel for displaying Order Entry Details */
	private JPanel pnlOrderDetails;
	/** Label for Order Entry Details study number title */
	private JLabel lblStudyTitle = new JLabel("Study: ");
	/** Label for Order Entry Details site number title */
	private JLabel lblSiteTitle = new JLabel("Site: ");
	/** Label for Order Entry Details Circassia sales order number title */
	private JLabel lblCirSalesOrderNumberTitle = new JLabel("Circassia Sales Order Number: ");
	/** Label for Order Entry Details customer PO title */
	private JLabel lblCustPOTitle = new JLabel("Customer PO: ");
	/** Label for Order Entry Details user id for order author title*/
	private JLabel lblUserIDTitle = new JLabel("Created By: ");
	/** Label for Order Entry Details created date title */
	private JLabel lblOrderEntryDateTitle = new JLabel("Created On: ");
	/** Label for Order Entry Details study*/
	private JLabel lblStudy = new JLabel("");
	/** Label for Order Entry Details site */
	private JLabel lblSite = new JLabel("");
	/** Label for Order Entry Details sales order number  */
	private JLabel lblCirSalesOrderNumber = new JLabel("");
	/** Label for Order Entry Details customer purchase order number */
	private JLabel lblCustPO = new JLabel("");
	/** Label for Order Entry Details user id */
	private JLabel lblUserID = new JLabel("");
	/** Label for Order Entry Employee Details overloaded */
	private JLabel lblOrderEntryDate = new JLabel("");
	/** User/Employee directory*/
	private UserDirectory userDirectory;
	/** Order Records */
	private OrderRecord orderRecord;
	
	
	/**
	 * Creates the requirements list.
	 */
	public OrderRecordsPanel() {
		super(new GridBagLayout());
		
		GRSManager manager = GRSManager.getInstance();
		userDirectory = manager.getUserDirectory();
		orderRecord = manager.getOrderRecord();
		
		//Set up the JPanel that will hold action buttons		
		btnLoadOrderRecords = new JButton("Load Order Records");
		btnLoadOrderRecords.addActionListener(this);
		btnLoadUserRecords = new JButton("Load User Records");
		btnLoadUserRecords.addActionListener(this);
		btnClearOrderRecords = new JButton("Clear Order Records");
		btnClearOrderRecords.addActionListener(this);
		
		JPanel pnlActions = new JPanel();
		pnlActions.setLayout(new GridLayout(1, 3));
		pnlActions.add(btnLoadOrderRecords);
		pnlActions.add(btnLoadUserRecords);
		pnlActions.add(btnClearOrderRecords);
		
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder borderActions = BorderFactory.createTitledBorder(lowerEtched, "Actions");
		pnlActions.setBorder(borderActions);
		pnlActions.setToolTipText("Actions");
					
		//Set up Company table
		orderTableModel = new OrderDirectoryTableModel();
		tableOrder = new JTable(orderTableModel) {
			private static final long serialVersionUID = 1L;
			
			/**
			 * Set custom tool tips for cells
			 * @param e MouseEvent that causes the tool tip
			 * @return tool tip text
			 */
			public String getToolTipText(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);
				int realColumnIndex = convertColumnIndexToModel(colIndex);
				
				if (rowIndex != -1 && realColumnIndex != -1) {
					return (String)orderTableModel.getValueAt(rowIndex, realColumnIndex);
				} else {
					return "";
				}
			}
			
		};
		tableOrder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		tableCatalog.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableOrder.setFillsViewportHeight(true);
		tableOrder.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String po = tableOrder.getValueAt(tableOrder.getSelectedRow(), 0).toString();
				String study = tableOrder.getValueAt(tableOrder.getSelectedRow(), 1).toString();
				//String site = tableOrder.getValueAt(tableOrder.getSelectedRow(), 2).toString();
				//String city = tableOrder.getValueAt(tableOrder.getSelectedRow(), 3).toString();
				//String state = tableOrder.getValueAt(tableOrder.getSelectedRow(), 4).toString();
				//String zip = tableOrder.getValueAt(tableOrder.getSelectedRow(), 5).toString();
				//String country = tableOrder.getValueAt(tableOrder.getSelectedRow(), 6).toString();
				updateOrderDetails(orderRecord.getOrderByPOAndStudy(po, study));
			}
			
		});
		
		JScrollPane scrollOrderRecords = new JScrollPane(tableOrder, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		TitledBorder borderCatalog = BorderFactory.createTitledBorder(lowerEtched, "All Orders");
		scrollOrderRecords.setBorder(borderCatalog);
		scrollOrderRecords.setToolTipText("All Orders");
		
		
		//userTableModel = new UserDirectoryTableModel();
		//tableUser = new JTable(userTableModel) {
			//private static final long serialVersionUID = 1L;
			
			/**
			 * Set custom tool tips for cells
			 * @param e MouseEvent that causes the tool tip
			 * 
			 * @return tool tip text
			 */
			//public String getToolTipText(MouseEvent e) {
				//java.awt.Point p = e.getPoint();
				//int rowIndex = rowAtPoint(p);
				//int colIndex = columnAtPoint(p);
				//int realColumnIndex = convertColumnIndexToModel(colIndex);
				
				//if (rowIndex != -1 && realColumnIndex != -1) {
					//return (String)orderTableModel.getValueAt(rowIndex, realColumnIndex);
				//} else {
					//return "";
				//}
			//}
		//};
		//tableUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		tableFaculty.setPreferredScrollableViewportSize(new Dimension(500, 500));
		//tableUser.setFillsViewportHeight(true);
		//tableUser.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			//@Override
			/*public void valueChanged(ListSelectionEvent e) {
				String id = tableUser.getValueAt(tableUser.getSelectedRow(), 2).toString();
				Order f = orderRecord.getOrderyId(id);
				if(f == null) throw new IllegalArgumentException("User " + id + " not found.");
				updateOrderDetails(f);
			}
			
		});*/
		
		//JScrollPane scrollUsers = new JScrollPane(tableUser, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
		//TitledBorder borderEmployee = BorderFactory.createTitledBorder(lowerEtched, "User Directory");
		//scrollUsers.setBorder(borderEmployee);
		//scrollUsers.setToolTipText("User Directory");
		
		
		updateTables();
		
		//Set up the user details panel
		pnlUserDetails = new JPanel();
		pnlUserDetails.setLayout(new GridLayout(5, 1));
		JPanel pnlUserNameAndAddress = new JPanel(new GridLayout(1, 4));
		pnlUserNameAndAddress.add(lblNameTitle);
		pnlUserNameAndAddress.add(lblPONumber);
		pnlUserNameAndAddress.add(lblAddress1Title);
		pnlUserNameAndAddress.add(lblAddress1);
		
		JPanel pnlAdd2AndCity = new JPanel(new GridLayout(1, 6));
		pnlAdd2AndCity.add(lblAddress2Title);
		pnlAdd2AndCity.add(lblAddress2);
		pnlAdd2AndCity.add(lblCityTitle);
		pnlAdd2AndCity.add(lblCity);
		pnlAdd2AndCity.add(lblStateTitle);
		pnlAdd2AndCity.add(lblState);
		
		
		JPanel pnlOrder = new JPanel(new GridLayout(1, 8));
		pnlOrder.add(lblStudyTitle);
		pnlOrder.add(lblStudy);
		pnlOrder.add(lblSiteTitle);
		pnlOrder.add(lblSite);
		pnlOrder.add(lblCirSalesOrderNumberTitle);
        pnlOrder.add(lblCirSalesOrderNumber);	
        pnlOrder.add(lblCustPOTitle);
        pnlOrder.add(lblCustPO);
        
		JPanel pnlDelivery = new JPanel(new GridLayout(1, 1));
		pnlDelivery.add(lblDeliveryDateTitle);
		pnlDelivery.add(lblDelivery);
		
		JPanel pnlEnrollment = new JPanel(new GridLayout(1, 4));
		pnlEnrollment.add(lblEnrollmentCapTitle);
		pnlEnrollment.add(lblEnrollmentCap);
		pnlEnrollment.add(lblOpenSeatsTitle);
		pnlEnrollment.add(lblProductSpaces);
		
		pnlUserDetails.add(pnlUserNameAndAddress);
		pnlUserDetails.add(pnlAdd2AndCity);
		pnlUserDetails.add(pnlOrder);
		pnlUserDetails.add(pnlDelivery);
		pnlUserDetails.add(pnlEnrollment);
		
		TitledBorder borderProductDetails = BorderFactory.createTitledBorder(lowerEtched, "Order Details");
		pnlUserDetails.setBorder(borderProductDetails);
		pnlUserDetails.setToolTipText("Order Details");
		
		//Set up order details panel
		pnlOrderDetails = new JPanel();
		pnlOrderDetails.setLayout(new GridLayout(6, 2));
		pnlOrderDetails.add(lblStudyTitle);
		pnlOrderDetails.add(lblStudy);
		pnlOrderDetails.add(lblSiteTitle);
		pnlOrderDetails.add(lblSite);
		pnlOrderDetails.add(lblCirSalesOrderNumberTitle);
		pnlOrderDetails.add(lblCirSalesOrderNumber);
		pnlOrderDetails.add(lblCustPOTitle);
		pnlOrderDetails.add(lblCustPO);
		pnlOrderDetails.add(lblUserIDTitle);
		pnlOrderDetails.add(lblUserID);
		pnlOrderDetails.add(lblOrderEntryDateTitle);
		pnlOrderDetails.add(lblOrderEntryDate);
		
		TitledBorder borderEmployeeDetails = BorderFactory.createTitledBorder(lowerEtched, "User Details");
		pnlOrderDetails.setBorder(borderEmployeeDetails);
		pnlOrderDetails.setToolTipText("User Details");
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollOrderRecords, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		//add(scrollUsers, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlActions, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlUserDetails, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlOrderDetails, c);
	}

	/**
	 * Performs an action based on the given {@link ActionEvent}.
	 * @param e user event that triggers an action.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLoadOrderRecords) {
			String titles = getTitlesFileName(true);
			String orders = getOrdersFileName(true);
			try {
				GRSManager.getInstance().getOrderRecord().loadTitlesFromFile(titles);
				GRSManager.getInstance().getOrderRecord().loadOrdersFromFile(orders);
				orderTableModel.updateData();
				//tableOrder.fireTableDataChange();
			}catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this,iae.getMessage());
			}
			int orderRow = tableOrder.getSelectedRow();
			//int employeeRow = tableUser.getSelectedRow();
			//if (companyRow == -1) {
				//JOptionPane.showMessageDialog(this, "No company selected in the directory.");
			//} else if (employeeRow == -1) {
				//JOptionPane.showMessageDialog(this, "No employee selected in the directory.");
			//} else {
				/*try {
					Order c = orderRecord.getOrderByNameAndStreet(tableOrder.getValueAt(companyRow, 0).toString(), tableOrder.getValueAt(companyRow, 1).toString());
					Employee f = orderRecord.getEmployeeById(tableUser.getValueAt(employeeRow, 2).toString());
					
					if (!GRSManager.getInstance().addEmployeeToCompany(c, f, companyRow)) {
						JOptionPane.showMessageDialog(this, "Employee cannot be added to company's schedule.");
					}
					updateCompanyDetails(c);
					updateOrderDetails(f);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(this, iae.getMessage());
				}
			}
			updateTables();
		} else if (e.getSource() == btnLoadUserRecords) {
			int catalogRow = tableOrder.getSelectedRow();
			int facultyRow = tableUser.getSelectedRow();
			if (catalogRow == -1) {
				JOptionPane.showMessageDialog(this, "No company selected in the directory.");
			} else if (facultyRow == -1) {
				JOptionPane.showMessageDialog(this, "No employee selected in the directory.");
			} else {
				Order c = orderRecord.getRecordByNameAndStreet(tableOrder.getValueAt(catalogRow, 0).toString(), tableOrder.getValueAt(catalogRow, 1).toString());
				Employee f = orderRecord.getEmployeeById(tableUser.getValueAt(facultyRow, 2).toString());
				
				if (!GRSManager.getInstance().removeEmployeeFromCompany(c, f)) {
					JOptionPane.showMessageDialog(this, "Employee cannot be removed from company's location.");
				}
				updateCompanyDetails(c);
				updateOrderDetails(f);
			}
			updateTables();*/
		} else if (e.getSource() == btnClearOrderRecords) {
			int orderRow = tableOrder.getSelectedRow();
			if (orderRow == -1) {
				JOptionPane.showMessageDialog(this, "No order selected in the directory.");
			} else {
				orderRecord.newOrderRecord();
				
				//Order f = orderRecord.getOrderById(tableOrder.getValueAt(orderRow, 2).toString());
				//GRSManager.getInstance().resetFacultySchedule(f);
				updateTables();
			}
		} 
		
		this.repaint();
		this.validate();
	}
	
	/**
	 * Updates the catalog and schedule tables.
	 */
	public void updateTables() {
		orderTableModel.updateData();
		//userTableModel.updateData();
	}
	
	/**
	 * Updates the pnlOrderDetails with full information about the most
	 * recently added Order
	 * @param o most recently added Order
	 */
	private void updateOrderDetails(Order o) {
		if (o != null ) {
			lblPONumber.setText(o.getPo());
			lblStudy.setText(o.getStudy());
			lblSite.setText(o.getSite());
			lblCity.setText(o.getCity());
			lblState.setText(o.getState());
			lblZipCode.setText(o.getZip());
			lblCountry.setText(o.getCountry());
			lblTotal.setText(Double.toString(o.getAmount()));

			//lblDelivery.setText(c.getMeetingString());
			//lblEnrollmentCap.setText("" + c.getCourseRoll().getCapacity());
			//lblProductSpaces.setText("" + c.getCourseRoll().getRemainingCapacity());
		}
	}
	
	/**
	 * Returns a file name generated through interactions with a {@link JFileChooser}
	 * object.
	 * @param chooserType true if open, false if save
	 * @return the file name selected through {@link JFileChooser}
	 */
	private String getTitlesFileName(boolean chooserType) {
		JFileChooser fc = new JFileChooser("./");  //Open JFileChoose to current working directory
		fc.setApproveButtonText("Select");
		int returnVal = Integer.MIN_VALUE;
		if (chooserType) {
			fc.setDialogTitle("Select Order Record Titles");
			returnVal = fc.showOpenDialog(this);
		} else {
			fc.setDialogTitle("Save Order Record Titles");
			returnVal = fc.showSaveDialog(this);
		}
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			//Error or user canceled, either way no file name.
			throw new IllegalStateException();
		}
		File catalogFile = fc.getSelectedFile();
		return catalogFile.getAbsolutePath();
	}
	
	/**
	 * Returns a file name generated through interactions with a {@link JFileChooser}
	 * object.
	 * @param chooserType true if open, false if save
	 * @return the file name selected through {@link JFileChooser}
	 */
	private String getOrdersFileName(boolean chooserType) {
		JFileChooser fc = new JFileChooser("./");  //Open JFileChoose to current working directory
		fc.setApproveButtonText("Select");
		int returnVal = Integer.MIN_VALUE;
		if (chooserType) {
			fc.setDialogTitle("Select Order Records");
			returnVal = fc.showOpenDialog(this);
		} else {
			fc.setDialogTitle("Save Order Records");
			returnVal = fc.showSaveDialog(this);
		}
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			//Error or user canceled, either way no file name.
			throw new IllegalStateException();
		}
		File catalogFile = fc.getSelectedFile();
		return catalogFile.getAbsolutePath();
	}
	
	/**
	 * {@link OrderDirectoryTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Courses to the user.
	 * @author Arthur Vargas
	 */
	private class OrderDirectoryTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Study", "Site", "City", "Country", "PO Number", "Note", "Email", "Date", "State","Phone","Fax"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link OrderDirectoryTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public OrderDirectoryTableModel() {
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
		 * Updates the given model with {@link Mino} information from the {@link NioxCatalog}.
		 */
		public void updateData() {
			try {
				data = GRSManager.getInstance().getOrderRecord().getShortOrderInfo();
			} catch(NullPointerException e) {
				data = GRSManager.getInstance().getOrderRecord().getRecord();
				System.out.println(e.getMessage());
			} catch(IOException e) {
				data = GRSManager.getInstance().getOrderRecord().getRecord();
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * {@link UserDirectoryTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Employees to the system.
	 * @author Arthur Vargas
	 */
	private class UserDirectoryTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"First Name", "Last Name", "User ID"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link UserDirectoryTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public UserDirectoryTableModel() {
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
		 * Updates the given model with {@link Employee} information from the {@link CustomerDirectory}.
		 */
		public void updateData() {
			data = userDirectory.getEmployeeDirectory();
		}
	}

}