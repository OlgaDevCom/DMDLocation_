package coolscan.dvsmart.com.dmdlocation.kostr;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MessagesApi {

    @GET("bPvorlFkwO?indent=2")
    Call<List<Marker>> messages();

}
