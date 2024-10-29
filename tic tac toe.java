import java.lang.*;
import java.util.*;
class main{
    public static void main(String[] args) {
        char[][]board=new char[3][3];
        for(int row=0;row<board.length;row++){
            for(int col=0;col<board.length;col++){
                board[row][col]=' ';
            }
        }
//        printboard(board);
        char player='X';
        Scanner sc=new Scanner(System.in);
        boolean winner=false;
        while(!winner) {
            printboard(board);
            System.out.println("enter " + player + " position");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if (board[row][col]== ' ') {
                board[row][col] = player;
                winner = win(board, player);
                if (winner) {
                    System.out.println(player + "is win🔥🔥");
                } else {
                    if (player == 'X') {
                        player = 'O';
                    } else {
                        player = 'X';
                    }
                }
            } else {
                System.out.println("invild move !! pleace try again🤔");
            }
        }
        printboard(board);
   }

    private static void printboard(char[][] board) {
        for(int row=0;row<board.length;row++){
            for(int col=0;col<board.length;col++){
                System.out.print(" "+board[row][col]+" |");
            }
            System.out.println();
        }
    }

    private static boolean win(char[][] board, char player) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }
        for (int col = 0; col < board.length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
     return false;

    }
}