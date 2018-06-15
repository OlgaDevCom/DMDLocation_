package coolscan.dvsmart.com.dmdlocation.kostr;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.json-generator.com/api/json/get/bPvorlFkwO?indent=")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
