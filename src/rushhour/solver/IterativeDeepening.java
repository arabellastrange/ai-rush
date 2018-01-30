package rushhour.solver;

import rushhour.model.move.Move;
import rushhour.model.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeDeepening extends AbstractSolver {
    int maxDepth;
    int depth;
    boolean goalFound;
    List<State> children;
    List<State> visited;
    Stack<State> stack;
    State goal;

    @Override
    public Stack<Move> solve(State state) {
        start = System.currentTimeMillis();

        stack = new Stack<State>();
        goalFound = state.isGoal();
        maxDepth = 0;

        State current = state;
        stack.push(current);

        nodeCount++;

        while(!goalFound){
            System.out.println("Goal not yet found");
            System.out.println("Current max depth pre loop " + maxDepth);
            if(maxDepth < 500){
                depthLimitedSearch(current);
                maxDepth++;
            }
            else{
                return fail();
            }
        }
        System.out.println("Goal found at: " + depth);
        System.out.println("Max depth at goals is: " + maxDepth);
        return calculateMoves(state, goal);
    }

    public void depthLimitedSearch(State c){
        visited = new ArrayList<State>();
        depth = 0;

        while(!stack.isEmpty()) {
            c = stack.pop();
            visited.add(c);
            nodeCount++;
            System.out.println("initial depth: " + depth);
            System.out.println("Current max depth in loop " + maxDepth);

            if(c.isGoal()){
                goalFound = true;
                goal = c;
                return;
            }
            else if(depth <= maxDepth){
                children = c.getChildren(); //check for unvisited children, push to stack
                if(children.size() != 0){
                    depth++;
                    System.out.println("Current depth: " + depth);
                    for(int i = 0; i  < children.size(); i++){
                        if(!visited.contains(children.get(i)) && !stack.contains(children.get(i))){
                            stack.push(children.get(i));
                        }
                    }
                }
            }
            else{
                return;
            }
        }
    }
}
