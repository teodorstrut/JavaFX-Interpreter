package model.Statement;

import exception.MyException;
import model.ADT.MyIDictionary;
import model.Expression.Exp;
import model.PrgState;


public class AssignStmt implements IStmt{
String var;
Exp exp;
public AssignStmt() {}
public AssignStmt(String var_name, Exp expression) {
    this.var = var_name;
    this.exp = expression;
}

@Override
public PrgState execute(PrgState state) throws MyException {
    MyIDictionary<String, Integer> SymTable = state.getSym_tbl();
    MyIDictionary<Integer,Integer> heap = state.getHeap();
    int val = this.exp.eval(SymTable,heap);
    if(SymTable.exists(var))
        SymTable.update(var,val);
    else
        SymTable.add(var,val);
    return null;
}
@Override
    public String toString(){return this.var+"= "+this.exp.toString();}
    public String getVar() {return this.var;}
    public Exp getExp() {return this.exp;}
}
