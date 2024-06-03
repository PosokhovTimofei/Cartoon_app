package com.example.cartoon;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CartoonRepositoriy {
    private LiveData<List<Cartoon>> allCartoons;
    private CartoonDao cartoonDao;
    public CartoonRepositoriy(Application application){
        CartoonDatabase db = CartoonDatabase.getInstance(application);
        cartoonDao = db.cartoonDao();
        allCartoons = cartoonDao.getAllCartoons();

    }
    public LiveData<List<Cartoon>> getList(){
        return allCartoons;
    }
}
