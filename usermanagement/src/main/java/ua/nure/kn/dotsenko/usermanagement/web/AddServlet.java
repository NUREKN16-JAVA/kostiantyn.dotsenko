package ua.nure.kn.dotsenko.usermanagement.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.dotsenko.usermanagement.User;
import ua.nure.kn.dotsenko.usermanagement.db.DaoFactory;
import ua.nure.kn.dotsenko.usermanagement.db.DatabaseException;

public class AddServlet extends EditServlet {

	@Override
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.getRequestDispatcher("/add.jsp").forward(req, resp);
	}

	@Override
	protected void processUser(User user) throws DatabaseException {
	DaoFactory.getInstance().getUserDAO().create(user);
	}

}
