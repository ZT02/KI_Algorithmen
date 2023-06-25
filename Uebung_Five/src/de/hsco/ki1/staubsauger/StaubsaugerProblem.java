package Uebung_Five.src.de.hsco.ki1.staubsauger;

import Uebung_Five.src.de.hsco.ki1.Problem;
import Uebung_Five.src.de.hsco.ki1.State;

import java.util.Arrays;
import java.util.List;

public class StaubsaugerProblem implements Problem {

    private final int numRooms;
    private final int startPos;

    private final List<String> actions = List.of("L", "S", "R");

    public StaubsaugerProblem(int numRooms, int startPos) {
        this.numRooms = numRooms;
        this.startPos = startPos;
    }

    @Override
    public State getInitialState() {
        boolean[] dirtyRooms = new boolean[numRooms];
        Arrays.fill(dirtyRooms, true);
        StaubsaugerState s = new StaubsaugerState(this, dirtyRooms, startPos);
        return s;
    }

    @Override
    public List<String> getActions(State s) {
        return actions;
    }

    @Override
    public State getResult(State s, String action) {
        StaubsaugerState state = (StaubsaugerState)s;

        // Variables to cache the values for the new state
        int newPos = 0;
        boolean[] newIsDirty = Arrays.copyOf(state.getDirtyRooms(), numRooms);

        if( "S".equalsIgnoreCase(action) ) {
            newPos = state.getPos();
            newIsDirty[state.getPos()] = false;
        } else if ( "L".equalsIgnoreCase(action) ) {
            newPos = Math.max(0, state.getPos() - 1);
        } else if ( "R".equalsIgnoreCase(action) ) {
            newPos = Math.min(numRooms - 1, state.getPos() + 1);
        } else {
            return null;
        }

        // build new state
        StaubsaugerState succState = new StaubsaugerState(this, newIsDirty, newPos);
        return succState;
    }

    @Override
    public boolean isGoalState(State s) {
        StaubsaugerState state = (StaubsaugerState)s;

        // Goal is reached if every room is clean
        boolean[] isDirty = state.getDirtyRooms();
        boolean allClean = true;
        // Check every room
        for( int i = 0; i < numRooms; i++ ) {
            // If one room is dirty, not all are clean
            if( isDirty[i] ) {
                allClean = false;
            }
        }

        return allClean;
    }

    @Override
    public double getCost(State s, String action, State succ) {
        // all actions cost 1
        return 1;
    }
}
