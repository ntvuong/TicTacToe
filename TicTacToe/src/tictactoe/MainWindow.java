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

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class MainWindow extends BaseWindow {
    
    private List<Window> gameWindows = new ArrayList<>();
    
    public MainWindow() {
        
        JButton small = new JButton();
        small.setText("6 x 6");
        
        small.addActionListener(getActionListener(6));
        
        JButton medium = new JButton();
        medium.setText("10 x 10");

        medium.addActionListener(getActionListener(10));

        JButton large = new JButton();
        large.setText("14 x 14");
        
        large.addActionListener(getActionListener(14));
        
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(small);
        getContentPane().add(medium);
        getContentPane().add(large);
    }
    
    private ActionListener getActionListener(final int size) {
        return new ActionListener() { 

            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = new Window(size, MainWindow.this);
                window.setVisible(true);
                gameWindows.add(window);
            }
            
        };
    }
    
    public List<Window> getWindowList() {
        return gameWindows;
    }
    
    @Override
    protected void doUponExit() {
        System.exit(0);
    }

}
