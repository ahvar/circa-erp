package com.circa.mrv.grs_manager.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import com.circa.mrv.grs_manager.directory.CompanyDirectory;
import com.circa.mrv.grs_manager.directory.UserDirectory;
import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.directory.Company;
import com.circa.mrv.grs_manager.location.BillTo;

/**
 * Creates a user interface for the administrator to assign/remove employees from vendor and research companies.
 * 
 * @author Arthur Vargas
 */
public class CompanyAssignmentPanel  extends JPanel implements ActionListener {
	/** ID number used for object serialization. */
	private static final long serialVersionUID = 1L;
	
	/** Button for adding the selected employees to the selected company */
	private JButton btnAssignEmployeeToCompany;
	/** Button for removing the selected employees from the selected company */
	private JButton btnRemoveEmployeeFromCompany;
	/** Button for resetting the employee's schedule */
	private JButton btnReset;
	
	/** JTable for displaying the list of companies */
	private JTable tableCompany;
	/** JTable for displaying the directory of employees */
	private JTable tableEmployee;
	/** TableModel for companies */
	private CompanyDirectoryTableModel companyTableModel;
	/** TableModel for employees */
	private EmployeeDirectoryTableModel employeeTableModel;
	
	/** Panel for displaying Company Details */
	private JPanel pnlCompanyDetails;
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
	private JLabel lblName = new JLabel("");
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
	private JLabel lblPrice = new JLabel("");
	/** Label for Product Details delivery */
	private JLabel lblDelivery = new JLabel("");
	/** Label for Product Details enrollment cap */
	private JLabel lblEnrollmentCap = new JLabel("");
	/** Label for Product Details space on this product list */
	private JLabel lblProductSpaces = new JLabel("");
	
	
	/** Panel for displaying Order Entry Employee Details */
	private JPanel pnlOrderEntryEmployeeDetails;
	/** Label for Order Entry Employee Details first name title */
	private JLabel lblFirstNameTitle = new JLabel("First Name: ");
	/** Label for Order Entry Employee Details last name title */
	private JLabel lblLastNameTitle = new JLabel("Last Name: ");
	/** Label for Order Entry Employee Details id title */
	private JLabel lblIdTitle = new JLabel("Title: ");
	/** Label for Order Entry Employee Details email title */
	private JLabel lblEmailTitle = new JLabel("Email: ");
	/** Label for Order Entry Employee Details max orders title*/
	private JLabel lblMaxOrderTitle = new JLabel("Max Orders: ");
	/** Label for Order Entry Employee Details overloaded title */
	private JLabel lblOverloadedTitle = new JLabel("Overloaded: ");
	/** Label for Order Entry Employee Details first name*/
	private JLabel lblFirstName = new JLabel("");
	/** Label for Order Entry Employee Details last name */
	private JLabel lblLastName = new JLabel("");
	/** Label for Order Entry Employee Details id */
	private JLabel lblId = new JLabel("");
	/** Label for Order Entry Employee Details email */
	private JLabel lblEmail = new JLabel("");
	/** Label for Order Entry Employee Details max orders */
	private JLabel lblMaxOrders = new JLabel("");
	/** Label for Order Entry Employee Details overloaded */
	private JLabel lblOverloaded = new JLabel("");
	/** User/Employee directory*/
	private UserDirectory userDirectory;
	/** Company directory */
	private CompanyDirectory companyDirectory;
	
	
	/**
	 * Creates the requirements list.
	 */
	public CompanyAssignmentPanel() {
		super(new GridBagLayout());
		
		GRSManager manager = GRSManager.getInstance();
		userDirectory = manager.getUserDirectory();
		companyDirectory = manager.getCompanyDirectory();
		
		//Set up the JPanel that will hold action buttons		
		btnAssignEmployeeToCompany = new JButton("Assign Employee to Company");
		btnAssignEmployeeToCompany.addActionListener(this);
		btnRemoveEmployeeFromCompany = new JButton("Remove Employee from Company");
		btnRemoveEmployeeFromCompany.addActionListener(this);
		btnReset = new JButton("Reset Employee Schedule");
		btnReset.addActionListener(this);
		
		JPanel pnlActions = new JPanel();
		pnlActions.setLayout(new GridLayout(1, 3));
		pnlActions.add(btnAssignEmployeeToCompany);
		pnlActions.add(btnRemoveEmployeeFromCompany);
		pnlActions.add(btnReset);
		
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder borderActions = BorderFactory.createTitledBorder(lowerEtched, "Actions");
		pnlActions.setBorder(borderActions);
		pnlActions.setToolTipText("Assignment Actions");
					
		//Set up Company table
		companyTableModel = new CompanyDirectoryTableModel();
		tableCompany = new JTable(companyTableModel) {
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
					return (String)companyTableModel.getValueAt(rowIndex, realColumnIndex);
				} else {
					return "";
				}
			}
			
		};
		tableCompany.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		tableCatalog.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableCompany.setFillsViewportHeight(true);
		tableCompany.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String name = tableCompany.getValueAt(tableCompany.getSelectedRow(), 0).toString();
				String address1 = tableCompany.getValueAt(tableCompany.getSelectedRow(), 1).toString();
				String address2 = tableCompany.getValueAt(tableCompany.getSelectedRow(), 2).toString();
				String city = tableCompany.getValueAt(tableCompany.getSelectedRow(), 3).toString();
				String state = tableCompany.getValueAt(tableCompany.getSelectedRow(), 4).toString();
				String zip = tableCompany.getValueAt(tableCompany.getSelectedRow(), 5).toString();
				String country = tableCompany.getValueAt(tableCompany.getSelectedRow(), 6).toString();
				updateCompanyDetails(companyDirectory.getCompanyByNameAndStreet(name, address1));
			}
			
		});
		
		JScrollPane scrollCatalog = new JScrollPane(tableCompany, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		TitledBorder borderCatalog = BorderFactory.createTitledBorder(lowerEtched, "Company Directory");
		scrollCatalog.setBorder(borderCatalog);
		scrollCatalog.setToolTipText("Company Directory");
		
		//Set up Employee Directory table
		employeeTableModel = new EmployeeDirectoryTableModel();
		tableEmployee = new JTable(employeeTableModel) {
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
					return (String)companyTableModel.getValueAt(rowIndex, realColumnIndex);
				} else {
					return "";
				}
			}
		};
		tableEmployee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		tableFaculty.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableEmployee.setFillsViewportHeight(true);
		tableEmployee.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String id = tableEmployee.getValueAt(tableEmployee.getSelectedRow(), 2).toString();
				Employee f = companyDirectory.getEmployeeById(id);
				if(f == null) throw new IllegalArgumentException("Employee " + id + " not found.");
				updateEmployeeDetails(f);
			}
			
		});
		
		JScrollPane scrollEmployees = new JScrollPane(tableEmployee, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
		TitledBorder borderEmployee = BorderFactory.createTitledBorder(lowerEtched, "Employee Directory");
		scrollEmployees.setBorder(borderEmployee);
		scrollEmployees.setToolTipText("Employee Directory");
		
		
		updateTables();
		
		//Set up the course details panel
		pnlCompanyDetails = new JPanel();
		pnlCompanyDetails.setLayout(new GridLayout(5, 1));
		JPanel pnlCompanyNameAndAddress = new JPanel(new GridLayout(1, 4));
		pnlCompanyNameAndAddress.add(lblNameTitle);
		pnlCompanyNameAndAddress.add(lblName);
		pnlCompanyNameAndAddress.add(lblAddress1Title);
		pnlCompanyNameAndAddress.add(lblAddress1);
		
		JPanel pnlAdd2AndCity = new JPanel(new GridLayout(1, 6));
		pnlAdd2AndCity.add(lblAddress2Title);
		pnlAdd2AndCity.add(lblAddress2);
		pnlAdd2AndCity.add(lblCityTitle);
		pnlAdd2AndCity.add(lblCity);
		pnlAdd2AndCity.add(lblStateTitle);
		pnlAdd2AndCity.add(lblState);
		
		
		JPanel pnlEmployee = new JPanel(new GridLayout(1, 8));
		pnlEmployee.add(lblFirstNameTitle);
		pnlEmployee.add(lblFirstName);
		pnlEmployee.add(lblLastNameTitle);
		pnlEmployee.add(lblLastName);
		pnlEmployee.add(lblIdTitle);
        pnlEmployee.add(lblId);	
        pnlEmployee.add(lblEmailTitle);
        pnlEmployee.add(lblEmail);
        
		JPanel pnlDelivery = new JPanel(new GridLayout(1, 1));
		pnlDelivery.add(lblDeliveryDateTitle);
		pnlDelivery.add(lblDelivery);
		
		JPanel pnlEnrollment = new JPanel(new GridLayout(1, 4));
		pnlEnrollment.add(lblEnrollmentCapTitle);
		pnlEnrollment.add(lblEnrollmentCap);
		pnlEnrollment.add(lblOpenSeatsTitle);
		pnlEnrollment.add(lblProductSpaces);
		
		pnlCompanyDetails.add(pnlCompanyNameAndAddress);
		pnlCompanyDetails.add(pnlAdd2AndCity);
		pnlCompanyDetails.add(pnlEmployee);
		pnlCompanyDetails.add(pnlDelivery);
		pnlCompanyDetails.add(pnlEnrollment);
		
		TitledBorder borderProductDetails = BorderFactory.createTitledBorder(lowerEtched, "Product Details");
		pnlCompanyDetails.setBorder(borderProductDetails);
		pnlCompanyDetails.setToolTipText("Product Details");
		
		//Set up employee details panel
		pnlOrderEntryEmployeeDetails = new JPanel();
		pnlOrderEntryEmployeeDetails.setLayout(new GridLayout(6, 2));
		pnlOrderEntryEmployeeDetails.add(lblFirstNameTitle);
		pnlOrderEntryEmployeeDetails.add(lblFirstName);
		pnlOrderEntryEmployeeDetails.add(lblLastNameTitle);
		pnlOrderEntryEmployeeDetails.add(lblLastName);
		pnlOrderEntryEmployeeDetails.add(lblIdTitle);
		pnlOrderEntryEmployeeDetails.add(lblId);
		pnlOrderEntryEmployeeDetails.add(lblEmailTitle);
		pnlOrderEntryEmployeeDetails.add(lblEmail);
		pnlOrderEntryEmployeeDetails.add(lblMaxOrderTitle);
		pnlOrderEntryEmployeeDetails.add(lblMaxOrders);
		pnlOrderEntryEmployeeDetails.add(lblOverloadedTitle);
		pnlOrderEntryEmployeeDetails.add(lblOverloaded);
		
		TitledBorder borderEmployeeDetails = BorderFactory.createTitledBorder(lowerEtched, "Employee Details");
		pnlOrderEntryEmployeeDetails.setBorder(borderEmployeeDetails);
		pnlOrderEntryEmployeeDetails.setToolTipText("Employee Details");
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollCatalog, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollEmployees, c);
		
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
		add(pnlCompanyDetails, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlOrderEntryEmployeeDetails, c);
	}

	/**
	 * Performs an action based on the given {@link ActionEvent}.
	 * @param e user event that triggers an action.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAssignEmployeeToCompany) {
			int companyRow = tableCompany.getSelectedRow();
			int employeeRow = tableEmployee.getSelectedRow();
			if (companyRow == -1) {
				JOptionPane.showMessageDialog(this, "No company selected in the directory.");
			} else if (employeeRow == -1) {
				JOptionPane.showMessageDialog(this, "No employee selected in the directory.");
			} else {
				try {
					Company c = companyDirectory.getCompanyByNameAndStreet(tableCompany.getValueAt(companyRow, 0).toString(), tableCompany.getValueAt(companyRow, 1).toString());
					Employee f = companyDirectory.getEmployeeById(tableEmployee.getValueAt(employeeRow, 2).toString());
					
					if (!GRSManager.getInstance().addEmployeeToCompany(c, f, companyRow)) {
						JOptionPane.showMessageDialog(this, "Employee cannot be added to company's schedule.");
					}
					updateCompanyDetails(c);
					updateEmployeeDetails(f);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(this, iae.getMessage());
				}
			}
			updateTables();
		} else if (e.getSource() == btnRemoveEmployeeFromCompany) {
			int catalogRow = tableCompany.getSelectedRow();
			int facultyRow = tableEmployee.getSelectedRow();
			if (catalogRow == -1) {
				JOptionPane.showMessageDialog(this, "No company selected in the directory.");
			} else if (facultyRow == -1) {
				JOptionPane.showMessageDialog(this, "No employee selected in the directory.");
			} else {
				Company c = companyDirectory.getCompanyByNameAndStreet(tableCompany.getValueAt(catalogRow, 0).toString(), tableCompany.getValueAt(catalogRow, 1).toString());
				Employee f = companyDirectory.getEmployeeById(tableEmployee.getValueAt(facultyRow, 2).toString());
				
				if (!GRSManager.getInstance().removeEmployeeFromCompany(c, f)) {
					JOptionPane.showMessageDialog(this, "Employee cannot be removed from company's location.");
				}
				updateCompanyDetails(c);
				updateEmployeeDetails(f);
			}
			updateTables();
		} else if (e.getSource() == btnReset) {
			int facultyRow = tableEmployee.getSelectedRow();
			if (facultyRow == -1) {
				JOptionPane.showMessageDialog(this, "No employee selected in the directory.");
			} else {
				Employee f = companyDirectory.getEmployeeById(tableEmployee.getValueAt(facultyRow, 2).toString());
				GRSManager.getInstance().resetFacultySchedule(f);
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
		companyTableModel.updateData();
		employeeTableModel.updateData();
	}
	
	/**
	 * Updates the pnlCompanyDetails with full information about the most
	 * recently added Company
	 * @param c most recently add company
	 */
	private void updateCompanyDetails(Company c) {
		if (c != null ) {
			lblName.setText(c.getName());
			for(int i = 0; i < c.getLocations().size(); i++) {
				if(c.getLocations().get(i) instanceof BillTo) {
					lblAddress1.setText(c.getLocations().get(i).getAddress1());
					
					if(c.getLocations().get(i).getAddress2() != null && !c.getLocations().get(i).getAddress2().equals(""))
					    lblAddress2.setText(c.getLocations().get(i).getAddress2());
					
					lblCity.setText(c.getLocations().get(i).getCity());
					lblState.setText(c.getLocations().get(i).getState());
					lblZipCode.setText(c.getLocations().get(i).getZip());
					lblCountry.setText(c.getLocations().get(i).getCountry());
					lblPrice.setText("" + 0);
				}
			}
			//lblDelivery.setText(c.getMeetingString());
			//lblEnrollmentCap.setText("" + c.getCourseRoll().getCapacity());
			//lblProductSpaces.setText("" + c.getCourseRoll().getRemainingCapacity());
		}
	}
	
	/**
	 * Updates the pnlEmployeeDetails with full information about the most
	 * recently selected employee.
	 * @param f most recently selected employee
	 */
	private void updateEmployeeDetails(Employee f) {
		if (f != null) {
			lblFirstName.setText(f.getFirstName());
			lblLastName.setText(f.getLastName());
			lblId.setText(f.getId());
			lblEmail.setText(f.getEmail());
			lblMaxOrders.setText("" + f.getMaxOrders());
			lblOverloaded.setText("" + f.isOverloaded());
		}
	}
	
	/**
	 * {@link CompanyDirectoryTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Courses to the user.
	 * @author Arthur Vargas
	 */
	private class CompanyDirectoryTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Name", "Address", "Address", "City", "State", "Zip", "Country"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link CompanyDirectoryTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public CompanyDirectoryTableModel() {
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
			data = companyDirectory.getCompanyDirectory();
		}
	}
	
	/**
	 * {@link EmployeeDirectoryTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Employees to the system.
	 * @author Arthur Vargas
	 */
	private class EmployeeDirectoryTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"First Name", "Last Name", "Employee ID"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link EmployeeDirectoryTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public EmployeeDirectoryTableModel() {
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