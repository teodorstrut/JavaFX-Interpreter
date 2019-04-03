package model.ADT;

import java.util.LinkedList;
import java.util.Queue;

public class MyList<T> implements MyIList<T>{
    Queue<T> q;
    public MyList() {
        q = new LinkedList<T>();
    }
    @Override
    public void add(T v){
        q.add(v);
    }
    @Override
    public T pop(){
        return q.poll();
    }
    @Override
    public String toString(){
        String ret_str ="";
        for(T e:q)
            ret_str = ret_str+e.toString()+"\n";
        return ret_str;
    }
    public Queue<T> getQ(){return this.q;}
}
