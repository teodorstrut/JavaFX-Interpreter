//package view;
//
//import controller.MyController;
//import model.ADT.*;
//import model.Expression.*;
//import model.PrgState;
//import model.Statement.*;
//import repository.MyIRepository;
//import repository.MyRepository;
//
//import java.io.BufferedReader;
//
//public class Interpreter {
//    public static void main(String[] args) {
//        IStmt ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new
//                VarExp("v")));
//        PrgState prg1 = new PrgState(1,new MyDictionary<String, Integer>(), new MyStack<IStmt>(), new MyList<Integer>(), new MyFileTable<Integer, MyPair<String, BufferedReader>>(), new MyDictionary<Integer, Integer>(), ex1);
//        MyIRepository repo1 = new MyRepository(prg1, "src/logs/log1.txt");
//        MyController ctr1 = new MyController(repo1);
//
//        IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithExp('+', new ConstExp(2), new
//                ArithExp('*', new ConstExp(3), new ConstExp(5)))),
//                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new
//                        ConstExp(1))), new PrintStmt(new VarExp("b"))));
//        PrgState prg2 = new PrgState(2,new MyDictionary<String, Integer>(), new MyStack<IStmt>(), new MyList<Integer>(), new MyFileTable<Integer, MyPair<String, BufferedReader>>(), new MyDictionary<Integer, Integer>() , ex2);
//        MyIRepository repo2 = new MyRepository(prg2, "src/logs/log2.txt");
//        MyController ctr2 = new MyController(repo2);
//
//        IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp('-', new ConstExp(2), new
//                ConstExp(2))),
//                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new
//                        AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
//        PrgState prg3 = new PrgState(3,new MyDictionary<String, Integer>(), new MyStack<IStmt>(), new MyList<Integer>(), new MyFileTable<Integer, MyPair<String, BufferedReader>>(), new MyDictionary<Integer, Integer>() , ex3);
//        MyIRepository repo3 = new MyRepository(prg3, "src/logs/log3.txt");
//        MyController ctr3 = new MyController(repo3);
//        IStmt ex4 =new CompStmt(new CompStmt(new OpenRFile("file","afile.in"),new ReadFile(new VarExp("file"),"ana")), new CloseRFile(new VarExp("file")));
//        PrgState prg4 = new PrgState(4,new MyDictionary<String, Integer>(), new MyStack<IStmt>(), new MyList<Integer>(), new MyFileTable<Integer, MyPair<String, BufferedReader>>(), new MyDictionary<Integer, Integer>() , ex4);
//        MyIRepository repo4 = new MyRepository(prg4,"src/logs/log4.txt");
//        MyController ctr4 = new MyController(repo4);
//        IStmt ex5 = new CompStmt(
//                new CompStmt(new OpenRFile("fname","src/testfiles/test.in"),
//                        new ReadFile(new VarExp("fname"),"val")),
//                new CompStmt(
//                        new PrintStmt(new VarExp("val")),
//                        new CompStmt(new IfStmt(new VarExp("val"),
//                                new CompStmt(new ReadFile(new VarExp("fname"),"val"),new PrintStmt(new VarExp("val"))),
//                                new PrintStmt(new ConstExp(0))),
//                                new CloseRFile(new VarExp("fname")))));
//        PrgState prg5 = new PrgState(5,new MyDictionary<String, Integer>(), new MyStack<IStmt>(), new MyList<Integer>(), new MyFileTable<Integer, MyPair<String, BufferedReader>>(), new MyDictionary<Integer, Integer>() , ex5);
//        MyIRepository repo5 = new MyRepository(prg5, "src/logs/log5.txt");
//        MyController ctr5 = new MyController(repo5);
//        IStmt ex6 =new CompStmt(new CompStmt(new NewAllocHeap("a",new ConstExp(5)),
//                                             new PrintStmt(new ReadHeap(new VarExp("a")))),
//                                new WriteHeap("a",new ConstExp(7)));
//        PrgState prg6 = new PrgState(6,new MyDictionary<String, Integer>(), new MyStack<IStmt>(), new MyList<Integer>(), new MyFileTable<Integer, MyPair<String, BufferedReader>>(), new MyDictionary<Integer, Integer>() , ex6);
//        MyIRepository repo6 = new MyRepository(prg6,"src/logs/log6.txt");
//        MyController ctr6 = new MyController(repo6);
//        IStmt ex7 = new CompStmt(new AssignStmt("v",new ConstExp(10)),
//                new CompStmt(new NewAllocHeap("v",new ConstExp(20)),
//                        new CompStmt(new NewAllocHeap("a",new ConstExp(22)),
//                                new CompStmt(new WriteHeap("a",new ConstExp(30)),
//                                        new CompStmt(new PrintStmt(new VarExp("a")),
//                                                new CompStmt(new PrintStmt(new ReadHeap(new VarExp("a"))),
//                                                        new CompStmt(new AssignStmt("a",new ConstExp(0)),
//                                                                     new PrintStmt(new ConstExp(6)))))))));
//        PrgState prg7 = new PrgState(7,new MyDictionary<String, Integer>(), new MyStack<IStmt>(), new MyList<Integer>(), new MyFileTable<Integer, MyPair<String, BufferedReader>>(), new MyDictionary<Integer, Integer>() , ex7);
//        MyIRepository repo7 = new MyRepository(prg7,"src/logs/log7.txt");
//        MyController ctr7 = new MyController(repo7);
//        IStmt ex8 = new CompStmt(new AssignStmt("v",new ConstExp(6)),
//                new CompStmt(new WhileStmt(new ArithExp('-',new VarExp("v"),new ConstExp(4)),new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ConstExp(1))))),
//                        new PrintStmt(new VarExp("v"))));
//        PrgState prg8 = new PrgState(8,new MyDictionary<String, Integer>(), new MyStack<IStmt>(), new MyList<Integer>(), new MyFileTable<Integer, MyPair<String, BufferedReader>>(), new MyDictionary<Integer, Integer>() , ex8);
//        MyIRepository repo8 = new MyRepository(prg8,"src/logs/log8.txt");
//        MyController ctr8 = new MyController(repo8);
//        IStmt ex9 = new CompStmt(new CompStmt(new AssignStmt("v",new ConstExp(10)),new NewAllocHeap("a",new ConstExp(22))),new CompStmt(new ForkStmt(new CompStmt(new WriteHeap("a",new ConstExp(30)),new CompStmt(new AssignStmt("v",new ConstExp(32)),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new ReadHeap(new VarExp("a"))))))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new ReadHeap(new VarExp("a"))))));
//        PrgState prg9 = new PrgState(9,new MyDictionary<String, Integer>(), new MyStack<IStmt>(), new MyList<Integer>(), new MyFileTable<Integer, MyPair<String, BufferedReader>>(), new MyDictionary<Integer, Integer>() , ex9);
//        MyIRepository repo9 = new MyRepository(prg9,"src/logs/log8.txt");
//        MyController ctr9 = new MyController(repo9);
//        TextMenu menu = new TextMenu();
//        menu.addCommand(new ExitCommand("0", "exit"));
//        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
//        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
//        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
//        menu.addCommand(new RunExample("4",ex4.toString(),ctr4));
//        menu.addCommand(new RunExample("5",ex5.toString(),ctr5));
//        menu.addCommand(new RunExample("6",ex6.toString(),ctr6));
//        menu.addCommand(new RunExample("7",ex7.toString(),ctr7));
//        menu.addCommand(new RunExample("8",ex8.toString(),ctr8));
//        menu.addCommand(new RunExample("9",ex9.toString(),ctr9));
//        menu.show();
//    }
//}
