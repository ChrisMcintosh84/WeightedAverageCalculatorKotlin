package org.mcralph.weightedaveragecalculatorkotlin

import javafx.scene.layout.Region
import javafx.util.Builder

class Controller() {

    private val model = Model()
    private val interactor = Interactor(model)
    private val viewBuilder: Builder<Region> = ViewBuilder(model) { calculateResult() }

    private fun calculateResult() {
        if (interactor.isDataValid()) {
            interactor.printToTextArea()
        }
        else {
            interactor.printError()
        }

        interactor.setValid()
    }

    fun getView(): Region = viewBuilder.build()
}