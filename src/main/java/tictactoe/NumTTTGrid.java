package tictactoe;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * @author Gariel Mahwastu
 * This class will do the winning condition for the number tictactoe
 * and validates input and location.
 */

public class NumTTTGrid extends GameGrid{
    //Arraylist for the odd number range and even number range
    private ArrayList<String> oddNum = new ArrayList<>(Arrays.asList("1","3","5","7","9"));
    private ArrayList<String> evNum = new ArrayList<>(Arrays.asList("0","2","4","6","8"));

public NumTTTGrid(int across, int down){
    super(across,down);
}

/**
 * Checking horizontal win condition for number tic tac toe
 * @return true or false
*/
public boolean horizontalWin(){
    for(int i = 0; i < super.getHeight(); i++){
        if(super.getValue(1,i+1) != " " 
        && super.getValue(2,i+1) != " " 
        && super.getValue(3,i+1) != " "){
            if(Integer.parseInt(super.getValue(1, i+1)) 
            + Integer.parseInt(super.getValue(2, i+1)) 
            + Integer.parseInt(super.getValue(3, i+1)) >= 15){
                return true;
            } 
        }
    }
return false;
}

/**
* Checking vertical win condition for the number tic tac toe
* @return true or false
*/
public boolean verticalWin(){
    for(int i = 0; i < super.getWidth(); i++){
        if(super.getValue(i+1, 1) != " " 
        && super.getValue(i+1, 2) != " " 
        && super.getValue(i+1, 3) != " "){
            if(Integer.parseInt(super.getValue(i+1, 1)) 
            + Integer.parseInt(super.getValue(i+1, 2)) 
            + Integer.parseInt(super.getValue(i+1, 3)) >= 15){
                return true;
            }
        }
    }
return false;    
}

/**
* Check for diagonal win condition for the number tic tac toe
* @return true or false
*/
public boolean diagonalWin(){
    if(super.getValue(1,1) != " " 
    && super.getValue(2,2) != " " 
    && super.getValue(3,3) != " "){
        if(Integer.parseInt(super.getValue(1,1)) 
        + Integer.parseInt(super.getValue(2,2)) 
        + Integer.parseInt(super.getValue(3,3)) >= 15){
            return true;
        }
    }
    if(super.getValue(3,1) != " " 
    && super.getValue(2,2) != " " 
    && super.getValue(1,3) != " "){
        if(Integer.parseInt(super.getValue(3,1)) 
        + Integer.parseInt(super.getValue(2,2)) 
        + Integer.parseInt(super.getValue(1,3)) >= 15){
            return true;
        }
    }
return false;    
}

/**
* Validate the input for number tic tac toe
* @param input
*/
public void validateInput(String input)throws Exception{ 
    if(Integer.parseInt(input) % 2 == 0){
        for(int i=0; i < evNum.size(); i++){
            if(input.equals(evNum.get(i))){
                evNum.remove(i);
                return;
            }
        }
    } else {
        for(int i=0;i < oddNum.size(); i++){
            if(input.equals(oddNum.get(i))){
                oddNum.remove(i);
                return;
            }
        }
    }
    throw new Exception("Input is invalid");
}

/**
* Validate location for number tic tac toe
* @param across,down
*/
public void validateLocation(int across, int down)throws Exception{
    if(across > 3 || down > 3 || across < 1 || down < 1 || super.getValue(across,down) != " "){
        throw new Exception("Input is invalid");
    }
    return;
}

}