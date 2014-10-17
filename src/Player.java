import java.util.ArrayList;

public class Player {
    public enum Card{шестерка, семерка, восьмерка, девятка, десятка, валет, дама, король, туз}
    private ArrayList<Card> cards ;
    public Player(ArrayList<Card> startCards){
        cards = startCards;
    }

    public Card RemoveFirstCard(){
        Card c =null;
        if(cards.size()!=0){
            c= cards.get(0);
            cards.remove(0);
        }
        return c;
    }

    public void AddCards(Card one, Card two){
        cards.add(one);
        cards.add(two);
    }

    public ArrayList<Card> getCards(){ return cards;}
}
