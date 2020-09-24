package com.velasteguicorps.analizador_lexico.Controller;

import com.velasteguicorps.analizador_lexico.Controller.AnalisisP1.AnalisisLexico;
import com.velasteguicorps.analizador_lexico.Herramientas.KeyWordsRules;
import com.velasteguicorps.analizador_lexico.Herramientas.HeaderRules;
import com.velasteguicorps.analizador_lexico.Herramientas.OperatorsRules;
import com.velasteguicorps.analizador_lexico.VO.Token;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.StackPane;

/**
 *
 * @author luis
 */
public class FXMLPrincipal implements Initializable{
    
    int i;
    HeaderRules libraryRules;
    KeyWordsRules keyWordsRules;
    OperatorsRules operatorsRules;
    
    @FXML
    private TabPane tabPanePrincipal;

    @FXML
    private Tab tabCode;

    @FXML
    private StackPane stackPaneCode;
    
    @FXML
    private TextArea txtCode;
    
    @FXML
    private TextArea txtLine;
    
    @FXML
    private TextArea txtResultados;

    @FXML
    private TableView<Token> tableLexico;

    @FXML
    private TableColumn<Token, String> tabValor;

    @FXML
    private TableColumn<Token, String> tabTipo;

    @FXML
    private TableColumn<Token, Number> tabLinea;
    
    @FXML
    private TableColumn<Token, Number> tabColumn;
    
    @FXML
    private Label labelNumero;
    
    
    @FXML
    void onCompile(ActionEvent event) {      
        if(!txtCode.getText().isEmpty()){
            if(!tableLexico.getItems().isEmpty())
                tableLexico.getItems().clear();
            AnalisisLexico aLexico = new AnalisisLexico(
                    txtCode.getText(), libraryRules, keyWordsRules, operatorsRules);
            txtResultados.setText(aLexico.getResults());
            aLexico.getSymbolTable().forEach((id, token) -> {
                tableLexico.getItems().add(token);
            });
            labelNumero.setText("Numero de tokens: " + aLexico.getSymbolTable().size());
            //generateTokenFile();  
        }else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("No se ha podido compilar");
            alert.setContentText("Ingrese codigo");
            alert.showAndWait();
        }
    }
    
    public void generateTokenFile(){
        String path = "Path";
        File file = new File(path);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter print = new PrintWriter(bw);
            Iterator it = tableLexico.getItems().iterator();
            while(it.hasNext()){
                Token token = (Token)it.next();
                print.append("Token: " + token.getValue()).println();
                print.append("Lexema: " + token.getType()).println();
                print.println();
            }
            bw.close();
            fw.close();
        } catch (FileNotFoundException ex) {} catch (IOException ex) {}
    }
    
    public void openFile(File file){
        txtCode.setText("");
        changeNameTab(file);
        StringBuilder programToShow = new StringBuilder(); 
        ArrayList<String> program = openProgram(file);
        int size = program.size();
        if(size > i){
            addSpace(size);
        }
        for(String line : program){
            programToShow.append(line).append("\n");                
        }
        txtCode.setText(programToShow.toString());
    }
    
    public ArrayList<String> openProgram(File file){
        ArrayList<String> program = new ArrayList<>();
        FileReader input;
        BufferedReader buffer;
        try {
            input = new FileReader(file);
            buffer = new BufferedReader(input);
            buffer.lines().forEach((line) -> program.add(line));
            buffer.close();
            input.close();
        } 
        catch (FileNotFoundException ex) {} 
        catch (IOException ex) {}
        return program;
    }
    
    public void changeNameTab(File file){
        tabCode.setText(file.getName());
    }
    
    public void addSpace(int size){
        int sub = size - i;
        stackPaneCode.setMinHeight((stackPaneCode.getMinHeight() + (sub * 16)));
        txtLine.setMinHeight(stackPaneCode.getMinHeight());
        txtCode.setMinHeight(stackPaneCode.getMinHeight());
        int max = i + sub;
        while(max > i){
            i++;
            if(i < 100)
                txtLine.appendText("  "+i+"\n");
            else
                txtLine.appendText(i+"\n");    
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTable();
        setLines();
        setKeyEvents();
        libraryRules = new HeaderRules();
        libraryRules.createRules();
        keyWordsRules = new KeyWordsRules();
        keyWordsRules.createRules();
        operatorsRules = new OperatorsRules(); 
        operatorsRules.createRules();
    }
     
    private void setTable(){
        tableLexico.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabValor.setCellValueFactory((cellData) -> cellData.getValue().getValueProperty());
        tabTipo.setCellValueFactory((cellData) -> cellData.getValue().getTypeProperty());
        tabLinea.setCellValueFactory((cellData) -> cellData.getValue().getLineProperty());
        tabColumn.setCellValueFactory((cellData) -> cellData.getValue().getColumnProperty());
        tableLexico.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    
    private void setLines(){
        txtLine.setEditable(false); 
        for(i = 1; i <= 15; i++){
            if(i < 10)
                txtLine.appendText("    "+i+"\n");
            else
                txtLine.appendText("  "+i+"\n");
        }
        i--;
    }
    
    private void setKeyEvents(){
        KeyCodeCombination enter = new KeyCodeCombination(KeyCode.ENTER);
        KeyCodeCombination ctrlv = new KeyCodeCombination(KeyCode.V, KeyCodeCombination.CONTROL_ANY);
        txtCode.setOnKeyPressed((event) -> {
            if(enter.match(event)){
                int size = i + 1;
                addSpace(size);
            }
        });
        txtCode.setOnKeyReleased((event) -> {
            if(ctrlv.match(event)){
                int size = txtCode.getText().split("\n").length;
                if(size > i)
                    addSpace(size);
            }
        });
    }
}
