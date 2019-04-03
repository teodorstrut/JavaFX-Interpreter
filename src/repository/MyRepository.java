package repository;

import model.ADT.*;
import model.PrgState;
import model.Statement.IStmt;

import java.io.*;
import java.util.*;

public class MyRepository implements MyIRepository {
    ArrayList<PrgState> programs;
    String filepath;
    PrintWriter log_file;
    PrgState CurrentState;
    public MyRepository(PrgState program,String filepath){
        programs=new ArrayList<PrgState>();
        programs.add(program);
        this.filepath=filepath;
    }
    @Override
    public void addProgram(PrgState program){this.programs.add(program);}
    @Override
    //public PrgState getCurrent(){return this.programs.get(0);}
    public void setCurrentState(PrgState prg){this.CurrentState=prg;}
    public void setLogPath(String path){this.filepath=path;}

    public void logPrgStateExec(PrgState program) throws IOException {
        log_file=new PrintWriter(new BufferedWriter(new FileWriter(filepath,true)));
        MyIStack<IStmt> exestack=program.getExe_stk();
        MyIDictionary<String,Integer> symtable=program.getSym_tbl();
        MyIList<Integer> out=program.getOut();
        MyIFileTable<Integer,MyPair<String, BufferedReader>> ft = program.getFile_tbl();
        MyIDictionary<Integer,Integer> heap = program.getHeap();
        //id gets written
        log_file.println("ID:"+program.getId());
        //the exe stack gets written
        log_file.println("ExeStack:");
        ArrayList<IStmt> l = new ArrayList<IStmt>(exestack.getStk());
        ListIterator<IStmt> l_iter = l.listIterator(l.size());
        while(l_iter.hasPrevious())
        {
            log_file.println("--> " + l_iter.previous().toString());
        }

        //the sym_table gets written
        log_file.println("SymTable:");
        for(HashMap.Entry<String,Integer> e: symtable.getH_Map().entrySet())
            log_file.println("--> Key: "+e.getKey().toString()+" Value: "+e.getValue().toString());

        //the out queue gets written
        log_file.println("Out:");
        for(Integer e:out.getQ())
            log_file.println("--> "+e.toString());

        log_file.println("FileTable:");
        for(HashMap.Entry<Integer,MyPair<String,BufferedReader>> e: ft.getH_Map().entrySet())
            log_file.println("--> Key: "+e.getKey().toString()+" Value: "+e.getValue().toString());

        //the heap gets written
        log_file.println("Heap:");
        for(HashMap.Entry<Integer,Integer> e: heap.getH_Map().entrySet())
            log_file.println("--> Key: "+e.getKey().toString()+" Value: "+e.getValue().toString());

        log_file.println("------------------------------------------");
        log_file.close();
    }

    @Override
    public ArrayList<PrgState> getPrgList(){
        return this.programs;
    }

    @Override
    public void setPrgList(ArrayList<PrgState> programs){
        this.programs=programs;
    }
}