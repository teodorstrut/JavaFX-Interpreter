package model.Statement;

import exception.MyException;
import model.ADT.MyIDictionary;
import model.Expression.Exp;
import model.PrgState;

public class NewAllocHeap implements IStmt {
    String var;
    Exp val;
    static int unique_addr=0;
    public NewAllocHeap(String var,Exp val){
        this.var=var;
        this.val=val;
    }

    @Override
    public PrgState execute(PrgState program) throws MyException {

        MyIDictionary<String,Integer> sym_tbl = program.getSym_tbl();
        MyIDictionary<Integer,Integer> heap = program.getHeap();
        int nr=val.eval(sym_tbl,heap);
        unique_addr++;
        heap.add(unique_addr,nr);
        sym_tbl.add(var,unique_addr);
        return null;
    }
    @Override
    public String toString(){
        return "new("+var.toString()+", "+val.toString()+")";
    }
}
