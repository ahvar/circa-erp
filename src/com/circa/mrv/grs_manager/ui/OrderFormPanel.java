/**
 * 
 */
package com.circa.mrv.grs_manager.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.user.schedule.OrderSchedule;

/**
 * 
 * @author Arthur Vargas
 */
public class OrderFormPanel extends JPanel implements ActionListener {
	private String newline = "\n";
    protected static final String GRS_ORDER_NUMBER = "JTextField";
    protected static final String passwordFieldString = "JPasswordField";
    protected static final String ftfString = "JFormattedTextField";
    protected static final String buttonString = "JButton";
    
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
	
	/** Current user */
	private Employee currentUser;
	private OrderSchedule schedule;
	
	/**
	 * 
	 */
	public OrderFormPanel() {
        super(new GridBagLayout());
		
		//RegistrationManager manager = RegistrationManager.getInstance();
		currentUser = (Employee)GRSManager.getInstance().getCurrentUser();
		if (currentUser != null)
			schedule = currentUser.getSchedule();
		
		GridBagConstraints c = new GridBagConstraints();
		
		/*******************************SET UP STUDY PANEL*********************************
		 *																				  *
		 *																				  *
		 *																				  *
		 **********************************************************************************/
		pnlStudyDetails = new JPanel();
		pnlStudyDetails.setLayout(new GridLayout(4, 10));
		
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
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
