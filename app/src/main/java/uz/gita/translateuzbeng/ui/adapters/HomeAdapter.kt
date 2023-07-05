package uz.gita.translateuzbeng.ui.adapters

import android.annotation.SuppressLint
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.translateuzbeng.R
import uz.gita.translateuzbeng.data.source.local.DictionaryData.DictionaryData
import uz.gita.translateuzbeng.utils.spannable


class HomeAdapter(var bool: Boolean, var cursor: Cursor, var query: String = "") :
    Adapter<HomeAdapter.Holder>() {
    private var changeSaveStatus: ((Int, Int) -> Unit)? = null

    @SuppressLint("Range")
    @Suppress("DEPRECATION")
    inner class Holder(view: View) : ViewHolder(view) {
        private val text1: AppCompatTextView = view.findViewById(R.id.text_1)
        private val text2: AppCompatTextView = view.findViewById(R.id.text_2)
        private val save: AppCompatImageView = view.findViewById(R.id.star)

        init {
            save.setOnClickListener {
                cursor.moveToPosition(adapterPosition)
                val old = cursor.getInt(cursor.getColumnIndex("favourite"))
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                if (old == 1) {
                    changeSaveStatus?.invoke(id, 0)
                    save.setImageResource(R.drawable.off_star)
                } else {
                    changeSaveStatus?.invoke(id, 1)
                    save.setImageResource(R.drawable.on_star)
                }
            }
        }

        fun bind(data: DictionaryData) {
            if (bool){
                if (query == "") text1.text = data.uzWord
                else text1.text = data.uzWord.spannable(query)
                text2.text = data.engWord
            }else{
                if (query == "") text1.text = data.engWord
                else text1.text = data.engWord.spannable(query)
                text2.text = data.uzWord
            }
            if (data.isSave == 0) save.setImageResource(R.drawable.off_star)
            else save.setImageResource(R.drawable.on_star)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cursor.count
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        cursor.moveToPosition(position)
        val data = DictionaryData(
            cursor.getInt(cursor.getColumnIndex("id")),
            cursor.getString(cursor.getColumnIndex("english")),
            cursor.getString(cursor.getColumnIndex("uzbek")),
            cursor.getInt(cursor.getColumnIndex("favourite"))
        )

        holder.bind(data)
    }

    fun setChangeSaveStatus(block: (Int, Int) -> Unit) {
        changeSaveStatus = block
    }
}