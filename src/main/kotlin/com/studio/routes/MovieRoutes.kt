package com.studio.routes

import com.studio.models.Movie
import com.studio.models.movieStorage
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.movieRouting() {
    route("/movie") {
        get {
            if (movieStorage.isNotEmpty()) {
                call.respond(movieStorage)
            } else {
                call.respondText("No movies found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val movie =
                movieStorage.find { it.id == id } ?: return@get call.respondText(
                    "No movie with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(movie)
        }
        post {
            val movie = call.receive<Movie>()
            movieStorage.add(movie)
            call.respondText("Movie stored correctly", status = HttpStatusCode.Created)
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (movieStorage.removeIf { it.id == id }) {
                call.respondText("Movie removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}