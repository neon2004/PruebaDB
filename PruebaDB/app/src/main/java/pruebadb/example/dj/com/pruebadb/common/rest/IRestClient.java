package pruebadb.example.dj.com.pruebadb.common.rest;



import java.util.ArrayList;

import pruebadb.example.dj.com.pruebadb.common.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by neon2004 on 28/01/2017.
 */

public interface IRestClient {
    @GET(Constants.TAG_PARAM_URL)
    Call<ArrayList<Fruit>> getData();


}
