package com.example.zeeshanassignmentmsd.ui.fragment

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.zeeshanassignmentmsd.data.model.DeckCard
import com.example.zeeshanassignmentmsd.databinding.FragmentCardBinding
import com.example.zeeshanassignmentmsd.utils.Constants
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoFilter

class CardFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentCardBinding
    private lateinit var deckCardObj: DeckCard
    private lateinit var spinnerArrayAdapter: ArrayAdapter<String>
    var mImageFilterList = mutableListOf<String>(
        "Select Filter", "NONE", "BRIGHTNESS",
        "CONTRAST", "CROSS_PROCESS", "DOCUMENTARY", "DUE_TONE"
    )
    private var mPhotoEditor: PhotoEditor? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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
        binding.cardSuit.text = "Suit: " + deckCardObj?.suit.toString()
    }

    private fun initPhotoEditorView() {
        mPhotoEditor = PhotoEditor.Builder(requireContext(), binding.photoEditorView)
            .setPinchTextScalable(true)
            .setClipSourceImage(true)
            //   .setDefaultTextTypeface(mTextRobotoTf)
            //   .setDefaultEmojiTypeface(mEmojiTypeFace)
            .build()
    }

    private fun loadImageInImageView() {
        Glide.with(requireContext())
            .load(deckCardObj?.image) // image url
            .fitCenter()
            .into(binding.cardImage);  // imageview

    }

    private fun loadImageInPhotoView() {
        Glide.with(requireContext())
            .load(deckCardObj?.image) // image url
            .fitCenter()
            .into(binding.photoEditorView.source);  // imageview
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
        if (str.equals("NONE")) {
            mPhotoEditor?.setFilterEffect(PhotoFilter.NONE);
        } else if (str.equals("BRIGHTNESS")) {
            mPhotoEditor?.setFilterEffect(PhotoFilter.BRIGHTNESS);
        } else if (str.equals("CONTRAST")) {
            mPhotoEditor?.setFilterEffect(PhotoFilter.CONTRAST);
        } else if (str.equals("CROSS_PROCESS")) {
            mPhotoEditor?.setFilterEffect(PhotoFilter.CROSS_PROCESS);
        } else if (str.equals("DOCUMENTARY")) {
            mPhotoEditor?.setFilterEffect(PhotoFilter.DOCUMENTARY);
        } else if (str.equals("DUE_TONE")) {
            mPhotoEditor?.setFilterEffect(PhotoFilter.DUE_TONE);
        } else {
            mPhotoEditor?.setFilterEffect(PhotoFilter.NONE);
        }
    }

    override fun onNothingSelected(arg0: AdapterView<*>?) {

    }
}
