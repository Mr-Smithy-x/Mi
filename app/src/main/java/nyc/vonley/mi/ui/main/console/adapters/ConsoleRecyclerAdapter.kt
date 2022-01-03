package nyc.vonley.mi.ui.main.console.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nyc.vonley.mi.databinding.VhConsoleBinding
import nyc.vonley.mi.models.Console

class ConsoleRecyclerAdapter : RecyclerView.Adapter<ConsoleRecyclerAdapter.ConsoleViewHolder>() {

    private var consoles: List<Console> = emptyList()

    class ConsoleViewHolder(val binding: VhConsoleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setConsole(console: Console) {
            val headers = "${console.name} - ${console.ip}"
            binding.vhConsoleNickname.text = headers
            val features = "Features: ${if (console.features.size > 1) console.features.filter { f -> f.port > 0 } else console.features} "
            binding.vhConsoleIp.text = features
            binding.root.setOnClickListener {
                //TODO: Set Click

            }
        }
    }

    fun setData(consoles: List<Console>) {
        this.consoles = consoles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsoleViewHolder {
        return ConsoleViewHolder(
            VhConsoleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ConsoleViewHolder, position: Int) {
        holder.setConsole(consoles[position])
    }

    override fun getItemCount(): Int {
        return this.consoles.size
    }


}