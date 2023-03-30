package com.example.zeeshanassignmentmsd.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zeeshanassignmentmsd.data.model.DeckCard
import com.example.zeeshanassignmentmsd.databinding.CardItemLayoutBinding
import com.example.zeeshanassignmentmsd.ui.CardItemClickListener

class CardsAdapter(
    val context: Context,
    private var cardsList: MutableList<DeckCard>,
    private val cardItemClickListener1: CardItemClickListener
) :

    RecyclerView.Adapter<CardsAdapter.DataViewHolder>() {

    lateinit var binding: CardItemLayoutBinding
    lateinit var cardItemClickListener: CardItemClickListener

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: DeckCard) {
            Glide.with(context)
                .load(card.image) // image url
                .centerCrop()
                .into(binding.cardIV);  // imageview object
            binding.cardIV.setOnClickListener {
                cardItemClickListener.onCLick(card)
            };

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        binding = CardItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        );
        cardItemClickListener = cardItemClickListener1
        return DataViewHolder(binding.root);
    }

    override fun getItemCount(): Int = cardsList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(cardsList[position])

    fun updateData(list: MutableList<DeckCard>) {
        cardsList = mutableListOf()
        cardsList = list;
        notifyDataSetChanged()
    }

}