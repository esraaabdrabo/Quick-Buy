import com.route.data.dataSources.products.ProductsRemoteDataSource
import com.route.data.models.products.ProductModel
import com.route.data.models.products.ProductsResponse
import com.route.domain.entities.ProductEntity
import com.route.domain.repos.products.ProductsRepo

class ProductsRepoImpl(
    private val remoteDataSource: ProductsRemoteDataSource,
) : ProductsRepo(
) {
    override suspend fun getProducts(page: Int): List<ProductEntity> {
        //todo check status code -> handle response according to the status code


        val response: ProductsResponse = remoteDataSource.getProducts()


        val products = mutableListOf<ProductEntity>()


        response.data.forEach { product ->
            if (product.id == null) throw Exception("product id is null")
            if (product.price == null) throw Exception("product title is null")

            val entity = ProductEntity(
                id = product.id,
                name = product.title ?: "Product",
                originalPrice = product.price.toDouble(),
                discountedPrice = product.price.toDouble(),
                imageUrl = product.imageCover,
                rating = (product.ratingsAverage ?: 0).toDouble(),
                reviewCount = product.ratingsQuantity ?: 0,
                isWishlisted = false // need to know
            )
            products.add(entity)
        }
        return products
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