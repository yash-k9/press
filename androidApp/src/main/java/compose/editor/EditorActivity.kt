package compose.editor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import compose.ComposeApp
import compose.widgets.ThemeAwareActivity
import me.saket.compose.shared.navigation.RealNavigator
import me.saket.compose.shared.navigation.ScreenKey.Back
import javax.inject.Inject

class EditorActivity : ThemeAwareActivity() {

  @field:Inject
  lateinit var editorView: EditorView.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    ComposeApp.component.inject(this)
    super.onCreate(savedInstanceState)

    val navigator = RealNavigator { screenKey ->
      when (screenKey) {
        Back -> finish()
        else -> error("Unhandled $screenKey")
      }
    }
    setContentView(editorView.create(this, navigator))
  }

  companion object {
    fun intent(context: Context): Intent = Intent(context, EditorActivity::class.java)
  }
}