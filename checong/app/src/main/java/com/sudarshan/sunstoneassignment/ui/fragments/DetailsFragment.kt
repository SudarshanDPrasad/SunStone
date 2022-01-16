package com.sudarshan.sunstoneassignment.ui.fragments

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sudarshan.sunstoneassignment.R
import com.sudarshan.sunstoneassignment.databinding.FragmentDetailsBinding
import java.util.*

class DetailsFragment : Fragment() {

    val args: DetailsFragmentArgs by navArgs()
    private lateinit var detailsFragmentBindings: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsFragmentBindings =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return detailsFragmentBindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsFragmentBindings.apply {
            Glide.with(ivImage).load(args.srcmodel.original).placeholder(
                ColorDrawable(getRandomColor())
            )
                .into(ivImage)

            btnSetWallpaper.setOnClickListener {
                setImage()
            }
        }
    }

    private fun setImage() {
        val bitmap: Bitmap = detailsFragmentBindings.ivImage.drawable.toBitmap();
        val manager: WallpaperManager = WallpaperManager.getInstance(context)
        try {
            manager.setBitmap(bitmap)
            Toast.makeText(context, "Wallpaper Changed", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
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