package com.example.lwb

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.example.lwb.core.data.LWBDatabase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var database: LWBDatabase

    @OptIn(ExperimentalMaterialApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window,
            window.decorView.findViewById(R.id.content)).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        // Запускаем корутину для получения упражнений из базы данных
//        lifecycleScope.launch {
//            val exercises = database.exerciseDao().getAll()
//            if (exercises.isEmpty()) {
//                Log.d("Exercise", "No exercises found in the database.")
//            } else {
//                exercises.forEach { exercise ->
//                    Log.d("Exercise", exercise.toString())
//                }
//            }
//        }


        setContent { LWBApp(applicationContext, lifecycleScope) }
    }
}