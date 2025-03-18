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

        // ActionListener for Add Habit Button
        addHabitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                String habitText = addHabitField.getText().trim();
                if (!habitText.isEmpty()) {
                    JPanel habitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); 
                    habitPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                    habitPanel.setBackground(new Color(200, 200, 200)); 
                    JLabel habitLabel = new JLabel(habitText);
                    habitPanel.add(habitLabel);
                    habitPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25)); 
                    displayPanel.add(habitPanel);

                    // Refresh the display panel
                    displayPanel.revalidate();
                    displayPanel.repaint();

                    addHabitField.setText(""); 
                }
            }
        });

        // ActionListener for Reset Progress Button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                displayPanel.removeAll();
                displayPanel.revalidate();
                displayPanel.repaint();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
