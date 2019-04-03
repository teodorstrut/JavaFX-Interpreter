package model.Statement;

import exception.MyException;
import model.ADT.MyIDictionary;
import model.ADT.MyIFileTable;
import model.ADT.MyPair;
import model.Expression.Exp;
import model.PrgState;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.util.HashMap;

public class CloseRFile implements IStmt{
    Exp exp_file_id;

    public CloseRFile(Exp exp_file_id){
        this.exp_file_id=exp_file_id;
    }

    @Override
    public PrgState execute(PrgState program) throws MyException {
        MyIFileTable<Integer, MyPair<String, BufferedReader>> file_tbl=program.getFile_tbl();
        MyIDictionary<Integer,Integer> heap = program.getHeap();
        MyIDictionary<String,Integer> symtbl=program.getSym_tbl();

        int nr=exp_file_id.eval(symtbl,heap);
        BufferedReader buff;
        for(HashMap.Entry<Integer, MyPair<String, BufferedReader>> e: file_tbl.getH_Map().entrySet())
            if(e.getKey()==nr) {
                try {
                    buff = e.getValue().getElem2();
                    buff.close();
                }catch(IOException ex){
                    //re-throw exception: cannot close buffered reader
                }
                file_tbl.remove(nr);
            }
            return null;

    }

    @Override
    public String toString(){
        return "CloseRFile("+exp_file_id.toString()+")";
    }
}
