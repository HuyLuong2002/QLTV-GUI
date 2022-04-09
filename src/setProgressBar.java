
import javax.swing.*;
import java.awt.*;
public class setProgressBar {
    JProgressBar pgBarHP;
    JFrame frProgress;
    public setProgressBar() throws InterruptedException{
        frProgress = new JFrame();
        pgBarHP = new JProgressBar(0,100);
        // set up progress login
        pgBarHP.setValue(0);
        pgBarHP.setBounds(0,0,100,10);
        pgBarHP.setStringPainted(true);
        pgBarHP.setFont(new Font("Arial", Font.BOLD,20));
        pgBarHP.setForeground(Color.black);
        pgBarHP.setBackground(Color.white);
        // set up frame frProgress
        frProgress.setSize(500,80);
        frProgress.setLocationRelativeTo(null);
        frProgress.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frProgress.add(pgBarHP);
        frProgress.setVisible(true);
        runProgress();
        frProgress.dispose();
    }
    public void runProgress() throws InterruptedException{
        int counter = 0;
        while(counter<=100){
            pgBarHP.setValue(counter);
            Thread.sleep(50);
            counter = counter + 5;
        }
    }
}
