import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.*;


public class GameFrame extends JFrame 
{
    private static final int GAME_FRAME_WIDTH = 700;
    private static final int GAME_FRAME_HEIGHT = 723;
    public static final int VERSUS_HUMAN = 1;
    public static final int VERSUS_COMP = 2;
    
    private String player1Name;
    private String player2Name;
    private int versus;
    private JTextField txtPlayersTurn;
    private GameContentComponents gc;
    
    /**
     * Create the frame.
     */
    public GameFrame(int vers, String p1)
    {
        versus = vers;
        player1Name = p1;
        player2Name = "Comp"; // by default player 2 is AI
        setSize(GAME_FRAME_WIDTH, GAME_FRAME_HEIGHT);
        getContentPane().setLayout(null);
        
        gc = new GameContentComponents();
        gc.setSize(700, 700);
        getContentPane().add(gc);
        
        createTextField();
        createButtons();
    }
    
    public String getPlayer1Name()
    {
        return player1Name;
    }
    
    public String getPlayer2Name()
    {
        return player2Name;
    }
    
    public void setPlayer2Name(String s2)
    {
        player2Name = s2;
    }
    
    public boolean vsComp()
    {
        return versus == VERSUS_COMP;
    }
    
    public void createTextField()
    {
        txtPlayersTurn = new JTextField();
        txtPlayersTurn.setHorizontalAlignment(SwingConstants.CENTER);
        txtPlayersTurn.setFont(new Font("Lucida Grande", Font.BOLD, 20));
        txtPlayersTurn.setEditable(false);
        txtPlayersTurn.setText(player1Name + "'s turn");
        txtPlayersTurn.setBounds(0, 0, 700, 50);
        getContentPane().add(txtPlayersTurn);
        txtPlayersTurn.setColumns(10);
    }
    
    public void createButtons()
    {
        JButton button1 = new JButton("1");
        if (versus == VERSUS_HUMAN)
        {
            ActionListener listener1 = new ButtonListener(this, gc, 1, txtPlayersTurn);
            button1.addActionListener(listener1);
        }
        else // AI component
        {
            ActionListener listener1 = new ButtonListenerAI(this, gc, 1, txtPlayersTurn);
            button1.addActionListener(listener1);
        }
        button1.setBounds(0, 50, 100, 50);
        getContentPane().add(button1);
        
        
        
        JButton button2 = new JButton("2");
        if (versus == VERSUS_HUMAN)
        {
            ActionListener listener2 = new ButtonListener(this, gc, 2, txtPlayersTurn);
            button2.addActionListener(listener2);
        }
        else // AI component
        {
            ActionListener listener2 = new ButtonListenerAI(this, gc, 2, txtPlayersTurn);
            button2.addActionListener(listener2);
        }
        button2.setBounds(100, 50, 100, 50);
        getContentPane().add(button2);
        
        
        
        JButton button3 = new JButton("3");
        if (versus == VERSUS_HUMAN)
        {
            ActionListener listener3 = new ButtonListener(this, gc, 3, txtPlayersTurn);
            button3.addActionListener(listener3);
        }
        else // AI component
        {
            ActionListener listener3 = new ButtonListenerAI(this, gc, 3, txtPlayersTurn);
            button3.addActionListener(listener3);
        }
        button3.setBounds(200, 50, 100, 50);
        getContentPane().add(button3);
        
        
        
        JButton button4 = new JButton("4");
        if (versus == VERSUS_HUMAN)
        {
            ActionListener listener4 = new ButtonListener(this, gc, 4, txtPlayersTurn);
            button4.addActionListener(listener4);
        }
        else // AI component
        {
            ActionListener listener4 = new ButtonListenerAI(this, gc, 4, txtPlayersTurn);
            button4.addActionListener(listener4);
        }
        button4.setBounds(300, 50, 100, 50);
        getContentPane().add(button4);
        
        
        
        JButton button5 = new JButton("5");
        if (versus == VERSUS_HUMAN)
        {
            ActionListener listener5 = new ButtonListener(this, gc, 5, txtPlayersTurn);
            button5.addActionListener(listener5);
        }    
        else // AI component
        {
            ActionListener listener5 = new ButtonListenerAI(this, gc, 5, txtPlayersTurn);
            button5.addActionListener(listener5);
        }
        button5.setBounds(400, 50, 100, 50);
        getContentPane().add(button5);
        
        
        
        JButton button6 = new JButton("6");
        if (versus == VERSUS_HUMAN)
        {
            ActionListener listener6 = new ButtonListener(this, gc, 6, txtPlayersTurn);
            button6.addActionListener(listener6);
        }
        else // AI component
        {
            ActionListener listener6 = new ButtonListenerAI(this, gc, 6, txtPlayersTurn);
            button6.addActionListener(listener6);
        }
        button6.setBounds(500, 50, 100, 50);
        getContentPane().add(button6);
        
        
        
        JButton button7 = new JButton("7");
        if (versus == VERSUS_HUMAN)
        {
            ActionListener listener7 = new ButtonListener(this, gc, 7, txtPlayersTurn);
            button7.addActionListener(listener7);
        }
        else // AI component
        {
            ActionListener listener7 = new ButtonListenerAI(this, gc, 7, txtPlayersTurn);
            button7.addActionListener(listener7);
        }
        button7.setBounds(600, 50, 100, 50);
        getContentPane().add(button7);
    }
}
