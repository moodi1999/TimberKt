package com.example.ahmadreza.timberkotlin.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ahmadreza.timberkotlin.R
import com.example.ahmadreza.timberkotlin.adaptors.SongsListAdapter
import com.example.ahmadreza.timberkotlin.models.SongModel
import java.util.*
import kotlin.Comparator

class SongsFragment : Fragment() {

    private val songList: ArrayList<SongModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val recyclerView: RecyclerView = inflater.inflate(R.layout.fragment_main, container, false) as RecyclerView
        setupSongsList(recyclerView)

        songList.sortWith(Comparator { o1, o2 ->
            o1.tile.compareTo(o2.tile)
        })

        return view
    }

    private fun setupSongsList(recyclerView: RecyclerView) {
        val musicResolver = activity!!.contentResolver
        val musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//        val musicCursor = musicResolver.query(musicUri, null, null, null, null)
        musicResolver.query(musicUri, null, null, null, null)?.run {

            if (moveToFirst()) {
                //get columns
                val titleColumn = getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE)
                val idColumn = getColumnIndex(android.provider.MediaStore.Audio.Media._ID)
                val artistColumn = getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST)
                //add songs to list
                do {
                    val thisId = getLong(idColumn)
                    val thisTitle = getString(titleColumn)
                    val thisArtist = getString(artistColumn)
                    songList.add(SongModel(thisId, thisTitle, thisArtist))
                } while (moveToNext())
            }
        }

        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = SongsListAdapter(activity!!, songList)
    }


}
