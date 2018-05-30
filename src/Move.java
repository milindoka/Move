import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/** @see https://stackoverflow.com/a/5312702/230513 */
public class Move extends JPanel {

    private static final String TITLE = "Drag me!";
    private static final int W = 640;
    private static final int H = 480;
    private Point textPt = new Point(W / 2, H / 2);
    private Point mousePt;

    public Move() {
    	this.setOpaque(false);
        this.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 32));
        this.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - mousePt.x;
                int dy = e.getY() - mousePt.y;
                textPt.setLocation(textPt.x + dx, textPt.y + dy);
                mousePt = e.getPoint();
                repaint();
            }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(W, H);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w2 = g.getFontMetrics().stringWidth(TITLE) / 2;
        g.drawString(TITLE, textPt.x - w2, textPt.y);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() 
            {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

            	
            	
                JFrame f = new JFrame(TITLE);
                
                
                f.add(new Move());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setUndecorated(true);
                f.setBackground(new Color(0, 0, 0, 0));
               
                
                f.pack();
                f.setLocationRelativeTo(null);
                
                f.setVisible(true);
            }
        });
    }
}

