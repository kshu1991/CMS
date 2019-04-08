package cms.controller;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import cms.model.CMSCalendar;
import cms.model.CMSDate;
import cms.model.CMSMonth;
import cms.model.Model;
import cms.model.Timeslot;
import cms.view.CalendarFrame;
import cms.view.TimeslotFrame;

public class OpenCalendarController {
	Model model;
	CalendarFrame frame;
	
	public OpenCalendarController(Model model, CalendarFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void process() {
		JList<CMSCalendar> calendarsList = frame.getCalendarsList();
		int idx = calendarsList.getSelectedIndex();
		if (idx < 0) {
			return;
		}
		
		CMSCalendar calendar = calendarsList.getSelectedValue();
		TimeslotFrame tsf = new TimeslotFrame(calendar);
		
		JList<Timeslot> list = tsf.getTimeslotList();
		list.setModel(new DefaultListModel<Timeslot>());
		ArrayList<CMSMonth> monthsList = calendar.getCMSMonths();
		if (monthsList.size() > 0) {
			CMSMonth firstMonth = monthsList.get(0);
			CMSDate firstDay = firstMonth.getCMSDates().get(0);
			tsf.setCurrentDate(firstDay.getDate());
			DefaultListModel<Timeslot> lm = (DefaultListModel<Timeslot>) list.getModel();
			for (Timeslot tm: firstDay.getTimeslots()) {
				lm.addElement(tm);
			}
		}
		
		tsf.setSize(700, 320);
		tsf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tsf.setVisible(true);
	}
}
