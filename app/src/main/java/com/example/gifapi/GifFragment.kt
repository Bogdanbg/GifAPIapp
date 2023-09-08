package com.example.gifapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.gifapi.databinding.FragmentGifBinding


class GifFragment : Fragment() {

    private var _binding: FragmentGifBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGifBinding.inflate(inflater,container,false)
        val view = binding.root
        val gifUrl = arguments?.getString("gif_url")

        Glide.with(this)
            .asGif()
            .load(gifUrl)
            .into(binding.fullScreenGif)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}