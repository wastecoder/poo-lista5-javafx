package org.example.poolista5javafx;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class CursoBoundary extends Application {
    private TextField txtId = new TextField("");
    private TextField txtNome = new TextField("");
    private TextField txtCodCurso = new TextField("");
    private TextField txtNomeCord = new TextField("");
    private TextField txtQtdAluno = new TextField("");

    private CursoControl control = new CursoControl();

    private TableView<Curso> tabela = new TableView<>();

    @Override
    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 500, 300);

        GridPane gridPane = new GridPane();

        Button btnGravar = new Button("Gravar");
        Button btnPesquisar = new Button("Pesquisar");

        gridPane.add(new Label("ID"), 0, 0);
        gridPane.add(txtId, 1, 0);

        gridPane.add(new Label("Nome"), 0, 1);
        gridPane.add(txtNome, 1, 1);

        gridPane.add(new Label("Codigo do curso"), 0, 2);
        gridPane.add(txtCodCurso, 1, 2);

        gridPane.add(new Label("Nome do coordenador"), 0, 3);
        gridPane.add(txtNomeCord, 1, 3);

        gridPane.add(new Label("Qtd de alunos"), 0, 4);
        gridPane.add(txtQtdAluno, 1, 4);

        gridPane.add(btnGravar, 0,5);
        gridPane.add(btnPesquisar, 1,5);

        bindBoundaryToControl();
        generateTable();

        btnGravar.setOnAction(e -> control.gravar());
        btnPesquisar.setOnAction(e -> control.pesquisarPorNome());

        borderPane.setTop(gridPane);
        borderPane.setCenter(tabela);
        stage.setScene(scene);
        stage.setTitle("Gestao de Cursos");
        stage.show();
    }

    public void bindBoundaryToControl() {
        StringConverter<Number> numberConverter = new NumberStringConverter();

        Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), numberConverter);
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtCodCurso.textProperty(), control.codigoCursoProperty(), numberConverter);
        Bindings.bindBidirectional(txtNomeCord.textProperty(), control.nomeCoordenadorProperty());
        Bindings.bindBidirectional(txtQtdAluno.textProperty(), control.quantidadeAlunosProperty(), numberConverter);
    }

    public void generateTable() {
        TableColumn<Curso, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Curso, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Curso, Integer> colCodCurso = new TableColumn<>("Código do curso");
        colCodCurso.setCellValueFactory(new PropertyValueFactory<>("codigoCurso"));

        TableColumn<Curso, String> colNomeCoord = new TableColumn<>("Nome do coordenador");
        colNomeCoord.setCellValueFactory(new PropertyValueFactory<>("nomeCoordenador"));

        TableColumn<Curso, Integer> colQtdAluno = new TableColumn<>("Qtd de alunos");
        colQtdAluno.setCellValueFactory(new PropertyValueFactory<>("quantidadeAlunos"));


        tabela.getColumns().addAll(colId,colNome,colCodCurso,colNomeCoord,colQtdAluno);
        tabela.setItems( control.getLista() );

        //Ao selecionar uma linha da tabela:
        //1. Exibe no console
        //2. Preenche os TextField
        tabela
                .getSelectionModel()
                .selectedItemProperty().addListener(
                        (obs, oldSelection, newSelection) -> {
                            if (newSelection != null) {
                                System.out.println("Selecionado: "+ newSelection);
                                control.fromEntity(newSelection);
                            }
                        }
                );
    }

    public static void main(String[] args) {
        launch();
    }
}
