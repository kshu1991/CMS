package cms.model;

import java.time.LocalDate;
import java.util.*;

public class CMSDate {
	ArrayList<Timeslot> timeslots = new ArrayList<Timeslot>();
	LocalDate date;
	
	public LocalDate getDate() { return date; }
	
	public CMSDate(LocalDate date) {
		this.date = date;
	}
	
	public void addTimeslots(Timeslot timeslot) {
		timeslots.add(timeslot);
	}
	
	public ArrayList<Timeslot> getTimeslots() {
		return timeslots;
	}
}
