package com.rageh.apiwithflow.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.rageh.apiwithflow.R
import com.rageh.apiwithflow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tabLayout.selectTab(
            binding.tabLayout.getTabAt(
                viewModel.savedStateHandle.get<Int?>(
                    SELECTED_TAB_POSITION
                ) ?: 0
            )
        )
        binding.tabLayout.addOnTabSelectedListener(this)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        viewModel.savedStateHandle.set(SELECTED_TAB_POSITION, tab?.position)
        //kotlin is very flexible
        findNavController(R.id.nav_host_fragment).navigate(
            when (tab?.position) {
                0 -> R.id.action_global_postsListFragment
                1 -> R.id.action_global_albumsListFragment
                else -> 0
            }
        )
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}


}