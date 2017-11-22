package zww.bawei.com.videodemo2;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Mr.周 on 2017/11/22.
 */

public interface ApiService {

    public static final String path = "http://result.eolinker.com/";

    @GET("iYXEPGn4e9c6dafce6e5cdd23287d2bb136ee7e9194d3e9?uri=vedio")
    Observable<Beans> getData();
}
