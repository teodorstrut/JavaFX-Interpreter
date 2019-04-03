//package test;
//
//import controller.MyController;
//import exception.MyException;
//import model.ADT.*;
//import model.Expression.ArithExp;
//import model.Expression.ConstExp;
//import model.Expression.Exp;
//import model.Expression.VarExp;
//import model.PrgState;
//import model.Statement.*;
//import org.junit.jupiter.api.Test;
//import repository.MyIRepository;
//import repository.MyRepository;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class AllTest {
//    @Test
//    void ConstExpTest() throws MyException {
//        MyIDictionary<String, Integer> tbl = new MyDictionary<String, Integer>();
//        MyIDictionary<Integer, Integer> hp = new MyDictionary<Integer, Integer>();
//        Exp first=new ConstExp(5);
//        assertEquals(5,first.eval(tbl,hp));
//        Exp second=new ConstExp(19);
//        assertEquals(19,second.eval(tbl,hp));
//        Exp third=new ConstExp(1000);
//        assertEquals(1000,third.eval(tbl,hp));
//    }
//    @Test
//    void VarExpTest() throws MyException {
//        MyDictionary<String, Integer> tbl = new MyDictionary<String, Integer>();
//        MyIDictionary<Integer, Integer> hp = new MyDictionary<Integer, Integer>();
//        tbl.getH_Map().put("i",5);
//        tbl.getH_Map().put("j",19);
//        tbl.getH_Map().put("k",1000);
//        Exp first=new VarExp("i");
//        assertEquals(5,first.eval(tbl,hp));
//        Exp second=new VarExp("j");
//        assertEquals(19,second.eval(tbl,hp));
//        Exp third=new VarExp("k");
//        assertEquals(1000,third.eval(tbl,hp));
//    }
//    @Test
//    void ArithExpTest() throws MyException {
//        MyIDictionary<String, Integer> tbl = new MyDictionary<String, Integer>();
//        MyIDictionary<Integer, Integer> hp = new MyDictionary<Integer, Integer>();
//        Exp expression1=new ArithExp('+',new ConstExp(5), new ConstExp(6));
//        Exp expression2=new ArithExp('-',new ConstExp(5), new ConstExp(6));
//        Exp expression3=new ArithExp('*',new ConstExp(5), new ConstExp(6));
//        Exp expression4=new ArithExp('/',new ConstExp(5), new ConstExp(6));
//
//        assertEquals(11,expression1.eval(tbl,hp));
//        assertEquals(-1,expression2.eval(tbl,hp));
//        assertEquals(30,expression3.eval(tbl,hp));
//        assertEquals(0,expression4.eval(tbl,hp));
//
//    }
//    @Test
//    void FileStmtTest() throws IOException, MyException {
//        IStmt ex1 = new CompStmt(
//                        new CompStmt(new OpenRFile("fname","src/testfiles/test.in"),
//                            new ReadFile(new VarExp("fname"),"val")),
//                            new CompStmt(
//                                    new PrintStmt(new VarExp("val")),
//                                    new CompStmt(new IfStmt(new VarExp("val"),
//                                                    new CompStmt(new ReadFile(new VarExp("fname"),"val"),new PrintStmt(new VarExp("val"))),
//                                                    new PrintStmt(new ConstExp(0))),
//                                                new CloseRFile(new VarExp("fname")))));
//        PrgState program = new PrgState(50,new MyDictionary<String, Integer>(), new MyStack<IStmt>(), new MyList<Integer>(), new MyFileTable<Integer, MyPair<String, BufferedReader>>(), new MyDictionary<Integer, Integer>() , ex1);
//        program = ex1.execute(program);
//        MyIRepository repo = new MyRepository(program,"src/testfiles/testlog.txt");
//        MyController ctr = new MyController(repo);
//        ctr.allStep();
//    }
//    @Test
//    void WhileStmtTest() throws IOException, MyException{
//        IStmt ex1 = new CompStmt(new AssignStmt("v",new ConstExp(6)),
//                                 new CompStmt(new WhileStmt(new ArithExp('-',new VarExp("v"),new ConstExp(4)),new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ConstExp(1))))),
//                                              new PrintStmt(new VarExp("v"))));
//        PrgState program = new PrgState(99,new MyDictionary<String, Integer>(), new MyStack<IStmt>(), new MyList<Integer>(), new MyFileTable<Integer, MyPair<String, BufferedReader>>(), new MyDictionary<Integer, Integer>() , ex1);
//        MyIRepository repo = new MyRepository(program,"src/testfiles/testlog.txt");
//        MyController ctr = new MyController(repo);
//        ctr.allStep();
//    }
//}
