package com.application.sunstonekotlinassignment.ui.adaptor

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.sunstonekotlinassignment.R
import com.application.sunstonekotlinassignment.databinding.ItemLayoutBinding
import com.application.sunstonekotlinassignment.local.interfacemo.OnCardClicked
import com.application.sunstonekotlinassignment.local.reponse.PhotoModel
import com.bumptech.glide.Glide
import java.util.*

class PhotoAdaptor(
    val list: List<PhotoModel>,
    val onCardClicked: OnCardClicked,
) : RecyclerView.Adapter<PhotoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {

        val itemLayoutBinding: ItemLayoutBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_layout, parent, false
            )
        return PhotoHolder(itemLayoutBinding, onCardClicked)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val photoList = list[position]
        holder.setData(photoList)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class PhotoHolder(
    val itemLayoutBinding: ItemLayoutBinding,
    val onCardClicked: OnCardClicked,
) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun setData(photoModel: PhotoModel) {

        Glide.with(itemLayoutBinding.ivlayoutImage).load(photoModel.srcModel.original).placeholder(
            ColorDrawable(getRandomColor())
        ).into(itemLayoutBinding.ivlayoutImage)

        itemLayoutBinding.ivCard.setOnClickListener {
            onCardClicked.onCardClicked(photoModel.srcModel)
        }
    }

    fun getRandomColor(): Int {
        val colours: MutableList<Int> = ArrayList()
        colours.add(Color.parseColor("#FED8A9"))
        colours.add(Color.parseColor("#C599D6"))
        colours.add(Color.parseColor("#78D6C6"))
        colours.add(Color.parseColor("#A6B8FF"))
        colours.add(Color.parseColor("#E5B9D2"))
        colours.add(Color.parseColor("#FFEABF"))
        colours.add(Color.parseColor("#CCBBE5"))
        colours.add(Color.parseColor("#BCE4FE"))
        colours.add(Color.parseColor("#DAF5A8"))
        colours.add(Color.parseColor("#FFA4B5"))
        colours.add(Color.parseColor("#92CED8"))
        colours.add(Color.parseColor("#DBCBA7"))
        val rand = Random()
        return colours[rand.nextInt(colours.size)]
    }
}
