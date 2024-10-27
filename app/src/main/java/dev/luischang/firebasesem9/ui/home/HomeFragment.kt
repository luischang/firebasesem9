package dev.luischang.firebasesem9.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import dev.luischang.firebasesem9.adapter.ProductAdapter
import dev.luischang.firebasesem9.databinding.FragmentHomeBinding
import dev.luischang.firebasesem9.model.ProductModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val db = FirebaseFirestore.getInstance()
        val rvProduct = binding.rvProduct
        var lstProducts : List<ProductModel>

        db.collection("products")
            .addSnapshotListener { value, error ->
                if(error != null){
                    Log.e("Firestore Error", error.message.toString())
                    return@addSnapshotListener
                }

                lstProducts = value!!.documents.map {document ->
                    ProductModel(
                        document["name"].toString(),
                        document["price"].toString(),
                        document["stock"].toString(),
                        document["imageUrl"].toString())
                }
                rvProduct.adapter = ProductAdapter(lstProducts)
                rvProduct.layoutManager = LinearLayoutManager(requireContext())
            }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}