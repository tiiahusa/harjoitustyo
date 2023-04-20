package com.example.lutemonit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lutemonfarm.Lutemon;
import lutemonfarm.Storage;


public class FragmentHomeList extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    View v;
    ArrayList<Lutemon> list;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;


    public FragmentHomeList() {
        // Required empty public constructor
    }

    //public static FragmentHomeList newInstance(String param1, String param2) {

        /*/
        FragmentHomeList fragment = new FragmentHomeList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }/*/
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.fragment_home_list, container, false);

            recyclerView = v.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // set it linearlayoutmanager

            list = Storage.getInstance().getLutemonsFromHome(); // Create list or get created one
            adapter = new ListAdapter(getContext(), list);
            recyclerView.setAdapter(adapter);

            return v;
        }
}