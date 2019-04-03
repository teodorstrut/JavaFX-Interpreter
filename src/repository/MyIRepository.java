package repository;

import model.ADT.MyIList;
import model.ADT.MyList;
import model.PrgState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface MyIRepository {
    void addProgram(PrgState prg);
    //PrgState getCurrent();
    void logPrgStateExec(PrgState program) throws IOException;
    void setCurrentState(PrgState state);
    //void setLogPath(String path);
    ArrayList<PrgState> getPrgList();
    void setPrgList(ArrayList<PrgState> prg);
}
