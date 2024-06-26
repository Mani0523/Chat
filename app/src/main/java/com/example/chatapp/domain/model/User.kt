package com.example.chatapp.domain.model

data class User(
    val id: String? = null,
    val name: String,
    val email: String,
    val profileImageUrl: String?,
    val bio:   String,
    val gender: Gender,
    val dob : String?
) {
     constructor() : this(null , "" , "" , null , "", Gender.Male , null)
}
