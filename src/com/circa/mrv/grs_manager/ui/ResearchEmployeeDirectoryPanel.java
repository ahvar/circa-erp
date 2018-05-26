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
import javax.swing.JComboBox;
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

import com.circa.mrv.grs_manager.directory.Company;
import com.circa.mrv.grs_manager.directory.CompanyDirectory;
import com.circa.mrv.grs_manager.directory.ResearchCompany;
import com.circa.mrv.grs_manager.directory.UserDirectory;
import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.location.BillTo;
import com.circa.mrv.grs_manager.location.ResearchSite;
import com.circa.mrv.grs_manager.location.ShipTo;

/**
 * Creates a user interface for working with the ResearchEmployeeDirectory.
 * 
 * @author Arthur Vargas
 */
public class ResearchEmployeeDirectoryPanel extends JPanel implements ActionListener {
	
	/** ID used for object serialization */
	private static final long serialVersionUID = 1L;

	/** Button for resetting the directory */
	private JButton btnNewResearchEmployeeList;
	/** Button for loading a new directory */
	private JButton btnLoadResearchEmployeeList;
	/** Button for saving the final directory */
	private JButton btnSaveResearchEmployeeList;
	/** JTable for displaying the directory of employees */
	private JTable tableResearchEmployeeDirectory;
	/** Scroll pane for table */
	private JScrollPane scrollResearchEmployeeDirectory;
	/** TableModel for directory of employees */
	private ResearchEmployeeDirectoryTableModel researchEmployeeDirectoryTableModel;
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
	/** JLabel for the name of the research company */
	private JLabel lblCompanyName;
	/** JLabel for the street address of the company */
	private JLabel lblCompanyStreetAddress;
	/** JLabel for a building, suite, or unit at the company's street location */
	private JLabel lblCompanyAddress2;
	/** JLabel for the city of the company location */
	private JLabel lblCompanyCity;
	/** JLabel for the state/provice for the company location */
	private JLabel lblCompanyState;
	/** JLabel for the country of the company location */
	private JLabel lblCompanyCountry;
	/** JLabel for the zip code of the company location */
	private JLabel lblCompanyZipCode;
	/** JLabel for the location type */
	private JLabel lblCompanyLocationType;
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
	/** JTextField for location classification */
	private JComboBox<String> comboLocationType;
	/** JTextField for company name */
	private JTextField txtCompanyName;
	/** JTextField for company street address */
	private JTextField txtCompanyStreetAddress;
	/** JTextField for a building, suite, or unit number for the company */
	private JTextField txtCompanyAddress2;
	/** JTextField for the city of the company location */
	private JTextField txtCompanyCity;
	/** JTextField for the state/province where the company is located */
	private JTextField txtCompanyState;
	/** JTextField for the country where the company is located */
	private JTextField txtCompanyCountry;
	/** JTextField for the zip code the company is located in */
	private JTextField txtCompanyZipCode;
	/** Button for adding a new research employee */
	private JButton btnAddResearchEmployee;
	/** Button for removing the selected employee from the directory */
	private JButton btnRemoveResearchEmployee;
	/** Reference to UserDirectory */
	private UserDirectory userDirectory;
	/** Reference to CompanyDirectory */
	private CompanyDirectory companyDirectory;
	
	/** Hash algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Constructs the ResearchEmployeeDirectoryPanel and sets up the GUI 
	 * components.
	 */
	public ResearchEmployeeDirectoryPanel() {
		super(new GridBagLayout());
		
		userDirectory = GRSManager.getInstance().getUserDirectory();
		companyDirectory = GRSManager.getInstance().getCompanyDirectory();
		
		//Set up Directory buttons
		btnNewResearchEmployeeList = new JButton("New Employee Directory");
		btnNewResearchEmployeeList.addActionListener(this);
		btnLoadResearchEmployeeList = new JButton("Load Employee Directory");
		btnLoadResearchEmployeeList.addActionListener(this);
		btnSaveResearchEmployeeList = new JButton("Save Employee Directory");
		btnSaveResearchEmployeeList.addActionListener(this);
		
		JPanel pnlDirectoryButton = new JPanel();
		pnlDirectoryButton.setLayout(new GridLayout(1, 3));
		pnlDirectoryButton.add(btnNewResearchEmployeeList);
		pnlDirectoryButton.add(btnLoadResearchEmployeeList);
		pnlDirectoryButton.add(btnSaveResearchEmployeeList);
		
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Directory Buttons");
		pnlDirectoryButton.setBorder(boarder);
		pnlDirectoryButton.setToolTipText("Directory Buttons");
		
		researchEmployeeDirectoryTableModel = new ResearchEmployeeDirectoryTableModel();
		tableResearchEmployeeDirectory = new JTable(researchEmployeeDirectoryTableModel);
		tableResearchEmployeeDirectory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableResearchEmployeeDirectory.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableResearchEmployeeDirectory.setFillsViewportHeight(true);
		
		scrollResearchEmployeeDirectory = new JScrollPane(tableResearchEmployeeDirectory, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Employee Directory");
		scrollResearchEmployeeDirectory.setBorder(boarder);
		scrollResearchEmployeeDirectory.setToolTipText("Employee Directory");
		
		//Set up Faculty buttons
		btnAddResearchEmployee = new JButton("Add Employee");
		btnAddResearchEmployee.addActionListener(this);
		btnRemoveResearchEmployee = new JButton("Remove Employee");
		btnRemoveResearchEmployee.addActionListener(this);
		
		JPanel pnlResearchEmployeeButtons = new JPanel();
		pnlResearchEmployeeButtons.setLayout(new GridLayout(1, 2));
		pnlResearchEmployeeButtons.add(btnAddResearchEmployee);
		pnlResearchEmployeeButtons.add(btnRemoveResearchEmployee);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Employee Controls");
		pnlResearchEmployeeButtons.setBorder(boarder);
		pnlResearchEmployeeButtons.setToolTipText("Employee Controls");
		
		lblFirstName = new JLabel("First Name");
		lblLastName = new JLabel("Last Name");
		lblId = new JLabel("ID");
		lblEmail = new JLabel("Email");
		lblPassword = new JLabel("Password");
		lblRepeatPassword = new JLabel("Repeat Password");
		lblCompanyName = new JLabel("Company Name: ");
		lblCompanyStreetAddress = new JLabel("Address: ");
		lblCompanyAddress2 = new JLabel("Suite or Unit: ");
		lblCompanyCity = new JLabel("City: ");
		lblCompanyState = new JLabel("State/Province: ");
		lblCompanyCountry = new JLabel("Country: ");
		lblCompanyZipCode = new JLabel("Zip Code: ");
		lblCompanyLocationType = new JLabel("Location Type: ");
		comboLocationType = new JComboBox<String>();
		txtFirstName = new JTextField(20);
		txtLastName = new JTextField(20);
		txtId = new JTextField(20);
		txtEmail = new JTextField(20);
		txtPassword = new JPasswordField(20);
		txtRepeatPassword = new JPasswordField(20);
		comboLocationType.addItem(ShipTo.getShipTo());
		comboLocationType.addItem(BillTo.getBillTo());
		comboLocationType.setEditable(false);
		txtCompanyName = new JTextField(20);
		txtCompanyStreetAddress = new JTextField(30);
		txtCompanyAddress2 = new JTextField(15);
		txtCompanyCity = new JTextField(20);
		txtCompanyState = new JTextField(20);
		txtCompanyCountry = new JTextField(20);
		txtCompanyZipCode = new JTextField(10);
		
		JPanel pnlResearchEmployeeForm = new JPanel();
		pnlResearchEmployeeForm.setLayout(new GridLayout(7, 2));
		pnlResearchEmployeeForm.add(lblFirstName);
		pnlResearchEmployeeForm.add(txtFirstName);
		pnlResearchEmployeeForm.add(lblLastName);
		pnlResearchEmployeeForm.add(txtLastName);
		pnlResearchEmployeeForm.add(lblId);
		pnlResearchEmployeeForm.add(txtId);
		pnlResearchEmployeeForm.add(lblEmail);
		pnlResearchEmployeeForm.add(txtEmail);
		pnlResearchEmployeeForm.add(lblPassword);
		pnlResearchEmployeeForm.add(txtPassword);
		pnlResearchEmployeeForm.add(lblRepeatPassword);
		pnlResearchEmployeeForm.add(txtRepeatPassword);
		
		pnlResearchEmployeeForm.add(lblCompanyName);
		pnlResearchEmployeeForm.add(txtCompanyName);
		pnlResearchEmployeeForm.add(lblCompanyStreetAddress);
		pnlResearchEmployeeForm.add(txtCompanyStreetAddress);
		pnlResearchEmployeeForm.add(lblCompanyAddress2);
		pnlResearchEmployeeForm.add(txtCompanyAddress2);
		pnlResearchEmployeeForm.add(lblCompanyCity);
		pnlResearchEmployeeForm.add(txtCompanyCity);
		pnlResearchEmployeeForm.add(lblCompanyState);
		pnlResearchEmployeeForm.add(txtCompanyState);
		pnlResearchEmployeeForm.add(lblCompanyZipCode);
		pnlResearchEmployeeForm.add(txtCompanyZipCode);
		pnlResearchEmployeeForm.add(lblCompanyCountry);
		pnlResearchEmployeeForm.add(txtCompanyCountry);
		pnlResearchEmployeeForm.add(lblCompanyLocationType);
		pnlResearchEmployeeForm.add(comboLocationType);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Employee Information");
		pnlResearchEmployeeForm.setBorder(boarder);
		pnlResearchEmployeeForm.setToolTipText("Employee Information");
		
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
		this.add(scrollResearchEmployeeDirectory, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = .5;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(pnlResearchEmployeeButtons, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(pnlResearchEmployeeForm, c);
		

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLoadResearchEmployeeList) {
			String fileName = getFileName(true);
			try {
				userDirectory.loadUsersFromFile(fileName);
				researchEmployeeDirectoryTableModel.updateData();
				scrollResearchEmployeeDirectory.revalidate();
				scrollResearchEmployeeDirectory.repaint();
				researchEmployeeDirectoryTableModel.fireTableDataChanged();
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, iae.getMessage());
			}
		} else if (e.getSource() == btnSaveResearchEmployeeList) {
			String fileName = getFileName(false);
			try {
				userDirectory.saveUserDirectory(fileName);
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, iae.getMessage());
			}
		} else if (e.getSource() == btnNewResearchEmployeeList) {
			userDirectory.newUserDirectory();
			researchEmployeeDirectoryTableModel.updateData();
			scrollResearchEmployeeDirectory.revalidate();
			scrollResearchEmployeeDirectory.repaint();
			researchEmployeeDirectoryTableModel.fireTableDataChanged();
		} else if (e.getSource() == btnAddResearchEmployee) {
			String firstName = txtFirstName.getText();
			String lastName = txtLastName.getText();
			String id = txtId.getText();
			String email = txtEmail.getText();
			char[] password = txtPassword.getPassword();
			char[] repeatPassword = txtRepeatPassword.getPassword();
			String companyName = txtCompanyName.getText();
			String streetAdd = txtCompanyStreetAddress.getText();
			String add2 = txtCompanyAddress2.getText();
			String city = txtCompanyCity.getText();
			String state = txtCompanyState.getText();
			String country = txtCompanyCountry.getText();
			String zip = txtCompanyZipCode.getText();
			String locationType = null;
			try {
				locationType = comboLocationType.getItemAt(comboLocationType.getSelectedIndex());
			} catch (NullPointerException npe) {
				JOptionPane.showMessageDialog(this, "Select the correct location type.");
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
			
			try {
				if(locationType.equals(BillTo.getBillTo())) {
					
					ResearchCompany rc = new ResearchCompany(companyName,streetAdd,add2,city,state,zip,country);
					rc.getBillTo().addEmployee(firstName, lastName, id, email, hashPW);
					companyDirectory.addResearchCompany(rc);
					
				} else if(locationType.equals(ShipTo.getShipTo())) {
					String studyNumber = null;
					studyNumber = JOptionPane.showInputDialog(this, "Enter the Site ID.");
					Long study = Long.parseLong(studyNumber);
					ResearchSite rs = new ResearchSite(streetAdd,add2,city,state,zip,country,study,0);
					rs.addEmployee(firstName, lastName, id, email, hashPW);
					companyDirectory.addResearchCompany(new ResearchCompany(rs,companyName)); 
				}
				txtCompanyName.setText("");
				txtCompanyStreetAddress.setText("");
				txtCompanyAddress2.setText("");
				txtCompanyCity.setText("");
				txtCompanyState.setText("");
				txtCompanyCountry.setText("");
				txtCompanyZipCode.setText("");
				comboLocationType.setSelectedIndex(0);
				
				txtFirstName.setText("");
				txtLastName.setText("");
				txtId.setText("");
				txtEmail.setText("");
				txtPassword.setText("");
				txtRepeatPassword.setText("");
					
	
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, iae.getMessage());
			}

			try {
				userDirectory.addUser(firstName, lastName, id, email, hashPW);
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this,iae.getMessage());
			}
			researchEmployeeDirectoryTableModel.updateData();
		} else if (e.getSource() == btnRemoveResearchEmployee) {
			int row = tableResearchEmployeeDirectory.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "No employee selected.");
			} else {
				try {
					userDirectory.removeUser(tableResearchEmployeeDirectory.getValueAt(row, 2).toString());
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(this, "No employee selected.");
				}
			}
			researchEmployeeDirectoryTableModel.updateData();
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
			fc.setDialogTitle("Load Employee Directory");
			returnVal = fc.showOpenDialog(this);
		} else {
			fc.setDialogTitle("Save Employee Directory");
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
	 * {@link ResearchEmployeeDirectoryTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Faculty to the system.
	 * @author Arthur Vargas
	 */
	private class ResearchEmployeeDirectoryTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"First Name", "Last Name", "Employee ID", "Employee Email"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link ResearchEmployeeDirectoryTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public ResearchEmployeeDirectoryTableModel() {
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
			data = companyDirectory.getResearchEmployeeData();
		}
	}

}