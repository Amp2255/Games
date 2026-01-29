package games;

import java.util.Scanner;

public class TictactoeConsole {

    static char[][] board = {
            {' ',' ',' '},
            {' ',' ',' '},
            {' ',' ',' '}
        };

    static char currentPlayer ='X';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
        printBoard();
        gameMove(sc);

        if(checkWinner()){
            printBoard();
            System.out.println(currentPlayer+" wins!!!");
            break;
        }
        if(checkBoardFull()){
            printBoard();
            System.out.println("It's a draw!!!");
            break;
        }
           
        changePlayer();
        }
        
        

        sc.close();
        
    }
    private static void changePlayer(){

        currentPlayer = (currentPlayer=='X')? '0' : 'X';

    }
    private static void printBoard() {
         System.out.println("--+---+--");
       for(int i=0; i<3;i++){
        for(int j=0;j<3;j++){
            if(j==2){
                System.out.print(board[i][j]);
            }
            else System.out.print(board[i][j] +" | ");
           
           
        }
         System.out.println();
         System.out.println("--+---+--");
         

       }
    }

    private static void gameMove(Scanner sc){
        
        System.out.println("Hi Player "+ currentPlayer + ".Choose the row (1-3)  ");
        int row = sc.nextInt()-1;
        System.out.println("Hi Player "+ currentPlayer + ".Now choose the column number (1-3) ");
        int col = sc.nextInt()-1;
        
        if((row >=0 && row <3 ) && (col >=0 && col <3) && board[row][col]==' '){
            board[row][col]= currentPlayer;
        }
        else {
            System.out.println("Invalid move! Try again");
            gameMove(sc);
        }
       
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

    
}
