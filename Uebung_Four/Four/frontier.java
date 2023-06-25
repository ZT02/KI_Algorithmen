package Uebung_Four.Four;

import java.util.*;

public class frontier {
    LinkedList<node> q;
    public frontier() {
        q = new LinkedList<node>();
        }
    public boolean IsEmpty() {
        return q.isEmpty();
    }

    public node Pop() {
        return q.poll();
    }

    public boolean Insert(node e) {
        return q.add(e);
    }


}



