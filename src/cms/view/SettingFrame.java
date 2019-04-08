package cms.view;

import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import cms.model.CMSCalendar;
import cms.model.CMSDate;
import cms.model.CMSMonth;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SettingFrame extends JDialog {
	private JComboBox yearMonthBox, dayBox, dayOfWeekBox, timeBox;
	private JButton closeBtn;
	private JButton resetBtn;
	
	public JComboBox getYearMonthBox() { return yearMonthBox; }
	public JComboBox getDayBox() { return dayBox; }
	public JComboBox getDayOfWeekBox() { return dayOfWeekBox; }
	public JComboBox getTimeBox() { return timeBox; }
	
	int stage = 0;
	JComboBox lastSelect = null;
	private boolean updated = false;
	
	public boolean wasUpdated() { return updated; }
	public int getStage() { return stage; }
	
	
	public SettingFrame(CMSCalendar calendar) {
		
		JLabel lblYearMonth = new JLabel("Year-Month:");
		
		JLabel lblDay = new JLabel("Day:");
		
		JLabel lblTimeslot = new JLabel("Day of Week:");
		
		JLabel lblTime = new JLabel("Time:");
		
		yearMonthBox = new JComboBox();
		
		dayBox = new JComboBox();
		dayBox.setEnabled(false);
		
		dayOfWeekBox = new JComboBox();
		
		timeBox = new JComboBox();
		
		closeBtn = new JButton("Close Timeslot");
		closeBtn.setEnabled(false);
		
		resetBtn = new JButton("Reset");
		resetBtn.setEnabled(false);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(222, Short.MAX_VALUE)
					.addComponent(resetBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(closeBtn)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblYearMonth)
						.addComponent(lblTimeslot)
						.addComponent(lblDay)
						.addComponent(lblTime))
					.addGap(79)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(yearMonthBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(timeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(dayBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(dayOfWeekBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(231, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYearMonth)
						.addComponent(yearMonthBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDay)
						.addComponent(dayBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTimeslot)
						.addComponent(dayOfWeekBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(timeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTime))
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(closeBtn)
						.addComponent(resetBtn))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		yearMonthBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(stage + " " + lastSelect);
				if (lastSelect != yearMonthBox) {
					stage++;
					lastSelect = yearMonthBox;
				}
				if (stage == 1) {
					dayBox.setEnabled(true);
					dayOfWeekBox.setEnabled(false);
					timeBox.setEnabled(false);
					resetBtn.setEnabled(true);
				}
				
				
				//	set day combo box
				int index = yearMonthBox.getSelectedIndex() - 1;
				if (index != -1) {
					CMSMonth month = calendar.getCMSMonths().get(index);
					ArrayList<Integer> dates = new ArrayList<Integer>();
					for (CMSDate date: month.getCMSDates()) {
						dates.add(date.getDate().getDayOfMonth());
					}
					DefaultComboBoxModel dbm = new DefaultComboBoxModel(dates.toArray());
					dayBox.setModel(dbm);
				}
				else {
					DefaultComboBoxModel dbm = (DefaultComboBoxModel) dayBox.getModel();
					dbm.removeAllElements();
					dbm.addElement("...");
				}
				
			}
		});
		
		dayBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(stage + " " + lastSelect);
				if (lastSelect != dayBox) {
					stage++;
					lastSelect = dayBox;
				}
				if (stage == 2) {
					timeBox.setEnabled(true);
					resetBtn.setEnabled(true);
					closeBtn.setEnabled(true);
				}
			}
		});
		
		dayOfWeekBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(stage + " " + lastSelect);
				if (lastSelect != dayOfWeekBox) {
					stage++;
					lastSelect = dayOfWeekBox;
				}
				if (stage == 1) {
					yearMonthBox.setEnabled(false);
					dayBox.setEnabled(false);
					resetBtn.setEnabled(true);
					closeBtn.setEnabled(true);
				}
			}
		});
		
		timeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(stage + " " + lastSelect);
				if (lastSelect != timeBox) {
					stage++;
					lastSelect = timeBox;
				}
				if (stage == 1) {
					yearMonthBox.setEnabled(false);
					dayBox.setEditable(false);
					dayOfWeekBox.setEnabled(false);
					resetBtn.setEnabled(true);
					closeBtn.setEnabled(true);
				}
				if (stage == 2) {
					yearMonthBox.setEnabled(false);
					dayBox.setEditable(false);
					resetBtn.setEnabled(true);
					closeBtn.setEnabled(true);
				}
			}
		});
		
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(stage + " " + lastSelect);
				yearMonthBox.setEnabled(true);
				yearMonthBox.setSelectedIndex(0);
				dayBox.setEnabled(false);
				dayBox.setSelectedItem(0);
				dayOfWeekBox.setEnabled(true);
				dayOfWeekBox.setSelectedIndex(0);
				timeBox.setEnabled(true);
				timeBox.setSelectedIndex(0);
				resetBtn.setEnabled(false);
				closeBtn.setEnabled(false);
				stage = 0;
				lastSelect = null;
			}
		});
		
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updated = true;
				SettingFrame.this.setVisible(false);
			}
		});
	}
}
