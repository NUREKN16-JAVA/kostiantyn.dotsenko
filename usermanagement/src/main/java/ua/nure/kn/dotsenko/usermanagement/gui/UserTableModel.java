package ua.nure.kn.dotsenko.usermanagement.gui;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import ua.nure.kn.dotsenko.usermanagement.User;
import ua.nure.kn.dotsenko.usermanagement.util.Messages;

public class UserTableModel extends AbstractTableModel {
	
	private static final String[] COLUMN_NAME = {"id",Messages.getString("UserTableModel.first_name"),Messages.getString("UserTableModel.last_name")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};
	private List users = null;
	
	public UserTableModel(Collection users) {
	this.users = new ArrayList(users);
	}

	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAME.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		}
		return null;
	}

	public String getColumnName(int column) {
		return COLUMN_NAME[column];
	}

	public Class getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	public void addUsers(Collection<User> users) {
		this.users.addAll(users);
	}

	public void clearUsers() {
		this.users = new ArrayList<>();
	}

}
