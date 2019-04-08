package cms.view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import cms.controller.AddCalendarController;
import cms.controller.DeleteCalendarController;
import cms.controller.OpenCalendarController;
import cms.model.CMSCalendar;
import cms.model.Model;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalendarFrame extends JFrame {
	Model model;
	
	JList<CMSCalendar> calendarsList;
	JButton addBtn, deleteBtn, openBtn;
	
	public JList<CMSCalendar> getCalendarsList() {
		return calendarsList;
	}
	
	public JButton getAddButton() {
		return addBtn;
	}
	
	public JButton getDeleteButton() {
		return deleteBtn;
	}
	
	public JButton getOpenButton() {
		return openBtn;
	}
	
	public CalendarFrame(Model model) {
		this.model = model;
		setSize(500, 320);
		
		JScrollPane scrollPane = new JScrollPane();
		
		addBtn = new JButton("Add");
		deleteBtn = new JButton("Delete");
		openBtn = new JButton("Open");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(addBtn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(deleteBtn)
							.addPreferredGap(ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
							.addComponent(openBtn)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addBtn)
						.addComponent(deleteBtn)
						.addComponent(openBtn))
					.addContainerGap())
		);
		
		calendarsList = new JList<CMSCalendar>();
		calendarsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(calendarsList);
		getContentPane().setLayout(groupLayout);
		
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddCalendarController(model, CalendarFrame.this).process();
			}
		});
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeleteCalendarController(model, CalendarFrame.this).process();
			}
		});
		openBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OpenCalendarController(model, CalendarFrame.this).process();
			}
		});
	}

}
