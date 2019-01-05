package com.apps.heber.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.apps.heber.agenda.dao.AlunoDAO;
import com.apps.heber.agenda.helper.FormularioHelper;
import com.apps.heber.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("ALUNO");
        if(aluno != null){
            helper.preencherFormulario(aluno);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_salvar:
                Aluno aluno = helper.pegaAluno();

                AlunoDAO alunoDAO = new AlunoDAO(this);
                if(aluno.getId() != null){
                    alunoDAO.alterar(aluno);
                    Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " alterado!", Toast.LENGTH_LONG).show();
                }else{
                    alunoDAO.insere(aluno);
                    Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " salvo!", Toast.LENGTH_LONG).show();
                }
                alunoDAO.close();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
