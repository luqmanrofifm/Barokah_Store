package com.example.barokahstore.core.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.barokahstore.databinding.DialogCustomProgressBinding

class DialogCustomProgress : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = DialogCustomProgressBinding.inflate(layoutInflater)
        return view.root
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    companion object {

        const val TAG = "LoadingDialogFragment"

        private lateinit var customProgressDialog: DialogCustomProgress

        fun toggle(fragmentManager: FragmentManager, show: Boolean) {

            val customProgressDialogFragment: Fragment? = fragmentManager.findFragmentByTag(TAG)

            if (customProgressDialogFragment == null) customProgressDialog = DialogCustomProgress()

            if (show) {

                if (customProgressDialog.isAdded.not()) {

                    customProgressDialog

                    customProgressDialog.show(fragmentManager, TAG)
                }

            } else {

                if (customProgressDialog.isAdded) {

                    customProgressDialog

                    customProgressDialog.dismiss()
                }
            }
        }
    }
}