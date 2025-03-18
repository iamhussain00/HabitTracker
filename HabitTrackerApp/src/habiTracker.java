import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class habiTracker {

	public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.black);

        // Button Panel (Mark as Completed, Reset Progress)
        JButton markCompletedButton = new JButton("Mark as Completed");
        JButton resetButton = new JButton("Reset Progress");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.add(markCompletedButton);
        buttonPanel.add(resetButton);
        buttonPanel.setBackground(new Color(150, 200, 255));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add Habit Panel (Input & Button)
        JPanel addHabitPanel = new JPanel(new FlowLayout());
        JTextField addHabitField = new JTextField(15);
        JButton addHabitButton = new JButton("Add New Habit");
        //addHabitButton.setBackground(new Color(200, 250, 200));
        addHabitPanel.add(addHabitField);
        addHabitPanel.add(addHabitButton);
        addHabitPanel.setBackground(new Color(100, 150, 130));
        panel.add(addHabitPanel, BorderLayout.NORTH);

        // Display Panel (Where habits will be added)
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS)); 
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setOpaque(true);
        panel.add(displayPanel, BorderLayout.CENTER);


        frame.add(panel);
        frame.setVisible(true);
    }
}
