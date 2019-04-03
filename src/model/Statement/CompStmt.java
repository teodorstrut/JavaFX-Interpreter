package model.Statement;

import model.ADT.MyIStack;
import model.PrgState;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt second;

    public CompStmt(IStmt f, IStmt s){
        this.first=f;
        this.second=s;
    }

    public String toString(){
        return "("+this.first+", "+this.second+")";
    }

    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stk = state.getExe_stk();
        stk.push(second);
        stk.push(first);
        return null;
    }

    public IStmt getFirst() {return this.first;}
    public IStmt getSecond() {return this.second;}
}
