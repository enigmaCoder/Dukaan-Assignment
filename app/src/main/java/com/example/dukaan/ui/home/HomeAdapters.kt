package com.example.dukaan.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dukaan.R
import java.lang.Exception
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class HeadersAdapter(private val headersList: List<HeadersData>, private val context: Context): RecyclerView.Adapter<HeadersDataVH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadersDataVH {
        return HeadersDataVH(LayoutInflater.from(parent.context).inflate(R.layout.header_child_row,parent,false))
    }

    override fun onBindViewHolder(holder: HeadersDataVH, position: Int) {
        holder.bindTo(headersList[position],context)
    }

    override fun getItemCount(): Int {
        return headersList.size
    }

}

class HeadersDataVH(view: View): RecyclerView.ViewHolder(view){
    private val titleBar: TextView = view.findViewById(R.id.titleBar)
    private val detailsBar: TextView = view.findViewById(R.id.detailsBar)
    private val arrowShow: ImageView = view.findViewById(R.id.arrowShow)
    private val childRecycler1: RecyclerView = view.findViewById(R.id.child1_recycler)
    private val childRecycler2: RecyclerView = view.findViewById(R.id.child2_recycler)

    fun bindTo(headersData: HeadersData,context: Context){
        titleBar.text = headersData.headerTitle
        detailsBar.text = headersData.headerSubTitle
        try {
            val drawableId  =  context.resources.getIdentifier(headersData.arrowType, "drawable", context.packageName)
            arrowShow.setImageResource(drawableId)
        }catch (e: Exception){
            e.stackTrace
        }
        headersData.summary?.let {summaryList ->
            if(summaryList.any()){
                childRecycler1.visibility = View.VISIBLE
                childRecycler1.apply {
                    addItemDecoration(FixMargin(resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),GridLayoutManager.VERTICAL,2))
                    layoutManager = GridLayoutManager(context,2)
                    adapter = SummaryAdapters(summaryList)
                    setRecycledViewPool(recycledViewPool)
                }
            }
        }
        headersData.orderSummary?.let { orderSummaryList ->
            if(orderSummaryList.any()){
                childRecycler1.visibility = View.VISIBLE
                childRecycler1.apply {
                    layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
                    val viewParams = layoutParams as ConstraintLayout.LayoutParams
                    viewParams.topMargin = 18 * context.resources.displayMetrics.density.roundToInt()
                    this.layoutParams = viewParams
                    adapter = OrderSummaryAdapters(orderSummaryList,context,childRecycler2)
                    setRecycledViewPool(recycledViewPool)
                }
            }
        }
    }
}

class SummaryAdapters(private val summaryList: List<Summary>): RecyclerView.Adapter<SummaryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        return SummaryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.grid_card_details,parent,false))
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        holder.bindTo(summaryList[position])
    }

    override fun getItemCount(): Int {
        return summaryList.size
    }

}

class SummaryViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val label: TextView = view.findViewById(R.id.label)
    private val details : TextView = view.findViewById(R.id.details)

    fun bindTo(summary: Summary){
        label.text = summary.summaryTitle
        details.text = summary.summaryData
    }
}

class OrderSummaryAdapters(private val orderSummaryList: List<OrderSummary>,private val context: Context,private val childRecycler2: RecyclerView): RecyclerView.Adapter<OrderSummaryViewHolder>(){
    companion object{
        var currentPosition = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderSummaryViewHolder {
        return OrderSummaryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_card_details,parent,false))
    }

    override fun onBindViewHolder(holder: OrderSummaryViewHolder, position: Int) {
        holder.bindTo(orderSummaryList[position],context,childRecycler2)
    }

    override fun getItemCount(): Int {
        return orderSummaryList.size
    }

}

class OrderSummaryViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val rowInfo: TextView = view.findViewById(R.id.rowInfo)
    val rowBg: ConstraintLayout = view.findViewById(R.id.rowBg)

    fun bindTo(orderSummary: OrderSummary,context: Context,childRecycler2: RecyclerView){
        rowInfo.text = "${orderSummary.orderStatus}(${orderSummary.quantity})"
        rowBg.setOnClickListener {
            if (orderSummary.isSelected) {
                if (orderSummary.orderDetails?.any() == true) {
                    childRecycler2.visibility = View.VISIBLE
                    childRecycler2.apply {
                        while(childRecycler2.itemDecorationCount > 0){
                            childRecycler2.removeItemDecorationAt(0)
                        }
                        addItemDecoration(FixMarginLin(resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin)))
                        layoutManager = LinearLayoutManager(context)
                        adapter = OrderDetailsAdapters(orderSummary.orderDetails!!, context)
                        setRecycledViewPool(recycledViewPool)
                    }
                }
                rowInfo.setTextColor(ContextCompat.getColor(context, R.color.white_two))
                rowBg.background = ContextCompat.getDrawable(context, R.drawable.nice_blue_bg)
            } else {
                rowInfo.setTextColor(ContextCompat.getColor(context, R.color.warm_grey))
                rowBg.background = ContextCompat.getDrawable(context, R.drawable.white_three_bg)
                childRecycler2.visibility = View.GONE
            }
        }
    }
}

class OrderDetailsAdapters(private val orderDetailsList: List<OrderDetail>,private val context: Context): RecyclerView.Adapter<OrderDetailsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailsViewHolder {
        return OrderDetailsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_details,parent,false))
    }

    override fun onBindViewHolder(holder: OrderDetailsViewHolder, position: Int) {
        holder.bindTo(orderDetailsList[position],context)
    }

    override fun getItemCount(): Int {
        return orderDetailsList.size
    }

}

class OrderDetailsViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val orderLabel: TextView = view.findViewById(R.id.order_label)
    private val newBox: TextView = view.findViewById(R.id.newBox)
    private val itemImage: ImageView = view.findViewById(R.id.item_image)
    private val itemQuant: TextView = view.findViewById(R.id.item_quant)
    private val itemPrice: TextView = view.findViewById(R.id.item_price)
    private val dateTime: TextView = view.findViewById(R.id.datestamp)
    private val codBox: TextView = view.findViewById(R.id.codBox)


    fun bindTo(orderDetails: OrderDetail,context: Context){
        orderLabel.text = orderDetails.orderNo
        newBox.visibility = if(orderDetails.isOrderNew == true)  View.VISIBLE else View.GONE
        try {
            val drawableId  =  context.resources.getIdentifier(orderDetails.orderImgId, "drawable", context.packageName)
            itemImage.setImageResource(drawableId)
        }catch (e: Exception){
            e.stackTrace
        }
        dateTime.text = orderDetails.orderDate
        when(orderDetails.orderPaymentStatus){
            "PAID" ->{
                codBox.text = orderDetails.orderPaymentStatus
                codBox.setTextColor(ContextCompat.getColor(context,R.color.cherryRed))
                codBox.background = ContextCompat.getDrawable(context,R.drawable.paids_box)
            }
            "COD" -> {
                codBox.text = orderDetails.orderPaymentStatus
                codBox.setTextColor(ContextCompat.getColor(context,R.color.dustyOrange))
                codBox.background = ContextCompat.getDrawable(context,R.drawable.cod_box)
            }
        }
        itemQuant.text = "${orderDetails.orderQuantity.toString()} ITEM"
        itemPrice.text = "₹${orderDetails.orderPrice}"
    }
}