package models;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.data.validation.Unique;
import play.db.jpa.Model;

@Entity
public class Turma extends Model {

    public String nome;
    public int anoLetivo;
    public int serie;

    @Enumerated(EnumType.STRING)
    public Turno turno;

    @Enumerated(EnumType.STRING)
    public Situacao situacao;

    public Turma() {
        this.situacao = Situacao.ATIVA;
    }

    public static boolean validacao(Turma turma) {
        List<String> mensagensDeErro = new ArrayList<>();

        int limiteDeAnoLetivo = LocalDate.now().minusYears(4).getYear();

        if (turma.nome.trim().isEmpty()) {
            mensagensDeErro.add("Nome inválido!");
        }
        if (turma.anoLetivo < limiteDeAnoLetivo) {
            mensagensDeErro.add("Ano letivo referente inválido!");
        }
        if (turma.serie < 0) {
            mensagensDeErro.add("serie inválida!");
        }
        if (turma.turno == null) {
            mensagensDeErro.add("Turno inválido!");
        }

        if (!mensagensDeErro.isEmpty()) {
            for (String mensagem : mensagensDeErro) {
                System.out.println(mensagem);
            }
            return false;
        }

        return true;
    }

    public static enum Turno {
        MATUTINO, VESPERTINO, NOTURNO
    }

}
