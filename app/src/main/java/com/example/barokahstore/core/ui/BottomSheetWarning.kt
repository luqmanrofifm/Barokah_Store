package com.example.barokahstore.core.ui

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.example.barokahstore.R
import com.example.barokahstore.databinding.BottomSheetWarningBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetWarning : BottomSheetDialogFragment() {
    private var _binding: BottomSheetWarningBinding? = null
    private val binding get() = _binding!!

    var okListener: (() -> Unit)? = null
    var cancelListener: (() -> Unit)? = null
    var dismissListener: (() -> Unit)? = null

    private var title: String? = ""

    private var message: String? = ""

    private var positiveText: String? = ""
    private var negativeText: String? = ""

    private var isDialogCancelable: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetWarningBinding.inflate(
            requireActivity().layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.invoke()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {

        binding.title.apply {
            text = title
        }

        binding.title.isVisible = binding.title.text.isNotEmpty()

        binding.message.apply {
            text = message
            isVisible = text.isNotEmpty()
        }

        binding.cancelButton.apply {
            isVisible = negativeText != null && negativeText!!.isNotEmpty()
            if (isVisible) text = negativeText
        }

        isCancelable = isDialogCancelable
    }

    private fun initListener() {

        binding.okButton.setOnClickListener {
            okListener?.invoke()
            dismiss()
        }

        binding.cancelButton.setOnClickListener {
            cancelListener?.invoke()
            dismiss()
        }
    }

    data class Builder(
        var fragmentManager: FragmentManager,
        var title: String? = null,
        var message: String? = null,
        var positiveText: String? = null,
        var negativeText: String? = null,
        var dismissListener: (() -> Unit)? = null,
        var okListener: (() -> Unit)? = null,
        var cancelListener: (() -> Unit)? = null,
        var isDialogCancelable: Boolean = true
    ) {

        fun setTitle(title: String?) = apply {
            this.title = title
        }

        fun setMessage(message: String?) = apply {
            this.message = message
        }

        fun setPositive(text: String = "", okListener: (() -> Unit)?) = apply {
            this.positiveText = text
            this.okListener = okListener
        }

        fun setNegative(
            text: String,
            cancelListener: (() -> Unit)?,
        ) = apply {
            this.negativeText = text
            this.cancelListener = cancelListener
        }

        fun setDismiss(dismissListener: (() -> Unit)?) = apply {
            this.dismissListener = dismissListener
        }

        fun show() {

            val dialog = BottomSheetWarning()

            dialog.title = title
            dialog.message = message
            dialog.positiveText = positiveText
            dialog.negativeText = negativeText
            dialog.dismissListener = dismissListener
            dialog.isDialogCancelable = isDialogCancelable

            okListener?.let {
                dialog.okListener = okListener
            }

            dismissListener?.let {
                dialog.cancelListener = cancelListener
            }

            dialog.show(fragmentManager, TAG)
        }
    }

    companion object {
        private val TAG: String = BottomSheetWarning::class.java.name
    }
}