package br.com.aguiar.misocial.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.aguiar.misocial.R
import br.com.aguiar.misocial.domain.post.Posts
import br.com.aguiar.misocial.ui.viewholder.PostsViewHolder

class PostAdapter(
    private val callback: (Int) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<PostsViewHolder>() {

    private var dataSource: List<Posts> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_holder, parent, false)
        return PostsViewHolder(view, callback, context)
    }

    override fun getItemCount() = dataSource.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(dataSource[position])
    }

    fun setList(list: List<Posts>) {
        dataSource = list
        notifyDataSetChanged()
    }

    fun getList() = dataSource

}