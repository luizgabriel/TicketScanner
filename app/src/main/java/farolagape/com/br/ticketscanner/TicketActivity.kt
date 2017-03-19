package farolagape.com.br.ticketscanner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Switch
import farolagape.com.br.ticketscanner.models.ApiResponse
import farolagape.com.br.ticketscanner.models.Ticket
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.TextView


class TicketActivity : AppCompatActivity(), Callback<ApiResponse<Ticket>> {

    lateinit var codeTextView : TextView
    lateinit var hasBoughtCD : Switch
    lateinit var hasGoneToShow : Switch

    var ticket: Ticket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        actionBar.setDisplayHomeAsUpEnabled(true)
        codeTextView = findViewById(R.id.codeTextView) as TextView
        hasBoughtCD = findViewById(R.id.hasBoughtCDSwitch) as Switch
        hasGoneToShow = findViewById(R.id.hasGoneToShowSwitch) as Switch

        getTicketData()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getTicketData() {
        val app = application as App
        val code = intent.extras.getString(MainActivity.CODE)
        app.ticketsService.getTicket(code).enqueue(this)
    }

    override fun onResponse(call: Call<ApiResponse<Ticket>>?, response: Response<ApiResponse<Ticket>>?) {
        ticket = response?.body()?.result
        codeTextView.text = ticket?.code
        hasBoughtCD.isChecked = ticket?.hasBoughtCD ?: false
        hasGoneToShow.isChecked = ticket?.hasGoneToShow ?: false
    }

    override fun onFailure(call: Call<ApiResponse<Ticket>>?, t: Throwable?) {
        ticket = null
        Log.e("TicketsService", "Could not retrieve ticket data", t)
        finish()
    }


}
