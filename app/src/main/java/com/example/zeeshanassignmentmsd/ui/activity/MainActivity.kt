package com.example.zeeshanassignmentmsd.ui.activity

import android.R
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.zeeshanassignmentmsd.databinding.ActivityMainBinding
import com.example.zeeshanassignmentmsd.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

import androidx.navigation.Navigation.findNavController
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}