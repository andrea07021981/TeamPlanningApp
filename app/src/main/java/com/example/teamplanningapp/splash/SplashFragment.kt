package com.example.teamplanningapp.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.teamplanningapp.R
import com.example.teamplanningapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private val splashViewModel: SplashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    private lateinit var dataBinding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        dataBinding = FragmentSplashBinding.inflate(inflater);
        dataBinding.splashViewModel = splashViewModel
        dataBinding.lifecycleOwner = this

        splashViewModel.navigateToLogin.observe(this.viewLifecycleOwner, Observer {
            if (it == true) {
                val extras = FragmentNavigatorExtras(
                    dataBinding.workerImageview to "worker_imageview"
                )
                this
                    .findNavController()
                    .navigate(
                        R.id.action_splashFragment_to_loginFragment,
                        null,
                        NavOptions.Builder()
                            .setPopUpTo(R.id.splashFragment,
                                true).build(),
                        extras)
                splashViewModel.doneNavigating()
            }
        })
        return dataBinding.root
    }
}