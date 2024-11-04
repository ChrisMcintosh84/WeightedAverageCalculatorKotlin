package org.mcralph.weightedaveragecalculatorkotlin

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty

class Model {

    val rakeOneTonnes : SimpleStringProperty = SimpleStringProperty("")
    val rakeTwoTonnes : SimpleStringProperty = SimpleStringProperty("")
    val rakeOneCCS : SimpleStringProperty = SimpleStringProperty("")
    val rakeTwoCCS : SimpleStringProperty = SimpleStringProperty("")
    var calculatedResult : SimpleStringProperty = SimpleStringProperty("")
    var rakeOneTonnesIsValid: SimpleBooleanProperty = SimpleBooleanProperty(false)
    val rakeTwoTonnesIsValid: SimpleBooleanProperty = SimpleBooleanProperty(false)
    val rakeOneCCSIsValid: SimpleBooleanProperty = SimpleBooleanProperty(false)
    val rakeTwoCCSIsValid: SimpleBooleanProperty = SimpleBooleanProperty(false)
}