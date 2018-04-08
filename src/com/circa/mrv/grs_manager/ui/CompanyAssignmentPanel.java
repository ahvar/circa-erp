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
import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.niox.Product;


/**
 * Creates a user interface for a administrator to assign/remove employees from the system.
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
	/** JTable for displaying the catalog of products */
	private JTable tableCatalog;
	/** JTable for displaying the directory of employees */
	private JTable tableEmployee;
	/** TableModel for catalog */
	private ProductCatalogTableModel catalogTableModel;
	/** TableModel for schedule */
	private EmployeeDirectoryTableModel employeeTableModel;
	/** Panel for displaying Product Details */
	private JPanel pnlProductDetails;
	/** Label for Company Details name title */
	private JLabel lblNameTitle = new JLabel("Customer: ");
	/** Label for Study Details section title */
	private JLabel lblStudyTitle = new JLabel("Study: ");
	/** Label for Site Details title title */
	private JLabel lblSiteTitle = new JLabel("Site: ");
	/** Label for Employee Details employee title */
	private JLabel lblEmployeeTitle = new JLabel("Employee: ");
	
	
	/** Label for ProductList Details credit hours title */
	private JLabel lblPriceTitle = new JLabel("Price: ");
	/** Label for Product delivery Date Details delivery title */
	private JLabel lblDeliveryDateTitle = new JLabel("Delivery Date: ");
	/** Label for Course Details enrollment cap title */
	private JLabel lblEnrollmentCapTitle = new JLabel("Enrollment Cap: ");
	/** Label for Course Details open seats title */
	private JLabel lblOpenSeatsTitle = new JLabel("Open Seats: ");
	/** Label for Product Details name */
	private JLabel lblName = new JLabel("");
	/** Label for Product Details part number */
	private JLabel lblPartNumber = new JLabel("");
	/** Label for Product Details description */
	private JLabel lblDescription = new JLabel("");
	/** Label for Product Details order entry employee */
	private JLabel lblOrderEntryEmployee = new JLabel("");
	/** Label for Product Details price */
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
	/** Label for Faculty Details max courses title*/
	private JLabel lblMaxCourseTitle = new JLabel("Max Courses: ");
	/** Label for Faculty Details overloaded title */
	private JLabel lblOverloadedTitle = new JLabel("Overloaded: ");
	/** Label for Faculty Details first name*/
	private JLabel lblFirstName = new JLabel("");
	/** Label for Faculty Details last name */
	private JLabel lblLastName = new JLabel("");
	/** Label for Faculty Details id */
	private JLabel lblId = new JLabel("");
	/** Label for Faculty Details email */
	private JLabel lblEmail = new JLabel("");
	/** Label for Faculty Details max courses */
	private JLabel lblMaxCourse = new JLabel("");
	/** Label for Faculty Details overloaded */
	private JLabel lblOverloaded = new JLabel("");
	/** Course catalog */
	private NioxCatalog catalog;
	/** Faculty directory */
	private CompanyDirectory companyDirectory;
	
	
	/**
	 * Creates the requirements list.
	 */
	public CompanyAssignmentPanel() {
		super(new GridBagLayout());
		
		GRSManager manager = GRSManager.getInstance();
		catalog = manager.getNioxCatalog();
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
		pnlActions.setToolTipText("Scheduler Actions");
					
		//Set up Catalog table
		catalogTableModel = new ProductCatalogTableModel();
		tableCatalog = new JTable(catalogTableModel) {
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
					return (String)catalogTableModel.getValueAt(rowIndex, realColumnIndex);
				} else {
					return "";
				}
			}
		};
		tableCatalog.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		tableCatalog.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableCatalog.setFillsViewportHeight(true);
		tableCatalog.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String name = tableCatalog.getValueAt(tableCatalog.getSelectedRow(), 0).toString();
				String partNumber = tableCatalog.getValueAt(tableCatalog.getSelectedRow(), 1).toString();
				Product c = catalog.getProductFromCatalog(name, partNumber);
				updateProductDetails(c);
			}
			
		});
		
		JScrollPane scrollCatalog = new JScrollPane(tableCatalog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		TitledBorder borderCatalog = BorderFactory.createTitledBorder(lowerEtched, "NIOX Catalog");
		scrollCatalog.setBorder(borderCatalog);
		scrollCatalog.setToolTipText("NIOX Catalog");
		
		//Set up Faculty Directory table
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
					return (String)catalogTableModel.getValueAt(rowIndex, realColumnIndex);
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
		pnlProductDetails = new JPanel();
		pnlProductDetails.setLayout(new GridLayout(5, 1));
		JPanel pnlName = new JPanel(new GridLayout(1, 4));
		pnlName.add(lblNameTitle);
		pnlName.add(lblName);
		pnlName.add(lblStudyTitle);
		pnlName.add(lblPartNumber);
		
		JPanel pnlTitle = new JPanel(new GridLayout(1, 1));
		pnlTitle.add(lblSiteTitle);
		pnlTitle.add(lblDescription);
		
		JPanel pnlEmployee = new JPanel(new GridLayout(1, 4));
		pnlEmployee.add(lblEmployeeTitle);
		pnlEmployee.add(lblOrderEntryEmployee);
		pnlEmployee.add(lblPriceTitle);
		pnlEmployee.add(lblPrice);
		
		JPanel pnlDelivery = new JPanel(new GridLayout(1, 1));
		pnlDelivery.add(lblDeliveryDateTitle);
		pnlDelivery.add(lblDelivery);
		
		JPanel pnlEnrollment = new JPanel(new GridLayout(1, 4));
		pnlEnrollment.add(lblEnrollmentCapTitle);
		pnlEnrollment.add(lblEnrollmentCap);
		pnlEnrollment.add(lblOpenSeatsTitle);
		pnlEnrollment.add(lblProductSpaces);
		
		pnlProductDetails.add(pnlName);
		pnlProductDetails.add(pnlTitle);
		pnlProductDetails.add(pnlEmployee);
		pnlProductDetails.add(pnlDelivery);
		pnlProductDetails.add(pnlEnrollment);
		
		TitledBorder borderProductDetails = BorderFactory.createTitledBorder(lowerEtched, "Product Details");
		pnlProductDetails.setBorder(borderProductDetails);
		pnlProductDetails.setToolTipText("Product Details");
		
		//Set up faculty details panel
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
		pnlOrderEntryEmployeeDetails.add(lblMaxCourseTitle);
		pnlOrderEntryEmployeeDetails.add(lblMaxCourse);
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
		add(pnlProductDetails, c);
		
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
			int catalogRow = tableCatalog.getSelectedRow();
			int employeeRow = tableEmployee.getSelectedRow();
			if (catalogRow == -1) {
				JOptionPane.showMessageDialog(this, "No product selected in the catalog.");
			} else if (employeeRow == -1) {
				JOptionPane.showMessageDialog(this, "No faculty selected in the directory.");
			} else {
				try {
					Product c = catalog.getProductFromCatalog(tableCatalog.getValueAt(catalogRow, 0).toString(), tableCatalog.getValueAt(catalogRow, 1).toString());
					Employee f = companyDirectory.getEmployeeById(tableEmployee.getValueAt(employeeRow, 2).toString());
					
					//if (!GRSManager.getInstance().addFacultyToCourse(c, f)) {
						//JOptionPane.showMessageDialog(this, "Course cannot be added to faculty's schedule.");
					//}
					updateProductDetails(c);
					updateEmployeeDetails(f);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(this, iae.getMessage());
				}
			}
			updateTables();
		} else if (e.getSource() == btnRemoveEmployeeFromCompany) {
			int catalogRow = tableCatalog.getSelectedRow();
			int facultyRow = tableEmployee.getSelectedRow();
			if (catalogRow == -1) {
				JOptionPane.showMessageDialog(this, "No course selected in the catalog.");
			} else if (facultyRow == -1) {
				JOptionPane.showMessageDialog(this, "No faculty selected in the directory.");
			} else {
				Product c = catalog.getProductFromCatalog(tableCatalog.getValueAt(catalogRow, 0).toString(), tableCatalog.getValueAt(catalogRow, 1).toString());
				Employee f = companyDirectory.getEmployeeById(tableEmployee.getValueAt(facultyRow, 2).toString());
				
				//if (!GRSManager.getInstance().removeFacultyFromCourse(c, f)) {
					//JOptionPane.showMessageDialog(this, "Course cannot be removed from faculty's schedule.");
				//}
				updateProductDetails(c);
				updateEmployeeDetails(f);
			}
			updateTables();
		} else if (e.getSource() == btnReset) {
			int facultyRow = tableEmployee.getSelectedRow();
			if (facultyRow == -1) {
				JOptionPane.showMessageDialog(this, "No faculty selected in the directory.");
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
		catalogTableModel.updateData();
		employeeTableModel.updateData();
	}
	
	/**
	 * Updates the pnlProductDetails with full information about the most
	 * recently added product.
	 * @param c most recently add product
	 */
	private void updateProductDetails(Product c) {
		if (c != null) {
			lblName.setText(c.getName());
			lblPartNumber.setText(c.getPartNumber());
			lblDescription.setText(c.getDescription());
			lblOrderEntryEmployee.setText(c.getName());
			lblPrice.setText("" + c.getPrice());
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
			lblMaxCourse.setText("" + f.getMaxOrders());
			lblOverloaded.setText("" + f.isOverloaded());
		}
	}
	
	/**
	 * {@link ProductCatalogTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Courses to the user.
	 * @author Arthur Vargas
	 */
	private class ProductCatalogTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Name", "Section", "Title", "Meeting Information", "Open Seats"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link ProductCatalogTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public ProductCatalogTableModel() {
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
			data = catalog.getNioxCatalog();
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
		private String [] columnNames = {"First Name", "Last Name", "Faculty ID"};
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
			data = companyDirectory.getCompanyDirectory();
		}
	}

}