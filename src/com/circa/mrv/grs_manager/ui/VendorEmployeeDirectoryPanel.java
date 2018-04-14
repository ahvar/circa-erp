package com.circa.mrv.grs_manager.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.directory.Company;
import com.circa.mrv.grs_manager.location.BillTo;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.directory.CompanyDirectory;
import com.circa.mrv.grs_manager.directory.UserDirectory;

/**
 * Creates a user interface for working with the VendorCompanyEmployeeDirectory.
 * 
 * @author Arthur Vargas
 */
public class VendorEmployeeDirectoryPanel extends JPanel implements ActionListener {
	
	/** ID used for object serialization */
	private static final long serialVersionUID = 1L;

	/** Button for resetting the directory */
	private JButton btnNewEmployeeList;
	/** Button for loading a new directory */
	private JButton btnLoadEmployeeList;
	/** Button for displaying the final directory */
	private JButton btnSaveEmployeeList;
	/** JTable for displaying the directory of Employees */
	private JTable tableEmployeeDirectory;
	/** Scroll pane for table */
	private JScrollPane scrollEmployeeDirectory;
	/** TableModel for directory of Employees */
	private EmployeeDirectoryTableModel employeeDirectoryTableModel;
	/** JLabel for firstName */
	private JLabel lblFirstName;
	/** JLabel for lastName */
	private JLabel lblLastName;
	/** JLabel for id */
	private JLabel lblId;
	/** JLabel for email */
	private JLabel lblEmail;
	/** JLabel for password */
	private JLabel lblPassword;
	/** JLabel for repeat password */
	private JLabel lblRepeatPassword;
	
	/** JLabel for maxCredits */
	private JLabel lblMaxCredits;
	
	/** JTextField for firstName */
	private JTextField txtFirstName;
	/** JTextField for lastName */
	private JTextField txtLastName;
	/** JTextField for id */
	private JTextField txtId;
	/** JTextField for email */
	private JTextField txtEmail;
	/** JPasswordField for password */
	private JPasswordField txtPassword;
	/** JPasswordField for repeat password */
	private JPasswordField txtRepeatPassword;
	
	/** JTextField for maxCredits */
	private JTextField txtMaxCredits;
	
	/** Button for adding the selected employee in the catalog to the schedule */
	private JButton btnAddEmployee;
	/** Button for removing the selected employee from the schedule */
	private JButton btnRemoveEmployee;
	/** Reference to EmployeeDirectory */
	private UserDirectory userDirectory;
	/** Reference to CompanyDirectory */
	private CompanyDirectory companyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

	
	/**
	 * Constructs the EmployeeDirectoryGUI and sets up the GUI 
	 * components.
	 */
	public VendorEmployeeDirectoryPanel() {
		super(new GridBagLayout());
		
		userDirectory = GRSManager.getInstance().getUserDirectory();
		companyDirectory = GRSManager.getInstance().getCompanyDirectory();
		
		//Set up Directory buttons
		btnNewEmployeeList = new JButton("New Employee Directory");
		btnNewEmployeeList.addActionListener(this);
		btnLoadEmployeeList = new JButton("Load Employee Directory");
		btnLoadEmployeeList.addActionListener(this);
		btnSaveEmployeeList = new JButton("Save Employee Directory");
		btnSaveEmployeeList.addActionListener(this);
		
		JPanel pnlDirectoryButton = new JPanel();
		pnlDirectoryButton.setLayout(new GridLayout(1, 3));
		pnlDirectoryButton.add(btnNewEmployeeList);
		pnlDirectoryButton.add(btnLoadEmployeeList);
		pnlDirectoryButton.add(btnSaveEmployeeList);
		
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Directory Buttons");
		pnlDirectoryButton.setBorder(boarder);
		pnlDirectoryButton.setToolTipText("Directory Buttons");
		
		//Set up Directory table
		employeeDirectoryTableModel = new EmployeeDirectoryTableModel();
		tableEmployeeDirectory = new JTable(employeeDirectoryTableModel);
		tableEmployeeDirectory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEmployeeDirectory.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableEmployeeDirectory.setFillsViewportHeight(true);
		
		scrollEmployeeDirectory = new JScrollPane(tableEmployeeDirectory, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Employee Directory");
		scrollEmployeeDirectory.setBorder(boarder);
		scrollEmployeeDirectory.setToolTipText("Employee Directory");
		
		//Set up Student buttons
		btnAddEmployee = new JButton("Add Employee");
		btnAddEmployee.addActionListener(this);
		btnRemoveEmployee = new JButton("Remove Employee");
		btnRemoveEmployee.addActionListener(this);
		
		JPanel pnlEmployeeButtons = new JPanel();
		pnlEmployeeButtons.setLayout(new GridLayout(1, 2));
		pnlEmployeeButtons.add(btnAddEmployee);
		pnlEmployeeButtons.add(btnRemoveEmployee);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Employee Controls");
		pnlEmployeeButtons.setBorder(boarder);
		pnlEmployeeButtons.setToolTipText("EmployeeControls");
		
		//Set up Student form
		lblFirstName = new JLabel("First Name");
		lblLastName = new JLabel("Last Name");
		lblId = new JLabel("ID");
		lblEmail = new JLabel("Email");
		lblPassword = new JLabel("Password");
		lblRepeatPassword = new JLabel("Repeat Password");
		lblMaxCredits = new JLabel("Max Products");
		txtFirstName = new JTextField(20);
		txtLastName = new JTextField(20);
		txtId = new JTextField(20);
		txtEmail = new JTextField(20);
		txtPassword = new JPasswordField(20);
		txtRepeatPassword = new JPasswordField(20);
		txtMaxCredits = new JTextField(20);
		
		JPanel pnlEmployeeForm = new JPanel();
		pnlEmployeeForm.setLayout(new GridLayout(7, 2));
		pnlEmployeeForm.add(lblFirstName);
		pnlEmployeeForm.add(txtFirstName);
		pnlEmployeeForm.add(lblLastName);
		pnlEmployeeForm.add(txtLastName);
		pnlEmployeeForm.add(lblId);
		pnlEmployeeForm.add(txtId);
		pnlEmployeeForm.add(lblEmail);
		pnlEmployeeForm.add(txtEmail);
		pnlEmployeeForm.add(lblPassword);
		pnlEmployeeForm.add(txtPassword);
		pnlEmployeeForm.add(lblRepeatPassword);
		pnlEmployeeForm.add(txtRepeatPassword);
		pnlEmployeeForm.add(lblMaxCredits);
		pnlEmployeeForm.add(txtMaxCredits);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Employee Information");
		pnlEmployeeForm.setBorder(boarder);
		pnlEmployeeForm.setToolTipText("Employee Information");
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = .2;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(pnlDirectoryButton, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(scrollEmployeeDirectory, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = .5;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(pnlEmployeeButtons, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(pnlEmployeeForm, c);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLoadEmployeeList) {
			String fileName = getFileName(true);
			try {
				userDirectory.loadUsersFromFile(fileName);
				//this will create problem if user loads multiple employee directory files. 
				companyDirectory.addCompany("Circassia Pharmaceuticals", "5151 McCrimmon Parkway","Suite 260","Morrisville","NC","27560","USA");
				employeeDirectoryTableModel.updateData();
				scrollEmployeeDirectory.revalidate();
				scrollEmployeeDirectory.repaint();
				employeeDirectoryTableModel.fireTableDataChanged();
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, iae.getMessage());
			}
		} else if (e.getSource() == btnSaveEmployeeList) {
			String fileName = getFileName(false);
			try {
				userDirectory.saveUserDirectory(fileName);
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, iae.getMessage());
			}
		} else if (e.getSource() == btnNewEmployeeList) {
			userDirectory.newUserDirectory();
			employeeDirectoryTableModel.updateData();
			scrollEmployeeDirectory.revalidate();
			scrollEmployeeDirectory.repaint();
			employeeDirectoryTableModel.fireTableDataChanged();
		} else if (e.getSource() == btnAddEmployee) {
			String firstName = txtFirstName.getText();
			String lastName = txtLastName.getText();
			String id = txtId.getText();
			String email = txtEmail.getText();
			char[] password = txtPassword.getPassword();
			char[] repeatPassword = txtRepeatPassword.getPassword();
			int maxCredits = 0;
			try {
				maxCredits = Integer.parseInt(txtMaxCredits.getText());
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, "Price must be a positive number between 3 and 18.");
				return;
			}
			
			String pwString = "";
			for (int i = 0; i < password.length; i++) {
				pwString += password[i];
			}
			
			String repeatPWString = "";
			for (int i = 0; i < repeatPassword.length; i++) {
				repeatPWString += repeatPassword[i];
			}
			
			try {
				if (userDirectory.addUser(firstName, lastName, id, email, pwString, repeatPWString)) {
					
					txtFirstName.setText("");
					txtLastName.setText("");
					txtId.setText("");
					txtEmail.setText("");
					txtPassword.setText("");
					txtRepeatPassword.setText("");
					txtMaxCredits.setText("");
					
					if (companyDirectory.getCompanyList().size() == 0) { 
						companyDirectory.addCompany("Circassia Pharmaceuticals","5151 McCrimmon Parkway","Suite 260","Morrisville","NC","27560","USA");
					} else {
					  Company c = null;
					  for(int i = 0; i < companyDirectory.getCompanyList().size(); i++) {
					      if(companyDirectory.getCompanyList().get(i).getName().equals("Circassia Pharmaceuticals"))
					  		  c = companyDirectory.getCompanyList().get(i);
					  }
					  if (c == null) companyDirectory.addCompany("Circassia Pharmaceuticals","5151 McCrimmon Parkway","Suite 260","Morrisville","NC","27560","USA");
					}
					
					Employee emp;
					if((emp = companyDirectory.getEmployeeById(id)) == null) {
						
						String hashPW = "";
						String repeatHashPW = "";
						
						if (pwString == null || repeatPWString == null || pwString.equals("") || repeatPWString.equals("")) {
							throw new IllegalArgumentException("Invalid password");
						}
						try {
							MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
							digest1.update(pwString.getBytes());
							hashPW = new String(digest1.digest());
							
							MessageDigest digest2 = MessageDigest.getInstance(HASH_ALGORITHM);
							digest2.update(repeatPWString.getBytes());
							repeatHashPW = new String(digest2.digest());
							
						} catch (NoSuchAlgorithmException ex) {
							throw new IllegalArgumentException("Cannot hash password");
						}
						
						if (!hashPW.equals(repeatHashPW)) {
							throw new IllegalArgumentException("Passwords do not match");
						}
						
						
					    if(!companyDirectory.addEmployeeToBillToLocation(new Employee(firstName,lastName,id,email,hashPW), "Circassia Pharmaceuticals", "5151 McCrimmon Parkway"))
							throw new IllegalArgumentException("Vendor company bill to not found in directory");
						
					}
					
					
				} else {
					JOptionPane.showMessageDialog(this, "Employee already in system.");
				}
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, iae.getMessage());
			}
			
			employeeDirectoryTableModel.updateData();
			
		} else if (e.getSource() == btnRemoveEmployee) {
			int row = tableEmployeeDirectory.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "No employee selected.");
			} else {
				try {
					userDirectory.removeUser(tableEmployeeDirectory.getValueAt(row, 2).toString());
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(this, "No employee selected.");
				}
			}
			employeeDirectoryTableModel.updateData();
		}
		
		this.validate();
		this.repaint();
	}
	
	/**
	 * Returns a file name generated through interactions with a {@link JFileChooser}
	 * object.
	 * @param chooserType true if open, false if save
	 * @return the file name selected through {@link JFileChooser}
	 */
	private String getFileName(boolean chooserType) {
		JFileChooser fc = new JFileChooser("./");  //Open JFileChoose to current working directory
		fc.setApproveButtonText("Select");
		int returnVal = Integer.MIN_VALUE;
		if (chooserType) {
			fc.setDialogTitle("Load Course Catalog");
			returnVal = fc.showOpenDialog(this);
		} else {
			fc.setDialogTitle("Save Schedule");
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
	 * {@link EmployeeDirectoryTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Students to the user.
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
		 * Updates the given model with {@link Product} information from the {@link VendorDirectory}.
		 */
		public void updateData() {
			data = userDirectory.getEmployeeDirectory();
		}
	}

}