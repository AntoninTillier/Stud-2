import java.awt.Color;
import java.awt.Graphics;

public class Animation {
    protected double angle = Math.PI * 1.999;
    protected int length;
    protected int count = 0;

    @SuppressWarnings("unused")
    public void anime() {
	if(count % 30 == 0)
	    length = 50+ (int)(Math.random()*((Frame.panel.getHeight() - 450) + 1));
	count ++;
	double angleAccel, angleVelocity = 0, dt = 0.1;
	angleAccel = 9.81 / length * Math.sin(angle);
	if(angleVelocity <1.5) 
	    angleVelocity =0.1; 
	angle += angleVelocity * dt;
	Frame.panel.repaint();
    }

    public void paint_animation(Graphics g) {
	g.setColor(Color.white);
	g.fillRect(0, 0, Frame.panel.getWidth(), Frame.panel.getHeight());
	g.setColor(Color.BLACK);
	int anchorX = Frame.panel.getWidth() / 2, anchorY = Frame.panel.getHeight() / 2;
	int ballX = anchorX + (int) (Math.sin(angle) * length);
	int ballY = anchorY + (int) (Math.cos(angle) * length);
	g.drawLine(anchorX, anchorY, ballX, ballY);
	g.fillOval(anchorX - 3, anchorY - 4, 7, 7);
	g.fillOval(ballX - 7, ballY - 7, 14, 14);
    }
}