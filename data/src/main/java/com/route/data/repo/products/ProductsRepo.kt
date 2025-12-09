import com.route.data.dataSources.products.ProductsRemoteDataSource
import com.route.data.models.products.ProductModel
import com.route.data.repo.products.ProductsParser
import com.route.domain.entities.PaginationResponse
import com.route.domain.entities.ProductEntity
import com.route.domain.repos.products.ProductsRepo

class ProductsRepoImpl(
    private val remoteDataSource: ProductsRemoteDataSource,
) : ProductsRepo(
) {
    override suspend fun getProducts(page: Int): PaginationResponse<List<ProductEntity>> {
        //todo check status code -> handle response according to the status code


        val response: PaginationResponse<List<ProductModel>> = remoteDataSource.getProducts(page)


        val products = mutableListOf<ProductEntity>()


        return ProductsParser.toEntity(response)
    }

    override suspend fun getProduct(id: Int): ProductEntity {
        val product: ProductModel = remoteDataSource.getProduct(id)
        if (product.id == null) throw Exception("product id is null")
        if (product.price == null) throw Exception("product title is null")

        return ProductEntity(
            id = product.id,
            name = product.title ?: "Product",
            originalPrice = product.price.toDouble(),
            discountedPrice = product.price.toDouble(),
            imageUrl = product.imageCover,
            rating = (product.ratingsAverage ?: 0).toDouble(),
            reviewCount = product.ratingsQuantity ?: 0,
            isWishlisted = false // need to know
        )
    }
}