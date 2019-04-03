package model.ADT;

import exception.MyException;

import java.util.HashMap;

public class MyDictionary<T1,T2> implements MyIDictionary<T1,T2> {
    HashMap<T1,T2> h_map;
    public MyDictionary() {h_map = new HashMap<T1,T2>();}

    @Override
    public void add(T1 v1, T2 v2){h_map.put(v1,v2);}
    @Override
    public void update(T1 v1, T2 v2){h_map.put(v1,v2);}

    @Override
    public T2 lookUp(T1 v1) throws MyException{
        if(h_map.get(v1) != null){
            return h_map.get(v1);
        }
        throw new MyException("no such element!");
    }
    @Override
    public boolean exists(T1 v1){
        if(h_map.get(v1)!=null)
            return true;
        return false;
    }
    @Override
    public String toString(){
        String ret_str="";
        for(HashMap.Entry<T1,T2> e:h_map.entrySet())
            ret_str = ret_str+"key: "+e.getKey().toString()+", Value: "+e.getValue().toString()+"\n";
        return ret_str;
    }
    public HashMap<T1,T2> getH_Map() {return h_map;}
    public void setH_Map(HashMap<T1,T2> newval){this.h_map=newval;}
}
