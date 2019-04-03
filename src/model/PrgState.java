package model;

import exception.MyException;
import model.ADT.*;
import model.Statement.IStmt;

import java.io.BufferedReader;
import java.io.IOException;

public class PrgState {
    MyIDictionary<String,Integer> sym_tbl;
    MyIStack<IStmt> exe_stk;
    MyIList<Integer> out;
    MyIFileTable<Integer, MyPair<String, BufferedReader>> file_tbl;
    MyIDictionary<Integer, Integer> heap;
    IStmt original;
    int id;

    public PrgState() {}
    public PrgState(int id,MyIDictionary<String,Integer> sym_tbl, MyIStack<IStmt> exe_stk,MyIList<Integer> out, MyIFileTable<Integer,MyPair<String, BufferedReader>> file_tbl,MyIDictionary<Integer,Integer> heap, IStmt program){
        this.id=id;
        this.sym_tbl = sym_tbl;
        this.exe_stk = exe_stk;
        this.out = out;
        this.original = program;
        this.file_tbl= file_tbl;
        this.heap = heap;
        exe_stk.push(program);
    }
    public MyIDictionary<String,Integer> getSym_tbl(){return this.sym_tbl;}
    public void setSym_tbl(MyIDictionary<String, Integer> sym_tbl){this.sym_tbl=sym_tbl;}
    public MyIFileTable<Integer,MyPair<String,BufferedReader>> getFile_tbl(){return this.file_tbl;}
    public void setFile_tbl(MyIFileTable<Integer, MyPair<String,BufferedReader>> file_tbl){this.file_tbl=file_tbl;}
    public MyIList<Integer> getOut(){return this.out;}
    public void setOut(MyIList<Integer> out){this.out=out;}
    public MyIStack<IStmt> getExe_stk(){return this.exe_stk;}
    public void setExe_stk(MyIStack<IStmt> stk){this.exe_stk=stk;}
    public MyIDictionary<Integer,Integer> getHeap(){return this.heap;}
    public IStmt getOriginal(){return this.original;}
    public void setOriginal(IStmt stmt){this.original=stmt;}
    public int getId(){return this.id; }
    public String toString(){
        return  "ID:"+this.id/*+"\n"+
                "ExeStack:\n"+this.exe_stk.toString()+
                "SymTable:\n"+this.sym_tbl.toString()+
                "Out:\n"+this.out.toString()+
                "FileTable:\n"+ this.file_tbl.toString()+
                "Heap:\n"+this.heap.toString()*/;
    }

    public Boolean isNotCompleted(){
        if(this.exe_stk.isEmpty())
            return false;
        else
            return true;
    }

    public PrgState oneStep() throws MyException, IOException {
        MyIStack<IStmt> exe_stk=this.exe_stk;
        if(exe_stk.isEmpty()) throw new MyException("the stack is empty!");
        IStmt current = exe_stk.pop();
        return current.execute(this);
    }
}
