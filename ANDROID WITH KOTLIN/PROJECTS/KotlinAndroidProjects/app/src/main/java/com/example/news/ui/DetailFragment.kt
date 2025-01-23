package com.example.news.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.news.apiService.RetrofitInstance
import com.example.news.R
import com.example.news.model.User
import com.example.news.databinding.FragmentDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private  var userId:Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentDetailBinding.inflate(inflater,container,false)
        val view=inflater.inflate(R.layout.fragment_detail, container, false)
        /*arguments?.getInt("userId")?.let {id->
            userId=id
            fetchDetails(userId)
        }
*/
        val user = arguments?.getParcelable<User>("user")
        Toast.makeText(requireContext(),"detail frag",Toast.LENGTH_SHORT).show()

        if (user != null) {
            Toast.makeText(requireContext(),"user is error"+user.name,Toast.LENGTH_SHORT).show()
        }

        if (user != null) {
            userId=user.id
            view.findViewById<TextView>(R.id.name).text = user?.name
            view.findViewById<TextView>(R.id.email).text = user?.email
            binding.name.text = user.name
            binding.email.text = user.email

            //   fetchDetails(userId)
        }else{
            Toast.makeText(requireContext(),"user is error",Toast.LENGTH_SHORT).show()

        }

        return view
    }

    private fun fetchDetails(userId:Int) {
        RetrofitInstance.api.getUserById(userId).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        binding.name.text = user.name
                        binding.email.text = user.email
                        //binding.company.text=user.company


                    }
                }else{
                    Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(requireContext(),"${t.message}",Toast.LENGTH_SHORT).show()
            }

        })
    }


}