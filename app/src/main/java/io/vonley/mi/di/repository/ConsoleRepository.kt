package io.vonley.mi.di.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.flow
import io.vonley.mi.di.network.SyncService
import io.vonley.mi.di.repository.base.BaseRepository
import io.vonley.mi.models.Console
import io.vonley.mi.persistence.ConsoleDao
import javax.inject.Inject

class ConsoleRepository @Inject constructor(
    private val sync: SyncService,
    dao: ConsoleDao
) : BaseRepository<ConsoleDao>(dao) {

    fun getMyConsoles(): LiveData<List<Console>> {
        if (sync.isConnected) {
            val wifi = sync.wifiInfo.ssid
            val consoles = if (wifi != null) {
                dao.get(wifi)
            } else {
                dao.getAll()
            }
            return consoles
        }
        return dao.getAll()
    }

    @WorkerThread
    suspend fun getConsoles(
        onSuccess: () -> Unit
    ) = flow {
        val consoles = getMyConsoles()
        emit(consoles)
        onSuccess()
    }

}
