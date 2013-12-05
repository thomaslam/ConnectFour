import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.*;
import java.awt.TextArea;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Font;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class Connect4 extends JFrame 
{
    private static final int MAIN_FRAME_WIDTH = 500;
    private static final int MAIN_FRAME_HEIGHT = 500;
    private JMenuBar menuBar;
    private JMenu newGameMenu;
    private Label label;

    /**
     * Create the frame.
     */
    public Connect4() 
    {
        getContentPane().setBackground(Color.PINK);    
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.MAGENTA);
        setJMenuBar(menuBar);
        
        newGameMenu = new JMenu("New Game");
        newGameMenu.setBackground(Color.YELLOW);
        menuBar.add(newGameMenu);
        
        newGameMenu.add(createPlayerVsPlayerItem());
        newGameMenu.add(createPlayerVsCompItem());
        
        setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        getContentPane().setLayout(null);
        label = new Label("Connect Four Game");
        label.setBounds(0, 200, 500, 456);
        label.setBackground(Color.PINK);
        label.setForeground(Color.RED);
        label.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
        label.setAlignment(Label.CENTER);
        getContentPane().add(label);
        getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{label}));
    }
    
    public JMenuItem createPlayerVsPlayerItem()
    {
        JMenuItem mntmPlayerVsPlayer = new JMenuItem("Player vs player");
        mntmPlayerVsPlayer.setBackground(Color.WHITE);
        class MenuItemListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                String player1Name = JOptionPane.showInputDialog("Player 1's name:");
                String player2Name = JOptionPane.showInputDialog("Player 2's name:");
                GameFrame gameFrame = new GameFrame(GameFrame.VERSUS_HUMAN, player1Name);
                gameFrame.setPlayer2Name(player2Name);
                gameFrame.setTitle("Connect4 - " + player1Name + " vs " + player2Name);
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setResizable(false);
                
                gameFrame.setVisible(true);
            }
        }
        ActionListener listener = new MenuItemListener();
        mntmPlayerVsPlayer.addActionListener(listener);
        return mntmPlayerVsPlayer;
    }
    
    public JMenuItem createPlayerVsCompItem()
    {
        JMenuItem mntmPlayerVsComp = new JMenuItem("Player vs comp");
        mntmPlayerVsComp.setBackground(Color.WHITE);
        class MenuItemListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                String playerName = JOptionPane.showInputDialog("Player's name:");
                GameFrame gameFrame = new GameFrame(GameFrame.VERSUS_COMP, playerName);
                gameFrame.setTitle("Connect4 - " + playerName + " vs comp");
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setResizable(false);
                
                gameFrame.setVisible(true);
            }
        }
        ActionListener listener = new MenuItemListener();
        mntmPlayerVsComp.addActionListener(listener);
        //mntmPlayerVsComp.setEnabled(false); // to be implemented for AI stuff
        return mntmPlayerVsComp;
    }

}
