
package com.arteaga.marlon.configuration.constants

import com.android.build.gradle.internal.dsl.BuildType
import java.util.Locale

object Variables {
    private const val APP_NAME = "Recipes"

    private fun BuildType.addVariable(
        typeVariable: TypeVariable,
        typeData: String,
        name: String,
        value: String,
    ) {
        when (typeVariable) {
            TypeVariable.ResValue -> {
                resValue(typeData.lowercase(Locale.getDefault()), name, "\"$value\"")
            }

            TypeVariable.BuildConfigField -> {
                buildConfigField(
                    typeData.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    },
                    name, "\"$value\""
                )
            }
        }
    }

    private fun addAppName(buildTypeEnum: BuildTypeEnum): Pair<String, Pair<String, String>> {
        if (buildTypeEnum == BuildTypeEnum.Release) {
            return Pair(TypeData.String.name, Pair("app_name", APP_NAME))
        }
        return Pair(TypeData.String.name, Pair("app_name", "$APP_NAME $buildTypeEnum"))
    }

    private fun addUrlBase(buildTypeEnum: BuildTypeEnum): Pair<String, Pair<String, String>> {
        val url = "http://demo4550768.mockable.io/"
        return when (buildTypeEnum) {
            BuildTypeEnum.Debug -> Pair(TypeData.String.name, Pair("url_name", url))
            BuildTypeEnum.Release -> Pair(TypeData.String.name, Pair("url_name", url))
        }
    }

    private fun BuildType.addVariables(list: List<Pair<TypeVariable, Pair<String, Pair<String, String>>>>) {
        list.forEach {
            addVariable(
                typeVariable = it.first,
                typeData = it.second.first,
                name = it.second.second.first.lowercase(Locale.getDefault()),
                value = it.second.second.second,
            )
        }
    }

    fun BuildType.addAppVariables(buildTypeEnum: BuildTypeEnum) {
        val appName: Pair<String, Pair<String, String>> = addAppName(buildTypeEnum)

        val listVariables: List<Pair<TypeVariable, Pair<String, Pair<String, String>>>> = listOf(
            Pair(TypeVariable.ResValue, appName),
        )

        addVariables(listVariables)
    }

    fun BuildType.addLibraryVariables(buildTypeEnum: BuildTypeEnum) {

        val endpoints: MutableList<Pair<TypeVariable, Pair<String, Pair<String, String>>>> =
            Endpoints.addEndpoints().map {
                Pair(TypeVariable.BuildConfigField, Pair(TypeData.String.name, it))
            }.toMutableList()

        val urlBase: Pair<String, Pair<String, String>> = addUrlBase(buildTypeEnum)
        endpoints.add(Pair(TypeVariable.BuildConfigField, urlBase))

        addVariables(endpoints)
    }

    enum class TypeVariable {
        ResValue,
        BuildConfigField
    }

    sealed class TypeData : NameTypeData {
        object String : TypeData() {
            override val name = "String"
        }
    }

    internal interface NameTypeData {
        val name: String
    }
}
