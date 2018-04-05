//Mike Hennelly
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class SnakeJApplet extends JApplet implements ActionListener
{
	JLabel display;
	JButton play;
	SnakeScreen screen;
	Timer t;
	int playPauseCount;

	public void init()
	{
		screen = new SnakeScreen();
		screen.addCell();
		screen.addCell();

		screen.setTargetColor(Color.GREEN);
		screen.setTarget();
		screen.paint();

		display = new JLabel(new ImageIcon(screen.getScreen()));
		add(display);

		play = new JButton("Start");
		play.addActionListener(this);
		add(play);

		playPauseCount = 0;

		t = new Timer(100, this);
		t.setRepeats(true);

		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
		getActionMap().put("left", new MoveAction("left", screen));
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(play))
		{
			if(playPauseCount % 2 == 0)
			{
				play.setText("Pause");
				t.start();
			}
			else
			{
				play.setText("Play");
				t.stop();
			}

			playPauseCount++;
		}
		else if(e.getSource().equals(t))
		{
			screen.moveSnake();

			for(int i=0; i<64; i++)
			{
				int[][] head = screen.getSnakeHead().getCell();
				if(head[i][0]<0 || head[i][0]>700 || head[i][1]<0 || head[i][1]>500)
					display.setText("Game Over :(");
			}

			if(screen.checkTargetHit())
			{
				screen.setTarget();
				screen.addCell();
			}

			screen.paint();
			display.setIcon(new ImageIcon(screen.getScreen()));
		}
	}

}
