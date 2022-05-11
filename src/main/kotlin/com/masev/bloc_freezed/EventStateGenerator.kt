package com.masev.bloc_freezed

class EventStateGenerator(
    blocName: String,
) : BaseBlocGenerator(blocName, templateName = "events_states") {
    override fun fileName() = "events_states.${fileExtension()}"
}