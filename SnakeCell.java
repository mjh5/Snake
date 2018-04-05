
//Mike Hennelly
import java.awt.Color;

//Basic snake component, 1 cell of a snake
public class SnakeCell
{
	int[][] cell;
	Color c;
	String direction;

	public SnakeCell()
	{
		cell = new int[64][2];
		c = Color.BLACK;
		direction = "right";
	}

	public SnakeCell(int x, int y)
	{
		cell = new int[64][2];
		fillCell(x,y);
		c = Color.BLACK;
		direction = "right";
	}

	public SnakeCell(int x, int y, Color col)
	{
		cell = new int[64][2];
		fillCell(x,y);
		c = col;
		direction = "right";
	}

	public void fillCell(int xStart, int yStart)
	{
		for(int r=0; r<64; r++)
				cell[r][0] = xStart + r/8;

		for(int r=0; r<63; r+=8)
			for(int i=0; i<8; i++)
				cell[r+i][1] = yStart + i;
	}

	//returns the position of the top left corner of the SnakeCell
	public int[] getTopLeft()
	{
		return cell[0];
	}

	public int[][] getCell()
	{
		return cell;
	}

	public Color getColor()
	{
		return c;
	}

	public String getDirection()
	{
		return direction;
	}

	public void setColor(Color col)
	{
		c = col;
	}

	public void setDirection(String d)
	{
		direction = d;
	}

	public void move()
	{
		if(direction.equals("up"))
		{
				fillCell(cell[0][0], cell[0][1] - 8);
		}
		else if(direction.equals("down"))
		{
				fillCell(cell[0][0], cell[0][1] + 8);
		}
		else if(direction.equals("right"))
		{
				fillCell(cell[0][0] + 8, cell[0][1]);
		}
		else if(direction.equals("left"))
		{
				fillCell(cell[0][0] - 8, cell[0][1]);
		}
		else
			System.out.println("\nmove() error");
	}

	public boolean equals(Object obj)
	{
		SnakeCell other = (SnakeCell)obj;

		return (getTopLeft()[0]==other.getTopLeft()[0]) && (getTopLeft()[1]==other.getTopLeft()[1]);
	}
}