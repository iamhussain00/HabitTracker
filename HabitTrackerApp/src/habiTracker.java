import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;



public class habiTracker {
	static int cur = 0;
    static int selectedIndex = -1;
    static JPanel selectedPanel = null;
    static JPanel displayPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Habit Tracker");
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.black);

        // Add Habit Panel (Input & Button)
        JPanel addHabitPanel = new JPanel(new FlowLayout());
        JTextField addHabitField = new JTextField(15);
        JButton addHabitButton = new JButton("Add New Habit");
        addHabitButton.setBackground(new Color(200, 250, 200));
        addHabitPanel.add(addHabitField);
        addHabitPanel.add(addHabitButton);
        addHabitPanel.setBackground(new Color(100, 150, 130));
        panel.add(addHabitPanel, BorderLayout.NORTH);

        // Display Panel (Scrollable)
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setPreferredSize(new Dimension(350, 250)); //Limit to 10 panels

        JScrollPane scrollPane = new JScrollPane(displayPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER); // ✅ Corrected ScrollPane addition

        // Button Panel (Mark Completed, Reset Progress)
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton markCompletedButton = new JButton("Mark as Completed");
        JButton resetButton = new JButton("Reset 🤓");
        JButton beginButton = new JButton("Start Again");

        buttonPanel.add(markCompletedButton);
        buttonPanel.add(beginButton);
        buttonPanel.add(resetButton);
        buttonPanel.setBackground(new Color(150, 200, 255));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        Habit[] habitArray = new Habit[100];

        addHabitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = addHabitField.getText().trim();
                if (!text.isEmpty()) {
                    System.out.println(text);
                    habitArray[cur] = new Habit(cur, text, 0);
                    displayPanel.add(habitArray[cur].habitPanel);

                    // Mouse Listener to track selected panel
                    final int index = cur;
                    habitArray[index].habitPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (selectedPanel != null) {
                                selectedPanel.setBackground(new Color(200, 200, 200)); // Reset previous selection
                            }
                            habitArray[index].habitPanel.setBackground(new Color(150, 200, 250)); // Highlight selected panel
                            selectedPanel = habitArray[index].habitPanel;
                            selectedIndex = index;
                        }
                    });

                    // 🔹 Adjust Display Panel Size Dynamically
                    displayPanel.setPreferredSize(new Dimension(350, Math.max(250, cur * 25))); 
                    displayPanel.revalidate();
                    displayPanel.repaint();

                    addHabitField.setText(""); // Clear input field
                    cur++;  // Increment habit count
                }
            }
        });

        markCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex != -1 && selectedPanel != null && habitArray[selectedIndex] != null) {
                    habitArray[selectedIndex].increaseDays();
                    //System.out.println("Updated days for: " + habitArray[selectedIndex].habitName + " -> " + habitArray[selectedIndex].days);

                    // Update text directly using selectedPanel
                    JLabel label = (JLabel) selectedPanel.getComponent(0);
                    label.setText((selectedIndex + 1) + ". " + habitArray[selectedIndex].habitName + " (days: " + habitArray[selectedIndex].days + ")");

                    // Reset color after marking complete
                    selectedPanel.setBackground(new Color(200, 200, 200));
                    selectedPanel = null;
                    selectedIndex = -1;

                    displayPanel.revalidate();
                    displayPanel.repaint();
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPanel.removeAll();
                displayPanel.revalidate();
                displayPanel.repaint();
                cur = 0;  // Reset habit count
                selectedPanel = null;
                selectedIndex = -1;
                displayPanel.setPreferredSize(new Dimension(350, 250)); // Reset panel size
            }
        });
        
        beginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (selectedIndex != -1 && selectedPanel != null && habitArray[selectedIndex] != null) {
            		habitArray[selectedIndex].days = 0;
                    
                    JLabel label = (JLabel) selectedPanel.getComponent(0);
                    label.setText((selectedIndex + 1) + ". " + habitArray[selectedIndex].habitName + " (days: " + habitArray[selectedIndex].days + ")");
                    
                    // Reset color after marking complete
                    selectedPanel.setBackground(new Color(200, 200, 200));
                    selectedPanel = null;
                    selectedIndex = -1;

                    displayPanel.revalidate();
                    displayPanel.repaint();
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}        
