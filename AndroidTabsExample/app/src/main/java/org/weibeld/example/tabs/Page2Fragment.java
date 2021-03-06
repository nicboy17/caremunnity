package org.weibeld.example.tabs;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.weibeld.example.R;

import java.util.ArrayList;

/* Fragment used as page 2 */
public class Page2Fragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<posts> arrayList = new ArrayList<posts>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page2, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerViewPosts);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapterPosts(backgroundTask(), getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    private ArrayList<posts> backgroundTask() {
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, "http://192.168.2.197:4000/api/posts/1",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getActivity().getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        /*int count = 0;
                        while(count < response.length()) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = response.getJSONObject(count);
                                posts p = new posts(Integer.parseInt(jsonObject.getString("user_id")), jsonObject.getString("first"),
                                        jsonObject.getString("last"), Integer.parseInt(jsonObject.getString("post_id")),
                                        jsonObject.getString("content"), jsonObject.getString("title"), jsonObject.getString("picture"),
                                        Integer.parseInt(jsonObject.getString("likes")), getActivity().getApplicationContext());
                                arrayList.add(p);
                                count ++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }*/
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Mysingleton.getInstance(getActivity().getApplicationContext()).addToRequestque(jsonRequest);
        return arrayList;
    }

}