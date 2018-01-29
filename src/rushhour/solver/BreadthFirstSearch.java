package rushhour.solver;

import java.util.*;

import rushhour.model.move.Move;
import rushhour.model.state.State;

public class BreadthFirstSearch extends AbstractSolver {

	@Override
	public Stack<Move> solve(State state) {
		start = System.currentTimeMillis();

        Queue<State> queue = new LinkedList<State>();
		List<State> children = state.getChildren();
		List<State> visited = new ArrayList<State>();
		State current = state;
		queue.add(current);

        System.out.println("Parent: " + current.toString());
		nodeCount++;

        while(!queue.isEmpty()) {
            current = queue.remove();
            visited.add(current);

            System.out.println("Current: " + current.toString());
            nodeCount++;

            if(current.isGoal()){
                return calculateMoves(state, current);
            }

            children = current.getChildren();
            if(children.size() != 0){
                for(int i = 0; i  < children.size(); i++){
                    if(!visited.contains(children.get(i))){
                        queue.add(children.get(i));
                    }
                }
            }
        }

        return fail();
	}
	
}
