package zww.bawei.com.videodemo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private List<Beans.DataBean> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.main_Rv);
        getData();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.path)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        service.getData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Beans>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Beans beans) {
                list = beans.getData();
                manager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(manager);
                adapter = new MyAdapter(list, MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        if (sp != null) {
            sp.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sp != null) {
            sp.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sp != null) {
            sp.onResume();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (sp != null) {
            sp.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (sp != null && sp.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }*/
}
