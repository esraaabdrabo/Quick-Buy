import com.route.data.dataSources.products.ProductsRemoteDataSource
import com.route.data.models.productDetails.ProductDetailsModel
import com.route.data.models.products.ProductModel
import com.route.data.repo.products.ProductDetailsParser
import com.route.data.repo.products.ProductsParser
import com.route.domain.entities.PaginationResponse
import com.route.domain.entities.ProductDetailsEntity
import com.route.domain.entities.ProductEntity
import com.route.domain.repos.products.ProductsRepo

class ProductsRepoImpl(
    private val remoteDataSource: ProductsRemoteDataSource,
) : ProductsRepo(
) {
    override suspend fun getProducts(page: Int): PaginationResponse<List<ProductEntity>> {
        //todo check status code -> handle response according to the status code

        val response: PaginationResponse<List<ProductModel>> = remoteDataSource.getProducts(page)

        return ProductsParser.toEntity(response)
    }

    override suspend fun getProduct(id: String): ProductDetailsEntity {
        val product: ProductDetailsModel = remoteDataSource.getProduct(id)

        if (product.id == null) throw Exception("product id is null")
        if (product.price == null) throw Exception("product title is null")

        return ProductDetailsParser.toEntity(product)
    }
}