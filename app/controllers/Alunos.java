package controllers;

import java.util.List;

import models.Aluno;
import models.Situacao;
import models.Turma;
import play.mvc.Controller;

public class Alunos extends Controller {

    public static void form(Long id) {
        if (id == null) {
            Turmas.listar(null);
            flash.error("Id inválido");
        }

        Turma turma = Turma.findById(id);
        render(turma);
    }

    public static void salvar(Aluno aluno) {
        if (!Aluno.validacao(aluno)) {
            flash.error("Erro ao cadastrar! Preencha os campos corretamente!");
            form(null);
        }

        flash.success("Aluno salvo com sucesso");
        aluno.save();
        Turmas.listar(null);
    }

    public static void listar(String filtro) {

        List<Aluno> alunos;

        if (filtro == null || filtro.trim().isEmpty()) {
            alunos = Aluno.find("situacao = ?1", Situacao.ATIVA).fetch();
        } else {
            alunos = Aluno
                    .find("situacao = ?1 and (lower(nome) like ?2 or lower(email) like ?2 or lower(matricula) like ?2)",
                            Situacao.ATIVA, "%" + filtro.toLowerCase() + "%")
                    .fetch();
        }

        render(alunos);
    }

    public static void editar(long id) {
        Aluno aluno = Aluno.findById(id);
        Turma turma = aluno.turma;
        renderTemplate("Alunos/form.html", aluno, turma);

    }

    public static void remover(long id) {
        Aluno aluno = Aluno.findById(id);
        aluno.situacao = Situacao.INATIVA;
        aluno.save();
        flash.put("info", "Sua conta foi deletada");
        listar(null);
    }

}
