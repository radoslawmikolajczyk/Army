import baza.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Baza baza = new Baza();

        JFrame frame = new JFrame("Army_GUI");
        frame.setContentPane(new Army_GUI(baza).GUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
