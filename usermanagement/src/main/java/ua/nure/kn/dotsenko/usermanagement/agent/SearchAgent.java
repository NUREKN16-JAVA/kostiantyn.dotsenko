package ua.nure.kn.dotsenko.usermanagement.agent;

import java.util.Collection;

import jade.core.AID;
import jade.core.Agent;
import ua.nure.kn.dotsenko.usermanagement.User;
import ua.nure.kn.dotsenko.usermanagement.db.DaoFactory;
import ua.nure.kn.dotsenko.usermanagement.db.DatabaseException;

public class SearchAgent extends Agent {

	@Override
	protected void setup() {
		super.setup();
		System.out.println(getAID().getName() + " started.");
	}

	@Override
	protected void takeDown() {
		super.takeDown();
		System.out.println(getAID().getName() + " terminated.");
	}
	
	public void search(String firstName, String lastName) throws SearchException {
		try {
			Collection<User> users = DaoFactory.getInstance().getUserDAO().find(firstName, lastName);
			if(users.size() > 0) {
			showUsers(users);
			} else {
				 addBehaviour(new SearchRequestBehaviour(new AID[] {} , firstName, lastName));
			}
		} catch(DatabaseException e) {
			throw new SearchException(e);
		}
		
		
	}

	void showUsers(Collection<User> users) {
		// TODO Auto-generated method stub
		
	}

}
