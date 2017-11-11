import java.util.*;


public class Tokeninterior
{
    private static Random Rnd = new Random();
    private static String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";

    private String token=" ";

    public void Crt_Tok()//Tworzenie tokenu i zapis do kolejki
    {
        token = Gen_Tok(25);
        System.out.println(token);
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
