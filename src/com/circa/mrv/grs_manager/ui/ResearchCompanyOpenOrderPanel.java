/**
 * 
 */
package com.circa.mrv.grs_manager.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.circa.mrv.grs_manager.niox.Product;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.user.schedule.OrderSchedule;

/**
 * 
 * @author Arthur Vargas
 */
public class ResearchCompanyOpenOrderPanel extends JPanel implements ActionListener {
	/** The order schedule */
	OrderSchedule orderSchedule;
	/** The user currently logged in */
    private Employee currentUser;
	

	/**
	 * 
	 */
	public ResearchCompanyOpenOrderPanel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
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
