package com.mohaberabi.foodiks.core.common.util.extension

import java.util.Locale


fun Number.toCurrencyFormat(
): String = String.format(Locale.getDefault(), "%.2f", this)

fun Number.clockFormat(
): String = String.format(Locale.getDefault(), "%02d", this)