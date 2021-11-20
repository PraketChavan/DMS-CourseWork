package code;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class DebugPanel extends JPanel {

    private static final Color DEF_BKG = Color.WHITE;


    private JButton m_skipLevel;
    private JButton m_resetBalls;

    private JSlider m_ballXSpeed;
    private JSlider m_ballYSpeed;

    private Wall m_wall;

    public JButton getSkipLevel() {
        return m_skipLevel;
    }

    public void setSkipLevel(JButton skipLevel) {
        this.m_skipLevel = skipLevel;
    }

    public JButton getResetBalls() {
        return m_resetBalls;
    }

    public void setResetBalls(JButton resetBalls) {
        this.m_resetBalls = resetBalls;
    }

    public JSlider getBallXSpeed() {
        return m_ballXSpeed;
    }

    public void setBallXSpeed(JSlider ballXSpeed) {
        this.m_ballXSpeed = ballXSpeed;
    }

    public JSlider getBallYSpeed() {
        return m_ballYSpeed;
    }

    public void setBallYSpeed(JSlider ballYSpeed) {
        this.m_ballYSpeed = ballYSpeed;
    }

    public Wall getWall() {
        return m_wall;
    }

    public void setWall(Wall wall) {
        this.m_wall = wall;
    }

    public DebugPanel(Wall wall) {

        this.setWall(wall);

        initialize();

        setSkipLevel(makeButton("Skip Level", e -> wall.nextLevel()));
        setResetBalls(makeButton("Reset Balls", e -> wall.resetBallCount()));

        setBallXSpeed(makeSlider(-4, 4, e -> wall.setBallXSpeed(getBallXSpeed().getValue())));
        setBallYSpeed(makeSlider(-4, 4, e -> wall.setBallYSpeed(getBallYSpeed().getValue())));

        this.add(getSkipLevel());
        this.add(getResetBalls());

        this.add(getBallXSpeed());
        this.add(getBallYSpeed());

    }

    public void initialize() {
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2, 2));
    }

    public JButton makeButton(String title, ActionListener e) {
        JButton out = new JButton(title);
        out.addActionListener(e);
        return out;
    }

    public JSlider makeSlider(int min, int max, ChangeListener e) {
        JSlider out = new JSlider(min, max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    public void setValues(int x, int y) {
        getBallXSpeed().setValue(x);
        getBallYSpeed().setValue(y);
    }
}
