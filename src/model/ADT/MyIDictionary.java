package model.ADT;

import exception.MyException;

import java.util.HashMap;

public interface MyIDictionary<T1,T2> {
    void add(T1 v1, T2 v2);
    void update(T1 v1, T2 v2);
    T2 lookUp(T1 v1)throws MyException;
    boolean exists(T1 v1);
    String toString();
    HashMap<T1,T2> getH_Map();
    void setH_Map(HashMap<T1, T2> newval);
}
