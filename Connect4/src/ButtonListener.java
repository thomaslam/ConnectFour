import java.awt.event.*;

import javax.swing.*;

public class ButtonListener implements ActionListener
{
    private GameFrame gframe;
    private GameContentComponents gc;
    private int col;
    private JTextField textField;
    
    public ButtonListener(GameFrame gf, GameContentComponents g, int c, JTextField tf)
    {
        super();
        gframe = gf;
        gc = g;
        col = c;
        textField = tf;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (gc.getVictoriousPlayer() == GameContentComponents.RED || gc.getVictoriousPlayer() == GameContentComponents.YELLOW || gc.getVictoriousPlayer() == GameContentComponents.DRAW)
        {    
            gc.reset();
            textField.setText("New Game");
            return;
        }
        GameContentComponents.TURN_NUM++;
        gc.putDiscInCol (col);
        if (GameContentComponents.TURN_NUM % 2 != 0) //player 1
        {
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
            else
                textField.setText(gframe.getPlayer2Name() + "'s turn");
        }
        else                                        //player 2
        {
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
    }
    
}