package dev.ogabek.facebookappui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import dev.ogabek.facebookappui.R
import dev.ogabek.facebookappui.model.Feed

class FeedAdapter(val context: Context, val feeds: ArrayList<Feed>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    private val TYPE_HEADER_VIEW = 0
    private val TYPE_STORY_VIEW = 1
    private val TYPE_POST_VIEW = 2

    override fun getItemViewType(position: Int): Int {
        val feed = feeds[position]
        
        return when {
            feed.isHeader -> {
                TYPE_HEADER_VIEW
            }
            feed.stories.size > 0 -> {
                TYPE_STORY_VIEW
            }
            else -> {
                TYPE_POST_VIEW
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER_VIEW -> {
                val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_header, parent, false)
                HeaderViewHolder(view)
            }
            TYPE_STORY_VIEW -> {
                val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_story, parent, false)
                StoryViewHolder(context, view)
            }
            else -> {
                val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_post, parent, false)
                PostViewHolder(view)
            }
        }
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    class StoryViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewStory)

        init {
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv_post_profile: ShapeableImageView = view.findViewById(R.id.iv_post_profile)
        val tv_post_full_name: TextView = view.findViewById(R.id.tv_post_full_name)
        val iv_post_photo: ImageView = view.findViewById(R.id.iv_post_photo)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = feeds[position]

        if (holder is PostViewHolder) {
            holder.apply {
                iv_post_photo.setImageResource(feed.post!!.photo)
                iv_post_profile.setImageResource(feed.post!!.profile)
                tv_post_full_name.text = feed.post!!.fullName
            }
        }

        if (holder is StoryViewHolder) {
            holder.apply {
                recyclerView.adapter = StoryAdapter(context, feed.stories)
            }
        }

    }

    override fun getItemCount() = feeds.size
}