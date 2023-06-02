package tictactoe;


/**
 * @author Gariel Mahwastu
 * This class is for the winning condition and validate the tictactoe game
 */

public class XOGrid extends GameGrid{

private static  String[] symbols ={"X","O"};

public XOGrid(int across, int down){
    super(across,down);
}

/**
* Check the horizontal win for the tic tac toe game
* @return true or false
*/
public boolean horizontalWin(){

return rowCheck(super.getHeight());
}

/**
* Check the vertical win for the tic tac toe game
* @return true or false
*/
public boolean verticalWin(){

return columnCheck(super.getWidth());
    
}

/**
* Check for diagonal win for the tic tac toe game
* @return true or false
*/
public boolean diagonalWin(){

return diagonalCheck();
    
}

/**
* Method to validate the input for tic tac toe game
* @param input
*/
@Override
public void validateInput(String input) throws Exception{ 
    for(String userInpt: symbols){
        if(input.equals(userInpt)){
            return;
        }
    }
    throw new Exception("Input is invalid");
}

/**
* Method to validate location for the tic tac toe game
* @param gameProgress
* @return true or false
*/
@Override
public void validateLocation(int across, int down)throws Exception{
    if(across > 3 || down > 3 || across < 1 || down < 1 || super.getValue(across,down) != " "){
        throw new Exception("Input is invalid");
    }
    return;
}


/**
* Check the row for winning condition
* @param row
* @return true or false
*/
private boolean rowCheck(int row){
    boolean match = false;
    for(int i = 0; i < row; i++){
        if(super.getValue(1, i+1) == super.getValue(2, i+1) && super.getValue(2, i+1) == super.getValue(3, i+1)){
            if(super.getValue(1, i+1) != " "){
                match = true;
                break;
            }
        }
    }
    return match;
}

/**
* Check the column for winning condition
* @param col
* @return true or false
*/
private boolean columnCheck(int col){
    for(int i = 0; i < col; i++){
        if(super.getValue(i+1, 1) == super.getValue(i+1, 2) && super.getValue(i+1, 2) == super.getValue(i+1, 3)){
            if(super.getValue(i+1, 1) != " "){
                return true;
            }
        }
    }
    return false;

}

/**
* Check for win condition diagonally
* @return true or false
*/
private boolean diagonalCheck(){
    if(super.getValue(1, 1) == super.getValue(2, 2) && super.getValue(2, 2) == super.getValue(3, 3)){
        if(super.getValue(1, 1) != " "){
            return true;
        }
    }
    if(super.getValue(3, 1) == super.getValue(2, 2) && super.getValue(2, 2) == super.getValue(1, 3)){
        if(super.getValue(3, 1) != " "){
            return true;
        }
    }
    return false;
}




}