package com.marinato.listatelefonica.model

data class ContactModel(
    val name: String = "",
    val email: String = "",
    val address: String = "",
    val id: Int = 0,
    val phone: Int = 0,
    val imageId: Int = 0
)
