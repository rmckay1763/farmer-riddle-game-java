package farmer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 * Button for an action the user can perform while playing the game.
 */
public class ControlButton extends JButton implements MouseListener {

    private static final Color BROWN;
    private static final Color BLONDE;
    private static final Font FONT;
    private static final CompoundBorder DEFAULT_BORDER;
    private static final CompoundBorder HOVER_BORDER;

    static {

        BROWN = new Color(94, 57, 32);
        BLONDE = new Color(246, 236, 213);
        FONT = new Font("Gill Sans MT", Font.BOLD, 18);

        DEFAULT_BORDER = BorderFactory.createCompoundBorder(
                
            BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(0),
                BorderFactory.createLineBorder(BROWN, 5)
            ),

            BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            )
        
        );

        HOVER_BORDER = BorderFactory.createCompoundBorder(

            BorderFactory.createLineBorder(BLONDE, 5),

            BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            )

        );
    }

    /**
     * The default icon to display with the button.
     */
    private ImageIcon icon;

    /**
     * The icon to display when the mouse enters the button area.
     */
    private ImageIcon hoverIcon;

    /**
     * Constructor. Sets the text label for the button.
     * @param label Description of the action the button performs.
     */
    public ControlButton(String label) {

        super(label);
        setFocusPainted(false);
        setBorder(DEFAULT_BORDER);
        setForeground(BROWN);
        setBackground(BLONDE);
        setFont(FONT);
        setOpaque(true);
        addMouseListener(this);
    }

    /**
     * Sets the default icon for the button.
     * 
     * @param icon Icon to display on the button.
     */
    public void setDefaultIcon(ImageIcon icon) {

        this.icon = icon;
        setIcon(icon);
    }

    /**
     * Sets the icon to use during mouse hover events.
     * 
     * @param hoverIcon Icon to display when the mouse enters the button area.
     */
    public void setHoverIcon(ImageIcon hoverIcon) {

        this.hoverIcon = hoverIcon;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        this.setForeground(BLONDE);
        this.setBackground(BROWN);
        this.setBorder(HOVER_BORDER);
        this.setIcon(hoverIcon);
    }

    @Override
    public void mouseExited(MouseEvent e) {

        this.setForeground(BROWN);
        this.setBackground(BLONDE);
        this.setBorder(DEFAULT_BORDER);
        this.setIcon(icon);
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
}

    
