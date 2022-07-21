package com.rebin.solulabtest.UI.fragments

import android.app.ProgressDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rebin.solulabtest.Adaptor.HomeviewAdaptor
import com.rebin.solulabtest.R
import com.rebin.solulabtest.viewmodel.HomefragmentViewModel

class Homefragment : Fragment() {

    private lateinit var viewModel: HomefragmentViewModel
    lateinit var recyclerview:RecyclerView
    lateinit var homeadaptor:HomeviewAdaptor

    lateinit var progressDialog : ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view=inflater.inflate(R.layout.fragment_homefragment, container, false)

        recyclerview=view.findViewById(R.id.recyclerview)
        return view    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("sghjfbsdj","ugwujchka")
        viewModel = ViewModelProvider(this).get(HomefragmentViewModel::class.java)
        progressDialog=ProgressDialog(requireActivity())
        progressDialog.setMessage("Application is loading, please wait")
        progressDialog.show()

        viewModel.getdata()

        setobserver()
      }

    private fun setobserver() {
        viewModel.mresponse.observe(requireActivity(), Observer {

            if (it.msg=="Success"){

                progressDialog.dismiss()
                GridLayoutManager(
                    requireActivity(),
                    3,
                    RecyclerView.VERTICAL,
                    false
                ).apply {
                    recyclerview.layoutManager = this
                }
                homeadaptor=HomeviewAdaptor(it.data.list)
                recyclerview.adapter=homeadaptor

            }
        })

        viewModel.merror.observe(requireActivity(), Observer {

            progressDialog.dismiss()
            Toast.makeText(requireActivity(),"Something went wrong",Toast.LENGTH_SHORT).show()
        })
    }

}