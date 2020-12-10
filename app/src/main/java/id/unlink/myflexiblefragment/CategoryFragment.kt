package id.unlink.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import id.unlink.myflexiblefragment.databinding.FragmentCategoryBinding


class CategoryFragment : Fragment(), View.OnClickListener {

    private lateinit var binding:FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_category,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == binding.button2){
            val mDetailCategoryFragment = DetailCategoryFragment()
            val mBundle = Bundle()
            mBundle.putString(DetailCategoryFragment.EXTRA_NAME,"Lifestyle")
            val description = "Kategori ini akan berisi produk-produk lifestyle"

            mDetailCategoryFragment.arguments=mBundle
            mDetailCategoryFragment.description = description

            val mFragmentManager= fragmentManager
            mFragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_container,mDetailCategoryFragment,DetailCategoryFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }


}