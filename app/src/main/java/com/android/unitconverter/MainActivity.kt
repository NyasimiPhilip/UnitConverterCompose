package com.android.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.android.unitconverter.compose.BaseScreen
import com.android.unitconverter.data.ConverterDatabase
import com.android.unitconverter.data.ConverterRepositoryImpl
import com.android.unitconverter.ui.theme.UnitConverterTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var factory: ConverterViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val dao = ConverterDatabase.getInstance(application).converterDAO
        //val repository= ConverterRepositoryImpl(dao)
        //val factory = ConverterViewModelFactory(repository)

        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BaseScreen(factory)
                }
            }
        }
    }
}