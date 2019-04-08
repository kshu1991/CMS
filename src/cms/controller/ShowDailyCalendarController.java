package cms.controller;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import cms.model.CMSCalendar;
import cms.model.CMSDate;
import cms.model.CMSMonth;
import cms.model.Timeslot;
import cms.view.TimeslotFrame;

public class ShowDailyCalendarController {
	CMSCalendar calendar;
	TimeslotFrame frame;
	
	public ShowDailyCalendarController(CMSCalendar calendar, TimeslotFrame frame) {
		this.calendar = calendar;
		this.frame = frame;
	}
	
	public void process() {
		ArrayList<CMSMonth> monthsList = calendar.getCMSMonths();
		if (monthsList.size() > 0) {
			CMSMonth firstMonth = monthsList.get(0);
			CMSDate firstDate = firstMonth.getCMSDates().get(0);
			frame.setCurrentDate(firstDate.getDate());
			DefaultListModel<Timeslot> lm = (DefaultListModel<Timeslot>) frame.getTimeslotList().getModel();
			lm.removeAllElements();
			for (Timeslot tm: firstDate.getTimeslots()) {
				lm.addElement(tm);
			}
		}
	}
}
