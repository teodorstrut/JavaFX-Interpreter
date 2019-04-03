package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Expression.ArithExp;
import model.Expression.ConstExp;
import model.Expression.ReadHeap;
import model.Expression.VarExp;
import model.Statement.*;

import java.io.IOException;
import java.util.ArrayList;

public class GUIController0 {

    @FXML
    private VBox VB0;

    @FXML
    private ListView<IStmt> LV0;

    @FXML
    private Button B0;

    public void initialize(){
        //initialize the list with values
        IStmt ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new
                VarExp("v")));
        IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithExp('+', new ConstExp(2), new
                ArithExp('*', new ConstExp(3), new ConstExp(5)))),
                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new
                        ConstExp(1))), new PrintStmt(new VarExp("b"))));
        IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp('-', new ConstExp(2), new
                ConstExp(2))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new
                        AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
        IStmt ex4 =new CompStmt(new CompStmt(new OpenRFile("file","afile.in"),new ReadFile(new VarExp("file"),"ana")), new CloseRFile(new VarExp("file")));
        IStmt ex5 = new CompStmt(
                new CompStmt(new OpenRFile("fname","src/testfiles/test.in"),
                        new ReadFile(new VarExp("fname"),"val")),
                new CompStmt(
                        new PrintStmt(new VarExp("val")),
                        new CompStmt(new IfStmt(new VarExp("val"),
                                new CompStmt(new ReadFile(new VarExp("fname"),"val"),new PrintStmt(new VarExp("val"))),
                                new PrintStmt(new ConstExp(0))),
                                new CloseRFile(new VarExp("fname")))));
        IStmt ex6 =new CompStmt(new CompStmt(new NewAllocHeap("a",new ConstExp(5)),
                new PrintStmt(new ReadHeap(new VarExp("a")))),
                new WriteHeap("a",new ConstExp(7)));
        IStmt ex7 = new CompStmt(new AssignStmt("v",new ConstExp(10)),
                new CompStmt(new NewAllocHeap("v",new ConstExp(20)),
                        new CompStmt(new NewAllocHeap("a",new ConstExp(22)),
                                new CompStmt(new WriteHeap("a",new ConstExp(30)),
                                        new CompStmt(new PrintStmt(new VarExp("a")),
                                                new CompStmt(new PrintStmt(new ReadHeap(new VarExp("a"))),
                                                        new CompStmt(new AssignStmt("a",new ConstExp(0)),
                                                                new PrintStmt(new ConstExp(6)))))))));
        IStmt ex8 = new CompStmt(new AssignStmt("v",new ConstExp(6)),
                new CompStmt(new WhileStmt(new ArithExp('-',new VarExp("v"),new ConstExp(4)),new CompStmt(new PrintStmt(new VarExp("v")),new AssignStmt("v",new ArithExp('-',new VarExp("v"),new ConstExp(1))))),
                        new PrintStmt(new VarExp("v"))));
        IStmt ex9 = new CompStmt(new CompStmt(new AssignStmt("v",new ConstExp(10)),new NewAllocHeap("a",new ConstExp(22))),new CompStmt(new ForkStmt(new CompStmt(new WriteHeap("a",new ConstExp(30)),new CompStmt(new AssignStmt("v",new ConstExp(32)),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new ReadHeap(new VarExp("a"))))))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new ReadHeap(new VarExp("a"))))));

        ObservableList<IStmt> olist = FXCollections.observableArrayList();
        olist.add(ex1);olist.add(ex2);olist.add(ex3);olist.add(ex4);olist.add(ex5);olist.add(ex6);olist.add(ex7);olist.add(ex8);olist.add(ex9);


        LV0.setItems(olist);
    }
    public void init2() throws IOException
    {
        //select the IStmt
        IStmt abc = LV0.getSelectionModel().getSelectedItem();
        //check if the statement is null
        if(LV0.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Selection!", ButtonType.OK);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        }
        else {
            //create loader and load it into a new scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Main/A8_GUI.fxml"));
            Parent root2 = loader.load();
            Scene newscene = new Scene(root2);
            Stage secondarystage = new Stage();
            secondarystage.setTitle("Primary Window");
            secondarystage.setScene(newscene);
            secondarystage.show();
            //give the selected statement to the new scene
            GUIController1 ctrl = loader.getController();
            ctrl.initialize(abc);
            //close the initial screen
            Stage st = (Stage) B0.getScene().getWindow();
            st.close();
        }
    }

}
