package com.circa.mrv.grs_manager.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import com.circa.mrv.grs_manager.catalog.NioxCatalog;
import com.circa.mrv.grs_manager.catalog.OrderRecord;
import com.circa.mrv.grs_manager.document.Order;
import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.user.schedule.OrderSchedule;

/**
 * Creates a user interface for vendor employees to view order schedule and modify order status.
 * 
 * @author Arthur Vargas
 */
public class VendorCompanyOrderSchedulePanel  extends JPanel implements ActionListener {
	/** ID number used for object serialization. */
	private static final long serialVersionUID = 1L;
	/** Button for modifying the selected Order in the OrderRecord to the schedule */
	private JButton btnModifyCourse;
	/** Button for removing the selected Order from the schedule */
	private JButton btnRemoveOrder;
	/** Button for resetting the schedule */
	private JButton btnComplete;
	/** Button for displaying the final schedule */
	private JButton btnDisplay;
	
	/** JTable for displaying the order schedule */
	private JTable orderSchedule;
	/** JTable for displaying the schedule of Orders */
	private JTable tableSchedule;
	/** TableModel for catalog */
	private OrderTableModel orderTableModel;
	/** TableModel for schedule */
	private OrderTableModel scheduleTableModel;
	
	/** Vendor's Schedule title label */
	private JLabel lblScheduleTitle;
	/** Vendor's Schedule text field */
	private JTextField txtScheduleTitle;
	/** Button for setting vendor's order schedule title */
	private JButton btnSetScheduleTitle;
	/** Scroll for vendor's order schedule */
	private JScrollPane scrollSchedule;
	/** Border for Schedule */
	private TitledBorder borderSchedule;
	/** Panel for displaying Order Details */
	private JPanel pnlOrderDetails;
	/** Label for Order Details GRSManager number title */
	private JLabel lblGRSOrderNumberTitle = new JLabel("GRS Order: ");
	/** Label for Order Details NAV sales order number title */
	private JLabel lblNAVSalesOrderNumberTitle = new JLabel("NAV Sales Order: ");
	/** Label for Order Details customer PO number */
	private JLabel lblCustomerPONumberTitle = new JLabel("Customer PO: ");
	/** Label for Order Details order schedule title title */
	private JLabel lblTitleTitle = new JLabel("Title: ");
	/** Label for Order Details user id title */
	private JLabel lblUserIDTitle = new JLabel("Created By: ");
	/** Label for Order Details number of products ordered title */
	private JLabel lblNumberOfProductsTitle = new JLabel("Number of Products Ordered: ");
	/** Label for Order Details date created title */
	private JLabel lblCreatedDateTitle = new JLabel("Entered On: ");
	/** Label for Order Details product cap title */
	private JLabel lblProductCapTitle = new JLabel("Product Cap: ");
	/** Label for Order Details delivery date title */
	private JLabel lblDeliveryDateTitle = new JLabel("Delivery Date: ");
	/** Label for Order Details GRSManager Number */
	private JLabel lblGRSManagerNumber = new JLabel("");
	/** Lable for Order Details customer PO number */
	private JLabel lblCustomerPONumber = new JLabel("");
	/** Label for Order Details NAV sales order number */
	private JLabel lblNAVSalesOrder = new JLabel("");
	/** Label for Order Details schedule title */
	private JLabel lblTitle = new JLabel("");
	/** Label for Order Details user id */
	private JLabel lblUserID = new JLabel("");
	/** Label for Order Details product count */
	private JLabel lblNumberOfProducts = new JLabel("");
	/** Label for Order Details date created*/
	private JLabel lblCreatedDate = new JLabel("");
	/** Label for Order Details product cap */
	private JLabel lblProductCap = new JLabel("");
	/** Label for Course Details open seats */
	private JLabel lblDeliveryDate = new JLabel("");
	/** Current user */
	private Employee currentUser;
	/** Course catalog */
	private OrderRecord orderRecord;
	/** Current user's schedule */
	private OrderSchedule schedule;
	
	
	/**
	 * Creates the requirements list.
	 */
	public VendorCompanyOrderSchedulePanel() {
		super(new GridBagLayout());
		
		GRSManager manager = GRSManager.getInstance();
	
		currentUser = (Employee)manager.getCurrentUser();
		orderRecord = manager.getOrderRecord();
		
		//Set up the JPanel that will hold action buttons		
		//btnModifyCourse = new JButton("Modify Order");
		//btnModifyCourse.addActionListener(this);
		//btnRemoveOrder = new JButton("Remove Order");
		//btnRemoveOrder.addActionListener(this);
		btnComplete = new JButton("Complete");
		btnComplete.addActionListener(this);
		//btnDisplay = new JButton("Display Final Schedule");
//		btnDisplay.addActionListener(this);
		//btnDisplay.setEnabled(false);
		lblScheduleTitle = new JLabel("Schedule Title: ");
		txtScheduleTitle = new JTextField("", 20); 
		btnSetScheduleTitle = new JButton("Set Title");
		btnSetScheduleTitle.addActionListener(this);
		
		JPanel pnlActions = new JPanel();
		pnlActions.setLayout(new GridLayout(3, 1));
		JPanel pnlComplete = new JPanel();
		pnlComplete.setLayout(new GridLayout(1, 1));
		pnlComplete.add(btnComplete);
		//pnlComplete.add(btnRemoveOrder);
		JPanel pnlResetDisplay = new JPanel();
		pnlResetDisplay.setLayout(new GridLayout(1, 2));
		pnlResetDisplay.add(btnComplete);
		pnlResetDisplay.add(btnDisplay);
		JPanel pnlScheduleTitle = new JPanel();
		pnlScheduleTitle.setLayout(new GridLayout(1, 3));
		pnlScheduleTitle.add(lblScheduleTitle);
		pnlScheduleTitle.add(txtScheduleTitle);
		pnlScheduleTitle.add(btnSetScheduleTitle);
		pnlActions.add(pnlComplete);
		pnlActions.add(pnlResetDisplay);
		pnlActions.add(pnlScheduleTitle);
		
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder borderActions = BorderFactory.createTitledBorder(lowerEtched, "Actions");
		pnlActions.setBorder(borderActions);
		pnlActions.setToolTipText("Scheduler Actions");
					
		//Set up Catalog table
		orderTableModel = new OrderTableModel();
		orderSchedule = new JTable(orderTableModel) {
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
				
				return (String)orderTableModel.getValueAt(rowIndex, realColumnIndex);
			}
		};
		orderSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		orderSchedule.setFillsViewportHeight(true);
		orderSchedule.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String name = orderSchedule.getValueAt(orderSchedule.getSelectedRow(), 0).toString();
				String section = orderSchedule.getValueAt(orderSchedule.getSelectedRow(), 1).toString();
				Order o = orderRecord.getOrderByPOAndStudy(name, section);
				updateOrderDetails(o);
			}
			
		});
		
		JScrollPane scrollOrderSchedule = new JScrollPane(orderSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		TitledBorder borderCatalog = BorderFactory.createTitledBorder(lowerEtched, "Order Schedule");
		scrollOrderSchedule.setBorder(borderCatalog);
		scrollOrderSchedule.setToolTipText("Order Schedule");
		
		//Set up Schedule table
		//scheduleTableModel = new OrderTableModel(false);
		//tableSchedule = new JTable(scheduleTableModel);
		//tableSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//tableSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		//tableSchedule.setFillsViewportHeight(true);
		
		//scrollSchedule = new JScrollPane(tableSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//borderSchedule = BorderFactory.createTitledBorder(lowerEtched, "");
		//scrollSchedule.setBorder(borderSchedule);
		
		
		updateTables();
		
		//Set up the course details panel
		pnlOrderDetails = new JPanel();
		pnlOrderDetails.setLayout(new GridLayout(5, 1));
		JPanel pnlOrderNumbers = new JPanel(new GridLayout(1, 4));
		pnlOrderNumbers.add(lblGRSOrderNumberTitle);
		pnlOrderNumbers.add(lblGRSManagerNumber);
		pnlOrderNumbers.add(lblCustomerPONumberTitle);
		pnlOrderNumbers.add(lblCustomerPONumber);
		pnlOrderNumbers.add(lblNAVSalesOrderNumberTitle);
		pnlOrderNumbers.add(lblNAVSalesOrder);
		
		JPanel pnlTitle = new JPanel(new GridLayout(1, 1));
		pnlTitle.add(lblTitleTitle);
		pnlTitle.add(lblTitle);
		
		JPanel pnlUserAndProductCount = new JPanel(new GridLayout(1, 4));
		pnlUserAndProductCount.add(lblUserIDTitle);
		pnlUserAndProductCount.add(lblUserID);
		pnlUserAndProductCount.add(lblNumberOfProductsTitle);
		pnlUserAndProductCount.add(lblNumberOfProducts);
		
		//JPanel pnlMeeting = new JPanel(new GridLayout(1, 1));
		//pnlMeeting.add(lblCreatedDateTitle);
		//pnlMeeting.add(lblCreatedDate);
		
		JPanel pnlCapAndDelivery = new JPanel(new GridLayout(1, 4));
		pnlCapAndDelivery.add(lblProductCapTitle);
		pnlCapAndDelivery.add(lblProductCap);
		pnlCapAndDelivery.add(lblDeliveryDateTitle);
		pnlCapAndDelivery.add(lblDeliveryDate);
		
		pnlOrderDetails.add(pnlOrderNumbers);
		pnlOrderDetails.add(pnlTitle);
		pnlOrderDetails.add(pnlUserAndProductCount);
		//pnlOrderDetails.add(pnlMeeting);
		pnlOrderDetails.add(pnlCapAndDelivery);
		
		TitledBorder borderOrderDetails = BorderFactory.createTitledBorder(lowerEtched, "Order Details");
		pnlOrderDetails.setBorder(borderOrderDetails);
		pnlOrderDetails.setToolTipText("Order Details");
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollOrderSchedule, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlActions, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		//add(scrollSchedule, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlOrderDetails, c);
	}

	/**
	 * Performs an action based on the given {@link ActionEvent}.
	 * @param e user event that triggers an action.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnComplete) {
			int row = orderSchedule.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "No order selected");
			} else {
				try {
					
					GRSManager.getInstance().getOrderRecord().getOrderByPOAndStudy(orderTableModel.getValueAt(row, 0).toString(), orderTableModel.getValueAt(row, 1).toString()).setStatus(Order.getProcessed()); 
						
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(this, iae.getMessage());
				}
			}
			updateTables();
		} else if (e.getSource() == btnComplete) {
			int row = orderSchedule.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "No item selected in the schedule.");
			} else {
				//if (!GRSManager.getInstance().dropStudentFromCourse(catalog.getProductFromCatalog(tableSchedule.getValueAt(row, 0).toString(), tableSchedule.getValueAt(row, 1).toString()))) {
					//JOptionPane.showMessageDialog(this, "Cannot drop student from " + tableSchedule.getValueAt(row, 0).toString());
				//}
			}
			updateTables();
		} else if (e.getSource() == btnComplete) {
			GRSManager.getInstance().resetSchedule();
			updateTables();
		} else if (e.getSource() == btnSetScheduleTitle) {
			try {
				//schedule.setTitle(txtScheduleTitle.getText()); 
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, "Invalid title.");
			}
			//borderSchedule.setTitle(schedule.getTitle());
		}
		
		this.repaint();
		this.validate();
	}
	
	/**
	 * Updates the catalog and schedule tables.
	 */
	public void updateTables() {
		orderTableModel.updateData();
		scheduleTableModel.updateData();
	}
	
	/**
	 * Updates the pnlCourseDetails with full information about the most
	 * recently selected course.
	 */
	private void updateOrderDetails(Order o) {
		if (o != null) {
			lblCustomerPONumber.setText(o.getPo());
			lblNAVSalesOrder.setText(o.getStudy());
			lblGRSManagerNumber.setText(o.getStudy()); // TODO: change this to GRSNumber instead of study number
			//lblInstructor.setText(c.getInstructorId());
			lblNumberOfProducts.setText("" + o.getAmount());
			//lblMeeting.setText(c.getDeliveryDate());
			//lblEnrollmentCap.setText("" + c.getCourseRoll().getCapacity());
			//lblOpenSeats.setText("" + c.getProductRoll().getRemainingCapacity());
		}
	}
	
	/**
	 * {@link OrderTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of {@link Order} to the user.
	 * @author Arthur Vargas
	 */
	private class OrderTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Study", "Site", "City", "Country", "PO Number", "Note", "Email", "Date", "State", "Phone", "Fax"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link OrderTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public OrderTableModel() {
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
		 * Updates the given model with {@link Order} information from the {@link GRSManager}.
		 */
		private void updateData() {
			data = orderRecord.getOpenOrderArray();
		}
	}

}