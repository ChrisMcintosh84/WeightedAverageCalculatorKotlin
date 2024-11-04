package org.mcralph.weightedaveragecalculatorkotlin

import java.math.BigDecimal
import java.math.MathContext
import java.util.regex.Pattern

class Interactor(private val model: Model) {

    fun isDataValid(): Boolean {
        return validateInput(model.rakeOneTonnes.get()) &&
                validateInput(model.rakeOneCCS.get()) &&
                validateInput(model.rakeTwoTonnes.get()) &&
                validateInput(model.rakeTwoCCS.get())
    }

    private fun validateInput(textContent: String): Boolean {
        return textContent.isNotEmpty() && Pattern.matches("^[0-9]+\\.[0-9]{2}$", textContent)
    }

    fun printError() {
        model.calculatedResult.set("Invalid data!")
    }

    fun setValid() {
        model.rakeOneTonnesIsValid.set(validateInput(model.rakeOneTonnes.get()))
        model.rakeOneCCSIsValid.set(validateInput(model.rakeOneCCS.get()))
        model.rakeTwoTonnesIsValid.set(validateInput(model.rakeTwoTonnes.get()))
        model.rakeTwoCCSIsValid.set(validateInput(model.rakeTwoCCS.get()))
    }

    fun printToTextArea() {
        model.calculatedResult.set(createResultsString(getStrings()))
    }

    private fun getStrings(): Map<String, BigDecimal> {
        val tonnes1 = convertToBigDecimal(model.rakeOneTonnes.get())
        val tonnes2 = convertToBigDecimal(model.rakeTwoTonnes.get())
        val CCS1 = convertToBigDecimal(model.rakeOneCCS.get())
        val CCS2 = convertToBigDecimal(model.rakeTwoCCS.get())
        val weight1 = calculateWeight(tonnes1, tonnes2, tonnes1)
        val weight2 = calculateWeight(tonnes1, tonnes2, tonnes2)
        val weightedAverage1 = calculateWeightedAverage(CCS1, weight1)
        val weightedAverage2 = calculateWeightedAverage(CCS2, weight2)
        val totalCCS = calculateTotalCCS(weightedAverage1, weightedAverage2)

        return mapOf(
            "tonnes1" to tonnes1,
            "tonnes2" to tonnes2,
            "CCS1" to CCS1,
            "CCS2" to CCS2,
            "weight1" to weight1,
            "weight2" to weight2,
            "weightedAverage1" to weightedAverage1,
            "weightedAverage2" to weightedAverage2,
            "totalCCS" to totalCCS)
    }

    private fun calculateWeight(tonnes1: BigDecimal, tonnes2: BigDecimal, tonnesToDivide: BigDecimal): BigDecimal = tonnesToDivide / (tonnes1 + tonnes2)

    private fun calculateWeightedAverage(CCS: BigDecimal, weight: BigDecimal): BigDecimal = CCS * weight

    private fun calculateTotalCCS(average1: BigDecimal, average2: BigDecimal): BigDecimal = average1 + average2

    private fun convertToBigDecimal(stringToConvert: String): BigDecimal = stringToConvert.toBigDecimal()

    private fun createResultsString(strings: Map<String, BigDecimal>): String {
        val results = "Rake one weight: ${strings["tonnes1"]} / (${strings["tonnes1"]} + ${strings["tonnes2"]}) = ${strings["weight1"]}. \n" +
                "Rake two weight: ${strings["tonnes2"]} / (${strings["tonnes1"]} + ${strings["tonnes2"]}) = ${strings["weight2"]}. \n" +
                "Rake one weighted average: ${strings["CCS1"]} * ${strings["weight1"]} = ${strings["weightedAverage1"]}. \n" +
                "Rake two weighted average: ${strings["CCS2"]} * ${strings["weight2"]} = ${strings["weightedAverage2"]}. \n" +
                "Total CCS: ${strings["weightedAverage1"]} + ${strings["weightedAverage2"]} = ${strings["totalCCS"]}, rounded to ${strings["totalCCS"]?.round(
                    MathContext(4)
                )}."

        return results
    }
}