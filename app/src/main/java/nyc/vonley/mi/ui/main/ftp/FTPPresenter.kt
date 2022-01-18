package nyc.vonley.mi.ui.main.ftp

import kotlinx.coroutines.launch
import nyc.vonley.mi.base.BasePresenter
import nyc.vonley.mi.di.network.MiFTPClient
import nyc.vonley.mi.di.network.SyncService
import org.apache.commons.net.ftp.FTPFile
import java.io.File
import java.io.InputStream
import javax.inject.Inject

class FTPPresenter @Inject constructor(
    val sync: SyncService,
    val ftp: MiFTPClient,
    val view: FTPContract.View
) : BasePresenter(), FTPContract.Presenter {

    override fun navigateTo(ftpFile: FTPFile) {
        if (ftpFile.isDirectory) {
            ftp.setWorkingDir(ftpFile)
        }
    }

    override fun navigateTo(path: String) {
        ftp.setWorkingDir(path)
    }

    override fun delete(ftpFile: FTPFile): Boolean {
        return false
    }

    override fun download(ftpFile: FTPFile, location: String): Boolean {
        return false
    }

    override fun replace(ftpFile: FTPFile, file: File): Boolean {
        return false
    }

    override fun upload(filename: String, stream: InputStream) {
        launch {
            val upload = ftp.upload(filename, stream)
            if(upload){
                view.onFileUpload(filename)
            }else{
                view.onFileFailed(filename)
            }
        }
    }


    override fun init() {
        if (!ftp.cwd.hasObservers()) {
            ftp.cwd.observeForever(this)
        }
        sync.target?.run {
            ftp.connect(ip)
        } ?: run {
            view.onError(Throwable("No target set"))
        }
    }

    override fun cleanup() {
        if (ftp.cwd.hasObservers()) {
            ftp.cwd.removeObserver(this)
            ftp.disconnect()
        }
    }

    override val TAG: String
        get() = FTPPresenter::class.java.name

    override fun onChanged(t: Array<out FTPFile>?) {
        t?.let {
            view.onFTPDirOpened(it)
        } ?: run {
            //TODO nothing?
        }
    }

}
