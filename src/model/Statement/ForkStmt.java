package model.Statement;

import model.ADT.*;
import model.PrgState;

import java.io.BufferedReader;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class ForkStmt implements IStmt {
    private IStmt stmt;
    public ForkStmt(IStmt stmt){
        this.stmt=stmt;
    }

    public PrgState execute(PrgState program){
        MyIFileTable<Integer, MyPair<String, BufferedReader>> ftbl = program.getFile_tbl();
        MyIList<Integer> out = program.getOut();
        MyIDictionary<Integer,Integer> heap = program.getHeap();
        int newid = program.getId()*10;
        MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String,Integer> sym_tbl = new MyDictionary<>();
        for(HashMap.Entry<String,Integer> e:program.getSym_tbl().getH_Map().entrySet())
            sym_tbl.add(e.getKey(),e.getValue());
        PrgState newprogram = new PrgState(newid,sym_tbl,stk,out,ftbl,heap,this.stmt);
        return newprogram;
    }

    public String toString(){
        return "Fork("+this.stmt.toString()+")";
    }

}
