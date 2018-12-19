package com.example.david.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class WelcomeActivity extends AppCompatActivity implements OnClickListener, FoodListAdapter.OnQuantityChange {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FoodListAdapter adapter;
    TextView totalTextView;
    int total =0;
    ProgressBar progressBar;
    int progress =0;
    Button buy;
    ArrayList<Food> food1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView welcomeTW = findViewById(R.id.welcome_tv);
        String mail =getIntent().getStringExtra("Welcome");
        welcomeTW.setText(getString(R.string.welcome)+" "+ mail);
        recyclerView= findViewById(R.id.food_rv);
        totalTextView =findViewById(R.id.total);
        layoutManager= new LinearLayoutManager(this);
        getProducts();
        progressBar= findViewById(R.id.progress_bar);
        buy = findViewById(R.id.buy);

    }
   private void getProducts() {
       RequestQueue queue = Volley.newRequestQueue(this);
       String url = "https://5ba19290ee710f0014dd764c.mockapi.io/Food#";
       StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       Log.d("HTTP", response);
                       try{
                           JSONObject responseJSON = new JSONObject(response);
                           JSONArray jsonArray = responseJSON.getJSONArray("food");
                           for(int i=0; i<jsonArray.length(); i++){
                               Food food = new Food(jsonArray.getJSONObject(i));
                               food1.add(food);
                               }
                               setFoodView();
                       }catch (JSONException e){
                           e.printStackTrace();
                       }
                       // Display the first 500 characters of the response string.
                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Log.d("error", error.getMessage());

                   }
               });

       // Add the request to the RequestQueue.
       queue.add(stringRequest);
   }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String mail =getIntent().getStringExtra("Welcome");
        String[] to = {mail,""};
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.setType("message/rfc822");
        Intent chooser = Intent.createChooser(intent, "Send email");
        startActivity(chooser);
    }

    @Override
    public void onItemAdd(float productPrice) {
        total += productPrice;
        totalTextView.setText("Total :"+ total);
        progressBar.setProgress(total);
        if(total>=5){
            buy.setEnabled(true);
        }
    }

    @Override
    public void onItemRemove(float productPrice) {
        if(total == 0) return;
        total -= productPrice;
        totalTextView.setText("Total :" + total);
        progressBar.setProgress(total);
        if(total<5) {
            buy.setEnabled(false);
        }
    }

    private void setFoodView(){
        adapter = new FoodListAdapter(this, food1);
        adapter.setOnQuantityChange(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}





