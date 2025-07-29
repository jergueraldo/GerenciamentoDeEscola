package controllers;

import java.util.Arrays;
import java.util.List;

import models.Aluno;
import models.Situacao;
import models.Turma;
import models.Turma.Turno;
import play.mvc.Controller;

public class Turmas extends Controller {

    public static void form() {
        List<Turno> turnos = Arrays.asList(Turno.values());
        render(turnos);
    }

    public static void salvar(Turma turma) {
        if (!Turma.validacao(turma)) {
            flash.error("Erro no cadastro! Prrencha os campos corretamente");
            form();
        }

        flash.success("Turma cadastrada com sucesso");
        turma.save();
        form();
    }

    public static void listar(String filtro) {
        List<Turma> turmas;

        if (filtro == null || filtro.trim().isEmpty()) {
            turmas = Turma.find("situacao = ?1", Situacao.ATIVA).fetch();
        } else {
            turmas = Turma.find("Situaoca = ?1 amd lower(nome) like ?2 or lower(serie) like ?2", Situacao.ATIVA,
                    "%" + filtro.toLowerCase() + "%").fetch();
        }

        render(turmas);
    }

    public static void editar(long id) {
        Turma turma = Turma.findById(id);
        renderTemplate("Turmas/form.html", turma);
    }

    public static void remover(long id) {
        Turma turma = Turma.findById(id);
        List<Aluno> alunosDessaTurma = Aluno.find("situacao = ?1 and turma.id = ?2", Situacao.ATIVA, id).fetch();
        for (Aluno aluno : alunosDessaTurma) {
            aluno.situacao = Situacao.INATIVA;
            aluno.save();
        }
        turma.situacao = Situacao.INATIVA;
        turma.save();
        flash.put("info", "A turma e seus respectivos alunos foram removidos");
        listar(null);
    }

}
