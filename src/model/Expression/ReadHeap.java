package model.Expression;

import exception.MyException;
import model.ADT.MyIDictionary;
import model.Statement.ReadFile;

import java.util.HashMap;

public class ReadHeap extends Exp {
    Exp var;
    public ReadHeap(Exp var){this.var=var;}

    public int eval(MyIDictionary<String,Integer> sym_tbl,MyIDictionary<Integer,Integer> heap) throws MyException {
        int ret=0,nr=var.eval(sym_tbl,heap);
        for(HashMap.Entry<Integer,Integer> e:heap.getH_Map().entrySet())
            if(e.getKey()==nr)
            {
                ret=e.getValue();
                return ret;
            }
            throw new MyException("no such element in the heap!");
    }

    public String toString(){
        return "rH("+var.toString()+")";
    }
}
