package br.com.aguiar.misocial.ui.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.aguiar.misocial.R
import br.com.aguiar.misocial.domain.comment.Comments
import kotlinx.android.synthetic.main.card_view_holder.view.*

class CommentsViewHolder(
    itemView: View,
    private val context: Context
): RecyclerView.ViewHolder(itemView) {

    fun bind(comment: Comments) {
        itemView.titleTxt.visibility = View.GONE
        itemView.descriptionTxt.text = comment.body
        itemView.ownerTxt.text = context
            .getString(R.string.text_posted_by, comment.email.split("@")[0])

    }

}