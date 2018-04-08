package rushhour.solver;

import rushhour.model.move.Move;
import rushhour.model.state.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class IterativeDeepening extends AbstractSolver {
    int maxDepth;
    int depth;
    boolean goalFound;
    State goal;
    List<State> children;
    HashSet<StateDepth> visited;
    Stack<StateDepth> stack;

    @Override
    public Stack<Move> solve(State state) {
        start = System.currentTimeMillis();
        stack = new Stack<StateDepth>();
        goalFound = state.isGoal();
        maxDepth = 1;
        visited = new HashSet<StateDepth>();

        State c = state;
        StateDepth current = new StateDepth(c, 0);
        stack.push(current);
        nodeCount++;

        while (!goalFound) {
            System.out.println("Goal not yet found");
            System.out.println("Current max depth pre loop " +  maxDepth);
            if(maxDepth < 10){
                depthfirstsearch(current);
                visited.clear();
                //stack.clear();
                maxDepth++;
            }
            else {
                return fail();
            }
        }
        return calculateMoves(state, goal);
    }

    public void depthfirstsearch(StateDepth c){
        visited = new HashSet<>();
        depth = 0;
        System.out.println("Initial depth: " + depth);
        while(depth <= maxDepth){ //need to change this condition
            System.out.println("Current max depth in loop " + maxDepth);
            System.out.println("Current depth " + depth);
            System.out.println("Current NODE: " + c.toString());
            c = stack.pop();
            visited.add(c);
            //if(depth <= maxDepth){
                if(c.getState().isGoal()){
                    System.out.println("Max depth at goals is: " + maxDepth);
                    goalFound = true;
                    goal = c.getState();
                    return;
                }

                children = c.getState().getChildren(); //check for unvisited children, push to stack
                if(children.size() != 0){
                    depth++;
                    nodeCount++;
                    for(State child : children){
                        StateDepth childDepth = new StateDepth(child, depth);
                        if(!visited.contains(childDepth)){
                            //!stack.contains(childDepth)
                            stack.push(childDepth);
                            System.out.println("Current Stack size: " + stack.size());
                        }
                    }
                }
           // }
        }
    }

    class StateDepth{
        State s;
        int d;

        StateDepth(State state, int depth){
            s = state;
            d = depth;
        }

        @Override
        public boolean equals(Object obj) {
            StateDepth sd = (StateDepth) obj;
            if(sd.getDepth() == this.getDepth() && this.getState().equals(sd.getState())){
                return true;
            }
            else {
                return false;
            }

        }

        @Override
        public int hashCode(){
            String numID = this.getState().toString().replaceAll("[a-zA-Z]", "0");
            numID = numID.replaceAll("\\.", "0");
            numID = numID.replaceAll( "[^\\w\\s]", "0");
            return Integer.valueOf(numID) + this.getDepth();
        }

        public int getDepth() {
            return d;
        }

        public State getState() {
            return s;
        }

    }
}
