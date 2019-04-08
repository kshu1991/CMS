package cms.view;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import cms.model.Timeslot;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DetailFrame extends JDialog {
	private JTextField personField;
	private JTextField locationField;
	JLabel timeLabel;
	JButton updateBtn;
	boolean updated = false;
	JButton cancelBtn;
	
	public JLabel getTimeLabel() { return timeLabel; }
	public JButton getUpdateButton() { return updateBtn; }
	public JButton getCancelButton() { return cancelBtn; }
	public JTextField getPersonField() { return personField; }
	public JTextField getLocationField() { return locationField; }
	
	public DetailFrame(Timeslot timeslot) {
		
		JLabel lblTime = new JLabel("Time:");
		
		JLabel lblPerson = new JLabel("Person:");
		
		JLabel lblLocation = new JLabel("Location:");
		
		personField = new JTextField();
		personField.setColumns(10);
		
		locationField = new JTextField();
		locationField.setColumns(10);
		
		timeLabel = new JLabel("Display Time");
		
		updateBtn = new JButton("Update");
		updateBtn.setEnabled(false);
		
		cancelBtn = new JButton("Cancel Meeting");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTime)
						.addComponent(lblPerson)
						.addComponent(lblLocation))
					.addGap(60)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(locationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(personField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(timeLabel))
					.addContainerGap(192, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(233, Short.MAX_VALUE)
					.addComponent(cancelBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(updateBtn)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTime)
						.addComponent(timeLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPerson)
						.addComponent(personField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLocation)
						.addComponent(locationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(updateBtn)
						.addComponent(cancelBtn))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		personField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateBtn.setEnabled(!personField.getText().equals(timeslot.getPerson()) && timeslot.getStatus() != -1);
				locationField.setEditable(personField.getText().length() != 0 && timeslot.getStatus() != -1);
			}
		});
		
		locationField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateBtn.setEnabled(!locationField.getText().equals(timeslot.getLocation()));
			}
		});
		
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updated = true;
				DetailFrame.this.setVisible(false);
			}
		});
		
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personField.setText("");
				updated = true;
				DetailFrame.this.setVisible(false);
			}
		});
		
	}
	
	public boolean wasUpdated() { return updated; }
}
