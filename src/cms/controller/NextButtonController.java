package cms.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;

import cms.model.CMSCalendar;
import cms.model.CMSDate;
import cms.model.CMSMonth;
import cms.model.Timeslot;
import cms.view.TimeslotFrame;

public class NextButtonController {
	CMSCalendar calendar;
	TimeslotFrame frame;
	
	public NextButtonController(CMSCalendar calendar, TimeslotFrame frame) {
		this.calendar = calendar;
		this.frame = frame;
	}
	
	public void process() {
		JRadioButton dailyBtn = frame.getDailyRadioButton();
		LocalDate currentDate = frame.getCurrentDate();
		
		if (dailyBtn.isSelected()) {
			ArrayList<CMSDate> datesList = calendar.getAllCMSDates();
			for (CMSDate date: datesList) {
				if (currentDate.isEqual(date.getDate())) {
					int index = datesList.indexOf(date);
					if (index < datesList.size() - 1) {
						index++;
						CMSDate prevDate = datesList.get(index);
						DefaultListModel<Timeslot> lm = (DefaultListModel<Timeslot>) frame.getTimeslotList().getModel();
						lm.removeAllElements();
						for (Timeslot tm: prevDate.getTimeslots()) {
							lm.addElement(tm);
						}
						frame.setCurrentDate(prevDate.getDate());
					}
				}
			}
		}
		else {
			ArrayList<CMSMonth> monthsList = calendar.getCMSMonths();
			for (CMSMonth month: monthsList) {
				if (currentDate.getMonthValue() == month.getMonth()) {
					int index = monthsList.indexOf(month);
					if (index < monthsList.size() - 1) {
						index++;
						CMSMonth nextMonth = monthsList.get(index);
						DefaultListModel<Timeslot> lm = (DefaultListModel<Timeslot>) frame.getTimeslotList().getModel();
						lm.removeAllElements();
						for (CMSDate date: nextMonth.getCMSDates()) {
							for (Timeslot tm: date.getTimeslots()) {
								lm.addElement(tm);
							}
						}
						frame.setCurrentDate(nextMonth.getCMSDates().get(0).getDate());
					}
				}
			}
		}
	}
}
