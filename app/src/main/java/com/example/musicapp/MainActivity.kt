package com.example.musicapp

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.musicapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlin.math.log

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var mb: MediaPlayer

    private lateinit var drawerToggle: DrawerLayout
    private lateinit var navView: NavigationView
    lateinit var toggle: ActionBarDrawerToggle
    private var totalTime = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mb = MediaPlayer.create(this, R.raw.cogaiphuongxa)
        drawerToggle = findViewById(R.id.main)
        toggle = ActionBarDrawerToggle(this, drawerToggle, R.string.open, R.string.close)
        navView = findViewById(R.id.nav_view)


        binding.btnplay.setOnClickListener {
            if(mb.isPlaying){
                mb.pause()
                binding.btnplay.setImageResource(R.drawable.play)
            }else
            {
                mb.start()
                binding.btnplay.setImageResource(R.drawable.pause)
            }
        }
        mb.isLooping = true
        mb.setVolume(0.5f, 0.5f)
        totalTime = mb.duration
        binding.position.max = totalTime

        music.setSeekBar(binding.volume, mb, true)
        music.setSeekBar(binding.position, mb, null,true)

        val handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                val currentPosition = msg.what
                binding.position.progress = currentPosition

                val startTime = music.createTimeLabe(currentPosition)
                binding.txtstart.text = startTime

                val endtime = music.createTimeLabe(totalTime - currentPosition)
                binding.txtend.text = "$endtime"
            }
        }
        Thread(Runnable {
            while (true){
                try {
                    val msg = Message()
                    msg.what = mb.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                }catch (e: InterruptedException){
                    Log.d("Thread", e.message.toString())
                }
            }
        }).start()

        drawerToggle.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> Toast.makeText(this, "Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.filemusic -> Toast.makeText(this, "Clicked File Music", Toast.LENGTH_SHORT).show()
                R.id.sync -> Toast.makeText(this, "Clicked Sunc", Toast.LENGTH_SHORT).show()
                R.id.trash -> Toast.makeText(this, "Clicked Setting", Toast.LENGTH_SHORT).show()
                R.id.setting -> Toast.makeText(this, "Clicked Setting", Toast.LENGTH_SHORT).show()
                R.id.login -> Toast.makeText(this, "Clicked Login", Toast.LENGTH_SHORT).show()
                R.id.share -> Toast.makeText(this, "Clicked Share", Toast.LENGTH_SHORT).show()
                R.id.rate -> Toast.makeText(this, "Clicked Rate Us", Toast.LENGTH_SHORT).show()
            }
            true

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(toggle.onOptionsItemSelected(item)){
           return true
       }
        return super.onOptionsItemSelected(item)
    }
}