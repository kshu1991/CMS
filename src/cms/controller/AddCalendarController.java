package cms.controller;

import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import cms.model.CMSCalendar;
import cms.model.CMSDate;
import cms.model.Model;
import cms.model.Timeslot;
import cms.view.AddCalendarFrame;
import cms.view.CalendarFrame;

public class AddCalendarController {
	Model model;
	CalendarFrame frame;
	int[] durationMap = {10, 15, 20, 30, 60};
	
	public AddCalendarController(Model model, CalendarFrame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public void process() {
		JList<CMSCalendar> calendarsList = frame.getCalendarsList();
		calendarsList.setModel(new DefaultListModel<CMSCalendar>());
		CMSCalendar newCalendar = new CMSCalendar();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
		
		AddCalendarFrame acf = new AddCalendarFrame(newCalendar);
		acf.setSize(500, 320);
		acf.setModal(true);
		acf.setVisible(true);
		
		if (acf.wasUpdated()) {
			String name = acf.getNameField().getText();
			int duration = durationMap[acf.getDurationBox().getSelectedIndex()];
			LocalDate startDate = LocalDate.parse(acf.getStartDateField().getText(), formatter);
			LocalDate endDate = LocalDate.parse(acf.getEndDateField().getText(), formatter);
			LocalTime earliestHour = LocalTime.parse(acf.getEarliestHourField().getText());
			LocalTime latestHour = LocalTime.parse(acf.getLatestHourField().getText());
			
			newCalendar.setName(name);
			newCalendar.setDuration(duration);
			newCalendar.setStartTime(earliestHour);
			newCalendar.setEndTime(latestHour);
			for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
				if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
					continue;
				}
				generateTimeslot(newCalendar, date, earliestHour, latestHour);
			}
			
			model.addCalendar(newCalendar);
			
			DefaultListModel<CMSCalendar> lm = (DefaultListModel<CMSCalendar>) calendarsList.getModel();
			lm.removeAllElements();
			for (CMSCalendar calendar: model.getCalendars()) {
				lm.addElement(calendar);
			}
		}
		
		acf.dispose();
		frame.repaint();
	}
	
	public void generateTimeslot(CMSCalendar calendar, LocalDate date, LocalTime start, LocalTime end) {
		CMSDate cmsDate = new CMSDate(date);
		int duration = calendar.getDuration();
		
		for (LocalTime time = start; !time.plusMinutes(duration).isAfter(end); time = time.plusMinutes(duration)) {
			Timeslot timeslot = new Timeslot(date, time, duration, "", "");
			cmsDate.addTimeslots(timeslot);
		}
		calendar.addCMSDates(cmsDate);
	}
}
