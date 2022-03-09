package com.example.cartaodevisitas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cartaodevisitas.R
import com.example.cartaodevisitas.app
import com.example.cartaodevisitas.data.BusinessCard
import com.example.cartaodevisitas.databinding.ActivityAddBusinessCardBinding
import com.example.cartaodevisitas.databinding.ActivityMainBinding

class AddBusinessCardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val MainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as app).repository)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }
        private fun insertListeners(){
            binding.tvClose.setOnClickListener {
                finish()
            }

            binding.btnConfirm.setOnClickListener{
                val BusinessCard = BusinessCard(
                    nome = binding.tilNome.editText?.text.toString(),
                    empresa = binding.tilEmpresa.editText?.text.toString(),
                    telefone = binding.tilTelefone.editText?.text.toString(),
                    email = binding.tilEmail.editText?.text.toString(),
                    fundoPersonalizado = binding.tilCor.editText?.text.toString()


                )
                MainViewModel.insert(BusinessCard)
                Toast.makeText(this,
                    R.string.label_show_success, Toast.LENGTH_SHORT ).show()
                finish()
            }
        }
}