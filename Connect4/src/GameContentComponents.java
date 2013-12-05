import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import javax.swing.JComponent;
import java.util.ArrayList;

public class GameContentComponents extends JComponent
{
	// Based on number of 4s consecutive containing the position; for example, there are 4 ways to get 4s consecutive that contain position array[1][0]
	private static int[][] evaluationTable = {{3, 4, 5, 7, 5, 4, 3}, 
											  {4, 6, 8,10, 8, 6, 4},
											  {5, 8,11,13,11, 8, 5},
											  {5, 8,11,13,11, 8, 5},
											  {4, 6, 8,10, 8, 6, 4},
											  {3, 4, 5, 7, 5, 4, 3}};
	private int maxColumn;
    public static final int BLANK = 0;
    public static final int RED = -1;
    public static final int YELLOW = 1;
    public static final int DRAW = 2;
    public static int TURN_NUM = 0;
    public static final boolean COMP_TURN = true;
    public static final boolean HUMAN_TURN = false;
    public static final int MAX_ROW = 6;
    public static final int MAX_COL = 7;
    private static final int MAX_DEEP = 7;
    
    
    private int[][] array = new int[MAX_ROW][MAX_COL];
    private ArrayList<Ellipse2D.Double> circlesArray = new ArrayList<Ellipse2D.Double>();
    
    public GameContentComponents()
    {
        for (int x = 0; x < array.length; x++)
        {
            for (int y = 0; y < array[0].length; y++)
                array[x][y] = BLANK;
        }
        
        for (int b = 600; b > 0; b -= 100)
        {
            for (int a = 0; a < 700; a += 100)
            {
                Ellipse2D.Double circle = new Ellipse2D.Double(a, b, 100, 100);
                circlesArray.add(circle);
            }
        }
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        initialize(g2);
        
        for (int x = 0; x < array.length; x++)
        {
            for (int y = 0; y < array[0].length; y++)
            {
                if (array[x][y] == BLANK)
                {
                    g2.setColor(Color.WHITE);
                    Ellipse2D.Double c = circlesArray.get(7*x + y);
                    g2.draw(c);
                    g2.fill(c);
                }
                else if (array[x][y] == RED)
                {
                    g2.setColor(Color.RED);
                    Ellipse2D.Double c = circlesArray.get(7*x + y);
                    g2.draw(c);
                    g2.fill(c);
                }
                else if (array[x][y] == YELLOW)
                {
                    g2.setColor(Color.YELLOW);
                    Ellipse2D.Double c = circlesArray.get(7*x + y);
                    g2.draw(c);
                    g2.fill(c);
                }
            }
        }
    }
    
    public void initialize(Graphics2D g2)
    {
        g2.setColor(Color.BLUE);
        Rectangle rect = new Rectangle(0, 100, 700, 600);
        g2.draw(rect);
        g2.fill(rect);
    }
    
    public void putDiscInCol(int col)
    {
        if ((getVictoriousPlayer() == RED) ||(getVictoriousPlayer() == YELLOW))
             return;
        
        if (TURN_NUM % 2 != 0) // player1's turn
        {
            switch (col)
            {
            case 1: placeDisc(0, 0, RED); break;
            case 2: placeDisc(0, 1, RED); break;
            case 3: placeDisc(0, 2, RED); break;
            case 4: placeDisc(0, 3, RED); break;
            case 5: placeDisc(0, 4, RED); break;
            case 6: placeDisc(0, 5, RED); break;
            case 7: placeDisc(0, 6, RED); break;
            }
            repaint();
            
        }
        else // player2's turn
        {
            switch (col)
            {
            case 1: placeDisc(0, 0, YELLOW); break;
            case 2: placeDisc(0, 1, YELLOW); break;
            case 3: placeDisc(0, 2, YELLOW); break;
            case 4: placeDisc(0, 3, YELLOW); break;
            case 5: placeDisc(0, 4, YELLOW); break;
            case 6: placeDisc(0, 5, YELLOW); break;
            case 7: placeDisc(0, 6, YELLOW); break;
            }
            repaint();
        }
    }
    
    public void placeDisc(int row, int col, int color)
    {
        if (row > 5)
        {    
            TURN_NUM --;
            return;
        }
        if (array[row][col] == BLANK)
            array[row][col] = color;
        else
        {
            placeDisc(row+1, col, color);
        }
    }
    
    public int getVictoriousPlayer()
    {
        // check horizontally
        for (int x = 0; x < MAX_ROW; x++)
        {
            for (int y = 0; y < MAX_COL - 3; y++)
            {
                int num = array[x][y];
                if (num == -1 && num == array[x][y+1] && num == array[x][y+2] && num == array[x][y+3])
                    return RED;
                else if (num == 1 && num == array[x][y+1] && num == array[x][y+2] && num == array[x][y+3])
                    return YELLOW;
            }
        }
        
        // check vertically
        for (int y = 0; y < MAX_COL; y++)
        {
            for (int x = 0; x < MAX_ROW - 3; x++)
            {
                int num = array[x][y];
                if (num == -1 && num == array[x+1][y] && num == array[x+2][y] && num == array[x+3][y])
                    return RED;
                else if (num == 1 && num == array[x+1][y] && num == array[x+2][y] && num == array[x+3][y])
                    return YELLOW;
            }
        }
        
        // check diagonally lower left to upper right
        for (int x = 0; x < MAX_ROW - 3; x++) 
        {
            for (int y = 0; y < MAX_COL -3; y++) 
            {
                int num = array[x][y];
                if (num == -1 && num == array[x+1][y+1] && num == array[x+2][y+2] && num == array[x+3][y+3])
                    return RED;
                else if (num == 1 && num == array[x+1][y+1] && num == array[x+2][y+2] && num == array[x+3][y+3])
                    return YELLOW;
            }
        }
        // check diagonal upper left to lower right
        for (int x = MAX_ROW - 1; x >= 3; x--) 
        {
            for (int y = 0; y < MAX_COL - 3; y++) {
                int num = array[x][y];
                if (num == -1 && num == array[x-1][y+1] && num == array[x-2][y+2] && num == array[x-3][y+3])
                    return RED;
                else if (num == 1 && num == array[x-1][y+1] && num == array[x-2][y+2] && num == array[x-3][y+3])
                    return YELLOW;
            }
        }
        return (isBoardFull()) ? DRAW : BLANK;
    }
    
    
    public void reset()
    {
        for (int x = 0; x < array.length; x++)
        {
            for (int y = 0; y < array[0].length; y++)
                array[x][y] = BLANK;
        }
        repaint();
    }
    
    public int[][] getBoard()
    {
        return array;
    }
    
    /**
     * TO_DO: write code for perform2
     */
    public boolean columnFull(int col)
    {
        return (array[MAX_ROW-1][col] != BLANK) ? true : false;
    }
    
    public ArrayList<Integer> getPossibleMoves()
    {
        ArrayList<Integer> possibleMovesList = new ArrayList<Integer>();
        for (int x = 0; x < MAX_COL; x++)
        {
            if (!columnFull(x))
                possibleMovesList.add(x);
        }
        return possibleMovesList;
    }
    
    public void perform(int move, boolean side) //move is from 0-6, assume column is not full
    {
        if (move == -1)
            return;
        if (side == COMP_TURN)
            placeDisc(0, move, YELLOW);
        else
            placeDisc(0, move, RED);
    }
    
    public void undo(int col)
    {
        if (columnFull(col))
            array[MAX_ROW-1][col] = BLANK;
        else
        {
            for (int x = 1; x < MAX_ROW; x++)
            {
                if (array[x][col] == BLANK)
                {
                    array[x-1][col] = BLANK;
                    return;
                }
            }
        }
    }
    
    public boolean isBoardFull()
    {
        for (int row = 0; row < MAX_ROW; row++)
        {
            for (int col = 0; col < MAX_COL; col++)
            {
                if (array[row][col] == BLANK)
                    return false;
            }
        }
        return true;
    }
    
    public int evaluateContent()
    {
    	int sum = 0;
    	for (int i = 0; i < MAX_ROW; i++)
    	{
    		for (int j = 0; j < MAX_COL; j++)
    		{
    			if (array[i][j] == YELLOW)
    				sum += evaluationTable[i][j];
    			else if (array[i][j] == RED)
    				sum -= evaluationTable[i][j];
    		}
    	}
    	return  sum;
    }  
    
    public int miniMaxDecision()
    {
    	miniMaxValue(0, YELLOW);
    	return maxColumn;
    }
    
    public int miniMaxValue(int depth, int player)
    {
    	if (getVictoriousPlayer() != BLANK && getVictoriousPlayer() != DRAW)
    	{
    		if (getVictoriousPlayer() == YELLOW)
    			return 1000 - depth; 
    		else
    			return -1000 + depth;
    	}
    	
    	if (depth == MAX_DEEP || getVictoriousPlayer() == DRAW)
    		return evaluateContent();
    	
    	depth++;
    	
    	if (player == YELLOW)
    	{
    		int max = Integer.MIN_VALUE;
    		int column = 0;
    		for(int j = 0; j < MAX_COL; j++)
        	{
        		if(!columnFull(j))
        		{
        			perform(j, COMP_TURN);
        			int value = miniMaxValue(depth, 0 - player);
        			if (max < value)
        			{
        				max = value;
        				column = j;
        			}
        			undo(j);
        		}
        	}
    		maxColumn = column;
    		return max;
    	}
    	else
    	{
    		int min = Integer.MAX_VALUE;
    		for(int j = 0; j < MAX_COL; j++)
        	{
        		if(!columnFull(j))
        		{
        			perform(j, HUMAN_TURN);
        			int value = miniMaxValue(depth, 0 - player);
        			if (min > value)
        				min = value;
        			undo(j);
        		}
        	}
    		return min;
    	}
    }
}