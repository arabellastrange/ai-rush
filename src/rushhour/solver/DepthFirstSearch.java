package rushhour.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import rushhour.model.move.Move;
import rushhour.model.state.State;

public class DepthFirstSearch extends AbstractSolver {

	@Override
	public Stack<Move> solve(State state) {
	    Stack stack = new Stack();

		start = System.currentTimeMillis();

		List<State> children = state.getChildren();
		//List<State> visited = new ArrayList<State>() ;

        State current = children.get(0);
        stack.push(current);
        //visited.add(current);


		while(!current.isGoal() && !stack.isEmpty()) {
			nodeCount++;
			children = current.getChildren();
			if(children.size() != 0){
                State next = children.get(1);
                current = next;
                stack.push(current);
                //visited.add(current);
            }

		}

		return calculateMoves(state, current);
	}
	
}
