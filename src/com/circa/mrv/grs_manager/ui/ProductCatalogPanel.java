package com.circa.mrv.grs_manager.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import javax.swing.table.AbstractTableModel;

import com.circa.mrv.grs_manager.catalog.NioxCatalog;
import com.circa.mrv.grs_manager.manager.GRSManager;

/**
 * Creates a user interface for working with the ProductCatalog.
 * 
 * @author Arthur Vargas
 */
public class ProductCatalogPanel extends JPanel implements ActionListener {
	
	/** ID used for object serialization */
	private static final long serialVersionUID = 1L;

	/** Button for resetting the catalog */
	private JButton btnNewProductCatalog;
	/** Button for resetting the catalog */
	private JButton btnLoadProductCatalog;
	/** Button for displaying the final catalog */
	private JButton btnSaveProductCatalog;
	/** JTable for displaying the catalog of Products */
	private JTable tableProductCatalog;
	/** Scroll pane for table */
	private JScrollPane scrollProductCatalog;
	/** TableModel for catalog of Products */
	private ProductCatalogTableModel productCatalogTableModel;
	/** JLabel for name */
	private JLabel lblName;
	/** JLabel for description */
	private JLabel lblDescription;
	/** JLabel for part number */
	private JLabel lblPartNumber;
	/** JLabel for price */
	private JLabel lblPrice;

	/** JTextField for name */
	private JTextField txtName;
	/** JTextField for title */
	private JTextField txtDescription;
	/** JTextField for part-number */
	private JTextField txtPartNumber;
	/** JTextField for section */
	//private JTextField txtPrice;
	
	/** Check box for Monday */
	//private JCheckBox cbMonday;
	/** Check box for Tuesday */
	//private JCheckBox cbTuesday;
	/** Check box for Wednesday */
	//private JCheckBox cbWednesday;
	/** Check box for Thursday */
	//private JCheckBox cbThursday;
	/** Check box for Friday */
	//private JCheckBox cbFriday;
	/** Check box for Arranged */
	//private JCheckBox cbArranged;
	/** Drop down for start hour */
	//private JComboBox<Integer> comboStartHour;
	/** Drop down for start minute */
	//private JComboBox<Integer> comboStartMin;
	/** Drop down for start am/pm */
	//private JComboBox<String> comboStartPeriod;
	/** Drop down for end hour */
	//private JComboBox<Integer> comboEndHour;
	/** Drop down for end minute */
	//private JComboBox<Integer> comboEndMin;
	/** Drop down for end am/pm */
	//private JComboBox<String> comboEndPeriod;
	/** Drop down for credits */
	private JComboBox<Double> comboPrices;
	/** Button for adding a course */
	private JButton btnAddProduct;
	/** Button for removing the selected Course from the catalog */
	private JButton btnRemoveProduct;
	/** Reference to CourseCatalog */
	private NioxCatalog catalog;
	
	/**
	 * Constructs the ProductCatalogPanel and sets up the GUI 
	 * components.
	 */
	public ProductCatalogPanel() {
		super(new GridBagLayout());
		
		catalog = GRSManager.getInstance().getNioxCatalog();
		
		//Set up Catalog buttons
		btnNewProductCatalog = new JButton("New Product Catalog");
		btnNewProductCatalog.addActionListener(this);
		btnLoadProductCatalog = new JButton("Load Product Catalog");
		btnLoadProductCatalog.addActionListener(this);
		btnSaveProductCatalog = new JButton("Save Product Catalog");
		btnSaveProductCatalog.addActionListener(this);
		
		JPanel pnlCatalogButton = new JPanel();
		pnlCatalogButton.setLayout(new GridLayout(1, 3));
		pnlCatalogButton.add(btnNewProductCatalog);
		pnlCatalogButton.add(btnLoadProductCatalog);
		pnlCatalogButton.add(btnSaveProductCatalog);
		
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder border = BorderFactory.createTitledBorder(lowerEtched, "Catalog Buttons");
		pnlCatalogButton.setBorder(border);
		pnlCatalogButton.setToolTipText("Catalog Buttons");
		
		//Set up Catalog table
		productCatalogTableModel = new ProductCatalogTableModel();
		tableProductCatalog = new JTable(productCatalogTableModel);
		tableProductCatalog.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableProductCatalog.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableProductCatalog.setFillsViewportHeight(true);
		
		scrollProductCatalog = new JScrollPane(tableProductCatalog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		border = BorderFactory.createTitledBorder(lowerEtched, "Product Catalog");
		scrollProductCatalog.setBorder(border);
		scrollProductCatalog.setToolTipText("Product Catalog");
		
		//Set up Course buttons
		btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(this);
		btnRemoveProduct = new JButton("Remove Product");
		btnRemoveProduct.addActionListener(this);
		
		JPanel pnlAddRemoveProductBtn = new JPanel();
		pnlAddRemoveProductBtn.setLayout(new GridLayout(1, 2));
		pnlAddRemoveProductBtn.add(btnAddProduct);
		pnlAddRemoveProductBtn.add(btnRemoveProduct);
		
		border = BorderFactory.createTitledBorder(lowerEtched, "Add and Remove Products");
		pnlAddRemoveProductBtn.setBorder(border);
		pnlAddRemoveProductBtn.setToolTipText("Product Controls");
		
		lblName = new JLabel("Product Family");
		lblDescription = new JLabel("Product Generation");
		lblPartNumber = new JLabel("Part Number");
		lblPrice = new JLabel("Price");

		txtName = new JTextField(20);
		txtDescription = new JTextField(20);
		txtPartNumber = new JTextField(20);
		//txtPrice = new JTextField(20);

		comboPrices = new JComboBox<Double>();
		comboPrices.addItem(Double.valueOf(100.00));
		comboPrices.addItem(Double.valueOf(200.00));
		comboPrices.addItem(Double.valueOf(300.00));
		comboPrices.addItem(Double.valueOf(400.99));
		comboPrices.addItem(Double.valueOf(599.99));
		
		/*
		JPanel pnlDays = new JPanel(new GridLayout(1, 15));
		pnlDays.add(new JLabel("Mon"));
		cbMonday = new JCheckBox();
		pnlDays.add(cbMonday);
		pnlDays.add(new JLabel("Tue"));
		cbTuesday = new JCheckBox();
		pnlDays.add(cbTuesday);
		pnlDays.add(new JLabel("Wed"));
		cbWednesday = new JCheckBox();
		pnlDays.add(cbWednesday);
		pnlDays.add(new JLabel("Thu"));
		cbThursday = new JCheckBox();
		pnlDays.add(cbThursday);
		pnlDays.add(new JLabel("Fri"));
		cbFriday = new JCheckBox();
		pnlDays.add(cbFriday);
		pnlDays.add(new JLabel("Arg"));
		cbArranged = new JCheckBox();
		pnlDays.add(cbArranged);*/
		
		/*
		JPanel pnlTime = new JPanel(new GridLayout(1, 2));
		JPanel pnlStartTime = new JPanel(new GridLayout(1, 4));
		JPanel pnlEndTime = new JPanel(new GridLayout(1, 4));
		
		comboStartHour = new JComboBox<Integer>();
		comboStartHour.addItem(Integer.valueOf(1));
		comboStartHour.addItem(Integer.valueOf(2));
		comboStartHour.addItem(Integer.valueOf(3));
		comboStartHour.addItem(Integer.valueOf(4));
		comboStartHour.addItem(Integer.valueOf(5));
		comboStartHour.addItem(Integer.valueOf(6));
		comboStartHour.addItem(Integer.valueOf(7));
		comboStartHour.addItem(Integer.valueOf(8));
		comboStartHour.addItem(Integer.valueOf(9));
		comboStartHour.addItem(Integer.valueOf(10));
		comboStartHour.addItem(Integer.valueOf(11));
		comboStartHour.addItem(Integer.valueOf(12));
		comboStartMin = new JComboBox<Integer>();
		comboStartMin.addItem(Integer.valueOf(0));
		comboStartMin.addItem(Integer.valueOf(5));
		comboStartMin.addItem(Integer.valueOf(10));
		comboStartMin.addItem(Integer.valueOf(15));
		comboStartMin.addItem(Integer.valueOf(20));
		comboStartMin.addItem(Integer.valueOf(25));
		comboStartMin.addItem(Integer.valueOf(30));
		comboStartMin.addItem(Integer.valueOf(35));
		comboStartMin.addItem(Integer.valueOf(40));
		comboStartMin.addItem(Integer.valueOf(45));
		comboStartMin.addItem(Integer.valueOf(50));
		comboStartMin.addItem(Integer.valueOf(55));
		comboStartPeriod = new JComboBox<String>();
		comboStartPeriod.addItem("AM");
		comboStartPeriod.addItem("PM");
		
		pnlStartTime.add(lblStartTime);
		pnlStartTime.add(comboStartHour);
		pnlStartTime.add(comboStartMin);
		pnlStartTime.add(comboStartPeriod);
		
		comboEndHour = new JComboBox<Integer>();
		comboEndHour.addItem(Integer.valueOf(1));
		comboEndHour.addItem(Integer.valueOf(2));
		comboEndHour.addItem(Integer.valueOf(3));
		comboEndHour.addItem(Integer.valueOf(4));
		comboEndHour.addItem(Integer.valueOf(5));
		comboEndHour.addItem(Integer.valueOf(6));
		comboEndHour.addItem(Integer.valueOf(7));
		comboEndHour.addItem(Integer.valueOf(8));
		comboEndHour.addItem(Integer.valueOf(9));
		comboEndHour.addItem(Integer.valueOf(10));
		comboEndHour.addItem(Integer.valueOf(11));
		comboEndHour.addItem(Integer.valueOf(12));
		comboEndMin = new JComboBox<Integer>();
		comboEndMin.addItem(Integer.valueOf(0));
		comboEndMin.addItem(Integer.valueOf(5));
		comboEndMin.addItem(Integer.valueOf(10));
		comboEndMin.addItem(Integer.valueOf(15));
		comboEndMin.addItem(Integer.valueOf(20));
		comboEndMin.addItem(Integer.valueOf(25));
		comboEndMin.addItem(Integer.valueOf(30));
		comboEndMin.addItem(Integer.valueOf(35));
		comboEndMin.addItem(Integer.valueOf(40));
		comboEndMin.addItem(Integer.valueOf(45));
		comboEndMin.addItem(Integer.valueOf(50));
		comboEndMin.addItem(Integer.valueOf(55));
		comboEndPeriod = new JComboBox<String>();
		comboEndPeriod.addItem("AM");
		comboEndPeriod.addItem("PM");
		
		pnlEndTime.add(lblEndTime);
		pnlEndTime.add(comboEndHour);
		pnlEndTime.add(comboEndMin);
		pnlEndTime.add(comboEndPeriod);
		
		pnlTime.add(pnlStartTime);
		pnlTime.add(pnlEndTime);*/
		
		JPanel pnlProductForm = new JPanel();
		pnlProductForm.setLayout(new GridLayout(8, 2));
		pnlProductForm.add(lblName);
		pnlProductForm.add(txtName);
		pnlProductForm.add(lblDescription);
		pnlProductForm.add(txtDescription);
		pnlProductForm.add(lblPartNumber);
		pnlProductForm.add(txtPartNumber);
		pnlProductForm.add(lblPrice);
		pnlProductForm.add(comboPrices);
		//pnlProductForm.add(lblInstructorId);
		//pnlProductForm.add(txtInstructorId);
		//pnlProductForm.add(lblEnrollmentCap);
		//pnlProductForm.add(txtEnrollmentCap);
		//pnlProductForm.add(pnlStartTime);
		//pnlProductForm.add(pnlEndTime);
		//pnlProductForm.add(lblMeetingDays);
		//pnlProductForm.add(pnlDays);
		
		border = BorderFactory.createTitledBorder(lowerEtched, "Product Information");
		pnlProductForm.setBorder(border);
		pnlProductForm.setToolTipText("Product Information");
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = .2;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(pnlCatalogButton, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(scrollProductCatalog, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = .5;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(pnlAddRemoveProductBtn, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		this.add(pnlProductForm, c);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLoadProductCatalog) {
			String fileName = getFileName(true);
			try {
				catalog.loadProductsFromFile(fileName);
				//System.out.println(catalog.allProducts());
				//productCatalogTableModel.updateData();
				//scrollProductCatalog.revalidate();
				//scrollProductCatalog.repaint();
				//productCatalogTableModel.fireTableDataChanged();
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, iae.getMessage());
			}
		} else if (e.getSource() == btnSaveProductCatalog) {
			String fileName = getFileName(false);
			try {
				catalog.saveProductCatalog(fileName);
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, iae.getMessage());
			}
		} else if (e.getSource() == btnNewProductCatalog) {
			catalog.newNioxCatalog();
			//productCatalogTableModel.updateData();
			//scrollProductCatalog.revalidate();
			//scrollProductCatalog.repaint();
			//productCatalogTableModel.fireTableDataChanged();
		} else if (e.getSource() == btnAddProduct) {
			String name = txtName.getText();
			String description = txtDescription.getText();
			String partNumber = txtPartNumber.getText();
			String price = "";
			/*int enrollmentCap = 0;
			try {
				enrollmentCap = Integer.parseInt(txtEnrollmentCap.getText());
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(this, "Enrollment capacity must be a number between 10 and 250.");
			}*/
			
			int priceIdx = comboPrices.getSelectedIndex();
			if (priceIdx == -1) {
				JOptionPane.showMessageDialog(this, "The price is invalid.");
				return;
			}
			price = Double.toString(comboPrices.getItemAt(priceIdx));
			/*
			String meetingDays = "";
			if (cbMonday.isSelected()) {
				meetingDays += "M";
			}
			if (cbTuesday.isSelected()) {
				meetingDays += "T";
			}
			if (cbWednesday.isSelected()) {
				meetingDays += "W";
			}
			if (cbThursday.isSelected()) {
				meetingDays += "H";
			}
			if (cbFriday.isSelected()) {
				meetingDays += "F";
			}
			if (cbArranged.isSelected()) {
				meetingDays += "A";
			}
			
			int startTime = 0;
			int hourIdx = comboStartHour.getSelectedIndex();
			if (hourIdx == -1) {
				JOptionPane.showMessageDialog(this, "The times are invalid.");
				return;
			}
			startTime = comboStartHour.getItemAt(hourIdx) * 100;
			int minIdx = comboStartMin.getSelectedIndex();
			if (minIdx == -1) {
				JOptionPane.showMessageDialog(this, "The times are invalid.");
				return;
			}
			startTime += comboStartMin.getItemAt(minIdx);
			int periodIdx = comboStartPeriod.getSelectedIndex();
			if (periodIdx == -1) {
				JOptionPane.showMessageDialog(this, "The times are invalid.");
				return;
			}
			if (comboStartPeriod.getItemAt(periodIdx).equals("PM") && startTime < 1200) {
				startTime += 1200;
			}
			
			int endTime = 0;
			hourIdx = comboEndHour.getSelectedIndex();
			if (hourIdx == -1) {
				JOptionPane.showMessageDialog(this, "The times are invalid.");
				return;
			}
			endTime = comboEndHour.getItemAt(hourIdx) * 100;
			minIdx = comboEndMin.getSelectedIndex();
			if (minIdx == -1) {
				JOptionPane.showMessageDialog(this, "The times are invalid.");
				return;
			}
			endTime += comboEndMin.getItemAt(minIdx);
			periodIdx = comboEndPeriod.getSelectedIndex();
			if (periodIdx == -1) {
				JOptionPane.showMessageDialog(this, "The times are invalid.");
				return;
			}
			if (comboEndPeriod.getItemAt(periodIdx).equals("PM") && endTime < 1200) {
				endTime += 1200;
			}
			*/
			try {
				catalog.addProductToCatalog(name, description, partNumber, price);
				txtName.setText("");
				txtDescription.setText("");
				txtPartNumber.setText("");
				comboPrices.setSelectedIndex(-1);	 
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this, iae.getMessage());
			}
		} else if (e.getSource() == btnRemoveProduct) {
			int row = tableProductCatalog.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "No product selected.");
			} else {
				try {
					catalog.removeProductFromCatalog(productCatalogTableModel.getValueAt(row, 0).toString(), productCatalogTableModel.getValueAt(row, 1).toString());
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(this, "No product selected.");
				}
			}
		}
		productCatalogTableModel.updateData();
		this.validate();
		this.repaint();
		productCatalogTableModel.fireTableDataChanged();
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
			fc.setDialogTitle("Load Product Catalog");
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
	 * {@link ProductCatalogTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Courses to the user.
	 * @author Arthur Vargas
	 */
	private class ProductCatalogTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Part Number", "Family", "Generation", "Description", "Price"};
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
		 * Updates the given model with {@link NIOX} information from the {@link NioxCatalog}.
		 */
		public void updateData() {
			data = catalog.getNioxCatalog();
		}
	}

}