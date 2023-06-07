package com.lumitest.feature.transactionslist

import android.view.View
import android.widget.TextView
import com.lumitest.R
import com.lumitest.feature.transactionslist.model.TypedTransaction
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class TransactionItem(val entry: TypedTransaction) :
    AbstractItem<TransactionItem.ViewHolder>() {
    override val type: Int = R.id.itemTransaction


    override val layoutRes: Int = R.layout.item_list_transaction


    class ViewHolder(itemView: View) : FastAdapter.ViewHolder<TransactionItem>(itemView) {
        private val confirmDate: TextView = itemView.findViewById(R.id.confirmDate)
        private val senderAddress: TextView = itemView.findViewById(R.id.senderAddress)
        private val recieverAddress: TextView = itemView.findViewById(R.id.recieverAddress)
        private val amountText: TextView = itemView.findViewById(R.id.amountText)
        private val typeText: TextView = itemView.findViewById(R.id.typeText)


        override fun bindView(item: TransactionItem, payloads: List<Any>) {
            val res = itemView.resources
            with(item.entry) {
                confirmDate.text = formatTimestamp(timeStamp)
                senderAddress.text = from
                recieverAddress.text = to
                amountText.text = formatEthValue(value)
                typeText.text = if (incoming) res.getString(R.string.incoming) else res.getString(R.string.outgoing)
            }
        }

        override fun unbindView(item: TransactionItem) {
            confirmDate.text = null
            senderAddress.text = null
            recieverAddress.text = null
            amountText.text = null
            typeText.text = null
        }

        private fun formatTimestamp(timestamp: String): String {
            val dateTime = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(timestamp.toLong()),
                TimeZone.getDefault().toZoneId()
            )
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return dateTime.format(formatter)
        }

        private fun formatEthValue(value: String): String {
            val ethValue = BigDecimal(value)
                .divide(BigDecimal.TEN.pow(18), 2, RoundingMode.HALF_UP)
            return String.format("%.2f ETH", ethValue)
        }
    }

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

}