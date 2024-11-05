package org.mcralph.weightedaveragecalculatorkotlin

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Region
import javafx.scene.layout.VBox
import javafx.util.Builder
import org.mcralph.weightedaveragecalculatorkotlin.widgets.Widgets

class ViewBuilder(private val model: Model, private val calculateResult: () -> Unit): Builder<Region> {

    override fun build(): Region {
        val results = BorderPane().apply {
            stylesheets += "widgets.css"
            styleClass += "borderpane"
            top = Widgets().headingLabel("Weighted Average Calculator")
            center = createCenter()
            bottom = createBottomRow()
            padding = Insets(20.0)
        }

        return results
    }

    private fun createCenter(): Node = VBox().apply {
        spacing = 10.0
        children += Widgets().verticalSpacer(30.0)
        children += Widgets().row(listOf(Widgets().validPromptedTextField("Rake 1 tonnes: ", model.rakeOneTonnes, model.rakeOneTonnesIsValid),
            Widgets().horizontalSpacer(20.0),
            Widgets().validPromptedTextField("Rake 1 CCS: ", model.rakeOneCCS, model.rakeOneCCSIsValid)))
        children += Widgets().verticalSpacer(20.0)
        children += Widgets().row(listOf(
            Widgets().validPromptedTextField("Rake 2 tonnes: ", model.rakeTwoTonnes, model.rakeTwoTonnesIsValid),
            Widgets().horizontalSpacer(20.0),
            Widgets().validPromptedTextField("Rake 2 CCS: ", model.rakeTwoCCS, model.rakeTwoCCSIsValid)))
        children += createCalculateButton()
    }

    private fun createCalculateButton(): Node = HBox().apply {
        spacing = 10.0
        alignment = Pos.CENTER_RIGHT
        children += Button("Calculate").apply {
            setOnAction {
                calculateResult.invoke()
            }
            isDefaultButton = true
        }
    }

    private fun createBottomRow(): Node = VBox().apply {
        children += listOf(Widgets().promptedTextArea("Results: ", model.calculatedResult), Widgets().verticalSpacer(50.0))
    }
}