package rushhour.solver;

import rushhour.model.move.Move;
import rushhour.model.state.State;

import java.util.Stack;

public class IDDFS extends AbstractSolver {
    int depthLimit = 200;
    State found;

    @Override
    public Stack<Move> solve(State state) {
        start = System.currentTimeMillis();

        for(int i = 0; i < depthLimit; i++){
           found = dls(state, i);
           if(found != null){
               return calculateMoves(state, found); // moves
           }
       }

       return fail();
    }

    public State dls(State node, int depth){

        if(depth == 0 && node.isGoal()){
            return  node;
        }

        if(depth > 0){
            for (State child: node.getChildren()){
                found = dls(child, depth -1);
                if (found != null){
                    return found;
                }
            }
        }

        return null;
    }
}

