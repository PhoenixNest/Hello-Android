package com.dev.a0303gallery.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.dev.a0303gallery.data.PhotoItem
import com.dev.a0303gallery.data.Pixabay
import com.dev.a0303gallery.data.VolleySingleton
import com.google.gson.Gson

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val _photoListLive = MutableLiveData<List<PhotoItem>>()
    val photoListLive: LiveData<List<PhotoItem>>
        get() {
            return _photoListLive
        }

    fun getData() {
        val stringRequest = StringRequest(Request.Method.GET, getUrl(), Response.Listener {
            _photoListLive.value = Gson().fromJson(it, Pixabay::class.java).hits.toList()
        }, Response.ErrorListener {
            Log.d("Error", it.toString())
        }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }

    private fun getUrl(): String {
        return "https://pixabay.com/api/?key=15455499-22cfacf3fc2820d332ff649aa&q=${keyWords.random()}&per_page=100";
    }

    private val keyWords = arrayOf("shoes", "converse")
}