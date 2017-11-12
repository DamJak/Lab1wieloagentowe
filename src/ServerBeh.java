import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ServerBeh extends CyclicBehaviour {

    Tokeninterior token_interior = new Tokeninterior();

    public void action()
    {
        MessageTemplate Mess_Tem = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);//wzor wiadomosci
        ACLMessage Msg = myAgent.receive(Mess_Tem);//czekanie na wiadomosc o zadanym wzorze

        if (Msg != null)
        {
            ACLMessage Answ = Msg.createReply();

                Answ.setContent(token_interior.Get_Tok());
                System.out.println("Wysylam token do: " + Msg.getSender().getName());
                myAgent.send(Answ);
        }


    }

    public Tokeninterior getToken_interior() {
        return token_interior;
    }

}