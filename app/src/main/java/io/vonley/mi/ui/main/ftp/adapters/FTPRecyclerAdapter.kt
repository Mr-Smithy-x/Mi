package io.vonley.mi.ui.main.ftp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import io.vonley.mi.R
import io.vonley.mi.databinding.VhFtpBinding
import io.vonley.mi.ui.main.ftp.FTPContract
import org.apache.commons.net.ftp.FTPFile
import javax.inject.Inject

class FTPRecyclerAdapter @Inject constructor(
    val view: FTPContract.View
) : RecyclerView.Adapter<FTPRecyclerAdapter.FTPHolder>() {

    private var files: Array<out FTPFile> = arrayOf()

    fun set(files: Array<out FTPFile>) {
        this.files = files
        notifyDataSetChanged()
    }

    inner class FTPHolder(val binding: VhFtpBinding) : RecyclerView.ViewHolder(binding.root) {

        fun Long.toFileSize(): String {
            return (this / 1024).toString() //kb
        }

        fun setInfo(ftpFile: FTPFile) {
            binding.vhFtpFile.text = if (ftpFile.name == ".") "/" else ftpFile.name
            binding.vhFtpInfo.text = ftpFile.timestamp.time.toString()
            val img = if (ftpFile.isDirectory) {
                binding.vhFtpImg.imageTintList = ContextCompat.getColorStateList(itemView.context, R.color.ftpFolderColor)
                binding.vhFtpFile.setTextColor(ContextCompat.getColor(itemView.context, R.color.ftpFolderTitleColor))
                binding.vhFtpInfo.setTextColor(ContextCompat.getColor(itemView.context, R.color.ftpFolderSubtitleColor))
                R.drawable.icon_svg_folder
            } else {
                binding.vhFtpImg.imageTintList = ContextCompat.getColorStateList(itemView.context, R.color.ftpFileColor)
                binding.vhFtpFile.setTextColor(ContextCompat.getColor(itemView.context, R.color.ftpFileTitleColor))
                binding.vhFtpInfo.setTextColor(ContextCompat.getColor(itemView.context, R.color.ftpFileSubtitleColor))
                R.drawable.icon_svg_file
            }
            val drawable = ContextCompat.getDrawable(itemView.context, img)?.let {
                binding.vhFtpImg.setImageDrawable(it)
            }
            itemView.setOnLongClickListener {
                if (ftpFile.isDirectory) {
                    view.onFTPLongClickDir(it, ftpFile)
                    true
                } else if (ftpFile.isFile) {
                    view.onFTPLongClickFile(it, ftpFile)
                    true
                }
                false
            }
            itemView.setOnClickListener {
                if (ftpFile.isDirectory) {
                    view.onFTPDirClicked(ftpFile)
                } else if (ftpFile.isFile) {
                    view.onFTPFileClicked(ftpFile)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FTPHolder {
        return FTPHolder(VhFtpBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FTPHolder, position: Int) {
        holder.setInfo(files[position])
    }

    override fun getItemCount(): Int {
        return files.size
    }

}