package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ADT.*;
import model.PrgState;
import model.Statement.IStmt;
import repository.MyIRepository;
import repository.MyRepository;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class GUIController1 {

    MyController controller;

    @FXML
    private VBox main;

    @FXML
    private HBox H1Row;

    @FXML
    private TableView<HashMap> THeap;

    @FXML
    private TableColumn<HashMap, String> adress;

    @FXML
    private TableColumn<HashMap, String> heapvalue;

    @FXML
    private TableView<HashMap> TFile;

    @FXML
    private TableColumn<HashMap, Integer> id;

    @FXML
    private TableColumn<HashMap, String> filename;

    @FXML
    private TableView<HashMap> TSymTbl;

    @FXML
    private TableColumn<HashMap, String> variablename;

    @FXML
    private TableColumn<HashMap, String> varvalue;

    @FXML
    private HBox H2Row;

    @FXML
    private ListView<Integer> LOut;

    @FXML
    private ListView<PrgState> LPrgState;

    @FXML
    private ListView<IStmt> LExeStk;

    @FXML
    private TextField TXField;

    @FXML
    private Button BStep;

    public static final Integer Column1MapKey = 1;
    public static final Integer Column2MapKey = 2;
    public static final Integer Column3MapKey = 3;
    public static final Integer Column4MapKey = 4;
    public static final Integer Column5MapKey = 5;
    public static final Integer Column6MapKey = 6;

    public void initialize(IStmt st){
        //create the program state,repo and controller
        PrgState prg = new PrgState(1,new MyDictionary<String, Integer>(), new MyStack<IStmt>(), new MyList<Integer>(), new MyFileTable<Integer, MyPair<String, BufferedReader>>(), new MyDictionary<Integer, Integer>() , st);
        MyIRepository repo = new MyRepository(prg,"src/logs/log.txt");
        MyController ctr = new MyController(repo);
        this.controller = ctr;
        //add the data to the tables
        ObservableList<PrgState> obvlist = FXCollections.observableArrayList();
        obvlist.add(prg);
        LPrgState.setItems(obvlist);

        LPrgState.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PrgState>() {

            @Override
            public void changed(ObservableValue<? extends PrgState> observable, PrgState oldValue, PrgState newValue) {
                // re-populate the lists and tables
                MyIStack<IStmt> exestack=newValue.getExe_stk();
                MyIList<Integer> out=newValue.getOut();
                MyIDictionary<String,Integer> symtable=newValue.getSym_tbl();
                MyIFileTable<Integer,MyPair<String, BufferedReader>> ft = newValue.getFile_tbl();
                MyIDictionary<Integer,Integer> heap = newValue.getHeap();

                //setup the "out" list
                ObservableList<Integer> obvout = FXCollections.observableArrayList();
                obvout.addAll(out.getQ());
                LOut.setItems(obvout);

                //setup the execution stack
                ObservableList<IStmt> obvexestk = FXCollections.observableArrayList();
                obvexestk.addAll(exestack.getStk());
                LExeStk.setItems(obvexestk);

                //setup the symbol table
                variablename.setCellValueFactory(new MapValueFactory(Column1MapKey));
                varvalue.setCellValueFactory(new MapValueFactory(Column2MapKey));

                ObservableList<HashMap> obvsymtbl = FXCollections.observableArrayList();
                for(HashMap.Entry e :symtable.getH_Map().entrySet())
                {
                    HashMap<Integer,String> hmp = new HashMap<Integer, String>();
                    hmp.put(Column1MapKey,e.getKey().toString());
                    hmp.put(Column2MapKey,e.getValue().toString());
                    obvsymtbl.addAll(hmp);
                }
                TSymTbl.setItems(obvsymtbl);

                //setup the heap
                adress.setCellValueFactory(new MapValueFactory(Column3MapKey));
                heapvalue.setCellValueFactory(new MapValueFactory(Column4MapKey));

                ObservableList<HashMap> obvheap = FXCollections.observableArrayList();
                for(HashMap.Entry e :heap.getH_Map().entrySet())
                {
                    HashMap<Integer,Integer> hmp2 = new HashMap<Integer, Integer>();
                    hmp2.put(Column3MapKey,(Integer)e.getKey());
                    hmp2.put(Column4MapKey,(Integer)e.getValue());

                    obvheap.add(hmp2);
                }
                THeap.setItems(obvheap);

                //setup the filetable
                id.setCellValueFactory(new MapValueFactory(Column5MapKey));
                filename.setCellValueFactory(new MapValueFactory(Column6MapKey));

                ObservableList<HashMap> obvfiletbl = FXCollections.observableArrayList();
                for(HashMap.Entry e :ft.getH_Map().entrySet())
                {
                    HashMap<Integer,String> hmp3 = new HashMap<Integer, String>();
                    hmp3.put(Column5MapKey,e.getKey().toString());
                    hmp3.put(Column6MapKey,e.getValue().toString());

                    obvfiletbl.add(hmp3);
                }
                TFile.setItems(obvfiletbl);
            }
        });

    }

    public void next(){
        if(controller.repo.getPrgList().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Program has Finished!", ButtonType.CLOSE);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.CLOSE) {
                Stage sc = (Stage)LPrgState.getScene().getWindow();
                sc.close();
                System.exit(0);
            }
        }
        this.controller.allStep();
        ObservableList<PrgState> obvprglist = FXCollections.observableArrayList();
        for(PrgState p : controller.repo.getPrgList())
        {
            obvprglist.add(p);
        }
        LPrgState.setItems(obvprglist);
        TXField.setText(((Integer)controller.repo.getPrgList().size()).toString());
    }

}