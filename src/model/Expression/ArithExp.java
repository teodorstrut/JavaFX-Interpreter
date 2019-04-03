package model.Expression;

import exception.MyException;
import model.ADT.MyIDictionary;

public class ArithExp extends Exp {
Exp e1;
Exp e2;
char operator;
public ArithExp() {}
public ArithExp(char operator, Exp expression1, Exp expression2) {
    this.e1=expression1;
    this.e2=expression2;
    this.operator=operator;
}

public int eval(MyIDictionary<String,Integer> SymTable,MyIDictionary<Integer,Integer> Heap)throws MyException {
    if(this.operator=='+')
        return this.e1.eval(SymTable,Heap)+this.e2.eval(SymTable,Heap);
    if(this.operator=='-')
        return this.e1.eval(SymTable,Heap)-this.e2.eval(SymTable,Heap);
    if(this.operator=='*')
        return this.e1.eval(SymTable,Heap)*this.e2.eval(SymTable,Heap);
    if(this.operator=='/')
        return this.e1.eval(SymTable,Heap)/this.e2.eval(SymTable,Heap);
    else if(this.e2.eval(SymTable,Heap)==0)
        return -1;//!!!!come back to add exception!!!!
    return -1;//!!!!add exception for which the operator is not one of the above!!!!

}
public String toString() {return this.e1.toString()+" "+this.operator+" "+this.e2.toString();}
public char getOp() {return this.operator;}
public Exp getExp1() {return this.e1;}
public Exp getExp2() {return this.e2;}

}

