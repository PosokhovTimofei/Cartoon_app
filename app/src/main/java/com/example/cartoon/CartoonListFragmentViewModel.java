package com.example.cartoon;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CartoonListFragmentViewModel extends AndroidViewModel{
    private CartoonRepositoriy rep;
    private LiveData<List<Cartoon>> list;

    public CartoonListFragmentViewModel(Application application) {
        super(application);
        rep = new CartoonRepositoriy(getApplication());
        list = rep.getList();
    }
    public LiveData<List<Cartoon>> getCartoons(){
        return list;
    }
}
