package dev.ogabek.facebookappui.adapter

import android.content.Context
import android.view.*
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import dev.ogabek.facebookappui.R
import dev.ogabek.facebookappui.model.Story

class StoryAdapter(val context: Context, val stories: ArrayList<Story>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story_view, parent, false);
        return StoryViewHolder(view)
    }

    class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv_story_photo: ShapeableImageView = view.findViewById(R.id.iv_story_photo)
        val iv_story_profile: ShapeableImageView = view.findViewById(R.id.iv_story_profile)
        val tv_story_full_name: TextView = view.findViewById(R.id.tv_story_full_name)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val story = stories[position]

        if (holder is StoryViewHolder) {
            holder.apply {
                iv_story_photo.setImageResource(story.photo)
                iv_story_profile.setImageResource(story.profile)
                tv_story_full_name.text = story.fullName
            }
        }
    }

    override fun getItemCount() = stories.size
}