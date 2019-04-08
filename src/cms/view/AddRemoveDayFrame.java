package cms.view;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import cms.model.CMSCalendar;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class AddRemoveDayFrame extends JDialog {
	private JTextField dateField;
	JRadioButton addRdbtn, removeRdbtn;
	JButton okBtn;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public JTextField getDateField() { return dateField; }
	public JRadioButton getAddRdbtn() { return addRdbtn; }
	public JRadioButton getremoveRdbtn() { return removeRdbtn; }
	
	public boolean updated = false;
	public boolean wasUpdated() { return updated; }
	
	public AddRemoveDayFrame(CMSCalendar calendar) {
		
		JLabel lblDate = new JLabel("Date:");
		
		dateField = new JTextField();
		dateField.setColumns(10);
		
		addRdbtn = new JRadioButton("Add");
		buttonGroup.add(addRdbtn);
		
		removeRdbtn = new JRadioButton("Remove");
		buttonGroup.add(removeRdbtn);
		
		okBtn = new JButton("OK");
		
		JLabel lblExample = new JLabel("Example: 2018-9-24");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblExample))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(addRdbtn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(removeRdbtn)))
					.addContainerGap(192, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(369, Short.MAX_VALUE)
					.addComponent(okBtn)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDate)
						.addComponent(dateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblExample))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addRdbtn)
						.addComponent(removeRdbtn))
					.addPreferredGap(ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
					.addComponent(okBtn)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updated = true;
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
					LocalDate.parse(dateField.getText(), formatter);
				} catch(Exception except) {
					JOptionPane.showMessageDialog(null, "Date format should be yyyy-M-d");
					updated = false;
				}
				if (updated) {
					AddRemoveDayFrame.this.setVisible(false);
				}
			}
		});
	}
}
