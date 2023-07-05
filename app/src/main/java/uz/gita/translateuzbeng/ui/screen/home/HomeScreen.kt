package uz.gita.translateuzbeng.ui.screen.home

import android.database.Cursor
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.translateuzbeng.R
import uz.gita.translateuzbeng.databinding.ScreenHomeBinding
import uz.gita.translateuzbeng.ui.adapters.HomeAdapter
import uz.gita.translateuzbeng.ui.screen.parent.ParentScreen
import java.util.*


class HomeScreen : Fragment(R.layout.screen_home), HomeContract.View, TextToSpeech.OnInitListener {
    private val binding by viewBinding(ScreenHomeBinding::bind)
    private lateinit var presenter: HomePresenter
    private lateinit var adapter: HomeAdapter
    private lateinit var cursor: Cursor
    private lateinit var handler: Handler
    private var _query = ""
    private var bool = false
    private var tts: TextToSpeech? = null
    private var scroll = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = HomePresenter(this)
        presenter.load()
        cursor = presenter.loadData()
        handler = Handler(Looper.getMainLooper())
        adapter = HomeAdapter(bool, cursor)
        tts = TextToSpeech(requireContext(), this)
        binding.apply {

            buttonLan.setOnClickListener {
                presenter.clickLanButton(!bool)
                cursor = presenter.loadData()
                adapter = HomeAdapter(bool, cursor)
                recyclerV.adapter = adapter
                recyclerV.layoutManager = LinearLayoutManager(requireContext())
            }


            imageVolume.setOnClickListener {
                speakOut()
            }
            recyclerV.adapter = adapter
            recyclerV.layoutManager = LinearLayoutManager(requireContext())
            val rv = recyclerV.layoutParams as ViewGroup.MarginLayoutParams

//            recyclerV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//
//                    if (dy > 1) {
//                        searchLine.visibility = View.INVISIBLE
//                        rv.topMargin = -356
//                        recyclerV.layoutParams = rv
//                    } else if (dy < 0) {
//                        searchLine.visibility = View.VISIBLE
//                        rv.topMargin = 0
//                        recyclerV.layoutParams = rv
//                    } else {
//
//                    }
//                }
//            })


            val searchEditText =
                searchText.findViewById<EditText>(com.airbnb.lottie.R.id.search_src_text)
            searchEditText.setHintTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white2
                )
            )
            searchEditText.setTextColor(Color.WHITE)
            adapter.setChangeSaveStatus { id, isSave ->
                presenter.clickStar(id, isSave)
                adapter.cursor = presenter.engWords(_query)
                adapter.notifyDataSetChanged()
            }

            handler = Handler(Looper.getMainLooper())
            searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    handler.removeCallbacksAndMessages(null)
                    query?.let {
                        if (bool) adapter.cursor = presenter.uzbekWords(query.trim())
                        else adapter.cursor = presenter.engWords(query.trim())
                        _query = it.trim()
                        adapter.notifyDataSetChanged()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    handler.removeCallbacksAndMessages(null)
                    handler.postDelayed({
                        newText?.let {
                            if (bool) adapter.cursor = presenter.uzbekWords(_query.trim())
                            else adapter.cursor = presenter.engWords(_query.trim())
                            _query = it.trim()
                            adapter.notifyDataSetChanged()
                        }
                    }, 500)
                    return true
                }
            })
        }
    }

    override fun loadView(_bool: Boolean) {
        binding.apply {
            bool = _bool
            if (!_bool) {
                imageEnglish.setImageResource(R.drawable.img_english)
                textEnglish.text = "English"
                imageUzb.setImageResource(R.drawable.img_uzb)
                textUzb.text = "Uzbek"
                textLanguage.text = "English"
            } else {
                imageEnglish.setImageResource(R.drawable.img_uzb)
                textEnglish.text = "Uzbek"
                imageUzb.setImageResource(R.drawable.img_english)
                textUzb.text = "English"
                textLanguage.text = "Uzbek"
            }

        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

            } else {
                binding.imageVolume.isEnabled = true
            }
        }
    }

    private fun speakOut() {
        tts!!.speak(_query, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}