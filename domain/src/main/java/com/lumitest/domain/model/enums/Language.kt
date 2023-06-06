package com.lumitest.domain.model.enums

enum class Language(
    val id: Int,
    val tag: String,
    val localizedName: String
) {
    EN(0, "en", "English"),
    RU(1, "com", "Русский");

    companion object {
        operator fun get(tag: String) = values().find { it.tag == tag } ?: RU
    }
}