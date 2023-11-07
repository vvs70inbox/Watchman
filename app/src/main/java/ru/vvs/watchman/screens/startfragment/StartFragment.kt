package ru.vvs.watchman.screens.startfragment

import android.icu.util.Calendar
import android.nfc.FormatException
import android.nfc.NfcAdapter
import android.nfc.NfcAdapter.ReaderCallback
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.vvs.watchman.databinding.FragmentStartBinding
import ru.vvs.watchman.mainActivity
import ru.vvs.watchman.model.Journal
import java.io.IOException
import java.nio.charset.StandardCharsets

class StartFragment : Fragment()/*, ReaderCallback */{

    private var mBinding: FragmentStartBinding?= null
    private val binding get() = mBinding!!

    //private var nfcAdapter: NfcAdapter? = null

    private lateinit var viewModel: StartViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        //mainActivity.actionBar.title = "Работа с картотекой"
        viewModel = ViewModelProvider(this)[StartViewModel::class.java]

        recyclerView = binding.startFragment
        adapter = StartAdapter()
        recyclerView.adapter = adapter

        viewModel.getRecords(false)
        viewModel.myList.observe(viewLifecycleOwner) { list ->
            adapter.setList(list)
        }

        //nfcAdapter = NfcAdapter.getDefaultAdapter(mainActivity)
    }

/*
    public override fun onResume() {
        super.onResume()
        enableNfcReaderMode()
    }

    public override fun onPause() {
        super.onPause()
        disableNfcReaderMode()
    }

    private fun enableNfcReaderMode() {
        nfcAdapter!!.enableReaderMode(
            mainActivity,
            this,
            NfcAdapter.FLAG_READER_NFC_A,  // or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK
            null
        )
    }

    private fun disableNfcReaderMode() {
        nfcAdapter!!.disableReaderMode(mainActivity)
    }
    // Обработка NFC-метки
    override fun onTagDiscovered(tag: Tag?) {
        val ndefTag = Ndef.get(tag)
        if (ndefTag != null) {
            try {
                ndefTag.connect()
                val ndefMessage = ndefTag.ndefMessage
                if (ndefMessage != null) {
                    val records = ndefMessage.records
                    val nfcData = String(records[0].payload, StandardCharsets.UTF_8)

                    viewModel.setRecord(Journal(0, Calendar.getInstance().time.toString(), nfcData, "Обход" ))
*/
/*                    runOnUiThread {
                        // выводим информацию о метке
                        val currentTime = Calendar.getInstance().getTime()

                        val nfcInfoTv =
                            findViewById<TextView>(R.id.nfc_info_tv)
                        nfcInfoTv.text = nfcData
                    }*//*

                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: FormatException) {
                e.printStackTrace()
            } finally {
                try {
                    ndefTag.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
*/
}