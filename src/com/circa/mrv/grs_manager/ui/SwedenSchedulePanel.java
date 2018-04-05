package com.circa.mrv.grs_manager.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import com.circa.mrv.grs_manager.directory.Product;
import com.circa.mrv.grs_manager.directory.VendorDirectory;
import com.circa.mrv.grs_manager.manager.GRSManager;
import com.circa.mrv.grs_manager.user.Employee;
import com.circa.mrv.grs_manager.user.schedule.CustomerSchedule;


/**
 * Displays all ROW (rest of world) orders for a given date. 
 * 
 * @author Ben Ioppolo. alot of copying from Sarah Heckman
 */
public class SwedenSchedulePanel extends JPanel implements ActionListener {
	/** Panel for displaying Course Details */
	private JPanel pnlCourseDetails;
	/** Label for Course Details name title */
	private JLabel lblNameTitle = new JLabel("Name: ");
	/** Label for Course Details section title */
	private JLabel lblSectionTitle = new JLabel("Section: ");
	/** Label for Course Details title title */
	private JLabel lblTitleTitle = new JLabel("Title: ");
	/** Label for Course Details instructor title */
	private JLabel lblInstructorTitle = new JLabel("Instructor: ");
	/** Label for Course Details credit hours title */
	private JLabel lblCreditsTitle = new JLabel("Credits: ");
	/** Label for Course Details meeting title */
	private JLabel lblMeetingTitle = new JLabel("Meeting: ");
	/** Label for Course Details enrollment cap title */
	private JLabel lblEnrollmentCapTitle = new JLabel("Enrollment Cap: ");
	/** Label for Course Details open seats title */
	private JLabel lblOpenSeatsTitle = new JLabel("Open Seats: ");
	/** Label for Course Details open seats title */
	private JLabel lblWaitlistTitle = new JLabel("Waitlist: ");
	/** Label for Course Details name */
	private JLabel lblName = new JLabel("1");
	/** Label for Course Details section */
	private JLabel lblSection = new JLabel("2");
	/** Label for Course Details title */
	private JLabel lblTitle = new JLabel("3");
	/** Label for Course Details instructor */
	private JLabel lblInstructor = new JLabel("4");
	/** Label for Course Details credit hours */
	private JLabel lblCredits = new JLabel("5");
	/** Label for Course Details meeting */
	private JLabel lblMeeting = new JLabel("6");
	/** Label for Course Details enrollment cap */
	private JLabel lblEnrollmentCap = new JLabel("7");
	/** Label for Course Details open seats */
	private JLabel lblOpenSeats = new JLabel("8");
	/** Label for Course Details Waitlist */
	private JLabel lblWaitlist = new JLabel("9");
	/** Panel for displaying Faculty Details */
	/** ID used for object serialization */
	private static final long serialVersionUID = 1L;
	/** JTable for displaying the faculty schedule of Courses */
	private JTable tableFacultySchedule;
	/** Scroll pane for table */
	private JScrollPane scrollFacultySchedule;
	/** TableModel for faculty schedule of Courses */
	private FacultyScheduleTableModel facultyScheduleTableModel;
	/** JTable for displaying the roll of Students */
	private JTable tableCourseRoll;
	/** Scroll pane for table */
	private JScrollPane scrollCourseRoll;
	/** TableModel for roll of Students */
	private CourseRollTableModel courseRollTableModel;
//	/** Button to logout */
//	private JButton btnLogout;
//	JPanel pnlButtons = new JPanel();
	/** Current user */
	private Employee currentUser;
	private CustomerSchedule schedule;
	
	/**
	 * Constructs the FacultySchedulePanel and sets up the GUI 
	 * components.
	 */
	public SwedenSchedulePanel() {
		super(new GridBagLayout());
		
		//RegistrationManager manager = RegistrationManager.getInstance();
		currentUser = (Employee)GRSManager.getInstance().getCurrentUser();
		if (currentUser != null)
			schedule = currentUser.getSchedule();

//		Container con = getContentPane();
//		con.setLayout(new GridBagLayout());
//		setSize(800, 800);
//		studentDirectory = RegistrationManager.getInstance().getStudentDirectory();
		
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder boarder = BorderFactory.createTitledBorder(lowerEtched, "Directory Buttons");		

//		pnlButtons.setLayout(new GridLayout(1, 1));
//		btnLogout = new JButton("Logout");
//		btnLogout.addActionListener(this);
//		pnlButtons.add(btnLogout);
		
		//Set up Course Roll table
		courseRollTableModel = new CourseRollTableModel();
		tableCourseRoll = new JTable(courseRollTableModel);
		tableCourseRoll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCourseRoll.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableCourseRoll.setFillsViewportHeight(true);
		
		scrollCourseRoll = new JScrollPane(tableCourseRoll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		boarder = BorderFactory.createTitledBorder(lowerEtched, "Course Roll");
		scrollCourseRoll.setBorder(boarder);
		
		//Set up Faculty Schedule table
		facultyScheduleTableModel = new FacultyScheduleTableModel();
		tableFacultySchedule = new JTable(facultyScheduleTableModel);
		tableFacultySchedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableFacultySchedule.setPreferredScrollableViewportSize(new Dimension(500, 500));
		tableFacultySchedule.setFillsViewportHeight(true);
		
		scrollFacultySchedule = new JScrollPane(tableFacultySchedule, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Border lowerEtched2 = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder border = BorderFactory.createTitledBorder(lowerEtched2, "Catalog Buttons");
		border = BorderFactory.createTitledBorder(lowerEtched2, "Faculty Schedule");
		scrollFacultySchedule.setBorder(border);
		
		updateTables();
		
		//Set up the course details panel
		pnlCourseDetails = new JPanel();
		pnlCourseDetails.setLayout(new GridLayout(9, 3));
		
		pnlCourseDetails.add(lblNameTitle);
		pnlCourseDetails.add(lblName);

		pnlCourseDetails.add(lblSectionTitle);
		pnlCourseDetails.add(lblSection);

		pnlCourseDetails.add(lblTitleTitle);
		pnlCourseDetails.add(lblTitle);

		pnlCourseDetails.add(lblInstructorTitle);
		pnlCourseDetails.add(lblInstructor);

		pnlCourseDetails.add(lblCreditsTitle);
		pnlCourseDetails.add(lblCredits);

		pnlCourseDetails.add(lblMeetingTitle);		
		pnlCourseDetails.add(lblMeeting);

		pnlCourseDetails.add(lblEnrollmentCapTitle);
		pnlCourseDetails.add(lblEnrollmentCap);

		pnlCourseDetails.add(lblOpenSeatsTitle);
		pnlCourseDetails.add(lblOpenSeats);

		pnlCourseDetails.add(lblWaitlistTitle);		
		pnlCourseDetails.add(lblWaitlist);
		
		TitledBorder borderCourseDetails = BorderFactory.createTitledBorder(lowerEtched, "Course Details");
		pnlCourseDetails.setBorder(borderCourseDetails);
		pnlCourseDetails.setToolTipText("Course Details");
		
		
		GridBagConstraints c = new GridBagConstraints();			
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridwidth = 1;
//		c.weightx = 1;
//		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		c.fill = GridBagConstraints.RELATIVE;
//		add(pnlButtons, c);		
		
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollFacultySchedule, c);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(pnlCourseDetails, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		add(scrollCourseRoll, c);
		//setVisible(true);
	}
	
	/**
	 * Updates the catalog and schedule tables.
	 */
	public void updateTables() {
		//courseRollTableModel.updateData();
		facultyScheduleTableModel.updateData();
		//initFacultySchedule();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {

		courseRollTableModel.updateData();
		facultyScheduleTableModel.updateData();
		
		this.validate();
		this.repaint();
	}
	
	
//	private void initFacultySchedule(){
//		if (currentUser != null){
//			for (int i = 0 ; i < schedule.getNumScheduledCourses() ; i++)
//				facultyScheduleTableModel.setValueAt(schedule.getScheduledCourses()[i][i], i, i);
//		}
//	}
//	
	
	/**
	 * {@link CourseRollTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Students to the user.
	 * @author Sarah Heckman
	 */
	private class CourseRollTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"First Name", "Last Name", "Student ID"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link CourseRollTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public CourseRollTableModel() {
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
			//data[row][col] = value;
			//fireTableCellUpdated(row, col);
		}
		
		/**
		 * Updates the given model with {@link Product} information from the {@link VendorDirectory}.
		 */
		public void updateData() {
			//data = currentUser.getSchedule().getScheduledCourses();
		}
	}

	/**
	 * {@link FacultyScheduleTableModel} is the object underlying the {@link JTable} object that displays
	 * the list of Courses to the user.
	 * @author Sarah Heckman
	 */
	private class FacultyScheduleTableModel extends AbstractTableModel {
		
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Column names for the table */
		private String [] columnNames = {"Name", "Section", "Title", "Meeting Information", "Open Seats"};
		/** Data stored in the table */
		private Object [][] data;
		
		/**
		 * Constructs the {@link FacultyScheduleTableModel} by requesting the latest information
		 * from the {@link RequirementTrackerModel}.
		 */
		public FacultyScheduleTableModel() {
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
		 * Updates the given model with {@link Mino} information from the {@link CustomerSchedule}.
		 */
		public void updateData() {
			if (currentUser != null){
				data = schedule.getScheduledCourses();
				//facultyScheduleTableModel.fireTableDataChanged();
				SwedenSchedulePanel.this.repaint();
				SwedenSchedulePanel.this.validate();
			}
		}	
	}
}