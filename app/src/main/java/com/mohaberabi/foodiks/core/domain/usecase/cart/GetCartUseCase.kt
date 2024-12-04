package com.mohaberabi.foodiks.core.domain.usecase.cart

import com.mohaberabi.foodiks.core.domain.model.CartModel
import com.mohaberabi.foodiks.core.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetCartUseCase(
    private val cartRepository: CartRepository
) {


    operator fun invoke(): Flow<CartModel> = cartRepository.getCartItems()
}