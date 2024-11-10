package dev.luischang.firebasesem9.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.luischang.firebasesem9.R
import dev.luischang.firebasesem9.model.ProductApiModel
import dev.luischang.firebasesem9.model.ProductModel

class ProductApiAdapter(private var lstProducts: List<ProductApiModel>):
    RecyclerView.Adapter<ProductApiAdapter.ViewHolder>(){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val ivProductApi = itemView.findViewById<ImageView>(R.id.ivProductApi)
        val tvDescriptionApi: TextView = itemView.findViewById(R.id.tvDescriptionApi)
        val tvPriceApi: TextView = itemView.findViewById(R.id.tvPriceApi)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater
                                .from(parent.context)
                                .inflate(R.layout.item_product_api, parent, false)
        return ViewHolder(layoutInflater)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemProduct = lstProducts[position]
        holder.tvDescriptionApi.text = itemProduct.description
        holder.tvPriceApi.text = itemProduct.price.toString()
        //Agregar Glide para la imagen
        Glide.with(holder.itemView.context)
            .load(itemProduct.imageUrl)
            .into(holder.ivProductApi)
    }
    override fun getItemCount(): Int {
        return lstProducts.size
    }

    //Método para actualizar la lista de productos
    fun updateListProducts(list: List<ProductApiModel>){
        lstProducts = list
        notifyDataSetChanged()
    }

}