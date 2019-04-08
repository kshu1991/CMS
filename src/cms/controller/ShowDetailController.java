package cms.controller;

import javax.swing.JList;
import javax.swing.JOptionPane;

import cms.model.CMSCalendar;
import cms.model.Timeslot;
import cms.view.DetailFrame;
import cms.view.TimeslotFrame;

public class ShowDetailController {
	CMSCalendar calendar;
	TimeslotFrame frame;
	
	public ShowDetailController(CMSCalendar calendar, TimeslotFrame frame) {
		this.calendar = calendar;
		this.frame = frame;
	}
	
	public void process() {
		JList<Timeslot> timeslotsList = frame.getTimeslotList();
		int idx = timeslotsList.getSelectedIndex();
		if (idx < 0) {
			return;
		}
		
		Timeslot timeslot = timeslotsList.getSelectedValue();
		if (timeslot.getStatus() == -1) {
			JOptionPane.showMessageDialog(null, "This timeslot has been closed");
			return;
		}
		DetailFrame df = new DetailFrame(timeslot);
		df.getTimeLabel().setText(timeslot.toString());
		df.getPersonField().setText(timeslot.getPerson());
		df.getLocationField().setText(timeslot.getLocation());
		if (timeslot.getPerson().equals("")) {
			df.getCancelButton().setEnabled(false);
		}
		
		df.setSize(800, 320);
		df.setModal(true);
		df.setVisible(true);
		
		if (df.wasUpdated()) {
			String person = df.getPersonField().getText();
			String location = df.getLocationField().getText();
			
			if (person.equals("")) {
				timeslot.setPerson("");
				timeslot.setLocation(timeslot.getDefaultLocation());
				timeslot.setStatus(0);
			}
			else {
				timeslot.setPerson(person);
				timeslot.setLocation(location);
				timeslot.setStatus(1);
			}
		}
		
		df.dispose();
		frame.repaint();
	}
}
