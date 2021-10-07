package com.hakbah.task.ui.dashboard

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hakbah.task.R
import com.hakbah.task.data.Status
import com.hakbah.task.databinding.FragmentAddPostBinding
import com.hakbah.task.db.Post
import com.hakbah.task.utils.FileUtils
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class AddPostFragment : Fragment(R.layout.fragment_add_post) {

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000

        //Permission code
        private val PERMISSION_CODE = 1001
    }

    private val viewModel: DashboardViewModel by viewModels()

    private var uri: Uri? = null

    private var binding: FragmentAddPostBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mFragmentAddPostBinding = FragmentAddPostBinding.bind(view)
        binding = mFragmentAddPostBinding

        binding!!.buttonAdd.setOnClickListener {
            addPost()
        }

        binding!!.imageView.setOnClickListener {


            checkPermission()

        }
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_DENIED
            ) {
                //permission denied
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                //show popup to request runtime permission
                requestPermissions(permissions, PERMISSION_CODE)
            } else {
                //permission already granted
                pickImageFromGallery()
            }
        } else {
            //system OS is < Marshmallow
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, IMAGE_PICK_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {

            when {
                data!!.clipData != null -> {
                    binding!!.imageView.setImageURI(data.clipData!!.getItemAt(0).uri)
                    uri = data.clipData!!.getItemAt(0).uri

                }
                data.data != null -> {
                    binding!!.imageView.setImageURI(data.data)
                    uri = data.data!!
                }
                else -> {
                    Toast.makeText(requireContext(), "Failed to pick an Image", Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    pickImageFromGallery()
                } else {

                    Toast.makeText(requireContext(), "Permission Dined", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun addPost() {

        if (binding!!.edtName.text.toString().isEmpty()) {
            Toast.makeText(requireContext(), "Enter name", Toast.LENGTH_SHORT).show()
            return
        }

        if (binding!!.edtDescription.text.toString().isEmpty()) {
            Toast.makeText(requireContext(), "Enter Description", Toast.LENGTH_SHORT).show()
            return
        }

        if (uri == null) {
            Toast.makeText(
                requireContext(),
                "please pick an image from gallery",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val post = Post(
            name = binding!!.edtName.text.toString(),
            description = binding!!.edtDescription.text.toString(),
            time = getDatetime(),
            image = FileUtils.getRealPath(requireContext(), uri)
        )

        viewModel.addPost(post).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
                Status.SUCCESS -> {
                    findNavController().navigateUp()

                }
            }
        })
    }

    private fun getDatetime(): String {
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val cal = Calendar.getInstance()
        return inputFormat.format(cal.time)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }


}