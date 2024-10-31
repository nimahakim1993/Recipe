package com.example.recipe2.presentation.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recipe2.MyActivity
import com.example.recipe2.R
import com.example.recipe2.databinding.ActivityMainBinding
import com.example.recipe2.presentation.viewmodel.HomeViewModel
import com.example.recipe2.presentation.viewmodel.HomeViewModelFactory
import com.example.recipe2.presentation.viewmodel.MainViewModel
import com.example.recipe2.presentation.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : MyActivity() {
    private lateinit var binding : ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var currentlabel :String

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { mainViewModelFactory }

    private var isBackPressed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.searchFragment, R.id.whatCookFragment, R.id.profileFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
//            val searchItem = binding.toolbar.menu.findItem(R.id.search_toolbar)
//            searchItem?.isVisible = false
//            showBottomNavigation()

//            mainViewModel.setCurrentFragment(destination.id)
            when (destination.id) {
                R.id.homeFragment -> {
//                    supportActionBar!!.show()
                    showBottomNavigation()
                }
                R.id.recipeFragment ->{
                    supportActionBar?.title = destination.label
                    hideBottomNavigation()
                }
                R.id.detailFragment ->{
                    supportActionBar?.title = destination.label
                }
                R.id.searchFragment -> {
                    showBottomNavigation()
                }
                R.id.whatCookFragment -> {
                    showBottomNavigation()
                }
                R.id.profileFragment -> {
                    showBottomNavigation()
                }
            }
        }

        mainViewModel.currentFragmentLabel.observe(this){ label ->
            val destinationId = navController.currentDestination?.id
//            if (destinationId == R.id.recipeFragment || destinationId == R.id.detailFragment) {
                supportActionBar?.title = label
//            }
        }

//        mainViewModel.currentFragmentId.observe(this){
//            if (it == R.id.formsFragment || it == R.id.inspectionDocumentFragment){
//                if (!checkStoragePermission())
//                    requestStoragePermission()
//            }
//            if (it == R.id.loginFragment && !checkSmsPermission()){
//                requestSmsPermission()
//            }
//            if (it != R.id.loginFragment && it != R.id.splashFragment) {
//                if (!isRunningLocationService) {
//                    if (checkPermission()) {
//                        isRunningLocationService = true
//                        locationService.startSendingGpsTask()
//                    } else
//                        requestPermission()
//                }
//            }else{
//                locationService.stopSendingGpsTask()
//            }
//        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    private fun hideBottomNavigation(){
        binding.navView.clearAnimation()
        binding.navView.animate().translationY(binding.navView.height.toFloat()).duration = 300
        binding.navView.visibility = View.GONE
    }

    private fun showBottomNavigation(){
        binding.navView.clearAnimation()
        binding.navView.animate().translationY(0f).duration = 300
        binding.navView.visibility = View.VISIBLE
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val currentDestinationId = navController.currentDestination?.id
        // Check if the current destination is one of the destinations in the app bar configuration
        val isInAppBarDestinations = currentDestinationId in appBarConfiguration.topLevelDestinations

        if (isInAppBarDestinations) {
            if (!isBackPressed) {
                Toast.makeText(applicationContext, getString(R.string.desc_back_application), Toast.LENGTH_SHORT).show()
                isBackPressed = true
                Handler(Looper.getMainLooper()).postDelayed({ isBackPressed = false }, 3000)
            } else
                finish()
        }
//        else if (currentDestinationId == R.id.loginFragment || currentDestinationId == R.id.splashFragment)
////            exitProcess(0)
//            finish()
        else
            super.onBackPressed()
    }

}