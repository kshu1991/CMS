package cms.view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import cms.controller.AddRemoveDayController;
import cms.controller.NextButtonController;
import cms.controller.PrevButtonController;
import cms.controller.SettingController;
import cms.controller.ShowDailyCalendarController;
import cms.controller.ShowDetailController;
import cms.controller.ShowMonthlyCalendarController;
import cms.model.CMSCalendar;
import cms.model.CMSDate;
import cms.model.Timeslot;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TimeslotFrame extends JFrame {
	CMSCalendar calendar;
	LocalDate currentDate;
	
	JButton detailBtn;
	JButton settingBtn;
	JButton prevBtn;
	JButton nextBtn;
	JRadioButton dailyRdbtn;
	JRadioButton monthlyRdbtn;
	JList<Timeslot> list;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnAddremoveDay;
	
	public ButtonGroup getButtonGroup() { return buttonGroup; }
	public JButton getPrevButton() { return prevBtn; }
	public JButton getNextButton() { return nextBtn; }
	public JButton getDetailButton() { return detailBtn; }
	public JButton getSettingButton() { return settingBtn; }
	public JRadioButton getDailyRadioButton() { return dailyRdbtn; }
	public JRadioButton getMonthlyRadioButton() { return monthlyRdbtn; }
	public JList<Timeslot> getTimeslotList() { return list; }
	
	public void setCurrentDate(LocalDate date) { currentDate = date; }
	public LocalDate getCurrentDate() { return currentDate; }
	
	
	public TimeslotFrame(CMSCalendar calendar) {
		this.calendar = calendar;
		
		JScrollPane scrollPane = new JScrollPane();
		
		detailBtn = new JButton("Detail");
		
		dailyRdbtn = new JRadioButton("Daily View");
		dailyRdbtn.setSelected(true);
		buttonGroup.add(dailyRdbtn);
		
		monthlyRdbtn = new JRadioButton("Monthly View");
		buttonGroup.add(monthlyRdbtn);
		
		settingBtn = new JButton("Setting");
		
		prevBtn = new JButton("Prev");
		
		nextBtn = new JButton("Next");
		
		btnAddremoveDay = new JButton("Add/Remove Day");

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(dailyRdbtn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(monthlyRdbtn)
							.addPreferredGap(ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
							.addComponent(settingBtn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(detailBtn))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(prevBtn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nextBtn)
							.addGap(309)
							.addComponent(btnAddremoveDay)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(prevBtn)
						.addComponent(nextBtn)
						.addComponent(btnAddremoveDay))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(detailBtn)
						.addComponent(dailyRdbtn)
						.addComponent(monthlyRdbtn)
						.addComponent(settingBtn))
					.addContainerGap())
		);
		
		list = new JList<Timeslot>();
		scrollPane.setViewportView(list);
		getContentPane().setLayout(groupLayout);
		
		dailyRdbtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					new ShowDailyCalendarController(calendar, TimeslotFrame.this).process();
				}
			}
		});
		
		monthlyRdbtn.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					new ShowMonthlyCalendarController(calendar, TimeslotFrame.this).process();
				}
			}
		});
		
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NextButtonController(calendar, TimeslotFrame.this).process();
			}
		});
		
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PrevButtonController(calendar, TimeslotFrame.this).process();
			}
		});
		
		detailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowDetailController(calendar, TimeslotFrame.this).process();
			}
		});
		
		settingBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SettingController(calendar, TimeslotFrame.this).process();
			}
		});
		
		btnAddremoveDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddRemoveDayController(calendar, TimeslotFrame.this).process();
			}
		});
	}
}
