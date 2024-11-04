package org.mcralph.weightedaveragecalculatorkotlin

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class CalculatorApplication : Application() {
    override fun start(stage: Stage) {
        stage.scene = Scene(Controller().getView(), 800.0, 600.0)
        stage.title = "Weighted Average Calculator"
        stage.show()
    }
}

fun main() {
    Application.launch(CalculatorApplication::class.java)
}