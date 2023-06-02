package com.vickikbt.kmptemplate.presentation.viewmodels

import KMP_Template.shared.MR
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.vickikbt.kmptemplate.pref.Preference
import com.vickikbt.kmptemplate.pref.PreferenceMutableState
import com.vickikbt.kmptemplate.presentation.utils.UiEvent
import com.vickikbt.kmptemplate.presentation.utils.UiText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel : ScreenModel {

 val scope: CoroutineScope = coroutineScope

  protected val _eventFlow = MutableSharedFlow<UiEvent>()
  open val eventFlow = _eventFlow.asSharedFlow()

   open fun showSnackBar(message: UiText?) {
    scope.launch {
      _eventFlow.showSnackBar(message ?: UiText.MStringResource(MR.strings.error_unknown))
    }
  }
  override fun onDispose() {
    onDestroy()
    super.onDispose()
  }
   open fun onDestroy() {
  }
   fun <T> Preference<T>.asState() = PreferenceMutableState(this, scope)
   fun <T> Preference<T>.asState(onChange: (T) -> Unit): PreferenceMutableState<T> {
    this.changes()
            .onEach { onChange(it) }
            .launchIn(scope)
    return PreferenceMutableState(this, scope)
  }
   fun <T> Flow<T>.asState(initialValue: T, onChange: (T) -> Unit= {}): State<T> {
    val state = mutableStateOf(initialValue)
    scope.launch {
      collect {
        state.value = it
        onChange(it)
      }
    }
    return state
  }
   fun <T> StateFlow<T>.asState(): State<T> {
    val state = mutableStateOf(value)
    scope.launch {
      collect { state.value = it }
    }
    return state
  }

}

suspend fun MutableSharedFlow<UiEvent>.showSnackBar(message: UiText?) {
    this.emit(
            UiEvent.ShowSnackbar(
                    uiText = message ?: UiText.MStringResource(MR.strings.error_unknown)
            )
    )
}
