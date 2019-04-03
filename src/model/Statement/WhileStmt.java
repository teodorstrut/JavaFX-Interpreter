package model.Statement;

import exception.MyException;
import model.ADT.MyIDictionary;
import model.ADT.MyIStack;
import model.Expression.Exp;
import model.PrgState;

import java.io.IOException;

public class WhileStmt implements IStmt {
    private Exp exp;
    private IStmt stmt;
    public WhileStmt(Exp exp, IStmt stmt){
        this.exp = exp;
        this.stmt = stmt;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIStack<IStmt> stk =  state.getExe_stk();
        MyIDictionary<String,Integer> sym_tbl = state.getSym_tbl();
        MyIDictionary<Integer,Integer> heap = state.getHeap();
        if(exp.eval(sym_tbl,heap)!=0) {
            stk.push(this);
            stk.push(stmt);
            return null;
        }
        else
            return null;
    }
    @Override
    public String toString(){
        return "while("+this.exp.toString()+")"+this.stmt.toString();
    }
}
