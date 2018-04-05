//Mike Hennelly

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.*;

//Wrapper class for the image containing the Snake and Target
public class SnakeScreen extends JComponent
{
	BufferedImage background;
	ArrayList<SnakeCell> snake;
	Color snakeColor;
	Color targetColor;
	SnakeCell target;

	public SnakeScreen()
	{
		ImageIcon whiteBackground = new ImageIcon(getClass().getResource("white-background.JPG"));

		setBackgroundImage(whiteBackground);

		snakeColor = Color.BLACK;

		snake = new ArrayList<SnakeCell>();
		snake.add(new SnakeCell(352,248));

		targetColor = Color.RED;
	}

	public SnakeScreen(Color c)
	{
		ImageIcon whiteBackground = new ImageIcon(getClass().getResource("white-background.JPG"));

		setBackgroundImage(whiteBackground);

		snakeColor = c;

		snake = new ArrayList<SnakeCell>();
		snake.add(new SnakeCell(352,248, snakeColor));

		targetColor = Color.RED;
	}

	public void setBackgroundImage(ImageIcon custom)
	{
		background = new BufferedImage(704,496, BufferedImage.TYPE_INT_RGB);

		Graphics g = background.createGraphics();
		custom.paintIcon(null, g, 0, 0);

	}

	public void setSnakeColor(Color c)
	{
		snakeColor = c;
	}

	public void setTargetColor(Color c)
	{
		targetColor = c;
	}

	public ArrayList<SnakeCell> getSnake()
	{
		return snake;
	}

	public SnakeCell getSnakeHead()
	{
		return snake.get(0);
	}

	public void setTarget()
	{
		int xPos;
		int yPos;

		boolean xCheck = true;
		boolean yCheck = true;

		do{
			xPos = (int)(Math.random() * 600 + 1);
			yPos = (int)(Math.random() * 400 + 1);

			//making sure xPos and yPos are multiples of 8 so they line up directly with the snake
			xPos -= xPos%8;
			yPos -= yPos%8;

			for(int s=0; s<snake.size(); s++)
				for(int i=0; i<16; i++)
				{
					if(snake.get(s).getCell()[i][0] == xPos)
						xCheck = false;

					if(snake.get(s).getCell()[i][1] == yPos)
						yCheck = false;

				}
		}while(!xCheck && !yCheck);

		target = new SnakeCell(xPos, yPos, targetColor);
	}

	public void paint()
	{
		ImageIcon whiteBackground = new ImageIcon(getClass().getResource("white-background.JPG"));

		setBackgroundImage(whiteBackground);


		for(int i=0; i<snake.size(); i++)
			for(int c=0; c<64; c++)
			{
				int x = snake.get(i).getCell()[c][0];
				int y = snake.get(i).getCell()[c][1];

				background.setRGB( x,  y, snakeColor.getRGB());
			}

		int[][] targetCell = target.getCell();
		for(int c=0; c<64; c++)
			background.setRGB(targetCell[c][0] , targetCell[c][1] , targetColor.getRGB());
	}

	public BufferedImage getScreen()
	{
		return background;
	}

	public void addCell()
	{
		SnakeCell lastCell = snake.get(snake.size()-1);

		if(lastCell.getDirection().equals("right"))
		{
			snake.add(new SnakeCell(lastCell.getTopLeft()[0]-8, lastCell.getTopLeft()[1]));
			snake.get(snake.size()-1).setDirection(snake.get(snake.size()-2).getDirection());
		}
		else if(lastCell.getDirection().equals("left"))
		{
			snake.add(new SnakeCell(lastCell.getTopLeft()[0]+8, lastCell.getTopLeft()[1]));
			snake.get(snake.size()-1).setDirection(snake.get(snake.size()-2).getDirection());
		}
		else if(lastCell.getDirection().equals("up"))
		{
			snake.add(new SnakeCell(lastCell.getTopLeft()[0], lastCell.getTopLeft()[1] + 8));
			snake.get(snake.size()-1).setDirection(snake.get(snake.size()-2).getDirection());
		}
		else if(lastCell.getDirection().equals("down"))
		{
			snake.add(new SnakeCell(lastCell.getTopLeft()[0], lastCell.getTopLeft()[1] - 8));
			snake.get(snake.size()-1).setDirection(snake.get(snake.size()-2).getDirection());
		}
	}

	public void moveSnake()
	{
		snake.get(0).move();
		for(int i=snake.size()-1; i>0; i--)
		{
			snake.get(i).move();
			snake.get(i).setDirection(snake.get(i-1).getDirection());
		}
	}

	public void setDirection(String d)
	{
		snake.get(0).setDirection(d);
	}

	//Check whether the snake has hit the target
	public boolean checkTargetHit()
	{
		for(int i=0; i<snake.size(); i++)
			for(int s=0; s<64; s++)
				for(int tar=0; tar<64; tar++)
					if((snake.get(i).getCell()[s][0] == target.getCell()[tar][0]) && (snake.get(i).getCell()[s][1] == target.getCell()[tar][1]))
						return true;

		return false;
	}
	//Check to see if the snake has crossed over itself, which results in Game Over
	public boolean checkSnakeHit()
	{
		for(int m=0; m<snake.size(); m++)
			for(int n=1; n<snake.size(); n++)
				if(m!=n)
					if(snake.get(m).equals(snake.get(n)))
						return true;

		return false;
	}
}
