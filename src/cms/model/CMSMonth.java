package cms.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class CMSMonth {
	ArrayList<CMSDate> dates = new ArrayList<CMSDate>();
	LocalDate cmsmonth;
	int year;
	int month;
	
	public LocalDate getCMSMonth() { return cmsmonth; }
	public int getYear() { return year; }
	public int getMonth() { return month; }
	public ArrayList<CMSDate> getCMSDates() { return dates; }
	
	public CMSMonth(LocalDate month) {
		this.cmsmonth = month;
		this.year = month.getYear();
		this.month = month.getMonthValue();
	}
	
	public void addCMSDates(CMSDate date) {
		dates.add(date);
	}
}
