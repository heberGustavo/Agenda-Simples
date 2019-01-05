package com.apps.heber.agenda.helper;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RatingBar;

import com.apps.heber.agenda.R;
import com.apps.heber.agenda.modelo.Aluno;

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoNota;

    private Aluno aluno;

    public FormularioHelper (Activity activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_edt_nome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_edt_endereco);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_edt_telefone);
        campoSite = (EditText) activity.findViewById(R.id.formulario_edt_site);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_rtb_stars);
        aluno = new Aluno();
    }

    public Aluno pegaAluno() {

        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));
        return aluno;
    }

    public void preencherFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEndereco.setText(aluno.getEndereco());
        campoSite.setText(aluno.getSite());
        campoNota.setProgress(aluno.getNota().intValue());
        this.aluno = aluno;
    }
}
