

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TicTacToeCanvas 
extends JFrame 
implements ActionListener{
	
	private JButton button[][];
	private static final int Button_Size=110;
	public String Board[][];
	private boolean isMax=false;
	private int depth=0 ;
	private Move move;
	private boolean isWinner=false;
	private boolean isPlayerWon=false;
	private boolean isDraw=false;
	public TicTacToeCanvas(){
		super("Tic Tac Toe");
		//JOptionPane.showMessageDialog(this, "First Move is yours \nPress ok To start the game", "Move", JOptionPane.INFORMATION_MESSAGE);
		move =new Move();
		boardInit();
		makeButtons();
		/*
		if(isPlayerWon==true)
			makeComputerMove();
		*/
		generateDefaultBoard();
		this.setLayout(null);
		this.setSize(new Dimension(360,380));
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	public void generateDefaultBoard() {
		for(int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				button[i][j].setText("");
				Board[i][j]="_";
			}
		}
		//if(isPlayerWon==true) makeComputerMove();
	}
	public void boardInit() {
		Board= new String[3][3];
		for (int x=0;x<3;x++) {
			for(int y=0;y<3;y++) {
				Board[x][y]="_";
				//System.out.print(Board[x][y]+" ");
			}
			//System.out.println();
		}
	}


	public boolean isMoveLeft() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(Board[i][j]=="_")
					return true;
			}
		}
		return false;
	}
	

	public int checkWinner() {
		
		if(!isMoveLeft()) {
			isWinner=true;
			return 0;
		}
		for(int row=0;row<3;row++) {
			if(Board[row][0]==Board[row][1] && Board[row][0]==Board[row][2] && Board[row][0]!="_") {
					 
					isWinner = true;
					if(Board[row][0]==Player.COMPUTER) {
						return 1;
					}
					else return -1;
					//JOptionPane.showMessageDialog(this, "win");
				}
				//JOptionPane.showMessageDialog(this, "win");
		}
		
		
		//for column check
		
		for (int col=0;col<3;col++) {
			if(Board[0][col]==Board[1][col] && Board[0][col]==Board[2][col] && Board[0][col]!="_") {
				 
				isWinner = true;
				if(Board[0][col]==Player.COMPUTER) {
					return 1;
				}
				else return -1;
				//JOptionPane.showMessageDialog(this, "win");
			}
		}
		
		//for diagonal check
	    if (Board[0][0]==Board[1][1] && Board[1][1]==Board[2][2] && Board[1][1]!="_") 
	    { 
	    	
	    	isWinner = true;
			 if(Board[0][0]==Player.COMPUTER) {
				 return 1;
			 }
			 else return -1;
	    	
	    } 
	  
	    if (Board[0][2]==Board[1][1] && Board[1][1]==Board[2][0] && Board[1][1]!="_") 
	    { 
	    	
	    	isWinner = true;
			 if(Board[0][0]==Player.COMPUTER) {
				 return 1;
			 }
			 else return -1;
	    	//JOptionPane.showMessageDialog(this, "win");
	    } 
	    
	    return 10;
	    
	}
	
	public void showWinner() {
		
		int value;
		value=checkWinner();
	
		if(isWinner) {
			if(value==1) {
				isPlayerWon=false;
				JOptionPane.showMessageDialog(this, "win "+Player.COMPUTER);
				
			}
			else if(value==-1) {
				isPlayerWon=true;
				JOptionPane.showMessageDialog(this, "win "+Player.PLAYER);
				
			}
			else {
				if(isPlayerWon) isPlayerWon=false;
				else isPlayerWon=true;
				isDraw=true;
				JOptionPane.showMessageDialog(this, "DRAW");
			}
			generateDefaultBoard();
			isWinner=false;
		}
	}
	

	public int evaluate() {
		//for Row Check
		
		for(int row=0;row<3;row++) {
			if(Board[row][0]==Board[row][1] && Board[row][0]==Board[row][2] ) {
				if (Board[row][0]==Player.PLAYER) 
	                return -10; 
	            else if (Board[row][0]==Player.COMPUTER) 
	                return +10; 
			}
		}
		
		//for column check
		
		for (int col=0;col<3;col++) {
			if(Board[0][col]==Board[1][col] && Board[0][col]==Board[2][col]) {
				if(Board[0][col]==Player.PLAYER)
					return -10;
				else if(Board[0][col]==Player.COMPUTER) {
					return +10;
				}
						
			}
		}
		
		//for diagonal check
	    if (Board[0][0]==Board[1][1] && Board[1][1]==Board[2][2]) 
	    { 
	        if (Board[0][0]==Player.COMPUTER) 
	            return +10; 
	        else if (Board[0][0]==Player.PLAYER) 
	            return -10; 
	    } 
	  
	    if (Board[0][2]==Board[1][1] && Board[1][1]==Board[2][0]) 
	    { 
	        if (Board[0][2]==Player.COMPUTER) 
	            return +10; 
	        else if (Board[0][2]==Player.PLAYER) 
	            return -10; 
	    } 
		
		return 0;
	}
	
	public int miniMax(String Board[][],int depth,boolean isMax) {
		int Score = evaluate();
		
		if(Score==10 || Score==-10) {
			return Score;
		}
		if(!isMoveLeft())
			return 0;
		
		if(isMax) {
			int best=-1000;
			
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					
					if(Board[i][j]=="_") {
						
						Board[i][j]=Player.COMPUTER;
						
						best= Math.max(best,miniMax(Board,depth+1,false));
						//undo the move after every step
						Board[i][j]="_";
					}
				}
			}
			return best;
		}
		else {
			int best=1000;
			
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					
					if(Board[i][j]=="_") {
						
						Board[i][j]=Player.PLAYER;
						
						best=Math.min(best,miniMax(Board,depth+1,true));
						//undo the move after every step
						Board[i][j]="_";
					}
				}
			}
			return best;
		}
	}
	
	public void findBestMove() {
		
		
		int bestVal=-1000;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				
				if(this.Board[i][j]=="_") {
					this.Board[i][j]=Player.COMPUTER;
					
					int moveVal=miniMax(this.Board,0,this.isMax);
					
					Board[i][j]="_";
					
					if(moveVal>bestVal) {
						move.ROW=i;
						move.COL=j;
						bestVal=moveVal;
					}
				}
			}
		}
	}
	
	
	public void makeComputerMove () {
		/*false because
		 * in minimax()
		 1.first move is selected for player and then next minimax call the function for minimizing the
		 advantage of opponent
		 */
		
		this.isMax=false;
		if(isDraw==false) {
		showWinner();
		}else{isDraw=false;}
		findBestMove();
		button[move.ROW][move.COL].setForeground(Color.RED);
		button[move.ROW][move.COL].setText(Player.COMPUTER);
		Board[move.ROW][move.COL]=Player.COMPUTER;
		showWinner();
		
	}
	
	
	public void makeButtons() {
		String s= "";
		Font font= new Font("Comic Sans MS", Font.PLAIN, 60);
		button=new JButton[3][3];
		for (int x=0;x<3;x++) {
			for(int y=0;y<3;y++) {
	
				button[x][y]=new JButton(s);
				button[x][y].setBounds(y*(Button_Size+3)+3,x*(Button_Size+3)+3,Button_Size,Button_Size);
				button[x][y].setBackground(Color.WHITE);
				button[x][y].setForeground(Color.BLUE);
				button[x][y].setFocusable(false);
				button[x][y].setFont(font);
				button[x][y].addActionListener(this);
				this.getContentPane().add(button[x][y]);

			}
		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton buttonPressed= (JButton) e.getSource();
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(button[i][j]== buttonPressed) {
					button[i][j].setForeground(Color.BLUE);
					button[i][j].setText(Player.PLAYER);
					this.Board[i][j]=Player.PLAYER;
					break;
				}
			}
		}
		makeComputerMove();
		
	}
	
	public static void main(String...s) {
		new TicTacToeCanvas();
	}
}
