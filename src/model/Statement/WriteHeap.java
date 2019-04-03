package model.Statement;

import exception.MyException;
import model.ADT.MyIDictionary;
import model.Expression.Exp;
import model.PrgState;

public class WriteHeap implements IStmt {
        String var;
        Exp new_val;
        public WriteHeap(String var, Exp new_val){
            this.var=var;
            this.new_val=new_val;
        }
        @Override
        public PrgState execute(PrgState prg)throws MyException {
            try{
                MyIDictionary<String,Integer> sym_tbl = prg.getSym_tbl();
                MyIDictionary<Integer,Integer> heap = prg.getHeap();
                int heap_poz=sym_tbl.lookUp(var);
                heap.add(heap_poz,new_val.eval(sym_tbl,heap));
            }catch (MyException e){
                throw new MyException("no such element in symbol table or heap!");
            }
            return null;
        }
        public String toString(){
            return "wH("+this.var.toString()+", "+this.new_val.toString()+")";
        }
    }
