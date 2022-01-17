package nyc.vonley.mi.ui.main

import nyc.vonley.mi.base.BasePresenter
import nyc.vonley.mi.di.network.SyncService
import nyc.vonley.mi.models.enums.ConsoleType
import javax.inject.Inject

class MainPresenter @Inject constructor(
    val view: MainContract.View,
    val sync: SyncService
) : BasePresenter(),
    MainContract.Presenter {

    override fun getConsoles(console: ConsoleType) {

    }

    override fun init() {
        view.setTitle("ミ - PS4 Tool")
        view.setSummary("Current Target: none")
    }

    override fun cleanup() {

    }

    override val TAG: String
        get() = MainPresenter::class.java.name

}