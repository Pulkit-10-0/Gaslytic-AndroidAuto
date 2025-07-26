package com.example.gaslytic.screen

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*
import androidx.core.util.Consumer

class CarMainScreen(carContext: CarContext) : Screen(carContext) {

    private var distanceInput = ""
    private var mileageInput = ""
    private var fuelPriceInput = ""
    private var resultText: String? = null

    override fun onGetTemplate(): Template {
        val paneBuilder = Pane.Builder()

        // Input 1: Distance
        paneBuilder.addRow(
            Row.Builder()
                .setTitle("Distance (km)")
                .addText(distanceInput.ifEmpty { "[Tap to enter]" })
                .setBrowsable(true)
                .setOnClickListener {
                    screenManager.push(
                        InputScreen(carContext, "Enter Distance", Consumer { input ->
                            distanceInput = input
                            invalidate()
                        })
                    )
                }.build()
        )

        // Input 2: Mileage
        paneBuilder.addRow(
            Row.Builder()
                .setTitle("Mileage (km/l)")
                .addText(mileageInput.ifEmpty { "[Tap to enter]" })
                .setBrowsable(true)
                .setOnClickListener {
                    screenManager.push(
                        InputScreen(carContext, "Enter Mileage", Consumer { input ->
                            mileageInput = input
                            invalidate()
                        })
                    )
                }.build()
        )

        // Input 3: Fuel Price
        paneBuilder.addRow(
            Row.Builder()
                .setTitle("Fuel Price (₹/L)")
                .addText(fuelPriceInput.ifEmpty { "[Tap to enter]" })
                .setBrowsable(true)
                .setOnClickListener {
                    screenManager.push(
                        InputScreen(carContext, "Enter Fuel Price", Consumer { input ->
                            fuelPriceInput = input
                            invalidate()
                        })
                    )
                }.build()
        )

        // Calculate Action Button
        paneBuilder.addAction(
            Action.Builder()
                .setTitle("Calculate")
                .setOnClickListener {
                    val d = distanceInput.toFloatOrNull()
                    val m = mileageInput.toFloatOrNull()
                    val p = fuelPriceInput.toFloatOrNull()
                    resultText = if (d != null && m != null && p != null && m != 0f) {
                        val cost = (d / m) * p
                        "Estimated Fuel Cost: ₹%.2f".format(cost)
                    } else {
                        "Please enter valid numbers"
                    }
                    invalidate()
                }
                .build()
        )

        // Show result (if any)
        resultText?.let {
            paneBuilder.addRow(
                Row.Builder()
                    .setTitle("Result")
                    .addText(it)
                    .build()
            )
        }

        return PaneTemplate.Builder(paneBuilder.build())
            .setTitle("⛽ Gaslytic")
            .setHeaderAction(Action.BACK)
            .build()
    }
}
