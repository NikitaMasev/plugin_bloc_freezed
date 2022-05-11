package com.masev.bloc_freezed

import com.google.common.io.CharStreams
import org.apache.commons.lang.text.StrSubstitutor
import java.io.InputStreamReader
import java.lang.RuntimeException

abstract class BaseBlocGenerator(
    private val name: String,
    templateName: String,
) {
    private val templatesDirectory = "templates"

    private val templateBlocCamelCase = "bloc_camel_case"
    private val templateBlocSnakeCase = "bloc_snake_case"
    private val templateImportEventState = "import_events_states"

    private val templateString: String
    private val templateValues: MutableMap<String, String>

    init {
        templateValues = mutableMapOf(
            templateBlocCamelCase to camelCase(),
            templateBlocSnakeCase to snakeCase()
        )
        try {
            val resource = "/$templatesDirectory/$templateName.${fileExtension()}.${templateExtension()}"
            val resourceAsStream = BaseBlocGenerator::class.java.getResourceAsStream(resource)
            templateString = CharStreams.toString(InputStreamReader(resourceAsStream, Charsets.UTF_8))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    abstract fun fileName(): String

    fun generate(importFilepath: String? = null): String {
        importFilepath?.let { filePath ->
            templateValues.put(templateImportEventState, filePath)
        }

        val substitutor = StrSubstitutor(templateValues)
        substitutor.escapeChar = '#'
        return substitutor.replace(templateString)
    }

    fun camelCase(): String = name

    fun snakeCase() = name.camelToSnakeCase()

    fun fileExtension() = "dart"

    fun templateExtension() = "template"
}