package ua.kpi.comsys.iv7213.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import ua.kpi.comsys.iv7213.R

class GalleryFragment : Fragment() {

    private val images: ArrayList<ImageView> = arrayListOf()
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var currentImage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        images.addAll(
            arrayListOf(
                view.findViewById(R.id.image1),
                view.findViewById(R.id.image2),
                view.findViewById(R.id.image3),
                view.findViewById(R.id.image4),
                view.findViewById(R.id.image5),
                view.findViewById(R.id.image6),
                view.findViewById(R.id.image7),
                view.findViewById(R.id.image8)
            )
        )

        val btn = view.findViewById<Button>(R.id.addImageButton)

        btn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            images[currentImage].setImageURI(imageUri)
            currentImage = (currentImage + 1) % images.size
        }
    }

}