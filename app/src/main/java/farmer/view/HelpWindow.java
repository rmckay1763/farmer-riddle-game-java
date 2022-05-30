package farmer.view;

import java.util.Scanner;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class HelpWindow extends JFrame {
    
    private static final int OFFSET;
    private static final Color BROWN;
    private static final Color BLONDE;
    private static final Font TITLE_FONT;
    private static final Font HEADER_FONT;
    private static final Font CONTENT_FONT;

    private static final String SELECT_ITEM_HELP;
    private static final String UNLOAD_BOAT_HELP;
    private static final String CROSS_RIVER_HELP;
    private static final String RESTART_GAME_HELP;
    private static final String TIP_ONE;
    private static final String TIP_TWO;

    static {
        OFFSET = 20;
        BROWN = new Color(94, 57, 32);
        BLONDE = new Color(246, 236, 213);
        TITLE_FONT = new Font("Gill Sans MT", Font.BOLD, 24);
        HEADER_FONT = new Font("Gill Sans MT", Font.BOLD, 18);
        CONTENT_FONT = new Font("Gill Sans MT", Font.PLAIN, 18);

        SELECT_ITEM_HELP = "Click on an item to load the item into the boat";
        UNLOAD_BOAT_HELP = "Click 'Unload Boat' to move the item in the boat to the river bank";
        CROSS_RIVER_HELP = "Click 'Cross River' to cross to the other side of the river";
        RESTART_GAME_HELP = "Click 'Restart Game' at any time to start a new game";
        TIP_ONE = "The farmer can cross the river without any item in the boat";
        TIP_TWO = "The farmer can take an item across the river in both directions";

        
    }

    public HelpWindow() {
        super("Help Window");

        JLabel titleLabel = new JLabel("Game Instructions");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(BROWN);
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(BLONDE);
        titlePanel.add(titleLabel);

        JLabel imageLabel = new JLabel(ImageLib.FARMER);
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel contentPanel = new JPanel();
        BoxLayout layout = new BoxLayout(contentPanel, BoxLayout.Y_AXIS);
        contentPanel.setLayout(layout);
        contentPanel.setBackground(BLONDE);
        contentPanel.setBorder(new EmptyBorder(20, 50, 50, 50));
        
        contentPanel.add(imageLabel);
        contentPanel.add(headerLabel("The Riddle"));
        contentPanel.add(loadTextArea("riddle.txt"));
        contentPanel.add(headerLabel("Game Controls"));
        contentPanel.add(bulletLabel(SELECT_ITEM_HELP));
        contentPanel.add(bulletLabel(UNLOAD_BOAT_HELP));
        contentPanel.add(bulletLabel(CROSS_RIVER_HELP));
        contentPanel.add(bulletLabel(RESTART_GAME_HELP));
        contentPanel.add(headerLabel("Game Tips"));
        contentPanel.add(bulletLabel(TIP_ONE));
        contentPanel.add(bulletLabel(TIP_TWO));
        
        add(titlePanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        pack();
        GraphicsConfiguration config = getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);
        int x = bounds.x + bounds.width - insets.right - getWidth() - OFFSET;
        int y = bounds.y + insets.top + OFFSET;
        this.setLocation(x, y);
    }

    private JTextArea loadTextArea(String path) {
        JTextArea textArea = new JTextArea();
        textArea.setBackground(BLONDE);
        textArea.setForeground(BROWN);
        textArea.setFont(CONTENT_FONT);
        textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        textArea.setEditable(false);
        Scanner scanner = new Scanner(HelpWindow.class.getClassLoader().getResourceAsStream(path));

        while (scanner.hasNextLine()) {
            textArea.append(scanner.nextLine());
            textArea.append("\n");
        }

        scanner.close();
        return textArea;
    }

    private JLabel headerLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(HEADER_FONT);
        label.setForeground(BROWN);
        label.setBorder(new EmptyBorder(20, 0, 20, 0));
        return label;
    }

    private JLabel bulletLabel(String text) {
        JLabel label = new JLabel(text);
        label.setIcon(ImageLib.BULLET_ICON);
        label.setFont(CONTENT_FONT);
        label.setForeground(BROWN);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

}
