package farmer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import farmer.model.Location;

/**
 * The status panel for the game. Displays a message after each action.
 */
public class StatusPanel extends JPanel {

    private static final Color BROWN;
    private static final Color BLONDE;
    private static final Font FONT;
    private static final CompoundBorder BORDER;

    static {
        BROWN = new Color(94, 57, 32);
        BLONDE = new Color(246, 236, 213);
        FONT = new Font("Gill Sans MT", Font.BOLD, 18);

        BORDER = BorderFactory.createCompoundBorder(
            
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(BROWN, 3),
                "Game Status",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                FONT,
                BROWN
            ),
            
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        );
    }

    private JLabel statusLabel;

    /**
     * Constructor. Instantiates the status label and a label for each river bank.
     */
    public StatusPanel() {
        this.setBackground(BLONDE);
        this.setBorder(new EmptyBorder(10, 50, 10, 50));

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        JLabel westBankLabel = new JLabel(Location.WEST_BANK.toString());
        westBankLabel.setFont(FONT);
        westBankLabel.setForeground(BROWN);

        JLabel eastBankLabel = new JLabel(Location.EAST_BANK.toString());
        eastBankLabel.setFont(FONT);
        eastBankLabel.setForeground(BROWN);

        statusLabel = new JLabel();
        statusLabel.setFont(FONT);
        statusLabel.setForeground(BROWN);
        statusLabel.setBorder(BORDER);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(westBankLabel, constraints);

        constraints.gridx = 1;
        constraints.weightx = 1;
        add(statusLabel, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.weightx = 0;
        add(eastBankLabel, constraints);
    }

    /**
     * Sets the status message to display in the view.
     * 
     * @param message Status message with the result of performing an action.
     */
    public void setMessage(String message) {
        
        statusLabel.setText(message);
    }
    
}
