package br.com.aguiar.misocial.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.aguiar.misocial.R
import br.com.aguiar.misocial.domain.comment.Comments
import br.com.aguiar.misocial.ui.viewholder.CommentsViewHolder

class CommentAdapter(
    private val context: Context
): RecyclerView.Adapter<CommentsViewHolder>() {

    var dataSource: List<Comments> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_holder, parent, false)
        return CommentsViewHolder(view, context)
    }

    override fun getItemCount() = dataSource.size

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }
}