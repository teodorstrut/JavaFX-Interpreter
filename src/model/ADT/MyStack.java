package model.ADT;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> stk;
    public MyStack(){
        stk=new Stack<T>();
    }
    @Override
    public void push(T v){
        stk.push(v);
    }
    @Override
    public T pop(){
        return stk.pop();
    }
    @Override
    public String toString(){
        String ret_str="";
        for(T e:stk)
            ret_str = ret_str + e.toString()+"\n";
        return ret_str;
    }
    @Override
    public boolean isEmpty(){
        if(stk.isEmpty())
            return true;
        else
            return false;
    }
    public Stack<T> getStk(){return this.stk;}
}
