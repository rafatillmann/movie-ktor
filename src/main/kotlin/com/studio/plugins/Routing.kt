package com.studio.plugins

import com.studio.routes.movieRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        movieRouting()
    }
}
