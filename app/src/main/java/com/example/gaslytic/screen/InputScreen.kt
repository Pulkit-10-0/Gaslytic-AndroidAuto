package com.example.gaslytic.screen

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.SearchTemplate
import androidx.car.app.model.Template
import androidx.core.util.Consumer

class InputScreen(
    carContext: CarContext,
    private val title: String,
    private val onInput: Consumer<String>
) : Screen(carContext) {

    override fun onGetTemplate(): Template {
        return SearchTemplate.Builder(object : SearchTemplate.SearchCallback {
            override fun onSearchTextChanged(searchText: String) {
                // Optional: handle live changes
            }

            override fun onSearchSubmitted(query: String) {
                onInput.accept(query)
                screenManager.pop()
            }
        })
            .setSearchHint(title)
            .setShowKeyboardByDefault(true)
            .build()
    }
}
