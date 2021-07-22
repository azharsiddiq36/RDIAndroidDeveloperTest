package com.example.rdiandroiddevelopertest.model

class Movie(val page: Long,
            val results: ArrayList<ItemsMovie>,
            val totalPages: Long,
            val totalResults: Long) {
}