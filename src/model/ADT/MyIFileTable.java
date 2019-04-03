package model.ADT;

import java.util.HashMap;

public interface MyIFileTable<T1,T2> {
    void add(T1 v1, T2 v2);
    void update(T1 v1, T2 v2);
    void remove(T1 v1);
    T2 lookUp(T1 v1);
    boolean exists(T1 v1);
    String toString();
    HashMap<T1,T2> getH_Map();
}
