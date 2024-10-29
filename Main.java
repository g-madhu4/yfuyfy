import java.lang.*;
import java.util.*;
class main{
    public static void main(String[] args) {
        int[][]sudoku=new int[9][9];
        making(sudoku,0,0);
    }

    private static void making(int[][] sudoku, int row, int col) {
        if(row==sudoku.length){
            print(sudoku);
            System.out.println("mmmmmm");
            return;
        }
        if(col==sudoku.length){
            making(sudoku,row+1,0);
            return;
        }
        if(sudoku[row][col]!=0){
            making(sudoku,row,col+1);
        }
        else {
            for (int i = 1; i < 10; i++) {
                if (isOkay(sudoku, row, col, i)) {
                    sudoku[row][col] = i;
                    making(sudoku, row, col + 1);
                    sudoku[row][col] = 0;

                }
            }
        }

    }

    private static void print(int[][] sudoku) {
        for(int[]a:sudoku){
            for(int x:a){
                System.out.print(x+" ");
            }
            System.out.println(" ");
        }
    }


    private static boolean isOkay(int[][] sudoku, int row, int col, int val) {
        for(int i=0;i<sudoku.length;i++){
            if(sudoku[row][i]==val){
                return false;
            }
        }
        for(int i=0;i<sudoku.length;i++){
            if(sudoku[i][col]==val){
                return false;
            }
        }
        int cg=col/3;
        int ro=row/3;
        for(int i=ro*3;i<=ro*3+2;i++){
            for(int j=cg*3;j<=cg*3+2;j++){
                if(sudoku[i][j]==val){
                    return false;
                }
            }
        }
        return true;
    }

}