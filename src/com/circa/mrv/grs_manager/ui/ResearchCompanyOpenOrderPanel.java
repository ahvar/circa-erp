/**
 * 
 */
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

import com.circa.mrv.grs_manager.catalog.OrderRecord;
import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.niox.Product;

import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.user.schedule.OrderSchedule;

/**
 * Displays a list of the orders with open status.
 * 
 * @author Arthur Vargas
 */
public class ResearchCompanyOpenOrderPanel extends JPanel implements ActionListener {
	
	/** Panel for displaying order details */
	/** ID used for object serialization */
	private static final long serialVersionUID = 1L;

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
	
	/** JTable for displaying the open order schedule of orders */
	private JTable tableOpenOrderSchedule;
	/** Scroll pane for table */
	private JScrollPane scrollOpenOrderSchedule;
	/** Table Model for schedule of currently open orders */
	private OpenOrderTableModel openOrderTableModel;

	/** The order record */
	OrderRecord orderRecord;
	/** The user currently logged in */
    private Employee currentUser;
	

	/**
	 * Constructs the ResearchCompanyOpenOrderPanel and sets up the GUI components
	 */
	public ResearchCompanyOpenOrderPanel() {
	    super(new GridBagLayout());
	    
		currentUser = (Employee)GRSManager.getInstance().getCurrentUser();
		
		orderRecord = GRSManager.getInstance().getOrderRecord();
	  
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Order Schedule Buttons");		

//		pnlButtons.setLayout(new GridLayout(1, 1));
//		btnLogout = new JButton("Logout");
//		btnLogout.addActionListener(this);
//		pnlButtons.add(btnLogout);
		
		//Set up Course Roll table
		openOrderTableModel = new OpenOrderTableModel();
		tableOpenOrderSchedule = new JTable(openOrderTableModel);
		tableOpenOrderSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableOpenOrderSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableOpenOrderSchedule.setFillsViewportHeight(true);
		
		scrollOpenOrderSchedule = new JScrollPane(tableOpenOrderSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Open Order Schedule");
		scrollOpenOrderSchedule.setBorder(boarder);
		
		GridBagConstraints c = new GridBagConstraints();
		
		/***************************SET UP OPEN ORDER SCHEDULE TABLE**************************
		 * 																					  *
		 * 																					  *
		 * 																					  *
		 * 																				      *
		 *************************************************************************************/
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollOpenOrderSchedule, c);
		
		/*******************************SET UP STUDY PANEL*********************************
		 *																				  *
		 *																				  *
		 *																				  *
		 **********************************************************************************
		pnlStudyDetails = new JPanel();
		pnlStudyDetails.setLayout(new GridLayout(0,6));
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = .5;
		pnlStudyDetails.add(lblOrderEntryDateTitle,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		pnlStudyDetails.add(txtFldDate,c);
		
		c.gridx = 0;
		c.gridy =2;
		c.weightx = .5;
		c.gridwidth = 2;
		pnlStudyDetails.add(lblStudyNumberTitle,c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = .5;
		pnlStudyDetails.add(cmbBoxStudyNumber,c);
		
		lblSiteNumberTitle.setSize(50,100);
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = .5;
		pnlStudyDetails.add(lblSiteNumberTitle,c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = .5;
		c.gridwidth = 8;
		pnlStudyDetails.add(cmbBoxSiteNumber,c);
		
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlStudyDetails, c);
		
		
		/******************** ADD ORDER DETAILS PANEL ********************************
		 * 																			  *	
		 * 																			  *
		 * ***************************************************************************
		pnlOrderDetails = new JPanel();
		pnlOrderDetails.setLayout(new GridBagLayout());
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
	*/
		updateTable();
	}
	
	/**
	 * Updates the orders in the OpenOrderScheduleTableModel.
	 */
	public void updateTable() {
		//courseRollTableModel.updateData();
		openOrderTableModel.updateData();
		//initFacultySchedule();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		this.validate();
		this.repaint();
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
		private String [] columnNames = {"Study", "Site", "City", "Country", "PO Number", "Note", "Email", "Date", "State","Phone","Fax"};
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
			data = orderRecord.getOpenOrderArray();
			//facultyScheduleTableModel.fireTableDataChanged();
			ResearchCompanyOpenOrderPanel.this.repaint();
			ResearchCompanyOpenOrderPanel.this.validate();
		}	
	}

}
