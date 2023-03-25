package com.example.zeeshanassignmentmsd.ui.activity

import android.R
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.zeeshanassignmentmsd.data.model.DeckCard
import com.example.zeeshanassignmentmsd.databinding.ActivityCardBinding
import com.example.zeeshanassignmentmsd.utils.Constants
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoFilter
import kotlinx.android.synthetic.main.activity_card.*

@AndroidEntryPoint
class CardActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityCardBinding
    private lateinit var spinnerArrayAdapter: ArrayAdapter<String>
    var mImageFilterList = mutableListOf<String>(
        "Select Filter","NONE", "BRIGHTNESS",
        "CONTRAST", "CROSS_PROCESS", "DOCUMENTARY", "DUE_TONE"
    )
    private var mPhotoEditor: PhotoEditor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bundle: Bundle? = intent.extras
        //  var message = bundle!!.getString(Constants.CARD_PARAM) // 1
        var deckCardObj: DeckCard? = bundle!!.getParcelable(Constants.CARD_PARAM) // 2
        Log.e("deckCardObj", Gson().toJson(deckCardObj))

        binding.cardSuit.text = "Suit: " + deckCardObj?.suit.toString()

        Glide.with(applicationContext)
            .load(deckCardObj?.image) // image url
            .fitCenter()
            .into(binding.cardImage);  // imageview

        Glide.with(applicationContext)
            .load(deckCardObj?.image) // image url
            .fitCenter()
            .into(photoEditorView.source);  // imageview


        mPhotoEditor = PhotoEditor.Builder(this, photoEditorView)
            .setPinchTextScalable(true)
            .setClipSourceImage(true)
            //   .setDefaultTextTypeface(mTextRobotoTf)
            //   .setDefaultEmojiTypeface(mEmojiTypeFace)
            .build()
        setUpSpinner()
//        Handler().postDelayed({
//            var mPhotoEditor = PhotoEditor.Builder(this, photoEditorView)
//                .setPinchTextScalable(true)
//                .setClipSourceImage(true)
//                //   .setDefaultTextTypeface(mTextRobotoTf)
//                //   .setDefaultEmojiTypeface(mEmojiTypeFace)
//                .build()
//            mPhotoEditor.setFilterEffect(PhotoFilter.DUE_TONE);
//        }, 2000)
    }


    private fun setUpSpinner() {
        binding.spinner.onItemSelectedListener = this
        //Creating the ArrayAdapter instance having the country list

        spinnerArrayAdapter = ArrayAdapter<String>(
            this, R.layout.simple_spinner_item,
            mImageFilterList
        )

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = spinnerArrayAdapter
    }

    override fun onItemSelected(arg0: AdapterView<*>?, arg1: View?, position: Int, id: Long) {
        var str = mImageFilterList.get(position).toString();
        Log.e("str", "abc:" + str);
        /* Handler().postDelayed({
             var mPhotoEditor = PhotoEditor.Builder(this, photoEditorView)
                 .setPinchTextScalable(true)
                 .setClipSourceImage(true)
                 //   .setDefaultTextTypeface(mTextRobotoTf)
                 //   .setDefaultEmojiTypeface(mEmojiTypeFace)
                 .build()*/

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
        //    }, 800)

    }

    override fun onNothingSelected(arg0: AdapterView<*>?) {

    }
}