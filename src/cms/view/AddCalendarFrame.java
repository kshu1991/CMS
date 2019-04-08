package cms.view;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import cms.model.CMSCalendar;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AddCalendarFrame extends JDialog {
	private JTextField nameField;
	private JTextField startDateField;
	private JTextField endDateField;
	private JTextField earliestHourField;
	private JTextField latestHourField;
	
	JComboBox durationBox;
	
	JButton createBtn;
	
	boolean updated = false;
	
	public JButton getCreateButton() {
		return createBtn;
	}
	
	public JTextField getNameField() {
		return nameField;
	}
	
	public JTextField getStartDateField() {
		return startDateField;
	}
	
	public JTextField getEndDateField() {
		return endDateField;
	}
	
	public JTextField getEarliestHourField() {
		return earliestHourField;
	}
	
	public JTextField getLatestHourField() {
		return latestHourField;
	}
	
	public JComboBox getDurationBox() {
		return durationBox;
	}
	
	public AddCalendarFrame(CMSCalendar newCalendar) {
		
		JLabel lblAddNewCalendar = new JLabel("Add New Calendar");
		
		JLabel lblName = new JLabel("Name:");
		
		JLabel lblStartingDate = new JLabel("Starting Date:");
		
		JLabel lblEndingDate = new JLabel("Ending Date:");
		
		JLabel lblEarliestHour = new JLabel("Earliest Hour:");
		
		JLabel lblLatestHour = new JLabel("Latest Hour:");
		
		JLabel lblDuration = new JLabel("Duration:");
		
		nameField = new JTextField();
		nameField.setText(newCalendar.getName());
		nameField.setColumns(10);
		
		startDateField = new JTextField();
		startDateField.setToolTipText("YYYY-M-D");
		startDateField.setColumns(10);
		
		endDateField = new JTextField();
		endDateField.setToolTipText("YYYY-M-D");
		endDateField.setColumns(10);
		
		earliestHourField = new JTextField();
		earliestHourField.setToolTipText("");
		earliestHourField.setColumns(10);
		
		latestHourField = new JTextField();
		latestHourField.setToolTipText("");
		latestHourField.setColumns(10);
		
		createBtn = new JButton("Create");
		
		String[] durationChoice = {"10 min", "15 min", "20 min", "30 min", "60 min"};
		durationBox = new JComboBox(durationChoice);
		
		JLabel lblExample = new JLabel("Example: 2018-9-24");
		
		JLabel lblExample_1 = new JLabel("Example: 2018-10-4");
		
		JLabel lblExample_2 = new JLabel("Example: 10:00");
		
		JLabel lblExample_3 = new JLabel("Example: 17:00");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAddNewCalendar)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblStartingDate)
								.addComponent(lblEndingDate)
								.addComponent(lblEarliestHour)
								.addComponent(lblLatestHour)
								.addComponent(lblDuration))
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(durationBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(latestHourField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblExample_3))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(endDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblExample_1))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(earliestHourField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblExample_2))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(startDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblExample))
								.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(123, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(360, Short.MAX_VALUE)
					.addComponent(createBtn)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAddNewCalendar)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartingDate)
						.addComponent(startDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExample))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndingDate)
						.addComponent(endDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExample_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEarliestHour)
						.addComponent(earliestHourField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExample_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLatestHour)
						.addComponent(latestHourField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExample_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDuration)
						.addComponent(durationBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(createBtn)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updated = true;
				String name = nameField.getText();
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "Name should not be empty");
					updated = false;
				}
				LocalDate startdate;
				LocalDate enddate;
				LocalTime starttime;
				LocalTime endtime;
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
					startdate = LocalDate.parse(startDateField.getText(), formatter);
					enddate = LocalDate.parse(endDateField.getText(), formatter);
					if (enddate.isBefore(startdate)) {
						JOptionPane.showMessageDialog(null, "End date should not be earlier than start date.");
						updated = false;
					}
				} catch(Exception except) {
					JOptionPane.showMessageDialog(null, "Date format should be yyyy-M-d");
					updated = false;
				}
				try {
					starttime = LocalTime.parse(earliestHourField.getText());
					endtime = LocalTime.parse(latestHourField.getText());
					if (endtime.isBefore(starttime)) {
						JOptionPane.showMessageDialog(null, "Latest time should not be earlier than earliest time.");
						updated = false;
					}
				} catch(Exception except) {
					JOptionPane.showMessageDialog(null, "Time format should be HH:MM");
					updated = false;
				}
				if (updated) {
					AddCalendarFrame.this.setVisible(false);
				}
			}
		});
	}
	
	public boolean wasUpdated() {
		return updated;
	}
}
