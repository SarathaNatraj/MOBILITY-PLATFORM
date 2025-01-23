package com.example.personinfo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personinfo.R
import com.example.personinfo.adapter.PeopleAdapter
import com.example.personinfo.api.PersonApi
import com.example.personinfo.api.RetrofitInstance
import com.example.personinfo.model.Person
import com.example.personinfo.model.PersonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {
    private lateinit var peopleAdapter: PeopleAdapter
    private lateinit var recyclerView: RecyclerView
    private val personApi = PersonApi.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView=view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        fetchPeople()

        return view
    }

    private fun fetchPeople(){
        val q="lauren"
        personApi.getAllPeople(q = "lauren").enqueue(object : Callback<List<PersonResponse>> {
            override fun onResponse(call: Call<List<PersonResponse>>, response: Response<List<PersonResponse>>) {
                Log.d("","inside fetch people"+response)
                if(response.isSuccessful){
                    val people=response.body()?: emptyList()

                    recyclerView.adapter=PeopleAdapter(people){ person:Person ->
                        val bundle=Bundle().apply { putInt("personId",person.id) }

                        findNavController().navigate(R.id.action_mainFragment_to_detailFragment,bundle)
                    }
                //    recyclerView.adapter=peopleAdapter
                    recyclerView.run { adapter?.notifyDataSetChanged() }


                }else{
                    Toast.makeText(requireContext(),"Error to fetching people",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                Toast.makeText(requireContext(),"${t.message}",Toast.LENGTH_SHORT).show()
            }

        })
    }

}
