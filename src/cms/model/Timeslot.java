package cms.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Timeslot {
	LocalDate date;
	LocalTime start_time;
	int duration;
	int status;
	String person;
	String location;
	
	public LocalDate getDate() { return date; }
	public LocalTime getStartTime() { return start_time; }
	public int getDuration() { return duration; }
	
	public String getPerson() { return person; }
	public void setPerson(String person) {
		this.person = person;
	}
	public String getLocation() { return location; }
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getStatus() { return status; }
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Timeslot(LocalDate date, LocalTime start_time, int duration, String person, String location) {
		this.date = date;
		this.start_time = start_time;
		this.duration = duration;
		this.status = 0;
		this.person = person;
		this.location = location == ""? "Fuller Lab 301": location;
	}
	
	public Timeslot(LocalDate date, LocalTime start_time, int duration, int status, String person, String location) {
		this.date = date;
		this.start_time = start_time;
		this.duration = duration;
		this.status = status;
		this.person = person;
		this.location = location == ""? "Fuller Lab 301": location;
	}
	
	public String toString() {
		String name = date + " " + start_time + "-" + (start_time.plusMinutes(duration));
		switch (status) {
			case 1: 
				name += " Booked, People: " + person + " Location: " + location;
				break;
			case 0: 
				name += " Available for booking";
				break;
			default:
				name += " Closed";
		}
		return name;
	}
	
	public String getDefaultLocation() { return "Fuller Lab 301"; }
}
