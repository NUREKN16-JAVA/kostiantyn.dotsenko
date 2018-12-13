package ua.nure.kn.dotsenko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class BrowsePanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private JScrollPane tablePanel;
	private JTable userTable;
	private JPanel buttonPanel;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton detailsButton;

	public BrowsePanel(MainFrame mainFrame) {
		this.parent = mainFrame;
		initialize();
	}

	private void initialize() {
		this.setName("browsePanel");
		this.setLayout(new BorderLayout());
		this.add(getTablePanel(), BorderLayout.CENTER);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
	}

	private JPanel getButtonPanel() {
		if(buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getAddButton());
			buttonPanel.add(getEditButton());
			buttonPanel.add(getDeleteButton());
			buttonPanel.add(getDetailsButton());
		}
		return buttonPanel;
	}

	private JButton getDetailsButton() {
		if(detailsButton == null) {
			detailsButton = new JButton();
			detailsButton.setName("detailsButton");
			detailsButton.setText("Details"); //localize
			detailsButton.setActionCommand("details");
			detailsButton.addActionListener(this);
		}
		return detailsButton;
	}

	private JButton getDeleteButton() {
		if(deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setName("deleteButton");
			deleteButton.setText("Delete"); //localize
			deleteButton.setActionCommand("delete");
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}

	private JButton getEditButton() {
		if(editButton == null) {
			editButton = new JButton();
			editButton.setName("editButton");
			editButton.setText("Edit"); //localize
			editButton.setActionCommand("edit");
			editButton.addActionListener(this);
		}
		return editButton;
	}

	private JButton getAddButton() {
		if(addButton == null) {
			addButton = new JButton();
			addButton.setName("addButton");
			addButton.setText("Add"); //localize
			addButton.setActionCommand("add");
	        addButton.addActionListener(this);
		}
		return addButton;
	}

	private JScrollPane getTablePanel() {
		if(tablePanel == null) {
			tablePanel = new JScrollPane(getUserTable());
		}
		return tablePanel;
	}

	private JTable getUserTable() {
		if(userTable == null) {
			userTable = new JTable();
			userTable.setName("userTable");
		}
		return userTable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if("add".equalsIgnoreCase(actionCommand)) {
			this.setVisible(false);
			parent.showAddPanel();
		}

	}

}
