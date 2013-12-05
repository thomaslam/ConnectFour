import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;

public class ButtonListenerAI implements ActionListener
{
    private GameFrame gframe;
    private GameContentComponents gc;
    private int col;
    private JTextField textField;
    //public ArrayList<MoveScore> moveScores = new ArrayList<MoveScore>();
    
    public ButtonListenerAI(GameFrame gf, GameContentComponents g, int c, JTextField tf)
    {
        super();
        gframe = gf;
        gc = g;
        col = c;
        textField = tf;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (gc.getVictoriousPlayer() == GameContentComponents.DRAW || gc.getVictoriousPlayer() != GameContentComponents.BLANK)
        {    
            gc.reset();
            textField.setText("New Game");
            return;
        }
        
        GameContentComponents.TURN_NUM++;
        gc.putDiscInCol (col);
        if (gc.getVictoriousPlayer() == GameContentComponents.RED || gc.getVictoriousPlayer() == GameContentComponents.DRAW)
        {
        	if (gc.getVictoriousPlayer() == GameContentComponents.DRAW)
        	{
        		textField.setText("Draw");
                GameContentComponents.TURN_NUM = 0;
                return;
        	}
            textField.setText(gframe.getPlayer1Name() + " wins!! Click on any button to reset");
            GameContentComponents.TURN_NUM = 0;
            return;
        }
  
        textField.setText(gframe.getPlayer2Name() + "'s turn");
      
        GameContentComponents.TURN_NUM++;
        gc.putDiscInCol (AImoveToCol());
        if (gc.getVictoriousPlayer() == GameContentComponents.YELLOW || gc.getVictoriousPlayer() == GameContentComponents.DRAW)
        {
        	if (gc.getVictoriousPlayer() == GameContentComponents.DRAW)
        	{
        		textField.setText("Draw");
                GameContentComponents.TURN_NUM = 0;
                return;
        	}
            textField.setText(gframe.getPlayer2Name() + " wins!! Click on any button to reset");
            GameContentComponents.TURN_NUM = 0;
            return;
        }
        else
            textField.setText(gframe.getPlayer1Name() + "'s turn");
    }
    
    public int AImoveToCol()
    {	
    	int decision = gc.miniMaxDecision() + 1;
    	System.out.println(decision);
    	return decision;
    }
}