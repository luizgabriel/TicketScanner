package farolagape.com.br.ticketscanner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import farolagape.com.br.ticketscanner.models.Ticket
import farolagape.com.br.ticketscanner.services.TicketsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TicketActivity : AppCompatActivity(), Callback<Ticket> {

    lateinit var codeTextView : TextView
    lateinit var hasBoughtCD : Switch
    lateinit var hasGoneToShow : Switch

    var ticket: Ticket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        getTicketData()
    }

    private fun getTicketData() {
        val app = application as App
        val code = intent.extras["code"] as String
        app.ticketsService.getTicket(code).enqueue(this)
    }

    override fun onResponse(call: Call<Ticket>?, response: Response<Ticket>?) {
        ticket = response?.body()
        codeTextView.text = ticket?.code
    }

    override fun onFailure(call: Call<Ticket>?, t: Throwable?) {
        ticket = null
    }
}
