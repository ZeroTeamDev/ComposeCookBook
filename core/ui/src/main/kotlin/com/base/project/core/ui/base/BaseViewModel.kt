package com.base.project.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.project.core.common.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Base ViewModel class that provides common functionality for all ViewModels
 */
abstract class BaseViewModel<UiState, UiAction, UiEvent> : ViewModel() {
    
    // Internal mutable state
    protected val _uiState = MutableStateFlow(getInitialState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    
    // Events channel
    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()
    
    // Loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    // Error state
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    // Exception handler for coroutines
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception, "Error in ${this::class.simpleName}")
        handleError(exception)
    }
    
    /**
     * Handle UI actions from the UI layer
     */
    abstract fun handleAction(action: UiAction)
    
    /**
     * Get initial state for the ViewModel
     */
    protected abstract fun getInitialState(): UiState
    
    /**
     * Update UI state safely
     */
    protected fun updateState(update: (UiState) -> UiState) {
        _uiState.value = update(_uiState.value)
    }
    
    /**
     * Emit UI event
     */
    protected fun emitEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
    
    /**
     * Launch coroutine with error handling
     */
    protected fun launchWithErrorHandling(
        showLoading: Boolean = true,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(exceptionHandler) {
            if (showLoading) setLoading(true)
            clearError()
            
            try {
                block()
            } finally {
                if (showLoading) setLoading(false)
            }
        }
    }
    
    /**
     * Handle Result wrapper with automatic loading and error handling
     */
    protected suspend fun <T> handleResult(
        result: Result<T>,
        onSuccess: suspend (T) -> Unit,
        onError: (suspend (Throwable) -> Unit)? = null,
        onLoading: (suspend () -> Unit)? = null
    ) {
        when (result) {
            is Result.Loading -> {
                setLoading(true)
                onLoading?.invoke()
            }
            is Result.Success -> {
                setLoading(false)
                clearError()
                onSuccess(result.data)
            }
            is Result.Error -> {
                setLoading(false)
                if (onError != null) {
                    onError(result.exception)
                } else {
                    handleError(result.exception)
                }
            }
        }
    }
    
    /**
     * Collect Flow with automatic Result handling
     */
    protected fun <T> Flow<Result<T>>.collectWithHandling(
        onSuccess: suspend (T) -> Unit,
        onError: (suspend (Throwable) -> Unit)? = null,
        onLoading: (suspend () -> Unit)? = null
    ) {
        viewModelScope.launch(exceptionHandler) {
            collect { result ->
                handleResult(result, onSuccess, onError, onLoading)
            }
        }
    }
    
    /**
     * Set loading state
     */
    protected fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }
    
    /**
     * Handle errors
     */
    protected open fun handleError(throwable: Throwable) {
        val errorMessage = throwable.message ?: "An unknown error occurred"
        _error.value = errorMessage
        Timber.e(throwable, "Error in ${this::class.simpleName}: $errorMessage")
    }
    
    /**
     * Clear error state
     */
    protected fun clearError() {
        _error.value = null
    }
    
    /**
     * Retry last action - to be implemented by subclasses if needed
     */
    open fun retry() {
        // Override in subclasses to implement retry logic
    }
    
    /**
     * Refresh data - to be implemented by subclasses if needed
     */
    open fun refresh() {
        // Override in subclasses to implement refresh logic
    }
}

/**
 * Simple base ViewModel for basic use cases
 */
abstract class SimpleBaseViewModel : ViewModel() {
    
    // Loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    // Error state
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    // Exception handler
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception, "Error in ${this::class.simpleName}")
        handleError(exception)
    }
    
    /**
     * Launch coroutine with error handling
     */
    protected fun launchWithErrorHandling(
        showLoading: Boolean = true,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(exceptionHandler) {
            if (showLoading) _isLoading.value = true
            _error.value = null
            
            try {
                block()
            } finally {
                if (showLoading) _isLoading.value = false
            }
        }
    }
    
    /**
     * Handle errors
     */
    protected open fun handleError(throwable: Throwable) {
        val errorMessage = throwable.message ?: "An unknown error occurred"
        _error.value = errorMessage
        Timber.e(throwable, "Error in ${this::class.simpleName}: $errorMessage")
    }
    
    /**
     * Clear error state
     */
    protected fun clearError() {
        _error.value = null
    }
}

/**
 * UiState interface for common UI state properties
 */
interface UiState {
    val isLoading: Boolean get() = false
    val error: String? get() = null
}

/**
 * Common UI actions
 */
sealed interface CommonUiAction {
    object Retry : CommonUiAction
    object Refresh : CommonUiAction
    object ClearError : CommonUiAction
}

/**
 * Common UI events
 */
sealed interface CommonUiEvent {
    data class ShowSnackbar(val message: String) : CommonUiEvent
    data class ShowToast(val message: String) : CommonUiEvent
    data class Navigate(val route: String) : CommonUiEvent
    object NavigateBack : CommonUiEvent
} 