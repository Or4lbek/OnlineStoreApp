package com.example.onlinestoreapp.utils.base_classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.onlinestoreapp.R
import com.example.onlinestoreapp.utils.Inflate
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseBindingFragment<B : ViewBinding>(private val inflate: Inflate<B>) : Fragment() {

    protected abstract fun initViews(savedInstanceState: Bundle?)

    lateinit var binding: B

    private lateinit var progressDialog: AlertDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialog()
        initViews(savedInstanceState)
    }

    private fun initDialog() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setView(R.layout.dialog_progress)
        builder.setCancelable(false)
        progressDialog = builder.create()
    }

    fun showDialog() {
        progressDialog.show()
    }

    fun hideDialog() {
        progressDialog.dismiss()
    }
}