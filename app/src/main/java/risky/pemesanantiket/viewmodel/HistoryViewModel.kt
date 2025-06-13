package risky.pemesanantiket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import risky.pemesanantiket.database.DatabaseClient.Companion.getInstance
import risky.pemesanantiket.database.dao.DatabaseDao
import risky.pemesanantiket.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    var dataList: LiveData<List<ModelDatabase>> = MutableLiveData()
    var databaseDao: DatabaseDao? = null

    fun deleteDataById(uid: Int) {
        Completable.fromAction {
            databaseDao?.deleteDataById(uid)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    init {
        val instance = getInstance(application)
        if (instance != null) {
            databaseDao = instance.appDatabase.databaseDao()
            dataList = databaseDao?.getAllData() ?: MutableLiveData(emptyList())
        } else {
            dataList = MutableLiveData(emptyList()) // fallback jika instance null
        }
    }
}
