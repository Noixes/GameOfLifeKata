
public class GameOfLife
{
	boolean[][] overworld;
	private int width;
	private int height;
	
	public GameOfLife(int width, int height)
	{
		if (width <= 0 || height <= 0)
		{
			throw new NoDimensionException("Width or height can't be 0 or less.");
		}
		this.width = width;
		this.height = height;
		overworld = new boolean[width][height];
	}
	
	public GameOfLife setLife(int x, int y)
	{
		overworld[x][y] = true;
		return this;
	}
	
	public boolean hasLife(int x, int y)
	{
		try
		{
			return overworld[x][y];
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
	}
	
	public GameOfLife tick()
	{
		GameOfLife gameOfLifeAfterTick = new GameOfLife(width, height);
		
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				gameOfLifeAfterTick.overworld[x][y] = getNeighbours(x, y) >= 2;
				
				if (getNeighbours(x, y) >= 3 && hasLife(x, y))
				{
					gameOfLifeAfterTick.overworld[x][y] = false;
				}
				else if (getNeighbours(x, y) >= 3)
				{
					gameOfLifeAfterTick.overworld[x][y] = true;
				}
			}
		}
		return gameOfLifeAfterTick;
	}
	
	private int getNeighbours(int x, int y)
	{
		int neighbours = 0;
		
		neighbours += hasLife(x - 1, y - 1) ? 1 : 0;
		neighbours += hasLife(x - 1, y) ? 1 : 0;
		
		neighbours += hasLife(x - 1, y + 1) ? 1 : 0;
		neighbours += hasLife(x, y + 1) ? 1 : 0;
		
		neighbours += hasLife(x + 1, y + 1) ? 1 : 0;
		neighbours += hasLife(x + 1, y) ? 1 : 0;
		
		neighbours += hasLife(x + 1, y - 1) ? 1 : 0;
		neighbours += hasLife(x, y - 1) ? 1 : 0;
		
		return neighbours;
	}
}