package id.unlink.myflexiblefragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import id.unlink.myflexiblefragment.databinding.FragmentOptionDialogBinding


class OptionDialogFragment : DialogFragment(), View.OnClickListener {
    private lateinit var binding: FragmentOptionDialogBinding
    private var optionDialogListener: OnOptionDialogListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_option_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btClose.setOnClickListener(this)
        binding.btChose.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btClose -> dialog?.cancel()
            binding.btChose -> {
                val checkedRadioButtonId = binding.radioGroup.checkedRadioButtonId
                if (checkedRadioButtonId != -1) {
                    var coach: String? = null
                    when (checkedRadioButtonId) {
                        R.id.radioButton1 -> coach = binding.radioButton1.text.toString().trim()
                        R.id.radioButton2 -> coach = binding.radioButton2.text.toString().trim()
                        R.id.radioButton3 -> coach = binding.radioButton3.text.toString().trim()
                        R.id.radioButton4 -> coach = binding.radioButton4.text.toString().trim()
                    }
                    if (optionDialogListener != null) {
                        optionDialogListener?.onOptionChosen(coach)

                    }
                    dialog?.dismiss()

                }
            }
        }
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragment = parentFragment
        if (fragment is DetailCategoryFragment){
            val detailCategoryFragment = fragment
            this.optionDialogListener = detailCategoryFragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }


}