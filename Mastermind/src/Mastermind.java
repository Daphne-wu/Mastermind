import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Mastermind implements ActionListener, MouseListener{


	//GridGuess gg = new GridGuess(rows,cols);
	JFrame frame = new JFrame();
//	GamePanel panel = new GamePanel();
	JButton newGame = new JButton("Reset");
	JButton check = new JButton("Check");
	JButton manuel = new JButton("manuel");
	JButton blue = new JButton("Blue");
	JButton green = new JButton("Green");
	JButton orange = new JButton("Orange");
	JButton purple = new JButton("Purple");
	JButton red = new JButton("Red");
	JButton yellow = new JButton("Yellow");
	Container west = new Container();
	Container east = new Container();
	Container center = new Container();
	Container south = new Container();
	final int BLUE = 0;
	final int GREEN = 1;
	final int ORANGE = 2;
	final int PURPLE = 3;
	final int RED = 4;
	final int YELLOW = 5;
	final int WHITE = 6;
	final int BLACK = 7;
	private static final int NONE = -1;
	int color = -1;
	int[] guess = new int[4];
	int activeRow = 1;
	
	static int MAX_ROWS = 10;
	boolean running = false;
	
	ArrayList<JButton> gridButton = new ArrayList<JButton>();
	ArrayList<JButton> resultPegs = new ArrayList<JButton>();
	
	
	public Mastermind() {
		
		frame.setSize(600,800);
		frame.setLayout(new BorderLayout());
		frame.add(west, BorderLayout.WEST);
		west.setLayout(new GridLayout(6,1));
		west.add(blue);
		blue.addActionListener(this);
		blue.setBackground(Color.BLUE);
		west.add(green);
		green.addActionListener(this);
		green.setBackground(Color.GREEN);
		west.add(orange);
		orange.addActionListener(this);
		orange.setBackground(Color.ORANGE);
		west.add(purple);
		purple.addActionListener(this);
		purple.setBackground(new Color(161,43,204));
		west.add(red);
		red.addActionListener(this);
		red.setBackground(Color.RED);
		west.add(yellow);
		yellow.addActionListener(this);
		yellow.setBackground(Color.YELLOW);
		
		//sets the 
		frame.add(south, BorderLayout.SOUTH);
		south.setLayout(new GridLayout(1,3));
		south.add(newGame);
		newGame.addActionListener(this);
		newGame.setBackground(Color.LIGHT_GRAY);
		south.add(manuel);
		manuel.addActionListener(this);
		manuel.setBackground(Color.LIGHT_GRAY);
		south.add(check);
		check.addActionListener(this);
		check.setBackground(Color.LIGHT_GRAY);
		
		//Add center and east panel
		
		resetGrid();
		//setEnabledRow(activeRow, true);
		frame.add(center, BorderLayout.CENTER);

		frame.add(east, BorderLayout.EAST);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	
	private void resetGrid() {
		activeRow = 1; //

		guess[0] = getRandomNum(0, 5); // Since color index are from 0 to 5
		guess[1] = getRandomNum(0, 5);
		guess[2] = getRandomNum(0, 5);
		guess[3] = getRandomNum(0, 5);

		for (int i = 0; i < guess.length; i++) {
		System.out.println(guess[i]);
		}

		// sets 10 by 4 grid
		centerPanel();
		eastGrid();

		setEnabledRow(activeRow, true);
		frame.repaint();
	}
			
	private void eastGrid() {
		east.removeAll();
		east.setLayout(new GridLayout(10, 4));
		
		resultPegs = new ArrayList<JButton>();
		
		for (int i = 0; i < 40; i++) { //fills in 40 buttons
		      JButton resultPeg = new JButton(" ");
		      resultPeg.setEnabled(false);
		      east.add(resultPeg); //adds to east container
		      resultPegs.add(resultPeg); //adds the buttons to the arraylist..
		    }
		east.revalidate();
		east.repaint();
		
	}
	
	private void centerPanel() {
		center.removeAll();
		center.setLayout(new GridLayout(10,4));
		
		gridButton = new ArrayList<JButton>();
		
		for (int i = 0; i < 40; i++) {
			JButton button = new JButton("");
			center.add(button);
			gridButton.add(button);
			button.addActionListener(this);
			button.setEnabled(false);
		}
		
		
		center.revalidate();
		center.repaint();
		
	}
	
	
	public static int getRandomNum(int min, int max){ //static means it exists till the end of the program as a constant
		int x = (int)(Math.random()*((max-min)+1))+min;
		return x; //returns the random integer that us used for the final code
	}
	/*
	 * int freq = frequency(guess, 4, guess[i]);
			if(freq > 1) {
				if(guess[i] == getButtonColor(gridButton.get(row + i))) { //if current cell is same as code in that position
					resultPegs.get(row + i).setBackground(Color.WHITE);;
					correct++;
					freq--;
				}
			}
			else if(freq == 1) {
				if(guess[i] == getButtonColor(gridButton.get(row + i))) { //if current cell is same as code in that position
					resultPegs.get(row + i).setBackground(Color.WHITE);;
					correct++;
				}
				else if(getButtonColor(gridButton.get(row + i)) == guess[0] || getButtonColor(gridButton.get(row + i)) == guess[1] ||
						getButtonColor(gridButton.get(row + i)) == guess[2] || getButtonColor(gridButton.get(row + i)) == guess[3]) 
					resultPegs.get(row + i).setBackground(Color.BLACK);
			}
	 */
	
	private void displayPegs(int row) {
		int correct = 0;
		row = (row - 1) * 4;
		int freq = frequency(guess, guess[0]);
		if(freq >= 1) {
			for (int i = 0; i < 4; i++) {
					if(guess[0] == getButtonColor(gridButton.get(row + i))) { //if current cell is same as code in that position
						resultPegs.get(row+i).setBackground(Color.WHITE);
						correct++;
						freq--;
						int y = freq;
					}
			}
			for (int i = 0; i < 4; i++) {
				if(!(freq == 0)) {
					if(!(getPegColor(resultPegs.get(row + i)) == 6)) {//white 
						resultPegs.get(row + i).setBackground(Color.BLACK);
					}
				}
			}
		}
		freq = frequency(guess, guess[1]);
		if(freq >= 1) {
			for (int i = 0; i < 4; i++) {
					if(guess[1] == getButtonColor(gridButton.get(row + i))) { //if current cell is same as code in that position
						resultPegs.get(row+i).setBackground(Color.WHITE);
						correct++;
						freq--;
						int y = freq;
					}
			}
			for (int i = 0; i < 4; i++) {
				if(!(freq == 0)) {
					if(!(getPegColor(resultPegs.get(row + i)) == 6)) {//white 
						resultPegs.get(row + i).setBackground(Color.BLACK);
					}
				}
			}
		}
		freq = frequency(guess, guess[2]);
			if(freq >= 1) {
				for (int i = 0; i < 4; i++) {
						if(guess[2] == getButtonColor(gridButton.get(row + i))) { //if current cell is same as code in that position
							resultPegs.get(row+i).setBackground(Color.WHITE);
							correct++;
							freq--;
							int y = freq;
						}
				}
				for (int i = 0; i < 4; i++) {
					if(!(freq == 0)) {
						if(!(getPegColor(resultPegs.get(row + i)) == 6)) {//white 
							resultPegs.get(row + i).setBackground(Color.BLACK);
						}
					}
				}
			}
		freq = frequency(guess, guess[3]);
			if(freq >= 1) {
				for (int i = 0; i < 4; i++) {
						if(guess[3] == getButtonColor(gridButton.get(row + i))) { //if current cell is same as code in that position
							resultPegs.get(row+i).setBackground(Color.WHITE);
							correct++;
							freq--;
							int y = freq;
						}
				}
				for (int i = 0; i < 4; i++) {
					if(!(freq == 0)) {
						if(!(getPegColor(resultPegs.get(row + i)) == 6)) {//white 
							resultPegs.get(row + i).setBackground(Color.BLACK);
						}
					}
				}
			}
			if(correct == 4) { //all four are correct
				JOptionPane.showMessageDialog(frame, "You won!");
				activeRow = MAX_ROWS+1;
			}
	
		
	}
	//count how many of the same numbers is in the guess
	public int frequency(int[] a, int x) { //n = guesslength, x = compared int
	    int count = 0; 
	    for (int i=0; i < a.length; i++) 
	       if (a[i] == x)  
	          count++; 
	    return count; 
	} 
			 
	
	private void setEnabledRow(int row, boolean status) {
		if(row > MAX_ROWS) return;
		row = (row-1) * 4; //bc there are 4 columns, and it  0  1  2  3    row#1   //if it fills in starting at 0, then we can refer directly 
							//								 4  5  6  7		    2  to each row as a multiple of four. 
													//		 8  9 10 11         3   so row# 5 would be (5-1) * 4 = 16, the starting value of the 5th row...
													//       12 13 14 15        4
													//		 16 17 18 19        5
													//		 20 21 22 23		6
													//		 24 25 26 27		7
													//		 28 29 30 31		8
													//		 32 33 34 35		9
													//		 36 37 38 39		10
		
		for (int i = 0; i < 4; i++) {
			gridButton.get(row + i).setEnabled(status);
		}
	}
	
	private boolean isCurrentRowCompleted (int row) {
		if(row > MAX_ROWS) return false;
		int count = 0; 
		row = (row - 1) * 4;
		for (int i = 0; i < 4; i++) {
			if(!"".equals(gridButton.get(row + i).getText())) { //if there isnt a blank "", as in if there is something written
				count++;
			}
		}
			return (count == 4);
		}
	
	private int getPegColor(JButton e) {
		int returnColor = NONE;
		if(e.getBackground().equals(Color.WHITE)) {
			returnColor = WHITE;
		}
		if(e.getBackground().equals(Color.BLACK)) {
			returnColor = BLACK;
		}
		return returnColor;
	}
	private int getButtonColor (JButton e) {
		int returnColor = NONE;
		if(e.getText().equals(blue.getText())) {
			returnColor = BLUE;
		}
		if(e.getText().equals(red.getText())) {
			returnColor = RED;
		}
		if(e.getText().equals(orange.getText())) {
			returnColor = ORANGE;
		}
		if(e.getText().equals(purple.getText())) {
			returnColor = PURPLE;
		}
		if(e.getText().equals(yellow.getText())) {
			returnColor = YELLOW;
		}
		if(e.getText().equals(green.getText())) {
			returnColor = GREEN;
		}
		
		return returnColor;
		
	}
	/*	private void clear(ArrayList<JButton> array, ArrayList<JButton> arrayTwo) {
			for (int i = 0; i < array.size(); i++) {
				if(array.get(i).getButtonColor(i) ==  {
				array.get(i).setBackground(new JButton().getBackground());
				array.get(i).setText("");
				}
			}
			for (int i = 0; i < arrayTwo.size(); i++) {
				arrayTwo.get(i).setBackground(new JButton().getBackground());
			}
			setEnabledRow(activeRow, false);
			activeRow = 1;
			setEnabledRow(1, true);
		}
	*/
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource().equals(newGame)) {
			resetGrid();
			return;
		}
		
		if(activeRow > MAX_ROWS) { //restart the game if they have already played.
			JOptionPane.showMessageDialog(frame, "Aw darn, no more tries. Restart");
			return;
		}
		
		if(e.getSource().equals(blue)) {
			color = BLUE;
		}
		if(e.getSource().equals(green)) {
			color = GREEN;
		}
		if(e.getSource().equals(orange)) {
			color = ORANGE;
		}
		if(e.getSource().equals(purple)) {
			color = PURPLE;
		}
		if(e.getSource().equals(red)) {
			color = RED;
		}
		if(e.getSource().equals(yellow)) {
			color = YELLOW;
		}
		
		for (int i = 0; i < gridButton.size(); i++) {
			if(e.getSource().equals(gridButton.get(i))) {
				if(color == 0) {
					gridButton.get(i).setBackground(Color.BLUE);
					gridButton.get(i).setText(blue.getText());
				}
				if(color == 1) {
					gridButton.get(i).setBackground(Color.GREEN);
					gridButton.get(i).setText(green.getText());
				}
				if(color == 2) {
					gridButton.get(i).setBackground(Color.ORANGE);
					gridButton.get(i).setText(orange.getText());
				}
				if(color == 3) {
					gridButton.get(i).setBackground(new Color(161,43,204));
					gridButton.get(i).setText(purple.getText());
				}
				if(color == 4) {
					gridButton.get(i).setBackground(Color.RED);
					gridButton.get(i).setText(red.getText());
				}
				if(color == 5) {
					gridButton.get(i).setBackground(Color.YELLOW);
					gridButton.get(i).setText(yellow.getText());
				}
			}
				
			//will check the buttons for colors, places the pegs, enables the next row 
				if(isCurrentRowCompleted(activeRow) && e.getSource().equals(check)){
					displayPegs(activeRow);					
					setEnabledRow(activeRow, false); //disables current row 
					activeRow++;//enables the next row
				}			
				if(activeRow == MAX_ROWS + 1) {
					JOptionPane.showMessageDialog(frame, "Ah man, no more tries");
					return;
				} else {
					setEnabledRow(activeRow, true);
				}					
		}	
	}
		
	public static void main(String []args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		new Mastermind();
	}
}
	