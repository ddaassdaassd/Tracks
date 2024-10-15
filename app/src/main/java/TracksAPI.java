import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Path;
import retrofit2.http.Body;

public interface TracksAPI {

    @GET("tracks")
    Call<List<Track>> getAllTracks();

    @POST("tracks")
    Call<Track> addTrack(@Body Track track);

    @PUT("tracks/{id}")
    Call<Track> updateTrack(@Path("id") int id, @Body Track track);

    @DELETE("tracks/{id}")
    Call<Void> deleteTrack(@Path("id") int id);
}