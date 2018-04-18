/**
 * 
 */
package com.circa.mrv.grs_manager.ui;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import com.circa.mrv.grs_manager.manager.GRSManager;
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
	/** JTable for displaying the open order schedule of orders */
	private JTable tableOpenOrderSchedule;
	/** Scroll pane for table */
	private JScrollPane scrollOpenOrderSchedule;
	/** TableModel for employee schedule of Orders */
	private OpenOrderScheduleTableModel openOrderScheduleTableModel;
	/** The order schedule */
	OrderSchedule orderSchedule;
	/** The user currently logged in */
    private Employee currentUser;
	

	/**
	 * Constructs the ResearchCompanyOpenOrderPanel and sets up the GUI components
	 */
	public ResearchCompanyOpenOrderPanel() {
	    super(new GridBagLayout());
	    
		currentUser = (Employee)GRSManager.getInstance().getCurrentUser();
		if (currentUser != null)
			orderSchedule = currentUser.getSchedule();
	  
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Schedule Buttons");
	  
		openOrderScheduleTableModel = new OpenOrderScheduleTableModel();
		tableOpenOrderSchedule = new JTable(openOrderScheduleTableModel);
		tableOpenOrderSchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableOpenOrderSchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableOpenOrderSchedule.setFillsViewportHeight(true);
		scrollOpenOrderSchedule = new JScrollPane(tableOpenOrderSchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Open Orders");
		scrollOpenOrderSchedule.setBorder(boarder);
	}
	
	/**
	 * Updates the orders in the OpenOrderScheduleTableModel.
	 */
	public void updateTable() {
		//courseRollTableModel.updateData();
		openOrderScheduleTableModel.updateData();
		//initFacultySchedule();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		openOrderScheduleTableModel.updateData();
		this.validate();
		this.repaint();
	}
	
	
	
	
	/**
	 * {@link OpenOrderScheduleTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Orders to the user.
	 * 
	 * @author Arthur Vargas
	 */
	private class OpenOrderScheduleTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Number", "Study", "Site", "Name", "Address", "Address", "State", "Zip", "Country", "Status"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link OpenOrderScheduleTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public OpenOrderScheduleTableModel() {
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
		 * Updates the given model with {@link Order} information from the {@link CustomerSchedule}.
		 */
		public void updateData() {
			if (currentUser != null){
				data = orderSchedule.getScheduledOrders();
				//facultyScheduleTableModel.fireTableDataChanged();
				ResearchCompanyOpenOrderPanel.this.repaint();
				ResearchCompanyOpenOrderPanel.this.validate();
			}
		}	
	}

}
