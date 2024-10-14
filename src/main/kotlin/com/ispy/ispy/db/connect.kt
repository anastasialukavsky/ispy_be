package com.ispy.ispy.db

import java.sql.DriverManager

fun main() {
    val userName = "alukavsky"
    val password = "postgrespass"
    val url = "jdbc:postgresql://localhost:5432/ispy_db"


    try {
        DriverManager.getConnection(url, userName, password).use { conn ->

        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}