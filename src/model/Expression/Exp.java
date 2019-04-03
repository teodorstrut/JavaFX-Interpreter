package model.Expression;

import exception.MyException;
import model.ADT.MyIDictionary;

public abstract class Exp {
    public abstract int eval(MyIDictionary<String, Integer> SymTable,MyIDictionary<Integer,Integer> Heap) throws MyException;
    public abstract String toString();
}
