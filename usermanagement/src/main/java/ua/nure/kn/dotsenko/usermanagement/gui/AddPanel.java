package ua.nure.kn.dotsenko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPanel extends JPanel implements ActionListener {
	
	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton okButton;
	private JButton cancelButton;
	private JTextField dateOfBirthField;
	private JTextField lastNameField;
	private JTextField firstNameField;

	public AddPanel(MainFrame parent) {
		
		this.parent = parent;
		initialize();
	}

	private void initialize() {
		this.setName("addPanel");
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
	}

	private Component getButtonPanel() {
		if(buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);
			
			
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
		if(cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Cancel");
			cancelButton.setName("cancelButton");
			cancelButton.setActionCommand("cancel");
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if(okButton == null) {
			okButton = new JButton();
			okButton.setText("Ok");
			okButton.setName("okButton");
			okButton.setActionCommand("ok");
			okButton.addActionListener(this);
		}
		return okButton;
	}

	private Component getFieldPanel() {
		if(fieldPanel == null) {
			fieldPanel = new JPanel();
		    fieldPanel.setLayout(new GridLayout(3, 2));
		    addLabeledField(fieldPanel, "First name", getFirstNameField());
		    addLabeledField(fieldPanel, "Last name", getLastNameField());
		    addLabeledField(fieldPanel, "Date of birth", getDateOfBirthField());
		}
		return fieldPanel;
	}

	private JTextField getDateOfBirthField() {
		if(dateOfBirthField == null) {
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName("dateOfBirthField");
		}
		return dateOfBirthField;
	}

	private JTextField getLastNameField() {
		if(lastNameField == null) {
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField");
		}
		return lastNameField;
	}

	private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
	}

	private JTextField getFirstNameField() {
		if(firstNameField == null) {
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField");
		}
		return firstNameField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
