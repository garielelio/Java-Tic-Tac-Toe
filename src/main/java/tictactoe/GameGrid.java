package tictactoe;

import java.util.Iterator;

/**
 * @author Gariel Mahwastu
 * This class will handle both XOGrid and NumTTTGrid
 */

public abstract class GameGrid extends boardgame.Grid{

public GameGrid(int across, int down){
    super(across,down);
}   
public abstract boolean horizontalWin();

public abstract boolean verticalWin();

public abstract boolean diagonalWin();

public  abstract void validateInput(String input) throws Exception;

public  abstract void validateLocation(int across, int down)throws Exception;


public boolean isFull(){
    Iterator<String> iter = iterator();
    int count = 0;
        while(iter.hasNext()){
            if(!iter.next().equals(" ")){
            count++;
            }
        }
    if(count == getWidth()*getHeight()){
        return true;
    }
    return false;

}




}