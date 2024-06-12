package com.example.musicapp

import android.media.MediaPlayer
import android.widget.SeekBar

object music {
    fun setSeekBar(seakBar: SeekBar, mb: MediaPlayer, volume: Boolean? = null, seakTo: Boolean? = null){
        seakBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    when{
                        volume == null -> mb.seekTo(progress)
                        seakTo == null -> {
                            val voLumeNum = progress / 100.0f
                            mb.setVolume(voLumeNum,voLumeNum)
                        }
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
    fun createTimeLabe(time: Int): String{
        var timeLable = ""
        val min = time / 1000 / 60
        val sec = time / 1000 % 60

        timeLable = "$min: "
        if(sec< 10 ) timeLable += "0"
        timeLable += sec
        return timeLable
    }
}