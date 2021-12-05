package com.dev.a0303gallery.fragment

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dev.a0303gallery.R
import com.dev.a0303gallery.adapter.RVAdapter
import com.dev.a0303gallery.viewmodel.GalleryViewModel
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {
    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        val rvAdapter = RVAdapter()
        recyclerView.apply {
            adapter = rvAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        val androidViewModelFactory = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        galleryViewModel = ViewModelProvider(this, androidViewModelFactory).get(GalleryViewModel::class.java)
        galleryViewModel.photoListLive.observe(viewLifecycleOwner, Observer {
            rvAdapter.submitList(it)
            swiperRefreshLayout.isRefreshing = false
        })

        galleryViewModel.photoListLive.value ?: galleryViewModel.getData()
        swiperRefreshLayout.setOnRefreshListener {
            galleryViewModel.getData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.swiperItem -> {
                swiperRefreshLayout.isRefreshing = true
                Handler().postDelayed({
                    galleryViewModel.getData()
                }, 1000)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
