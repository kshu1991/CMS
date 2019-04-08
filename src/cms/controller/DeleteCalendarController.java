package cms.controller;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import cms.model.CMSCalendar;
import cms.model.Model;
import cms.view.CalendarFrame;

public class DeleteCalendarController {
	Model model;
	CalendarFrame frame;
	
	public DeleteCalendarController(Model model, CalendarFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void process() {
		JList<CMSCalendar> calendarsList = frame.getCalendarsList();
		int idx = calendarsList.getSelectedIndex();
		if (idx < 0) {
			return;
		}
		
		CMSCalendar c = calendarsList.getSelectedValue();
		DefaultListModel<CMSCalendar> lm = (DefaultListModel<CMSCalendar>) calendarsList.getModel();
		model.removeCalendar(c.getName());
		lm.remove(idx);
		
		frame.repaint();
	}
}
