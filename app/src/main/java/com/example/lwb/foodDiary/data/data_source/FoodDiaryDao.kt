import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import com.example.lwb.foodDiary.domain.model.Product
import com.example.lwb.foodDiary.domain.model.ProductDetails

@Dao
interface FoodDiaryDao {
    fun getProductsByName(productName: String): List<Product>

    fun getProductDetailsByName(productName: String): ProductDetails

    @Insert
    fun addProductToDiary(product: Product)

    // Для этого метода вам нужно будет создать соответствующую сущность и таблицу в базе данных
    // @Insert
    // fun addWaterToDiary(volume: Double)
}