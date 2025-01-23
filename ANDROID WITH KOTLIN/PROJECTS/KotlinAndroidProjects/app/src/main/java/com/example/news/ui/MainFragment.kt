package com.example.news.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.adapter.UserAdapter
import com.example.news.apiService.RetrofitInstance
import com.example.news.R
import com.example.news.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {
    private lateinit var name:TextView
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_main, container, false)
        recyclerView=view.findViewById(R.id.recycleview)
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        fetchUser()
        return view
    }

    private fun fetchUser() {
        RetrofitInstance.api.getAllUsers().enqueue(object: Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful){
                    val person=response.body()?: emptyList()
                    recyclerView.adapter= UserAdapter(person){ user ->
                       // val bundle=Bundle().apply { "userId";user.id }
                        val action = MainFragmentDirections.actionMainFragment3ToDetailFragment2(user)
                        recyclerView.adapter?.notifyDataSetChanged()
                        findNavController().navigate(action)
                    }
                 //   recyclerView.adapter=userAdapter
                }else{
                    Toast.makeText(requireContext(),"Error to fecth",Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(requireContext(),"${t.message}",Toast.LENGTH_SHORT).show()
            }

        })
    }

}