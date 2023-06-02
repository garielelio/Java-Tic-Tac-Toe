package tictactoe;

/**
 * @author Gariel Mahwastu
 * This class is the backbone for the program, this controls every movement made
 * in the game
 */
public class TicTacToe extends boardgame.BoardGame implements boardgame.Saveable{
 private int currentPlayer = 1;
 private String gameStateMessage;
 private boolean done = false;

public TicTacToe(int wide, int tall){
    super(wide, tall);
}

/**
* This is the method to determine the string to be saved in the file
* @return strToRet
*/
@Override
public String getStringToSave(){
    String strToRet = "";
    for(int k = 0; k < 3; k++){
        for(int j = 0; j < 3; j++){
            if(super.getCell(j+1, k+1) == " "){
                if(j < 2){
                    strToRet += ","; 
                }
            } else{
                strToRet += getCell(j+1, k+1);
                if(j < 2){    
                    strToRet += ",";
                }
            }
        }
        strToRet += "\n";
   }

    return strToRet;
}

/**
* Check for win condition in vertical and horizontal
* @param toLoad
*/
@Override
public void loadSavedString(String toLoad){
    if(toLoad.charAt(0) == 'X' && currentPlayer == 1){
        switchPlayer();
    } else if(toLoad.charAt(0) == 'O' && currentPlayer == 2){
        switchPlayer();
    }

    String subToLoad = toLoad.substring(1);
    grid().emptyGrid();
    int loc = 0;
    for(int k = 1; k < 4; k++){
        for(int j = 1; j < 4; j++){
            if(subToLoad.charAt(loc) != 'Z'){
                String val = Character.toString(subToLoad.charAt(loc));
                takeTurn(j,k,val);
            }
            loc = loc+1;
        }
    }
}

/**
* This is the getters method for currentPlayer
* @return currentPlayer
*/
public int getCurrentPlayer(){
    return currentPlayer;
}

/**
* This is the setters method for done
* @param state
*/
public void setGameOver(boolean state){
    done = state;
}

/**
* Take turn method for the tic tac toe game
* @param across,down,input
* @return true or false
*/
@Override
public  boolean takeTurn(int across, int down, String input){
    try{
        //Validating location and input
        grid().validateLocation(across, down);
        grid().validateInput(input);

        //Setting the value
        super.setValue(across,down,input);
        if(!isDone()){
            //Changing the player
            switchPlayer();
            setGameStateMessage(nextPlayerMessage());
            return true;
        } else{
            setGameStateMessage(gameOverMessage());
            return true;
        }
    } catch(Exception e){
        throw new RuntimeException(e.getMessage());
    }
}

/**
* Take turn method for the number tic tac toe game
* @param across,down,input
* @return true or false
*/
@Override
public  boolean takeTurn(int across, int down, int input){
    try{
        //Validate location and input
        grid().validateLocation(across,down);
        if(getCurrentPlayer() == 1){
            if(input % 2 == 0){
                throw new Exception("Input is invalid");
            }
        } else if(getCurrentPlayer() == 2){
            if(input % 2 != 0){
                throw new Exception("Input is invalid");
            }
        }
        grid().validateInput(Integer.toString(input));
        //Set the value
        super.setValue(across, down, input);
        if(!isDone()){
            //Changing the player
            switchPlayer();
            setGameStateMessage(nextPlayerMessage());
            return true;
        } else{
            setGameStateMessage(gameOverMessage());
            return true;
        }
    } catch(Exception e){
        throw new RuntimeException(e.getMessage());
    }
}

/**
* This is the method to set the grid
* @param grid
*/
@Override
public void setGrid(boardgame.Grid grid){
    super.setGrid(grid);
    setGameOver(false);
}

/**
* This method determines if the game is done or not
* @return done
*/
@Override
 public boolean isDone(){
    if(grid().diagonalWin() || grid().horizontalWin() || grid().verticalWin()){
        setGameOver(true);
        return done;
    } else if(getWinner() == 0){
        setGameOver(true);
        return done;
    }
    return done;
 }

/**
* This method determines who win the game or if it is a tie
* @return checkCon
*/
@Override
public int getWinner(){
    int checkCon = 3;
    if(grid().isFull()){
        checkCon = 0;
    }
    if(grid().diagonalWin() || grid().horizontalWin() || grid().verticalWin()){
        checkCon = getCurrentPlayer();
    }
    return checkCon;
}

/**
* This is the getters method for the gameStateMessage
* @return gameStateMessage
*/
@Override
public String getGameStateMessage(){
    return gameStateMessage;

}

    /**
     * This method will switch the player
     */
    private void switchPlayer(){
        if(getCurrentPlayer() == 1){
            currentPlayer = 2;
        }else{
            currentPlayer = 1;
        }
    }

    /**
     * This is the method for grid
     * @return grid
     */
    private GameGrid grid(){
        return (GameGrid) getGrid();
    }

    /**
     * The setters method for the gameStateMessage
     * @param msg
     */
    private void setGameStateMessage(String msg){
        gameStateMessage = msg;
    }

    /**
     * This method determines the next player message
     * @param gameProgress
     * @return message
     */
    private String nextPlayerMessage(){
        String player = "Player 1";
        if(currentPlayer == 2){
            player = "Player 2";
        }
        return(player + " please indicate where you would like to put your token.");
    }

    /**
     * This method determines the game over message
     * @return message
     */
    private String gameOverMessage(){
        /*should compose a nice string about who won and/or tie game*/
        if(grid().diagonalWin() || grid().horizontalWin() || grid().verticalWin()){
            return("Player " + Integer.toString(getCurrentPlayer()) + " wins!");
        } else if(getWinner() == 0){
            return("Game is a tie.");
        }
        return("game over");
    }


/**
* This is the method to create the grid
* @param kind,wide,tall
* @return grid
*/
public static GameGrid newGrid(int kind, int wide, int tall){
    if(kind == 1){
        return new XOGrid(wide,tall);
    }else{
        return new NumTTTGrid(wide,tall);
    }
}

}