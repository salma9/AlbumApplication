package com.example.albumapplication.view

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.albumapplication.domain.models.Album
import com.albumapplication.domain.utils.Constants
import com.example.albumapplication.R
import com.example.albumapplication.databinding.DetailBottomSheetFragmentBinding
import com.squareup.picasso.Picasso


class DetailBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DetailBottomSheetFragmentBinding

    companion object{
        fun newInstance(): DetailBottomSheetFragment? {
            return DetailBottomSheetFragment()
        }

        fun newInstance(album: Album) = DetailBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putSerializable(Constants.EXTRA_ALBUM, album)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailBottomSheetFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val album = arguments?.getSerializable(Constants.EXTRA_ALBUM) as Album?
        album?.let {
            binding.albumTitle.text = it.title
            Picasso.get()
                .load(album.url)
                .placeholder(R.drawable.ic_baseline_image_not_supported_24)
                .error(R.drawable.ic_baseline_image_not_supported_24)
                .into(binding.albumImage)
        }


    }
}