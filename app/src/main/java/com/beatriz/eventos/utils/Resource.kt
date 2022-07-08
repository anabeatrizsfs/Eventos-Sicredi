package com.beatriz.eventos.utils

data class Resource<out T>(val status: ResourceStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = ResourceStatus.SUCCESS, data = data, message = null)

        fun <T> error(data: T? = null, message: String): Resource<T> =
            Resource(status = ResourceStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T? = null): Resource<T> =
            Resource(status = ResourceStatus.LOADING, data = data, message = null)
    }
}
