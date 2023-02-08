package com.example.kattabozor.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kattabozor.entity.Offers
import com.example.kattabozor.util.NetworkHelper
import retrofit2.Call
import retrofit2.Response
import com.example.kattabozor.util.Resource
import com.example.kattabozor.retrofit.ApiClient

class ProductListViewModel : ViewModel() {

    private val _getProducts: MutableLiveData<Resource> = MutableLiveData(Resource.Loading)
    val getProducts: LiveData<Resource> get() = _getProducts

    fun getProducts(context: Context) {
        if (NetworkHelper(context = context).isNetworkConnected()) {
            ApiClient.apiService.getWeather()
                .enqueue(object : retrofit2.Callback<Offers> {
                    override fun onResponse(call: Call<Offers>, response: Response<Offers>) {
                        if (response.isSuccessful) {
                            _getProducts.postValue(Resource.Success(data = response.body()?.offers))
                        } else {
                            _getProducts.postValue(Resource.Error(response.errorBody()?.string()))
                        }
                    }

                    override fun onFailure(call: Call<Offers>, t: Throwable) {
                        _getProducts.postValue(Resource.Error(t.message))
                    }

                })
        } else _getProducts.postValue(Resource.NoInternet)
    }
}