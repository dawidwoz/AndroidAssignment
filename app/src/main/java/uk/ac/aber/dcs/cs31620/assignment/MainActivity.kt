package uk.ac.aber.dcs.cs31620.assignment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uk.ac.aber.dcs.cs31620.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object UiController {
        private lateinit var binding: ActivityMainBinding

        fun hideBottomNav() {
            binding.navView.visibility = View.GONE
        }

        fun showBottomNav() {
            binding.navView.visibility = View.VISIBLE
        }

        fun hideToolbar() {
            binding.toolbar.visibility = View.GONE
        }

        fun showToolbar() {
            binding.toolbar.visibility = View.VISIBLE
        }

        fun displayToast(content: Context, text: Int) {
            val toast = Toast.makeText(content, text, Toast.LENGTH_LONG)
            toast.show()
        }

        fun hideSoftKeyboard(activity: Activity) {
            val inputMethodManager =
                (activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
                    hideSoftInputFromWindow(
                        activity.currentFocus!!.windowToken, 0)
                }
        }

        fun hideSoftKeyboard(activity: FragmentActivity) {
            val inputMethodManager =
                (activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
                    hideSoftInputFromWindow(
                        activity.currentFocus?.windowToken, 0)
                }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navView: BottomNavigationView = binding.navView
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_start, R.id.navigation_home, R.id.navigation_list, R.id.navigation_learn, R.id.navigation_test, R.id.navigation_settings))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

}
