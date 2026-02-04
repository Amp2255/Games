package games;
import java.awt. *;
import java.util.*;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing. JOptionPane;
import javax.swing.Timer;

public class TictactoeUltimate extends JFrame  {

    static char[][] board = {
            {' ',' ',' '},
            {' ',' ',' '},
            {' ',' ',' '}
        };

    static char currentPlayer ='X';

    private static void changePlayer(){
        currentPlayer = (currentPlayer=='X')? 'O' : 'X';
    }

    static boolean vsComputer;

    public static void main(String[] args) {
    
    setGameMode();    
    
    }

    private static void setGameMode() {
        currentPlayer = 'X';
        
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            board[i][j] = ' ';
        }
    }
       int choice = askForGameMode();
        if (choice == -1) {
        System.exit(0); // user closed dialog
    }
        vsComputer = (choice == 1); // true if Vs Computer

        if (vsComputer) {
            createWindowForComputer();
        } else {
    // Multiplayer → switch player
            createWindowForMultiplayer();
        }
    }

    private static int askForGameMode() {
        String[] options = {"Multiplayer", "Vs Computer"};
        return JOptionPane.showOptionDialog(
            null,
            "Choose Game Mode",
            "Tic-Tac-Toe",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
    );
    }

    private static void createWindowForMultiplayer() {
        JFrame wiFrame = new JFrame();
        wiFrame.setTitle("Tic-Tac-Toe");
        wiFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        wiFrame.setLocationRelativeTo(null);
        wiFrame.setSize(500,500);
        wiFrame.setLayout(new GridLayout(3, 3));
        
        JButton [][] buttons = new JButton[3][3] ; 
        for(int i=0;i<3; i++){
            for(int j=0;j<3; j++){
                buttons[i][j] = new JButton();
                setButtonStyle(buttons[i][j]);
                final int row =i;
                final int col=j;
                buttons[i][j].addActionListener(e->handleButtonClick(e, row, col, buttons, wiFrame));
                wiFrame.add(buttons[i][j]);
            }
        }  
        wiFrame.setVisible(true);
    }
    
    private static void handleButtonClick(java.awt.event.ActionEvent e, int row, int col, JButton[][] buttons, JFrame wiFrame) {
        if (board[row][col] != ' ') {
    JOptionPane.showMessageDialog(wiFrame, "Invalid choice");
    return;
}
        if(board[row][col]==' '){
            updateButton(buttons[row][col], row, col);
        }
        
        if (checkWinner(currentPlayer)) {
            JOptionPane.showMessageDialog(wiFrame, "Player " + currentPlayer + " wins!");
            resetButtonsAndBoard(buttons,board,wiFrame);
            setGameMode();    
            return;
        } 
        if (checkBoardFull()) {
            JOptionPane.showMessageDialog(wiFrame, "It's a draw!");
            resetButtonsAndBoard(buttons,board,wiFrame);
            setGameMode();
            return;
        } 
        changePlayer();
    }
    
    private static void updateButton(JButton button, int row, int col) {
        board[row][col]=currentPlayer;
        button.setText(String.valueOf(currentPlayer));
        button.setBackground(Color.LIGHT_GRAY);
        button.setOpaque(true);
        if(currentPlayer=='X')
            button.setForeground(Color.BLUE);
        else
            button.setForeground(Color.RED);
    }
    
    private static void setButtonStyle(JButton jButton) {
        jButton.setFont(new Font("Arial", Font.BOLD, 25));
    }


    private static boolean checkBoardFull(){
         for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j] ==' ')
                    return false;
            }
        }
        return true;
    }

    private static boolean checkWinner(char currentPlayer){

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
    private static void resetButtonsAndBoard(JButton[][] buttons,char[][] board,JFrame wiFrame){
         
         for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText(" ");
                buttons[i][j].setBackground(null);
                buttons[i][j].setEnabled(true);
                board[i][j] = ' ';

            }}
            wiFrame.dispose();
    }

    private static void createWindowForComputer() {
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
        if (checkWinner(currentPlayer)) {
            disableAllButtons(buttons);
            JOptionPane.showMessageDialog(wiFrame, "You won!");
           resetButtonsAndBoard(buttons,board,wiFrame);
            setGameMode();    
            return;
        }
        if (checkBoardFull()) {
            JOptionPane.showMessageDialog(wiFrame, "It's a draw!");
           resetButtonsAndBoard(buttons,board,wiFrame);
setGameMode();
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

                    if (checkWinner(currentPlayer)) {
                        disableAllButtons(buttons);
                        JOptionPane.showMessageDialog(wiFrame, "Computer wins!");
                       resetButtonsAndBoard(buttons,board,wiFrame);
                        setGameMode();    
                        return;                        
                    } 
                    if (checkBoardFull()) {
                        JOptionPane.showMessageDialog(wiFrame, "It's a draw!");
                       resetButtonsAndBoard(buttons,board,wiFrame);
                        setGameMode();    
                        return;   
                    }
                    wiFrame.setTitle("Tic-Tac-Toe");
                    currentPlayer ='X'; 
    }

    private static int[] checkWinLogic(List<int[]> emptyCellList) {
        
        for (int[] pos : emptyCellList) {
            board[pos[0]][pos[1]] = 'O'; //Can AI win?
            if(checkWinner('O')){
                board[pos[0]][pos[1]] = ' ';
                return pos;
            }
            board[pos[0]][pos[1]] = ' ';
            }
        for (int[] pos : emptyCellList) {
            board[pos[0]][pos[1]] = 'X'; //Can X win?
            
            if(checkWinner('X')){
                board[pos[0]][pos[1]] = ' ';
                
                return pos;
            }
            board[pos[0]][pos[1]] = ' ';
            
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


