package com.circa.mrv.grs_manager.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.directory.VendorCompany;
import com.circa.mrv.grs_manager.directory.ResearchCompany;

/**
 * Main GUI for GRS Manager project.  It controls authentication
 * and manages the panels for each user.
 * @author Arthur Vargas
 */
public class GRSManagerGUI {
	
	/** JFrame for the GUI */
	private static JFrame gui;
	/** GRSManagerGUI title */	
	private static final String APP_TITLE = "GRS Manager";
	/** Constant to identify LoginPanel */
	private static final String LOGIN_PANEL = "LoginPanel";
	/** Constant to identify VendorDirectoryPanel */
	private static final String VENDOR_COMPANY_PANEL = "VendorPanel";
	/** Constant to identify ResearchPanel */
	private static final String RESEARCH_COMPANY_PANEL = "ResearchPanel";
	/** Constant to identify AdministratorPanel */
	private static final String ADMINISTRATOR_PANEL = "AdministratorPanel";
	/** LoginPanel */
	private LoginPanel pnlLogin;
	/** AdministratorPanel */
	private AdministratorPanel pnlAdministrator;
	/** VendorPanel */
	private VendorPanel pnlVendor;
	/** ResearchPanel */
	private ResearchPanel pnlResearch;
	/** CardLayout for GUI */
	private CardLayout cardLayout;
	/** Panel that will contain all of the application views */
	private JPanel panel;
	
	/**
	 * Constructs the GRSManager GUI and creates the menus and panels.
	 */
	public GRSManagerGUI() {
		gui = new JFrame();
		gui.setSize(800, 800);
		gui.setLocation(50, 50);
		gui.setTitle(APP_TITLE);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnlLogin = new LoginPanel();
		pnlAdministrator = new AdministratorPanel();
		pnlVendor = new VendorPanel();
		pnlResearch = new ResearchPanel();
		
		panel = new JPanel();
		cardLayout = new CardLayout();
		panel.setLayout(cardLayout);
		panel.add(pnlLogin, LOGIN_PANEL);
		panel.add(pnlAdministrator, ADMINISTRATOR_PANEL);
		panel.add(pnlVendor, VENDOR_COMPANY_PANEL);
		panel.add(pnlResearch, RESEARCH_COMPANY_PANEL);
		cardLayout.show(panel, LOGIN_PANEL);
		
		Container c = gui.getContentPane();
		c.add(panel, BorderLayout.CENTER);
			
		gui.setVisible(true);
	}
	
	/**
	 * Starts GRS Manager program.
	 * @param args command line arguments
	 */
	public static void main(String [] args) {
		new GRSManagerGUI();
	}
	
	/**
	 * Creates a panel for user authentication into the system.
	 * @author Arthur Vargas
	 */
	private class LoginPanel extends JPanel implements ActionListener {

		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		
		/** JLabel for id */
		private JLabel lblId;
		/** JTextField for id */
		private JTextField txtId;
		
		/** JLabel for password */
		private JLabel lblPassword;
		/** JTextField for password */
		private JPasswordField txtPassword;
		
		/** JButton to Login */
		private JButton btnLogin;
		/** JButton to Clear */
		private JButton btnClear;
		
		/**
		 * Constructs the LoginPanel.
		 */
		public LoginPanel() {
			super(new GridBagLayout());
			
			GridBagConstraints c = new GridBagConstraints();
			
			//Create ID components
			lblId = new JLabel("User ID:");
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.weightx = 0.5;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.RELATIVE;
			add(lblId, c);
			
			txtId = new JTextField(20);
			c.gridx = 1;
			c.gridy = 0;
			c.gridwidth = 1;
			c.weightx = 0.5;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.RELATIVE;
			add(txtId, c);
			
			//Create Password components
			lblPassword = new JLabel("Password:");
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.weightx = 0.5;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.RELATIVE;
			add(lblPassword, c);
			
			txtPassword = new JPasswordField(20);
			c.gridx = 1;
			c.gridy = 1;
			c.gridwidth = 1;
			c.weightx = 0.5;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.RELATIVE;
			add(txtPassword, c);
			
			//Create Buttons
			btnClear = new JButton("Clear");
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			c.weightx = 0.5;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.RELATIVE;
			add(btnClear, c);
			
			btnLogin = new JButton("Login");
			c.gridx = 1;
			c.gridy = 2;
			c.gridwidth = 1;
			c.weightx = 0.5;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.RELATIVE;
			add(btnLogin, c);
						
			//Add ActionListeners
			btnLogin.addActionListener(this);
			btnClear.addActionListener(this);
		}

		/**
		 * Performs actions when any component with an action listener is selected.
		 * @param e ActionEvent representing the user action
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnLogin) {
				String id = txtId.getText();
				String password = new String(txtPassword.getPassword());
				
				GRSManager manager = GRSManager.getInstance();
				try {
				if (manager.login(id, password)) {
					txtId.setText("");
					txtPassword.setText("");
					if (manager.getCurrentUser() instanceof Employee && manager.getCompany() instanceof VendorCompany) {
						cardLayout.show(panel, VENDOR_COMPANY_PANEL);
						pnlVendor.updateTables();
					} else if (manager.getCurrentUser() instanceof Employee && manager.getCompany() instanceof ResearchCompany) {
						cardLayout.show(panel, RESEARCH_COMPANY_PANEL );
						pnlResearch.updateTables();
					} else {
						cardLayout.show(panel, ADMINISTRATOR_PANEL);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Invalid id and password.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(this, iae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else if (e.getSource() == btnClear) {
				txtId.setText("");
				txtPassword.setText("");
			}
		}
		
	}
	
	/**
	 * Creates a panel for user authentication into the system.
	 * @author Arthur Vargas
	 */
	private class AdministratorPanel extends JPanel implements ActionListener {

		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Constant to identify VendorDirectoryPanel */
		private static final String VENDOR_DIRECTORY_PANEL = "VendorDirectoryPanel";
		/** Constant to identify ResearchDirectoryPanel */
		private static final String RESEARCH_DIRECTORY_PANEL = "ResearchDirectoryPanel";
		/** Constant to identify ProductCatalog */
		private static final String PRODUCT_CATALOG_PANEL = "ProductCatalogPanel";
		/** Constant to identify InstructorAssignmentPanel */
		private static final String COMPANY_ASSIGNMENT_PANEL = "CompanyAssignmentPanel";
		
		/** VendorEmployeeDirectoryPanel */
		private VendorEmployeeDirectoryPanel pnlVendorEmployeeDirectory;
		/** ResearchEmployeeDirectoryPanel */
		private ResearchEmployeeDirectoryPanel pnlResearchEmployeeDirectory;
		/** ProductCatalogPanel */
		private ProductCatalogPanel pnlCatalog;
		/** CompanyAssignmentPanel */
		private CompanyAssignmentPanel pnlCompanyAssignment;
		/** CardLayout for the AdministratorPanel */
		private CardLayout aCardLayout;
		/** Panel for the AdministratorPanel */
		private JPanel aPanel;
		
		/** Button for the VendorEmployeeDirectory functionality */
		private JButton btnVendorEmployeeDirectory;
		/** Button for the ResearchCompanyDirectory functionality */
		private JButton btnResearchCompanyDirectory;
		/** Button for the ProductCatalog functionality */
		private JButton btnProductCatalog;
		/** Button for CompanyAssignment functionality */
		private JButton btnCompanyAssignment;
		/** Button to logout */
		private JButton btnLogout;
		
		public AdministratorPanel() {
			super(new GridBagLayout());
			
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new GridLayout(1, 4));
			btnVendorEmployeeDirectory = new JButton("Vendor Employee Directory");
			btnVendorEmployeeDirectory.addActionListener(this);
			btnResearchCompanyDirectory = new JButton("Research Employee Directory");
			btnResearchCompanyDirectory.addActionListener(this);
			btnProductCatalog = new JButton("Product Catalog");
			btnProductCatalog.addActionListener(this);
			btnCompanyAssignment = new JButton("Company Assignment");
			btnCompanyAssignment.addActionListener(this);
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener(this);
			pnlButtons.add(btnVendorEmployeeDirectory);
			pnlButtons.add(btnResearchCompanyDirectory);
			pnlButtons.add(btnProductCatalog);
			pnlButtons.add(btnCompanyAssignment);
			pnlButtons.add(btnLogout);
			
			aPanel = new JPanel();
			aCardLayout = new CardLayout();
			aPanel.setLayout(aCardLayout);
			pnlVendorEmployeeDirectory = new VendorEmployeeDirectoryPanel();
			pnlResearchEmployeeDirectory = new ResearchEmployeeDirectoryPanel();
			pnlCatalog = new ProductCatalogPanel();
			pnlCompanyAssignment = new CompanyAssignmentPanel();
			aPanel.add(pnlVendorEmployeeDirectory, VENDOR_DIRECTORY_PANEL);
			aPanel.add(pnlResearchEmployeeDirectory, RESEARCH_DIRECTORY_PANEL);
			aPanel.add(pnlCatalog, PRODUCT_CATALOG_PANEL);
			aPanel.add(pnlCompanyAssignment, COMPANY_ASSIGNMENT_PANEL);
			aCardLayout.show(aPanel, VENDOR_DIRECTORY_PANEL);
			
//			scrollRPanel = new JScrollPane(rPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.weightx = 1;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.RELATIVE;
			add(pnlButtons, c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.BOTH;
			add(aPanel, c);
		}
		
		/**
		 * Performs actions when any component with an action listener is selected.
		 * @param e ActionEvent representing the user action
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnVendorEmployeeDirectory) {
				aCardLayout.show(aPanel, VENDOR_DIRECTORY_PANEL); 
			} else if (e.getSource() == btnResearchCompanyDirectory) {
				aCardLayout.show(aPanel, RESEARCH_DIRECTORY_PANEL);
			} else if (e.getSource() == btnProductCatalog) {
				aCardLayout.show(aPanel, PRODUCT_CATALOG_PANEL);
			} else if (e.getSource() == btnCompanyAssignment) {
				aCardLayout.show(aPanel, COMPANY_ASSIGNMENT_PANEL);
				pnlCompanyAssignment.updateTables();
			} else if (e.getSource() == btnLogout) {
				GRSManager.getInstance().logout();
				cardLayout.show(panel, LOGIN_PANEL);
			}
		}
		
	}
	
	/**
	 * Creates a panel for vendor company to view order schedule.
	 * @author Arthur Vargas
	 */
	private class VendorPanel extends JPanel implements ActionListener {

		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Button to logout */
		private JButton btnLogout;
		/** VendorCompanyOrderSchedulePanel */
		private VendorCompanyOrderSchedulePanel vcOrdSchPanel;
		
		public VendorPanel() {
			super(new GridBagLayout());
			
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new GridLayout(1, 1));
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener(this);
			pnlButtons.add(btnLogout);
			
			vcOrdSchPanel = new VendorCompanyOrderSchedulePanel();
			
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.weightx = 1;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.RELATIVE;
			add(pnlButtons, c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.BOTH;
			add(vcOrdSchPanel, c);
		}
		
		/**
		 * Performs actions when any component with an action listener is selected.
		 * @param e ActionEvent representing the user action
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnLogout) {
				GRSManager.getInstance().logout();
				cardLayout.show(panel, LOGIN_PANEL);
			}
		}
		
		/**
		 * Updates tables
		 */
		public void updateTables() {
			vcOrdSchPanel.updateTables();
		}
		
	}
	
	/**
	 * Creates a panel of the current order schedule for research companies
	 * @author Arthur Vargas
	 */
	private class ResearchPanel extends JPanel implements ActionListener {

		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Button to logout */
		private JButton btnLogout;
		private ResearchCompanyOrderSchedulePanel rcOrdSchPanel;
		/**
		 * Temporary class for the ResearchPanel until we implement
		 * that functionality.
		 */
		public ResearchPanel() {			
			super(new GridBagLayout());
			
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new GridLayout(1, 1));
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener(this);
			pnlButtons.add(btnLogout);
			
			rcOrdSchPanel = new ResearchCompanyOrderSchedulePanel();
			
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.weightx = 1;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.RELATIVE;
			add(pnlButtons, c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.BOTH;
			add(rcOrdSchPanel, c);
		}
		
		/**
		 * Performs actions when any component with an action listener is selected.
		 * @param e ActionEvent representing the user action
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnLogout) {
				GRSManager.getInstance().logout();
				cardLayout.show(panel, LOGIN_PANEL);
			}
		}
		
		/**
		 * Updates tables
		 */
		public void updateTables() {
			rcOrdSchPanel = new ResearchCompanyOrderSchedulePanel();
		}
	}

}