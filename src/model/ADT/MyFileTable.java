package model.ADT;

import java.util.HashMap;

public class MyFileTable<T1,T2> implements MyIFileTable<T1,T2> {
    HashMap<T1,T2> h_map;
    public MyFileTable() {h_map = new HashMap<T1,T2>();}

    @Override
    public void add(T1 v1, T2 v2){h_map.put(v1,v2);}
    @Override
    public void update(T1 v1, T2 v2){h_map.put(v1,v2);}
    @Override
    public void remove(T1 v1){h_map.remove(v1);}

    @Override
    public T2 lookUp(T1 v1){
        if(h_map.get(v1)!=null)
            return h_map.get(v1);
        return null;//put exception for no item
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
}
