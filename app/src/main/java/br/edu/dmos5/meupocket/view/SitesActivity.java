package br.edu.dmos5.meupocket.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

import br.edu.dmos5.meupocket.R;
import br.edu.dmos5.meupocket.dao.SiteDao;
import br.edu.dmos5.meupocket.model.Site;

public class SitesActivity extends AppCompatActivity {
    //Referência para o elemento de layout.
    private ListView sitesListView;

    //Fonte de dados, essa lista possue os dados que são apresentados
    //na tela dos dispositivo.
    private List<Site> siteList;

    //Um adapter é responsável pela ligação da fonte de dados com o elemento
    //de interface (ListView), é esse objeto que configura a apresentação
    //dos dados na tela do app.
    private ArrayAdapter<Site> siteArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);
        //Recupera a referência do elemento no layout
        sitesListView = findViewById(R.id.list_sites);

        //Carrega a fonte de dados
        siteList = SiteDao.recuperateAll();

        //Instancia do adapter, aqui configura-se como os dados serão apresentados e também
        //qual a fonte de dados será utilizada.
        siteArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, siteList);

        //Com o adapter pronto, vinculamos ele ao nosso ListView. Após esse comando o
        //ListView já consegue apresentar os dados na tela.
        sitesListView.setAdapter(siteArrayAdapter);
    }
}