package uz.gita.translateuzbeng.ui.adapters

import android.annotation.SuppressLint
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.translateuzbeng.R
import uz.gita.translateuzbeng.data.source.local.DictionaryData.DictionaryData


@Suppress("DEPRECATION")
class FavouriteAdapter(var bool: Boolean, var cursor: Cursor) : RecyclerView.Adapter<FavouriteAdapter.Holder>(){
    private var clickSaveListener: ((Int, Int) -> Unit)? = null

    @SuppressLint("Range")
    inner class Holder(view: View) : ViewHolder(view) {
        private val text1: AppCompatTextView = view.findViewById(R.id.text_1)
        private val text2: AppCompatTextView = view.findViewById(R.id.text_2)
        private val save: AppCompatImageView = view.findViewById(R.id.star)

        init {
            save.setOnClickListener {
                cursor.moveToPosition(adapterPosition)
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                clickSaveListener?.invoke(id, 0)

            }
        }

        fun bind(data: DictionaryData) {
            if (bool) {
                text1.text = data.uzWord
                text2.text = data.engWord
            } else {
                text1.text = data.engWord
                text2.text = data.uzWord
            }
            save.setImageResource(R.drawable.on_star)
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
    override fun onBindViewHolder(holder: FavouriteAdapter.Holder, position: Int) {
        cursor.moveToPosition(position)
        val data = DictionaryData(
            cursor.getInt(cursor.getColumnIndex("id")),
            cursor.getString(cursor.getColumnIndex("english")),
            cursor.getString(cursor.getColumnIndex("uzbek")),
            cursor.getInt(cursor.getColumnIndex("favourite"))
        )

        holder.bind(data)
    }

    fun setClickStarBtn(block: (Int, Int) -> Unit) {
        clickSaveListener = block
    }
}