package br.com.aguiar.misocial.ui.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.aguiar.misocial.R
import br.com.aguiar.misocial.domain.post.Posts
import kotlinx.android.synthetic.main.card_view_holder.view.*

class PostsViewHolder(
    itemView: View,
    private val callback: (Int) -> Unit,
    private val context: Context
) : RecyclerView.ViewHolder(itemView) {

    fun bind(post: Posts) {
        itemView.titleTxt.text = context
            .getString(R.string.text_titulo, post.title)
        itemView.descriptionTxt.text = post.body
        itemView.ownerTxt.text = context
            .getString(R.string.text_created_by, post.createdBy)

        itemView.setOnClickListener { callback.invoke(post.id) }
    }


}