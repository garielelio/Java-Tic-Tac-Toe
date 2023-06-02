package tictactoe;
import java.util.Scanner;
import java.util.InputMismatchException;


/**
 * @author Gariel Mahwastu
 * This class will run the tic tac toe game in terminal.
 * This class connects to the TicTacToe.java to be able to work.
 */
public class TextUI{
    private TicTacToe game = new TicTacToe(3,3);    
    private Scanner input = new Scanner(System.in);
    private int acrossVal;
    private int downVal;

    /**
     * This method sets the grid for terminal game.
     * @param gameType
     */
    public TextUI(int gameType){
        game = new TicTacToe(3,3);
        game.setGrid(game.newGrid(1,3,3));
    }

    /**
     * This is the method to determine if game finishes or not
     */
    public void play(){
        while(!game.isDone()){
            getPosition();
            if(acrossVal == 0){
                game.setGameOver(true);
                break;
            }
            try{
                game.takeTurn(acrossVal,downVal,getToken());
                System.out.println(game);
                System.out.println(game.getGameStateMessage());
            }catch(RuntimeException e){
                System.out.println(e.getMessage());
            }
        } 
    }

    /**
     * This method asks the user for input
     */
    private void getPosition(){
        /*this method needs some validation and error checking*/
        int checkError = 0;
        while(checkError == 0){
            try{
                System.out.println("across? (0 to quit)");
                acrossVal = input.nextInt();
    
                input.nextLine(); //to get rid of hard return
                System.out.println("down?");
                downVal = input.nextInt(); //to get rid or hard return;
                checkError = 1;
            } catch(InputMismatchException e){
                System.out.println("Input is invalid");

                input.nextLine();
                checkError = 0;
            }
        }
    }

    /**
     * This method sets whose player turn it is
     * @return O or X
     */
    private String getToken(){
        if(game.getCurrentPlayer() == 1){
            return "X";
        }
        return "O";
    }

    /**
     * This is the main method for the terminal game
     */
    public static void main(String[] args){
        TextUI tester = new TextUI(1); //textUI for XO game
        tester.play();
    }
}