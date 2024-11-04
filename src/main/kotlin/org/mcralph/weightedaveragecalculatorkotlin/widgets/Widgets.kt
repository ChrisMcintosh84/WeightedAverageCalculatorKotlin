package org.mcralph.weightedaveragecalculatorkotlin.widgets

import javafx.beans.property.BooleanProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.StringProperty
import javafx.css.PseudoClass
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.Region

class Widgets {

    private val invalidPseudoClass: PseudoClass = PseudoClass.getPseudoClass("invalid")

    private fun promptLabel(contents: String): Node {
        return styledLabel(contents, "prompt-label")
    }

    fun headingLabel(contents: String): Node {
        return styledLabel(contents, "heading-label")
    }

    private fun styledLabel(contents: String, styleClassApplied: String): Node {
        return Label().apply{
            text = contents
            styleClass += styleClassApplied
        }
    }

    fun promptedTextField(prompt: String, boundProperty: StringProperty): Node {
        return HBox().apply {
            spacing = 6.0
            children += listOf(promptLabel(prompt), boundTextField(boundProperty))
        }
    }

    private fun boundTextField(boundProperty: StringProperty): Node {
        return TextField().apply {
            textProperty().bindBidirectional(boundProperty)
        }
    }

    fun promptedTextArea(prompt: String, boundProperty: StringProperty): Node {
        return HBox().apply {
            spacing = 6.0
            children += listOf(promptLabel(prompt), boundTextArea(boundProperty))
        }
    }

    private fun boundTextArea(boundProperty: StringProperty): Node {
        return TextArea().apply {
            textProperty().bindBidirectional(boundProperty)
            editableProperty().set(false)
            styleClass += "text-area"
        }
    }

    fun row(nodeList: List<Node>): Node = HBox().apply {
        children += nodeList
    }

    fun verticalSpacer(value: Double): Node = Region().apply {
        prefHeight = value
    }

    fun horizontalSpacer(value: Double): Node = Region().apply {
        prefWidth = value
    }

    fun validPromptedTextField(prompt: String, boundProperty: StringProperty, isValidProperty: BooleanProperty): Node {
        return HBox().apply {
            spacing = 6.0
            children += listOf(promptLabel(prompt), validBoundTextField(boundProperty, isValidProperty))
        }
    }

    private fun validBoundTextField(boundProperty: StringProperty, isValidProperty: BooleanProperty): Node {
        return TextField().apply {
            textProperty().bindBidirectional(boundProperty)
            bindValidation(isValidProperty)
            styleClass += "text-field"
        }
    }

    private val TextField.isValidProperty: BooleanProperty
        get() = properties.getOrPut("validationProperty") {
            SimpleBooleanProperty(true).apply {
                addListener { _, _, newValue ->
                    pseudoClassStateChanged(invalidPseudoClass, !newValue)
                }
            }
        } as BooleanProperty

    private fun TextField.bindValidation(validationProperty: BooleanProperty) {
        isValidProperty.bind(validationProperty)
    }

    fun TextField.unbindValidation() {
        isValidProperty.unbind()
    }
}