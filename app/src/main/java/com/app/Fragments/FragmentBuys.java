package com.app.Fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.app.Adapters.AdapterFragment_Buys;
import com.app.Models.TenHang;
import com.app.humangreen.APIs_path;
import com.app.humangreen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ThanhCong on 22/4/2018.
 */

public class FragmentBuys extends Fragment {
    AdapterFragment_Buys adapterFragment_buys;
    RecyclerView recyclerView;
    List<TenHang>dstenhang =new ArrayList<>();
    Dialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_buys,container,false);
        dialog=new Dialog(getActivity());
        dialog.setTitle("Loading");
        dialog.show();
        addControlls(view);
        addEvents(view);
        LoadData();
        return view;
    }

    private void LoadData() {
        RequestQueue reques =Volley.newRequestQueue(getActivity());
        reques.add(request);
        reques.start();
    }

    private void addEvents(View view) {

    }

    private void addControlls(View view) {
        recyclerView=view.findViewById(R.id.lvall_tenhang);
        GridLayoutManager gitmanager =new GridLayoutManager(getActivity(),2);
        gitmanager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gitmanager);
        adapterFragment_buys =new AdapterFragment_Buys(dstenhang,getActivity());
        recyclerView.setAdapter(adapterFragment_buys);

    }
   JsonArrayRequest request =new JsonArrayRequest(APIs_path._all, new Response.Listener<JSONArray>() {
       @Override
       public void onResponse(JSONArray response) {
           dstenhang.clear();
           try{
               for (int i=0;i<response.length();i++){
                   JSONObject obj =response.getJSONObject(i);
                   TenHang tenHang =new TenHang();
                   tenHang.setMa(obj.getString("MaHRC"));
                   tenHang.setTen(obj.getString("TenHCR"));
                   tenHang.setHinhanh(obj.getString("Anh"));
                   tenHang.setDongia(obj.getString("DonGia"));
                   tenHang.setDonvi(obj.getString("DonVi"));
                   tenHang.setChitiet(obj.getString("ChiTiet"));
                   dstenhang.add(tenHang);
                   Log.e("ten",tenHang.getTen());
               }
               adapterFragment_buys.notifyDataSetChanged();
               dialog.cancel();
               Log.e("len",response.length()+"");
           }
           catch (JSONException ex){
               Log.e("err",ex.toString());
           }
       }
   }, new Response.ErrorListener() {
       @Override
       public void onErrorResponse(VolleyError error) {
           Log.e("err",error.toString());
           dialog.cancel();
       }
   }){
       @Override
       public Map<String, String> getHeaders() throws AuthFailureError {
           Map<String,String> pagram =new HashMap<String, String>();
           pagram.put("Content-Type","application/json; charset=utf-8");
           return super.getHeaders();
       }
   };
}
