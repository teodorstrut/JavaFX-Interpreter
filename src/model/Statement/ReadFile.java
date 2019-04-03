package model.Statement;

import exception.MyException;
import model.ADT.MyIDictionary;
import model.ADT.MyIFileTable;
import model.ADT.MyPair;
import model.Expression.Exp;
import model.PrgState;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadFile implements IStmt {
    Exp exp_file_id;
    String var_name;

    public ReadFile(Exp exp_file_id,String var_name){
        this.exp_file_id=exp_file_id;
        this.var_name=var_name;
    }

    @Override
    public PrgState execute(PrgState program) throws MyException, IOException {

        MyIFileTable<Integer, MyPair<String, BufferedReader>> file_tbl=program.getFile_tbl();
        MyIDictionary<Integer,Integer> heap = program.getHeap();
        MyIDictionary<String,Integer> symtbl=program.getSym_tbl();
        int nr=0,val;
        try {
            nr = exp_file_id.eval(symtbl, heap);
        }catch(MyException e){
            throw new MyException("no such file!");
        }
        BufferedReader buff;
        for(HashMap.Entry<Integer, MyPair<String, BufferedReader>> e: file_tbl.getH_Map().entrySet())
            if(e.getKey()==nr) {
                try {
                    buff = e.getValue().getElem2();
                    String str=buff.readLine();
                    if(str.equals(""))
                        val=0;
                    else
                        val = Integer.parseInt(str);
                    if(symtbl.exists(var_name))
                        symtbl.update(var_name,val);
                    else
                        symtbl.add(var_name,val);
                }catch(IOException ex) {
                    throw new IOException("cannot close buffered reader");
                }
            }

        return null;
    }
    @Override
    public String toString(){
        return "ReadFile("+exp_file_id.toString()+", "+var_name.toString()+")";
    }
}
