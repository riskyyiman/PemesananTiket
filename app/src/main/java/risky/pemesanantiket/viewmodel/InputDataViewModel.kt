package risky.pemesanantiket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import risky.pemesanantiket.database.DatabaseClient.Companion.getInstance
import risky.pemesanantiket.database.dao.DatabaseDao
import risky.pemesanantiket.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class InputDataViewModel(application: Application) : AndroidViewModel(application) {

    private var databaseDao: DatabaseDao? = null

    init {
        databaseDao = getInstance(application)?.appDatabase?.databaseDao()
    }

    fun addDataPemesan(
        namaPenumpang: String,
        keberangkatan: String,
        tujuan: String,
        tanggal: String,
        nomorTelepon: String,
        anakAnak: Int,
        dewasa: Int,
        hargaTiket: Int,
        kelas: String,
        status: String
    ) {
        Completable.fromAction {
            val data = ModelDatabase().apply {
                this.namaPenumpang = namaPenumpang
                this.keberangkatan = keberangkatan
                this.tujuan = tujuan
                this.tanggal = tanggal
                this.nomorTelepon = nomorTelepon
                this.anakAnak = anakAnak
                this.dewasa = dewasa
                this.hargaTiket = hargaTiket
                this.kelas = kelas
                this.status = status
            }
            databaseDao?.insertData(data)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}
