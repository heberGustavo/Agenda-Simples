package com.apps.heber.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.apps.heber.agenda.dao.AlunoDAO;
import com.apps.heber.agenda.modelo.Aluno;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private Button novooAluno;
    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        novooAluno = (Button) findViewById(R.id.listaAlunos_btn_novoAluno);
        listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        registerForContextMenu(listaAlunos); // Criando Menu de Contexto

        novooAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent formularioIntent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(formularioIntent);
            }
        });

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);
                Intent intentFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intentFormulario.putExtra("ALUNO", aluno);
                startActivity(intentFormulario);

            }
        });
    }

    @Override
    protected void onResume() {
        carregarLista();
        super.onResume();
    }

    private void carregarLista() {
        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> alunoList = alunoDAO.buscaAluno();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alunoList);
        listaAlunos.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        // Titulo da janela
        menu.setHeaderTitle("Informe a opção");
        // Opções da janela
        MenuItem deletar = menu.add("Deletar");
        MenuItem cancelar = menu.add("Cancelar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deletar(aluno);
                dao.close();
                carregarLista();

                Toast.makeText(ListaAlunosActivity.this, "Aluno "+ aluno.getNome()+ " deletado", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}
