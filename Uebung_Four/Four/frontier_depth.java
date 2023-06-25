package Uebung_Four.Four;

import java.util.LinkedList;

public class frontier_depth {
    LinkedList<node> q;
    public frontier_depth() {
        q = new LinkedList<node>();
        }
    public boolean IsEmpty() {
        return q.isEmpty();
    }

    public node Pop() {
        return q.pop();
    }

    public boolean Insert(node e) {
        return q.add(e);
    }


}



