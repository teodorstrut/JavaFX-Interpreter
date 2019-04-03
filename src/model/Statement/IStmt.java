package model.Statement;

import exception.MyException;
import model.PrgState;

import java.io.IOException;

public interface IStmt {
    String toString();
    PrgState execute(PrgState state) throws MyException, IOException;
}
