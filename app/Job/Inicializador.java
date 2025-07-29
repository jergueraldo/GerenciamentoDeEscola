package Job;

import models.Turma;
import models.Turma.Turno;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

    @Override
    public void doJob() {
        Turma turma1 = new Turma();
        turma1.nome = "Sexto A";
        turma1.anoLetivo = 2029;
        turma1.serie = 6;
        turma1.turno = Turno.MATUTINO;
        turma1.save();

        Turma turma2 = new Turma();
        turma2.nome = "Primeiro B";
        turma2.anoLetivo = 2025;
        turma2.serie = 1;
        turma2.turno = Turno.VESPERTINO;
        turma2.save();

        Turma turma3 = new Turma();
        turma3.nome = "Terceirão C";
        turma3.anoLetivo = 2023;
        turma3.serie = 3;
        turma3.turno = Turno.NOTURNO;
        turma3.save();
    }

}
