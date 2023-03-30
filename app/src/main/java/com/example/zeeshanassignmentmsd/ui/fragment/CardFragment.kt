package com.example.zeeshanassignmentmsd.ui.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

import com.example.zeeshanassignmentmsd.data.model.DeckCard
import com.example.zeeshanassignmentmsd.databinding.FragmentCardBinding
import com.example.zeeshanassignmentmsd.utils.Constants
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoFilter

class CardFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentCardBinding
    private var deckCardObj: DeckCard? = null
    private lateinit var spinnerArrayAdapter: ArrayAdapter<String>
    private var mPhotoEditor: PhotoEditor? = null

    private var mImageFilterList = mutableListOf<String>(
        "Select Filter", "NONE", "CROSS_PROCESS", "DOCUMENTARY", "DUE_TONE",
        "SATURATE", "SEPIA", "SHARPEN", "TEMPERATURE", "TINT", "VIGNETTE"
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBinding.inflate(inflater, container, false)

        deckCardObj = arguments?.getParcelable(Constants.CARD_PARAM)!!

        setTextInCardView()
        initPhotoEditorView()
        loadImageInImageView()
        loadImageInPhotoView()
        setUpSpinner()
        return binding.root
    }

    private fun setTextInCardView() {
        deckCardObj?.let {
            binding.cardSuit.text = "Suit: " + it.suit.toString()
        }
    }

    private fun initPhotoEditorView() {
        mPhotoEditor = PhotoEditor.Builder(requireContext(), binding.photoEditorView)
            .setPinchTextScalable(true)
            .setClipSourceImage(true)
            .build()
    }

    private fun loadImageInImageView() {
        deckCardObj?.let {
            Glide.with(requireContext())
                .load(it.image) // image url
                .fitCenter()
                .into(binding.cardImage);  // imageview
        }
    }

    private fun loadImageInPhotoView() {
        deckCardObj?.let {
            Glide.with(requireContext())
                .load(it.image) // image url
                .fitCenter()
                .into(binding.photoEditorView.source);  // imageview
        }
    }

    private fun setUpSpinner() {
        binding.spinner.onItemSelectedListener = this
        //Creating the ArrayAdapter instance having the country list

        spinnerArrayAdapter = ArrayAdapter<String>(
            requireContext(), R.layout.simple_spinner_item,
            mImageFilterList
        )

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = spinnerArrayAdapter
    }

    override fun onItemSelected(arg0: AdapterView<*>?, arg1: View?, position: Int, id: Long) {
        var str = mImageFilterList.get(position).toString();
        if (str == "NONE") {
            mPhotoEditor?.setFilterEffect(PhotoFilter.NONE);
        } else if (str == "CROSS_PROCESS") {
            mPhotoEditor?.setFilterEffect(PhotoFilter.CROSS_PROCESS);
        } else if (str == "DOCUMENTARY") {
            mPhotoEditor?.setFilterEffect(PhotoFilter.DOCUMENTARY);
        } else if (str == "DUE_TONE") {
            mPhotoEditor?.setFilterEffect(PhotoFilter.DUE_TONE);
        } else if (str == "SATURATE") {
            mPhotoEditor?.setFilterEffect(PhotoFilter.SATURATE);
        } else if (str == "SEPIA") {
            mPhotoEditor?.setFilterEffect(PhotoFilter.SEPIA);
        } else if (str == "SHARPEN") {
            mPhotoEditor?.setFilterEffect(PhotoFilter.SHARPEN);
        } else if (str == "TEMPERATURE") {
            mPhotoEditor?.setFilterEffect(PhotoFilter.TEMPERATURE);
        } else if (str == "TINT") {
            mPhotoEditor?.setFilterEffect(PhotoFilter.TINT);
        } else if (str == "VIGNETTE") {
            mPhotoEditor?.setFilterEffect(PhotoFilter.VIGNETTE);
        } else {
            mPhotoEditor?.setFilterEffect(PhotoFilter.NONE);
        }
    }

    override fun onNothingSelected(arg0: AdapterView<*>?) {

    }
}
