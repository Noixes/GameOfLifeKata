import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class GameOfLifeTest
{
	
	@Test
	public void oneCellCanExistOnTheGameOverworld() throws Exception
	{
		Assert.assertThat(new GameOfLife(1, 1).setLife(0, 0).hasLife(0, 0), CoreMatchers.is(true));
	}
	
	@Test
	public void oneCellIsDeadIfWeNotSetItToLive() throws Exception
	{
		Assert.assertThat(new GameOfLife(1, 1).hasLife(0, 0), CoreMatchers.is(false));
	}
	
	@Test
	public void twoCellsCanExistOnTheGameOverworldVertically() throws Exception
	{
		Assert.assertThat(new GameOfLife(2, 1).setLife(1, 0).hasLife(0, 0), CoreMatchers.is(false));
	}
	
	@Test
	public void twoCellsCanExistOnTheGameOverworldHorizontally() throws Exception
	{
		Assert.assertThat(new GameOfLife(1, 2).setLife(0, 1).hasLife(0, 0), CoreMatchers.is(false));
	}
	
	@Test
	public void lonelyCellDiesAfterOneTick() throws Exception
	{
		Assert.assertThat(new GameOfLife(1, 1).setLife(0, 0).tick().hasLife(0, 0), CoreMatchers.is(false));
	}
	
	@Test
	public void threeCellsInARowMiddleDontDieHorizontally() throws Exception
	{
		Assert.assertThat(new GameOfLife(1, 3).setLife(0, 0).setLife(0, 1).setLife(0, 2).tick().hasLife(0, 1),
				CoreMatchers.is(true));
	}
	
	@Test
	public void threeCellsInARowMiddleDontDieVertically() throws Exception
	{
		Assert.assertThat(new GameOfLife(3, 1).setLife(0, 0).setLife(1, 0).setLife(2, 0).tick().hasLife(1, 0),
				CoreMatchers.is(true));
	}
	
	@Test
	public void threeCellsInARowRightDiesHorizontally() throws Exception
	{
		Assert.assertThat(new GameOfLife(3, 1).setLife(0, 0).setLife(1, 0).setLife(2, 0).tick().hasLife(2, 0),
				CoreMatchers.is(false));
	}
	
	@Test
	public void threeCellsInARowRightDiesVertically() throws Exception
	{
		Assert.assertThat(new GameOfLife(1, 3).setLife(0, 0).setLife(0, 1).setLife(0, 2).tick().hasLife(0, 2),
				CoreMatchers.is(false));
	}
	
	@Test
	public void threeCellsLikeFormOfLCornerDontDieAfterTick() throws Exception
	{
		Assert.assertThat(new GameOfLife(2, 2).setLife(0, 0).setLife(0, 1).setLife(1, 0).tick().hasLife(0, 0),
				CoreMatchers.is(true));
	}
	
	@Test
	public void cellComesToLifeIfItHasThreeNeighboursAfterTick() throws Exception
	{
		Assert.assertThat(new GameOfLife(3, 3).setLife(0, 0).setLife(0, 1).setLife(0, 2).tick().hasLife(1, 1),
				CoreMatchers.is(true));
	}
	
	@Test
	public void cellComesToLiveIfItHasMoreThanThreeNeighbours() throws Exception
	{
		Assert.assertThat(
				new GameOfLife(3, 3).setLife(0, 0).setLife(0, 2).setLife(1, 0).setLife(1, 1).tick().hasLife(0, 1),
				CoreMatchers.is(true));
	}
	
	@Test
	public void cellDiesIfItHasMoreThanThreeNeighbours() throws Exception
	{
		Assert.assertThat(
				new GameOfLife(3, 3).setLife(0, 1)
						.setLife(0, 0)
						.setLife(0, 2)
						.setLife(1, 0)
						.setLife(1, 1)
						.setLife(1, 2)
						.tick()
						.hasLife(0, 1),
				CoreMatchers.is(false));
	}
	
	@Test(expected = NoDimensionException.class)
	public void shouldThrowStupidExceptionIfUserIsDumb() throws Exception
	{
		new GameOfLife(-1, -1);
	}
}