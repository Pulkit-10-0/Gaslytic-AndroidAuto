package com.example.gaslytic

import android.content.Intent
import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.SessionInfo
import androidx.car.app.validation.HostValidator
import com.example.gaslytic.session.GaslyticSession

class CarService : CarAppService() {
    override fun onCreateSession(sessionInfo: SessionInfo): Session{
        return GaslyticSession()
    }

    override fun createHostValidator(): HostValidator {
        return HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
    }
}
