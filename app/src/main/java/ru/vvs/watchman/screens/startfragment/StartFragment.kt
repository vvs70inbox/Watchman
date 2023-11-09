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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.RecyclerView
import ru.vvs.watchman.MainActivity
import ru.vvs.watchman.databinding.FragmentStartBinding
import ru.vvs.watchman.mainActivity
import ru.vvs.watchman.model.Journal
import java.io.IOException
import java.nio.charset.StandardCharsets

class StartFragment : Fragment() {

    private var mBinding: FragmentStartBinding?= null
    private val binding get() = mBinding!!

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
        viewModel = ViewModelProvider(requireActivity())[StartViewModel::class.java]

        recyclerView = binding.startFragment
        adapter = StartAdapter()
        recyclerView.adapter = adapter

        viewModel.getRecords(false)
        viewModel.myList.observe(viewLifecycleOwner) { list ->
            adapter.setList(list)
        }
    }
}