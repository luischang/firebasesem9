package dev.luischang.firebasesem9.ui.slideshow

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.luischang.firebasesem9.R
import dev.luischang.firebasesem9.adapter.ProductApiAdapter
import dev.luischang.firebasesem9.databinding.FragmentSlideshowBinding
import dev.luischang.firebasesem9.model.ProductApiModel
import dev.luischang.firebasesem9.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class SlideshowFragment : Fragment() {

    private var lstProducts = listOf<ProductApiModel>()
    private lateinit var productApiAdapter: ProductApiAdapter
    private lateinit var etSearchProduct: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_slideshow, container, false)
        var rvProductApi = view.findViewById<RecyclerView>(R.id.rvProductApi)
        etSearchProduct = view.findViewById<EditText>(R.id.etSearchProduct)

        rvProductApi.layoutManager = LinearLayoutManager(requireContext())
        productApiAdapter = ProductApiAdapter(lstProducts)
        rvProductApi.adapter = productApiAdapter

        //Llamar a la carga de productos
        loadProducts()

        configureSearch()

        return view
    }

    private fun configureSearch(){
        //etSearchProduct text changed
        etSearchProduct.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filterProducts(p0.toString())
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        }
        )
    }

    //Función parar filtrar los productos
    private fun filterProducts(query: String) {
        val filteredList = lstProducts.filter { product ->
            product.description.contains(query, ignoreCase = true)
        }
        productApiAdapter.updateListProducts(filteredList)
    }

    private fun loadProducts() {
        val call = RetrofitInstance.api.getProducts()
        call.enqueue(object : retrofit2.Callback<List<ProductApiModel>> {
            override fun onResponse(
                call: Call<List<ProductApiModel>>,
                response: Response<List<ProductApiModel>>
            ) {
                if(response.isSuccessful) {
                    lstProducts = response.body() ?: emptyList()
                    productApiAdapter.updateListProducts(lstProducts)
                }
            }

            override fun onFailure(call: Call<List<ProductApiModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }


}