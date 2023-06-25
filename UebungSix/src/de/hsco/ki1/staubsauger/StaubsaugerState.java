package UebungSix.src.de.hsco.ki1.staubsauger;


import UebungSix.src.de.hsco.ki1.Problem;
import UebungSix.src.de.hsco.ki1.State;

import java.util.Arrays;
import java.util.Objects;

public class StaubsaugerState implements State {

    private final boolean[] dirtyRooms;
    private final int pos;

    private final StaubsaugerProblem prob;

    public StaubsaugerState(StaubsaugerProblem problem, boolean[] dirtyRooms, int pos) {
        this.prob = problem;
        this.dirtyRooms = Arrays.copyOf(dirtyRooms, dirtyRooms.length);
        this.pos = pos;
    }

    @Override
    public Problem getProblem() {
        return this.prob;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < dirtyRooms.length; i++ ) {
            if( i > 0 ) {
                sb.append('|');
            }
            if( dirtyRooms[i] ) {
                sb.append('~');
            } else {
                sb.append(' ');
            }
            if( i == pos ) {
                sb.append('X');
            } else {
                sb.append(' ');
            }
        }
        sb.append(']');

        return sb.toString();
    }

    public int getPos() {
        return pos;
    }

    public boolean[] getDirtyRooms() {
        return Arrays.copyOf(dirtyRooms, dirtyRooms.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaubsaugerState that = (StaubsaugerState) o;
        return pos == that.pos && Arrays.equals(dirtyRooms, that.dirtyRooms) && Objects.equals(prob, that.prob);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(pos, prob);
        result = 31 * result + Arrays.hashCode(dirtyRooms);
        return result;
    }
}
