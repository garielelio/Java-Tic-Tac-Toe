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
import java.io.BufferedWriter;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Gariel Mahwastu
 * This class is the GUI for the tic tac toe game.
 * This class controls everything for the GUI for tic tac toe
 */
public class TicTacToeGUI extends JPanel{
    private JLabel guiMsg;
    private TicTacToe tictacGame;
    private PositionAwareButton[][] buttons;
    private JLabel stateMessage;
    private JFileChooser userFile;

    /**
     * This is where everything in the tic tac toe GUI will be added and set
     * @param kind,across,down
     */
    public TicTacToeGUI(int kind, int across, int down){
        super();
        setLayout(new BorderLayout());
        tictacGame = new TicTacToe(across,down);
        tictacGame.setGrid(tictacGame.newGrid(kind,across,down));

        guiMsg = new JLabel("Welcome to Tic Tac Toe");
        add(guiMsg, BorderLayout.NORTH);
        add(ticTacGrid(across, down),BorderLayout.SOUTH);

        stateMessage = new JLabel("Player 1 please indicate where you would like to put your token.");
        add(stateMessage, BorderLayout.CENTER);

        add(buttonPanel(), BorderLayout.EAST);

        userFile = new JFileChooser("./assets/");

    }

    /**
     * This is the panel for the tic tac toe game buttons
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
     * This is the method to check if the game is over or not
     */
    private void checkGameState(){
        if(tictacGame.isDone()){
            stateMessage.setText(tictacGame.getGameStateMessage() + " Click 'Start New Game' to start again.");
            for(int k = 0; k < 3; k++){
                for(int j = 0; j < 3; j++){
                    buttons[k][j].setEnabled(false);
                }
            }
        }
    
    }

    /**
     * This method determines what happens if the user click the buttons
     * @param e
     */
    private void userChoice(ActionEvent e){
        //get input from user
        String num;
        if(tictacGame.getCurrentPlayer() == 1){
            num = "X";
        } else{
            num = "O";
        } 
        
        //send input to game and update view
        PositionAwareButton clicked = ((PositionAwareButton)(e.getSource()));
        if(tictacGame.takeTurn(clicked.getAcross(), clicked.getDown(),num)){
            stateMessage.setText(nextMessage());
            clicked.setText(tictacGame.getCell(clicked.getAcross(),clicked.getDown()));
            clicked.setEnabled(false);
        }
    }

    /**
     * This is a method to determine whose player turn is it
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
     * This is the panel for save and load game button
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
     * Save Game button
     * @return saveGame
     */
    private JButton saveTheGameButton(){
        JButton saveGame = new JButton("Save Game");
        saveGame.addActionListener(e->saveTheGame());
        return saveGame;
    }

    /**
     * This method determines what pressing the Save Game button will do
     */
    protected void saveTheGame(){

        if(userFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File fName = userFile.getSelectedFile();
            String fNamePath = fName.getPath();
            try{
                BufferedWriter writeTheFile = new BufferedWriter(new FileWriter(fNamePath));
        
                if(tictacGame.getCurrentPlayer() == 1){
                    writeTheFile.write("O\n");
                } else {
                    writeTheFile.write("X\n");
                }
                writeTheFile.write(tictacGame.getStringToSave());
        
                writeTheFile.close();
            } catch(IOException err){
                JOptionPane.showMessageDialog(null, "File can not be opened.");
            }
        }
    }

    /**
     * Load Game button
     * @return ticTac
     */
    private JButton loadTheGameButton(){
        JButton ticTac = new JButton("Load Game");
        ticTac.addActionListener(e->loadTheGame());
        return ticTac;
    }

    /**
     * This method will determine what happens if Load Game button is pressed
     */
    protected void loadTheGame(){

        if(userFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File fLoadName = userFile.getSelectedFile();
            String fLoadNamePath = fLoadName.getPath();
            String storeString = "";
            String toPass = "";
            try{
                BufferedReader readTheFile = new BufferedReader(new FileReader(fLoadNamePath));
                while((storeString = readTheFile.readLine()) != null){
                    for(int k = 0; k < storeString.length(); k++){
                        if(storeString.charAt(k) == '\n' && storeString.charAt(k-1) == ','){
                            storeString = storeString.replace('\n','Z');
                        }
                    }
                    String[] fromFile = storeString.split(",");
                    for(int k = 0; k < fromFile.length; k++){
                        if(fromFile[k] == ""){
                            fromFile[k] = "Z";
                        }
                        toPass += fromFile[k];
                    }
                }
                tictacGame.loadSavedString(toPass);
                readTheFile.close();
            } catch(IOException err){
                JOptionPane.showMessageDialog(null, "File can not be opened."); 
            }
        }
    }

}