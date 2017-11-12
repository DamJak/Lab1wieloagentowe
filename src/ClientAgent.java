import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;


import java.util.Random;


public class ClientAgent extends Agent{

    private ACLMessage Mess_Snd = new ACLMessage(ACLMessage.REQUEST);
    private ACLMessage Mess_Rcv;

    @Override
    protected void setup()
    {
        super.setup();
        Integer time = Rand(500, 3000);
        System.out.println("Klient - Odstep wynosi: " + time);

        addBehaviour(new TickerBehaviour(this, time)
        {
            protected void onTick()
            {


                AID server = Fnd_Serv(myAgent)[Rand(0, Fnd_Serv(myAgent).length-1)].getName(); //wyszukiwanie dostepnych sererów
                System.out.println(server);

                Mess_Rcv = myAgent.receive();

                if (Mess_Rcv == null)
                {
                    Snd_Mess(server,myAgent); //odbiór tokena
                }
            }
        });
    }

    public void Snd_Mess(AID Ser_Ag, Agent Ag)
    {
        Mess_Snd.addReceiver(Ser_Ag);
        Ag.send(Mess_Snd);
    }

    public static Integer Rand(Integer Start, Integer Stop)
    {
        Random r = new Random();
        return r.nextInt(Stop-Start+1)+Start;
    }



    public DFAgentDescription[] Fnd_Serv(Agent myAgent)
    {
        DFAgentDescription[] result = null;
        DFAgentDescription DFAD = new DFAgentDescription();
        ServiceDescription SD = new ServiceDescription();
        SD.setType("Sender");
        SD.setName("Token");
        DFAD.addServices(SD);
        try {
            result = DFService.search(myAgent, DFAD);
        } catch (Exception ex) {
        }
        return result;
    }

}
