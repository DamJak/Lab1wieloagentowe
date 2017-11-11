import jade.core.Agent;


import java.util.Random;


public class ServerAgent extends Agent
{

    @Override
    protected void setup() {
        super.setup();
        System.out.println("Dodaje Server Agent");

        ServerBeh Se_tok_beh = new ServerBeh();//wywołuje action()
        addBehaviour(Se_tok_beh);

        Se_tok_beh.getToken_interior().setMax(Rand(19, 20)); //maks tokenow
    }

    public static Integer Rand(Integer Start, Integer Stop){
        Random r = new Random();
        return r.nextInt(Stop-Start+1)+Start;

    }
}