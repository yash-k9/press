package press.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import me.saket.press.shared.db.NoteId
import press.home.NoteAdapter.NoteVH
import me.saket.press.shared.home.HomeUiModel.Note as Model

// TODO: rename to NoteListAdapter
class NoteAdapter : BaseHomeRowAdapter<Model, NoteVH>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    NoteVH(
      NoteRowView(parent.context).apply {
        setOnClickListener { clicks.onNext(model) }
      }
    )

  override fun onBindViewHolder(holder: NoteVH, position: Int) {
    holder.view.render(getItem(position))
  }

  fun viewHolderFor(noteId: NoteId, viewHolders: Sequence<ViewHolder>): ViewHolder? {
    return viewHolders.firstOrNull { it is NoteVH && it.view.model.id == noteId }
  }

  class NoteVH(val view: NoteRowView) : ViewHolder(view)
}
