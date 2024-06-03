package com.example.cartoon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartoonListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartoonAdapter adapter;
    private CartoonListFragmentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cartoon_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter =  new CartoonAdapter();
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(requireActivity()).get(CartoonListFragmentViewModel.class);
        viewModel.getCartoons().observe(getViewLifecycleOwner(),cartoons -> adapter.setCartoonList(cartoons));
    }
}
