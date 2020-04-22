package com.example.handthishomathon.main.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.handthishomathon.data.Client;
import com.example.handthishomathon.model.Business;
import com.example.handthishomathon.model.Consumer;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SignUpVM extends ViewModel {

    public Consumer consumer = new Consumer();
    public Business business = new Business();
    public MutableLiveData<Consumer> consumerMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Business> businessMutableLiveData = new MutableLiveData<>();
    CompositeDisposable compositeDisposable;
    public void signUpConsumer(Consumer consumer){
        Observable<Consumer> observable = Client.getINSTANCE().signUpConsumer(consumer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(observable.subscribe(o->consumerMutableLiveData.setValue(o), e-> Log.d("PVMError",e.getMessage())));
    }
    public void signUpBusiness(Business business){
        Observable<Business> observable = Client.getINSTANCE().signUpBusiness(business)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(observable.subscribe(o->businessMutableLiveData.setValue(o), e-> Log.d("PVMError",e.getMessage())));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
