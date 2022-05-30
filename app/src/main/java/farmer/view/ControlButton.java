package farmer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class ControlButton extends JButton implements MouseListener {

    private static final Color BROWN;
    private static final Color BLONDE;
    private static final Font FONT;
    private static final CompoundBorder DEFAULT_BORDER;
    private static final CompoundBorder HOVER_BORDER;

    private ImageIcon icon;
    private ImageIcon hoverIcon;

    static {
        BROWN = new Color(94, 57, 32);
        BLONDE = new Color(246, 236, 213);
        FONT = new Font("Gill Sans MT", Font.BOLD, 24);

        DEFAULT_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BLONDE, 5),
                BorderFactory.createBevelBorder(1)),
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BROWN, 10),
                BorderFactory.createEmptyBorder(5, 20, 5, 20))
        );

        HOVER_BORDER = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BLONDE, 15),
            BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(1),
                BorderFactory.createEmptyBorder(5, 20, 5, 20))
        );
    }

    public ControlButton(String label, ImageIcon icon, ImageIcon hoverIcon) {
        super(label);
        this.icon = icon;
        this.hoverIcon = hoverIcon;
        this.setIcon(icon);
        this.setFocusPainted(false);
        this.setBorder(DEFAULT_BORDER);
        this.setForeground(BROWN);
        this.setBackground(BLONDE);
        this.setFont(FONT);
        this.setOpaque(true);
        addMouseListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setForeground(BLONDE);
        this.setBackground(BROWN);
        this.setBorder(HOVER_BORDER);
        this.setIcon(hoverIcon);

        // this.setForeground(BROWN);
        // this.setBackground(BLONDE);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(BROWN);
        this.setBackground(BLONDE);
        this.setBorder(DEFAULT_BORDER);
        this.setIcon(icon);

        // this.setForeground(BLONDE);
        // this.setBackground(BROWN);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}

    
