package com.example.teamplanningapp.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teamplanningapp.databinding.FragmentTeamBinding

class TeamFragment : Fragment() {

    private lateinit var dataBinding: FragmentTeamBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentTeamBinding.inflate(inflater)
        /*animateTransition(
            HomeFragmentArgs.fromBundle(
                requireArguments()
            ).params)*/
        return dataBinding.root
    }
}