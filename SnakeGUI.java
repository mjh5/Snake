
//Mike Hennelly
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//GUI for the Snake game
public class SnakeGUI extends JFrame implements ActionListener
{
	JPanel panel;
	JLabel display;
	JLabel length;
	JButton play;

	SnakeScreen screen;
	Timer t;
	int playPauseCount;

	JMenuBar menuBar;
	JMenu newGame;
	JMenuItem startNewGame;
	JMenu settings;
	JMenuItem setSnakeColor;
	JMenuItem setTargetColor;
	JMenuItem setSpeed;

	public SnakeGUI()
	{
		setLayout(new FlowLayout());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();

		menuBar = new JMenuBar();

		newGame = new JMenu("New Game");
		settings = new JMenu("Setting");

		startNewGame = new JMenuItem("New Game");
		startNewGame.addActionListener(this);

		newGame.add(startNewGame);

		setSnakeColor = new JMenuItem("Set Snake Color");
		setSnakeColor.addActionListener(this);

		setTargetColor = new JMenuItem("Set Target Color");
		setTargetColor.addActionListener(this);

		setSpeed = new JMenuItem("Set Speed");
		setSpeed.addActionListener(this);

		settings.add(setSnakeColor);
		settings.add(setTargetColor);
		settings.add(setSpeed);

		menuBar.add(newGame);
		menuBar.add(settings);

		setJMenuBar(menuBar);

		screen = new SnakeScreen();
		screen.addCell();
		screen.addCell();

		screen.setTargetColor(Color.RED);
		screen.setTarget();
		screen.paint();

		display = new JLabel();
		display.setIcon(new ImageIcon(screen.getScreen()));
		panel.add(display);

		play = new JButton("Start");
		play.addActionListener(this);
		panel.add(play);

		length = new JLabel("" + screen.getSnake().size());
		panel.add(length);

		playPauseCount = 0;

		t = new Timer(70, this);
		t.setRepeats(true);

		//Assigning the Key Bindings for the arrow keys
		panel.getInputMap(screen.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");
		panel.getActionMap().put("left", new MoveAction("left", screen));

		panel.getInputMap(screen.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
		panel.getActionMap().put("right", new MoveAction("right", screen));

		panel.getInputMap(screen.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
		panel.getActionMap().put("up", new MoveAction("up", screen));

		panel.getInputMap(screen.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
		panel.getActionMap().put("down", new MoveAction("down", screen));

		add(panel);
	}

	public void gameOver()
	{
		display.setText("Game Over :(");
		play.setText("Play Again");
		playPauseCount = -1;
		t.stop();
	}

	public void newGame()
	{
		screen = new SnakeScreen();
		screen.addCell();
		screen.addCell();

		screen.setTargetColor(Color.RED);
		screen.setTarget();
		screen.paint();

		length.setText("" + screen.getSnake().size());

		panel.getInputMap(screen.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");
		panel.getActionMap().put("left", new MoveAction("left", screen));

		panel.getInputMap(screen.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
		panel.getActionMap().put("right", new MoveAction("right", screen));

		panel.getInputMap(screen.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
		panel.getActionMap().put("up", new MoveAction("up", screen));

		panel.getInputMap(screen.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
		panel.getActionMap().put("down", new MoveAction("down", screen));

		display.setText("");
		play.setText("Play");

		display.setIcon(new ImageIcon(screen.getScreen()));
	}
	//changes the color of the snake based on the String col
	public void setSnakeColor(String col)
	{
		if(col.toUpperCase().equals("BLACK"))
			screen.setSnakeColor(Color.BLACK);
		else if(col.toUpperCase().equals("BLUE"))
			screen.setSnakeColor(Color.BLUE);
		else if(col.toUpperCase().equals("CYAN"))
			screen.setSnakeColor(Color.CYAN);
		else if(col.toUpperCase().equals("DARK_GRAY"))
			screen.setSnakeColor(Color.DARK_GRAY);
		else if(col.toUpperCase().equals("GRAY"))
			screen.setSnakeColor(Color.GRAY);
		else if(col.toUpperCase().equals("GREEN"))
			screen.setSnakeColor(Color.GREEN);
		else if(col.toUpperCase().equals("LIGHT_GRAY"))
			screen.setSnakeColor(Color.LIGHT_GRAY);
		else if(col.toUpperCase().equals("MAGENTA"))
			screen.setSnakeColor(Color.MAGENTA);
		else if(col.toUpperCase().equals("ORANGE"))
			screen.setSnakeColor(Color.ORANGE);
		else if(col.toUpperCase().equals("PINK"))
			screen.setSnakeColor(Color.PINK);
		else if(col.toUpperCase().equals("RED"))
		{
			screen.setSnakeColor(Color.RED);
			//System.out.println("snakeColor changed to RED");
		}
		else if(col.toUpperCase().equals("YELLOW"))
			screen.setSnakeColor(Color.YELLOW);
		else
			setSnakeColor(JOptionPane.showInputDialog("Color not available. Please choose another Color:"));
	}
	//changes the color of the target based on the String col
	public void setTargetColor(String col)
	{
		if(col.toUpperCase().equals("BLACK"))
			screen.setTargetColor(Color.BLACK);
		else if(col.toUpperCase().equals("BLUE"))
			screen.setTargetColor(Color.BLUE);
		else if(col.toUpperCase().equals("CYAN"))
			screen.setTargetColor(Color.CYAN);
		else if(col.toUpperCase().equals("DARK_GRAY"))
			screen.setTargetColor(Color.DARK_GRAY);
		else if(col.toUpperCase().equals("GRAY"))
			screen.setTargetColor(Color.GRAY);
		else if(col.toUpperCase().equals("GREEN"))
			screen.setTargetColor(Color.GREEN);
		else if(col.toUpperCase().equals("LIGHT_GRAY"))
			screen.setTargetColor(Color.LIGHT_GRAY);
		else if(col.toUpperCase().equals("MAGENTA"))
			screen.setTargetColor(Color.MAGENTA);
		else if(col.toUpperCase().equals("ORANGE"))
			screen.setTargetColor(Color.ORANGE);
		else if(col.toUpperCase().equals("PINK"))
			screen.setTargetColor(Color.PINK);
		else if(col.toUpperCase().equals("RED"))
			screen.setTargetColor(Color.RED);
		else if(col.toUpperCase().equals("YELLOW"))
			screen.setTargetColor(Color.YELLOW);
		else
			setTargetColor(JOptionPane.showInputDialog("Color not available. Please choose another Color:"));
	}

	public void actionPerformed(ActionEvent e)
	{
		//handles the Action for the play button
		if(e.getSource().equals(play))
		{
			if(playPauseCount == -1)
			{
				newGame();
			}
			else if(playPauseCount % 2 == 0)
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
		//handles the Action for the setSnakeColor menu item
		else if(e.getSource().equals(setSnakeColor))
		{
			String col = JOptionPane.showInputDialog("Please enter a color:");
			setSnakeColor(col);

			screen.paint();
			display.setIcon(new ImageIcon(screen.getScreen()));
		}
		//handles the Action for the setTargetColor menu item
		else if(e.getSource().equals(setTargetColor))
		{
			String col = JOptionPane.showInputDialog("Please enter a color:");
			setTargetColor(col);

			screen.paint();
			display.setIcon(new ImageIcon(screen.getScreen()));
		}
		//handles the Action for the setSpeed menu item
		else if(e.getSource().equals(setSpeed))
		{
			int speed = Integer.parseInt(JOptionPane.showInputDialog("Please enter a speed(1-5):"));

			t.setDelay(115 - (speed * 15));
		}
		//handles the Action for the startNewGame menu item
		else if(e.getSource().equals(startNewGame))
		{
			newGame();
		}
		//handles the Action for the timer
		else if(e.getSource().equals(t))
		{
			if(length.getText().equals("5,456"))
			{
				display.setText("Congratulations! You have won Snake!");
				play.setText("Play Again");
				playPauseCount = -1;
				t.stop();
			}

			SnakeCell head = screen.getSnakeHead();
			if(head.getCell()[0][0]==0 && head.getDirection().equals("left")){
				gameOver();}
			else if(head.getCell()[0][1]==0 && head.getDirection().equals("up")){
				gameOver();}
			else if(head.getCell()[63][0]==703 && head.getDirection().equals("right")){
				gameOver();}
			else if(head.getCell()[63][1]==495 && head.getDirection().equals("down")){
				gameOver();}
			else
			{
				screen.moveSnake();

				if(screen.checkTargetHit())
				{
					screen.setTarget();
					screen.addCell();
					length.setText("" + screen.getSnake().size());
				}
				else if(screen.checkSnakeHit())
				{
					gameOver();
				}

				screen.paint();
				display.setIcon(new ImageIcon(screen.getScreen()));
			}
		}
	}
}