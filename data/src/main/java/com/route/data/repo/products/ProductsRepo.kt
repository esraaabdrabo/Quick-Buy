package com.route.data.repo.products

import com.route.data.core.network.NetworkErrorMapper
import com.route.data.dataSources.products.ProductsRemoteDataSource
import com.route.data.models.productDetails.ProductDetailsModel
import com.route.data.models.products.ProductModel
import com.route.data.parsers.ProductDetailsParser
import com.route.data.parsers.ProductsParser
import com.route.domain.core.AppResult
import com.route.domain.entities.PaginationResponse
import com.route.domain.entities.ProductDetailsEntity
import com.route.domain.entities.ProductEntity
import com.route.domain.repos.products.ProductsRepo
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

class ProductsRepoImpl @Inject constructor(
    private val remoteDataSource: ProductsRemoteDataSource,
) : ProductsRepo() {
    override suspend fun getProducts(page: Int): AppResult<PaginationResponse<List<ProductEntity>>> {
        return try {
            val response: PaginationResponse<List<ProductModel>> =
                remoteDataSource.getProducts(page)
            AppResult.Success(ProductsParser.toEntity(response))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppResult.Failure(NetworkErrorMapper.map(e))
        }
    }

    override suspend fun getProduct(id: String): AppResult<ProductDetailsEntity> {
        return try {
            val product: ProductDetailsModel = remoteDataSource.getProduct(id)


            AppResult.Success(ProductDetailsParser.toEntity(product))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            AppResult.Failure(NetworkErrorMapper.map(e))
        }
    }
}
