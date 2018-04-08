package rushhour.solver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import rushhour.model.move.Move;
import rushhour.model.state.State;

public class BreadthFirstBenchmark extends AbstractSolver {

	@Override
	public Stack<Move> solve(State state) {
		
		Set<State> visited = new HashSet<State>();
		Queue<State> agenda = new LinkedList<State>();
		nodeCount = 0;
		
		start = System.currentTimeMillis();
		
		agenda.add(state);
		
		while(agenda.size() > 0) {
			State current = agenda.remove();
			nodeCount++;
			if (current.isGoal()) {
				return calculateMoves(state, current);
			}
			visited.add(current);
			List<State> children = current.getChildren();
			for(State child : children) {
				if (!agenda.contains(child) && !visited.contains(child)) {
					agenda.add(child);
				}
			}
		}
		return fail();
	}
	
}
