package cms.model;

import java.time.LocalTime;
import java.util.*;

public class CMSCalendar {
	ArrayList<CMSMonth> months = new ArrayList<CMSMonth>();
	String name;
	int duration;
	LocalTime start, end;
	
	public String getName() { return name; }
	public int getDuration() { return duration; }
	public LocalTime getStartTime() {return start; }
	public LocalTime getEndTime() {return end; }
	
	public CMSCalendar() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void setStartTime(LocalTime start) {
		this.start = start;
	}
	
	public void setEndTime(LocalTime end) {
		this.end = end;
	}
	
	public void addCMSMonths(CMSMonth month) {
		months.add(month);
	}
	
	public void addCMSDates(CMSDate date) {
		for (CMSMonth month: months) {
			if (month.getMonth() == date.getDate().getMonthValue()) {
				month.addCMSDates(date);
				return;
			}
		}
		CMSMonth month = new CMSMonth(date.getDate());
		month.addCMSDates(date);
		months.add(month);
	}
	
	public ArrayList<CMSMonth> getCMSMonths() {
		return months;
	}
	
	public ArrayList<CMSDate> getAllCMSDates() {
		ArrayList<CMSDate> dates = new ArrayList<CMSDate>();
		for (CMSMonth month: months) {
			for (CMSDate date: month.getCMSDates()) {
				dates.add(date);
			}
		}
		return dates;
	}
	
	public String toString() {
		return name;
	}
}
