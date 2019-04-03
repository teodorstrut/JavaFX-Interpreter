package view;//package view;
//
//import controller.MyController;
//import model.ADT.*;
//import model.Expression.ArithExp;
//import model.Expression.ConstExp;
//import model.Expression.VarExp;
//import model.PrgState;
//import model.Statement.*;
//import repository.MyRepository;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.Scanner;
//public class View {
//    static MyRepository myRepository = new MyRepository();
//    static MyController myController = new MyController(myRepository);
//
//    public static void main(String[] args) {
//        IStmt originalProgram;
//        MyIStack<IStmt> exeStack = new MyStack<IStmt>();
//        MyIDictionary<String, Integer> symTable = new MyDictionary<String, Integer>();
//        MyIList<Integer> out = new MyList<Integer>();
//        MyIFileTable<Integer,MyPair<String, BufferedReader>> ft = new MyFileTable<>();
//
//        try{
//        BufferedReader buffer= new BufferedReader(new FileReader("log.txt"));
//        MyPair<String,BufferedReader> a = new MyPair<>("ana",buffer);
//        System.out.println(a.toString());
//        }catch(FileNotFoundException e){System.out.println("tzapa");}
//
//
//        System.out.println("Programs:\n");
//        System.out.println("1: v=2;Print(v)\n");
//        System.out.println("2: a=2+3*5;b=a+1;Print(b)\n");
//        System.out.println("3: a=2-2;(If a Then v=2 Else v=3);Print(v)\n");
//        System.out.println("4: openRFile(file,afile.in);closeRFile(file)\n");
//        System.out.println("Please input the path to the log file:");
//        Scanner log=new Scanner(System.in);
//        String log_str=log.nextLine();
//        myRepository.setLogPath(log_str);
//        System.out.println("Please choose a program: ");
//        Scanner in=new Scanner(System.in);
//        int cmd=Integer.parseInt(in.nextLine());
//        switch(cmd){
//            case 1:originalProgram= new CompStmt(new AssignStmt("v",new ConstExp(2)), new PrintStmt(new
//                    VarExp("v")));
//            break;
//            case 2: originalProgram=  new CompStmt(new AssignStmt("a", new ArithExp('+',new ConstExp(2),new
//                    ArithExp('*',new ConstExp(3), new ConstExp(5)))),
//                    new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new
//                            ConstExp(1))), new PrintStmt(new VarExp("b"))));
//            break;
//            case 3: originalProgram= new CompStmt(new AssignStmt("a", new ArithExp('-',new ConstExp(2), new
//                    ConstExp(2))),
//                    new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(2)), new
//                            AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
//            break;
//            case 4: originalProgram=new CompStmt(new CompStmt(new OpenRFile("file","afile.in"),new ReadFile(new VarExp("file"),"ana")), new CloseRFile(new VarExp("file")));
//            break;
//            default:
//                    originalProgram=new PrintStmt(new ConstExp(-1));
//
//        }
//        PrgState myPrgState = new PrgState(symTable,exeStack, out, ft, originalProgram);
//        myController.addProgram(myPrgState);
//        myController.allStep();
//    }
//}
//
