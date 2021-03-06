package ua.nure.kn.dotsenko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import ua.nure.kn.dotsenko.usermanagement.User;
import ua.nure.kn.dotsenko.usermanagement.db.DatabaseException;
import ua.nure.kn.dotsenko.usermanagement.util.Messages;

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
		this.setName("browsePanel"); //$NON-NLS-1$
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
			detailsButton.setName("detailsButton"); //$NON-NLS-1$
			detailsButton.setText(Messages.getString("BrowsePanel.details")); //localize //$NON-NLS-1$
			detailsButton.setActionCommand("details"); //$NON-NLS-1$
			detailsButton.addActionListener(this);
		}
		return detailsButton;
	}

	private JButton getDeleteButton() {
		if(deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setName("deleteButton"); //$NON-NLS-1$
			deleteButton.setText(Messages.getString("BrowsePanel.delete")); //localize //$NON-NLS-1$
			deleteButton.setActionCommand("delete"); //$NON-NLS-1$
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}

	private JButton getEditButton() {
		if(editButton == null) {
			editButton = new JButton();
			editButton.setName("editButton"); //$NON-NLS-1$
			editButton.setText(Messages.getString("BrowsePanel.edit")); //localize //$NON-NLS-1$
			editButton.setActionCommand("edit"); //$NON-NLS-1$
			editButton.addActionListener(this);
		}
		return editButton;
	}

	private JButton getAddButton() {
		if(addButton == null) {
			addButton = new JButton();
			addButton.setName("addButton"); //$NON-NLS-1$
			addButton.setText(Messages.getString("BrowsePanel.add")); //localize //$NON-NLS-1$
			addButton.setActionCommand("add"); //$NON-NLS-1$
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
			userTable.setName("userTable"); //$NON-NLS-1$
		}
		return userTable;
	}

	public void initTable() {
		UserTableModel model;
		try {
			model = new UserTableModel(parent.getDao().findAll());
		} catch (DatabaseException e) {
			model = new UserTableModel(new ArrayList());
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
		}
		getUserTable().setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if ("add".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
			this.setVisible(false);
			parent.showAddPanel();
			return;
		}
		if ("edit".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
			if (userTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, Messages.getString("BrowsePanel.select_user_to_update"), "Error", //$NON-NLS-1$ //$NON-NLS-2$
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			this.setVisible(false);
			parent.showEditPanel((Long) userTable.getValueAt(userTable.getSelectedRow(), 0));
			return;
		}

		if ("delete".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
			if (userTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, Messages.getString("BrowsePanel.user_to_delete"), "Error", //$NON-NLS-1$ //$NON-NLS-2$
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			int answer = JOptionPane.showConfirmDialog(this, Messages.getString("BrowsePanel.question_deletion"), //$NON-NLS-1$
					Messages.getString("BrowsePanel.deletion"), //$NON-NLS-1$
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (answer == 0) {
				try {
					parent.getDao()
							.delete(parent.getDao().find((Long) userTable.getValueAt(userTable.getSelectedRow(), 0)));
				} catch (DatabaseException e1) {
					e1.printStackTrace();
				}
				parent.showBrowsePanel();
			}

			return;
		}

		if ("details".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
			if (userTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, Messages.getString("BrowsePanel.select_to_view_details"), "Error", //$NON-NLS-1$ //$NON-NLS-2$
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			User selectedUser = null;
			try {
				selectedUser = parent.getDao().find((Long) userTable.getValueAt(userTable.getSelectedRow(), 0));
			} catch (DatabaseException e1) {
				e1.printStackTrace();
			}

			String message = "Id - " + selectedUser.getId().toString() + "\n" + //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("BrowsePanel.full_name") + //$NON-NLS-1$
					selectedUser.getFullName() + "\n" + //$NON-NLS-1$
					Messages.getString("BrowsePanel.date_of_birth") + //$NON-NLS-1$
					selectedUser.getDateOfBirth().toString();

			JOptionPane.showMessageDialog(this, message, Messages.getString("BrowsePanel.details"), //$NON-NLS-1$
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
	}

}
