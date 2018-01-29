package rushhour.solver;

import java.util.List;
import java.util.Stack;

import rushhour.model.move.Move;
import rushhour.model.state.State;

public class BreadthFirstSearch extends AbstractSolver {

	@Override
	public Stack<Move> solve(State state) {
		start = System.currentTimeMillis();
		List<State> children = state.getChildren();

		State current = children.get(0);

        while(!current.isGoal()) {
            nodeCount++;
            children = current.getChildren();
            State next = children.get(1);
            current = next;
        }

        return calculateMoves(state, current);
	}
	
}
