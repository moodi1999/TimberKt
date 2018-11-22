package com.example.ahmadreza.timberkotlin.adaptors

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ahmadreza.timberkotlin.R
import com.example.ahmadreza.timberkotlin.models.SongModel
import kotlinx.android.synthetic.main.item_song.view.*
import java.text.FieldPosition

class SongsListAdapter(val context: Context, val allSongsList: List<SongModel>?) :
    RecyclerView.Adapter<SongsListAdapter.AllSongsGridHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AllSongsGridHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_song, null)
        return AllSongsGridHolder(view)
    }

    override fun getItemCount(): Int {
        return if (allSongsList != null) allSongsList.size else 0
    }

    override fun onBindViewHolder(viewHolder: AllSongsGridHolder, position: Int) {
        allSongsList?.get(position)!!.run {
            viewHolder.title.text = this.tile
            viewHolder.artist.text = this.artist
        }
    }


    inner class AllSongsGridHolder : RecyclerView.ViewHolder {

        var title: TextView
        var artist: TextView

        constructor(view: View) : super(view) {
            title = view.song_title!!
            artist = view.song_artist!!
        }

    }
}