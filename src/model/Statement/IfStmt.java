package model.Statement;

import exception.MyException;
import model.ADT.MyIDictionary;
import model.ADT.MyIStack;
import model.Expression.Exp;
import model.PrgState;

public class IfStmt implements IStmt{
    Exp expr;
    IStmt st1,st2;
    public IfStmt() {}
    public IfStmt(Exp expression, IStmt statement1, IStmt statement2){
        this.expr = expression;
        this.st1 = statement1;
        this.st2 = statement2;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stack = state.getExe_stk();
        MyIDictionary<String,Integer> symtbl= state.getSym_tbl();
        MyIDictionary<Integer,Integer> heap = state.getHeap();
        if(expr.eval(symtbl,heap)!=0)
            stack.push(st1);
        else
            stack.push(st2);
        return null;
    }
    @Override
    public String toString(){
        return "If "+expr.toString()+" Then "+st1.toString()+" Else "+st2.toString();
    }
    public Exp getExp() {return this.expr;}
    public IStmt getSt1() {return this.st1;}
    public IStmt getSt2() {return this.st2;}
}
