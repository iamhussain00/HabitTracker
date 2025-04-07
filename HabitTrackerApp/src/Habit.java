import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Habit {
    int index;
    String habitName;
    int days = 0;
    JPanel habitPanel;

    Habit(int index, String habitName, int days) {
        this.index = index;
        this.habitName = habitName;
        this.days = days;

        habitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        habitPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        habitPanel.setBackground(new Color(200, 200, 200));
        JLabel habitLabel = new JLabel((index + 1) + ". " + habitName + " (Streak: " + 0 + "days)");
        habitPanel.add(habitLabel);
        habitPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
    }

    void increaseDays() {
        days++;
    }
}
