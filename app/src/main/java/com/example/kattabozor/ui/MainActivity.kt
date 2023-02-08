package com.example.kattabozor.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kattabozor.R
import com.example.kattabozor.databinding.ActivityMainBinding
import com.example.kattabozor.entity.Product
import com.example.kattabozor.ui.adapters.ProductAdapter
import com.example.kattabozor.util.VerticalMarginItemDecoration
import com.example.kattabozor.util.dpToPx
import com.example.kattabozor.util.invisible
import com.example.kattabozor.util.visible
import com.example.kattabozor.util.Resource

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVM()
        loadProducts()
        observe()
        loadAdapter()
    }

    private fun observe() {
        viewModel.getProducts.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.progress.visible()
                }
                is Resource.NoInternet -> {
                    binding.progress.invisible()
                    Toast.makeText(this, getString(R.string.internet_error), Toast.LENGTH_SHORT).show()
                }
                is Resource.Error<*> -> {
                    binding.progress.invisible()
                    Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is Resource.Success<*> -> {
                    binding.progress.invisible()
                    val data = it.data as List<Product>
                    adapter.submitList(data)
                }
            }
        }
    }

    private fun loadAdapter() {
        adapter = ProductAdapter(context = this)
        binding.rv.adapter = adapter
        val itemDecoration = VerticalMarginItemDecoration(16.dpToPx(displayMetrics = resources.displayMetrics))
        binding.rv.addItemDecoration(itemDecoration)
    }

    private fun loadProducts() {
        viewModel.getProducts(context = this)
    }

    private fun initVM() {
        viewModel = ViewModelProvider(this)[ProductListViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}