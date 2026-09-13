data class ApiErrorResponse(
    val message: String?,
    val errors: ApiErrorDetails?,
)

data class ApiErrorDetails(
    val msg: String?,
)