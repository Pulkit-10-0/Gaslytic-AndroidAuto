package com.example.gaslytic.session

import android.content.Intent
import androidx.car.app.CarContext
import com.example.gaslytic.screen.CarMainScreen
import androidx.car.app.Screen
import androidx.car.app.Session

class GaslyticSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        return CarMainScreen(carContext)
    }
}
