package model.Expression;

import exception.MyException;
import model.ADT.MyIDictionary;

public class BoolExp extends Exp {
    private String sign;
    private Exp ex1,ex2;
    public BoolExp(Exp ex1,String sign,Exp ex2){
        this.ex1=ex1;
        this.ex2=ex2;
        this.sign=sign;
    }

    @Override
    public int eval(MyIDictionary<String, Integer> SymTable, MyIDictionary<Integer, Integer> Heap) throws MyException {
        if(sign.equals("<"))
        {
            if(ex1.eval(SymTable,Heap)<ex2.eval(SymTable,Heap))
                return 1;
            else
                return 0;
        }else if(sign.equals("<="))
        {
            if(ex1.eval(SymTable,Heap)<=ex2.eval(SymTable,Heap))
                return 1;
            else
                return 0;
        }else if(sign.equals(">"))
        {
            if(ex1.eval(SymTable,Heap)>ex2.eval(SymTable,Heap))
                return 1;
            else
                return 0;
        }else if(sign.equals(">="))
        {
            if(ex1.eval(SymTable,Heap)>=ex2.eval(SymTable,Heap))
                return 1;
            else
                return 0;
        }else if(sign.equals("=="))
        {
            if(ex1.eval(SymTable,Heap)==ex2.eval(SymTable,Heap))
                return 1;
            else
                return 0;
        }else if(sign.equals("!="))
        {
            if(ex1.eval(SymTable,Heap)!=ex2.eval(SymTable,Heap))
                return 1;
            else
                return 0;
        }else throw new MyException("invalid boolean operator!");
    }

    @Override
    public String toString() {
        return "("+ex1.toString()+" "+this.sign+" "+this.ex2.toString()+")";
    }
}
