import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;


import java.util.Random;


public class ServerAgent extends Agent
{

    @Override
    protected void setup()
    {
        super.setup();
        System.out.println("Dodaje Server Agent");

        DFAgentDescription DFAD = new DFAgentDescription();
        DFAD.setName(getAID());
        ServiceDescription SD = new ServiceDescription();
        SD.setType("Sender");
        SD.setName("Token");
        DFAD.addServices(SD);
        try
        {
            DFService.register(this, DFAD);
        }
        catch(FIPAException fe)
        {
            fe.printStackTrace();
        }

        ServerBeh Se_tok_beh = new ServerBeh();//wywołuje action()
        addBehaviour(Se_tok_beh);

        Se_tok_beh.getToken_interior().setMax(Rand(19, 20)); //maks tokenow

        int tmr=Rand(500, 1000); //odstep miedzy generowaniem tokenow
        System.out.println("Server - odstep wynosi: " + tmr);

        addBehaviour(new TickerBehaviour( this, tmr) //cykliczne generowanie tokenów
        {
            protected void onTick()
            {
                Integer Act_Am = Se_tok_beh.getToken_interior().getCount();
                if(!Se_tok_beh.getToken_interior().Is_Full())
                {
                    Se_tok_beh.getToken_interior().Crt_Tok();
                    Act_Am++;
                    System.out.println("Wygenerowano: " + Act_Am + " token");
                }
                Se_tok_beh.getToken_interior().setCount(Act_Am);
            }
        });
    }



    public static Integer Rand(Integer Start, Integer Stop){
        Random r = new Random();
        return r.nextInt(Stop-Start+1)+Start;

    }
}