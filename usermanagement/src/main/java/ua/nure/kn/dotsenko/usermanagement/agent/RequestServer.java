package ua.nure.kn.dotsenko.usermanagement.agent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ua.nure.kn.dotsenko.usermanagement.User;
import ua.nure.kn.dotsenko.usermanagement.db.DaoFactory;
import ua.nure.kn.dotsenko.usermanagement.db.DatabaseException;

public class RequestServer extends CyclicBehaviour {

	@Override
	public void action() {
		ACLMessage message = myAgent.receive();
		if (message != null) {
			if (message.getPerformative() == ACLMessage.REQUEST) {
				myAgent.send(createReply(message));
			} else {
				Collection<User> users = parseMessage(message);
				((SearchAgent) myAgent).showUsers(users);
			}
		} else {
			block();
		}
	}
	
	 private Collection<User> parseMessage(ACLMessage message) {
	        Collection<User> users = new LinkedList<>();
	        String content = message.getContent();
	        
	        if (content != null) {
	            StringTokenizer contentTokenizer = new StringTokenizer(content, ";");
	            while (contentTokenizer.hasMoreTokens()) {
	                String userInfo = contentTokenizer.nextToken();
	                StringTokenizer userTokenizer = new StringTokenizer(userInfo, ",");
	                String id = userTokenizer.nextToken();
	                String firstName = userTokenizer.nextToken();
	                String lastName = userTokenizer.nextToken();
	                users.add(new User(new Long(id), firstName, lastName, null));
	            }
	        }
	        return users;
	    }

	private ACLMessage createReply(ACLMessage message) {
        ACLMessage reply = message.createReply();
        
        String content = message.getContent();
        StringTokenizer tokenizer = new StringTokenizer(content, ",");
        
        if (tokenizer.countTokens() == 2) {
            String firstName = tokenizer.nextToken();
            String lastName = tokenizer.nextToken();
            Collection users = null;
            try {
                users = DaoFactory.getInstance().getUserDAO().find(firstName, lastName);
            } catch (DatabaseException e) {
                e.printStackTrace();
                users = new ArrayList<>(0);
            }
            
            StringBuffer buffer = new StringBuffer();
            for (Iterator it = users.iterator(); it.hasNext();) {
                User user = (User) it.next();
                buffer.append(user.getId()).append(",");
                buffer.append(user.getFirstName()).append(",");
                buffer.append(user.getLastName()).append(";");
            }
            reply.setContent(buffer.toString());
        }
        return reply;
    }

}
