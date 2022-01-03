package nyc.vonley.mi.ui.main

import nyc.vonley.mi.base.BaseContract
import nyc.vonley.mi.models.Console
import nyc.vonley.mi.models.enums.ConsoleType

interface MainContract {

    interface View : BaseContract.View {
        fun start()
        fun onConsolesFound(consoles: List<Console>)
    }

    interface Presenter : BaseContract.Presenter {
        fun getConsoles(console: ConsoleType);
    }

}