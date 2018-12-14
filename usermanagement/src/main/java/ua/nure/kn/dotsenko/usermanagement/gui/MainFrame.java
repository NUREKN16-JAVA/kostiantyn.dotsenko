package ua.nure.kn.dotsenko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ua.nure.kn.dotsenko.usermanagement.db.DaoFactory;
import ua.nure.kn.dotsenko.usermanagement.db.DatabaseException;
import ua.nure.kn.dotsenko.usermanagement.db.UserDAO;
import ua.nure.kn.dotsenko.usermanagement.util.Messages;

public class MainFrame extends JFrame {

	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	private UserDAO dao;
	private JPanel contentPanel;
	private BrowsePanel browsePanel;
	private AddPanel addPanel;
	private EditPanel editPanel;

	public MainFrame() {
		super();
		dao = DaoFactory.getInstance().getUserDAO();
		initialize();
	}
	
	public UserDAO getDao() {
		return dao;
	}
	
	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle(Messages.getString("MainFrame.user_management")); //localize //$NON-NLS-1$
		this.setContentPane(getContentPanel());
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
		} 
		return contentPanel;
	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}

	public void showBrowsePanel() {
		showPanel(getBrowsePanel());
	}
	
	public void showAddPanel() {
		showPanel(getAddPanel());
	}
	
	public void showEditPanel(Long userId) {
		showPanel(getEditPanel());
		try {
			editPanel.fillUserToUpdateData(dao.find(userId));
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}


	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
	}

	private JPanel getBrowsePanel() {
		if(browsePanel == null) {
			browsePanel = new BrowsePanel(this);
		} 
		browsePanel.initTable();
		return browsePanel;
	}
	
	private AddPanel getAddPanel() {
		if(addPanel == null) {
			addPanel = new AddPanel(this);
		}
		return addPanel;
	}

	private EditPanel getEditPanel() {
		if(editPanel == null) {
			editPanel = new EditPanel(this);
		}
		return editPanel;
	}
}
