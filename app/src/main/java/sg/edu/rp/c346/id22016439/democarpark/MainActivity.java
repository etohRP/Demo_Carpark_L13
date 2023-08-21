package sg.edu.rp.c346.id22016439.democarpark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvCarpark;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCarpark = findViewById(R.id.lvCarpark);
        client = new AsyncHttpClient();

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Carpark> alCarpark = new ArrayList<>();

        client.get("https://api.data.gov.sg/v1/transport/carpark-availability", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONArray carparkData = response.getJSONArray("items")
                            .getJSONObject(0)
                            .getJSONArray("carpark_data");

                    for (int i = 0; i < carparkData.length(); i++) {
                        JSONObject carparkInfo = carparkData.getJSONObject(i);
                        String carparkNumber = carparkInfo.getString("carpark_number");
                        JSONArray carparkInfoArray = carparkInfo.getJSONArray("carpark_info");

                        for (int j = 0; j < carparkInfoArray.length(); j++) {
                            JSONObject info = carparkInfoArray.getJSONObject(j);
                            int lotsAvailable = Integer.parseInt(info.getString("lots_available"));

                            // Create a Carpark object and add it to the list
                            Carpark carpark = new Carpark(lotsAvailable, carparkNumber);
                            alCarpark.add(carpark);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Create an adapter to display the Carpark objects in the ListView
                ArrayAdapter<Carpark> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, alCarpark);
                lvCarpark.setAdapter(adapter);
            }
        });
    }
}