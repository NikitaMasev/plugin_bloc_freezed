package com.masev.bloc_freezed

class BlocGenerator(
    name: String,
) : BaseBlocGenerator(name, templateName = "bloc") {
    override fun fileName() = "${snakeCase()}_bloc.${fileExtension()}"
}