package br.com.aguiar.misocial.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.aguiar.misocial.R



class LoadingDialog : DialogFragment() {

    val popupWidth by lazy {
        resources.displayMetrics.widthPixels
    }

    val popupHeight by lazy {
        resources.displayMetrics.heightPixels / 4
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.loading_dialog_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(popupWidth, popupHeight)
    }

}