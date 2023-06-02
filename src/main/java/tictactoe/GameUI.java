package tictactoe;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

/**
 * @author Gariel Mahwastu
 * This class is the main class for the GUI.
 * This class contains the main menu for the GUI.
 */
public class GameUI extends JFrame{
    private JPanel guiPanel;
    private JMenuBar guiMenuBar;
    private JPanel panelForButton;

    /**
     * This is where to set and add everything available in the GUI
     */
    public GameUI(){
        super();
        setTitle("Welcome to Tic Tac Toe Game!");
        setSize(2000,2000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiPanel = new JPanel();
        setLayout(new BorderLayout());
        add(guiPanel, BorderLayout.CENTER);

        panelForButton = new JPanel();
        panelForButton = buttonPanel();
        add(panelForButton, BorderLayout.EAST);

        //Menu bar
        addMenuBar();
        setJMenuBar(guiMenuBar);
        pack();
    }

    /**
     * Panel for buttons
     */
    private JPanel buttonPanel(){
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));
        panelButton.add(ticTacButton());
        panelButton.add(numberTicTacToe());
        return panelButton;
    }

    /**
     * Start tictactoe button
     */
    private JButton ticTacButton(){
        JButton ticTac = new JButton("Start Tic Tac Toe");
        ticTac.addActionListener(e->startTicTacToe());
        return ticTac;
    }

    /**
     * Function to determine what pressing Start Tic Tac Toe will do
     */
    protected void startTicTacToe(){
        guiPanel.removeAll();
        panelForButton.removeAll(); 
        panelForButton.add(newGameButtonTT()); 
        panelForButton.add(returnMenuButtonTT());
        guiMenuBar.setVisible(true); 
        guiPanel.add(new TicTacToeGUI(1,3,3));
        pack();
    }

    /**
     * Number tic tac toe button
     */
    private JButton numberTicTacToe(){
        JButton numberTicTac = new JButton("Start Number Tic Tac Toe");
        numberTicTac.addActionListener(e->startNumTicTacToe());
        return numberTicTac;
    }

    /**
     * Function to determine what pressing the Start Number Tic Tac Toe will do
     */
    protected void startNumTicTacToe(){
        guiPanel.removeAll();
        panelForButton.removeAll();
        panelForButton.add(newGameButtonNumTT());
        panelForButton.add(returnMenuButtonTT());
        guiMenuBar.setVisible(true);
        guiPanel.add(new NumTTTGUI(2,3,3));
        pack();
    }

    /**
     * New game button for tic tac toe
     */
    protected JButton newGameButtonTT(){
        JButton newGameTT = new JButton("Start New Game");
        newGameTT.addActionListener(e-> startTicTacToe());
        return newGameTT;
    }

    /**
     * Main menu button
     */
    protected JButton returnMenuButtonTT(){
        JButton retMenTT = new JButton("Main Menu");
        retMenTT.addActionListener(e-> backToMenuTT());
        return retMenTT;
    }

    /**
     * Functions for main menu button functionality
     */
    protected void backToMenuTT(){
        guiPanel.removeAll();
        panelForButton.removeAll();
        panelForButton.add(ticTacButton());
        panelForButton.add(numberTicTacToe());
        guiMenuBar.setVisible(false);
        pack();
    }

    /**
     * New game button for num tic tac toe
     */
    protected JButton newGameButtonNumTT(){
        JButton newNumTT = new JButton("Start New Game");
        newNumTT.addActionListener(e->startNumTicTacToe());
        return newNumTT;
    }

    /**
     * Menu bar
     */
    public void addMenuBar(){
        guiMenuBar = new JMenuBar();
        JMenu inBar = new JMenu("Save");
        JMenu newBar = new JMenu("Load");
        JMenuItem saveFile = new JMenuItem("Save Player");
        JMenuItem loadFile = new JMenuItem("Load Player");
        inBar.add(saveFile);
        newBar.add(loadFile);
        guiMenuBar.add(inBar);
        guiMenuBar.add(newBar);
        guiMenuBar.setVisible(false);
    }

    /**
     * Main method for GUI
     */
    public static void main(String[] args){
        GameUI theGame = new GameUI();
        theGame.setVisible(true);
    }
}