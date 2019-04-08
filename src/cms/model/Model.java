package cms.model;

import java.util.*;

public class Model {
	ArrayList<CMSCalendar> calendars = new ArrayList<CMSCalendar>();
	
	public void addCalendar(CMSCalendar calendar) {
		calendars.add(calendar);
	}
	
	public void removeCalendar(String name) {
		Iterator<CMSCalendar> iter = calendars.iterator();
		
		while (iter.hasNext()) {
			CMSCalendar c = iter.next();
			
			if (c.getName() == name) {
				iter.remove();
			}
		}
	}
	
	public ArrayList<CMSCalendar> getCalendars() {
		return calendars;
	}
}
