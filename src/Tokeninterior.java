import java.util.*;


public class Tokeninterior
{
    private static Random Rnd = new Random();
    private static String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";

    private String token="";

    public Queue<String> getQue()
    {
        return Que;
    }


    private Queue<String> Que = new LinkedList<>();
    private Integer Max_Am = 0;
    private Integer Curr_Am = 0;

    public void Crt_Tok()//Tworzenie tokenu i zapis do kolejki
    {
        token = Gen_Tok(25);
        System.out.println(token);
        Que.add(token);

    }

    public String Get_Tok()//Pobranie tokenu z kolejki
    {
        if(Que.isEmpty() == false)
        {
            return Que.poll();
        }
        else
            return null;
    }

    public boolean Is_Full() //sprawdzanie czy wygenerowano juz wszystkie tokeny
    {
        if(Curr_Am >= Max_Am)
            return true;
        else
            return false;
    }

    public void setMax(Integer max) {
        this.Max_Am = max;
    }

    public Integer getCount() {
        return Curr_Am;
    }

    public void setCount(Integer count) {
        this.Curr_Am = count;
    }

    public static String Gen_Tok(int len) //generator tokenów
    {
        StringBuilder token = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            token.append(CHARS.charAt(Rnd.nextInt(CHARS.length())));
        }
        return token.toString();
    }
}
