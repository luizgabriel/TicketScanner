package farolagape.com.br.ticketscanner.models

data class Ticket(
        val id: Int,
        val code: String,
        val hasBoughtCD: Boolean,
        val hasGoneToShow: Boolean);