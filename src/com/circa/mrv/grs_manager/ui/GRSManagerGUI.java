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
	private static final String VENDOR_CUSTOMER_PANEL = "VendorPanel";
	/** Constant to identify ResearchPanel */
	private static final String RESEARCH_COMPANY_PANEL = "CustomerPanel";
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
		panel.add(pnlVendor, VENDOR_CUSTOMER_PANEL);
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
					if (manager.getCurrentUser() instanceof Employee && ma) {
						cardLayout.show(panel, VENDOR_CUSTOMER_PANEL);
						pnlVendor.updateTables();
					} else if (manager.getCurrentUser() instanceof Employee) {
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
	 * @author SarahHeckman
	 */
	private class AdministratorPanel extends JPanel implements ActionListener {

		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Constant to identify StudentDirectoryPanel */
		private static final String MORRISVILLE_DIRECTORY_PANEL = "MorrisvilleDirectoryPanel";
		/** Constant to identify FacultyDirectoryPanel */
		private static final String SWEDEN_DIRECTORY_PANEL = "SwedenDirectoryPanel";
		/** Constant to identify CourseCatalog */
		private static final String PRODUCT_CATALOG_PANEL = "ProductCatalogPanel";
		/** Constant to identify InstructorAssignmentPanel */
		private static final String INSTRUCTOR_ASSIGNMENT_PANEL = "InstructorAssignmentPanel";
		/** StudentDirectoryPanel */
		private MorrisvilleDirectoryPanel pnlMorrisvilleDirectory;
		/** FacultyDirectoryPanel */
		private SwedenDirectoryPanel pnlSwedenDirectory;
		/** CourseCatalogPanel */
		private ProductCatalogPanel pnlCatalog;
		/** InstructorAssignmentPanel */
		private InstructorAssignmentPanel pnlInstructorAssignment;
		/** CardLayout for the RegistrarPanel */
		private CardLayout rCardLayout;
		/** Panel for the RegistrarPanel */
		private JPanel rPanel;
		/** Button for the StudentDirectory functionality */
		private JButton btnMorrisvilleDirectory;
		/** Button for the FacultyDirectory functionality */
		private JButton btnSwedenDirectory;
		/** Button for the CourseCatalog functionality */
		private JButton btnProductCatalog;
		/** Button for InstructorAssignment functionality */
		private JButton btnInstructorAssignment;
		/** Button to logout */
		private JButton btnLogout;
		
		public AdministratorPanel() {
			super(new GridBagLayout());
			
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new GridLayout(1, 4));
			btnMorrisvilleDirectory = new JButton("Morrisville Directory");
			btnMorrisvilleDirectory.addActionListener(this);
			btnSwedenDirectory = new JButton("Sweden Directory");
			btnSwedenDirectory.addActionListener(this);
			btnProductCatalog = new JButton("Product Catalog");
			btnProductCatalog.addActionListener(this);
			btnInstructorAssignment = new JButton("Faculty Assignment");
			btnInstructorAssignment.addActionListener(this);
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener(this);
			pnlButtons.add(btnMorrisvilleDirectory);
			pnlButtons.add(btnSwedenDirectory);
			pnlButtons.add(btnProductCatalog);
			pnlButtons.add(btnInstructorAssignment);
			pnlButtons.add(btnLogout);
			
			rPanel = new JPanel();
			rCardLayout = new CardLayout();
			rPanel.setLayout(rCardLayout);
			pnlMorrisvilleDirectory = new MorrisvilleDirectoryPanel();
			pnlSwedenDirectory = new SwedenDirectoryPanel();
			pnlCatalog = new ProductCatalogPanel();
			pnlInstructorAssignment = new InstructorAssignmentPanel();
			rPanel.add(pnlMorrisvilleDirectory, MORRISVILLE_DIRECTORY_PANEL);
			rPanel.add(pnlSwedenDirectory, SWEDEN_DIRECTORY_PANEL);
			rPanel.add(pnlCatalog, PRODUCT_CATALOG_PANEL);
			rPanel.add(pnlInstructorAssignment, INSTRUCTOR_ASSIGNMENT_PANEL);
			rCardLayout.show(rPanel, MORRISVILLE_DIRECTORY_PANEL);
			
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
			add(rPanel, c);
		}
		
		/**
		 * Performs actions when any component with an action listener is selected.
		 * @param e ActionEvent representing the user action
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnMorrisvilleDirectory) {
				rCardLayout.show(rPanel, MORRISVILLE_DIRECTORY_PANEL); 
			} else if (e.getSource() == btnSwedenDirectory) {
				rCardLayout.show(rPanel, SWEDEN_DIRECTORY_PANEL);
			} else if (e.getSource() == btnProductCatalog) {
				rCardLayout.show(rPanel, PRODUCT_CATALOG_PANEL);
			} else if (e.getSource() == btnInstructorAssignment) {
				rCardLayout.show(rPanel, INSTRUCTOR_ASSIGNMENT_PANEL);
				pnlInstructorAssignment.updateTables();
			} else if (e.getSource() == btnLogout) {
				GRSManager.getInstance().logout();
				cardLayout.show(panel, LOGIN_PANEL);
			}
		}
		
	}
	
	/**
	 * Creates a panel for student registration.
	 * @author SarahHeckman
	 */
	private class VendorPanel extends JPanel implements ActionListener {

		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Button to logout */
		private JButton btnLogout;
		/** StudentRegistrationPanel */
		private MorrisvilleRegistrationPanel mvRegPanel;
		
		public VendorPanel() {
			super(new GridBagLayout());
			
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new GridLayout(1, 1));
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener(this);
			pnlButtons.add(btnLogout);
			
			mvRegPanel = new MorrisvilleRegistrationPanel();
			
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
			add(mvRegPanel, c);
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
			mvRegPanel.updateTables();
		}
		
	}
	
	/**
	 * Creates a panel for faculty to manage their classes
	 * @author Sarah Heckman
	 */
	private class ResearchPanel extends JPanel implements ActionListener {

		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Button to logout */
		private JButton btnLogout;
		private SwedenSchedulePanel swedSchedulePanel;
		/**
		 * Temporary class for the FacultyPanel until we implement
		 * that functionality.
		 */
		public ResearchPanel() {			
			super(new GridBagLayout());
			
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new GridLayout(1, 1));
			btnLogout = new JButton("Logout");
			btnLogout.addActionListener(this);
			pnlButtons.add(btnLogout);
			
			swedSchedulePanel = new SwedenSchedulePanel();
			
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
			add(swedSchedulePanel, c);
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
			swedSchedulePanel = new SwedenSchedulePanel();
		}
	}

}