package farolagape.com.br.ticketscanner.models

data class ApiResponse<out T>(
        val result: T,
        val count: Int
)
