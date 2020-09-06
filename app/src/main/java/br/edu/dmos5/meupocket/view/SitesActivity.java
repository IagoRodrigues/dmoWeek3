package br.edu.dmos5.meupocket.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import br.edu.dmos5.meupocket.R;
import br.edu.dmos5.meupocket.dao.SiteDao;
import br.edu.dmos5.meupocket.model.Site;

public class SitesActivity extends AppCompatActivity{

    //Referência para o elemento de RecyclerView
    private RecyclerView sitesRecyclerView;

    //Fonte de dados, essa lista possue os dados que são apresentados
    //na tela dos dispositivo.
    private List<Site> siteList;

    //Um adapter é responsável pela ligação da fonte de dados com o elemento
    //de interface (ListView), é esse objeto que configura a apresentação
    //dos dados na tela do app.
    private ItemSiteAdapter siteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);

        //Recupera a referência do elemento no layout
        sitesRecyclerView = findViewById(R.id.recycler_lista_sites);

        //Ao contrário do ListView um RecyclerView necessita de um LayoutManager (gerenciador de
        // layout) para gerenciar o posicionamento de seus itens. Utilizarei um LinearLayoutManager
        // que fará com que nosso RecyclerView se pareça com um ListView.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        sitesRecyclerView.setLayoutManager(layoutManager);

        //Carrega a fonte de dados
        siteList = SiteDao.recuperateAll();
        siteAdapter = new ItemSiteAdapter(siteList);
        sitesRecyclerView.setAdapter(siteAdapter);

        siteAdapter.setClickListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String url = corrigeEndereco(siteList.get(position).getEndereco());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private String corrigeEndereco(String endereco) {
        String url = endereco.trim().replace(" ","");
        if (!url.startsWith("http://")) {
            return "http://" + url;
        }
        return url;
    }
}