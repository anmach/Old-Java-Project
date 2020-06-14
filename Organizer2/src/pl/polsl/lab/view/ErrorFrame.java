package pl.polsl.lab.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Frame showing user there was an error.
 *
 * @author Anna Mach
 * @version 1.0
 */
public class ErrorFrame extends JFrame {

    /**
     * Label to show to user in case of error.
     */
    private final JLabel errorLabel;

    /**
     * Constructor for class ErrorFrame.
     */
    ErrorFrame() {
        super("Organizer");
        int fY = 100;
        int fX = 100;
        int fWidth = 600;
        int fHeight = 600;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(fHeight, fWidth);
        setLocation(fX, fY);

        errorLabel = new JLabel("ERROR");
        add(errorLabel);

        pack();
        this.show();
    }
}
