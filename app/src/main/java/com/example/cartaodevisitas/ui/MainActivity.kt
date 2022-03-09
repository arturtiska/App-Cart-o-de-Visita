package com.example.cartaodevisitas.ui

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.activity.viewModels
import com.example.cartaodevisitas.R
import com.example.cartaodevisitas.app
import com.example.cartaodevisitas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val MainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as app).repository)

    }

    private val adapter by lazy{BusinessCardAdpter()}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCards.adapter = adapter
        getAllBusinessCard()
        insertListener()
    }

    private fun insertListener(){
        binding.fab.setOnClickListener{
            val intent = Intent(this@MainActivity,AddBusinessCardActivity::class.java)
            startActivity(intent)
        }
    }


    private fun getAllBusinessCard() {
        MainViewModel.getAll().observe(this) {businessCard ->
            adapter.submitList(businessCard)
        }
    }
}