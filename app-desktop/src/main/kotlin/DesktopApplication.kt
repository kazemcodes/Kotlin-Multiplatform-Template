import androidx.compose.ui.window.application
import com.vickikbt.kmptemplate.di.commonModule
import com.vickikbt.kmptemplate.di.initKoin
import org.koin.core.Koin
import org.koin.core.context.startKoin
import ui.screens.MainScreen


fun main() {
    //koin = initKoin(isDebug = false).koin

    startKoin {
        modules(commonModule(isDebug = false))
    }
    return application {
        MainScreen(applicationScope = this)
    }
}
