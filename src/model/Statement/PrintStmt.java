package model.Statement;

import exception.MyException;
import model.ADT.MyIDictionary;
import model.ADT.MyIList;
import model.Expression.Exp;
import model.PrgState;

public class PrintStmt implements IStmt{
    Exp expression;
    public PrintStmt() {}
    public PrintStmt(Exp expression) {this.expression=expression;}

    @Override
    public PrgState execute(PrgState state) throws MyException {
    MyIList<Integer> q = state.getOut();
    MyIDictionary<String,Integer> SymTable = state.getSym_tbl();
    MyIDictionary<Integer,Integer> heap = state.getHeap();
    q.add(this.expression.eval(SymTable,heap));
    return null;
    }

    @Override
    public String toString(){return "print("+this.expression.toString()+")";}
    public Exp getExp(){return this.expression;}
}
