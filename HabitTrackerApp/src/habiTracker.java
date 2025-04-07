import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class habiTracker {
    static int selectedIndex = -1;
    static JPanel selectedPanel = null;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Habit Tracker");
        frame.setSize(450, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.black);

        JPanel addHabitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,8));
        addHabitPanel.setPreferredSize(new Dimension(450,40));
        JTextField addHabitField = new JTextField(20);
        addHabitField.setFont(new Font("Arial", Font.PLAIN, 14));  
        JButton addHabitButton = new JButton("Add New Habit");
        addHabitButton.setBackground(new Color(200, 250, 200));
        addHabitPanel.add(addHabitField);
        addHabitPanel.add(addHabitButton);
        addHabitPanel.setBackground(new Color(100, 150, 130));
        panel.add(addHabitPanel, BorderLayout.NORTH);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setPreferredSize(new Dimension(350, 250));

        JScrollPane scrollPane = new JScrollPane(displayPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton markCompletedButton = new JButton("Mark as Completed");
        JButton resetButton = new JButton("Reset");
        JButton beginButton = new JButton("Start Again");
        JButton deleteButton = new JButton("Delete ");

        buttonPanel.add(markCompletedButton);
        buttonPanel.add(beginButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(resetButton); 
       

        buttonPanel.setBackground(new Color(150, 200, 255));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        ArrayList<Habit> habitList = new ArrayList<>();

        addHabitButton.addActionListener(e -> {
            String text = addHabitField.getText().trim();
            if (!text.isEmpty()) {
                int index = habitList.size();
                Habit habit = new Habit(index, text, 0);
                habitList.add(habit);
                displayPanel.add(habit.habitPanel);

                habit.habitPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (selectedPanel != null) {
                            selectedPanel.setBackground(new Color(200, 200, 200));
                        }
                        habit.habitPanel.setBackground(new Color(150, 200, 250));
                        selectedPanel = habit.habitPanel;
                        selectedIndex = habit.index;
                    }
                });

                displayPanel.setPreferredSize(new Dimension(350, Math.max(250, habitList.size() * 30)));
                displayPanel.revalidate();
                displayPanel.repaint();
                
                addHabitField.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size to 14 or match label font size
                addHabitField.setText("");
            }
        });

        markCompletedButton.addActionListener(e -> {
            if (selectedIndex != -1 && selectedPanel != null) {
                Habit habit = habitList.get(selectedIndex);
                habit.increaseDays();

                JLabel label = (JLabel) selectedPanel.getComponent(0);
                label.setText((selectedIndex + 1) + ". " + habit.habitName + " (Streak: " + habit.days + " days)");

                selectedPanel.setBackground(new Color(200, 200, 200));
                selectedPanel = null;
                selectedIndex = -1;

                displayPanel.revalidate();
                displayPanel.repaint();
            }
        });

        beginButton.addActionListener(e -> {
            if (selectedIndex != -1 && selectedPanel != null) {
                Habit habit = habitList.get(selectedIndex);
                habit.days = 0;

                JLabel label = (JLabel) selectedPanel.getComponent(0);
                label.setText((selectedIndex + 1) + ". " + habit.habitName + " (Streak: 0 days)");

                selectedPanel.setBackground(new Color(200, 200, 200));
                selectedPanel = null;
                selectedIndex = -1;

                displayPanel.revalidate();
                displayPanel.repaint();
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex != -1 && selectedPanel != null) {
                    
                    displayPanel.remove(selectedPanel);

                    habitList.remove(selectedIndex);

                    for (int i = 0; i < habitList.size(); i++) {
                        Habit h = habitList.get(i);
                        h.index = i;
                        JLabel label = (JLabel) h.habitPanel.getComponent(0);
                        label.setText((i + 1) + ". " + h.habitName + " (Streak: " + h.days + " days)");
                    }

                    selectedPanel = null;
                    selectedIndex = -1;

                    displayPanel.revalidate();
                    displayPanel.repaint();
                }
            }
        });


        resetButton.addActionListener(e -> {
            habitList.clear();
            displayPanel.removeAll();
            displayPanel.setPreferredSize(new Dimension(350, 250));
            selectedPanel = null;
            selectedIndex = -1;
            displayPanel.revalidate();
            displayPanel.repaint();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
