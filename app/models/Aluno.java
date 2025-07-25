package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;

@Entity
public class Aluno extends Model {

    public String nome;
    public String email;
    public String telefone;
    public String matricula;

    @Enumerated(EnumType.STRING)
    public Situacao situacao;

    public Aluno() {
        this.situacao = Situacao.ATIVA;
    }

    public static boolean validacao(Aluno aluno) {

        List<String> mensagensDeErro = new ArrayList();
        if (aluno.nome == null || aluno.nome.trim().isEmpty()) {
            mensagensDeErro.add("Nome inválido");
        }
        if (aluno.email == null || aluno.email.trim().isEmpty()) {
            mensagensDeErro.add("Email inválido");
        }
        if (aluno.telefone == null || aluno.telefone.trim().isEmpty()) {
            mensagensDeErro.add("Telefone inválido");
        }
        if (aluno.matricula == null || aluno.nome.trim().isEmpty()) {
            mensagensDeErro.add("Matricula inválida");
        }

        if (!mensagensDeErro.isEmpty()) {

            return false;
        }

        return true;
    }

}
