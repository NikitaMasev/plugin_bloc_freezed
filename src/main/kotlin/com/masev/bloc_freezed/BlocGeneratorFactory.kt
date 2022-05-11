package com.masev.bloc_freezed

object BlocGeneratorFactory {
    fun getBlocGenerators(name: String): List<BaseBlocGenerator> {
        val bloc = BlocGenerator(name)
        val eventState = EventStateGenerator(name)
        return listOf(bloc, eventState)
    }
}
