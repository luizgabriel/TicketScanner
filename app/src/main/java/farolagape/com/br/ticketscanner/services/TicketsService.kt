package farolagape.com.br.ticketscanner.services

import farolagape.com.br.ticketscanner.models.Ticket
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TicketsService {
    @GET("/tickets")
    fun getTicket(@Query("code") code: String): Call<Ticket>
}
