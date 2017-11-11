import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ServerBeh extends CyclicBehaviour {

    Tokeninterior token_interior = new Tokeninterior();

    public void action()
    {

    }

    public Tokeninterior getToken_interior() {
        return token_interior;
    }

}