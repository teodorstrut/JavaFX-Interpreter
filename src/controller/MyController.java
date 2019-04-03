package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import exception.MyException;
import model.ADT.*;
import model.Expression.ArithExp;
import model.Expression.ConstExp;
import model.Expression.ReadHeap;
import model.Expression.VarExp;
import model.PrgState;
import model.Statement.*;
import repository.MyIRepository;
import repository.MyRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MyController {

    @FXML
    private VBox main;

    @FXML
    private HBox H1Row;

    @FXML
    private TableView<?> THeap;

    @FXML
    private TableColumn<?, ?> adress;

    @FXML
    private TableColumn<?, ?> heapvalue;

    @FXML
    private TableView<?> TFile;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> filename;

    @FXML
    private TableView<?> TSymTbl;

    @FXML
    private TableColumn<?, ?> variablename;

    @FXML
    private TableColumn<?, ?> varvalue;

    @FXML
    private HBox H2Row;

    @FXML
    private ListView<?> LOut;

    @FXML
    private ListView<?> LPrgState;

    @FXML
    private ListView<?> LExeStk;

    @FXML
    private Button BStep;

    @FXML
    private TextField TXField;

    MyIRepository repo;
    ExecutorService executor;
    public MyController() {}
    public MyController(MyIRepository repo) {
        this.repo=repo;
    }
    public void addProgram(PrgState program){
        this.repo.addProgram(program);
    }

//    public void allStep(){
//        PrgState prg=repo.getCurrent();
//        repo.setCurrentState(prg);
//        try{
//            while(true){
//                System.out.println(prg.toString());
//                prg.oneStep();
//
//                prg.getHeap().setH_Map(conservativeGarbageCollector(
//                        prg.getSym_tbl().getH_Map().values(),
//                        prg.getHeap().getH_Map()));
//
//                repo.logPrgStateExec(prg);
//                if(prg.getExe_stk().isEmpty()){
//                System.out.println(prg.toString());
//                break;
//                }
//            }
//        }
//        catch(Exception e) {
//            System.out.println(e.getMessage());
//            System.exit(0);
//        }finally {
//            MyIFileTable<Integer, MyPair<String, BufferedReader>> fileTable=prg.getFile_tbl();
//
//            fileTable.getH_Map().values().stream().flatMap(pair ->
//            {
//                try {
//                    pair.getElem2().close();
//                } catch (IOException e) {
//                    System.out.println("error closing files!");
//                }
//                return null;
//            });
//            for(Integer k:fileTable.getH_Map().keySet())
//            {
//                fileTable.getH_Map().remove(k);
//            }
//        }
//    }

    public List<PrgState> removeCompletedPrograms(List<PrgState> lst){
        return lst.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<PrgState> lst) throws InterruptedException {
        //log state of the program
        lst.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        List<Callable<PrgState>> callList = lst.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = executor.invokeAll(callList). stream()
                . map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.exit(0);
                    }
                    return null;
                }).filter(p -> p!=null).collect(Collectors.toList());

        lst.addAll(newPrgList);

        lst.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        repo.setPrgList((ArrayList<PrgState>)lst);
    }

    public void allStep() {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList=removeCompletedPrograms(repo.getPrgList());
//        while(prgList.size() > 0){
        if(prgList.size()>0) {
            repo.getPrgList().get(0).getHeap().setH_Map(conservativeGarbageCollector(
                    repo.getPrgList().get(0).getSym_tbl().getH_Map().values(),
                    repo.getPrgList().get(0).getHeap().getH_Map()));
            try {
                oneStepForAllPrg(prgList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //remove the completed programs
            System.out.println(repo.getPrgList().toString());
            prgList = removeCompletedPrograms(repo.getPrgList());
//        }
        }
        else {
            executor.shutdownNow();
            List<PrgState> tmpList = repo.getPrgList();
            MyIFileTable<Integer, MyPair<String, BufferedReader>> fileTable = tmpList.get(0).getFile_tbl();

            fileTable.getH_Map().values().stream().flatMap(pair ->
            {
                try {
                    pair.getElem2().close();
                } catch (IOException e) {
                    System.out.println("error closing files!");
                }
                return null;
            });
            for (Integer k : fileTable.getH_Map().keySet()) {
                fileTable.getH_Map().remove(k);
            }
            //update the repository state
            System.out.println(repo.getPrgList().toString());
            repo.setPrgList((ArrayList<PrgState>) prgList);
        }
    }

    private HashMap<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, HashMap<Integer,Integer> heap){
            return (HashMap<Integer,Integer>)heap.entrySet().stream()
                    .filter(e->symTableValues.contains(e.getKey()))
                            .collect(Collectors.toMap(HashMap.Entry::getKey,HashMap.Entry::getValue));
    }


}
