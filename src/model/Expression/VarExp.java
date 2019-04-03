package model.Expression;

import exception.MyException;
import model.ADT.MyIDictionary;

public class VarExp extends Exp{
    String var;
    public VarExp() {}
    public VarExp(String variable) {this.var=variable;}
    public int eval(MyIDictionary<String,Integer> SymTable,MyIDictionary<Integer,Integer>Heap) throws MyException {return SymTable.lookUp(var);}
    public String toString() {return var;}
    public String getVar() {return this.var;}
}
