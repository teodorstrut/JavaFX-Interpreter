package model.Expression;

import model.ADT.MyIDictionary;

public class ConstExp extends Exp {
    int nr;
    public ConstExp() {}
    public ConstExp(int number) {this.nr = number;}
    public int eval(MyIDictionary<String,Integer> SymTable,MyIDictionary<Integer,Integer>Heap) {return this.nr;}
    public String toString() {return Integer.toString(nr);}
    public int getNr() {return this.nr;}
}
