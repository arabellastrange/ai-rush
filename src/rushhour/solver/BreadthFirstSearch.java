package rushhour.solver;

import java.util.Stack;

import rushhour.model.move.Move;
import rushhour.model.state.State;

public class BreadthFirstSearch extends AbstractSolver {

	@Override
	public Stack<Move> solve(State state) {
		start = System.currentTimeMillis();

		return fail();
	}
	
}
