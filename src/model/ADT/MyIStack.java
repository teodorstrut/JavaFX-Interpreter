package model.ADT;

import java.util.Stack;

public interface MyIStack<T> {
    void push(T v);
    T pop();
    boolean isEmpty();
    String toString();
    Stack<T> getStk();
}
