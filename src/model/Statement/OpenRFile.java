package model.Statement;

import model.ADT.MyIDictionary;
import model.ADT.MyIFileTable;
import model.ADT.MyPair;
import model.PrgState;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class OpenRFile implements IStmt {
    String filename;
    String var_file_id;
    static int count=0;
    public OpenRFile(String var_file_id,String filename){
        this.var_file_id=var_file_id;
        this.filename=filename;
    }

    @Override
    public PrgState execute(PrgState program){
        MyIFileTable<Integer, MyPair<String, BufferedReader>> fileTable = program.getFile_tbl();
        MyIDictionary<String,Integer> symTable = program.getSym_tbl();

        for(HashMap.Entry<Integer, MyPair<String, BufferedReader>> e : fileTable.getH_Map().entrySet()) {
            if (e.getValue().getElem1().equals(filename))
                //throw exception: entry already exists
                return program;
        }
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(filename));
            MyPair<String, BufferedReader> tuple = new MyPair<String, BufferedReader>(filename, buffer);
            count++;
            fileTable.add(count, tuple);
            if (symTable.exists(var_file_id))
                symTable.update(var_file_id, count);
            else
                symTable.add(var_file_id, count);
        }catch(FileNotFoundException e){
            //rethrow exception as my exception
        }
        return null;
    }
    @Override
    public String toString() {
        return "openFile(" + var_file_id + ", " + filename + ")";
    }
}
