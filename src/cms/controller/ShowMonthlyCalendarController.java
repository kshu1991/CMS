package cms.controller;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import cms.model.CMSCalendar;
import cms.model.CMSDate;
import cms.model.CMSMonth;
import cms.model.Timeslot;
import cms.view.TimeslotFrame;

public class ShowMonthlyCalendarController {
	CMSCalendar calendar;
	TimeslotFrame frame;
	
	public ShowMonthlyCalendarController(CMSCalendar calendar, TimeslotFrame frame) {
		this.calendar = calendar;
		this.frame = frame;
	}
	
	public void process() {
		ArrayList<CMSMonth> monthsList = calendar.getCMSMonths();
		if (monthsList.size() > 0) {
			CMSMonth month = monthsList.get(0);
			DefaultListModel<Timeslot> lm = (DefaultListModel<Timeslot>) frame.getTimeslotList().getModel();
			lm.removeAllElements();
			for (CMSDate date: month.getCMSDates()) {
				for (Timeslot tm: date.getTimeslots()) {
					lm.addElement(tm);
				}
			}
		}
	}
}
