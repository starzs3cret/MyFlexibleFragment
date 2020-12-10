package id.unlink.myflexiblefragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import id.unlink.myflexiblefragment.databinding.FragmentDetailCategoryBinding

class DetailCategoryFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentDetailCategoryBinding

    var description: String? =null

    companion object{
        var EXTRA_NAME="extra_name"
        var EXTRA_DESCRIPTION= "extra_description"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_category,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btProfile.setOnClickListener(this)
        binding.btShowDialog.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null){
            val descFromBundle= savedInstanceState.getString(EXTRA_DESCRIPTION)
            description = descFromBundle
        }
        if (arguments != null){
            val categoryName = arguments?.getString(EXTRA_NAME)
            binding.textView3.text = categoryName
            binding.textView4.text = description
        }
    }

    override fun onClick(v: View?) {
        when (v){
            binding.btProfile->{
                val mIntent = Intent(activity,ProfilActivity::class.java)
                startActivity(mIntent)
            }
            binding.btShowDialog->{
                val mOptionDialogFragment = OptionDialogFragment()
                val mFragmentManager=childFragmentManager
                mOptionDialogFragment.show(mFragmentManager,OptionDialogFragment::class.java.simpleName)
            }
        }
    }
    internal var optionDialogListener: OptionDialogFragment.OnOptionDialogListener = object:OptionDialogFragment.OnOptionDialogListener{
        override fun onOptionChosen(text: String?) {
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
        }
    }


}