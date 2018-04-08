package rushhour.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import rushhour.model.move.Move;
import rushhour.model.state.State;

public class DepthFirstSearch extends AbstractSolver {

	@Override
	public Stack<Move> solve(State state) {
		start = System.currentTimeMillis();

        Stack<State> stack = new Stack<State>();
		List<State> children = state.getChildren();
		List<State> visited = new ArrayList<State>();
        State current = state;
        stack.push(current);

        //System.out.println("Parent: " + current.toString());
        nodeCount++;

		while(!stack.isEmpty()) {
		    current = stack.pop();
		    visited.add(current);
		    //System.out.println("Current: " + current.toString());
		    nodeCount++;
            if(current.isGoal()){
                return calculateMoves(state, current);
            }
            children = current.getChildren(); //check for unvisited children, push to stack
            if(children.size() != 0){
                for(int i = 0; i  < children.size(); i++){
                    if(!visited.contains(children.get(i)) && !stack.contains(children.get(i))){
                        stack.push(children.get(i));
                    }
                }
            }
		}
		return fail();
	}
	
}
