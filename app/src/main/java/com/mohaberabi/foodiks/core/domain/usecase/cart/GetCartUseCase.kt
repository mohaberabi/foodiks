package com.mohaberabi.foodiks.core.domain.usecase.cart

import com.mohaberabi.foodiks.core.domain.model.CartModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetCartUseCase {


    operator fun invoke(): Flow<CartModel> = flowOf<CartModel>()
}