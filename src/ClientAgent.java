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
    Integer Flag = 0;
    int Num_mess = 0;

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
                if(Flag == 1)
                {
                    Flag = 0;
                }
                else
                {
                    AID server = Fnd_Serv(myAgent)[Rand(0, Fnd_Serv(myAgent).length-1)].getName(); //wyszukiwanie dostepnych sererów
                    Mess_Rcv = myAgent.receive();
                    if (Mess_Rcv != null)
                    {
                        Rcv_Mess(myAgent); //odbiór tokena
                    }
                    else
                    {
                        Snd_Mess(server,myAgent);//wysyłanie żadania
                        block();
                    }

                }
            }
        });
    }

    public void Snd_Mess(AID Ser_Ag, Agent Ag)
    {
        Mess_Snd.addReceiver(Ser_Ag);
        Ag.send(Mess_Snd);
    }
    public void Rcv_Mess(Agent Ag)
    {
        if (Mess_Rcv.getPerformative() == ACLMessage.INFORM)//
            Flag = 1;
        else if (Mess_Rcv.getPerformative() == ACLMessage.CANCEL) {
            Flag = 1;
            System.out.println(Ag.getName() + " odebral: " + Num_mess + " tokenów");
            Ag.doDelete();
        } else {
            System.out.println("Agent klient: " + Ag.getName() + " odebral od agenta servera: " +
                    Mess_Rcv.getSender().getName() + " token: " + Mess_Rcv.getContent().toString());
            Num_mess++;
            Flag = 1;
        }
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
