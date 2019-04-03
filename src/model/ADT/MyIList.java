package model.ADT;

import java.util.Queue;

public interface MyIList<T> {
    void add(T v);
    T pop();
    String toString();
    Queue<T> getQ();
}
