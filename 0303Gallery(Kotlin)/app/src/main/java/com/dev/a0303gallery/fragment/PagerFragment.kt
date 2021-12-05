package com.dev.a0303gallery.fragment

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dev.a0303gallery.R
import com.dev.a0303gallery.adapter.VPAdapter
import com.dev.a0303gallery.adapter.VP_VH
import com.dev.a0303gallery.data.PhotoItem
import kotlinx.android.synthetic.main.fragment_pager.*
import kotlinx.android.synthetic.main.vp_item.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val REQUEST_WRITE_EXTERNAL_STORAGE = 1

class PagerFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val parcelableArrayList = arguments?.getParcelableArrayList<PhotoItem>("PhotoItem")
        VPAdapter().apply {
            vp_photo2.adapter = this
            submitList(parcelableArrayList)
        }

        vp_photo2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tv_photo_info.text = getString(R.string.photo_position, position + 1, parcelableArrayList?.size)
            }
        })

        vp_photo2.setCurrentItem(arguments?.getInt("PhotoPosition") ?: 0, false)

        btn_save.setOnClickListener {
            if (Build.VERSION.SDK_INT < 29 &&
                    ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_WRITE_EXTERNAL_STORAGE
                )
            } else {
                viewLifecycleOwner.lifecycleScope.launch { savePhoto() }
            }
        }
    }

    private suspend fun savePhoto() {
        withContext(Dispatchers.IO) {
            val vpAdapter = (vp_photo2[0] as RecyclerView).findViewHolderForAdapterPosition(vp_photo2.currentItem) as VP_VH
            val toBitmap = vpAdapter.itemView.imageView2.drawable.toBitmap()
            val uri = requireContext().contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    ContentValues()
            ) ?: kotlin.run {
                Toast.makeText(requireContext(), "存储失败", Toast.LENGTH_SHORT).show()
                return@withContext
            }

            requireContext().contentResolver.openOutputStream(uri).use {
                val isSuccess = toBitmap.compress(Bitmap.CompressFormat.JPEG, 90, it)
                if (isSuccess) {
                    MainScope().launch { Toast.makeText(requireContext(), "存储成功", Toast.LENGTH_SHORT).show() }
                } else {
                    MainScope().launch { Toast.makeText(requireContext(), "存储失败", Toast.LENGTH_SHORT).show() }
                }

            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    viewLifecycleOwner.lifecycleScope.launch { savePhoto() }
                } else {
                    Toast.makeText(requireContext(), "存储失败", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}
