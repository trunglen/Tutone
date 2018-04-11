package vn.miraway.tutone.model

class SuccessResponse<T> {
    val data: T? = null
    val status: String = ""
}

class ErrorResponse<T> {
    val error: String = ""
    val status: String = "error"
}