package cms.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import cms.model.CMSCalendar;
import cms.model.CMSDate;
import cms.model.CMSMonth;
import cms.model.Timeslot;
import cms.view.AddRemoveDayFrame;
import cms.view.TimeslotFrame;

public class AddRemoveDayController {
	CMSCalendar calendar;
	TimeslotFrame frame;
	
	public AddRemoveDayController(CMSCalendar calendar, TimeslotFrame frame) {
		this.calendar = calendar;
		this.frame = frame;
	}
	
	public void process() {
		AddRemoveDayFrame ardf = new AddRemoveDayFrame(calendar);
		ardf.setSize(500,320);
		ardf.setModal(true);
		ardf.setVisible(true);
		
		if (ardf.wasUpdated()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
			LocalDate modDate = LocalDate.parse(ardf.getDateField().getText(), formatter);
			ArrayList<CMSMonth> monthsList = calendar.getCMSMonths();
			
			if (modDate.getDayOfWeek() != DayOfWeek.SATURDAY && modDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
				if (ardf.getAddRdbtn().isSelected()) {
					CMSDate newdate = new CMSDate(modDate);
					for (LocalTime start = calendar.getStartTime(); start.isBefore(calendar.getEndTime()); start = start.plusMinutes(calendar.getDuration())) {
						Timeslot timeslot = new Timeslot(modDate, start, calendar.getDuration(), "", "");
						newdate.addTimeslots(timeslot);
					}
					
					boolean added = false;
					for (CMSMonth month: monthsList) {
						if (modDate.isBefore(month.getCMSMonth().withDayOfMonth(month.getCMSMonth().lengthOfMonth()))) {
							if (modDate.getMonthValue() == month.getMonth()) {
								for (CMSDate date: month.getCMSDates()) {
									if (modDate.isBefore(date.getDate())) {
										int index = month.getCMSDates().indexOf(date);
										month.getCMSDates().add(index, newdate);
										added = true;
										break;
									}
								}
								if (!added) {
									month.getCMSDates().add(newdate);
									added = true;
									break;
								}
							}
							else {
								CMSMonth newMonth = new CMSMonth(modDate);
								newMonth.addCMSDates(newdate);
								int index = monthsList.indexOf(month);
								monthsList.add(index, newMonth);
								added = true;
								break;
							}
						}
					}
					if (!added) {
						CMSMonth newMonth = new CMSMonth(modDate);
						newMonth.addCMSDates(newdate);
						monthsList.add(newMonth);
						added = true;
					}
				}
				if (ardf.getremoveRdbtn().isSelected()) {
					for (CMSMonth month: monthsList) {
						if (month.getYear() == modDate.getYear() && month.getMonth() == modDate.getMonthValue()) {
							for (CMSDate date: month.getCMSDates()) {
								if (date.getDate().equals(modDate)) {
									month.getCMSDates().remove(date);
									if (month.getCMSDates().size() == 0) {
										monthsList.remove(month);
									}
									break;
								}
							}
						}
					}
				}
			}
		}
		ardf.dispose();
		frame.repaint();
	}
}
