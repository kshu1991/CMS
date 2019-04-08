package cms.controller;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import cms.model.CMSCalendar;
import cms.model.CMSDate;
import cms.model.CMSMonth;
import cms.model.Timeslot;
import cms.view.SettingFrame;
import cms.view.TimeslotFrame;

public class SettingController {
	CMSCalendar calendar;
	TimeslotFrame frame;
	
	public SettingController(CMSCalendar calendar, TimeslotFrame frame) {
		this.calendar = calendar;
		this.frame = frame;
	}
	
	public void process() {
		SettingFrame sf = new SettingFrame(calendar);
		
		//	set year month combobox
		ArrayList<CMSMonth> monthsList = calendar.getCMSMonths();
		ArrayList<String> yearMonthBox = new ArrayList<String>();
		yearMonthBox.add("None");
		for (CMSMonth month: monthsList) {
			yearMonthBox.add(month.getYear() + "-" + (month.getMonth()));
		}
		DefaultComboBoxModel bm = new DefaultComboBoxModel(yearMonthBox.toArray());
		sf.getYearMonthBox().setModel(bm);
		
		//	set day of week combobox
		String[] dayOfWeek = {"None", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		DefaultComboBoxModel dowbm = new DefaultComboBoxModel(dayOfWeek);
		sf.getDayOfWeekBox().setModel(dowbm);
		
		//	set timeslot combobox
		LocalTime starttime = calendar.getStartTime();
		LocalTime endtime = calendar.getEndTime();
		ArrayList<String> timeBox = new ArrayList<String>();
		timeBox.add("All");
		for (LocalTime time = starttime; time.isBefore(endtime); time = time.plusMinutes(calendar.getDuration())) {
			timeBox.add(time.toString() + "-" + time.plusMinutes(calendar.getDuration()));
		}
		DefaultComboBoxModel tbm = new DefaultComboBoxModel(timeBox.toArray());
		sf.getTimeBox().setModel(tbm);
		
		sf.setSize(500, 320);
		sf.setModal(true);
		sf.setVisible(true);
		
		if (sf.wasUpdated()) {
			int yearIndex = sf.getYearMonthBox().getSelectedIndex();
			int dayIndex = sf.getDayBox().getSelectedIndex();
			int dayofweekIndex = sf.getDayOfWeekBox().getSelectedIndex();
			int timeIndex = sf.getTimeBox().getSelectedIndex();
			
			if (yearIndex > 0) {
				CMSDate date = calendar.getCMSMonths().get(yearIndex - 1).getCMSDates().get(dayIndex);
				if (timeIndex == 0) {
					for (Timeslot timeslot: date.getTimeslots()) {
						if (timeslot.getPerson().length() == 0) {
							timeslot.setStatus(-1);
						}
					}
				}
				else {
					Timeslot timeslot = date.getTimeslots().get(timeIndex - 1);
					if (timeslot.getPerson().equals("")) {
						timeslot.setStatus(-1);
					}
				}
			}
			else if (dayofweekIndex > 0) {
				ArrayList<CMSDate> datesList = calendar.getAllCMSDates();
				for (CMSDate date: datesList) {
					if (date.getDate().getDayOfWeek() == DayOfWeek.of(dayofweekIndex)) {
						if (timeIndex == 0) {
							for (Timeslot timeslot: date.getTimeslots()) {
								if (timeslot.getPerson().length() == 0) {
									timeslot.setStatus(-1);
								}
							}
						}
						else {
							Timeslot timeslot = date.getTimeslots().get(timeIndex - 1);
							if (timeslot.getPerson().length() == 0) {
								timeslot.setStatus(-1);
							}
						}
					}
				}
			}
			else {
				ArrayList<CMSDate> datesList = calendar.getAllCMSDates();
				for (CMSDate date: datesList) {
					if (timeIndex == 0) {
						for (Timeslot timeslot: date.getTimeslots()) {
							if (timeslot.getPerson().length() == 0) {
								timeslot.setStatus(-1);
							}
						}
					}
					else {
						Timeslot timeslot = date.getTimeslots().get(timeIndex - 1);
						if (timeslot.getPerson().length() == 0) {
							timeslot.setStatus(-1);
						}
					}
				}
			}
		}
		
		sf.dispose();
	}
}
