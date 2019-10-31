// Nguyen Tien Vuong
//
// DYUQFJ
//
// Assignment2
//
// 2018/11/21 08:07:14
//
// This solution was submitted and prepared by Nguyen Tien Vuong, DYUQFJ for the
// Assignment2 assignment of the Practical software engineering I. course.
//
// I declare that this solution is my own work.
//
// I have not copied or used third party solutions.
//
// I have not passed my solution to my classmates, neither  made it public.
//
// Students’ regulation of Eötvös Loránd University (ELTE Regulations
// Vol. II. 74/C. § ) states that as long as a student presents another
// student’s work - or at least the significant part of it - as his/her own
// performance, it will count as a disciplinary fault. The most serious
// consequence of a disciplinary fault can be dismissal of the student from
// the University.

package tictactoe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Window extends BaseWindow {

    private final int size;
    private final Model model;
    private final JLabel label;
    private final MainWindow mainWindow;
    
    private JButton[][] b;
    
    
    public Window(int size, MainWindow mainWindow) {
        this.size = size;
        this.mainWindow = mainWindow;
        mainWindow.getWindowList().add(this);
        model = new Model(size);
        b = new JButton[size][size];

        JPanel top = new JPanel();
        
        label = new JLabel();
        updateLabelText();
        
        JButton newGameButton = new JButton();
        newGameButton.setText("New game");
        newGameButton.addActionListener(e -> newGame());
        
        top.add(label);
        top.add(newGameButton);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                addButton(mainPanel, i, j);
            }
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void addButton(JPanel panel, final int i,
            final int j) {
        final JButton button = new JButton();
        b[i][j] =  button;
        
        button.addActionListener(e -> {
            Player newValue = model.step(i, j);
            button.setText(newValue.name());
            System.out.println(newValue.name());
            
            updateLabelText();
            
            Player winner = model.findWinner();
            if (winner != Player.NOBODY) {
                showGameOverMessage(winner);
            }
            
            int cnt = model.findDraw();
            if (cnt == size * size){
                showDrawMessage();
            }
            
            ArrayList<PlayerXO> tmp = model.playTrick(i ,j);
            if (tmp.size() != 0){
                for (int a = 0; a < tmp.size(); a++){
                    System.out.println(tmp.get(a).getRow() + " " + tmp.get(a).getColumn());
                    b[tmp.get(a).getRow()][tmp.get(a).getColumn()].setText("");
                }
            }
            
        });

        panel.add(button);
    }

    private void showGameOverMessage(Player winner) {
        JOptionPane.showMessageDialog(this,
                "Game is over. Winner: " + winner.name());
        newGame();
    }
    
    private void showDrawMessage(){
        JOptionPane.showMessageDialog(this, "Draw");
        newGame();
    }
    
    private void newGame() {
        Window newWindow = new Window(size, mainWindow);
        newWindow.setVisible(true);
        
        this.dispose();
        mainWindow.getWindowList().remove(this);
    }
    
    private void updateLabelText() {
        label.setText("Current player: "
                + model.getActualPlayer().name());
    }

    @Override
    protected void doUponExit() {
        super.doUponExit();
        mainWindow.getWindowList().remove(this);
    }
    
}
