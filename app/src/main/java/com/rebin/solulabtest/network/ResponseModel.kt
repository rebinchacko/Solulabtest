package com.rebin.solulabtest.network

data class ResponseModel(
    val `data`: Data,
    val msg: String,
    val result: Int
)

data class Data(
    val itemsPerPage: Int,
    val list: List<Response>,
    val startIndex: Int,
    val totalItems: Int
)

data class Response(
    val _id: String,
    val age: Int,
    val history: String,
    val isCoin: Boolean,
    val isGraded: Boolean,
    val isSold: Boolean,
    val name: String,
    val pictures: Pictures,
    val price: Int,
    val tags: List<String>,
    val year: Int
)

data class Pictures(
    val back: Back,
    val front: Front
)

data class Back(
    val key: String,
    val sizeInMegaByte: Double,
    val url: String
)

data class Front(
    val key: String,
    val sizeInMegaByte: Double,
    val url: String
)