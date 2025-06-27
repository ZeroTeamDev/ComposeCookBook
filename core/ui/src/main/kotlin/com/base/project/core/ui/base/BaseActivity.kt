package com.base.project.core.ui.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.base.project.core.design.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Base Activity class that provides common functionality for all activities
 */
abstract class BaseActivity : ComponentActivity() {
    
    /**
     * Whether to show splash screen
     */
    protected open val showSplashScreen: Boolean = true
    
    /**
     * Whether to enable edge-to-edge display
     */
    protected open val enableEdgeToEdge: Boolean = true
    
    /**
     * Whether to apply system UI theming (status bar, navigation bar)
     */
    protected open val applySystemUiTheming: Boolean = true
    
    override fun onCreate(savedInstanceState: Bundle?) {
        // Install splash screen if enabled
        if (showSplashScreen) {
            installSplashScreen()
        }
        
        super.onCreate(savedInstanceState)
        
        Timber.d("${this::class.simpleName} onCreate")
        
        // Enable edge-to-edge if enabled
        if (enableEdgeToEdge) {
            enableEdgeToEdge()
        }
        
        setContent {
            AppTheme {
                // Apply system UI theming
                if (applySystemUiTheming) {
                    ApplySystemUiTheming()
                }
                
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
        
        // Initialize after content is set
        initializeActivity()
    }
    
    /**
     * Main content of the activity - to be implemented by subclasses
     */
    @Composable
    abstract fun Content()
    
    /**
     * Initialize activity after content is set
     */
    protected open fun initializeActivity() {
        // Setup observers, initialize components, etc.
        setupObservers()
        initializeComponents()
    }
    
    /**
     * Setup observers for lifecycle-aware components
     */
    protected open fun setupObservers() {
        // Observe app state changes
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                observeUiState()
            }
        }
    }
    
    /**
     * Initialize components (analytics, crash reporting, etc.)
     */
    protected open fun initializeComponents() {
        // Override in subclasses to initialize specific components
    }
    
    /**
     * Observe UI state changes - to be implemented by subclasses if needed
     */
    protected open suspend fun observeUiState() {
        // Override in subclasses to observe specific UI state
    }
    
    override fun onStart() {
        super.onStart()
        Timber.d("${this::class.simpleName} onStart")
    }
    
    override fun onResume() {
        super.onResume()
        Timber.d("${this::class.simpleName} onResume")
    }
    
    override fun onPause() {
        super.onPause()
        Timber.d("${this::class.simpleName} onPause")
    }
    
    override fun onStop() {
        super.onStop()
        Timber.d("${this::class.simpleName} onStop")
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Timber.d("${this::class.simpleName} onDestroy")
    }
}

/**
 * Composable function to apply system UI theming
 */
@Composable
private fun ApplySystemUiTheming() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    
    LaunchedEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = androidx.compose.ui.graphics.Color.Transparent,
            darkIcons = useDarkIcons
        )
    }
}

/**
 * Check if system is in dark theme
 */
@Composable
private fun isSystemInDarkTheme(): Boolean {
    return androidx.compose.foundation.isSystemInDarkTheme()
} 