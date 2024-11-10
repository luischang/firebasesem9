package dev.luischang.firebasesem9.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.luischang.firebasesem9.R
import dev.luischang.firebasesem9.model.ProductModel

class ProductAdapter(private var lstProducts: List<ProductModel>):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>(){

        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val ivProduct: ImageView = itemView.findViewById(R.id.ivProduct)
            val tvName: TextView = itemView.findViewById(R.id.tvName)
            val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
            val tvStock : TextView = itemView.findViewById(R.id.tvStock)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_product, parent, false))
    }

    override fun getItemCount(): Int {
        return lstProducts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPosition = lstProducts[position]
        holder.tvName.text = itemPosition.name
        holder.tvPrice.text = itemPosition.price
        holder.tvStock.text = itemPosition.stock
        //Agregar picasso para cargar imagenes
        Picasso.get().load(itemPosition.imageUrl).into(holder.ivProduct)
    }


}