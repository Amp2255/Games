package games;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.awt. *;
import java.util.*;
import java.util.List;

public class TictactoeWithComputerWinLogic extends JFrame  {

    static char[][] board = {
            {' ',' ',' '},
            {' ',' ',' '},
            {' ',' ',' '}
        };

    static char currentPlayer ='X';

    

    
    public static void main(String[] args) {
        createWindow();
        
    }

    private static void createWindow() {
        JFrame wiFrame = new JFrame();
        wiFrame.setTitle("Tic-Tac-Toe");
        wiFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        wiFrame.setLocationRelativeTo(null);
        wiFrame.setSize(500,500);
        wiFrame.setLayout(new GridLayout(3, 3));
        
        JButton [][] buttons = new JButton[3][3] ; 
        for(int i=0;i<3; i++){
            for(int j=0;j<3; j++){
                buttons[i][j] = new JButton(); // Create button
                setButtonStyle(buttons[i][j]);
                 final int row =i;
                 final int col=j;
                buttons[i][j].addActionListener(e -> handleButtonClick(row, col, buttons, wiFrame));
                wiFrame.add(buttons[i][j]);
            }
        }  
        wiFrame.setVisible(true);
        
    }

    private static void handleButtonClick(int row, int col, JButton[][] buttons, JFrame wiFrame) {
        if(currentPlayer !='X') return;
        if(board[row][col] !=' ') {
            JOptionPane.showMessageDialog(wiFrame, "Invalid choice");
            return;
        }    
        makePlayerMove(row, col, buttons);
        if (checkWinner()) {
            disableAllButtons(buttons);
            JOptionPane.showMessageDialog(wiFrame, "You won!");
            resetButtonsAndBoard(buttons,board);
            return;
        }
        if (checkBoardFull()) {
            JOptionPane.showMessageDialog(wiFrame, "It's a draw!");
            resetButtonsAndBoard(buttons,board);
            return;   
        } 
        delayComputerMove(buttons, wiFrame);
    }

    private static void makePlayerMove(int row, int col, JButton[][] buttons) {
        board[row][col] = currentPlayer;
        buttons[row][col].setText(String.valueOf(currentPlayer));
        buttons[row][col].setBackground(Color.LIGHT_GRAY);
        buttons[row][col].setOpaque(true);
        if(currentPlayer=='X') {
            buttons[row][col].setForeground(Color.BLUE);
        } else {
            buttons[row][col].setForeground(Color.RED);
        }
    }
    
    private static void setButtonStyle(JButton jButton) {
        jButton.setFont(new Font("Arial", Font.BOLD, 25));
    }


    private static boolean checkBoardFull(){
        boolean boardFull = true;
         for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j] ==' ')
                    return false;
            }
        }
        return boardFull;
    }

    private static boolean checkWinner(){

         if (board[0][0] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][2] == currentPlayer) return true;

        if (board[0][2] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][0] == currentPlayer) return true;

         for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer &&
                board[i][1] == currentPlayer &&
                board[i][2] == currentPlayer) return true;

            if (board[0][i] == currentPlayer &&
                board[1][i] == currentPlayer &&
                board[2][i] == currentPlayer) return true;
            }
            return false;
    }

    private static void disableAllButtons(JButton[][] buttons){
         for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setEnabled(false);
            }}
    }
    private static void resetButtonsAndBoard(JButton[][] buttons,char[][] board){
         for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText(" ");
                buttons[i][j].setBackground(null);
                board[i][j] = ' ';

            }}
    }

    private static void delayComputerMove(JButton[][] buttons, JFrame wiFrame) {
        currentPlayer = 'O';
        wiFrame.setTitle("Computer thinking!");
        Timer timer = new Timer(700, e -> computerPlays(buttons, wiFrame));

        timer.setRepeats(false); // run only once
        timer.start();
        
    }


    private static void computerPlays(JButton[][] buttons, JFrame wiFrame){
        
       
        List<int[]> emptyCellList= checkForEmptyCells();
        //get a random position from the above list
        int[] nextMove = checkWinLogic(emptyCellList);
   
        int nextRow= nextMove[0];
        int nextCol= nextMove[1];
        
        if(board[nextRow][nextCol]==' '){
                        board[nextRow][nextCol]=currentPlayer;
                        buttons[nextRow][nextCol].setText(String.valueOf(currentPlayer));
                        buttons[nextRow][nextCol].setForeground(Color.RED);
                    }

                    if (checkWinner()) {
                        disableAllButtons(buttons);
                        JOptionPane.showMessageDialog(wiFrame, "Computer wins!");
                        resetButtonsAndBoard(buttons,board);
                        return;                        
                    } 
                    if (checkBoardFull()) {
                        JOptionPane.showMessageDialog(wiFrame, "It's a draw!");
                        resetButtonsAndBoard(buttons,board);
                        return;   
                    }
                    wiFrame.setTitle("Tic-Tac-Toe");
                    currentPlayer ='X'; 
    }

    private static int[] checkWinLogic(List<int[]> emptyCellList) {
        
        for (int[] pos : emptyCellList) {
            board[pos[0]][pos[1]] = 'O'; //Can AI win?
            if(checkWinner()){
                board[pos[0]][pos[1]] = ' ';
                return pos;
            }
            board[pos[0]][pos[1]] = ' ';
            }
        for (int[] pos : emptyCellList) {
            board[pos[0]][pos[1]] = 'X'; //Can X win?
            currentPlayer = 'X';
            if(checkWinner()){
                board[pos[0]][pos[1]] = ' ';
                currentPlayer = 'O';
                return pos;
            }
            board[pos[0]][pos[1]] = ' ';
            currentPlayer ='O';
            }    
        
        if(board[1][1] == ' '){
            return new int[]{1,1};
        }
            
        
        return emptyCellList.get(0);
    }

    private static List<int[]>  checkForEmptyCells() {
        List<int[]> emptyCellsList= new ArrayList<>();
        for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                
                if(board[i][j] == ' '){
                    emptyCellsList.add(new int[]{i,j} );
                }
            }
        }
        return emptyCellsList;
        }
}
