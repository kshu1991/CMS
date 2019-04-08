package cms;

import cms.model.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import cms.controller.*;
import cms.view.*;
import storage.IStorage;

public class Main {

	public static void main(String[] args) {
		Model model = new Model();
		IStorage storage = new IStorage();
		storage.load(model);
		
		DefaultListModel<CMSCalendar> dlm = new DefaultListModel<CMSCalendar>();
		ArrayList<CMSCalendar> calendarsList = model.getCalendars();
		for (CMSCalendar calendar: calendarsList) {
			dlm.addElement(calendar);
		}
		
		CalendarFrame frame = new CalendarFrame(model);
		frame.getCalendarsList().setModel(dlm);
		
		frame.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				 if (new QuitController().confirm(frame)) {
					 storage.store(model);
                     frame.dispose();
             }
			}
		});
		frame.setVisible(true);
		
	}

}
