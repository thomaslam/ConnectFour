import javax.swing.JFrame;

public class Connect4Viewer 
{
    public static void main(String[] args)
    {
        JFrame frame = new Connect4();
        
        frame.setTitle("Main menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        frame.setVisible(true);
    }
}