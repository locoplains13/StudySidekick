package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MainPanel extends ViewPanel{
    public static final long serialVersionUID = 1;

    static final int FONT_SIZE = 32;
    static final int BUTTON_SPACER_SIZE = 30;
    static final int BUTTON_WIDTH = 140;
    static final int BUTTON_HEIGHT = 30;

    private static JPanel mainPanel;
    private static ActionListener listener;

    /**
     * Create the main application panel for when the application is launched or the user returns to the main menu
     * @param width the width of the panel
     * @param height the height of the panel
     * @param listener the class listening for the event that signals a button was pressed
     */
    public MainPanel(int width, int height, ActionListener listener) {

        this.listener = listener;

//////////////////////////////////////////////////////////////////////////////////////////////

        JPanel jPanelTop = new JPanel();

        // set initial conditions for jPanelTop
        jPanelTop.setSize(new Dimension(getWidth(), 250));
        jPanelTop.setLayout(new BorderLayout());
        jPanelTop.setBackground(new Color(179, 217, 255));

        // create components for jPanelTop
        JLabel jLabelLogo =  new JLabel();
        ImageIcon logoImageIcon = new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"));
        Image scaledImageLogo = getScaledImage(logoImageIcon.getImage(), 300, 100);
        jLabelLogo.setIcon(new ImageIcon(scaledImageLogo));

        // add components to jPanelTop
        jPanelTop.add(jLabelLogo, BorderLayout.WEST);

//////////////////////////////////////////////////////////////////////////////////////////////

        // Set initial conditions for this
        setSize(width, height);
        setLayout(new BorderLayout());

        // create components for this
        JPanel jPanelSidePanel = new MainSidePanel(listener);

        add(jPanelTop,BorderLayout.PAGE_START);
        add(jPanelSidePanel,BorderLayout.WEST);
        add(getMainPanel(),BorderLayout.CENTER);

//////////////////////////////////////////////////////////////////////////////////////////////

    }
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public static JPanel getMainPanel(){
        if (mainPanel==null){
            mainPanel = new DashboardPanel(listener);
        }
        return mainPanel;
    }

    public static void setMainPanel(JPanel panel){
        mainPanel = panel;
    }
}
