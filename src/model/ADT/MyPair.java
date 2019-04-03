package model.ADT;

public class MyPair<T1,T2> {
    T1 elem1;
    T2 elem2;
    public MyPair(T1 elem1,T2 elem2){this.elem1=elem1;this.elem2=elem2;}

    public T1 getElem1() {
        return elem1;
    }

    public void setElem2(T2 elem2) {
        this.elem2 = elem2;
    }
    public T2 getElem2(){
        return elem2;
    }
    public String toString(){
        return this.elem1.toString();
    }
}
