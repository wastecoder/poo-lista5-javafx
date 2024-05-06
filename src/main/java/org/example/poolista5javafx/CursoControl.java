package org.example.poolista5javafx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CursoControl {
    private ObservableList<Curso> lista = FXCollections.observableArrayList();

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nome = new SimpleStringProperty("");
    private IntegerProperty codigoCurso = new SimpleIntegerProperty();
    private StringProperty nomeCoordenador = new SimpleStringProperty("");
    private IntegerProperty quantidadeAlunos = new SimpleIntegerProperty();

    //Botoes
    public void gravar() {
        Curso a = toEntity();
        lista.add(a);
        limparCampos();
    }

    public void pesquisarPorNome() {
        for (Curso a : lista) {
            if (a.getNome().contains(nome.get())) {
                fromEntity(a);
            }
        }
    }

    //Acoes
    public void limparCampos() {
        id.set(0);
        nome.set("");
        codigoCurso.set(0);
        nomeCoordenador.set("");
        quantidadeAlunos.set(0);
    }

    public Curso toEntity() {
        Curso a = new Curso();

        a.setId(id.get());
        a.setNome(nome.get());
        a.setCodigoCurso(codigoCurso.get());
        a.setNomeCoordenador(nomeCoordenador.get());
        a.setQuantidadeAlunos(quantidadeAlunos.get());

        return a;
    }

    public void fromEntity(Curso curso) {
        nome.set(curso.getNome());
        id.set(curso.getId());
        codigoCurso.set(curso.getCodigoCurso());
        nomeCoordenador.set(curso.getNomeCoordenador());
        quantidadeAlunos.set(curso.getQuantidadeAlunos());
    }


    public ObservableList<Curso> getLista() {
        return lista;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public IntegerProperty codigoCursoProperty() {
        return codigoCurso;
    }

    public StringProperty nomeCoordenadorProperty() {
        return nomeCoordenador;
    }

    public IntegerProperty quantidadeAlunosProperty() {
        return quantidadeAlunos;
    }
}