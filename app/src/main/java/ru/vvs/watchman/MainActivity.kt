package ru.vvs.watchman

import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.nfc.FormatException
import android.nfc.NfcAdapter
import android.nfc.NfcAdapter.ReaderCallback
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.vvs.watchman.databinding.ActivityMainBinding
import ru.vvs.watchman.model.Journal
import ru.vvs.watchman.screens.startfragment.StartViewModel
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.Locale


class MainActivity : AppCompatActivity(), ReaderCallback {

    private var mBinding: ActivityMainBinding?= null
    private val binding get() = mBinding!!

    lateinit var navController: NavController
    lateinit var actionBar: ActionBar

    lateinit var settings: SharedPreferences

    private var nfcAdapter: NfcAdapter? = null
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.GERMANY)
    private val formatterD = SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY)
    private val formatterH = SimpleDateFormat("hh-mm-ss", Locale.GERMANY)

    //private val startFragment by viewModels<StartViewModel> { ViewModelProvider.AndroidViewModelFactory(application) }
    lateinit var startFragment: StartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivity = this
        navController = Navigation.findNavController(this, R.id.nav_fragment)
        actionBar = supportActionBar!!

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        startFragment = ViewModelProvider(this)[StartViewModel::class.java]

    }

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

                    startFragment.setRecord(Journal(0, formatter.format(Calendar.getInstance().timeInMillis), nfcData, "Обход" ))

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

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}