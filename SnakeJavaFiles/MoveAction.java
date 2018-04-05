//Mike Hennelly

import java.awt.event.*;
import javax.swing.*;

//Handles actions for the key bindings in SnakeGUI
public class MoveAction extends AbstractAction
{
	String direction;
	SnakeScreen screen;
	public MoveAction(String s, SnakeScreen ss)
	{
		direction = s;
		screen = ss;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(direction.equals("right") && !screen.getSnakeHead().getDirection().equals("left"))
			screen.setDirection(direction);
		else if(direction.equals("left") && !screen.getSnakeHead().getDirection().equals("right"))
			screen.setDirection(direction);
		else if(direction.equals("up") && !screen.getSnakeHead().getDirection().equals("down"))
			screen.setDirection(direction);
		else if(direction.equals("down") && !screen.getSnakeHead().getDirection().equals("up"))
			screen.setDirection(direction);
	}
}
