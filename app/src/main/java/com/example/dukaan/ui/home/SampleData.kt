package com.example.dukaan.ui.home

import kotlinx.serialization.Serializable

@Serializable
data class OrderSampleData(
    var headersData: List<HeadersData>? = null
)

@Serializable
data class OrderDetail(
    var isOrderNew: Boolean? = null,
    var orderDate: String? = null,
    var orderImgId: String? = null,
    var orderNo: String? = null,
    var orderPaymentStatus: String? = null,
    var orderPrice: Int? = null,
    var orderQuantity: Int? = null
)

@Serializable
data class OrderSummary(
    var orderDetails: List<OrderDetail>? = null,
    var orderStatus: String? = null,
    var quantity: Int? = null,
    var isSelected: Boolean = false
)

@Serializable
data class Summary(
    var summaryData: String? = null,
    var summaryTitle: String? = null
)

@Serializable
data class HeadersData(
    var arrowType: String? = null,
    var headerSubTitle: String? = null,
    var headerTitle: String? = null,
    var orderSummary: MutableList<OrderSummary>? = null,
    var summary: List<Summary>? = null
)