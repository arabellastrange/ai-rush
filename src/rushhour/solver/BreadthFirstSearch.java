package rushhour.solver;

import java.util.*;

import rushhour.model.move.Move;
import rushhour.model.state.State;

public class BreadthFirstSearch extends AbstractSolver {

	@Override
	public Stack<Move> solve(State state) {
		start = System.currentTimeMillis();

        Queue<State> queue = new LinkedList<State>();
		List<State> children;
		HashSet<State> visited = new HashSet<>();
		State current = state;
		queue.add(current);

		nodeCount++;

        while(!queue.isEmpty()) {
            current = queue.remove();
            visited.add(current);

            nodeCount++;

            if(current.isGoal()){
                return calculateMoves(state, current);
            }

            children = current.getChildren();
            if(children.size() != 0){
                for(State child : children){
                    if(!visited.contains(child) && !queue.contains(child)){
                        queue.add(child);
                    }
                }
            }
        }

        return fail();
	}
	
}
