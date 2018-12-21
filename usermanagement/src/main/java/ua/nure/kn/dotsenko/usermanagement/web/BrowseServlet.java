package ua.nure.kn.dotsenko.usermanagement.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.dotsenko.usermanagement.User;
import ua.nure.kn.dotsenko.usermanagement.db.DaoFactory;
import ua.nure.kn.dotsenko.usermanagement.db.DatabaseException;

public class BrowseServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		if(req.getAttribute("addButton") != null) {
//			
//			add(req, resp);
//			
//		} else if (req.getAttribute("editButton") != null) {
//			edit(req, resp);
//		} else if (req.getAttribute("deleteButton") != null) {
//			delete(req, resp);
//		} else if (req.getAttribute("detailsButton") != null) {
//			details(req, resp);
//		} else {
//			browse(req, resp);
//		}	
		browse(req, resp);
	}
	
	private void browse (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Collection <User> users;
		try {
			users = DaoFactory.getInstance().getUserDAO().findAll();
			req.getSession().setAttribute("users", users);
			req.getRequestDispatcher("/browse.jsp").forward(req, resp);
		} catch (DatabaseException e) {
			throw new ServletException(e);
		}
		
	}

}
