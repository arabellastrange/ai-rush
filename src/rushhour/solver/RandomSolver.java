package rushhour.solver;

import java.util.List;
import java.util.Random;
import java.util.Stack;

import rushhour.model.move.Move;
import rushhour.model.state.State;

public class RandomSolver extends AbstractSolver {
	
	private final Random rng = new Random();

	@Override
	public Stack<Move> solve(State state) {
		
		start = System.currentTimeMillis();
		
		List<State> children = state.getChildren();
		
		State current = children.get(rng.nextInt(children.size()));
		
		while(!current.isGoal()) {
			nodeCount++;
			children = current.getChildren();
			State next = children.get(rng.nextInt(children.size()));
			current = next;
		}
		
		return calculateMoves(state, current);
	}

}
