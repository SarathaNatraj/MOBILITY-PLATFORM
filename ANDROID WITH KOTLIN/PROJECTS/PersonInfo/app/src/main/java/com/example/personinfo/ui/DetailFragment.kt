package com.example.personinfo.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.personinfo.R
import com.example.personinfo.api.RetrofitInstance
import com.example.personinfo.databinding.FragmentDetailBinding
import com.example.personinfo.model.Person
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment() {
     private var  personId: Int = 0
    private lateinit var person: Person

    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDetailBinding.inflate(inflater,container,false)

        arguments?.getInt("PersonId")?.let { id->
            personId=id
            fetchPersonDetails(personId)
        }
        return binding.root
    }

    private fun fetchPersonDetails(personId: Int) {
        RetrofitInstance.api.getPersonById(personId).enqueue(object : Callback<Person> {

            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if(response.isSuccessful){
                    val person=response.body()
                    person?.let {
                       binding.nameTextView.text="Name: ${it.name}"
                        binding.idTextView.text="id: ${it.id}"
                        binding.emailTextView.text="id: ${it.email}"
                        binding.addressTextView.text="id: ${it.address}"
                        binding.companyTextView.text="id: ${it.company}"
                        binding.phoneTextView.text="id: ${it.phone}"
                        binding.usernameTextView.text="id: ${it.username}"
                    }

                }else{
                    Toast.makeText(requireContext(),"Error to fetch details",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                Toast.makeText(requireContext(),"${t.message}",Toast.LENGTH_SHORT).show()
            }

        })

    }

}