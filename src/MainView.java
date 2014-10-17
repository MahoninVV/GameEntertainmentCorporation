import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TimerTask;


public class MainView extends JFrame {
    JButton leftPlayer, rightPlayer, fight, next;

    Player firstPlayer, secondPlayer;
    public void createPlayers(){
        Random rand = new Random();
        ArrayList<Player.Card> first = new ArrayList<Player.Card>() ;
        ArrayList<Player.Card> second = new ArrayList<Player.Card>() ;
       for(int i = 0; i <= Player.Card.туз.ordinal(); i++){
            for(int j =0; j < 4; j++){
                Player.Card card = Player.Card.values()[i];
                if(first.size()==18)
                    second.add(card);
                else if(second.size()==18)
                    first.add(card);
                else
                {
                    if(rand.nextInt(2)==1)
                        first.add(card);
                    else
                        second.add(card);
                }
            }
       }
        Collections.shuffle(first);
        Collections.shuffle(second);
        firstPlayer = new Player(first);
        secondPlayer = new Player(second);
    }

    private void nextTurn(){
        Random rand = new Random();
        Player.Card first = firstPlayer.RemoveFirstCard();
        Player.Card second = secondPlayer.RemoveFirstCard();
        if(first==null || second==null)
            return;
        if(first.ordinal() > second.ordinal())
            firstPlayer.AddCards(first,second);
        else if(first.ordinal() < second.ordinal())
            secondPlayer.AddCards(first,second);
        else if(rand.nextInt(2)==1)
            firstPlayer.AddCards(first,second);
        else
            secondPlayer.AddCards(first,second);


        fight.setText(first.toString() + "     "+ second.toString());
        leftPlayer.setText(cardListText(firstPlayer.getCards()));
        rightPlayer.setText(cardListText(secondPlayer.getCards()));
    }

    private String cardListText(ArrayList<Player.Card> a){
        String s = "<html>";
        for(int i = 0; i < a.size();i++)
            s+= a.get(i).toString() + "<br>";
        s+="</html>";
        return s;
    }

    public MainView(String title) throws HeadlessException {

        super(title);
        createPlayers();

        setResizable(false);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        leftPlayer = new JButton("<html>1<br>1<br>1<br>1<br>1<br>1<br></html>");

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight=2;
        this.add(leftPlayer, c);

        next = new JButton("Кликните для следующего хода");
        next.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nextTurn();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty=0.8;
        c.gridheight=1;
        c.gridx = 1;
        c.gridy = 0;
        this.add(next, c);

        rightPlayer = new JButton();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridheight=2;
        c.gridx = 2;
        c.gridy = 0;
        this.add(rightPlayer, c);

        fight = new JButton("Long-Named Button 4");
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.weighty=0.2;
        c.gridwidth = 1;
        c.gridheight=1;
        c.gridx = 1;
        c.gridy = 1;
        this.add(fight, c);

        nextTurn();
    }


}

