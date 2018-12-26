package ua.nure.kn.dotsenko.usermanagement.agent;

import java.util.Collection;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import ua.nure.kn.dotsenko.usermanagement.User;
import ua.nure.kn.dotsenko.usermanagement.db.DaoFactory;
import ua.nure.kn.dotsenko.usermanagement.db.DatabaseException;

public class SearchAgent extends Agent {

	private AID[] aids;
	private SearchGui gui = null;

	@Override
	protected void setup() {
		super.setup();
		System.out.println(getAID().getName() + " started.");

		gui = new SearchGui(this);
		gui.setVisible(true);

		DFAgentDescription description = new DFAgentDescription();
		description.setName(getAID());
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setName("JADE-searching");
		serviceDescription.setType("searching");
		description.addServices(serviceDescription);

		try {
			DFService.register(this, description);
		} catch (FIPAException e) {
			e.printStackTrace();
		}

		addBehaviour(new RequestServer());

		addBehaviour(new TickerBehaviour(this, 60000) {

			@Override
			protected void onTick() {
				DFAgentDescription agentDescription = new DFAgentDescription();
				ServiceDescription serviceDescription = new ServiceDescription();
				serviceDescription.setType("searching");
				agentDescription.addServices(serviceDescription);
				try {
					DFAgentDescription[] descriptions = DFService.search(myAgent, agentDescription);
					aids = new AID[descriptions.length];
					for (int i = 0; i < descriptions.length; ++i) {
						DFAgentDescription description = descriptions[i];
						aids[i] = description.getName();
					}
				} catch (FIPAException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void takeDown() {
		System.out.println(getAID().getName() + " terminated.");
		try {
			DFService.deregister(this);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		gui.setVisible(false);
		gui.dispose();
		super.takeDown();
	}

	public void search(String firstName, String lastName) throws SearchException {
		try {
			Collection<User> users = DaoFactory.getInstance().getUserDAO().find(firstName, lastName);
			if (users.size() > 0) {
				showUsers(users);
			} else {
				addBehaviour(new SearchRequestBehaviour(aids, firstName, lastName));
			}
		} catch (DatabaseException e) {
			throw new SearchException(e);
		}

	}

	void showUsers(Collection<User> users) {
		gui.addUsers(users);
	}

}
