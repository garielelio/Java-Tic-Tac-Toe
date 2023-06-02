package tictactoe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import boardgame.ui.PositionAwareButton;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import javax.swing.JFileChooser;
import java.io.File;

/**
 * @author Gariel Mahwastu
 * This class is the GUI for the number tic tac toe.
 * This class controls everything for the GUI in number tic tac toe.
 */
public class NumTTTGUI extends JPanel{
    private JLabel guiMsg;
    private TicTacToe tictacGame;
    private PositionAwareButton[][] buttons;
    private JLabel stateMessage;
    private JFileChooser userFile;


    /**
     * This is where everything in the number tic tac toe GUI will be added and set
     * @param kind,across,down
     */
    public NumTTTGUI(int kind, int across, int down){
        super();
        setLayout(new BorderLayout());
        tictacGame = new TicTacToe(across,down);
        tictacGame.setGrid(tictacGame.newGrid(kind,across,down));

        guiMsg = new JLabel("Welcome to Number Tic Tac Toe");
        add(guiMsg, BorderLayout.NORTH);
        add(ticTacGrid(across, down),BorderLayout.SOUTH);

        stateMessage = new JLabel("Player 1 please indicate where you would like to put your token.");
        add(stateMessage, BorderLayout.CENTER);

        add(buttonPanel(), BorderLayout.EAST);
        userFile = new JFileChooser("./assets/");
    }

    /**
     * This is the panel for the buttons for number tic tac toe
     * @param down,across
     * @return panel
     */
    private JPanel ticTacGrid(int down, int across){
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[down][across];
        panel.setLayout(new GridLayout(across, down));
        for (int y=0; y<across; y++){
            for (int x=0; x<down; x++){ 
                //Create buttons and link each button back to a coordinate on the grid
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x+1); //made the choice to be 1-based
                buttons[y][x].setDown(y+1);
                buttons[y][x].addActionListener(e->{
                                    userChoice(e);
                                    checkGameState();
                                    });
            panel.add(buttons[y][x]);
        }
    }
    return panel;
    }

    /**
     * Check for win condition in for the number tic tac toe
     */
    private void checkGameState(){
        if(tictacGame.isDone()){
            stateMessage.setText(tictacGame.getGameStateMessage()+" Click 'Start New Game' to start again.");
            for(int k = 0; k < 3; k++){
                for(int j = 0; j < 3; j++){
                    buttons[k][j].setEnabled(false);
                }
            }
        }
    }

    /**
     * This is the method that controls what happens when the user click the button
     * and ask for input
     * @param e
     */
    private void userChoice(ActionEvent e){
        String num = JOptionPane.showInputDialog(askInputMessage());

        try{
            PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
            if(tictacGame.takeTurn(clicked.getAcross(), clicked.getDown(),Integer.parseInt(num))){
                stateMessage.setText(nextMessage());
                clicked.setText(tictacGame.getCell(clicked.getAcross(),clicked.getDown()));
                clicked.setEnabled(false);
            }
        } catch(NumberFormatException err){
            JOptionPane.showMessageDialog(null, "Enter an integer from the given numbers");
        } catch(RuntimeException err){
            JOptionPane.showMessageDialog(null, "Invalid. Number out of range or already selected");
        }
    }

    /**
     * This method determines the message to display to user
     * @return message
     */
    private String nextMessage(){
        String curTurn = "Player 1";
        if(tictacGame.getCurrentPlayer() == 2){
            curTurn = "Player 2";
        }
        return(curTurn + " please indicate where you would like to put your token.");
    }

    /**
     * This method displays the player turn
     * @return curPlayer
     */
    private String askInputMessage(){
        String curPlayer = "Player 1, Please enter an odd number from 1-9";
        if(tictacGame.getCurrentPlayer() == 2){
            curPlayer = "Player 2, please enter an even number from 0-8";
        }
        return curPlayer;
    }

    /**
     * This method is the panel for the button
     * @return panelButton
     */
    private JPanel buttonPanel(){
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));
        panelButton.add(saveTheGameButton());
        panelButton.add(loadTheGameButton());
        return panelButton;
    }

    /**
     * This method is for the save game button
     * @param gameProgress
     * @return saveGame
     */
    private JButton saveTheGameButton(){
        JButton saveGame = new JButton("Save Game");
        saveGame.addActionListener(e->saveTheGame());
        return saveGame;
    }

    /**
     * This is the method to save the game
     */
    protected void saveTheGame(){
        if(userFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File fName = userFile.getSelectedFile();
            String fNamePath = fName.getPath();
            try{
                BufferedWriter writeTheFile = new BufferedWriter(new FileWriter(fNamePath));
        
                if(tictacGame.getCurrentPlayer() == 1){
                    writeTheFile.write("E\n");
                } else {
                    writeTheFile.write("O\n");
                }
                writeTheFile.write(tictacGame.getStringToSave());
        
                writeTheFile.close();
            } catch(IOException err){
                JOptionPane.showMessageDialog(null, "File can not be opened.");
            }
        }

    }

    /**
     * This method determines what pressing load game will do
     * @return ticTac
     */
    private JButton loadTheGameButton(){
        JButton ticTac = new JButton("Load Game");
        ticTac.addActionListener(e->loadTheGame());
        return ticTac;
    }

    /**
     * Method for loading the game
     */
    protected void loadTheGame(){
        
    }

}
